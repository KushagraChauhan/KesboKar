package com.kesbokar.kesbokar;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderBtnSearch extends AsyncTaskLoader<ArrayList<ExampleItem>> {
    private String Query;
    private String Suburb;
    private String BaseUrl;
    private int stateId;
    private String type;
    private double  lat, longitude;
    public LoaderBtnSearch(Context context, String Query, String suburb, String url, int stateId, String type, double lat, double longitude){
        super(context);
        this.Query = Query;
        this.Suburb = suburb;
        BaseUrl = url;
        this.stateId = stateId;
        this.type = type;
        this.lat = lat;
        this.longitude = longitude;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<ExampleItem> loadInBackground() {
        ArrayList<ExampleItem> btnSearchData = new ArrayList<>();
        String data = (new SetHttpPost()).sendPostSearchBtn(Query,Suburb,stateId,type,lat,longitude,BaseUrl);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonBtnSrch = new JsonParser();
                btnSearchData = jsonBtnSrch.getBtnSrchData(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return btnSearchData;
        }
        return btnSearchData;
    }
}
