package com.kesbokar.kesbokar;
import android.content.Context;

import android.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderMarket extends AsyncTaskLoader<ArrayList<MarketPlaceApi>> {
    ArrayList<MarketPlaceApi> market;
    public LoaderMarket(Context context) {
        super(context);
    }
    protected void onStartLoading(){
        super.onStartLoading();
        if (market != null) {
            // Use cached data
            deliverResult(market);
        } else {
            // We have no data, so kick off loading it
            forceLoad();
        }
    }

    @Override
    public ArrayList<MarketPlaceApi> loadInBackground() {
        market=new ArrayList<>();
        String data=(new SetHttpConnection("https://serv.kesbokar.com.au/jil.0.1/v2/product-featured?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK#")).getInputStreamData();
        if(data != null){
            try {
                JsonParser json_news_parser = new JsonParser();
                market = json_news_parser.getMarket(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return market;
        }
        return null;
    }
}
