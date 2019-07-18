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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    EditText etFirstName,etLastName,etMobile,etPhone,etAddress;
    Button btnUpdate;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getData();
        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etMobile=findViewById(R.id.etMobile);
        etPhone=findViewById(R.id.etPhone);
        etAddress=findViewById(R.id.etAddress);
        btnUpdate=findViewById(R.id.btnUpdate);
        final String [] parts=full_name.split(" ");
        etFirstName.setText(parts[0]);
        etLastName.setText(parts[1]);
        etPhone.setText(phone_no);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("first_name",etFirstName.getText().toString());
                    jsonObject.put("last_name",etLastName.getText().toString());
                    jsonObject.put("mobile",etMobile.getText().toString());
                    jsonObject.put("phone",etPhone.getText().toString());
                    jsonObject.put("address",etAddress.getText().toString());
                    jsonObject.put("image","avatar.png");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url = "http://serv.kesbokar.com.au/jil.0.1/v1/profile/" + id;
                RequestQueue requestQueue = Volley.newRequestQueue(EditProfileActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response",response);
                        Toast.makeText(EditProfileActivity.this, "Your Profile Has Been Updated", Toast.LENGTH_SHORT).show();
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
                        params.put("data",jsonObject.toString());
                        params.put("api_token", "FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;

                    }
                };
                requestQueue.add(stringRequest);
                SharedPreferences loginData= getSharedPreferences("data",0);
                SharedPreferences.Editor editor=loginData.edit();
                editor.putString("Name",etFirstName.getText().toString()+" "+etLastName.getText().toString());
                if (etPhone.getText().toString().length()>0)
                {
                    editor.putString("phone",etPhone.getText().toString());
                }
                editor.apply();
                Intent intent=new Intent(EditProfileActivity.this,Profile.class);
                startActivity(intent);
                finish();
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
