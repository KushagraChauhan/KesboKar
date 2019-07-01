package com.kesbokar.kesbokar;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class LoaderGetTags extends AsyncTaskLoader<ArrayList<TagsObject>> {
    private ArrayList<String> tagsName;
    private String tags;
    private String url;
    public LoaderGetTags(Context context,String tags, ArrayList<String> tagsName, String url){
        super(context);
        this.tagsName = tagsName;
        this.url = url;
        this.tags = tags;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<TagsObject> loadInBackground() {
        ArrayList<TagsObject> tagsObjectArrayList = new ArrayList<>();
        String data = (new SetHttpPost()).sendPostTags(tags, url);
        if(data!=null){
            try {
                JsonParser jsonParser = new JsonParser();
                tagsObjectArrayList = jsonParser.getTags(data, tagsName);
            }catch (Throwable t){
                Log.i("ERRRRRR", "loadInBackground: " + "FUCK");
                t.printStackTrace();
            }
            return tagsObjectArrayList;
        }
        return null;
    }
}
