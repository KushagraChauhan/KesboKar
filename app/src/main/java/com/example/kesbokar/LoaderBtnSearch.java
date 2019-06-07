package com.example.kesbokar;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderBtnSearch extends AsyncTaskLoader<String> {
    private String Query;
    private String Suburb;
    private String BaseUrl;
    private int stateId;
    private String type;
    public LoaderBtnSearch(Context context, String Query, String suburb, String url, int stateId, String type){
        super(context);
        this.Query = Query;
        this.Suburb = suburb;
        BaseUrl = url;
        this.stateId = stateId;
        this.type = type;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
//        ArrayList<String> businessSearchBarsValues = new ArrayList<>();
        String data = (new SetHttpPost()).sendPostSearchBtn(Query,Suburb,stateId,type,BaseUrl);
        //call jsonParser only if the data is not null
//        if(data != null){
//            try {
//                JsonParser jsonBusSrch = new JsonParser();
//                businessSearchBarsValues = jsonBusSrch.getBusinessSearch(data);
//            }catch (Throwable t){
//                t.printStackTrace();
//            }
//            return businessSearchBarsValues;
//        }
        return data;
    }
}
