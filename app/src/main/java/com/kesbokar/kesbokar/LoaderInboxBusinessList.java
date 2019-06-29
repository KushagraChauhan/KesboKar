package com.kesbokar.kesbokar;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderInboxBusinessList extends AsyncTaskLoader<ArrayList<InboxBusinessList>> {
    private String BaseUrl;
    public LoaderInboxBusinessList(Context context, String url){
        super(context);
        BaseUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<InboxBusinessList> loadInBackground() {
        ArrayList<InboxBusinessList> inboxBusinessLists = new ArrayList<>();
        String data = (new SetHttpConnection(BaseUrl)).getInputStreamData(BaseUrl);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonParser = new JsonParser();
                inboxBusinessLists = jsonParser.getInboxBusinessList(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return inboxBusinessLists;
        }
        return null;
    }
}
