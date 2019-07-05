package com.kesbokar.kesbokar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesBusinessFragment extends Fragment {


    public ServicesBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services_business, container, false);

        int totalSpace = 0;
        FrameLayout fragment_container = (FrameLayout) view.findViewById(R.id.fragmentServicesBusiness);

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

}
