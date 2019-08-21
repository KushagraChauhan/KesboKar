package com.kesbokar.kesbokar;


import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static final int PERMISSION_REQUEST_CODE = 1;
    Bitmap[] bitmap;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    int count;
    JSONArray json_name_array;
    JSONObject json_name;
    String pic_name;

    private int id,flag;

    private int edit1=0;

    GridView gvGallery;
    private GalleryAdapter galleryAdapter;

    private String loginId, loginPass, full_name, email, image, phone_no,created,updated,product_id,product_name, yellowpage_id;

    private String name, registration_no, license_no, website, category_id, phone, address, description, latitude, longitude, email1,yellowpage_id1,
            quote_message, short_description, name_title, new_name;

    ViewPager viewPager;
    TabLayout tabLayout;


    public SliderBusinessFragment(ViewPager myViewPager, TabLayout myTabLayout) {
        // Required empty public constructor
        this.viewPager=myViewPager;
        this.tabLayout=myTabLayout;
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

                showFileChooser();

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploaduserimage();
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

              getData();
              uploaduserimage();

            }
        });

        return view;
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }



    public void uploaduserimage(){


        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String url;

                Log.i("Myresponse",""+response);
                Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                try {
                    json_name=new JSONObject(response);
                    pic_name=json_name.getString("image");
                    Log.i("image_name",pic_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (edit1==1){
                    url = "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id1+"/slider";
                }
                else {
                    url = "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id+"/slider";
                }
                StringRequest stringRequest_name = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("Name Response",""+response);
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
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param = new HashMap<>();
                        param.put("image",pic_name);
                        param.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return param;
                    }
                };

                requestQueue.add(stringRequest_name);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Mysmart",""+error);

                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                String[] images=new String[count];
                JSONArray jsonArray=new JSONArray();
                JSONObject[] jsonObjects = new JSONObject[count];
                Log.i("count",""+count);
                for (int i=0; i<count; i++) {
                    images[i] = getStringImage(bitmap[i]);
                    Log.i("image",""+images[i]);
                    try {
                        jsonObjects[i]=new JSONObject();
                        jsonObjects[i].put("name","image"+i+".png");
                        jsonObjects[i].put("image",images[i]);
                        jsonArray.put(jsonObjects[i]);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("JSON Array",jsonArray.toString());
                if (edit1==1)
                {
                    param.put("filename",name);

                }
                else {
                    param.put("filename", new_name);
                }
                param.put("images",jsonArray.toString());
                param.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return param;
            }
        };

        requestQueue.add(stringRequest);
        int item=viewPager.getCurrentItem();
        View tab=tabLayout.getTabAt(item+1).view;
        tab.setEnabled(true);
        viewPager.setCurrentItem(item+1);


    }


        public String getStringImage(Bitmap bitmap){
            Log.i("function bit",""+bitmap);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            byte [] b=baos.toByteArray();
            return Base64.encodeToString(b, Base64.DEFAULT);
        }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {

            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                String loc=filePathColumn[0];
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getActivity().getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getContext().getApplicationContext(),mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        bitmap=new Bitmap[mClipData.getItemCount()];
                        count=mClipData.getItemCount();
                        Uri filePath=data.getData();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {


                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            bitmap[i] = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                            Log.i("Bitmap",bitmap[i].toString());
                            Toast.makeText(getContext(), ""+bitmap, Toast.LENGTH_SHORT).show();
                            //Setting the Bitmap to ImageView
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContext().getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row

                            cursor.moveToFirst();


                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String fileName = cursor.toString();
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getContext().getApplicationContext(),mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(getContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
            Log.i("error",e.toString());
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getData() {
        SharedPreferences loginData = getActivity().getSharedPreferences("data", 0);
        flag = loginData.getInt("Flag", 0);
        full_name = loginData.getString("Name", "");
        email = loginData.getString("mail", "");
        image = loginData.getString("image", "");
        phone_no = loginData.getString("phone", "");
        id = loginData.getInt("id", 0);
        created = loginData.getString("create", "");
        updated = loginData.getString("update", "");
        SharedPreferences get_product_detail = getActivity().getSharedPreferences("product_detail", 0);
        product_id = get_product_detail.getString("product_id", "");
//        product_name=get_product_detail.getString("product_name","");
//        SharedPreferences get_business_detail = getActivity().getSharedPreferences("business_detail", 0);
        yellowpage_id = get_product_detail.getString("yellowpage_id", "");
        new_name = get_product_detail.getString("new_name", "");
        SharedPreferences basicInfoBusiness = getActivity().getSharedPreferences("business_edit", 0);
        edit1 = basicInfoBusiness.getInt("edit", 0);

        if (edit1 == 1) {

            name = basicInfoBusiness.getString("name", "");
            registration_no = basicInfoBusiness.getString("registration_no", "");
            license_no = basicInfoBusiness.getString("license_no", "");
            website = basicInfoBusiness.getString("website", "");
            category_id = basicInfoBusiness.getString("category_id", "");
            phone = basicInfoBusiness.getString("phone", "");
            address = basicInfoBusiness.getString("address", "");
            description = basicInfoBusiness.getString("description", "");
            latitude = basicInfoBusiness.getString("latitude", "");
            longitude = basicInfoBusiness.getString("longitude", "");
            email1 = basicInfoBusiness.getString("email", "");
            quote_message = basicInfoBusiness.getString("quote_message", "");
            short_description = basicInfoBusiness.getString("short_desc", "");
            yellowpage_id1=basicInfoBusiness.getString("yellowpage_id","");

        }
    }

}
