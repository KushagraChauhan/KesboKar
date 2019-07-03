package com.kesbokar.kesbokar;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Help extends AppCompatActivity {
    String ip;
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button send=(Button)findViewById(R.id.send);
        getData();
        Context context;
        context=Help.this;
        WifiManager wifiManager=(WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        //=Formatter.formatIpAddress()
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i("response", "***** IP="+ ip);
                    }
                }
            }
        } catch (SocketException ex) {
            Log.i("error", ex.toString());
            ip="000.000.000.000";
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=((EditText)findViewById(R.id.email)).getText().toString();
                final String message=((EditText)findViewById(R.id.mess)).getText().toString();
                final String phone=((EditText)findViewById(R.id.PhoneNo)).getText().toString();
                final String name=((EditText)findViewById(R.id.Name)).getText().toString();
                RequestQueue queue= Volley.newRequestQueue(Help.this);
                //Toast.makeText(Help.this, "Ipaddress"+ip, Toast.LENGTH_SHORT).show();

                String url="http://serv.kesbokar.com.au/jil.0.1/v2/feedback";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Help.this, "Response"+"Your Query Has been Submitted", Toast.LENGTH_SHORT).show();
                        Log.i("Resposnse",response);

                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // errorLog.d("Error.Response", String.valueOf(error));
                                Toast.makeText(Help.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String >();
                        params.put("name", name);
                        params.put("email", email);
                        params.put("phone", phone);
                        params.put("message", message);
                        params.put("i6paddress",ip);
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");

                        return params;
                    }
                };
                RequestQueue requestQueue=Volley.newRequestQueue(Help.this);
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
                        Intent intent1=new Intent(Help.this,Navigation.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivityForResult(intent1, 0);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                },2000);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Help.this,Navigation.class);
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
