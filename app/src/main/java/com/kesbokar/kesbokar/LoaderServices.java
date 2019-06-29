package com.kesbokar.kesbokar;

import android.content.Context;

import android.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderServices extends AsyncTaskLoader<ArrayList<ServiceExpertSpace>> {
    ArrayList<ServiceExpertSpace> serviceDetails;
    public LoaderServices(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (serviceDetails != null) {
            // Use cached data
            deliverResult(serviceDetails);
        } else {
            // We have no data, so kick off loading it
            forceLoad();
        }
    }

    @Override
    public ArrayList<ServiceExpertSpace> loadInBackground() {
        serviceDetails = new ArrayList<>();
        String data = (new SetHttpConnection("https://serv.kesbokar.com.au/jil.0.1/v2/yellowpage-featured?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK")).getInputStreamData();

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

    @Override
    public void deliverResult(ArrayList<ServiceExpertSpace> data) {
        super.deliverResult(data);
    }
}

