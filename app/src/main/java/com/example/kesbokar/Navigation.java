package com.example.kesbokar;
import android.Manifest;
import android.app.LoaderManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Loader;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LocationListener {
    TextView ab;
    ImageButton[] imagebutton;
    TextView[] dynamicTxt;
    LinearLayout layoutmain;
    LinearLayout layout;
    LinearLayout layoutsec;
    int id;
    ImageView search;
    Button btnSrch,logout;
    String about;
    TextView name;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated, title;
    boolean a;
    LinearLayout.LayoutParams params;
    LinearLayout.LayoutParams params1;
    int i;
    private static int dataSize = 0;
    private static final int LOADER_ID_BUSINESS = 0;
    private static final int LOADER_ID_SERVICES = 1;
    private static final int LOADER_ID_MARKET = 2;
    private static final int LOADER_ID_BUSVAL = 3;
    private static final int LOADER_ID_BUSSUB = 4;
    private static final int LOADER_ID_BTNSRCH = 5;
    private ArrayList<String> valsBus;
    private ArrayList<StateAndSuburb> valsSub;
    int flag=0;
    private LoaderManager.LoaderCallbacks<ArrayList<ButtonsDetails>> buttonsDetailsLoaderCallbacks;
    private LoaderManager.LoaderCallbacks<ArrayList<ServiceExpertSpace>> serviceExpertSpaceLoaderCallbacks;
    private LoaderManager.LoaderCallbacks<ArrayList<MarketPlaceApi>> MarketPlaceApiCallbacks;
    private LoaderManager.LoaderCallbacks<ArrayList<String>> businessSearch;
    private LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>> businessSuburb;
    private LoaderManager.LoaderCallbacks<ArrayList<ExampleItem>> btnSearch;
    private String subType;
    //private static ArrayList<String> tags;
    Toolbar toolbar;
    ImageView[] bi, mi;
    TextView[] bc, bd, mc, md;
    HorizontalScrollView category;
    DrawerLayout drawer;
    AutoCompleteTextView textView;
    AutoCompleteTextView textView2;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Button top, signup, login, help, market;

    String q, subV;
    String querySub;
    private double lat, longitude;
    ArrayList<ExampleItem> exampleItems;
    int stateid = 0;
    private static final String[] COUNTRIES = new String[] { "Belgium","France", "France_", "Italy", "Germany", "Spain" };
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
    private String query = "";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        //savedInstanceState.putBoolean("a",a);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);
        a = false;
       // Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
        toolbar = findViewById(R.id.toolbar);
        valsBus = new ArrayList<>();
        valsSub = new ArrayList<>();
        exampleItems = new ArrayList<>();
        bi = new ImageView[4];
        mi = new ImageView[4];
        bc = new TextView[4];
        final Button location;
        mc = new TextView[4];
        md = new TextView[4];
        bd = new TextView[4];
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        lat = 0.0;
        longitude = 0.0;
        textView2 = findViewById(R.id.bs2);
        search=findViewById(R.id.search);
        q = subV = querySub = "";
        setSupportActionBar(toolbar);
        ab = (TextView) findViewById(R.id.about);
        bi[0] = findViewById(R.id.bi1);
        bi[1] = findViewById(R.id.bi2);
        bi[2] = findViewById(R.id.bi3);
        bi[3] = findViewById(R.id.bi4);
        bc[0] = findViewById(R.id.bc1);
        bc[1] = findViewById(R.id.bc2);
        bc[2] = findViewById(R.id.bc3);
        bc[3] = findViewById(R.id.bc4);
        bd[0] = findViewById(R.id.bd1);
        bd[1] = findViewById(R.id.bd2);
        bd[2] = findViewById(R.id.bd3);
        bd[3] = findViewById(R.id.bd4);
        mi[0] = findViewById(R.id.mi1);
        mi[1] = findViewById(R.id.mi2);
        mi[2] = findViewById(R.id.mi3);
        mc[0] = findViewById(R.id.mc1);
        mc[1] = findViewById(R.id.mc2);
        mc[2] = findViewById(R.id.mc3);
        md[0] = findViewById(R.id.md1);
        md[1] = findViewById(R.id.md2);
        md[2] = findViewById(R.id.md3);
        md[3] = findViewById(R.id.md4);
        mc[3] = findViewById(R.id.mc4);
        mi[3] = findViewById(R.id.mi4);

        location=findViewById(R.id.location);
        category = findViewById(R.id.category);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        btnSrch = findViewById(R.id.btnSrch);
        textView=findViewById(R.id.bs1);
        navigationView.setNavigationItemSelectedListener(this);
        top = (Button) findViewById(R.id.top);
        View ab = navigationView.getHeaderView(0);
        Menu show=navigationView.getMenu();
        signup = (Button) ab.findViewById(R.id.signup);
        login = (Button) ab.findViewById(R.id.login);
        logout=ab.findViewById(R.id.logout);
        name=(TextView)ab.findViewById(R.id.name_user);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        RadioButton rb_marketplace = findViewById(R.id.rb_marketplace);
        RadioButton rb_business = findViewById(R.id.rb_businesses);
        market = findViewById(R.id.mar);
        help = (Button) findViewById(R.id.help);
        layoutmain = findViewById(R.id.abc);
        layoutsec = findViewById(R.id.bcd);
        params = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width = 300;
        params.height = 300;
        params.rightMargin = 15;
        params1 = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.width = 300;
        params1.rightMargin = 15;
        getData();
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        if (extras!=null) {
//            //Intent intent = getIntent();
//
//            flag = extras.getInt("Flag");
//            full_name=extras.getString("Name");
//            email=extras.getString("mail");
//            image=extras.getString("image");
//            phone_no=extras.getString("phone");
//            id=extras.getInt("id");
//            created=extras.getString("create");
//            updated=extras.getString("update");
//            Toast.makeText(this, "I have done this", Toast.LENGTH_SHORT).show();
//        }
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(Navigation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Navigation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                onLocationChanged(location);
            }
        });
        if(flag==1)
        {
            name.setText(full_name);
            login.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            show.findItem(R.id.nav_send).setVisible(true);
            show.findItem(R.id.nav_share).setVisible(true);
            show.findItem(R.id.advertise).setVisible(true);
            show.findItem(R.id.loginPage).setVisible(true);
            logout.setVisibility(View.VISIBLE);
            show.findItem(R.id.loginPage).setTitle(full_name+"  GO!!");
        }
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, 0);
            }
        });
        rb_marketplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, Navigation_market.class);
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
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, Navigation_market.class);
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, Login.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, SignUp.class);
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
                Intent intent=new Intent(Navigation.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, Help.class);
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
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, 0);
            }
        });


        //Loader
        btnSearch = new LoaderManager.LoaderCallbacks<ArrayList<ExampleItem>>() {
            @Override
            public Loader<ArrayList<ExampleItem>> onCreateLoader(int id, Bundle args) {
                LoaderBtnSearch loaderBtnSearch = new LoaderBtnSearch(Navigation.this,q,subV,"https://serv.kesbokar.com.au/jil.0.1/v2/yellowpages",stateid,subType,lat,longitude);
                return loaderBtnSearch;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<ExampleItem>> loader, ArrayList<ExampleItem> data) {
                switch (loader.getId()){
                    case LOADER_ID_BTNSRCH:
                        if(data != null && q.length()!=0){
                            exampleItems = data;
                            Log.i("Search", data.toString());
                            Intent intent = new Intent(Navigation.this,Buisness_Listing.class);
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
                LoaderBusSuburb loaderBusSuburb = new LoaderBusSuburb(Navigation.this,querySub,"http://serv.kesbokar.com.au/jil.0.1/v2/yellowpages/search/cities");
                return loaderBusSuburb;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<StateAndSuburb>> loader, ArrayList<StateAndSuburb> data) {
                if (data.size() != 0) {
                    valsSub = data;
                    Log.i("Tag", valsSub + "");
                    ArrayAdapter<StateAndSuburb> adapter=new ArrayAdapter<StateAndSuburb>(Navigation.this,android.R.layout.simple_dropdown_item_1line,valsSub);
                    textView2=findViewById(R.id.bs2);
                    textView2.setAdapter(adapter);
                    getLoaderManager().destroyLoader(LOADER_ID_BUSVAL);
                } else {
                   // Toast.makeText(Navigation.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoaderReset(Loader<ArrayList<StateAndSuburb>> loader) {

            }
        };
        businessSearch = new LoaderManager.LoaderCallbacks<ArrayList<String>>() {
            @Override
            public Loader<ArrayList<String>> onCreateLoader(int id, Bundle args) {
                LoaderBusSearch loaderBusSearch = new LoaderBusSearch(Navigation.this,query,"http://serv.kesbokar.com.au/jil.0.1/v2/yellowpages/search");
                return loaderBusSearch;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
                if (data.size() != 0) {
                    valsBus = data;
                    Log.i("Tag", valsBus + "");
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Navigation.this,android.R.layout.simple_dropdown_item_1line,valsBus);
                    textView.setAdapter(adapter);
                    getLoaderManager().initLoader(LOADER_ID_BUSSUB,null,businessSuburb);
                } else {
                 //   Toast.makeText(Navigation.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoaderReset(Loader<ArrayList<String>> loader) {

            }
        };
        buttonsDetailsLoaderCallbacks = new LoaderManager.LoaderCallbacks<ArrayList<ButtonsDetails>>() {
            @Override
            public Loader<ArrayList<ButtonsDetails>> onCreateLoader(int id, Bundle args) {
                String BASE_URL = "https://serv.kesbokar.com.au/jil.0.1/v2/business-categories?tlevel=0&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                LoaderButtons loaderButtons = new LoaderButtons(Navigation.this, BASE_URL);
                return loaderButtons;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<ButtonsDetails>> loader, final ArrayList<ButtonsDetails> data) {
                switch (loader.getId()) {
                    case LOADER_ID_BUSINESS:
                        // Add image path for imagebutton from drawable folder.
                        if (data.size() != 0) {
                            dataSize = data.size();
                            layoutmain.setOrientation(LinearLayout.HORIZONTAL);
                            LinearLayout layout = new LinearLayout(Navigation.this);
                            layout.setOrientation(LinearLayout.HORIZONTAL);
                            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            imagebutton = new ImageButton[dataSize];
                            dynamicTxt = new TextView[dataSize];
                            layoutsec.removeAllViews();

                            for (i = 0; i < dataSize; i++) {
                                imagebutton[i] = new ImageButton(Navigation.this);
                                dynamicTxt[i] = new TextView(Navigation.this);
                               // layout[] = new LinearLayout();

//                    imagebutton[i].setImageResource(R.mipmap.ic_launcher_round);
                                imagebutton[i].setBackground(getDrawable(R.drawable.button_bg_round));
                                imagebutton[i].setLayoutParams(params);
                                imagebutton[i].setTag(data.get(i).getId());
                                final int index = i;
                                String imgURL = "https://www.kesbokar.com.au/uploads/category/" + data.get(i).getImage();
                                Picasso.with(Navigation.this).load(imgURL).into(imagebutton[i]);
                                imagebutton[i].setAdjustViewBounds(true);
                                //new DownLoadImageTask(imagebutton[i]).execute(imgURL);
                                imagebutton[i].setId(data.get(i).getId());
                                int ID = imagebutton[i].getId();
                                imagebutton[i].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String url = "http://serv.kesbokar.com.au/jil.0.1/v2/yellowpages?&caturl="+data.get(index).getUrl()+"&catid="+data.get(index).getId()+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                        Intent intent = new Intent(Navigation.this, Buisness_Listing.class);
                                        intent.putExtra("URL",url);
                                        intent.putExtra("CHOICE","imgBtnService");
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivityForResult(intent, 0);
                                        overridePendingTransition(0, 0);
                                    }
                                });

                                dynamicTxt[i].setText(data.get(i).getTitle());
                                dynamicTxt[i].setLayoutParams(params1);
                                dynamicTxt[i].setGravity(Gravity.CENTER_HORIZONTAL);
                                layoutmain.removeAllViews();

                                //layoutmain.addView(layout);
                                //layout.removeAllViews();
                                layout.addView(imagebutton[i]);
                                layoutsec.addView(dynamicTxt[i]);
                                layoutmain.addView(layout);

                                getLoaderManager().initLoader(LOADER_ID_SERVICES, null, serviceExpertSpaceLoaderCallbacks);
                            }
                        } else {
                           // Toast.makeText(Navigation.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<ButtonsDetails>> loader) {

            }
        };
        serviceExpertSpaceLoaderCallbacks = new LoaderManager.LoaderCallbacks<ArrayList<ServiceExpertSpace>>() {
            @Override
            public Loader<ArrayList<ServiceExpertSpace>> onCreateLoader(int id, Bundle args) {
                LoaderServices loaderServices = new LoaderServices(Navigation.this);
                return loaderServices;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<ServiceExpertSpace>> loader, final ArrayList<ServiceExpertSpace> serviceExpertSpaces) {
//                String BASE_URL = "https://serv.kesbokar.com.au/jil.0.1/v2/yellowpage-featured?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";;
                switch (loader.getId()) {
                    case LOADER_ID_SERVICES:
                        // Prepare textview object programmatically



                        //if(serviceExpertSpaces.size()!=0){
                        for (i = 0; i < 4; i++) {
                            bc[i].setText(serviceExpertSpaces.get(i).getName());
                            bd[i].setText(serviceExpertSpaces.get(i).getCat_title() + " - " + serviceExpertSpaces.get(i).getCity().getTitle() + " , " + serviceExpertSpaces.get(i).getState().getTitle());

                            String imgURL = "https://www.kesbokar.com.au/uploads/yellowpage/" + serviceExpertSpaces.get(i).getImageLogo();
                            Picasso.with(Navigation.this).load(imgURL).into(bi[i]);

                            //new DownLoadImageTask(bi[i]).execute(imgURL);
                            final int index = i;
                            final String ab = serviceExpertSpaces.get(i).getCity().getTitle().replaceAll(" ", "+");

                            bi[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url = "https://www.kesbokar.com.au/business/" + ab + "/" + serviceExpertSpaces.get(index).getUrlname() + "/" + serviceExpertSpaces.get(index).getId();
                                    Intent intent = new Intent(Navigation.this, WebViewActivity.class);
                                    intent.putExtra("URL", url);
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
                            getLoaderManager().initLoader(LOADER_ID_MARKET, null, MarketPlaceApiCallbacks);

                        }
                        //}
//                        else{
//                            Toast.makeText(Navigation.this, "no internet connection", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                }
            }


            @Override
            public void onLoaderReset(Loader<ArrayList<ServiceExpertSpace>> loader) {

            }
        };
        MarketPlaceApiCallbacks = new LoaderManager.LoaderCallbacks<ArrayList<MarketPlaceApi>>() {
            @Override
            public Loader<ArrayList<MarketPlaceApi>> onCreateLoader(int id, Bundle args) {
                LoaderMarket loaderMarket = new LoaderMarket(Navigation.this);
                return loaderMarket;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<MarketPlaceApi>> loader, final ArrayList<MarketPlaceApi> marketPlaceApis) {
                switch (loader.getId()) {
                    case LOADER_ID_MARKET:
                        for (int j = 0; j < 4; j++) {

                            mc[j].setText(marketPlaceApis.get(j).getName());
                            md[j].setText(marketPlaceApis.get(j).getCat_title() + " - " + marketPlaceApis.get(j).getCity().getTitle() + " , " + marketPlaceApis.get(j).getState().getTitle());

                            String imgURL = "https://www.kesbokar.com.au/uploads/product/thumbs/" + marketPlaceApis.get(j).getImageLogo();
                            Picasso.with(Navigation.this).load(imgURL).into(mi[j]);
                            //new DownLoadImageTask(mi[j]).execute(imgURL);
                            final int index = j;
                            final String cat = marketPlaceApis.get(j).getCat_title().replaceAll("", "-");
                            final String ab = marketPlaceApis.get(j).getCity().getTitle().replaceAll(" ", "+");
                            final String url = "https://www.kesbokar.com.au/marketplace/" + ab + "/" + marketPlaceApis.get(index).getCat_title() + "/" + marketPlaceApis.get(index).getUrlname() + "/" + marketPlaceApis.get(index).getId();

                            mi[j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //url = "httpss://www.kesbokar.com.au/marketplace/" + ab + "/" + marketPlaceApis.get(index).getCat_title()+ marketPlaceApis.get(index).getUrlname() + "/" + marketPlaceApis.get(index).getId();
                                    Intent intent = new Intent(Navigation.this, WebViewActivity.class);
                                    intent.putExtra("URL", url);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                        }
                        getLoaderManager().initLoader(LOADER_ID_BUSVAL,null,businessSearch);
                        getLoaderManager().destroyLoader(LOADER_ID_BUSINESS);
                        getLoaderManager().destroyLoader(LOADER_ID_SERVICES);
                        getLoaderManager().destroyLoader(LOADER_ID_MARKET);
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<MarketPlaceApi>> loader) {

            }
        };

        if(isNetworkAvailable()) {
            getLoaderManager().initLoader(LOADER_ID_BUSINESS, null, buttonsDetailsLoaderCallbacks);

        }else{
            setContentView(R.layout.no_internet);
        }


        textView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                subV = stateAndSuburb.getValue();
                stateid = stateAndSuburb.getId();
                subType = stateAndSuburb.getType();
            }
        });

        textView2.addTextChangedListener(new TextWatcher() {
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

        btnSrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q = textView.getText().toString();
                Log.i("Q and subV", q + " " + subV);
                if(q.length() == 0 && subV.length() == 0){
                    Toast.makeText(Navigation.this, "Cannot Search Empty fields", Toast.LENGTH_SHORT).show();
                }
                else if (subV.length()==0)
                {
                    Toast.makeText(Navigation.this, "Cannot Search Empty State", Toast.LENGTH_SHORT).show();
                }
                else if (q.length()==0)
                {
                    Toast.makeText(Navigation.this, "Cannot Search Empty Query", Toast.LENGTH_SHORT).show();
                }
                getLoaderManager().initLoader(LOADER_ID_BTNSRCH,null,btnSearch);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isNetworkAvailable()) {
            getLoaderManager().restartLoader(LOADER_ID_BUSINESS, null, buttonsDetailsLoaderCallbacks);
        }else{
            setContentView(R.layout.no_internet);
        }
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
        int Id = item.getItemId();

        if (Id == R.id.nav_send) {

        } else if (Id == R.id.nav_share) {

        } else if (Id == R.id.about) {
            Intent intent = new Intent(Navigation.this, About.class);
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

        } else if (Id == R.id.career) {
            Intent intent = new Intent(Navigation.this, Career.class);
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

        } else if (Id == R.id.advertise) {

        }else if (Id == R.id.loginPage) {
            Intent intent=new Intent(Navigation.this,LoginData.class);
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
    void getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, new LocationListener()
            {
                @Override
                public void onLocationChanged(Location location) {
                   // Toast.makeText(Navigation.this, "Lat:"+location.getLatitude() + "\n Long: " + location.getLongitude(), Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(Navigation.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

                }
            });
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitudeV=location.getLongitude();
        double latitude=location.getLatitude();
        Geocoder gc = new Geocoder(Navigation.this);

        List<Address> list = null;
        try {

            list = gc.getFromLocation(latitude, longitudeV,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address address = list.get(0);

        StringBuffer str = new StringBuffer();
        str.append(address.getLocality()+" " );
        str.append(address.getSubAdminArea()+" " );
        str.append(address.getAdminArea()+" ");
        str.append(address.getCountryName()+" ");
        str.append(address.getCountryCode()+" ");

        String strAddress = str.toString();

        //Toast.makeText(this, "Longitude"+longitudeV+"     Latitude"+latitude +"   "+ strAddress, Toast.LENGTH_SHORT).show();
        lat = latitude;
        longitude = longitudeV;
        textView2.setText(strAddress);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(Navigation.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}