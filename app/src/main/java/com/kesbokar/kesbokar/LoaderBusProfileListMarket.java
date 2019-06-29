package com.kesbokar.kesbokar;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderBusProfileListMarket  extends AsyncTaskLoader<ArrayList<MarketProfileList>> {
    private String BaseUrl;
    public LoaderBusProfileListMarket(Context context, String url){
        super(context);
        BaseUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<MarketProfileList> loadInBackground() {
        ArrayList<MarketProfileList> marketProfileLists = new ArrayList<>();
        String data = (new SetHttpConnection(BaseUrl)).getInputStreamData(BaseUrl);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonParser = new JsonParser();
                marketProfileLists = jsonParser.getMarProfileList(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return marketProfileLists;
        }
        return null;
    }
}
