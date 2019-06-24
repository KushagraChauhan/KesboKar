package com.example.kesbokar;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;


public class BasicInfoBusinessFragment extends Fragment implements LocationListener {


    Button btnFirst, btnSecond, btnThird, btnVerify, btnState, btnSuburb, btnCountry, getLocationBtn, btnSave;
    EditText etCompany, etABN, etLicense, etWebsite, etQuote, etPhone, etEmail, etStreet, etLongitude, etLatitude;
    AutoCompleteTextView acTags;
    LocationManager locationManager;
    String provider;
    Location location;



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
        final String[] firstValueArray = {"API1", "API1", "API1"};
        final String[] secondValueArray;
        final String[] thirdValueArray;
        final String[] locationValueArray1;
        final String[] locationValueArray2;
        final String[] locationValueArray3;
        final String[] value = new String[3];
        final String[] state = {""};
        final String[] suburb = {""};
        final String[] country = {""};




        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(firstValueArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        value[0] = firstValueArray[item];
                        btnFirst.setText(value[0]);
                        btnSecond.setVisibility(View.VISIBLE);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        secondValueArray = new String[]{"API2", "API2", "API2"};


        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(secondValueArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        value[1] = secondValueArray[item];
                        btnSecond.setText(value[1]);
                        btnThird.setVisibility(View.VISIBLE);

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();



            }
        });

        thirdValueArray = new String[]{"API3", "API3", "API3"};


        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(thirdValueArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        value[2] = thirdValueArray[item];
                        btnThird.setText(value[2]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();



            }
        });


        locationValueArray1 = new String[]{"L1", "L1", "L1"};
        locationValueArray2 = new String[]{"L2", "L2", "L2"};
        locationValueArray3 = new String[]{"L3", "L3", "L3"};

        btnState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(locationValueArray1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        state[0] = locationValueArray1[item];
                        btnState.setText(state[0]);

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        btnSuburb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(locationValueArray2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        suburb[0] = locationValueArray2[item];
                        btnSuburb.setText(suburb[0]);

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        btnCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(locationValueArray3, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        country[0] = locationValueArray3[item];
                        btnCountry.setText(country[0]);

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

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

        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager =(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
                Criteria c=new Criteria();
                //if we pass false than
                //it will check first satellite location than Internet and than Sim Network
                provider=locationManager.getBestProvider(c, false);
                if ((ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                    location=locationManager.getLastKnownLocation(provider);
                }

                if(location!=null)
                {
                    double lng=location.getLongitude();
                    double lat=location.getLatitude();
                    etLatitude.setText(""+lat);
                    etLongitude.setText(""+lng);
                }
                else
                {
                    etLatitude.setText("No Provider");
                    etLongitude.setText("No Provider");
                }
            }
        });

        return view;
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}




