package com.example.kesbokar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
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

public class Buisness_Listing extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private ArrayList<ExampleItem> exampleItems;
    private RequestQueue requestQueue;
    boolean isLoading = false;
    String name,image,synopsis,url1,city,city_id;
    int id;
    String loginId, loginPass, full_name, email, image1, phone_no,created,updated;
    int id1,flag;
    double ratings;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buisness__listing);
        final ScrollView scrollView=(ScrollView)findViewById(R.id.scroll);
        getData();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View ab = navigationView.getHeaderView(0);
        Menu show=navigationView.getMenu();
        TextView name1=(TextView)ab.findViewById(R.id.name_user);
        Button signup=(Button)ab.findViewById(R.id.signup);
        Button login=(Button)ab.findViewById(R.id.login);
        Button logout=ab.findViewById(R.id.logout);
        intent=getIntent();
        bundle=intent.getExtras();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        exampleItems = new ArrayList<>();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buisness_Listing.this, Login.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buisness_Listing.this, SignUp.class);
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
                Intent intent=new Intent(Buisness_Listing.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

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
        String denote = bundle.getString("CHOICE");
        if(denote.equals("imgBtnService")){
            imgBtnService();
        }else if(denote.equals("btnSearch")){
            exampleItems = bundle.getParcelableArrayList("ARRAYLIST");
            dataAdapter = new DataAdapter(Buisness_Listing.this, exampleItems,flag);
            recyclerView.setAdapter(dataAdapter);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Buisness_Listing.this,Navigation.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(intent, 0);
        overridePendingTransition(0, 0); startActivity(intent);
        finish();
    }

    public void imgBtnService(){
        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
        initScrollListener();
    }

    private void parseJSON() {
        String url =bundle.getString("URL");
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dat = jsonArray.getJSONObject(i);
                                Log.i("JSON PAGI",dat.toString());
                                name = dat.getString("name");
                                synopsis = dat.getString("synopsis");
                                image = dat.getString("image");
                                url1=dat.getString("url_name");
                                city_id=dat.getString("city_id");
                                JSONArray rate=dat.getJSONArray("reviews");
                                if(rate.length()>0) {
                                    for (int j = 0; j < rate.length(); j++) {
                                        JSONObject review = rate.getJSONObject(j);
                                        ratings = review.getDouble("ratings");

                                    }
                                }
                                else {
                                    ratings=0.0;
                                }

                                if (city_id!="null") {
                                    JSONObject cityob=dat.getJSONObject("city");

                                    city = cityob.getString("title");
                                    city = city.replace(" ", "+");
                                }
                                else
                                {
                                    city="city";
                                }

                                id=dat.getInt("id");
                                exampleItems.add(new ExampleItem(image, name, synopsis,url1,city,id,ratings));
                            }
                            dataAdapter = new DataAdapter(Buisness_Listing.this, exampleItems,flag);
                            recyclerView.setAdapter(dataAdapter);
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
        requestQueue.add(request);
    }
    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == exampleItems.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });


    }

    private void loadMore() {
        //exampleItems.add(null);
        dataAdapter.notifyItemInserted(exampleItems.size() - 1);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                exampleItems.remove(exampleItems.size() - 1);
                int scrollPosition = exampleItems.size();
                dataAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                while (currentSize - 1 < nextLimit) {
                    exampleItems.add(new ExampleItem(image, name, synopsis,url1,city,id,ratings));
                    currentSize++;
                }

                dataAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_send) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.about) {
            Intent about=new Intent(Buisness_Listing.this,About.class);
            startActivity(about);

        } else if (id == R.id.career) {
            Intent career=new Intent(Buisness_Listing.this,Career.class);
            startActivity(career);

        } else if (id == R.id.advertise) {

        }else if (id == R.id.loginPage) {
            Intent intent=new Intent(Buisness_Listing.this,LoginData.class);
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
        image1=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id1=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");

    }
}
