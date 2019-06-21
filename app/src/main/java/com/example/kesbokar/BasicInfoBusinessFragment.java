package com.example.kesbokar;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicInfoBusinessFragment extends Fragment {


    Button btnFirst, btnSecond, btnThird, btnVerify, btnState, btnSuburb, btnCountry, getLocationBtn, btnSave;
    EditText etCompany, etABN, etLicense, etWebsite, etQuote, etPhone, etEmail, etStreet, etLongitude, etLatitude;
    AutoCompleteTextView acTags;
    LocationManager locationManager;


    public BasicInfoBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_info_business, container, false);


        acTags = view.findViewById(R.id.acTags);
        etCompany = view.findViewById(R.id.etCompany);
        etABN = view.findViewById(R.id.etABN);
        etLicense = view.findViewById(R.id.etLicense);
        etWebsite = view.findViewById(R.id.etWebsite);
        etQuote = view.findViewById(R.id.etQuote);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        etStreet = view.findViewById(R.id.etStreet);
        etLongitude = view.findViewById(R.id.etLongitude);
        etLatitude = view.findViewById(R.id.etLatitude);
        btnFirst = view.findViewById(R.id.btnFirst);
        btnSecond = view.findViewById(R.id.btnSecond);
        btnThird = view.findViewById(R.id.btnThird);
        btnVerify = view.findViewById(R.id.btnVerify);
        btnState = view.findViewById(R.id.btnState);
        btnSuburb = view.findViewById(R.id.btnSuburb);
        btnCountry = view.findViewById(R.id.btnCountry);
        getLocationBtn = view.findViewById(R.id.btnDetect);
        btnSave = view.findViewById(R.id.btnSave);


        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnSecond.setVisibility(View.VISIBLE);

            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnThird.setVisibility(View.VISIBLE);

            }
        });

        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSuburb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}




