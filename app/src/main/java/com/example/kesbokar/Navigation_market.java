package com.example.kesbokar;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class Navigation_market extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    TextView ab;
    String about;
    LinearLayout.LayoutParams params;
    int i;
    LinearLayout relativelayout;
    private static int dataSize = 0;
    ImageButton[] imagebutton;
    //private static int dataSize = 0;
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
    private LoaderManager.LoaderCallbacks<String> btnSearch;

    private ArrayList<String> valsMarket;
    private ArrayList<StateAndSuburb> valsSub;

    private static ArrayList<String> tags;
    Toolbar toolbar;
    ImageView[] bi, mi;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_market);
        final ScrollView scrollView=(ScrollView)findViewById(R.id.scroll);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ab=(TextView)findViewById(R.id.about);
        relativelayout = findViewById(R.id.abc1);
        params = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width=300;
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

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        top=(Button)findViewById(R.id.top);
        View ab = navigationView.getHeaderView(0);
        signup=(Button)ab.findViewById(R.id.signup);
        login=(Button)ab.findViewById(R.id.login);

        btnSrch = findViewById(R.id.marBtnSrch);
        RadioGroup radioGroup=findViewById(R.id.radio_group);
        RadioButton rb_marketplace=findViewById(R.id.rb_marketplace);
        RadioButton rb_business=findViewById(R.id.rb_businesses);
        business=findViewById(R.id.buis);
        help=(Button)findViewById(R.id.help);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0,0);
            }
        });
        rb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Navigation_market.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent,0);
                overridePendingTransition(0,0);
                finish();
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Navigation_market.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent,0);
                overridePendingTransition(0,0);
                finish();
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

        btnSearch = new LoaderManager.LoaderCallbacks<String>() {
            @Override
            public Loader<String> onCreateLoader(int id, Bundle args) {
                LoaderBtnSearch loaderBtnSearch = new LoaderBtnSearch(Navigation_market.this,q,subV,"http://serv.kesbokar.com.au/jil.0.1/v2/product",stateid,subType);
                return loaderBtnSearch;
            }

            @Override
            public void onLoadFinished(Loader<String> loader, String data) {
                switch (loader.getId()){
                    case LOADER_ID_BTNSRCH:
                        if(data != null){
                            Toast.makeText(Navigation_market.this, data, Toast.LENGTH_SHORT).show();
                            Log.i("Search", data);
                        }
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<String> loader) {

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
                    Toast.makeText(Navigation_market.this, "No internet Connection", Toast.LENGTH_SHORT).show();
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
                    ml=findViewById(R.id.ml);
                    ml.setAdapter(adapter);
                    getLoaderManager().initLoader(LOADER_ID_MARSUB,null,marketSub);
                } else {
                    Toast.makeText(Navigation_market.this, "No internet Connection", Toast.LENGTH_SHORT).show();
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
                        if (data.size() != 0) {
                            dataSize = data.size();
                            LinearLayout layout = new LinearLayout(Navigation_market.this);
                            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            imagebutton = new ImageButton[dataSize];
                            for (i = 0; i < dataSize; i++) {
                                imagebutton[i] = new ImageButton(Navigation_market.this);
//                    imagebutton[i].setImageResource(R.mipmap.ic_launcher_round);
                                imagebutton[i].setBackground(getDrawable(R.drawable.button_bg_round_market));
                                imagebutton[i].setLayoutParams(params);
                                imagebutton[i].setTag(data.get(i).getId());
                                final int index = i;
                                String imgURL = "https://www.kesbokar.com.au/uploads/category/" + data.get(i).getImage();
                                Picasso.with(Navigation_market.this).load(imgURL).into(imagebutton[i]);
                                //new DownLoadImageTask(imagebutton[i]).execute(imgURL);
                                imagebutton[i].setId(data.get(i).getId());
                                int ID = imagebutton[i].getId();
                                imagebutton[i].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String url = "https://www.kesbokar.com.au/marketplace/" + data.get(index).getUrl().replaceAll(" ","%20") + "/c" + data.get(index).getId();
                                        Intent intent = new Intent(Navigation_market.this, WebViewActivity.class);
                                        intent.putExtra("URL", url);
                                        startActivity(intent);
                                        finish();
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
                if(subType.equals("state")) {
                    String url = "https://www.kesbokar.com.au/marketplace/" + subV + "/sl" + stateid + "?q=" + q;
                    Intent intent = new Intent(Navigation_market.this, WebViewActivity.class);
                    intent.putExtra("URL", url);
                    startActivity(intent);
                    finish();
                }
                if(subType.equals("city")){
                    String url = "https://www.kesbokar.com.au/marketplace/" + subV + "/l" + stateid + "?q=" + q;
                    Intent intent = new Intent(Navigation_market.this, WebViewActivity.class);
                    intent.putExtra("URL", url);
                    startActivity(intent);
                    finish();
                }
            }
        });

        getLoaderManager().initLoader(LOADER_ID_BUSINESS,null,buttonsDetailsLoaderCallbacks);
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
