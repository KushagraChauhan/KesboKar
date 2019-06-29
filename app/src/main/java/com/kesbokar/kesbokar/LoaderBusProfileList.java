package com.kesbokar.kesbokar;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderBusProfileList extends AsyncTaskLoader<ArrayList<BusinessProfileList>> {
    private String BaseUrl;
    public LoaderBusProfileList(Context context, String url){
        super(context);
        BaseUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<BusinessProfileList> loadInBackground() {
        ArrayList<BusinessProfileList> businessProfileLists = new ArrayList<>();
        String data = (new SetHttpConnection(BaseUrl)).getInputStreamData(BaseUrl);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonParser = new JsonParser();
                businessProfileLists = jsonParser.getBusProfileList(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return businessProfileLists;
        }
        return null;
    }
}
