package com.kesbokar.kesbokar;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
public class SocialLinkBusinessFragment extends Fragment {

    EditText etFacebook, etTwitter, etLinkedIn, etGoogle, etInstagram, etYouTube, etTelegram;
    Button btnPrevious, btnSave;
    private String loginId, loginPass, full_name, email, image, phone_no,created,updated,product_id,product_name, yellowpage_id;
    private int id,flag;

    ViewPager viewPager;
    TabLayout tabLayout;


    public SocialLinkBusinessFragment(ViewPager myViewPager, TabLayout myTabLayout) {
        this.viewPager=viewPager;
        this.tabLayout=tabLayout;

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_link_business, container, false);

        etFacebook = view.findViewById(R.id.etFacebook);
        etTwitter = view.findViewById(R.id.etTwitter);
        etLinkedIn = view.findViewById(R.id.etLinkedIn);
        etGoogle = view.findViewById(R.id.etGoogle);
        etInstagram = view.findViewById(R.id.etInstagram);
        etYouTube = view.findViewById(R.id.etYouTube);
        etTelegram = view.findViewById(R.id.etTelegram);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getData();
                    RequestQueue queue= Volley.newRequestQueue(getActivity());
                    String url;

                    url="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id+"/sociallinks";

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
                            params.put("facebook",etFacebook.getText().toString());
                            params.put("twitter", etTwitter.getText().toString());
                            params.put("linkedin",etLinkedIn.getText().toString());
                            params.put("googleplus", etGoogle.getText().toString());
                            params.put("instagram",etInstagram.getText().toString());
                            params.put("youtube", etYouTube.getText().toString());
                            params.put("telegram", etTelegram.getText().toString());

                            params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                            return params;
                        }
                    };
                    queue.add(stringRequest);
//                    int item=viewPager.getCurrentItem();
//                    View tab=tabLayout.getTabAt(item+1).view;
//                    tab.setEnabled(true);
//                    viewPager.setCurrentItem(item+1);
                }



        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    }


}
