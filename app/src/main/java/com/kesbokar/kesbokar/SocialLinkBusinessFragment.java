package com.kesbokar.kesbokar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SocialLinkBusinessFragment extends Fragment {

    EditText etFacebook, etTwitter, etLinkedIn, etGoogle, etInstagram, etYouTube, etTelegram;
    Button btnPrevious, btnSave;

    public SocialLinkBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_link_business, container, false);

        etFacebook = view.findViewById(R.id.etFacebook);
        etTwitter = view.findViewById(R.id.etTwitter);
        etLinkedIn = view.findViewById(R.id.etLinkedIn);
        etGoogle = view.findViewById(R.id.etGoogle);
        etInstagram = view.findViewById(R.id.etInstagram);
        etYouTube = view.findViewById(R.id.etYouTube);
        etTelegram = view.findViewById(R.id.etTelegram);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnSave = view.findViewById(R.id.btnSave);

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

        return view;
    }

}
