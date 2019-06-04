package com.example.acer.pocjil;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderServices extends AsyncTaskLoader<ArrayList<ServiceExpertSpace>> {
    public LoaderServices(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<ServiceExpertSpace> loadInBackground() {
        ArrayList<ServiceExpertSpace> serviceDetails = new ArrayList<>();
        String data = (new SetHttpConnectionGet()).getInputStreamData();
        SetHTTPConnectionPost setHTTPConnectionPost = new SetHTTPConnectionPost();
        setHTTPConnectionPost.sendPost();
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser json_news_parser = new JsonParser();
                serviceDetails = json_news_parser.getServiceSpace(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return serviceDetails;
        }
        return null;
    }
}
