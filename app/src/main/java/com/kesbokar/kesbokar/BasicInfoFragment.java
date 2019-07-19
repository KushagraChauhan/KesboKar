package com.kesbokar.kesbokar;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;

import android.app.ProgressDialog;
import android.content.Context;

import android.app.Dialog;

import androidx.loader.content.Loader;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicInfoFragment extends Fragment {

    ProgressDialog progressDialog;
    CategoriesThirdAdapter categoriesThirdAdapter;
    CategoriesBaseAdapter categoriesBaseAdapter;
    CategoriesSecondAdapter categoriesSecondAdapter;
    private TextView txtCatFirst, txtCatSecond, txtCatThird,etPostProduct;
    String make_id,model_id,year,variant_id,vehicle_id,colour,airconditioning,registered,registration_state,registration_number,registration_expiry,name_title,product_condition,product_section,category_id1,price1,phone1,address1,description1,status1,topcat_id,parentcat_id,topcat_name,parentcat_name,category_name,attribute_ids;
    int edit1;
    String attribute_value;

    String condition1, condition2, product_name, product_id, condition1Value, condition2Value,pro_id;
    RadioGroup rgProductCondition, rgProductSelection;
    int entry_state;
    ViewPager viewPager;
    TabLayout tabLayout;
    String firstCat, secondCat, thirdCat;
    String tagsIds = "";
    String attributes_data="";

    private MultiAutoCompleteTextView mltAutoKeyWords;

    private ArrayList<TagsObject> tagsSelectedArrayList;
    private Button btnCancel_1, btnCancel_2, btnCancel_3;
    private String tags;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    int count = 0;
    private Context context;
    private String parent_id = "";
    private static final int LOADER_FIRST_CATEGORY = 101;
    private static final int LOADER_SECOND_CATEGORY = 102;
    private static final int LOADER_THIRD_CATEGORY = 103;
    private static final int LOADER_TAGS = 104;
    String attribute_id;

    private LoaderManager.LoaderCallbacks<ArrayList<CategoryBase>> firstCategoryLoader;
    private LoaderManager.LoaderCallbacks<ArrayList<CategorySecond>> secondCategoryLoader;
    private LoaderManager.LoaderCallbacks<ArrayList<CategoryThird>> thirdCategoryLoader;
    private LoaderManager.LoaderCallbacks<ArrayList<TagsObject>> tagsObjectLoader;

    private ListView listCategoriesBase,listCategoriesSecond,listCategoriesThird;
    private ArrayList<CategoryBase> categoryBaseArrayList;
    private ArrayList<CategorySecond> categorySecondArrayList;
    private ArrayList<CategoryThird> categoryThirdArrayList;
    private ArrayList<TagsObject> tagsObjectArrayList;

    ArrayAdapter<TagsObject> tagsObjectArrayAdapter;
    private ArrayList<String> tagsName;
    Button cancel_tag, btn_save_and_nxt;

    EditText edtProductTitle, etPrice;
    String attributes;

    public BasicInfoFragment(ViewPager viewPager, TabLayout tabLayout)
    {
        this.viewPager=viewPager;
        this.tabLayout=tabLayout;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_info, container, false);
        getData();
        rgProductCondition = view.findViewById(R.id.rgProductCondition);
        rgProductSelection = view.findViewById(R.id.rgProductSelection);
        context = view.getContext();
        entry_state=0;
        // Categories
        final String[] value = new String[3];
        txtCatFirst =(TextView) view.findViewById(R.id.txtCatFirst);
        txtCatSecond =(TextView) view.findViewById(R.id.txtCatSecond);
        txtCatThird =(TextView) view.findViewById(R.id.txtCatThird);
        etPostProduct = view.findViewById(R.id.etPostProduct);
        edtProductTitle = (EditText) view.findViewById(R.id.etProductTitle);

        cancel_tag= view.findViewById(R.id.cancel_tag);
        btnCancel_1 = (Button) view.findViewById(R.id.btnCancel_1);
        btnCancel_2 = (Button) view.findViewById(R.id.btnCancel_2);
        btnCancel_3 = (Button) view.findViewById(R.id.btnCancel_3);
        btn_save_and_nxt=view.findViewById(R.id.btn_save_and_next);
        txtCatSecond.setVisibility(View.GONE);
        txtCatThird.setVisibility(View.GONE);

        btnCancel_1.setVisibility(View.GONE);
        btnCancel_2.setVisibility(View.GONE);
        btnCancel_3.setVisibility(View.GONE);

        etPrice = (EditText)  view.findViewById(R.id.etPrice);

        mltAutoKeyWords = (MultiAutoCompleteTextView) view.findViewById(R.id.mltAutoKeyWords);
        final String[] firstValueArray = {"API1", "API1", "API1"};
        final String[] secondValueArray;
        final String[] thirdValueArray;

        categoryBaseArrayList = new ArrayList<>();
        categorySecondArrayList = new ArrayList<>();
        categoryThirdArrayList = new ArrayList<>();
        tagsObjectArrayList = new ArrayList<>();
        tagsName = new ArrayList<>();
        tagsSelectedArrayList = new ArrayList<>();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading...");

        etPostProduct.setText(full_name);
        if (edit1==1) {
            edtProductTitle.setText(name_title);
            if (product_condition.equals("n")||product_condition.equals("N")) {
                RadioButton rgNew=rgProductCondition.findViewById(R.id.rbNew);
                rgNew.toggle();
                condition1Value="N";

            }
            else {
                RadioButton rgUsed=rgProductCondition.findViewById(R.id.rbUsed);
                rgUsed.toggle();
                condition1Value="U";
            }
            if (product_section.equals("s")||product_section.equals("S"))
            {
                RadioButton rbSell=rgProductSelection.findViewById(R.id.rbSell);
                rbSell.toggle();
                condition2Value="S";
            }
            else {
                RadioButton rbRent=rgProductSelection.findViewById(R.id.rbRent);
                rbRent.toggle();
                condition2Value="R";
            }
            etPrice.setText(price1);
            firstCat=topcat_id;
            secondCat=parentcat_id;
            thirdCat=category_id1;
            txtCatFirst.setVisibility(View.VISIBLE);
            txtCatSecond.setVisibility(View.VISIBLE);
            txtCatThird.setVisibility(View.VISIBLE);
            txtCatFirst.setText(topcat_name);
            txtCatSecond.setText(parentcat_name);
            txtCatThird.setText(category_name);
            btnCancel_1.setVisibility(View.VISIBLE);
            txtCatFirst.setEnabled(false);
            txtCatSecond.setEnabled(false);
            txtCatThird.setEnabled(false);
            attributes=attribute_ids;


        }




        txtCatFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(LOADER_FIRST_CATEGORY, null, firstCategoryLoader);
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        final Dialog dialog = new Dialog(getActivity());
                        View view1 = getActivity().getLayoutInflater().inflate(R.layout.category_base_dialog, null);
                        dialog.setContentView(view1);
                        listCategoriesBase = view1.findViewById(R.id.categoriesBaseListView);
                        categoriesBaseAdapter = new CategoriesBaseAdapter(getActivity(), categoryBaseArrayList);
                        listCategoriesBase.setAdapter(categoriesBaseAdapter);
                        listCategoriesBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                CategoryBase categoryBase = (CategoryBase) adapterView.getAdapter().getItem(i);
                                parent_id = categoryBase.getId();
                                firstCat = parent_id;
                                txtCatFirst.setText(categoryBase.getTitle());
                                getLoaderManager().initLoader(LOADER_SECOND_CATEGORY, null, secondCategoryLoader);
                                Log.i("Parent id", parent_id);
                                txtCatSecond.setVisibility(View.VISIBLE);
                                txtCatFirst.setEnabled(false);
                                btnCancel_1.setVisibility(View.VISIBLE);

                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                }, 1800);
            }

        });

        firstCategoryLoader = new LoaderManager.LoaderCallbacks<ArrayList<CategoryBase>>(){
            @Override
            public Loader<ArrayList<CategoryBase>> onCreateLoader(int i, Bundle bundle) {
                LoaderFirstCategory loaderFirstCategory = new LoaderFirstCategory(context, "http://serv.kesbokar.com.au/jil.0.1/v1/category?parent_id=0&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return loaderFirstCategory;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<CategoryBase>> loader, ArrayList<CategoryBase> categoryBases) {
                if(categoryBases!=null){
                    categoryBaseArrayList = categoryBases;
                    Log.i("API RESULT Category", "onLoadFinished: " + categoryBases);
                }
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<CategoryBase>> loader) {
                categoryBaseArrayList.removeAll(null);
            }
        };
        secondCategoryLoader = new LoaderManager.LoaderCallbacks<ArrayList<CategorySecond>>() {
            @NonNull
            @Override
            public Loader<ArrayList<CategorySecond>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderCategorySecond loaderCategorySecond = new LoaderCategorySecond(context, "http://serv.kesbokar.com.au/jil.0.1/v1/category?parent_id=" + parent_id + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return loaderCategorySecond;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<CategorySecond>> loader, ArrayList<CategorySecond> data) {
                if(data!=null){
                    categorySecondArrayList = data;
                    //Log.i("API SECOND", data.get(0).getTitle());
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<ArrayList<CategorySecond>> loader) {
                categorySecondArrayList.removeAll(null);
            }
        };
        thirdCategoryLoader = new LoaderManager.LoaderCallbacks<ArrayList<CategoryThird>>() {
            @NonNull
            @Override
            public Loader<ArrayList<CategoryThird>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderCategoriesThird loaderCategoriesThird = new LoaderCategoriesThird(context, "http://serv.kesbokar.com.au/jil.0.1/v1/category?parent_id=" + parent_id + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return loaderCategoriesThird;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<CategoryThird>> loader, ArrayList<CategoryThird> data) {
                if(data!=null){
                    categoryThirdArrayList = data;
                    Log.i("API THIRD Cat", data + "");
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<ArrayList<CategoryThird>> loader) {
                categoryThirdArrayList.removeAll(null);
            }
        };
        tagsObjectLoader = new LoaderManager.LoaderCallbacks<ArrayList<TagsObject>>() {
            @NonNull
            @Override
            public Loader<ArrayList<TagsObject>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderGetTags loaderGetTags = new LoaderGetTags(context, tags, tagsName, "http://serv.kesbokar.com.au/jil.0.1/v1/tags/dd");
                return loaderGetTags;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<TagsObject>> loader, ArrayList<TagsObject> data) {
                if(data!=null){
                    tagsObjectArrayList.clear();
                    tagsSelectedArrayList.clear();
                    Toast.makeText(getActivity(), ""+tagsSelectedArrayList.size(), Toast.LENGTH_SHORT).show();
                    tagsObjectArrayList = data;
                    tagsObjectArrayAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,tagsObjectArrayList);
                    mltAutoKeyWords.setAdapter(tagsObjectArrayAdapter);
                    mltAutoKeyWords.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                    mltAutoKeyWords.setThreshold(1);
                    mltAutoKeyWords.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (tagsSelectedArrayList.size()<5) {
                                    mltAutoKeyWords.showDropDown();
                                }
                                else
                                {
                                    Log.i("TAGSIDS", "onTouch: " + tagsIds);
                                    mltAutoKeyWords.dismissDropDown();
                                    mltAutoKeyWords.setEnabled(false);
                                }
                                return false;
                            }
                    });
                    cancel_tag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mltAutoKeyWords.setText("");
                            mltAutoKeyWords.setEnabled(true);
                            tagsObjectArrayList.addAll(tagsSelectedArrayList);
                            Toast.makeText(context, ""+tagsObjectArrayList.toString(), Toast.LENGTH_SHORT).show();
                            tagsSelectedArrayList.clear();
                            tagsObjectArrayAdapter.notifyDataSetChanged();
                            tagsObjectArrayAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,tagsObjectArrayList);
                            mltAutoKeyWords.setAdapter(tagsObjectArrayAdapter);
                        }
                    });
                    mltAutoKeyWords.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    mltAutoKeyWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            TagsObject tagsObject = (TagsObject) adapterView.getAdapter().getItem(i);
                            Toast.makeText(getActivity(), ""+tagsSelectedArrayList.size(), Toast.LENGTH_SHORT).show();
                            //long id = adapterView.getAdapter().getItemId(i);
                            if(!tagsSelectedArrayList.contains(tagsObject)){
                                if(tagsSelectedArrayList.size() < 5) {
                                    if(count==4){
                                        tagsIds += tagsObject.getId();
                                    }else{
                                        tagsIds += tagsObject.getId() + ",";
                                    }
                                    tagsSelectedArrayList.add(tagsObject);
                                    tagsObjectArrayAdapter.remove(tagsObject);
                                    tagsObjectArrayAdapter.notifyDataSetChanged();
                                    tagsObjectArrayAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,tagsObjectArrayList);
                                    mltAutoKeyWords.setAdapter(tagsObjectArrayAdapter);
                                    count++;
                                }else{

                                }
                            }
                        }
                    });
                    Log.i("TAGS ", "onLoadFinished: " + data);
                }else{
                    Log.i("ERROR", "WTF");
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<ArrayList<TagsObject>> loader) {

            }
        };



        //getLoaderManager().initLoader(LOADER_FIRST_CATEGORY, null, firstCategoryLoader);
        txtCatSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(LOADER_SECOND_CATEGORY, null, secondCategoryLoader);
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        final Dialog dialog = new Dialog(getActivity());
                        View view1 = getActivity().getLayoutInflater().inflate(R.layout.category_base_dialog, null);
                        dialog.setContentView(view1);
                        listCategoriesSecond = view1.findViewById(R.id.categoriesBaseListView);
                        categoriesSecondAdapter = new CategoriesSecondAdapter(getActivity(), categorySecondArrayList);
                        listCategoriesSecond.setAdapter(categoriesSecondAdapter);
                        listCategoriesSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                CategorySecond categorySecond = (CategorySecond) adapterView.getAdapter().getItem(i);
                                parent_id = categorySecond.getId();
                                secondCat = parent_id;
                                txtCatSecond.setText(categorySecond.getTitle());
                                Log.i("Parent id", parent_id);
                                txtCatThird.setVisibility(View.VISIBLE);
                                dialog.dismiss();
                                getLoaderManager().initLoader(LOADER_THIRD_CATEGORY, null, thirdCategoryLoader);
                                txtCatSecond.setEnabled(false);
                                //btnCancel_2.setVisibility(View.VISIBLE);
                            }
                        });
                        dialog.show();
                    }
                }, 1800);
            }
        });

        thirdValueArray = new String[]{"API3", "API3", "API3"};



        txtCatThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(LOADER_THIRD_CATEGORY, null, thirdCategoryLoader);
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        final Dialog dialog = new Dialog(getActivity());
                        View view1 = getActivity().getLayoutInflater().inflate(R.layout.category_base_dialog, null);
                        dialog.setContentView(view1);
                        listCategoriesThird = view1.findViewById(R.id.categoriesBaseListView);
                        categoriesThirdAdapter = new CategoriesThirdAdapter(getActivity(), categoryThirdArrayList);
                        listCategoriesThird.setAdapter(categoriesThirdAdapter);
                        listCategoriesThird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                CategoryThird categoryThird = (CategoryThird) adapterView.getAdapter().getItem(i);
                                parent_id = categoryThird.getId();
                                thirdCat = parent_id;
                                tags = categoryThird.getTags();
                                StringTokenizer stringTokenizer = new StringTokenizer(tags,",");
                                while(stringTokenizer.hasMoreTokens()){
                                    tagsName.add(stringTokenizer.nextToken());
                                }
                                txtCatThird.setText(categoryThird.getTitle());
                                Log.i("Parent id", parent_id);
                                Log.i("tags", "onItemClick: "+tags + "**********"+tagsName);
                                txtCatThird.setEnabled(false);
                                getLoaderManager().initLoader(LOADER_TAGS, null, tagsObjectLoader);
                                //btnCancel_3.setVisibility(View.VISIBLE);;
                                String url;
                                RequestQueue queue= Volley.newRequestQueue(getActivity());
                                final String price = etPrice.getText().toString();
                                    url="http://serv.kesbokar.com.au/jil.0.1/v1/category/"+thirdCat+"?api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                                    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONObject jsonObject = response.getJSONObject("data");
                                            attributes=jsonObject.getString("attributes");
                                            Log.i("Response",jsonObject.toString());
                                            Log.i("Attributes",attributes);
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
                                queue.add(jsonObjectRequest);
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                }, 1800);


            }
        });


//        btnCancel_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txtCatThird.setEnabled(true);
//                txtCatThird.setText("SELECT");
//                btnCancel_3.setVisibility(View.GONE);
//                if(categoriesThirdAdapter!=null && categoryThirdArrayList!=null){
//                    categoryThirdArrayList.clear();
//                    categoriesThirdAdapter.notifyDataSetChanged();
//                }
//                //categoryThirdArrayList.removeAll(null);
//            }
//        });
//
//        btnCancel_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                txtCatSecond.setEnabled(true);
//                txtCatSecond.setText("SELECT");
//                txtCatThird.setEnabled(true);
//                txtCatThird.setVisibility(View.GONE);
//                txtCatThird.setText("SELECT");
//                btnCancel_2.setVisibility(View.GONE);
//                btnCancel_3.setVisibility(View.GONE);
//                if(categoriesSecondAdapter!=null && categorySecondArrayList!=null) {
//                    categorySecondArrayList.clear();
//                    categoriesSecondAdapter.notifyDataSetChanged();
//
//                }
//               if(categoriesThirdAdapter!=null && categoryThirdArrayList!=null){
//                   categoryThirdArrayList.clear();
//                   categoriesThirdAdapter.notifyDataSetChanged();
//               }
////                categorySecondArrayList.removeAll(null);
////                if(categoryThirdArrayList.size() > 0)
////                    categoryThirdArrayList.removeAll(null);
//            }
//        });

        btnCancel_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCatFirst.setEnabled(true);
                txtCatFirst.setText("SELECT");
                txtCatSecond.setEnabled(true);
                txtCatSecond.setVisibility(View.GONE);
                txtCatSecond.setText("SELECT");
                txtCatThird.setEnabled(true);
                txtCatThird.setVisibility(View.GONE);
                txtCatThird.setText("SELECT");
                btnCancel_1.setVisibility(View.GONE);
//                btnCancel_2.setVisibility(View.GONE);
//                btnCancel_3.setVisibility(View.GONE);
                if(categoriesSecondAdapter!=null && categorySecondArrayList!=null) {
                    categorySecondArrayList.clear();
                    categoriesSecondAdapter.notifyDataSetChanged();

                }
                if(categoriesThirdAdapter!=null && categoryThirdArrayList!=null){
                    categoryThirdArrayList.clear();
                    categoriesThirdAdapter.notifyDataSetChanged();
                }
                  if(categorySecondArrayList.size() > 0)
                    categorySecondArrayList.removeAll(null);
                if(categoryThirdArrayList.size() > 0)
                    categoryThirdArrayList.removeAll(null);
            }
        });

        rgProductCondition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {

                    case R.id.rbNew:condition1="New";
                    condition1Value = "N";
                        break;

                    case R.id.rbUsed:condition1="used";
                    condition1Value = "U";
                                    }
            }
        });


        rgProductSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.rbSell:condition2="rbSell";
                        condition2Value = "S";
                        break;

                    case R.id.rbRent:condition2="rbRent";
                        condition2Value = "R";

                }
            }
        });
        btn_save_and_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                String url;
                String url1;
                RequestQueue queue= Volley.newRequestQueue(getActivity());
                final String price = etPrice.getText().toString();
                if (edit1==1)
                {
                    url="http://serv.kesbokar.com.au/jil.0.1/v1/product/"+pro_id;
                    url1="http://serv.kesbokar.com.au/jil.0.1/v1/product/"+pro_id+"/attributes?attr_ids="+attributes+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                }
                else if(entry_state==1)
                {
                    url="http://serv.kesbokar.com.au/jil.0.1/v1/product/"+product_id;
                    url1="http://serv.kesbokar.com.au/jil.0.1/v1/product/"+product_id+"/attributes?attr_ids="+attributes+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                }
                else {
                    url="http://serv.kesbokar.com.au/jil.0.1/v1/product";
                    url1="http://serv.kesbokar.com.au/jil.0.1/v1/product/attributes?attr_ids="+attributes+"&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response",response);
                        try {
                            JSONObject jsonObject1=new JSONObject(response);
                            product_id=jsonObject1.getString("product_id");
                            String title_name=jsonObject1.getString("name");
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
                        params.put("product_condition",condition1Value);
                        params.put("product_section",condition2Value);
//                        params.put("topcat_id",);
//                        params.put("parentcat_id",);
//                        params.put("category_id",);
//                        params.put("tags",);
                        params.put("price",price);
//                        params.put("product_condition",);
//                        params.put("product_section",);
                        params.put("topcat_id", firstCat);
                        params.put("parentcat_id",secondCat);
                        String user_id=""+id;
                        params.put("user_id",user_id);
                        params.put("category_id",thirdCat);
                        params.put("tags",tagsIds);
//                        params.put("price",);

                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                queue.add(stringRequest);

                RequestQueue queue1= Volley.newRequestQueue(getActivity());
                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("data");

                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                attribute_id=jsonObject.getString("id");
                                String attribute_name=jsonObject.getString("title");
                                String attribute_cat_title=jsonObject.getString("attr_cat_title");
                                attribute_value=attribute_value+jsonObject.getString("attribute_value")+",";
                                attributes_data=attributes_data+attribute_id+":"+attribute_name+"?"+attribute_cat_title+",";
                                Log.i("Response", jsonObject.toString());
                                Log.i("Attributes", attributes_data);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        SharedPreferences attribute_info=getActivity().getSharedPreferences("attributes",0);
                        SharedPreferences.Editor editor=attribute_info.edit();
                        editor.putString("attribute_info",attributes_data);
                        editor.putString("attrribute_id",attribute_id);
                        editor.putString("attribute_value",attribute_value);
                        editor.commit();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                queue1.add(jsonObjectRequest);
                int item=viewPager.getCurrentItem();
                View tab=tabLayout.getTabAt(item+1).view;
                tab.setEnabled(true);
                viewPager.setCurrentItem(item+1);
            }
        });

        return view;
    }
    public void getData()
    {
        SharedPreferences loginData=getActivity().getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");

        SharedPreferences get_product_detail=getActivity().getSharedPreferences("product_detail",0);

        product_id =get_product_detail.getString("product_id","");
        product_name=get_product_detail.getString("product_name","");
        SharedPreferences entry=getActivity().getSharedPreferences("entry_state",0);

        entry_state =entry.getInt("entry_state1",0);

        SharedPreferences business_edit=getActivity().getSharedPreferences("market_edit",0);

        edit1=business_edit.getInt("edit",0);
        if (edit1==1)
        {
            make_id=business_edit.getString("make_id","");
            model_id=business_edit.getString("model_id","");
            year=business_edit.getString("year","");
            variant_id=business_edit.getString("variant_id","");
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
        topcat_id=business_edit.getString("topcat_id","");
        parentcat_id=business_edit.getString("parentcat_id","");
        category_name=business_edit.getString("category_name","");
        topcat_name=business_edit.getString("topcat_name","");
        parentcat_name=business_edit.getString("parentcat_name","");
        attribute_ids=business_edit.getString("attributes","");


    }

}
