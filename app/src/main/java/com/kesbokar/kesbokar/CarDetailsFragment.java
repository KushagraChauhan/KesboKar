package com.kesbokar.kesbokar;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarDetailsFragment extends Fragment {

    AutoCompleteTextView car_make, car_model, car_year, car_variant,car_color,car_air,car_registered,car_state;
    String make,id_make,make1,model_id,model_title,model1,url1,id_model,model_year,year,variant_id,variant_title;
    RequestQueue requestQueue;
    Dictionary<String, String> make_dictionary,model_dictionary,variant_dictionary;
    TextView make_text,model_text,year_text,variant_text,color_text,air_text,registered_text,state_text,number_text,expiry_text;
    ArrayList<String> make_array,model_array,year_array,variant_array;
    String [] color_array,response,state_array;
    String url;
    String color_response,registered_response,air_response,state,number,expiry,id_series,name_series,des_body,des_engine,make_name;
    DatePicker car_expiry;
    EditText car_number;
    Button next_frag;
    int id,entry_state,cal_year,cal_month,cal_date;
    String make_id,model_id1,year1,variant_id1,vehicle_id,colour,airconditioning,registered,registration_state,registration_number,registration_expiry,name_title,product_condition,product_section,category_id1,price1,phone1,address1,description1,status1,pro_id,model_name,variant_name;
    int edit1;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id1,flag;
    ListView lvSeries;
    String series_id,product_id,title_name;
    ArrayList<CarDetailsSeries> carDetailsSeries;
    ViewPager viewPager;
    TabLayout tabLayout;
    public CarDetailsFragment(ViewPager viewPager, TabLayout tabLayout)
    {
        this.viewPager=viewPager;
        this.tabLayout=tabLayout;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_car_details, container, false);

        View view = inflater.inflate(R.layout.fragment_car_details, container, false);
        getData();

        car_make = view.findViewById(R.id.car_make);
        car_model = view.findViewById(R.id.car_model);
        car_variant = view.findViewById(R.id.car_variant);
        car_year = view.findViewById(R.id.car_year);
        car_color=view.findViewById(R.id.car_color);
        car_air=view.findViewById(R.id.car_air);
        car_registered=view.findViewById(R.id.car_registered);
        car_state=view.findViewById(R.id.car_state);
        car_number=view.findViewById(R.id.car_number);
        car_expiry=view.findViewById(R.id.car_expiry);
        make_text=view.findViewById(R.id.make);
        lvSeries=view.findViewById(R.id.lvSeries);
        model_text=view.findViewById(R.id.model);
        year_text=view.findViewById(R.id.year);
        car_number=view.findViewById(R.id.car_number);
        car_expiry=view.findViewById(R.id.car_expiry);
        variant_text=view.findViewById(R.id.variant);
        color_text=view.findViewById(R.id.color);
        state_text=view.findViewById(R.id.state);
        air_text=view.findViewById(R.id.air);
        registered_text=view.findViewById(R.id.registered);
        number_text=view.findViewById(R.id.number);
        expiry_text=view.findViewById(R.id.expiry);
        requestQueue = Volley.newRequestQueue(getActivity());
        make_dictionary = new Hashtable<>();
        model_dictionary=new Hashtable<>();
        variant_dictionary = new Hashtable<>();
        model_array=new ArrayList<String>();
        make_array=new ArrayList<String>();
        year_array=new ArrayList<String>();
        variant_array = new ArrayList<String>();
        carDetailsSeries=new ArrayList<>();
        next_frag=view.findViewById(R.id.next_frag);
        color_array=new String[]{"Black","Blue","Bronze","Brown","Burgandy","Cream","Gold","Grey","Green","Orange","Pink","Purple","Red","Silver","White","Yellow","Tan","Turquoise","Other"};
        response = new String[]{"Yes","No"};
        state_array = new String[]{"NSW","VIC","QLD","WA","SA","TAS","ACT","NT"};
        jsonParserMake();
        if (edit1==1)
        {
            car_model.setVisibility(View.VISIBLE);
            model_text.setVisibility(View.VISIBLE);
            car_year.setVisibility(View.VISIBLE);
            year_text.setVisibility(View.VISIBLE);
            car_variant.setVisibility(View.VISIBLE);
            variant_text.setVisibility(View.VISIBLE);
            color_text.setVisibility(View.VISIBLE);
            air_text.setVisibility(View.VISIBLE);
            registered_text.setVisibility(View.VISIBLE);
            car_color.setVisibility(View.VISIBLE);
            car_air.setVisibility(View.VISIBLE);
            car_registered.setVisibility(View.VISIBLE);
            variant_id=variant_dictionary.get(car_variant.getText().toString());
            next_frag.setVisibility(View.VISIBLE);
            lvSeries.setVisibility(View.VISIBLE);
            if (registered.equals("y")||registered.equals("Y"))
            {
                state_text.setVisibility(View.VISIBLE);
                car_state.setVisibility(View.VISIBLE);
                number_text.setVisibility(View.VISIBLE);
                car_number.setVisibility(View.VISIBLE);
                expiry_text.setVisibility(View.VISIBLE);
                car_expiry.setVisibility(View.VISIBLE);

            }
            registered_response=registered;
            air_response=airconditioning;
            color_response=colour;
            id_make=make_id;
            car_make.setText(make_name);
            id_model=model_id1;
            variant_id=variant_id1;
            car_model.setText(model_name);
            car_year.setText(year1);
            car_variant.setText(variant_name);
            car_color.setText(colour);
            if (airconditioning.equals("y")||airconditioning.equals("Y")) {
                car_air.setText("Yes");
            }
            else {
                car_air.setText("No");
            }
            if (registered.equals("y")||registered.equals("Y")) {
                car_registered.setText("Yes");
            }
            else {
                car_registered.setText("No");
            }
            if (!registration_expiry.equals("null")) {
                String[] date = registration_expiry.split("-");
                cal_year = Integer.parseInt(date[0]);
                cal_month = Integer.parseInt(date[1]);
                cal_date = Integer.parseInt(date[2]);
            }
            car_expiry.updateDate(cal_year,cal_month-1,cal_date);
            car_state.setText(registration_state);
            car_number.setText(registration_number);
            year=year1;
            jsonParserModel();
            jsonParserYear();
            jsonParserVariant();
            jsonParserSeries();
        }
        ArrayAdapter<String> adapter_make = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, make_array);
        car_make.setThreshold(0);
        car_make.setAdapter(adapter_make);
        car_make.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                car_make.showDropDown();
                return false;
            }
        });
        car_make.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                car_model.setVisibility(View.VISIBLE);
                model_text.setVisibility(View.VISIBLE);
                make1=car_make.getText().toString();
                id_make=make_dictionary.get(make1);
                model_array.clear();

                jsonParserModel();
            }
        });
        ArrayAdapter<String> adapter_model = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, model_array);
        car_model.setThreshold(0);
        car_model.setAdapter(adapter_model);
        car_model.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                car_model.showDropDown();
                return false;
            }
        });
        car_model.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                car_year.setVisibility(View.VISIBLE);
                year_text.setVisibility(View.VISIBLE);
                model1=car_model.getText().toString();
                id_model=model_dictionary.get(model1);

                year_array.clear();
                jsonParserYear();
            }
        });
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, year_array);
        car_year.setThreshold(0);
        car_year.setAdapter(adapter_year);
        car_year.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                car_year.showDropDown();
                return false;
            }
        });
        car_year.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                car_variant.setVisibility(View.VISIBLE);
                variant_text.setVisibility(View.VISIBLE);
                year=car_year.getText().toString();
                jsonParserVariant();
            }
        });
        ArrayAdapter<String> adapter_variant = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, variant_array);
        car_variant.setThreshold(0);
        car_variant.setAdapter(adapter_variant);
        car_variant.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                car_variant.showDropDown();
                return false;
            }
        });
        car_variant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                color_text.setVisibility(View.VISIBLE);
                air_text.setVisibility(View.VISIBLE);
                registered_text.setVisibility(View.VISIBLE);
                car_color.setVisibility(View.VISIBLE);
                car_air.setVisibility(View.VISIBLE);
                car_registered.setVisibility(View.VISIBLE);
                variant_id=variant_dictionary.get(car_variant.getText().toString());
                next_frag.setVisibility(View.VISIBLE);
                lvSeries.setVisibility(View.VISIBLE);
                lvSeries.setAdapter(null);
                jsonParserSeries();

            }
        });
        ArrayAdapter<String> adapter_color = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, color_array);
        car_color.setThreshold(0);
        car_color.setAdapter(adapter_color);
        car_color.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                car_color.showDropDown();
                return false;
            }
        });
        car_color.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Answer=car_color.getText().toString();
                color_response=Answer;


            }
        });
        ArrayAdapter<String> adapter_response = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, response);
        car_air.setThreshold(0);
        car_air.setAdapter(adapter_response);
        car_air.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                car_air.showDropDown();
                return false;
            }
        });
        car_air.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Answer=car_air.getText().toString();
                if(Answer.equals("Yes"))
                {

                    air_response="y";
                }
                else{
                    air_response="n";
                }


            }
        });
        car_registered.setThreshold(0);
        car_registered.setAdapter(adapter_response);
        car_registered.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                car_registered.showDropDown();
                return false;
            }
        });
        car_registered.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Answer=car_registered.getText().toString();
                if(Answer.equals("Yes"))
                {
                    state_text.setVisibility(View.VISIBLE);
                    car_state.setVisibility(View.VISIBLE);
                    number_text.setVisibility(View.VISIBLE);
                    car_number.setVisibility(View.VISIBLE);
                    expiry_text.setVisibility(View.VISIBLE);
                    car_expiry.setVisibility(View.VISIBLE);
                    registered_response="y";
                }
                else{
                    registered_response="n";
                    state_text.setVisibility(View.INVISIBLE);
                    car_state.setVisibility(View.INVISIBLE);
                    number_text.setVisibility(View.INVISIBLE);
                    car_number.setVisibility(View.INVISIBLE);
                    expiry_text.setVisibility(View.INVISIBLE);
                    car_expiry.setVisibility(View.INVISIBLE);
                }


            }
        });

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, state_array);
        car_state.setThreshold(0);
        car_state.setAdapter(adapter_state);
        car_state.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                car_state.showDropDown();
                return false;
            }
        });

        car_state.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                state=car_state.getText().toString();
            }
        });

        next_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                number=car_number.getText().toString();
                int d=car_expiry.getDayOfMonth();
                int mon=car_expiry.getMonth()+1;
                String month=""+mon;
                String day=""+d;
                if(month.length()==1)
                {
                    month="0"+month;
                }
                if(day.length()==1)
                {
                    day="0"+day;
                }
                if (registered_response.equals("y"))
                {
                    expiry = car_expiry.getYear() + "-" + month + "-" + day;
                }
                else
                {
                    expiry="";
                }
                Toast.makeText(getActivity(), number+"     and     "+expiry+"   and   "+registered_response+"   and   "+color_response+"   and   "+air_response, Toast.LENGTH_SHORT).show();
                if (edit1==1)
                {
                    url="http://serv.kesbokar.com.au/jil.0.1/v1/product/"+pro_id+"/vehicle";
                }
                else {
                    url = "http://serv.kesbokar.com.au/jil.0.1/v1/product";
                }
                RequestQueue queue= Volley.newRequestQueue(getActivity());
                //Toast.makeText(Help.this, "Ipaddress"+ip, Toast.LENGTH_SHORT).show();
                final JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("make_id",id_make);
                    jsonObject.put("model_id",id_model);
                    jsonObject.put("year",year);
                    jsonObject.put("variant_id",variant_id);
                    jsonObject.put("vehicle_id",series_id);
                    jsonObject.put("colour",color_response);
                    jsonObject.put("airconditioning",air_response);
                    jsonObject.put("registered",registered_response);
                    jsonObject.put("registration_state",state);
                    jsonObject.put("registration_number",number);
                    jsonObject.put("registration_expiry",expiry);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("JSONObject",jsonObject.toString());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "Response"+"Your Query Has been Submitted", Toast.LENGTH_SHORT).show();
                        Log.i("Response",response);
                        try {
                            JSONObject jsonObject1=new JSONObject(response);
                            product_id=jsonObject1.getString("product_id");
                            title_name=jsonObject1.getString("name");
                            Log.i("fetched data","id:"+product_id+"name:"+title_name);
                            SharedPreferences get_product_detail= getActivity().getSharedPreferences("product_detail",0);
                            SharedPreferences.Editor editor=get_product_detail.edit();
                            editor.putString("product_id",product_id);
                            editor.putString("product_name",title_name);
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // errorLog.d("Error.Response", String.valueOf(error));
                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        String user_id;
                        user_id=""+id1;
                        Map<String, String>  params = new HashMap<String, String >();
                        if (edit1==1)
                        {
                            params.put("make_id",id_make);
                            params.put("model_id",id_model);
                            params.put("year",year);
                            params.put("variant_id",variant_id);
                            params.put("vehicle_id",series_id);
                            params.put("colour",color_response);
                            params.put("airconditioning",air_response);
                            params.put("registered",registered_response);
                            if(registered_response=="y"||registered_response=="y") {
                                params.put("registration_state", state);
                                params.put("registration_number", number);
                                params.put("registration_expiry", expiry);
                            }
                            else {
                                params.put("registration_state","");
                                params.put("registration_number","");
                                params.put("registration_expiry","");


                            }
                            params.put("product_id", pro_id);
                        }
                        else {
                            params.put("category_id", "16");
                            params.put("user_id", user_id);
                            params.put("vehicle", jsonObject.toString());
                        }
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
                queue.add(stringRequest);
                entry_state=1;
                SharedPreferences get_entry_state= getActivity().getSharedPreferences("entry_state",0);
                SharedPreferences.Editor editor=get_entry_state.edit();
                editor.putInt("entry_state1",entry_state);
                editor.apply();

                

                int item=viewPager.getCurrentItem();
                View tab=tabLayout.getTabAt(item+1).view;
                tab.setEnabled(true);
                viewPager.setCurrentItem(item+1);


            }

        });
        //Toast.makeText(getActivity(), "" + make_dictionary.get("ZX Auto"), Toast.LENGTH_SHORT).show();
        //Log.i("Dictionary",make_dictionary.get("BMW"));
        return view;
    }

    private void jsonParserMake() {
        String url1 = "http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/make/dd?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    for (int i = 0; i < jsonObject.length(); i++) {
                        int k = i + 1;
                        String j = "" + k;
                        int p = jsonObject.length();
                        String l = "" + p;
                        Log.i("JSON Help", jsonObject.toString());
                        Log.i("Length", l);
                        make = jsonObject.getString(j);
                        make_dictionary.put(make, j);
                        Log.i("Dictionary", make_dictionary.get(make));
                        make_array.add(make);
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
        requestQueue.add(jsonObjectRequest);
    }
    public void jsonParserModel()
    {
        url1 = "http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/model/dd/get?make_id="+id_make+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    Log.i("Model Array",jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Log.i("Model",jsonObject.toString());
                        int k = i + 1;
                        String j = "" + k;
                        int p = jsonObject.length();
                        String l = "" + p;
                        Log.i("JSON Help", jsonObject.toString());
                        Log.i("Length", l);
                        model_id = jsonObject.getString("id");
                        model_title=jsonObject.getString("title");
                        model_dictionary.put(model_title, model_id);
                        //                 Log.i("Dictionary", model_dictionary.get(model_title));
                        model_array.add(model_title);
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
        requestQueue.add(jsonObjectRequest);
    }

    private void jsonParserYear()
    {
        url1="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/year/dd/get?model_id="+id_model+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    Log.i("Year",jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        model_year = jsonObject.getString("id");
                        year_array.add(model_year);
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
        requestQueue.add(jsonObjectRequest);
    }
    private void jsonParserVariant()
    {
        url1="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/variant/dd/get?model_id="+id_model+"&year="+year+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    Log.i("Variant Array",jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int k = i + 1;
                        String j = "" + k;
                        int p = jsonObject.length();
                        String l = "" + p;
                        Log.i("Length", l);
                        variant_id = jsonObject.getString("id");
                        variant_title=jsonObject.getString("title");
                        variant_dictionary.put(variant_title, variant_id);
                        //                 Log.i("Dictionary", model_dictionary.get(model_title));
                        variant_array.add(variant_title);
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
        requestQueue.add(jsonObjectRequest);
    }
    public void jsonParserSeries()
    {
        url1="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/detail/get?make_id="+id_make+"&model_id="+id_model+"&year="+year+"&variant_id="+variant_id+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    Log.i("Series Array",jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int k = i + 1;
                        String j = "" + k;
                        int p = jsonObject.length();
                        String l = "" + p;
                        Log.i("Length", l);
                        Log.i("Series Object",jsonObject.toString());
                        id=jsonObject.getInt("id");
                        name_series=jsonObject.getString("name");
                        des_body=jsonObject.getString("des_body");
                        des_engine=jsonObject.getString("des_engine");
                        carDetailsSeries.add(new CarDetailsSeries(id,name_series,des_body,des_engine));
                    }
                    AdapterCarDetailsSeries adapterCarDetailsSeries=new AdapterCarDetailsSeries(getActivity(),getActivity(),carDetailsSeries);
                    lvSeries.setAdapter(adapterCarDetailsSeries);

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
        requestQueue.add(jsonObjectRequest);
    }
    public void getData()
    {
        SharedPreferences loginData=getActivity().getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id1=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");
        SharedPreferences get=getActivity().getSharedPreferences("data1",0);
        series_id=get.getString("series","");
        SharedPreferences business_edit=getActivity().getSharedPreferences("market_edit",0);
        edit1=business_edit.getInt("edit",0);
        if (edit1==1)
        {
            make_id=business_edit.getString("make_id","");
            model_id1=business_edit.getString("model_id","");
            year1=business_edit.getString("year","");
            variant_id1=business_edit.getString("variant_id","");
            vehicle_id=business_edit.getString("vehicle_id","");
            colour=business_edit.getString("colour","");
            airconditioning=business_edit.getString("airconditioning","");
            registered=business_edit.getString("registered","");
            registration_state=business_edit.getString("registration_state","");
            registration_number=business_edit.getString("registration_number","");
            registration_expiry=business_edit.getString("registration_expiry","");
        }
        name_title=business_edit.getString("name","");
        product_condition=business_edit.getString("product_condition","");
        product_section=business_edit.getString("product_section","");
        category_id1=business_edit.getString("category_id","");
        price1=business_edit.getString("price","");
        phone1=business_edit.getString("phone","");
        address1=business_edit.getString("address","");
        description1=business_edit.getString("description","");
        status1=business_edit.getString("status","");
        pro_id=business_edit.getString("product_id","");
        make_name=business_edit.getString("make_name","");
        model_name=business_edit.getString("model_name","");
        variant_name=business_edit.getString("variant_name","");
    }


}
