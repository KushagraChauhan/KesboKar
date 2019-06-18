package com.example.kesbokar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

    private AutoCompleteTextView autoCompleteTextViewOne,autoCompleteTextViewTwo;
    private Button btnAlertDialogSearch;
    private static final int LOADER_ID_BUSVAL = 3;
    private static final int LOADER_ID_BUSSUB = 4;
    private static final int LOADER_ID_BTNSRCH = 5;

    private LoaderManager.LoaderCallbacks<ArrayList<String>> businessSearch;
    private LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>> businessSuburb;
    private LoaderManager.LoaderCallbacks<ArrayList<ExampleItem>> btnSearch;

    private ArrayList<String> valsBus;
    private ArrayList<StateAndSuburb> valsSub;
    private String query = "";
    String querySub,subV,subType,q;
    int stateid = 0;

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
        ImageView imageView = (ImageView)findViewById(R.id.imgSearch);
        valsBus = new ArrayList<>();
        valsSub = new ArrayList<>();
        querySub = subV = subType = q = "";

//        autoCompleteTextViewOne = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewOne);
//        autoCompleteTextViewTwo = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewTwo);
//        btnAlertDialogSearch = findViewById(R.id.btnAlertDialogSearch);
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestAlertDialogBox();
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



    private void RequestAlertDialogBox()
    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        // get the layout inflater
//        LayoutInflater inflater = this.getLayoutInflater();
//
//        // inflate and set the layout for the dialog
//        // pass null as the parent view because its going in the dialog layout
//        builder.setView(inflater.inflate(R.layout.search_alert_dialog_box, null))
//                .show();
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.search_alert_dialog_box);
        autoCompleteTextViewOne = dialog.findViewById(R.id.autoCompleteTextViewOne);
        autoCompleteTextViewTwo = dialog.findViewById(R.id.autoCompleteTextViewTwo);
        btnAlertDialogSearch = dialog.findViewById(R.id.btnAlertDialogSearch);


        btnSearch = new LoaderManager.LoaderCallbacks<ArrayList<ExampleItem>>() {
            @Override
            public Loader<ArrayList<ExampleItem>> onCreateLoader(int id, Bundle args) {
                LoaderBtnSearch loaderBtnSearch = new LoaderBtnSearch(Buisness_Listing.this,q,subV,"https://serv.kesbokar.com.au/jil.0.1/v2/yellowpages",stateid,subType,0.0,0.0);
                return loaderBtnSearch;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<ExampleItem>> loader, ArrayList<ExampleItem> data) {
                switch (loader.getId()){
                    case LOADER_ID_BTNSRCH:
                        if(data != null){
                            exampleItems = data;
                            Log.i("Search", data.toString());
                            Intent intent = new Intent(Buisness_Listing.this,Buisness_Listing.class);
                            intent.putExtra("CHOICE", "btnSearch");
                            intent.putParcelableArrayListExtra("ARRAYLIST",exampleItems);
                            startActivity(intent);
                        }
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<ExampleItem>> loader) {

            }
        };
        businessSuburb = new LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>>() {
            @Override
            public Loader<ArrayList<StateAndSuburb>> onCreateLoader(int id, Bundle args) {
                LoaderBusSuburb loaderBusSuburb = new LoaderBusSuburb(Buisness_Listing.this,querySub,"http://serv.kesbokar.com.au/jil.0.1/v2/yellowpages/search/cities");
                return loaderBusSuburb;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<StateAndSuburb>> loader, ArrayList<StateAndSuburb> data) {
                if (data.size() != 0) {
                    valsSub = data;
                    Log.i("Tag", valsSub + "");
                    ArrayAdapter<StateAndSuburb> adapter=new ArrayAdapter<StateAndSuburb>(Buisness_Listing.this,android.R.layout.simple_dropdown_item_1line,valsSub);
                    autoCompleteTextViewTwo.setAdapter(adapter);
                    getLoaderManager().destroyLoader(LOADER_ID_BUSVAL);
                } else {
                    Toast.makeText(Buisness_Listing.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoaderReset(Loader<ArrayList<StateAndSuburb>> loader) {

            }
        };
        businessSearch = new LoaderManager.LoaderCallbacks<ArrayList<String>>() {
            @Override
            public Loader<ArrayList<String>> onCreateLoader(int id, Bundle args) {
                LoaderBusSearch loaderBusSearch = new LoaderBusSearch(Buisness_Listing.this,query,"http://serv.kesbokar.com.au/jil.0.1/v2/yellowpages/search");
                return loaderBusSearch;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
                if (data.size() != 0) {
                    valsBus = data;
                    Log.i("Tag", valsBus + "");
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Buisness_Listing.this,android.R.layout.simple_dropdown_item_1line,valsBus);
                    autoCompleteTextViewOne.setAdapter(adapter);
                    getLoaderManager().initLoader(LOADER_ID_BUSSUB,null,businessSuburb);
                } else {
                    Toast.makeText(Buisness_Listing.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoaderReset(Loader<ArrayList<String>> loader) {

            }
        };

        getLoaderManager().initLoader(LOADER_ID_BUSVAL, null, businessSearch);
        dialog.show();
        btnAlertDialogSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q = autoCompleteTextViewOne.getText().toString();
                Log.i("Q and subV", q + " " + subV);
                if(q.length() == 0 && subV.length() == 0){
                    Toast.makeText(Buisness_Listing.this, "Cannot Search Empty fields", Toast.LENGTH_SHORT).show();
                }
                else if (subV.length()==0)
                {
                    Toast.makeText(Buisness_Listing.this, "Cannot Search Empty State", Toast.LENGTH_SHORT).show();
                }
                getLoaderManager().initLoader(LOADER_ID_BTNSRCH,null,btnSearch);
            }
        });

        autoCompleteTextViewTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                subV = stateAndSuburb.getValue();
                stateid = stateAndSuburb.getId();
                subType = stateAndSuburb.getType();
            }
        });
        autoCompleteTextViewTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                querySub = s.toString();
                getLoaderManager().restartLoader(LOADER_ID_BUSSUB,null,businessSuburb);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
