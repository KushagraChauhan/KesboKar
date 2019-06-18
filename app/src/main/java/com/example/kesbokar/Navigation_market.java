package com.example.kesbokar;

import android.Manifest;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.DocumentsContract;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class Navigation_market extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LocationListener {
    TextView ab;
    String about;
    LinearLayout.LayoutParams params;
    int i;
    ImageView search;
    Button logout;
    TextView name;
    int flag=0;
    LinearLayout relativelayout;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id;
    private static int dataSize = 0;
    ImageButton[] imagebutton;
    private static final int LOADER_ID_BUSINESS = 0;
    private static final int LOADER_ID_SERVICES = 1;
    private static final int LOADER_ID_MARKET = 2;
    private static final int LOADER_ID_MARVAL = 3;
    private static final int LOADER_ID_MARSUB = 4;
    private static final int LOADER_ID_BTNSRCH = 5;

    private LoaderManager.LoaderCallbacks<ArrayList<ButtonsDetails>> buttonsDetailsLoaderCallbacks;
    private LoaderManager.LoaderCallbacks<ArrayList<ServiceExpertSpace>> serviceExpertSpaceLoaderCallbacks;
    private LoaderManager.LoaderCallbacks<ArrayList<MarketPlaceApi>> MarketPlaceApiCallbacks;
    private LoaderManager.LoaderCallbacks<ArrayList<String>> marketSearch;
    private LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>> marketSub;
    private LoaderManager.LoaderCallbacks<ArrayList<MarketIem>> btnSearch;

    private ArrayList<String> valsMarket;
    private ArrayList<StateAndSuburb> valsSub;

    private static ArrayList<String> tags;
    Toolbar toolbar;
    ImageView[] bi, mi;
    Button location;
    TextView[] bc, bd, mc, md;
    private static final int LOADER_ID = 1;
    //Toolbar toolbar;
    HorizontalScrollView category;
    DrawerLayout drawer;
    LinearLayout layout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Button top,signup,login,help,business;
    AutoCompleteTextView ml;
    AutoCompleteTextView ms;
    String q, subV;
    String querySub;
    String subType;
    int stateid;
    Button btnSrch;
    private ArrayList<MarketIem> marketItems;

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isNetworkAvailable())
        {
            setContentView(R.layout.no_internet);
        }
        else {
            setContentView(R.layout.activity_navigation_market);
        }
        final ScrollView scrollView=(ScrollView)findViewById(R.id.scroll);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ab=(TextView)findViewById(R.id.about);
        relativelayout = findViewById(R.id.abc1);
        params = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width=300;
        search=findViewById(R.id.search);
        params.height=300;
        params.rightMargin=15;
        valsMarket = new ArrayList<>();
        valsSub = new ArrayList<>();
        q = subV = querySub = "";
        layout = new LinearLayout(Navigation_market.this);
        bi = new ImageView[4];
        mi = new ImageView[3];
        bc = new TextView[4];
        mc = new TextView[3];
        md = new TextView[3];
        bd = new TextView[4];
        location=findViewById(R.id.location);
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

        ms = findViewById(R.id.ms);



        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        marketItems = new ArrayList<>();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        top=(Button)findViewById(R.id.top);
        View ab = navigationView.getHeaderView(0);
        Menu show=navigationView.getMenu();
        name=(TextView)ab.findViewById(R.id.name_user);
        signup=(Button)ab.findViewById(R.id.signup);
        login=(Button)ab.findViewById(R.id.login);
        logout=ab.findViewById(R.id.logout);
        btnSrch = findViewById(R.id.marBtnSrch);
        RadioGroup radioGroup=findViewById(R.id.radio_group);
        RadioButton rb_marketplace=findViewById(R.id.rb_marketplace);
        RadioButton rb_business=findViewById(R.id.rb_businesses);
        business=findViewById(R.id.buis);
        ml=findViewById(R.id.ml);
        help=(Button)findViewById(R.id.help);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0,0);
            }
        });
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras!=null) {
            //Intent intent = getIntent();

            flag = extras.getInt("Flag");
            full_name=extras.getString("Name");
            email=extras.getString("mail");
            image=extras.getString("image");
            phone_no=extras.getString("phone");
            id=extras.getInt("id");
            created=extras.getString("create");
            updated=extras.getString("update");
           // Toast.makeText(this, "I have done this", Toast.LENGTH_SHORT).show();
        }
        if(flag==1)
        {
            name.setText(full_name);
            login.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            show.findItem(R.id.nav_send).setVisible(true);
            show.findItem(R.id.nav_share).setVisible(true);
            show.findItem(R.id.advertise).setVisible(true);
            logout.setVisibility(View.VISIBLE);
            show.findItem(R.id.loginPage).setVisible(true);
            show.findItem(R.id.loginPage).setTitle(full_name+"  GO!!");
        }
        rb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Navigation_market.this,Navigation.class);
                intent.putExtra("Flag", flag);
                intent.putExtra("Name",full_name);
                intent.putExtra("mail",email);
                intent.putExtra("image",image);
                intent.putExtra("phone",phone_no);
                intent.putExtra("create",created);
                intent.putExtra("update",updated);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent,0);
                overridePendingTransition(0,0);
                finish();
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
                Intent intent=new Intent(Navigation_market.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Navigation_market.this,Navigation.class);
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
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(Navigation_market.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Navigation_market.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, 0);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Navigation_market.this,Login.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Navigation_market.this,SignUp.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help1=new Intent(Navigation_market.this,Help.class);
                startActivity(help1);
            }
        });

        btnSearch = new LoaderManager.LoaderCallbacks<ArrayList<MarketIem>>() {
            @Override
            public Loader<ArrayList<MarketIem>> onCreateLoader(int id, Bundle args) {
                LoaderBtnSrchMarket loaderBtnSearch = new LoaderBtnSrchMarket(Navigation_market.this,q,subV,"http://serv.kesbokar.com.au/jil.0.1/v2/product",stateid,subType,0.0,0.0);
                return loaderBtnSearch;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<MarketIem>> loader, ArrayList<MarketIem> data) {
                switch (loader.getId()){
                    case LOADER_ID_BTNSRCH:
                        if(data != null){
                            marketItems = data;
                            Log.i("Search", data.toString());
                            Intent intent = new Intent(Navigation_market.this,MarketListing.class);
                            intent.putExtra("CHOICE", "btnSearch");
                            intent.putParcelableArrayListExtra("ARRAYLIST",marketItems);
                            startActivity(intent);
                            //Toast.makeText(Navigation_market.this, data, Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<MarketIem>> loader) {

            }
        };
        marketSub = new LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>>() {
            @Override
            public Loader<ArrayList<StateAndSuburb>> onCreateLoader(int id, Bundle args) {
                LoaderBusSuburb loaderBusSuburb = new LoaderBusSuburb(Navigation_market.this,querySub,"http://serv.kesbokar.com.au/jil.0.1/v2/product/search/cities");
                return loaderBusSuburb;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<StateAndSuburb>> loader, ArrayList<StateAndSuburb> data) {
                if (data.size() != 0) {
                    valsSub = data;
                    Log.i("Tag Sub", valsSub + "");
                    ArrayAdapter<StateAndSuburb> adapter=new ArrayAdapter<StateAndSuburb>(Navigation_market.this,android.R.layout.simple_dropdown_item_1line,valsSub);

                    ms.setAdapter(adapter);
                } else {
                   // Toast.makeText(Navigation_market.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                }
                getLoaderManager().destroyLoader(LOADER_ID_MARVAL);
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<StateAndSuburb>> loader) {

            }
        };
        marketSearch = new LoaderManager.LoaderCallbacks<ArrayList<String>>() {
            @Override
            public Loader<ArrayList<String>> onCreateLoader(int id, Bundle args) {
                LoaderMarketSearch loaderMarketSearch= new LoaderMarketSearch(Navigation_market.this,"","http://serv.kesbokar.com.au/jil.0.1/v2/product/search");
                return loaderMarketSearch;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
                if (data.size() != 0) {
                    valsMarket = data;
                    Log.i("Tag", valsMarket + "");
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Navigation_market.this,android.R.layout.simple_dropdown_item_1line,valsMarket);

                    ml.setAdapter(adapter);
                    getLoaderManager().initLoader(LOADER_ID_MARSUB,null,marketSub);
                } else {
                  //  Toast.makeText(Navigation_market.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoaderReset(Loader<ArrayList<String>> loader) {

            }
        };
        buttonsDetailsLoaderCallbacks = new LoaderManager.LoaderCallbacks<ArrayList<ButtonsDetails>>() {
            @Override
            public Loader<ArrayList<ButtonsDetails>> onCreateLoader(int id, Bundle args) {
                String BASE_URL = "https://serv.kesbokar.com.au/jil.0.1/v2/product-categories?tlevel=0&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK\n";
                LoaderButtons loaderButtons = new LoaderButtons(Navigation_market.this, BASE_URL);
                return loaderButtons;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<ButtonsDetails>> loader, final ArrayList<ButtonsDetails> data) {
                switch (loader.getId()) {
                    case LOADER_ID_BUSINESS:
                        // Add image path for imagebutton from drawable folder.
                        if(data!=null) {
                            if (data.size() != 0) {
                                dataSize = data.size();
                                LinearLayout layout = new LinearLayout(Navigation_market.this);
                                layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                imagebutton = new ImageButton[dataSize];
                                for (i = 0; i < dataSize; i++) {
                                    imagebutton[i] = new ImageButton(Navigation_market.this);
//                    imagebutto[i].setImageResource(R.mipmap.ic_launcher_round);
                                    Drawable drawable = getDrawable(R.drawable.button_bg_round_market);
                                    imagebutton[i].setBackground(drawable);
                                    imagebutton[i].setLayoutParams(params);
                                    imagebutton[i].setTag(data.get(i).getId());
                                    final int index = i;
                                    String imgURL = "https://www.kesbokar.com.au/uploads/category/" + data.get(i).getImage();
                                    Picasso.with(Navigation_market.this).load(imgURL).into(imagebutton[i]);
                                    imagebutton[i].setAdjustViewBounds(true);
                                    //new DownLoadImageTask(imagebutton[i]).execute(imgURL);
                                    imagebutton[i].setId(data.get(i).getId());
                                    int ID = imagebutton[i].getId();
                                    imagebutton[i].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String url = "http://serv.kesbokar.com.au/jil.0.1/v2/product?caturl=" + URLEncoder.encode(data.get(index).getUrl()) + "&catid=" + data.get(index).getId() + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                            Intent intent = new Intent(Navigation_market.this, MarketListing.class);
                                            intent.putExtra("URL", url);
                                            intent.putExtra("CHOICE", "imgBtnService");
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivityForResult(intent, 0);
                                            overridePendingTransition(0, 0);
                                        }
                                    });
                                    relativelayout.removeAllViews();
                                    relativelayout.addView(layout);
                                    layout.addView(imagebutton[i]);
                                    getLoaderManager().initLoader(LOADER_ID_SERVICES, null, serviceExpertSpaceLoaderCallbacks);
                                }
                            } else {
                                Toast.makeText(Navigation_market.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                           // Toast.makeText(Navigation_market.this, "No internet Connection", Toast.LENGTH_SHORT).show();

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
                LoaderServices loaderServices = new LoaderServices(Navigation_market.this);
                return loaderServices;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<ServiceExpertSpace>> loader, final ArrayList<ServiceExpertSpace> serviceExpertSpaces) {
//                String BASE_URL = "https://serv.kesbokar.com.au/jil.0.1/v2/yellowpage-featured?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";;
                switch (loader.getId()) {
                    case LOADER_ID_SERVICES:
                        //if(serviceExpertSpaces.size()!=0){
                        for (i = 0; i < 4; i++) {
                            bc[i].setText(serviceExpertSpaces.get(i).getName());
                            bd[i].setText(serviceExpertSpaces.get(i).getCat_title() + " - " + serviceExpertSpaces.get(i).getCity().getTitle() + " , " + serviceExpertSpaces.get(i).getState().getTitle());

                            String imgURL = "https://www.kesbokar.com.au/uploads/yellowpage/" + serviceExpertSpaces.get(i).getImageLogo();
                            Picasso.with(Navigation_market.this).load(imgURL).into(bi[i]);
                            //new DownLoadImageTask(bi[i]).execute(imgURL);
                            final int index = i;
                            final String ab = serviceExpertSpaces.get(i).getCity().getTitle().replaceAll(" ", "+");

                            bi[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url = "https://www.kesbokar.com.au/business/" + ab + "/" + serviceExpertSpaces.get(index).getUrlname() + "/" + serviceExpertSpaces.get(index).getId();
                                    Intent intent = new Intent(Navigation_market.this, WebViewActivity.class);
                                    intent.putExtra("URL", url);
                                    startActivity(intent);
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
                LoaderMarket loaderMarket = new LoaderMarket(Navigation_market.this);
                return loaderMarket;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<MarketPlaceApi>> loader, final ArrayList<MarketPlaceApi> marketPlaceApis) {
                switch (loader.getId()) {
                    case LOADER_ID_MARKET:
                        for (int j = 0; j < 3; j++) {
                            mc[j].setText(marketPlaceApis.get(j).getName());
                            md[j].setText(marketPlaceApis.get(j).getCat_title() + " - " + marketPlaceApis.get(j).getCity().getTitle() + " , " + marketPlaceApis.get(j).getState().getTitle());

                            String imgURL = "https://www.kesbokar.com.au/uploads/product/thumbs/" + marketPlaceApis.get(j).getImageLogo();
                            Picasso.with(Navigation_market.this).load(imgURL).into(mi[j]);
                            //new DownLoadImageTask(mi[j]).execute(imgURL);
                            final int index = j;
                            final String cat = marketPlaceApis.get(j).getCat_title().replaceAll("", "-");
                            final String ab = marketPlaceApis.get(j).getCity().getTitle().replaceAll(" ", "+");
                            final String url = "https://www.kesbokar.com.au/marketplace/" + ab + "/" + marketPlaceApis.get(index).getCat_title() + "/" + marketPlaceApis.get(index).getUrlname() + "/" + marketPlaceApis.get(index).getId();

                            mi[j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //url = "https://www.kesbokar.com.au/marketplace/" + ab + "/" + marketPlaceApis.get(index).getCat_title()+ marketPlaceApis.get(index).getUrlname() + "/" + marketPlaceApis.get(index).getId();
                                    Intent intent = new Intent(Navigation_market.this, WebViewActivity.class);
                                    intent.putExtra("URL", url);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                        }
                        getLoaderManager().initLoader(LOADER_ID_MARVAL,null,marketSearch);
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

        ms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                subV = stateAndSuburb.getValue();
                stateid = stateAndSuburb.getId();
                subType = stateAndSuburb.getType();
            }
        });

        ms.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                querySub = s.toString();
                getLoaderManager().restartLoader(LOADER_ID_MARSUB,null,marketSub);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q = ml.getText().toString();

                Log.i("Q and subV", q + " " + subV);
                if(q.length() == 0 && subV.length() == 0){
                    Toast.makeText(Navigation_market.this, "Cannot Search Empty fields", Toast.LENGTH_SHORT).show();
                }
                //getLoaderManager().initLoader(LOADER_ID_BTNSRCH,null,btnSearch);
                else if (subV.length()==0)
                {
                    Toast.makeText(Navigation_market.this, "Cannot Search Empty State", Toast.LENGTH_SHORT).show();
                }
                getLoaderManager().initLoader(LOADER_ID_BTNSRCH, null, btnSearch);
            }
        });

        if(isNetworkAvailable()) {
            getLoaderManager().initLoader(LOADER_ID_BUSINESS, null, buttonsDetailsLoaderCallbacks);

        }else{
            setContentView(R.layout.no_internet);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(LOADER_ID_BUSINESS, null, buttonsDetailsLoaderCallbacks);
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

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.about) {
            Intent about=new Intent(Navigation_market.this,About.class);
            startActivity(about);

        } else if (id == R.id.career) {
            Intent career=new Intent(Navigation_market.this,Career.class);
            startActivity(career);

        } else if (id == R.id.advertise) {

        }else if (id == R.id.loginPage) {
            Intent intent=new Intent(Navigation_market.this,LoginData.class);
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

    @Override
    public void onLocationChanged(Location location) {
        double longitude=location.getLongitude();
        double latitude=location.getLatitude();
        Geocoder gc = new Geocoder(Navigation_market.this);

        List<Address> list = null;
        try {

            list = gc.getFromLocation(latitude, longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert list != null;
        Address address = list.get(0);

        StringBuffer str = new StringBuffer();
        str.append("Name: " + address.getLocality() + "\n");
        str.append("Sub-Admin Ares: " + address.getSubAdminArea() + "\n");
        str.append("Admin Area: " + address.getAdminArea() + "\n");
        str.append("Country: " + address.getCountryName() + "\n");
        str.append("Country Code: " + address.getCountryCode() + "\n");

        String strAddress = str.toString();

       // Toast.makeText(this, "Longitude"+longitude+"     Latitude"+latitude +"   "+ strAddress, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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

//    @Override
//    public void onClick(View v) {
//        for (i=0;i<dataSize;i++)
//        {
//
//        }
//    }


    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageButton imageView;

        public DownLoadImageTask(ImageButton imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}
