package com.example.kesbokar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {
    public ArrayList<ButtonsDetails> getbtndata(String url) throws JSONException {
        ArrayList<ButtonsDetails> btnsDetails = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(url);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int index = 0; index < jsonArray.length(); index++) {
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

    public ArrayList<ServiceExpertSpace> getServiceSpace(String url) throws JSONException {
        ArrayList<ServiceExpertSpace> serviceDetails = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(url);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int index = 0; index < jsonArray.length(); index++) {
            jsonObject = jsonArray.getJSONObject(index);
            ServiceExpertSpace serviceExpertSpace = new ServiceExpertSpace();
            City city = new City();
            State state = new State();
            Country country = new Country();

            //getting city, state and country objects
            city.setId(jsonObject.getJSONObject("city").getInt("id"));
            city.setTitle(jsonObject.getJSONObject("city").getString("title"));

            state.setId(jsonObject.getJSONObject("state").getInt("id"));
            state.setTitle(jsonObject.getJSONObject("state").getString("title"));

            country.setId(jsonObject.getJSONObject("country").getInt("id"));
            country.setTitle(jsonObject.getJSONObject("country").getString("title"));

            serviceExpertSpace.setCity(city);
            serviceExpertSpace.setCountry(country);
            serviceExpertSpace.setState(state);

            //getting remaining data
            serviceExpertSpace.setId(jsonObject.getInt("id"));
            serviceExpertSpace.setCat_title(jsonObject.getString("cat_title"));
            serviceExpertSpace.setUrlname(jsonObject.getString("url_name"));
            serviceExpertSpace.setName(jsonObject.getString("name"));
            serviceExpertSpace.setImageLogo(jsonObject.getString("image"));

            serviceDetails.add(serviceExpertSpace);
        }
        return serviceDetails;
    }

    public ArrayList<MarketPlaceApi> getMarket(String url) throws JSONException {
        ArrayList<MarketPlaceApi> marketDetails = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(url);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int index = 0; index < jsonArray.length(); index++) {
            jsonObject = jsonArray.getJSONObject(index);
            MarketPlaceApi marketPlaceApi = new MarketPlaceApi();
            City city = new City();
            State state = new State();
            Country country = new Country();

            //getting city, state and country objects
            city.setId(jsonObject.getJSONObject("city").getInt("id"));
            city.setTitle(jsonObject.getJSONObject("city").getString("title"));

            state.setId(jsonObject.getJSONObject("state").getInt("id"));
            state.setTitle(jsonObject.getJSONObject("state").getString("title"));

            country.setId(jsonObject.getJSONObject("country").getInt("id"));
            country.setTitle(jsonObject.getJSONObject("country").getString("title"));
            marketPlaceApi.setCity(city);
            marketPlaceApi.setCountry(country);
            marketPlaceApi.setState(state);

            //getting remaining data
            marketPlaceApi.setId(jsonObject.getInt("id"));
            marketPlaceApi.setCat_title(jsonObject.getString("cat_title"));
            marketPlaceApi.setUrlname(jsonObject.getString("url_name"));
            marketPlaceApi.setName(jsonObject.getString("name"));
            marketPlaceApi.setImageLogo(jsonObject.getString("image"));

            marketDetails.add(marketPlaceApi);
        }
        return marketDetails;
    }
}
