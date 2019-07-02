package com.kesbokar.kesbokar;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class LoaderInboxMarketList extends AsyncTaskLoader<ArrayList<InboxMarketList>> {
    private String BaseUrl;
    public LoaderInboxMarketList(Context context, String url){
        super(context);
        BaseUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<InboxMarketList> loadInBackground() {
        ArrayList<InboxMarketList> inboxMarketLists = new ArrayList<>();
        String data = (new SetHttpConnection(BaseUrl)).getInputStreamData(BaseUrl);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonParser = new JsonParser();
                inboxMarketLists = jsonParser.getInboxMarketList(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return inboxMarketLists;
        }
        return null;
    }
}
