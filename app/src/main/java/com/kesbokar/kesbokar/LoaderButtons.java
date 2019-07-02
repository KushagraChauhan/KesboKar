package com.kesbokar.kesbokar;
import android.content.Context;

import android.content.Context;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderButtons extends AsyncTaskLoader<ArrayList<ButtonsDetails>> {
    ArrayList<ButtonsDetails> buttonsDetails;
    private static String Base_Url;
    public LoaderButtons(Context context, String url){
        super(context);
        this.Base_Url = url;
        if (buttonsDetails != null) {
            // Use cached data
            deliverResult(buttonsDetails);
        } else {
            // We have no data, so kick off loading it
            forceLoad();
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<ButtonsDetails> loadInBackground() {
        buttonsDetails = new ArrayList<>();
        String data = (new SetHttpConnection(Base_Url)).getInputStreamData();

        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser json_news_parser = new JsonParser();
                buttonsDetails = json_news_parser.getbtndata(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return buttonsDetails;
        }
        return null;
    }

    @Override
    public void deliverResult(ArrayList<ButtonsDetails> data) {
        super.deliverResult(data);
    }
}