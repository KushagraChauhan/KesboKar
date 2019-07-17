package com.kesbokar.kesbokar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    EditText new_password,confirm_password;
    Button change_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getData();
        new_password=findViewById(R.id.new_password);
        confirm_password=findViewById(R.id.confirm_password);
        change_password_confirm=findViewById(R.id.change_password_confirm);
        change_password_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new_password.getText().toString().equals(confirm_password.getText().toString())) {
                    String url = "http://serv.kesbokar.com.au/jil.0.1/v1/users/" + id;
                    RequestQueue requestQueue = Volley.newRequestQueue(ChangePassword.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("Response",response);
                            Toast.makeText(ChangePassword.this, "Your Password Has Been Changed", Toast.LENGTH_SHORT).show();
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("Error",error.toString());
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("id", "" + id);
                            params.put("password", new_password.getText().toString());
                            params.put("api_token", "FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                    Intent intent=new Intent(ChangePassword.this,Profile.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(ChangePassword.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }

        });

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
