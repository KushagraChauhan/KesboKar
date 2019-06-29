package com.kesbokar.kesbokar;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderCategoriesThird extends AsyncTaskLoader<ArrayList<CategoryThird>> {
    private String url;
    public LoaderCategoriesThird(Context context, String url){
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<CategoryThird> loadInBackground() {
        ArrayList<CategoryThird> categoryThirdArrayList = new ArrayList<>();
        String data = ((new SetHttpConnection(url))).getInputStreamData(url);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonParser = new JsonParser();
                categoryThirdArrayList = jsonParser.getCategoryThird(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return categoryThirdArrayList;
        }
        return null;
    }
}
