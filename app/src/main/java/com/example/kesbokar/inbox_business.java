package com.example.kesbokar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class inbox_business extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_business);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(inbox_business.this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int Id = menuItem.getItemId();

        if (Id == R.id.nav_home) {
            // Handle the camera action
        } else if (Id == R.id.dashboard) {

        } else if (Id == R.id.profile) {
            Intent intent = new Intent(inbox_business.this, Profile.class);
            intent.putExtra("Flag",flag);
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

        } else if (Id == R.id.business_lg_page) {
            Intent intent=new Intent(inbox_business.this,ProfileBusinessListing.class);
            intent.putExtra("Flag",flag);
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

        } else if (Id == R.id.market_lg_page) {
            Intent intent=new Intent(inbox_business.this,ProfileMarket.class);
            intent.putExtra("Flag",flag);
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
        } else if (Id == R.id.business_in) {

        } else if (Id == R.id.market_in) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
