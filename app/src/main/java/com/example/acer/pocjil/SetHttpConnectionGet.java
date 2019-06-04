package com.example.acer.pocjil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SetHttpConnectionGet {
    private static String BASE_URL;

    //Setting category code
    public SetHttpConnectionGet(){
        BASE_URL = "http://serv.kesbokar.com.au/jil.0.1/v2/yellowpage-featured?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
    }

    public static String getInputStreamData(){
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try{
            httpURLConnection = (HttpURLConnection)(new URL(BASE_URL)).openConnection();
            //Set the request method
            httpURLConnection.setRequestMethod("GET");
            //Since we only need to read the data
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            //Buffer to store data
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = httpURLConnection.getInputStream();

            //A reader to read data line by line
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;

            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + "\n");
            }

            //Close the connection and input stream
            inputStream.close();
            httpURLConnection.disconnect();

            return stringBuffer.toString();
        }catch (Throwable t){
            t.printStackTrace();
        }finally {
            try {
                //If the network is disconnected in between fetching then the input stream is empty or null
                //And closing a null inputStream will give us exception java.lang.NullPointerException:
                // Attempt to invoke virtual method 'void java.io.InputStream.close()' on a null object reference
                //Hence we need to mange this to avoid crashing in our app
                if(inputStream != null)
                    inputStream.close();

                httpURLConnection.disconnect();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
