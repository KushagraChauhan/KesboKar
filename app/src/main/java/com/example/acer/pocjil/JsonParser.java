package com.example.acer.pocjil;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {
    //Method to retrieve a complete News_Data object using json
    public ArrayList<ButtonsDetails> getbtndata(String url) throws JSONException {
        ArrayList<ButtonsDetails> btnsDetails = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(url);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int index = 0;index<jsonArray.length();index++) {
            jsonObject = jsonArray.getJSONObject(index);
            ButtonsDetails buttonsDetails = new ButtonsDetails();
            //Now from the object of each arrayItem
            buttonsDetails.setId(jsonObject.getInt("id"));
            buttonsDetails.setTitle(jsonObject.getString("title"));
            buttonsDetails.setImage(jsonObject.getString("image"));
            buttonsDetails.setUrl(jsonObject.getString("url_name"));
            buttonsDetails.setIcon(jsonObject.getString("icon"));
            btnsDetails.add(buttonsDetails);
        }
        return btnsDetails;
    }



}
