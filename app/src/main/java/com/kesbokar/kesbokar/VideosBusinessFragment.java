package com.kesbokar.kesbokar;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class VideosBusinessFragment extends Fragment {

    Button btnSave, btnPrevious, btnSubmitCode;
    EditText etTitle, etCode;
    RadioGroup rgDefault;
    String condition;
    private String loginId, loginPass, full_name, email, image, phone_no,created,updated,product_id,product_name, yellowpage_id;
    private int id,flag;

    private String name, registration_no, license_no, website, category_id, phone, address, description, latitude, longitude, email1,
            quote_message, short_description,yellowpage_id1,video_title,video_code;
    int edit1=0;
    ViewPager viewPager;
    TabLayout tabLayout;



    public VideosBusinessFragment(ViewPager myViewPager, TabLayout myTabLayout) {
        // Required empty public constructor
        viewPager=myViewPager;
        tabLayout=myTabLayout;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos_business, container, false);

        btnSave = view.findViewById(R.id.btnSave);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnSubmitCode = view.findViewById(R.id.btnSubmitCode);
        etTitle = view.findViewById(R.id.etTitle);
        etCode = view.findViewById(R.id.etCode);
        rgDefault = view.findViewById(R.id.rgDefault);
        getData();
        if (edit1==1)
        {
            etTitle.setText(video_title);
            etCode.setText(video_code);
        }

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSubmitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                RequestQueue queue= Volley.newRequestQueue(getActivity());
                String url;
                if (edit1==1)
                {
                    url="http:/serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id1+"/video";
                }
                else {
                    url = "http:/serv.kesbokar.com.au/jil.0.1/v1/yellowpage/" + yellowpage_id + "/video";
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
                        params.put("title",etTitle.getText().toString());
                        params.put("video_code", etCode.getText().toString());
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                queue.add(stringRequest);
                int item=viewPager.getCurrentItem();
                View tab=tabLayout.getTabAt(item+1).view;
                tab.setEnabled(true);
                viewPager.setCurrentItem(item+1);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                RequestQueue queue= Volley.newRequestQueue(getActivity());
                String url;

                url="http:/serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id+"/video";

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
                        params.put("title",etTitle.getText().toString());
                        params.put("video_code", etCode.getText().toString());
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                 queue.add(stringRequest);
                int item=viewPager.getCurrentItem();
                View tab=tabLayout.getTabAt(item+1).view;
                tab.setEnabled(true);
                viewPager.setCurrentItem(item+1);
            }
        });

        rgDefault.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.rbSell:condition="rbYes";
                        break;

                    case R.id.rbRent:condition="rbNo";
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
//        SharedPreferences get_business_detail = getActivity().getSharedPreferences("business_detail", 0);
        yellowpage_id = get_product_detail.getString("yellowpage_id","" );
        SharedPreferences basicInfoBusiness = getActivity().getSharedPreferences("business_edit", 0);
        edit1 = basicInfoBusiness.getInt("edit", 0);
        if (edit1 == 1) {

            name = basicInfoBusiness.getString("name", "");
            registration_no = basicInfoBusiness.getString("registration_no", "");
            license_no = basicInfoBusiness.getString("license_no", "");
            website = basicInfoBusiness.getString("website", "");
            category_id = basicInfoBusiness.getString("category_id", "");
            phone = basicInfoBusiness.getString("phone", "");
            address = basicInfoBusiness.getString("address", "");
            description = basicInfoBusiness.getString("description", "");
            latitude = basicInfoBusiness.getString("latitude", "");
            longitude = basicInfoBusiness.getString("longitude", "");
            email1 = basicInfoBusiness.getString("email", "");
            quote_message = basicInfoBusiness.getString("quote_message", "");
            short_description = basicInfoBusiness.getString("short_desc", "");
            yellowpage_id1=basicInfoBusiness.getString("yellowpage_id","");
            video_code=basicInfoBusiness.getString("video_code","");
            video_title=basicInfoBusiness.getString("video_title","");


        }

    }

}
