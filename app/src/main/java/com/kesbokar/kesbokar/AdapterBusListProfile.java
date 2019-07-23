package com.kesbokar.kesbokar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterBusListProfile extends BaseAdapter {
    ArrayList<BusinessProfileList> businessProfileLists;
    LayoutInflater layoutInflater;
    TextView txtSno,txtTitle,txtAbn,txtPhone,txtStatus;
    Button adpBtnDel,adapBtnEdt;
    Context context;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    int pos;
    Activity activity;

    String id1;
    public AdapterBusListProfile(Context context, ArrayList<BusinessProfileList> businessProfileLists, Activity activity){
        this.context = context;
        this.activity=activity;
        this.businessProfileLists = businessProfileLists;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return businessProfileLists.size();
    }

    @Override
    public Object getItem(int i) {
        return businessProfileLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.adapter_layout,null);
        txtSno = view.findViewById(R.id.adapTxtSno);
        txtTitle = view.findViewById(R.id.adapTxtTitle);
        txtAbn = view.findViewById(R.id.adapTxtABN);
        txtPhone = view.findViewById(R.id.adapTxtPhone);
        txtStatus = view.findViewById(R.id.adapTxtStatus);
        adpBtnDel=view.findViewById(R.id.adapBtnDel);
        adapBtnEdt=view.findViewById(R.id.adapBtnEdt);
        txtSno.setText(businessProfileLists.get(i).getTxtSno());
        txtTitle.setText(businessProfileLists.get(i).getTxtTitle());
        txtPhone.setText(businessProfileLists.get(i).getTxtPhone());
        txtAbn.setText(businessProfileLists.get(i).getTxtAbn());
        int status = businessProfileLists.get(i).getTxtStatus();
        if(status == 0){
            txtStatus.setText("Deactive");
        }else if(status == 1){
            txtStatus.setText("Awaiting Approval");
        }else if(status == 2){
            txtStatus.setText("Active");
        }
        adpBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue= Volley.newRequestQueue(context);
                //Toast.makeText(Help.this, "Ipaddress"+ip, Toast.LENGTH_SHORT).show();

                String url="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/delete";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Response"+"Your Query Has been Submitted", Toast.LENGTH_SHORT).show();
                        Log.i("Resposnse",response);

                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // errorLog.d("Error.Response", String.valueOf(error));
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        id1=""+businessProfileLists.get(pos).getId();
                        getData();
                        String user_id=""+id;
                        Map<String, String>  params = new HashMap<String, String >();
                        params.put("id",id1);
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        params.put("user_id",user_id);
                        return params;
                    }
                };
                queue.add(stringRequest);
                Intent intent=new Intent(context,ProfileBusinessListing.class);
                activity.startActivityForResult(intent, 0);
                activity.overridePendingTransition(0, 0);
                activity.finish();

            }
        });
        adapBtnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id1=""+businessProfileLists.get(pos).getId();
                getData();

                final String url="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+id1+"?user_id="+id+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                //final String pro_id="" + businessProfileLists.get(i).getId();
                //Log.i("Url",url+"    "+pro_id+"    "+i);

                final RequestQueue queue= Volley.newRequestQueue(context);


                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Json Response",response.toString());
                        try {

                            final String name=response.getString("name");
                            final String registration_no=response.getString("registration_no");
                            String license_no=response.getString("licence_no");
                            String website=response.getString("website");
                            String category_id=response.getString("category_id");
                            String phone=response.getString("phone");
                            String address=response.getString("address");
                            String description=response.getString("description");
                            String latitude = response.getString("latitude");
                            String longitude = response.getString("longitude");
                            String email1= response.getString("email");
                            String quote_message = response.getString("quote_message");
                            String short_description = response.getString("short_desc");
                            String topcat_id=response.getString("topcat_id");
                            String parentcat_id=response.getString("parentcat_id");
                            String status=response.getString("status");
                            String yellowpage_id=response.getString("id");


                                SharedPreferences basicInfoBusiness = context.getSharedPreferences("business_edit", 0);
                                SharedPreferences.Editor editor = basicInfoBusiness.edit();
                                editor.putString("name", name);
                                editor.putString("registration_no", registration_no);
                                editor.putString("licence_no", license_no);
                                editor.putString("website", website);
                                editor.putString("category_id", category_id);
                                editor.putString("phone", phone);
                                editor.putString("address", address);
                                editor.putString("description", description);
                                editor.putString("latitude", latitude);
                                editor.putString("longitude", longitude);
                                editor.putString("email",email1);
                                editor.putString("quote_message",quote_message);
                                editor.putString("short_desc",short_description);
                                editor.putInt("edit",1);
                                editor.putString("topcat_id",topcat_id);
                                editor.putString("parentcat_id",parentcat_id);
                                editor.putString("status",status);
                                editor.putString("yellowpage_id",yellowpage_id);
                                editor.commit();
                                String url_video="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id+"/video?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                            final JsonObjectRequest jsonObjectRequest_video= new JsonObjectRequest(Request.Method.GET, url_video, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONArray jsonArray=response.getJSONArray("data");
                                        Log.i("video",response.toString());
                                        for (int i=0;i<1;i++) {
                                            JSONObject response1=jsonArray.getJSONObject(i);
                                            String video_title = response1.getString("title");
                                            String video_code = response1.getString("video_code");
                                            SharedPreferences sharedPreferences = context.getSharedPreferences("business_edit", 0);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("video_title", video_title);
                                            editor.putString("video_code", video_code);
                                            editor.commit();
                                        }



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                            queue.add(jsonObjectRequest_video);
                            String url_social="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id+"/sociallinks?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                            final JsonObjectRequest jsonObjectRequest_social= new JsonObjectRequest(Request.Method.GET, url_social, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Log.i("social link",response.toString());
                                            String facebook = response.getString("facebook");
                                            String twitter = response.getString("twitter");
                                            String linkedin=response.getString("linkedin");
                                            String googleplus=response.getString("googleplus");
                                            String instagram=response.getString("instagram");
                                            String youtube=response.getString("youtube");
                                            String telegram=response.getString("telegram");
                                            SharedPreferences sharedPreferences = context.getSharedPreferences("business_edit", 0);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("facebook", facebook);
                                            editor.putString("twitter",twitter);
                                            editor.putString("linkedin",linkedin);
                                            editor.putString("googleplus",googleplus);
                                            editor.putString("instagram",instagram);
                                            editor.putString("youtube",youtube);
                                            editor.putString("telegram",telegram);
                                            editor.commit();




                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                            queue.add(jsonObjectRequest_social);
                            String url4="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage-category/"+topcat_id+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                            final JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.GET, url4, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {

                                        JSONObject jsonObject1=response.getJSONObject("data");

                                        String topcat_name=jsonObject1.getString("title");
                                        SharedPreferences sharedPreferences = context.getSharedPreferences("business_edit", 0);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("topcat_name",topcat_name);
                                        editor.commit();



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                            queue.add(jsonObjectRequest4);
                            String url5="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage-category/"+parentcat_id+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                            final JsonObjectRequest jsonObjectRequest5 = new JsonObjectRequest(Request.Method.GET, url5, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONObject jsonObject1=response.getJSONObject("data");
                                        String parentcat_name=jsonObject1.getString("title");
                                        SharedPreferences sharedPreferences = context.getSharedPreferences("business_edit", 0);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("parentcat_name",parentcat_name);
                                        editor.commit();



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                            queue.add(jsonObjectRequest5);
                            String url6="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage-category/"+category_id+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                            final JsonObjectRequest jsonObjectRequest6 = new JsonObjectRequest(Request.Method.GET, url6, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONObject jsonObject1=response.getJSONObject("data");
                                        String category_name=jsonObject1.getString("title");
                                        String attributes_ids=jsonObject1.getString("attributes");
                                        SharedPreferences sharedPreferences = context.getSharedPreferences("business_edit", 0);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("category_name",category_name);
                                        editor.putString("attributes",attributes_ids);

                                        editor.commit();



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                            queue.add(jsonObjectRequest6);


                                               } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error",error.toString());

                            }
                        });
                queue.add(jsonObjectRequest);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Intent intent=new Intent(context,Main3BusinessActivity.class);
                        context.startActivity(intent);
                    }
                }, 2000);


            }
        });

        return view;
    }
    public void getData()
    {
        SharedPreferences loginData=context.getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");

    }
}
