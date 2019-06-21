package com.example.kesbokar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionBusinessFragment extends Fragment {

    ImageView ivImage;
    TextView tvImageChosen;
    EditText etShortDescription, etDescription;
    Button btnChooseFile, btnPrevious, btnSave;


    public DescriptionBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description_business, container, false);

        ivImage = view.findViewById(R.id.ivImage);
        tvImageChosen = view.findViewById(R.id.tvImageChosen);
        etShortDescription = view.findViewById(R.id.etShortDescription);
        etDescription = view.findViewById(R.id.etDescription);
        btnChooseFile = view.findViewById(R.id.btnChooseFile);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnSave = view.findViewById(R.id.btnSave);


        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
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
