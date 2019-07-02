package com.kesbokar.kesbokar;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderBusSearch extends AsyncTaskLoader<ArrayList<String>> {
    private String Query;
    private String base_url;
    public LoaderBusSearch(Context context, String Query,String url){
        super(context);
        this.Query = Query;
        base_url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<String> loadInBackground() {
        ArrayList<String> businessSearchBarsValues = new ArrayList<>();
        String data = (new SetHttpPost()).sendPostMarkAndBus(Query,base_url);
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
