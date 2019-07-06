package com.kesbokar.kesbokar;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttributeFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated,product_id,product_name,attribute_info,attribute_id,attribute="";
    int id,flag,entry_state;
    Button btnRefresh;


    public AttributeFragment(ViewPager viewPager, TabLayout tabLayout)
    {
        this.viewPager=viewPager;
        this.tabLayout=tabLayout;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attribute, container, false);


        final FrameLayout fragment_container = (FrameLayout) view.findViewById(R.id.fragmentAttribute);
        final Button btnRefresh = view.findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalSpace = 0;
                getData();
                int count =0;
                String temp = "",id="";
                int pos1=0, pos2=0, pos3=0;
                btnRefresh.setVisibility(View.INVISIBLE);
                for(int k=0;k<attribute_info.length();k++)
                {
                    if(attribute_info.charAt(k)==',')
                    {
                        count++;
                    }
                }
                final String[] resultId = new String[count];
                final String[] result = new String[count];
                for (int i=0;i<count;i++) {
                    temp = "";
                    id="";


                    for(int n=pos1;n<attribute_info.length();n++)
                    {
                        pos1++;
                        if(attribute_info.charAt(n)==':')
                        {
                            break;
                        }
                    }
                    for(int p=pos2;p<attribute_info.length();p++)
                    {
                        pos2++;
                        if(attribute_info.charAt(p)=='?')
                        {
                            break;
                        }
                    }

                    for(int r=pos3;r<pos1-1;r++)
                    {
                        id = id + attribute_info.charAt(r);
                    }

                    for(int p=pos3;p<attribute_info.length();p++)
                    {
                        pos3++;
                        if(attribute_info.charAt(p)==',')
                        {
                            break;
                        }
                    }



                    for (int l=pos1;l<pos2-1;l++)
                    {
                        temp = temp + attribute_info.charAt(l);
                    }



                    resultId[i] = id;


                    TextView tv = new TextView(getContext());
                    tv.setText("" + temp);
                    //tv.setId(i);
                    tv.setPadding(0,10,0,0);
                    FrameLayout.LayoutParams textViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                    textViewParams.setMargins(100 , 100 * i, 20, 20);
                    fragment_container.addView(tv, textViewParams);

                    EditText et = new EditText(getContext());
                    et.setHint("Enter ...");
                    et.setId(i);
                    et.setWidth(500);
                    FrameLayout.LayoutParams editTextParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                    editTextParams.setMargins(500, 90 * i, 20, 20);
                    fragment_container.addView(et, editTextParams);
                    final int finalI = i;
                    et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {


                            result[finalI] = String.valueOf(s);

                        }
                    });

                    totalSpace = totalSpace + (90*(i+1));
                }

                Button btnPrevious = new Button(getContext());
                btnPrevious.setText("Previous");
                btnPrevious.setId((2 * count) + 1);
                btnPrevious.setBackgroundColor(Color.parseColor("#008577"));
                btnPrevious.setTextColor(Color.parseColor("#FFFFFF"));
                btnPrevious.setTypeface(btnPrevious.getTypeface(), Typeface.BOLD);
                btnPrevious.setPadding(80,0,80,0);
                FrameLayout.LayoutParams btnPreviousParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                btnPreviousParams.setMargins(200,totalSpace,100,20);

                fragment_container.addView(btnPrevious, btnPreviousParams);

                Button btnSave = new Button(getContext());
                btnSave.setText("Save");
                btnSave.setId((2 * count) + 2);
                btnSave.setBackgroundColor(Color.parseColor("#008577"));
                btnSave.setTextColor(Color.parseColor("#FFFFFF"));
                btnSave.setTypeface(btnSave.getTypeface(), Typeface.BOLD);
                FrameLayout.LayoutParams btnSaveParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                btnSaveParams.setMargins(600,totalSpace,100,20);

                final int finalCount = count;
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final JSONArray jsonArray =new JSONArray();

                        for (int i = 0; i< finalCount; i++)
                        {


                            JSONObject jsonObject=new JSONObject();
                            try {
                                jsonObject.put("product_id",product_id);
                                jsonObject.put("attribute_id",resultId[i]);
                                jsonObject.put("attribute_value",result[i]);
                                jsonArray.put(i,jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            attribute=attribute+resultId[i]+",";


                            Toast.makeText(getContext(),resultId[i],Toast.LENGTH_SHORT).show();

                        }
                        RequestQueue queue= Volley.newRequestQueue(getActivity());
                        String url;

                        url="http://serv.kesbokar.com.au/jil.0.1/v1/product/"+product_id+"/attributes";

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
                                params.put("product_id",product_id);
                                params.put("data",jsonArray.toString());
                                Log.i("jsonarray",jsonArray.toString());
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

                fragment_container.addView(btnSave, btnSaveParams);

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
        SharedPreferences entry=getActivity().getSharedPreferences("entry_state",0);
        entry_state =entry.getInt("entry_state1",0);
        SharedPreferences attribute=getActivity().getSharedPreferences("attributes",0);
        attribute_info = attribute.getString("attribute_info","");
        attribute_id = attribute.getString("attribute_id","");

    }

}
