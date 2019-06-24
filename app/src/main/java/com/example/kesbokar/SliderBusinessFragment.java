package com.example.kesbokar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SliderBusinessFragment extends Fragment {

    Button btnChooseFiles, btnUpload, btnPrevious, btnSave;
    TextView tvChosen;
    ImageView ivImage;


    public SliderBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider_business, container, false);

        btnChooseFiles = view.findViewById(R.id.btnChooseFiles);
        btnUpload = view.findViewById(R.id.btnUpload);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnSave = view.findViewById(R.id.btnSave);
        tvChosen = view.findViewById(R.id.tvChosen);
        ivImage = view.findViewById(R.id.ivImage);

        return view;
    }

}
