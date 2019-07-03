package com.kesbokar.kesbokar;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailsFragment extends Fragment {

    private TextView tvEmail;
    private EditText etPhone, etStreet;
    private AutoCompleteTextView etState,etSuburb;
    private Button btnBack, btnSubmit;
    private String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    private int id,flag;

    private String querySub;
    private int stateid;
    private int subUrbID;
    private String subType;

    private String q, subV;

    private static final int LOADER_ID_BUSVAL = 10101;
    private ArrayList<StateAndSuburb> valsBus;
    private androidx.loader.app.LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>> businessSuburb;


    public ContactDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);

        tvEmail = view.findViewById(R.id.tvEmail);
        etPhone = view.findViewById(R.id.etPhone);
        etStreet = view.findViewById(R.id.etStreet);
        etState = view.findViewById(R.id.etState);
        etSuburb = view.findViewById(R.id.etSuburb);
        btnBack = view.findViewById(R.id.btnBack);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        valsBus = new ArrayList<>();

        querySub = "";

        q = subV = querySub = "au";


        getData();
        tvEmail.setText(email);


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


        etSuburb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                subV = stateAndSuburb.getValue();
                stateid = stateAndSuburb.getId();
                subType = stateAndSuburb.getType();
            }
        });

        etSuburb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                querySub = s.toString();
                getLoaderManager().initLoader(LOADER_ID_BUSVAL,null,businessSuburb);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        businessSuburb = new androidx.loader.app.LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>>() {
            @NonNull
            @Override
            public Loader<ArrayList<StateAndSuburb>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderBusSuburb loaderBusSuburb = new LoaderBusSuburb(getContext(), querySub, "http://serv.kesbokar.com.au/jil.0.1/v2/product/search/cities");
                return loaderBusSuburb;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<StateAndSuburb>> loader, ArrayList<StateAndSuburb> data) {
                if (data.size() !=0){
                    valsBus = data;
                    Log.i("Tag",valsBus +"");
                    ArrayAdapter<StateAndSuburb> adapter = new ArrayAdapter<StateAndSuburb>(getContext(), android.R.layout.simple_dropdown_item_1line, valsBus);
                    etState.setAdapter(adapter);
                    getLoaderManager().destroyLoader(LOADER_ID_BUSVAL);
                    etSuburb.setAdapter(adapter);
                    getLoaderManager().destroyLoader(LOADER_ID_BUSVAL);
                    etState.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etState.showDropDown();
                        }
                    });
                    etSuburb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etSuburb.showDropDown();
                        }
                    });
                    etState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                            stateid = stateAndSuburb.getId();
                        }
                    });
                    etSuburb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                            subUrbID = stateAndSuburb.getId();
                        }
                    });
                }

            }

            @Override
            public void onLoaderReset(@NonNull Loader<ArrayList<StateAndSuburb>> loader) {

            }
        };

        getLoaderManager().initLoader(LOADER_ID_BUSVAL,null, businessSuburb);
        return view;

    }


    public void getData(){
        SharedPreferences contactDetailsFragment = getContext().getSharedPreferences("data",0);
        flag = contactDetailsFragment.getInt("Flag",0);
        full_name=contactDetailsFragment.getString("Name","");
        email=contactDetailsFragment.getString("mail","");
        image=contactDetailsFragment.getString("image","");
        phone_no=contactDetailsFragment.getString("phone","");
        id=contactDetailsFragment.getInt("id",0);
        created=contactDetailsFragment.getString("create","");
        updated=contactDetailsFragment.getString("update","");

    }

}
