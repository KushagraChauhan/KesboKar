package com.kesbokar.kesbokar;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttributeFragment extends Fragment {

    EditText etSpecification, etAdditional;
    Button btnBack, btnSubmit;
    ViewPager viewPager;
    TabLayout tabLayout;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated,product_id,product_name,attribute_info;
    int id,flag,entry_state;


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

        int totalSpace = 0;
        FrameLayout fragment_container = (FrameLayout) view.findViewById(R.id.fragmentAttribute);

        int n = 5;
        for (int i=0;i<n;i++) {

            TextView tv = new TextView(getContext());
            tv.setText("TextView " + i);
            tv.setId(i);
            tv.setPadding(0,10,0,0);
            FrameLayout.LayoutParams textViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            textViewParams.setMargins(100 , 100 * i, 20, 20);
            fragment_container.addView(tv, textViewParams);

            EditText et = new EditText(getContext());
            et.setHint("EditText " + i);
            et.setId(i + n);
            et.setWidth(500);
            FrameLayout.LayoutParams editTextParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            editTextParams.setMargins(500, 90 * i, 20, 20);
            fragment_container.addView(et, editTextParams);

            totalSpace = totalSpace + (40 * (i+1));
        }

        Button btnPrevious = new Button(getContext());
        btnPrevious.setText("Previous");
        btnPrevious.setId((2 * n) + 1);
        btnPrevious.setPadding(80,0,80,0);
        FrameLayout.LayoutParams btnPreviousParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        btnPreviousParams.setMargins(100,totalSpace,100,20);

        fragment_container.addView(btnPrevious, btnPreviousParams);

        Button btnSave = new Button(getContext());
        btnSave.setText("Save");
        btnSave.setId((2 * n) + 2);
        FrameLayout.LayoutParams btnSaveParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        btnSaveParams.setMargins(500,totalSpace,100,20);

        fragment_container.addView(btnSave, btnSaveParams);



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
    }

}
