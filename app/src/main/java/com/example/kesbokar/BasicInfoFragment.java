package com.example.kesbokar;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicInfoFragment extends Fragment {

    private Button btnCatFirst, btnCatSecond, btnCatThird;

    EditText edtProductTitle;
    public BasicInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_info, container, false);


        // Categories
        final String[] value = new String[3];
        btnCatFirst =(Button) getView().findViewById(R.id.btnCatFirst);
        btnCatSecond =(Button) getView().findViewById(R.id.btnCatSecond);
        btnCatThird =(Button) getView().findViewById(R.id.btnCatThird);
        final String[] firstValueArray = {"API1", "API1", "API1"};
        final String[] secondValueArray;
        final String[] thirdValueArray;
        btnCatFirst.setClickable(true);
        btnCatSecond.setClickable(true);
        btnCatThird.setClickable(true);
        btnCatSecond.setVisibility(View.INVISIBLE);
        btnCatThird.setVisibility(View.INVISIBLE);
        btnCatFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(firstValueArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        value[0] = firstValueArray[item];
                        btnCatFirst.setText(value[0]);
                        btnCatSecond.setVisibility(View.VISIBLE);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });


        secondValueArray = new String[]{"API2", "API2", "API2"};



        btnCatSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(secondValueArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        value[1] = secondValueArray[item];
                        btnCatSecond.setText(value[1]);
                        btnCatThird.setVisibility(View.VISIBLE);

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        thirdValueArray = new String[]{"API3", "API3", "API3"};



        btnCatThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(thirdValueArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        value[2] = thirdValueArray[item];
                        btnCatThird.setText(value[2]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });




//        edtProductTitle = view.findViewById(R.id.etProductTitle);
//        String data = "fail";
//        if(savedInstanceState!=null)
//            data = getArguments().getString("EDITTEXT_VALUE");
//        Log.i("DATA KI MA KI CHOOT", data);
//        edtProductTitle.setText(data);
        return view;
    }

}
