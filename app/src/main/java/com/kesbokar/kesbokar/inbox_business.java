package com.kesbokar.kesbokar;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class inbox_business extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int en_id;
    private LoaderManager.LoaderCallbacks<ArrayList<InboxBusinessList>> busLoader;
    private static final int LOADER_BUS_PRO_LIST = 66;
    ListView listView;
    Button adpbtnedt;

    int id,flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_business);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getData();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(inbox_business.this);

        listView = findViewById(R.id.listProfileBusiness);

        busLoader = new LoaderManager.LoaderCallbacks<ArrayList<InboxBusinessList>>() {
            @Override
            public Loader<ArrayList<InboxBusinessList>> onCreateLoader(int i, Bundle bundle) {
                LoaderInboxBusinessList loaderInboxBusinessList = new LoaderInboxBusinessList (inbox_business.this,"http://serv.kesbokar.com.au/jil.0.1/v1/quotes-yellowpage?user_id="+ id + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return loaderInboxBusinessList;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<InboxBusinessList>> loader, ArrayList<InboxBusinessList> inboxBusinessLists) {
                if(inboxBusinessLists!=null) {
                    if (inboxBusinessLists.size() != 0) {
                        AdapterInboxBusiness adapterInboxBusiness = new AdapterInboxBusiness(inbox_business.this, inboxBusinessLists,inbox_business.this);
                        listView.setAdapter(adapterInboxBusiness);
                    } else {
                        Toast.makeText(inbox_business.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onLoaderReset(Loader<ArrayList<InboxBusinessList>> loader) {

            }
        };
        getLoaderManager().initLoader(LOADER_BUS_PRO_LIST,null,busLoader);



    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(inbox_business.this,LoginData.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int Id = menuItem.getItemId();

        if (Id == R.id.nav_home) {
            // Handle the camera action
        } else if (Id == R.id.dashboard) {
            Intent intent = new Intent(inbox_business.this, LoginData.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("enquiry_id", en_id);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        } else if (Id == R.id.profile) {
            Intent intent = new Intent(inbox_business.this, Profile.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("enquiry_id", en_id);
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
            intent.putExtra("enquiry_id", en_id);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        }else if (Id == R.id.manage_help_desk){
            Intent intent=new Intent(inbox_business.this,ManageHelpDeskActivity.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("enquiry_id", en_id);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        }
        else if (Id == R.id.market_lg_page) {
            Intent intent=new Intent(inbox_business.this,ProfileMarket.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("enquiry_id", en_id);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        } else if (Id == R.id.business_in) {

        } else if (Id == R.id.market_in) {
            Intent intent = new Intent(inbox_business.this, inbox_market.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("update",updated);
            intent.putExtra("enquiry_id", en_id);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();
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
//        en_id = loginData.getInt("enquiry_id", 0);
    }
}
