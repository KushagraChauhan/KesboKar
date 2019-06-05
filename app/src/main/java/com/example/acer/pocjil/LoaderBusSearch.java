package com.example.acer.pocjil;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderBusSearch extends AsyncTaskLoader<ArrayList<BusinessSearchBar>> {
    private String Query;
    public LoaderBusSearch(Context context, String Query){
        super(context);
        this.Query = Query;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<BusinessSearchBar> loadInBackground() {
        ArrayList<BusinessSearchBar> businessSearchBars = new ArrayList<>();
        String data = (new SetHTTPConnectionPost()).sendPost(Query);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonBusSrch = new JsonParser();
                businessSearchBars = jsonBusSrch.getBusinessSearch(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return businessSearchBars;
        }
        return null;
    }
}
