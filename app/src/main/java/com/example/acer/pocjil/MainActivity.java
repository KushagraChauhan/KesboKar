package com.example.acer.pocjil;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<ButtonsDetails>> {

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
    public Loader<ArrayList<ButtonsDetails>> onCreateLoader(int i, Bundle bundle) {
        LoaderButtons loaderButtons = new LoaderButtons(this);
        return loaderButtons;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ButtonsDetails>> loader, ArrayList<ButtonsDetails> buttonsDetails) {
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
    public void onLoaderReset(Loader<ArrayList<ButtonsDetails>> loader) {

    }
}
