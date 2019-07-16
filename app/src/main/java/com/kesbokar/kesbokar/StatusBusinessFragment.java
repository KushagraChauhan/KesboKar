package com.kesbokar.kesbokar;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusBusinessFragment extends Fragment {

    RadioGroup rgStatus;
    Button btnBack, btnSubmit;
    String condition;
    ViewPager viewPager;
    private String loginId, loginPass, full_name, email, image, phone_no,created,updated,product_id,product_name,yellowpage_id;
    private int id,flag;

    TabLayout tabLayout;

    private String status;

    public StatusBusinessFragment(ViewPager myViewPager, TabLayout myTabLayout) {
        this.viewPager=viewPager;
        this.tabLayout=tabLayout;

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status_business, container, false);

        rgStatus = view.findViewById(R.id.rgStatus);
        btnBack = view.findViewById(R.id.btnBack);
        btnSubmit= view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                String url;

                url = "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/" + yellowpage_id;

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response", response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("name",edtProductTitle.getText().toString());
//                        params.put("product_condition",);
//                        params.put("product_section",);
//                        params.put("topcat_id",);
//                        params.put("parentcat_id",);
//                        params.put("category_id",);
//                        params.put("tags",);
//                        params.put("price",);
                        String user_id = "" + id;
                        params.put("user_id", user_id);
                        params.put("status", status);
                        params.put("api_token", "FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                queue.add(stringRequest);
                Intent intent = new Intent(getActivity(), LoginData.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbActive:condition = "rbActive";
                        break;

                    case R.id.rbDeactive:condition = "rbDeactive";
                }
            }
        });


        return view;
    }
    public void getData()
    {
        SharedPreferences loginData=getActivity().getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");
        SharedPreferences get_product_detail=getActivity().getSharedPreferences("product_detail",0);
        product_id =get_product_detail.getString("product_id","");
        product_name=get_product_detail.getString("product_name","");

  //     SharedPreferences get_business_detail = getActivity().getSharedPreferences("business_detail", 0);
         yellowpage_id = get_product_detail.getString("yellowpage_id","" );

    }


}
