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
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicInfoFragment extends Fragment {

    private Button btnCatFirst, btnCatSecond, btnCatThird;
    String condition1, condition2;
    RadioGroup rgProductCondition, rgProductSelection;

    EditText edtProductTitle;
    public BasicInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_info, container, false);
        rgProductCondition = view.findViewById(R.id.rgProductCondition);
        rgProductSelection = view.findViewById(R.id.rgProductSelection);
        // Categories
        final String[] value = new String[3];
        btnCatFirst =(Button) view.findViewById(R.id.btnCatFirst);
        btnCatSecond =(Button) view.findViewById(R.id.btnCatSecond);
        btnCatThird =(Button) view.findViewById(R.id.btnCatThird);
        final String[] firstValueArray = {"API1", "API1", "API1"};
        final String[] secondValueArray;
        final String[] thirdValueArray;

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



        rgProductCondition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbNew:condition1="New";
                        break;

                    case R.id.rbUsed:condition1="used";
                }
            }
        });


        rgProductSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.rbSell:condition2="rbSell";
                        break;

                    case R.id.rbRent:condition2="rbRent";
                }
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
