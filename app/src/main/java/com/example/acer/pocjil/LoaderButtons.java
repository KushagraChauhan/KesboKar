package com.example.acer.pocjil;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderButtons extends AsyncTaskLoader<ArrayList<ButtonsDetails>> {

    public LoaderButtons(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<ButtonsDetails> loadInBackground() {
        ArrayList<ButtonsDetails> buttonsDetails = new ArrayList<>();
        String data = (new SetHttpConnectionGet()).getInputStreamData();

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
