package com.kesbokar.kesbokar;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class WebViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<ArrayList<ButtonsDetails>>{
    static String URL1;
    Document doc1;
    public static WebView webView;
    String loginId, loginPass, full_name, email, image1, phone_no,created,updated;
    int id1,flag;
    ImageView search_btn;
    private AutoCompleteTextView autoCompleteTextViewOne,autoCompleteTextViewTwo;
    private Button btnAlertDialogSearch;
    private ArrayList<ExampleItem> exampleItems;
    private static final int LOADER_ID_BUSVAL = 3;
    private static final int LOADER_ID_BUSSUB = 4;
    private static final int LOADER_ID_BTNSRCH = 5;
    Button rqst_quote;
    String entry_level;

    private LoaderManager.LoaderCallbacks<ArrayList<String>> businessSearch;
    private androidx.loader.app.LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>> businessSuburb;
    private LoaderManager.LoaderCallbacks<ArrayList<ExampleItem>> btnSearch;
    private ArrayList<String> valsBus;
    private ArrayList<StateAndSuburb> valsSub;
    private String query = "";
    String querySub,subV,subType,q;
    int stateid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        final ScrollView scrollView=(ScrollView)findViewById(R.id.scroll);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getData();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        webView = (WebView) findViewById(R.id.webview);
        search_btn=findViewById(R.id.search_btn);
        valsBus = new ArrayList<>();
        valsSub = new ArrayList<>();
        querySub = subV = subType = q = "";
        exampleItems = new ArrayList<>();
        rqst_quote=findViewById(R.id.rqst_quote);
        Intent intent = getIntent();
        Bundle extras=intent.getExtras();
        URL1 = extras.getString("URL");
        flag = extras.getInt("Flag");
        full_name=extras.getString("Name");
        Menu show=navigationView.getMenu();
        View ab = navigationView.getHeaderView(0);
        TextView name1=(TextView)ab.findViewById(R.id.name_user);
        Button signup=(Button)ab.findViewById(R.id.signup);
        Button login=(Button)ab.findViewById(R.id.login);
        Button logout=ab.findViewById(R.id.logout);
        email=extras.getString("mail");
        image1=extras.getString("image");
        phone_no=extras.getString("phone");
        id1=extras.getInt("id");
        created=extras.getString("create");
        updated=extras.getString("update");
        if (entry_level.equals("1"))
        {
            rqst_quote.setVisibility(View.INVISIBLE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebViewActivity.this, Login.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebViewActivity.this, SignUp.class);
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
                Intent intent=new Intent(WebViewActivity.this,Navigation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestAlertDialogBox();
            }
        });
        getData();
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
        }
        rqst_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==1) {
                    Dialog dialog1 = new Dialog(WebViewActivity.this);
                    dialog1.setContentView(R.layout.get_in_touch);
                    dialog1.getWindow();
                    dialog1.show();
                }
                else {
                    Intent intent1=new Intent(WebViewActivity.this,Login.class);
                    startActivity(intent1);
                }
            }
        });

        new MyAsyncTask().execute();
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
            Intent about=new Intent(WebViewActivity.this,ProductManagementActivity.class);
            startActivity(about);

        } else if (id == R.id.nav_share) {
            Intent about=new Intent(WebViewActivity.this,Main3BusinessActivity.class);
            startActivity(about);

        } else if (id == R.id.about) {
            Intent about=new Intent(WebViewActivity.this,About.class);
            startActivity(about);

        } else if (id == R.id.career) {
            Intent career=new Intent(WebViewActivity.this,Career.class);
            startActivity(career);

        } else if (id == R.id.advertise) {

        }else if (id == R.id.loginPage) {
            Intent intent=new Intent(WebViewActivity.this,LoginData.class);
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
    public Loader<ArrayList<ButtonsDetails>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ButtonsDetails>> loader, ArrayList<ButtonsDetails> data) {

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ButtonsDetails>> loader) {

    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;

        }
    }
    @Override
    public void onBackPressed () {
        Intent intent=new Intent(WebViewActivity.this,Navigation.class);
        intent.putExtra("Flag", flag);
        intent.putExtra("Name",full_name);
        intent.putExtra("mail",email);
        intent.putExtra("image",image1);
        intent.putExtra("phone",phone_no);
        intent.putExtra("create",created);
        intent.putExtra("update",updated);
        intent.putExtra("id",id1);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(intent, 0);
        overridePendingTransition(0, 0);
        finish();

    }
    private static class MyAsyncTask extends AsyncTask<Void, Void, Document> {
        @Override
        protected Document doInBackground(Void... voids) {

            Document document = null;
            try {
                document= Jsoup.connect(URL1).get();
                document.getElementsByClass("navbar navbar-default").remove();
                document.getElementsByClass("needhelp_in_touch").remove();
                document.getElementsByClass("footer_area").remove();
                document.getElementsByClass("section additional-details clear proerty-th").remove();
                document.getElementsByClass("breadcrumb").remove();
                document.getElementsByClass("page-subheader sorting pl0").remove();
                document.getElementsByClass("sel-filters text-left").remove();
                document.getElementsByClass("text-center btnn-position").remove();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return document;
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            //webView.setWebViewClient(new WebViewClient());
            WebSettings webSettings=webView.getSettings();
            //webSettings.setBuiltInZoomControls(true);
            webView.loadDataWithBaseURL(URL1,document.toString(),"text/html","utf-8","");
            //webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
            webSettings.setJavaScriptEnabled(true);
            
        }
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
        SharedPreferences loginData1=getSharedPreferences("entry",0);
        entry_level=loginData1.getString("entry_level","");

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
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        autoCompleteTextViewOne = dialog.findViewById(R.id.autoCompleteTextViewOne);
        autoCompleteTextViewTwo = dialog.findViewById(R.id.autoCompleteTextViewTwo);
        btnAlertDialogSearch = dialog.findViewById(R.id.btnAlertDialogSearch);


        btnSearch = new LoaderManager.LoaderCallbacks<ArrayList<ExampleItem>>() {
            @Override
            public Loader<ArrayList<ExampleItem>> onCreateLoader(int id, Bundle args) {
                LoaderBtnSearch loaderBtnSearch = new LoaderBtnSearch(WebViewActivity.this,q,subV,"https://serv.kesbokar.com.au/jil.0.1/v2/yellowpages",stateid,subType,0.0,0.0);
                return loaderBtnSearch;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<ExampleItem>> loader, ArrayList<ExampleItem> data) {
                switch (loader.getId()){
                    case LOADER_ID_BTNSRCH:
                        if(data != null && q.length()!=0){
                            exampleItems = data;
                            Log.i("Search", data.toString());
                            Intent intent = new Intent(WebViewActivity.this,Buisness_Listing.class);
                            intent.putExtra("CHOICE", "btnSearch");
                            intent.putParcelableArrayListExtra("ARRAYLIST",exampleItems);
                            startActivity(intent);
                            finish();
                        }
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<ExampleItem>> loader) {

            }
        };



        businessSuburb = new androidx.loader.app.LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>>() {
            @NonNull
            @Override
            public androidx.loader.content.Loader<ArrayList<StateAndSuburb>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderBusSuburb loaderBusSuburb = new LoaderBusSuburb(WebViewActivity.this,querySub,"http://serv.kesbokar.com.au/jil.0.1/v2/yellowpages/search/cities");
                return loaderBusSuburb;
            }

            @Override
            public void onLoadFinished(@NonNull androidx.loader.content.Loader<ArrayList<StateAndSuburb>> loader, ArrayList<StateAndSuburb> data){
                if (data.size() != 0) {
                    valsSub = data;
                    Log.i("Tag", valsSub + "");
                    ArrayAdapter<StateAndSuburb> adapter=new ArrayAdapter<StateAndSuburb>(WebViewActivity.this,android.R.layout.simple_dropdown_item_1line,valsSub);
                    autoCompleteTextViewTwo.setAdapter(adapter);
                    getLoaderManager().destroyLoader(LOADER_ID_BUSVAL);
                } else {
                    Toast.makeText(WebViewActivity.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoaderReset(@NonNull androidx.loader.content.Loader<ArrayList<StateAndSuburb>> loader) {

            }
        };
        businessSearch = new LoaderManager.LoaderCallbacks<ArrayList<String>>() {
            @Override
            public Loader<ArrayList<String>> onCreateLoader(int id, Bundle args) {
                LoaderBusSearch loaderBusSearch = new LoaderBusSearch(WebViewActivity.this,query,"http://serv.kesbokar.com.au/jil.0.1/v2/yellowpages/search");
                return loaderBusSearch;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
                if (data.size() != 0) {
                    valsBus = data;
                    Log.i("Tag", valsBus + "");
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(WebViewActivity.this,android.R.layout.simple_dropdown_item_1line,valsBus);
                    autoCompleteTextViewOne.setAdapter(adapter);
                    getSupportLoaderManager().initLoader(LOADER_ID_BUSSUB,null,businessSuburb);
                } else {
                    Toast.makeText(WebViewActivity.this, "No internet Connection", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(WebViewActivity.this, "Cannot Search Empty fields", Toast.LENGTH_SHORT).show();
                }
                else if (subV.length()==0)
                {
                    Toast.makeText(WebViewActivity.this, "Cannot Search Empty State", Toast.LENGTH_SHORT).show();
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
                getSupportLoaderManager().restartLoader(LOADER_ID_BUSSUB,null,businessSuburb);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
