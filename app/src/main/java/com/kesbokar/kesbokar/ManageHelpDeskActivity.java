package com.kesbokar.kesbokar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageHelpDeskActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    ListView listView;
    int id1;
    Button mng_helpdesk_new;
    String date,reply,subject;
    RequestQueue requestQueue;
    ArrayList<GetHelpDesk> getHelpDesks;

    Button logout;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_help_desk);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getHelpDesks=new ArrayList<GetHelpDesk>();
        listView=findViewById(R.id.listhelpdesk);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getData();

        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        logout=header.findViewById(R.id.logout);
        name=(TextView) header.findViewById(R.id.name_user);

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
                Intent intent=new Intent(ManageHelpDeskActivity.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        mng_helpdesk_new=findViewById(R.id.mng_helpdesk_new);
        requestQueue = Volley.newRequestQueue(this);
        jsonParser();
        mng_helpdesk_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManageHelpDeskActivity.this,New_manage_help_desk.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void jsonParser()
    {
        String url1="http://serv.kesbokar.com.au/jil.0.1/v1/helpdesk?user_id="+id+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject dat = jsonArray.getJSONObject(i);
                        Log.i("JSON Help", dat.toString());
                        date=dat.getString("created_at");
                        subject=dat.getString("subject");
                        reply=dat.getString("reply_by_name");
                        id1=dat.getInt("id");
                        getHelpDesks.add(new GetHelpDesk(date,subject,reply,id1));

                    }
                    if (getHelpDesks!=null) {
                        AdapterhelpDesk adapterhelpDesk = new AdapterhelpDesk(ManageHelpDeskActivity.this,ManageHelpDeskActivity.this , getHelpDesks);
                        listView.setAdapter(adapterhelpDesk);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int Id = item.getItemId();

        if (Id == R.id.nav_share) {
            if (flag==1){
                Intent about=new Intent(ManageHelpDeskActivity.this,Main3BusinessActivity.class);
                startActivity(about);
            } else {
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.business_lg_page) {
            if (flag==1) {
                Intent intent=new Intent(ManageHelpDeskActivity.this,ProfileBusinessListing.class);
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
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.nav_send) {

            if (flag==1){
                Intent about=new Intent(ManageHelpDeskActivity.this,ProductManagementActivity.class);
                startActivity(about);
            } else {
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.market_lg_page) {

            if (flag==1) {
                Intent intent=new Intent(ManageHelpDeskActivity.this,ProfileMarket.class);
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
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }


        } else if (Id == R.id.business_in) {

            if (flag==1){
                Intent intent=new Intent(ManageHelpDeskActivity.this,inbox_business.class);
                startActivity(intent);

            } else {
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }


        } else if (Id == R.id.market_in) {

            if (flag==1){
                Intent intent=new Intent(ManageHelpDeskActivity.this,inbox_market.class);
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
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.profile) {

            if (flag==1) {
                Intent intent = new Intent(ManageHelpDeskActivity.this, Profile.class);
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
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }

        } else if(Id == R.id.manage_help_desk) {

            if (flag==1) {
                Intent intent = new Intent(ManageHelpDeskActivity.this, ManageHelpDeskActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.about) {

            if (flag==1){
                Intent intent = new Intent(ManageHelpDeskActivity.this, About.class);
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
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }

        } else if (Id == R.id.career) {

            if (flag==1){
                Intent intent = new Intent(ManageHelpDeskActivity.this, Career.class);
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
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }


        } else if (Id == R.id.advertise) {

        } else if (Id == R.id.loginPage) {

            if (flag==1){
                Intent intent=new Intent(ManageHelpDeskActivity.this,LoginData.class);
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
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
                startActivity(intent);
            }

        } else if(Id == R.id.dashboard) {

            if (flag==1) {
                Intent intent = new Intent(ManageHelpDeskActivity.this, Navigation.class);
                startActivity(intent);
            }

            else {
                Intent intent = new Intent(ManageHelpDeskActivity.this, Login.class);
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

