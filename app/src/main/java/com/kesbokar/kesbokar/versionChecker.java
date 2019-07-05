package com.kesbokar.kesbokar;

import android.os.AsyncTask;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;

public class versionChecker extends AsyncTask<String, String, String> {
        String newVersion;

@Override
protected String doInBackground(String... params) {

        try {
        Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=com.kesbokar.kesbokar").get();
        newVersion=document.getElementsByClass("IQ1z0d").get(5).text();
        } catch (IOException e) {
        e.printStackTrace();
        }

        return newVersion;
        }
        }
