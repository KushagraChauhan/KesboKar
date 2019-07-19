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

public class AdapterBusListProfileMarket extends BaseAdapter {
    ArrayList<MarketProfileList> marketProfileLists;
    LayoutInflater layoutInflater;
    TextView txtSno,txtTitle,txtStatus;
    String id1;
    Context context;
    String vehicle;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated,make_id,make_name;
    int id,flag;
    int pos;
    Activity activity;
    Button adpMBtnDel,adpMBtnEdt;
    public AdapterBusListProfileMarket(Context context, ArrayList<MarketProfileList> marketProfileLists,Activity activity){
        this.context = context;
        this.activity=activity;
        this.marketProfileLists = marketProfileLists;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return marketProfileLists.size();
    }

    @Override
    public Object getItem(int i) {
        return marketProfileLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.adapter_layout_market,null);
        txtSno = view.findViewById(R.id.adapMTxtSno);
        adpMBtnEdt=view.findViewById(R.id.adapMBtnEdt);
        txtTitle = view.findViewById(R.id.adapMTxtTitle);
        txtStatus = view.findViewById(R.id.adapMTxtStatus);
        adpMBtnDel=view.findViewById(R.id.adapMBtnDel);
        txtSno.setText(marketProfileLists.get(i).getTxtSno());
        txtTitle.setText(marketProfileLists.get(i).getTxtTitle());
        int status = marketProfileLists.get(i).getTxtStatus();
        if(status == 0){
            txtStatus.setText("Deactive");
        }else if(status == 1){
            txtStatus.setText("Awaiting Approval");
        }else if(status == 2){
            txtStatus.setText("Active");
        }



        adpMBtnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String url="http://serv.kesbokar.com.au/jil.0.1/v1/product/"+marketProfileLists.get(i).getId()+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                final String pro_id="" + marketProfileLists.get(i).getId();

                Log.i("Url",url+"    "+pro_id+"    "+i);


                final RequestQueue queue= Volley.newRequestQueue(context);


                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Json Response",response.toString());
                        try {

                            String name=response.getString("name");
                            String product_condition=response.getString("product_condition");
                            String product_section=response.getString("product_section");
                            String category_id=response.getString("category_id");
                            String price=response.getString("price");
                            String phone=response.getString("phone");
                            String address=response.getString("address");
                            String description=response.getString("description");
                            vehicle=response.getString("vehicle");
                            String parentcat_id=response.getString("parentcat_id");
                            String topcat_id=response.getString("topcat_id");
                            if(!vehicle.equals("null")) {
                                final JSONObject jsonObject = response.getJSONObject("vehicle");
                                make_id = jsonObject.getString("make_id");
                                String model_id = jsonObject.getString("model_id");
                                String year = jsonObject.getString("year");
                                final String variant_id = jsonObject.getString("variant_id");
                                String vehicle_id = jsonObject.getString("vehicle_id");
                                String colour = jsonObject.getString("colour");
                                String airconditioning = jsonObject.getString("airconditioning");
                                final String registered = jsonObject.getString("registered");
                                String registration_state = jsonObject.getString("registration_state");
                                String registration_number = jsonObject.getString("registration_number");
                                String registration_expiry = jsonObject.getString("registration_expiry");
                                SharedPreferences sharedPreferences = context.getSharedPreferences("market_edit", 0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();editor.putString("make_id", make_id);
                                editor.putString("model_id", model_id);
                                editor.putString("year", year);
                                editor.putString("variant_id", variant_id);
                                editor.putString("vehicle_id", vehicle_id);
                                editor.putString("colour", colour);
                                editor.putString("airconditioning", airconditioning);
                                editor.putString("registered", registered);
                                editor.putString("registration_state", registration_state);
                                editor.putString("registration_number", registration_number);
                                editor.putString("registration_expiry", registration_expiry);
                                editor.commit();
                                String url1="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/make/"+make_id+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                final JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONObject jsonObject=response.getJSONObject("data");
                                            make_name=jsonObject.getString("title");
                                            SharedPreferences sharedPreferences = context.getSharedPreferences("market_edit", 0);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("make_name",make_name);
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
                                queue.add(jsonObjectRequest1);
                                String url2="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/model/"+model_id+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                final JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            String model_name=response.getString("title");
                                            SharedPreferences sharedPreferences = context.getSharedPreferences("market_edit", 0);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("model_name",model_name);
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
                                queue.add(jsonObjectRequest2);
                                String url3="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/variant/dd/get?model_id="+model_id+"&year="+year+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                final JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.GET, url3, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONArray jsonArray=response.getJSONArray("data");
                                            for (int i=0;i<jsonArray.length();i++)
                                            {
                                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                                String var_id=jsonObject1.getString("id");
                                                if (var_id.equals(variant_id))
                                                {
                                                    String variant_name=jsonObject1.getString("title");
                                                    SharedPreferences sharedPreferences = context.getSharedPreferences("market_edit", 0);
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString("variant_name",variant_name);
                                                    editor.commit();
                                                }
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
                                queue.add(jsonObjectRequest3);
                                String url4="http://serv.kesbokar.com.au/jil.0.1/v1/category/"+topcat_id+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                final JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.GET, url4, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {

                                                JSONObject jsonObject1=response.getJSONObject("data");

                                                    String topcat_name=jsonObject1.getString("title");
                                                    SharedPreferences sharedPreferences = context.getSharedPreferences("market_edit", 0);
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
                                String url5="http://serv.kesbokar.com.au/jil.0.1/v1/category/"+parentcat_id+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                final JsonObjectRequest jsonObjectRequest5 = new JsonObjectRequest(Request.Method.GET, url5, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONObject jsonObject1=response.getJSONObject("data");
                                                    String parentcat_name=jsonObject1.getString("title");
                                                    SharedPreferences sharedPreferences = context.getSharedPreferences("market_edit", 0);
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
                                String url6="http://serv.kesbokar.com.au/jil.0.1/v1/category/"+category_id+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                final JsonObjectRequest jsonObjectRequest6 = new JsonObjectRequest(Request.Method.GET, url6, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONObject jsonObject1=response.getJSONObject("data");
                                                String category_name=jsonObject1.getString("title");
                                                String attributes_ids=jsonObject1.getString("attributes");
                                                SharedPreferences sharedPreferences = context.getSharedPreferences("market_edit", 0);
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
                            }
                                SharedPreferences sharedPreferences = context.getSharedPreferences("market_edit", 0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name", name);
                                editor.putString("product_condition", product_condition);
                                editor.putString("product_section", product_section);
                                editor.putString("category_id", category_id);
                                editor.putString("price", price);
                                editor.putString("phone", phone);
                                editor.putString("address", address);
                                editor.putString("description", description);
                                editor.putString("product_id", pro_id);
                                editor.putInt("edit", 1);
                                editor.putString("parentcat_id",parentcat_id);
                                editor.putString("topcat_id",topcat_id);
                                editor.commit();
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
                        final Intent intent=new Intent(context,Main2Activity.class);
                        if (vehicle.equals("null"))
                        {
                            intent.putExtra("CAR_YES_OR_NO", false);

                        }
                        else {
                            intent.putExtra("CAR_YES_OR_NO", true);
                        }
                        context.startActivity(intent);
                    }
                }, 2000);


            }
        });

        adpMBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id1=""+marketProfileLists.get(i).getId();
                RequestQueue queue= Volley.newRequestQueue(context);
                //Toast.makeText(Help.this, "Ipaddress"+ip, Toast.LENGTH_SHORT).show();

                String url="http://serv.kesbokar.com.au/jil.0.1/v1/product/delete";
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
                Intent intent=new Intent(context,ProfileMarket.class);
                activity.startActivityForResult(intent, 0);
                activity.overridePendingTransition(0, 0);
                activity.finish();


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
