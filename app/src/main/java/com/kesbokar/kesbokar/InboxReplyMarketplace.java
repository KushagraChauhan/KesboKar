package com.kesbokar.kesbokar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.LoaderManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InboxReplyMarketplace extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private LoaderManager.LoaderCallbacks<ArrayList<InboxMarketList>> busLoader;
    private static final int LOADER_BUS_PRO_LIST = 66;
    TextView subject,message;
    ListView listView;
    String replyMessage,replyBy,date1,subject1,message1;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    RequestQueue requestQueue;
    int id1,user_id;
    Intent intent;
    Bundle bundle;
    ArrayList<inbox_reply_market> get_for_replies;
    Button cancel,send;

    Button btnProductManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_inbox_market);
        Toolbar toolbar = findViewById(R.id.toolbar);
        subject=findViewById(R.id.tvSubject);
        message=findViewById(R.id.tvMessage);
        setSupportActionBar(toolbar);
        intent=getIntent();
        bundle=intent.getExtras();
        getData();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        requestQueue = Volley.newRequestQueue(this);
        navigationView.setNavigationItemSelectedListener(this);
        get_for_replies=new ArrayList<>();
        cancel=findViewById(R.id.btnCancel);
        send=findViewById(R.id.btnSend);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InboxReplyMarketplace.this,ManageHelpDeskActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0, 0);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url="http://serv.kesbokar.com.au/jil.0.1/v1/quotes-yellowpage/126/reply"+id;
                final String reply_message=((EditText)findViewById(R.id.etReply)).getText().toString();
                RequestQueue queue= Volley.newRequestQueue(InboxReplyMarketplace.this);
                //Toast.makeText(Help.this, "Ipaddress"+ip, Toast.LENGTH_SHORT).show();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(InboxReplyMarketplace.this, "Response"+"Your Query Has been Submitted", Toast.LENGTH_SHORT).show();
                        Log.i("Resposnse",response);

                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // errorLog.d("Error.Response", String.valueOf(error));
                                Toast.makeText(InboxReplyMarketplace.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        String id1=""+id;
                        String enquiry_id=""+bundle.getInt("id");
                        Map<String, String>  params = new HashMap<String, String >();
                        params.put("user_id", id1);
                        params.put("enquiry_id", enquiry_id);
                        params.put("reply_message", reply_message);
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");

                        return params;
                    }
                };
                RequestQueue requestQueue=Volley.newRequestQueue(InboxReplyMarketplace.this);
                queue.add(stringRequest);
//                Intent mail=new Intent(Intent.ACTION_SENDTO);
//                mail.setType("text/plain");
//                //mail.putExtra(Intent.EXTRA_EMAIL,new String[]{"ashubansal.ashishbansal@gmail.com"});
//                mail.setData(Uri.parse("mailto:ashubansal.ashishbansal@gmail.com"));
//                mail.putExtra(Intent.EXTRA_SUBJECT, email);
//                mail.putExtra(Intent.EXTRA_TEXT, message);
//                //mail.setType("message/rfc822");
//                mail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(mail);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent1=new Intent(InboxReplyMarketplace.this,inbox_market.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivityForResult(intent1, 0);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                },2000);

            }
        });
        listView = findViewById(R.id.listreply);
        jsonParser();
    }
    private void jsonParser()
    {
        String url1="http://serv.kesbokar.com.au/jil.0.1/v1/quotes-product/"+bundle.getInt("id") +"reply"+id+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject=response.getJSONObject("data");

                    //JSONObject dat = jsonObject.getJSONObject(i);
                    Log.i("JSON Help", jsonObject.toString());
                    id1=jsonObject.getInt("id");

                    user_id=jsonObject.getInt("sender_id");
                    subject1=jsonObject.getString("subject");
                    message1=jsonObject.getString("message");
//                        replyBy=jsonObject.getString("reply_by_name");
                    JSONArray replies=jsonObject.getJSONArray("replies");
                    for (int j=0;j<replies.length();j++)
                    {
                        JSONObject rdata=replies.getJSONObject(j);
                        replyMessage=rdata.getString("reply_message");
                        date1=rdata.getString("created_at");
                        JSONObject user=rdata.getJSONObject("user");
                        replyBy=user.getString("first_name");
                        get_for_replies.add(new inbox_reply_market(replyMessage,replyBy,date1,user_id,id1));
                    }
                    subject.setText(subject1);
                    message.setText(message1);
                    if (get_for_replies!=null) {
                        AdapterInboxReplyMarket adapterInboxReplyMarket = new AdapterInboxReplyMarket(InboxReplyMarketplace.this,InboxReplyMarketplace.this , get_for_replies);
                        listView.setAdapter(adapterInboxReplyMarket);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
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
