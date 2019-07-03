package com.kesbokar.kesbokar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideosBusinessFragment extends Fragment {

    Button btnSave, btnPrevious, btnSubmitCode;
    EditText etTitle, etCode;
    RadioGroup rgDefault;
    String condition;

    public VideosBusinessFragment() {
        // Required empty public constructor
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSubmitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

}
