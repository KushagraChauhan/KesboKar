package com.example.acer.pocjil;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;



import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BusinessSearchBar>> {

//    TextView txtId,txtImage,txtUrl,txtIcon,txtTitle;

    private String query;
    private ProgressDialog progressDialog;
    ArrayList<BusinessSearchBar> mSrchBar;
    BusSrchAdapter busSrchAdapter;
    //ListView mListView;

    private AutoCompleteTextView userValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_bar);
        query = "";
        userValue = findViewById(R.id.user_value);


        getLoaderManager().initLoader(0,null,MainActivity.this);

        userValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query = s.toString();
                if(query.length()>2)
                    getLoaderManager().restartLoader(0,null,MainActivity.this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //mListView = findViewById(R.id.mlistView);
//        txtIcon = findViewById(R.id.icon);
//        txtId = findViewById(R.id.id);
//        txtImage = findViewById(R.id.image);
//        txtTitle = findViewById(R.id.title);
//        txtUrl = findViewById(R.id.url);
    }

    @Override
    public Loader<ArrayList<BusinessSearchBar>> onCreateLoader(int i, Bundle bundle) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LoaderBusSearch loaderBusSearch = new LoaderBusSearch(this,query);
        return loaderBusSearch;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BusinessSearchBar>> loader, ArrayList<BusinessSearchBar> businessSearchBars) {
        switch (loader.getId()){
            case 0:
                if(businessSearchBars.size()!=0 && query.length()>2){

//                    ButtonAdapter adapter = new ButtonAdapter(this,buttonsDetails);
//                    ServicesAdapter adapter = new ServicesAdapter(this,serviceExpertSpaces);
//                    mListView.setAdapter(adapter);
                    BusSrchAdapter busSrchAdapter = new BusSrchAdapter(businessSearchBars,this);
                    busSrchAdapter.notifyDataSetChanged();
                    userValue.setAdapter(busSrchAdapter);
                    userValue.setThreshold(2);

                    progressDialog.dismiss();
                }
                else{
                    //Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BusinessSearchBar>> loader) {

    }
}

/* @Override
    public Loader<ArrayList<ServiceExpertSpace>> onCreateLoader(int i, Bundle bundle) {
//        LoaderButtons loaderButtons = new LoaderButtons(this);
        LoaderServices loaderServices = new LoaderServices(this);
        return loaderServices;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ServiceExpertSpace>> loader, ArrayList<ServiceExpertSpace> serviceDetails) {
        switch (loader.getId()){
            case 0:
                if(buttonsDetails.size()!=0){
                    ButtonAdapter adapter = new ButtonAdapter(this,buttonsDetails);
                    mListView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ServiceExpertSpace>> loader) {

    }*/