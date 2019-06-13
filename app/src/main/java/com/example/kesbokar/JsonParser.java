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

    public ArrayList<String> getBusinessSearch(String url) throws JSONException{
        ArrayList<String> getBusSrchValues = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(url);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int index = 0;index<jsonArray.length();index++) {
            jsonObject = jsonArray.getJSONObject(index);
            String values = jsonObject.getString("value");
            getBusSrchValues.add(values);
        }
        return getBusSrchValues;
    }

    public ArrayList<StateAndSuburb> getSuburbs(String url) throws JSONException {
        ArrayList<StateAndSuburb> stateAndSuburbs = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(url);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int index = 0; index < jsonArray.length(); index++) {
            jsonObject = jsonArray.getJSONObject(index);
            StateAndSuburb stateAndSuburb = new StateAndSuburb();
            stateAndSuburb.setId(jsonObject.getInt("id"));
            stateAndSuburb.setType(jsonObject.getString("type"));
            stateAndSuburb.setValue(jsonObject.getString("value"));
            stateAndSuburbs.add(stateAndSuburb);
        }
        return stateAndSuburbs;
    }

    public LoginInfo getLoginInfo(String url) throws JSONException {
        JSONObject JsonObject = new JSONObject(url);
        boolean flag = JsonObject.getBoolean("status");
        if(flag){
            JSONObject jsonObject = JsonObject.getJSONObject("data");

            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setFullName(jsonObject.getString("full_name"));
            loginInfo.setEmail_id(jsonObject.getString("email"));
            loginInfo.setImage(jsonObject.getString("image"));
            loginInfo.setPhone_no(jsonObject.getString("phone"));
            loginInfo.setId(jsonObject.getInt("id"));
            loginInfo.setCreated(jsonObject.getString("created_at"));
            loginInfo.setUpdated(jsonObject.getString("updated_at"));
            return loginInfo;
        }
        return null;
    }

    public ArrayList<BusinessProfileList> getBusProfileList(String url) throws JSONException {
        ArrayList<BusinessProfileList> businessProfileLists = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(url);

        for (int index = 0; index < jsonArray.length(); index++) {
            int i = index+1;
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            BusinessProfileList businessProfileList = new BusinessProfileList();
            businessProfileList.setTxtTitle(jsonObject.getString("name"));
            businessProfileList.setTxtSno(i + "");
            businessProfileList.setTxtPhone(jsonObject.getString("phone"));
            businessProfileList.setTxtAbn(jsonObject.getString("registration_no"));
            businessProfileLists.add(businessProfileList);
        }
        return businessProfileLists;
    }
}
