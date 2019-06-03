package com.example.kesbokar;
import android.content.Context;

import android.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderMarket extends AsyncTaskLoader<ArrayList<MarketPlaceApi>> {
    public LoaderMarket(Context context) {
        super(context);
    }
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<MarketPlaceApi> loadInBackground() {
        ArrayList<MarketPlaceApi> market=new ArrayList<>();
        String data=(new SetHttpConnection("http://serv.kesbokar.com.au/jil.0.1/v2/product-featured?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK#")).getInputStreamData();
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
