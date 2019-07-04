package com.kesbokar.kesbokar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {

    EditText etDescription;
    Button btnBack, btnSubmit;
    ViewPager viewPager;
    TabLayout tabLayout;


    public DescriptionFragment(ViewPager viewPager, TabLayout tabLayout)
    {
        this.viewPager=viewPager;
        this.tabLayout=tabLayout;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, new AwesomeFragment()).commit();
        etDescription = view.findViewById(R.id.etDescription);
        btnBack = view.findViewById(R.id.btnBack);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
