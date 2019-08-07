package com.kesbokar.kesbokar;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SliderBusinessFragment extends Fragment {

    String myurl = "https://www.kesbokar.com.au/jil.0.1/api/v1/yellowpage/slider/upload";

    Button btnChooseFiles, btnUpload, btnPrevious, btnSave;
    TextView tvChosen;
    ImageView ivImage;

    GridView gvGallery;
    private GalleryAdapter galleryAdapter;


    public SliderBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider_business, container, false);

        getData();

        btnChooseFiles = view.findViewById(R.id.btnChooseFiles);
        btnUpload = view.findViewById(R.id.btnUpload);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnSave = view.findViewById(R.id.btnSave);
        tvChosen = view.findViewById(R.id.tvChosen);

        gvGallery = view.findViewById(R.id.gv);


        btnChooseFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    startGallery();
                }
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadUserImage();

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


    private void uploadUserImage() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Myresponse",""+response);
                Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Mysmart",""+error);

                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String>getParams()throws AuthFailureError{
                Map<String,String> param = new HashMap<>();
                String image = new String();
                JSONArray jsonArray = new JSONArray();

                JSONObject jsonObject = new JSONObject();


                return param;
            }
        };

        requestQueue.add(stringRequest);
    }


    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == 1000){
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ivImage.setImageBitmap(bitmapImage);
                btnChooseFiles.setText("Selected");
                tvChosen.setText("");
            }
        }

    }

    private void getData() {
    }

}
