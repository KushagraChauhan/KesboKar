package com.example.kesbokar;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.DocumentsContract;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView ab;
    String about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ab=(TextView)findViewById(R.id.about);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            // Handle the camera action
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.about) {

        } else if (id == R.id.career) {

        } else if (id == R.id.advertise) {

        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class Description extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document doc1 = Jsoup.connect("https://www.kesbokar.com.au/").get();
                Elements repositories=doc1.select("#business-list-block");//doc1.getElementsByClass("content-area home-area-1 recent-property");
                for (Element repository : repositories) {
                    Elements header = repository.getElementsByClass("col-md-10col-sm-12 text-center page-title");
                    for (Element str : header) {
                        about = str.getElementsByTag("p").text();

                    }
                }
            }catch (Exception e){e.printStackTrace();}

            return null;
        }
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ab.append(about);
        }
    }
}
