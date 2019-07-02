package com.kesbokar.kesbokar;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderFirstCategory extends AsyncTaskLoader<ArrayList<CategoryBase>> {
    private String url;
    public LoaderFirstCategory(Context context, String url){
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<CategoryBase> loadInBackground() {
        ArrayList<CategoryBase> firstCategoriesList = new ArrayList<>();
        String data = ((new SetHttpConnection(url))).getInputStreamData(url);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonParser = new JsonParser();
                firstCategoriesList = jsonParser.getCategoryBase(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return firstCategoriesList;
        }
        return null;
    }
}
