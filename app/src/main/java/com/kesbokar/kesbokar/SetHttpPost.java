package com.kesbokar.kesbokar;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SetHttpPost {
    public String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String sendPostMarkAndBus(String query, String baseUrl){
        String ResponseData;
        try{
            URL url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
//            jsonParam.put("timestamp", 1488873360);
            jsonParam.put("query", query);
            jsonParam.put("api_token", "FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
//            jsonParam.put("latitude", 0D);
//            jsonParam.put("longitude", 0D);

            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());
//            System.out.println("Status" + (conn.getResponseCode()));
//            System.out.println("MSG" + conn.getResponseMessage());
//            System.out.println("Response" + conn.getRequestMethod());
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            ResponseData = convertStreamToString(inputStream);
            Log.i("API" , ResponseData);


            conn.disconnect();
            return ResponseData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendPostSearchBtn(String queryValue, String querySub,int subId, String type, double lat, double longitude, String baseUrl){
        String ResponseData;
        try{
            URL url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            //jsonParam.put("timestamp", 1488873360);
            //jsonParam.put("page",1);
            //jsonParam.put("per_page",15);

            jsonParam.put("q", queryValue);
            if(type!=null) {
                if (type.equals("state")) {
                    jsonParam.put("state", querySub);
                }
                if (type.equals("city")) {
                    jsonParam.put("city", querySub);
                }


                if (type.equals("state")) {
                    jsonParam.put("stateid", subId);
                }
                if (type.equals("city")) {
                    jsonParam.put("cityid", subId);
                }
            }

            if(lat!=0.0 && longitude!=0.0){
                jsonParam.put("lat",lat);
                jsonParam.put("lng", longitude);
            }
            jsonParam.put("api_token", "FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
//

            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());

            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());
//            System.out.println("Status" + (conn.getResponseCode()));
//            System.out.println("MSG" + conn.getResponseMessage());
//            System.out.println("Response" + conn.getRequestMethod());
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            ResponseData = convertStreamToString(inputStream);
            Log.i("API" , ResponseData);

            conn.disconnect();
            return ResponseData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendPostLogin(String query_id, String query_pass, String baseUrl){
        String ResponseData;
        try{
            URL url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("email", query_id);
            jsonParam.put("password", query_pass);
            jsonParam.put("api_token", "FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");

            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());

            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            ResponseData = convertStreamToString(inputStream);
            Log.i("API" , ResponseData);


            conn.disconnect();
            return ResponseData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendPostTags(String tags, String baseUrl){
        String ResponseData;
        try{
            URL url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();

            jsonParam.put("ids", tags);
            jsonParam.put("api_token", "FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");


            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            ResponseData = convertStreamToString(inputStream);
            Log.i("API" , ResponseData);


            conn.disconnect();
            return ResponseData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
