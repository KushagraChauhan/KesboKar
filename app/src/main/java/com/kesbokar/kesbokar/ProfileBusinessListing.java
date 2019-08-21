package com.kesbokar.kesbokar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ProfileBusinessListing extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LoaderManager.LoaderCallbacks<ArrayList<BusinessProfileList>> busLoader;
    private static final int LOADER_BUS_PRO_LIST = 66;
    ListView listView;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;

    Button btnCreateNewBusinessListing;

    Button logout;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_business_listing);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getData();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        SharedPreferences sharedPreferences = getSharedPreferences("business_edit", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        logout=header.findViewById(R.id.logout);
        name=header.findViewById(R.id.name_user);

        if (flag==1){
            name.setText(full_name);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                SharedPreferences loginData= getSharedPreferences("data",0);
                SharedPreferences.Editor editor=loginData.edit();
                editor.putInt("Flag",flag);
                editor.apply();
                Intent intent=new Intent(ProfileBusinessListing.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        btnCreateNewBusinessListing = (Button)findViewById(R.id.btnCreate_new_business_listing);

        btnCreateNewBusinessListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileBusinessListing.this, Main3BusinessActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.listProfileBusiness);
        busLoader = new LoaderManager.LoaderCallbacks<ArrayList<BusinessProfileList>>() {
            @Override
            public Loader<ArrayList<BusinessProfileList>> onCreateLoader(int i, Bundle bundle) {
                LoaderBusProfileList loaderBusProfileList = new LoaderBusProfileList(ProfileBusinessListing.this,"http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage?user_id=" + id);
                return loaderBusProfileList;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<BusinessProfileList>> loader, ArrayList<BusinessProfileList> businessProfileLists) {
                if(businessProfileLists!=null) {
                    if (businessProfileLists.size() != 0) {
                        AdapterBusListProfile adapterBusListProfile = new AdapterBusListProfile(ProfileBusinessListing.this, businessProfileLists,ProfileBusinessListing.this);
                        listView.setAdapter(adapterBusListProfile);
                    } else {
                        Toast.makeText(ProfileBusinessListing.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onLoaderReset(Loader<ArrayList<BusinessProfileList>> loader) {

            }
        };
        getLoaderManager().initLoader(LOADER_BUS_PRO_LIST,null,busLoader);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int Id = item.getItemId();

        if (Id == R.id.nav_share) {
            if (flag==1){
                Intent about=new Intent(ProfileBusinessListing.this,Main3BusinessActivity.class);
                startActivity(about);
            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.business_lg_page) {
            if (flag==1) {
                Intent intent=new Intent(ProfileBusinessListing.this,ProfileBusinessListing.class);
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


            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.nav_send) {

            if (flag==1){
                Intent about=new Intent(ProfileBusinessListing.this,ProductManagementActivity.class);
                startActivity(about);
            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.market_lg_page) {

            if (flag==1) {
                Intent intent=new Intent(ProfileBusinessListing.this,ProfileMarket.class);
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

            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }


        } else if (Id == R.id.business_in) {

            if (flag==1){
                Intent intent=new Intent(ProfileBusinessListing.this,inbox_business.class);
                startActivity(intent);

            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }


        } else if (Id == R.id.market_in) {

            if (flag==1){
                Intent intent=new Intent(ProfileBusinessListing.this,inbox_market.class);
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

            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.profile) {

            if (flag==1) {
                Intent intent = new Intent(ProfileBusinessListing.this, Profile.class);
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

            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }

        } else if(Id == R.id.manage_help_desk) {

            if (flag==1) {
                Intent intent = new Intent(ProfileBusinessListing.this, ManageHelpDeskActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.about) {

            if (flag==1){
                Intent intent = new Intent(ProfileBusinessListing.this, About.class);
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


            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.career) {

            if (flag==1){
                Intent intent = new Intent(ProfileBusinessListing.this, Career.class);
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


            } else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }


        } else if (Id == R.id.advertise) {

        } else if (Id == R.id.loginPage) {

            if (flag==1){
                Intent intent=new Intent(ProfileBusinessListing.this,LoginData.class);
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

            }

            else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }

        } else if(Id == R.id.dashboard) {

            if (flag==1) {
                Intent intent = new Intent(ProfileBusinessListing.this, Navigation.class);
                startActivity(intent);
            }

            else {
                Intent intent = new Intent(ProfileBusinessListing.this, Login.class);
                startActivity(intent);
            }
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
