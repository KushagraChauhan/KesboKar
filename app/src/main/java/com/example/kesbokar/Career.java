package com.example.kesbokar;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ScrollView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class Career extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<ArrayList<ButtonsDetails>>{
    static String URL1;
    Document doc1;
    public static WebView webView;
    public static Button contact;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;

    private Button btnHel,btnBuis,btnMar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);
        getData();

        btnHel = (Button)findViewById(R.id.help);
        btnBuis = (Button)findViewById(R.id.buis);
        btnMar = (Button)findViewById(R.id.mar);


        contact=findViewById(R.id.contact);
        webView = (WebView) findViewById(R.id.webview);


        getData();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        getData();
        intent = getIntent();
        extras = intent.getExtras();
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.kesbokar.com.au/contact-us";
                Intent intent = new Intent(Career.this, WebViewActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
                finish();

            }
        });
        URL1 ="https://www.kesbokar.com.au/career";
        new Career.MyAsyncTask().execute();

        btnHel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Career.this, Help.class);
                intent.putExtra("Flag", flag);
                intent.putExtra("Name",full_name);
                intent.putExtra("mail",email);
                intent.putExtra("image",image);
                intent.putExtra("phone",phone_no);
                intent.putExtra("create",created);
                intent.putExtra("update",updated);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        btnBuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Career.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("Flag", flag);
                intent.putExtra("Name",full_name);
                intent.putExtra("mail",email);
                intent.putExtra("image",image);
                intent.putExtra("phone",phone_no);
                intent.putExtra("create",created);
                intent.putExtra("update",updated);
                intent.putExtra("id",id);
                startActivityForResult(intent,0);
                overridePendingTransition(0,0);
                finish();
            }
        });

        btnMar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Career.this, Navigation_market.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("Flag", flag);
                intent.putExtra("Name",full_name);
                intent.putExtra("mail",email);
                intent.putExtra("image",image);
                intent.putExtra("phone",phone_no);
                intent.putExtra("create",created);
                intent.putExtra("update",updated);
                intent.putExtra("id",id);
                startActivityForResult(intent, 0);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_send) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.about) {
            Intent about=new Intent(Career.this,About.class);
            startActivity(about);

        } else if (id == R.id.career) {
            Intent career=new Intent(Career.this,Career.class);
            startActivity(career);

        } else if (id == R.id.advertise) {

        }


        return true;
    }

    @Override
    public Loader<ArrayList<ButtonsDetails>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ButtonsDetails>> loader, ArrayList<ButtonsDetails> data) {

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ButtonsDetails>> loader) {

    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;

        }
    }
    @Override
    public void onBackPressed () {
        Intent intent=new Intent(Career.this,Navigation.class);
        intent.putExtra("Flag", flag);
        intent.putExtra("Name",full_name);
        intent.putExtra("mail",email);
        intent.putExtra("image",image);
        intent.putExtra("phone",phone_no);
        intent.putExtra("create",created);
        intent.putExtra("update",updated);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(intent, 0);
        overridePendingTransition(0, 0);
        finish();

    }
    private static class MyAsyncTask extends AsyncTask<Void, Void, Document> {
        @Override
        protected Document doInBackground(Void... voids) {

            Document document = null;
            try {
                document= Jsoup.connect(URL1).get();
                document.getElementsByClass("navbar navbar-default").remove();
                document.getElementsByClass("needhelp_in_touch").remove();
                document.getElementsByClass("footer_area").remove();
                document.getElementsByClass("section additional-details clear proerty-th").remove();
                document.getElementsByClass("breadcrumb").remove();
                document.getElementsByClass("cmsHideForApps").remove();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return document;
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            webView.setWebViewClient(new WebViewClient());
            WebSettings webSettings=webView.getSettings();
            webSettings.setBuiltInZoomControls(true);
            webView.loadDataWithBaseURL(URL1,document.toString(),"text/html","utf-8","");
            webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
            webSettings.setJavaScriptEnabled(true);
        }
    }
    public void getData()
    {
        SharedPreferences loginData=getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");

    }
}
