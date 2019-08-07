package com.kesbokar.kesbokar;


import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class DescriptionBusinessFragment extends Fragment {

    String myurl = "https://www.kesbokar.com.au/jil.0.1/api/v1/yellowpage/image/upload";

    private static final int PERMISSION_REQUEST_CODE = 1;
    Bitmap[] bitmap;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    int count;
    Bitmap bitmapImage;
    ImageView ivImage;
    TextView tvImageChosen;
    String response1="";
    EditText etShortDescription, etDescription;
    Button btnChoose, btnPrevious, btnSave, btnUpload;
    private String loginId, loginPass, full_name, email, image, phone_no,created,updated,product_id,product_name, yellowpage_id;
    private int id,flag;
    private int edit1=0;

    private String name, registration_no, license_no, website, category_id, phone, address, description, latitude, longitude, email1,yellowpage_id1,
            quote_message, short_description, new_name;

    ViewPager viewPager;
    TabLayout tabLayout;

    public DescriptionBusinessFragment(ViewPager myViewPager, TabLayout myTabLayout) {
        this.viewPager=myViewPager;
        this.tabLayout=myTabLayout;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description_business, container, false);
        getData();

        ivImage = view.findViewById(R.id.ivImage);
        etShortDescription = view.findViewById(R.id.etShortDescription);
        etDescription = view.findViewById(R.id.etDescription);
        btnChoose = view.findViewById(R.id.btnChoose);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnSave = view.findViewById(R.id.btnSave);
        btnUpload = view.findViewById(R.id.btnUpload);

        if (edit1==1)
        {
            etDescription.setText(description);
            etShortDescription.setText(short_description);
        }

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploaduserimage();
            }
        });


        btnChoose.setOnClickListener(new View.OnClickListener() {
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
                    showFileChooser();
                }

            }
        });

        if (Build.VERSION.SDK_INT >= 23){
            if (checkPermission()){

            }
            else {
                requestPermissions();
            }
        }


//        btnPrevious.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                RequestQueue queue= Volley.newRequestQueue(getActivity());
                String url;
                if (edit1==1)
                {
                    url="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id1;
                }
                else {
                    url = "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/" + yellowpage_id;
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response",response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error",error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String >();
//                        params.put("name",edtProductTitle.getText().toString());
//                        params.put("product_condition",);
//                        params.put("product_section",);
//                        params.put("topcat_id",);
//                        params.put("parentcat_id",);
//                        params.put("category_id",);
//                        params.put("tags",);
//                        params.put("price",);
                        String user_id=""+id;
                        params.put("user_id",user_id);
                        params.put("image",response1);
                        params.put("description",etDescription.getText().toString());
                        params.put("synopsis", etShortDescription.getText().toString());
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                queue.add(stringRequest);
                int item=viewPager.getCurrentItem();
                View tab=tabLayout.getTabAt(item+1).view;
                tab.setEnabled(true);
                viewPager.setCurrentItem(item+1);
            }
        });

        return view;
    }


    public void uploaduserimage(){


        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Myresponse",""+response);
                Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    response1 = jsonObject.getString("image");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error",""+error);

                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                if (edit1==1)
                {
                    param.put("filename",name+".png");
                }
                else {
                    param.put("filename", new_name+".png");
                }
                imageEncoded = getStringImage(bitmapImage);
                Log.i("imageEncoded",imageEncoded);
                param.put("image",imageEncoded);
                param.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return param;
            }
        };

        requestQueue.add(stringRequest);

    }

    public String getStringImage(Bitmap bitmap){
        Log.i("function bit",""+bitmap);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,10, baos);
        Bitmap.createScaledBitmap(bitmap, 350, 200, false);
        byte [] b=baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }


    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), " Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "Permission Granted Successfully! ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Permission Denied 🙁 ", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void showFileChooser() {
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
                bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ivImage.setImageBitmap(bitmapImage);

            }
        }

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


