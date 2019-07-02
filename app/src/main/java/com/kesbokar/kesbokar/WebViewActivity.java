package com.kesbokar.kesbokar;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class WebViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<ArrayList<ButtonsDetails>>{
    static String URL1;
    Document doc1;
    public static WebView webView;
    String loginId, loginPass, full_name, email, image1, phone_no,created,updated;
    int id1,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        final ScrollView scrollView=(ScrollView)findViewById(R.id.scroll);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        webView = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        Bundle extras=intent.getExtras();
        URL1 = extras.getString("URL");
        flag = extras.getInt("Flag");
        full_name=extras.getString("Name");
        Menu show=navigationView.getMenu();
        View ab = navigationView.getHeaderView(0);
        TextView name1=(TextView)ab.findViewById(R.id.name_user);
        Button signup=(Button)ab.findViewById(R.id.signup);
        Button login=(Button)ab.findViewById(R.id.login);
        Button logout=ab.findViewById(R.id.logout);
        email=extras.getString("mail");
        image1=extras.getString("image");
        phone_no=extras.getString("phone");
        id1=extras.getInt("id");
        created=extras.getString("create");
        updated=extras.getString("update");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebViewActivity.this, Login.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebViewActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                SharedPreferences loginData= getSharedPreferences("data",0);
                SharedPreferences.Editor editor=loginData.edit();
                editor.putInt("Flag",flag);
                editor.apply();
                Intent intent=new Intent(WebViewActivity.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        getData();
        if(flag==1)
        {
            name1.setText(full_name);
            login.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            show.findItem(R.id.nav_send).setVisible(true);
            show.findItem(R.id.nav_share).setVisible(true);
            show.findItem(R.id.advertise).setVisible(true);
            logout.setVisibility(View.VISIBLE);
            show.findItem(R.id.loginPage).setVisible(true);
            show.findItem(R.id.loginPage).setTitle(full_name+"  GO!!");
        }

        new MyAsyncTask().execute();
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
            Intent about=new Intent(WebViewActivity.this,About.class);
            startActivity(about);

        } else if (id == R.id.career) {
            Intent career=new Intent(WebViewActivity.this,Career.class);
            startActivity(career);

        } else if (id == R.id.advertise) {

        }else if (id == R.id.loginPage) {
            Intent intent=new Intent(WebViewActivity.this,LoginData.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
        Intent intent=new Intent(WebViewActivity.this,Navigation.class);
        intent.putExtra("Flag", flag);
        intent.putExtra("Name",full_name);
        intent.putExtra("mail",email);
        intent.putExtra("image",image1);
        intent.putExtra("phone",phone_no);
        intent.putExtra("create",created);
        intent.putExtra("update",updated);
        intent.putExtra("id",id1);
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
                document.getElementsByClass("page-subheader sorting pl0").remove();
                document.getElementsByClass("sel-filters text-left").remove();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return document;
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            //webView.setWebViewClient(new WebViewClient());
            WebSettings webSettings=webView.getSettings();


            webSettings.setPluginState(WebSettings.PluginState.ON);
            webView.loadDataWithBaseURL(URL1,document.toString(),"text/html","utf-8","");
            //webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
            webSettings.setJavaScriptEnabled(true);
            
        }
    }
    public void getData()
    {
        SharedPreferences loginData=getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image1=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id1=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");

    }
}
