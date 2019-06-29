package com.kesbokar.kesbokar;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderCategorySecond extends AsyncTaskLoader<ArrayList<CategorySecond>> {
    private String BASE_URL;
    public LoaderCategorySecond(Context context, String url){
        super(context);
        this.BASE_URL = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<CategorySecond> loadInBackground() {
        ArrayList<CategorySecond> secondCategoriesList = new ArrayList<>();
        String data = ((new SetHttpConnection(BASE_URL))).getInputStreamData(BASE_URL);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser jsonParser = new JsonParser();
                secondCategoriesList = jsonParser.getCategorySecond(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return secondCategoriesList;
        }
        return null;
    }
}
