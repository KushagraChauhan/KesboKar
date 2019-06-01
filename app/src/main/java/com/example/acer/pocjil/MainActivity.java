package com.example.acer.pocjil;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<ServiceExpertSpace>> {

//    TextView txtId,txtImage,txtUrl,txtIcon,txtTitle;

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        mListView = findViewById(R.id.mlistView);
//        txtIcon = findViewById(R.id.icon);
//        txtId = findViewById(R.id.id);
//        txtImage = findViewById(R.id.image);
//        txtTitle = findViewById(R.id.title);
//        txtUrl = findViewById(R.id.url);
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<ArrayList<ServiceExpertSpace>> onCreateLoader(int i, Bundle bundle) {
        LoaderServices loaderServices = new LoaderServices(this);
        return loaderServices;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ServiceExpertSpace>> loader, ArrayList<ServiceExpertSpace> serviceExpertSpaces) {
        switch (loader.getId()){
            case 0:
                if(serviceExpertSpaces.size()!=0){
//                    ButtonAdapter adapter = new ButtonAdapter(this,buttonsDetails);
                    ServicesAdapter adapter = new ServicesAdapter(this,serviceExpertSpaces);
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