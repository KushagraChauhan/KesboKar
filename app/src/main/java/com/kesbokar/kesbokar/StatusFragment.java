package com.kesbokar.kesbokar;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
public class StatusFragment extends Fragment {

    RadioGroup rgStatus;
    Button btnBack, btnSubmit;
    String condition;
    ViewPager viewPager;
    private String loginId, loginPass, full_name, email, image, phone_no,created,updated,product_id,product_name;
    private int id,flag;
    RadioButton rbactive,rbdeactive;
    String make_id,model_id1,year1,variant_id1,vehicle_id,colour,airconditioning,registered,registration_state,registration_number,registration_expiry,name_title,product_condition,product_section,category_id1,price1,phone1,address1,description1,status1,pro_id,model_name,variant_name;
    int edit1;

    TabLayout tabLayout;

    private String status;

    public StatusFragment(ViewPager viewPager, TabLayout tabLayout)
    {
        this.viewPager=viewPager;
        this.tabLayout=tabLayout;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_status, container, false);
        getData();
        rgStatus = view.findViewById(R.id.rgStatus);
        btnBack = view.findViewById(R.id.btnBack);
        btnSubmit= view.findViewById(R.id.btnSubmit);
        rbactive=rgStatus.findViewById(R.id.rbActive);
        rbdeactive=rgStatus.findViewById(R.id.rbDeactive);
        if (edit1==1)
        {
            if (status1.equals("1"))
            {
                rbactive.toggle();
                status="1";

            }
            else
            {
                rbdeactive.toggle();
                status="0";
            }
        }



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                RequestQueue queue= Volley.newRequestQueue(getActivity());
                String url;
                if (edit1==1)
                {
                    url="http://serv.kesbokar.com.au/jil.0.1/v1/product/"+pro_id;
                }
                else {
                    url = "http://serv.kesbokar.com.au/jil.0.1/v1/product/" + product_id;
                }

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response",response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error",error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String >();
//                        params.put("name",edtProductTitle.getText().toString());
//                        params.put("product_condition",);
//                        params.put("product_section",);
//                        params.put("topcat_id",);
//                        params.put("parentcat_id",);
//                        params.put("category_id",);
//                        params.put("tags",);
//                        params.put("price",);
                        String user_id=""+id;
                        params.put("user_id",user_id);
                        params.put("status",status);
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                queue.add(stringRequest);
                Intent intent=new Intent(getActivity(),LoginData.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbActive:condition = "rbActive";
                    status = "1";
                    break;

                    case R.id.rbDeactive:condition = "rbDeactive";
                    status = "0";
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
        SharedPreferences business_edit=getActivity().getSharedPreferences("market_edit",0);
        edit1=business_edit.getInt("edit",0);
        name_title=business_edit.getString("name","");
        product_condition=business_edit.getString("product_condition","");
        product_section=business_edit.getString("product_section","");
        category_id1=business_edit.getString("category_id","");
        price1=business_edit.getString("price","");
        phone1=business_edit.getString("phone","");
        address1=business_edit.getString("address","");
        description1=business_edit.getString("description","");
        status1=business_edit.getString("status","");
        pro_id=business_edit.getString("product_id","");


    }

}
