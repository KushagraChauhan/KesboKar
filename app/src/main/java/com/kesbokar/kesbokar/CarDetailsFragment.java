package com.kesbokar.kesbokar;


import android.os.Bundle;
<<<<<<< HEAD
=======
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
>>>>>>> d5c66d1074921e69805bda64abc2255ce44e0c47

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarDetailsFragment extends Fragment {
<<<<<<< HEAD


=======
    AutoCompleteTextView car_make, car_model, car_year, car_variant,car_color,car_air,car_registered,car_state;
    String make,id_make,make1,model_id,model_title,model1,url1,id_model,model_year,year,variant_id,variant_title;
    RequestQueue requestQueue;
    Dictionary<String, String> make_dictionary,model_dictionary,variant_dictionary;
    TextView make_text,model_text,year_text,variant_text,color_text,air_text,registered_text,state_text,number_text,expiry_text;
    ArrayList<String> make_array,model_array,year_array,variant_array;
    String [] color_array,response,state_array;
    String color_response,registered_response,air_response,state,number,expiry,id_series,name_series,des_body,des_engine;
    DatePicker car_expiry;
    EditText car_number;
    Button next_frag;
    int id;
    ListView lvSeries;
    ArrayList<CarDetailsSeries> carDetailsSeries;
>>>>>>> d5c66d1074921e69805bda64abc2255ce44e0c47
    public CarDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        return inflater.inflate(R.layout.fragment_car_details, container, false);
=======
        View view = inflater.inflate(R.layout.fragment_car_details, container, false);

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
                url1 = "http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/model/dd/get?make_id="+id_make+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
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
                url1="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/year/dd/get?model_id="+id_model+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
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
                url1="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/variant/dd/get?model_id="+id_model+"&year="+year+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
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
                url1="http://serv.kesbokar.com.au/jil.0.1/v1/vehicle/detail/get?make_id="+id_make+"&model_id="+id_model+"&year="+year+"&variant_id="+variant_id+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
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
                if(Answer=="Yes")
                {

                    color_response="y";
                }
                else{
                    color_response="n";
                }


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
                if(Answer=="Yes")
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
                expiry=car_expiry.getYear()+"-"+month+"-"+day;
                Toast.makeText(getActivity(), number+"     and     "+expiry+"   and   "+registered_response+"   and   "+color_response+"   and   "+air_response, Toast.LENGTH_SHORT).show();

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
>>>>>>> d5c66d1074921e69805bda64abc2255ce44e0c47
    }

<<<<<<< HEAD
=======
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
>>>>>>> d5c66d1074921e69805bda64abc2255ce44e0c47
}
