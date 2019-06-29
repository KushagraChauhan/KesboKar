package com.kesbokar.kesbokar;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderMarketSearch extends AsyncTaskLoader<ArrayList<String>> {
    private String Query;
    private String BaseUrl;
    public LoaderMarketSearch(Context context, String Query, String url){
        super(context);
        this.Query = Query;
        BaseUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<String> loadInBackground() {
        ArrayList<String> businessSearchBarsValues = new ArrayList<>();
        String data = (new SetHttpPost()).sendPostMarkAndBus(Query,BaseUrl);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonBusSrch = new JsonParser();
                businessSearchBarsValues = jsonBusSrch.getBusinessSearch(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return businessSearchBarsValues;
        }
        return null;
    }
}
