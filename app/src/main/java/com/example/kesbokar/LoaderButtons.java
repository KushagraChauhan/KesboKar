package com.example.kesbokar;
import android.content.Context;

import android.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderButtons extends AsyncTaskLoader<ArrayList<ButtonsDetails>> {

    private static String Base_Url;
    public LoaderButtons(Context context, String url){
        super(context);
        Base_Url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<ButtonsDetails> loadInBackground() {
        ArrayList<ButtonsDetails> buttonsDetails = new ArrayList<>();
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
}