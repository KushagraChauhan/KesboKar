package com.kesbokar.kesbokar;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManager.LoaderCallbacks;
import androidx.loader.content.Loader;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class BasicInfoBusinessFragment extends Fragment implements LocationListener {

    ProgressDialog progressDialog;

    private EditText edtCompanyTitle;
    private MultiAutoCompleteTextView mltAutoKeyWords;
    private TextView txtCatFirst, txtCatSecond, txtCatThird;
    private Button btnCancel_1, btnCancel_2, btnCancel_3;
    private EditText edtABN_Number;
    private Button btnVerify;
    String loginId, loginPass, full_name, email, image, phone_no, created, updated;
    int id, flag, edit1=0;
    private Button btnDetect, btnSave;

    private String name, registration_no, license_no, website, category_id, phone, address, description, latitude, longitude, email1,topcat_id,parentcat_id,
            quote_message, short_description,category_name,topcat_name,parentcat_name,yellowpage_id, new_name;


    private EditText etLongitude, etLatitude, etLicense, etQuote, etPhone, etEmail, etStreet, etWebsite;

    private AutoCompleteTextView etState, etSuburb;
    ViewPager viewPager;
    TabLayout tabLayout;

    Button cancel_tag;

    Context context;
    String result;

    CategoriesBaseAdapter categoriesBaseAdapter;
    CategoriesSecondAdapter categoriesSecondAdapter;
    CategoriesThirdAdapter categoriesThirdAdapter;

    private String parent_id = "";
    String tagsIds = "";
    String firstCat, secondCat, thirdCat;
    private String tags;
    int count = 0;

    private String q, subV;
    private String querySub;
    private String subType;
    private int stateid;
    private int subUrbID;

    private ArrayList<CategoryBase> categoryBaseArrayList;
    private ArrayList<CategorySecond> categorySecondArrayList;
    private ArrayList<CategoryThird> categoryThirdArrayList;

    private ArrayList<String> tagsName;
    private ArrayList<TagsObject> tagsObjectArrayList;
    private ArrayList<TagsObject> tagsSelectedArrayList;
    ArrayAdapter<TagsObject> tagsObjectArrayAdapter;

    //Loaders
    private static final int LOADER_FIRST_CATEGORY = 10101;
    private static final int LOADER_SECOND_CATEGORY = 10102;
    private static final int LOADER_THIRD_CATEGORY = 10103;
    private static final int LOADER_TAGS = 10104;

    private static final int LOADER_ID_BUSVAL = 10201;
    private ArrayList<StateAndSuburb> valsBus;
    private androidx.loader.app.LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>> businessSuburb;

    private LoaderCallbacks<ArrayList<CategoryBase>> firstCategoryLoader;
    private LoaderCallbacks<ArrayList<CategorySecond>> secondCategoryLoader;
    private LoaderCallbacks<ArrayList<CategoryThird>> thirdCategoryLoader;
    private LoaderCallbacks<ArrayList<TagsObject>> tagsObjectLoader;

    private ListView listCategoriesBase, listCategoriesSecond, listCategoriesThird;


    public BasicInfoBusinessFragment(ViewPager myViewPager, TabLayout myTabLayout) {
        // Required empty public constructor
        this.viewPager = myViewPager;
        this.tabLayout = myTabLayout;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_info_business, container, false);

        progressDialog = new ProgressDialog(getActivity());
        edtCompanyTitle = view.findViewById(R.id.edtCompanyTitle);
        mltAutoKeyWords = (MultiAutoCompleteTextView) view.findViewById(R.id.mltAutoKeyWords_business);
        edtABN_Number = (EditText) view.findViewById(R.id.etABN);
        btnVerify = (Button) view.findViewById(R.id.btnVerify);
        btnDetect = (Button) view.findViewById(R.id.btnDetect);

        etState = (AutoCompleteTextView) view.findViewById(R.id.etState);
        etSuburb = (AutoCompleteTextView) view.findViewById(R.id.etSuburb);
        etLicense = (EditText) view.findViewById(R.id.etLicense);
        etQuote = (EditText) view.findViewById(R.id.etQuote);
        etPhone = (EditText) view.findViewById(R.id.etPhone);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etStreet = (EditText) view.findViewById(R.id.etStreet);
        etWebsite = (EditText) view.findViewById(R.id.etWebsite);

        etLongitude = (EditText) view.findViewById(R.id.etLongitude);
        etLatitude = (EditText) view.findViewById(R.id.etLatitude);

        context = getContext();


        btnCancel_1 = (Button) view.findViewById(R.id.btnCancel_1);
        btnCancel_2 = (Button) view.findViewById(R.id.btnCancel_2);
        btnCancel_3 = (Button) view.findViewById(R.id.btnCancel_3);

        btnCancel_1.setVisibility(View.GONE);
        btnCancel_2.setVisibility(View.GONE);
        btnCancel_3.setVisibility(View.GONE);

        cancel_tag = view.findViewById(R.id.cancel_tag);
        tags = "";

        txtCatFirst = view.findViewById(R.id.txtCatFirst);
        txtCatSecond = view.findViewById(R.id.txtCatSecond);
        txtCatThird = view.findViewById(R.id.txtCatThird);
        btnSave = view.findViewById(R.id.btnSave);

        txtCatSecond.setVisibility(View.GONE);
        txtCatThird.setVisibility(View.GONE);

        categoryBaseArrayList = new ArrayList<>();
        categorySecondArrayList = new ArrayList<>();
        categoryThirdArrayList = new ArrayList<>();

        tagsName = new ArrayList<>();
        tagsSelectedArrayList = new ArrayList<>();
        tagsObjectArrayList = new ArrayList<>();

        querySub = "";

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("business_edit",0);
        edit1 = sharedPreferences.getInt("edit",0);

        q = subV = querySub = "au";

        if (edit1 == 1){
            getData();
            edtCompanyTitle.setText(name);

            etLicense.setText(license_no);

            edtABN_Number.setText(registration_no);
            etLicense.setText(license_no);
            etWebsite.setText(website);
            etQuote.setText(quote_message);
            etPhone.setText(phone);
            etEmail.setText(email1);
            etStreet.setText(address);
            etLatitude.setText(latitude);
            etLongitude.setText(longitude);
            txtCatFirst.setText(topcat_name);
            txtCatSecond.setText(parentcat_name);
            txtCatThird.setText(category_name);
            txtCatFirst.setEnabled(false);
            txtCatSecond.setEnabled(false);
            txtCatThird.setEnabled(false);
            txtCatFirst.setVisibility(View.VISIBLE);
            txtCatSecond.setVisibility(View.VISIBLE);
            txtCatThird.setVisibility(View.VISIBLE);
            btnCancel_1.setVisibility(View.VISIBLE);
            firstCat=topcat_id;
            secondCat=parentcat_id;
            thirdCat=category_id;


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
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                CategoryBase categoryBase = (CategoryBase) adapterView.getAdapter().getItem(position);
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
        firstCategoryLoader = new LoaderCallbacks<ArrayList<CategoryBase>>() {
            @Override
            public Loader<ArrayList<CategoryBase>> onCreateLoader(int i, Bundle bundle) {
                LoaderFirstCategory loaderFirstCategory = new LoaderFirstCategory(getContext(), "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage-category?parent_id=0&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return loaderFirstCategory;
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<CategoryBase>> loader, ArrayList<CategoryBase> categoryBases) {
                if (categoryBases != null) {
                    categoryBaseArrayList = categoryBases;
                    Log.i("API RESULT Category", "onLoadFinished: " + categoryBases);
                }
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<CategoryBase>> loader) {
                categoryBaseArrayList.removeAll(null);
            }
        };
        secondCategoryLoader = new LoaderCallbacks<ArrayList<CategorySecond>>() {
            @NonNull
            @Override
            public Loader<ArrayList<CategorySecond>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderCategorySecond loaderCategorySecond = new LoaderCategorySecond(getContext(), "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage-category?parent_id=" + parent_id + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return loaderCategorySecond;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<CategorySecond>> loader, ArrayList<CategorySecond> data) {
                if (data != null) {
                    categorySecondArrayList = data;
                    //Log.i("API SECOND", data.get(0).getTitle());
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<ArrayList<CategorySecond>> loader) {
                categorySecondArrayList.removeAll(null);
            }
        };
        thirdCategoryLoader = new LoaderCallbacks<ArrayList<CategoryThird>>() {
            @NonNull
            @Override
            public Loader<ArrayList<CategoryThird>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderCategoriesThird loaderCategoriesThird = new LoaderCategoriesThird(getContext(), "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage-category?parent_id=" + parent_id + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                return loaderCategoriesThird;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<CategoryThird>> loader, ArrayList<CategoryThird> data) {
                if (data != null) {
                    categoryThirdArrayList = data;
//                    Log.i("API THIRD Cat", data.get(0).getTags() + "");
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
                LoaderGetTags loaderGetTags = new LoaderGetTags(getActivity(), tags, tagsName, "http://serv.kesbokar.com.au/jil.0.1/v1/tags/dd");
                return loaderGetTags;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<TagsObject>> loader, ArrayList<TagsObject> data) {
                if (data != null) {
                    tagsObjectArrayList.clear();
                    tagsSelectedArrayList.clear();
                    Toast.makeText(getActivity(), "" + tagsSelectedArrayList.size(), Toast.LENGTH_SHORT).show();
                    tagsObjectArrayList = data;
                    tagsObjectArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tagsObjectArrayList);
                    mltAutoKeyWords.setAdapter(tagsObjectArrayAdapter);
                    mltAutoKeyWords.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                    mltAutoKeyWords.setThreshold(1);
                    mltAutoKeyWords.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (tagsSelectedArrayList.size() < 5) {
                                mltAutoKeyWords.showDropDown();
                            } else {
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
                            Toast.makeText(getContext(), "" + tagsObjectArrayList.toString(), Toast.LENGTH_SHORT).show();
                            tagsSelectedArrayList.clear();
                            tagsObjectArrayAdapter.notifyDataSetChanged();
                            tagsObjectArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tagsObjectArrayList);
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
                            Toast.makeText(getActivity(), "" + tagsSelectedArrayList.size(), Toast.LENGTH_SHORT).show();
                            //long id = adapterView.getAdapter().getItemId(i);
                            if (!tagsSelectedArrayList.contains(tagsObject)) {
                                if (tagsSelectedArrayList.size() < 5) {
                                    if (count == 4) {
                                        tagsIds += tagsObject.getId();
                                    } else {
                                        tagsIds += tagsObject.getId() + ",";
                                    }
                                    tagsSelectedArrayList.add(tagsObject);
                                    tagsObjectArrayAdapter.remove(tagsObject);
                                    tagsObjectArrayAdapter.notifyDataSetChanged();
                                    tagsObjectArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tagsObjectArrayList);
                                    mltAutoKeyWords.setAdapter(tagsObjectArrayAdapter);
                                    count++;
                                } else {

                                }
                            }
                        }
                    });
                    Log.i("TAGS ", "onLoadFinished: " + data);
                } else {
                    Log.i("ERROR", "WTF");
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<ArrayList<TagsObject>> loader) {

            }
        };


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
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                                CategorySecond categorySecond = (CategorySecond) adapterView.getAdapter().getItem(i);
                                parent_id = categorySecond.getId();
                                secondCat = parent_id;
                                txtCatSecond.setText(categorySecond.getTitle());
                                Log.i("Parent id", parent_id);
                                txtCatThird.setVisibility(View.VISIBLE);
                                dialog.dismiss();
                                getLoaderManager().initLoader(LOADER_THIRD_CATEGORY, null, thirdCategoryLoader);
                                txtCatSecond.setEnabled(false);
                            }
                        });
                        dialog.show();
                    }
                }, 1800);
            }
        });

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
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                                CategoryThird categoryThird = (CategoryThird) adapterView.getAdapter().getItem(i);
                                parent_id = categoryThird.getId();
                                thirdCat = parent_id;
                                tags = categoryThird.getTags();
                                StringTokenizer stringTokenizer = new StringTokenizer(tags, ",");
                                while (stringTokenizer.hasMoreTokens()) {
                                    tagsName.add(stringTokenizer.nextToken());
                                }
                                txtCatThird.setText(categoryThird.getTitle());
                                Log.i("Parent id", parent_id);
                                Log.i("tags", "onItemClick: " + tags + "**********" + tagsName);
                                txtCatThird.setEnabled(false);
                                getLoaderManager().initLoader(LOADER_TAGS, null, tagsObjectLoader);
                                dialog.dismiss();

                            }
                        });
                        dialog.show();
                    }
                }, 1800);
            }
        });

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
                if (categoriesSecondAdapter != null && categorySecondArrayList != null) {
                    categorySecondArrayList.clear();
                    categoriesSecondAdapter.notifyDataSetChanged();

                }
                if (categoriesThirdAdapter != null && categoryThirdArrayList != null) {
                    categoryThirdArrayList.clear();
                    categoriesThirdAdapter.notifyDataSetChanged();
                }
//                if(categorySecondArrayList.size() > 0)
//                    categorySecondArrayList.removeAll(null);
//                if(categoryThirdArrayList.size() > 0)
//                    categoryThirdArrayList.removeAll(null);
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(getActivity());
                String url;
                url = "https://www.kesbokar.com.au/jil.0.1/api/v1/yellowpage/verify/abn?abn=" + edtABN_Number.getText().toString() + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //Toast.makeText(context, "Response:"+response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            result = response.getString("result");
                            Log.i("result", result);
                            Toast.makeText(context, "result" + result + "    " + jsonObject.toString(), Toast.LENGTH_SHORT).show();
                            if (result.equals("")) {
                                Toast.makeText(context, "ABN Not Verified", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "ABN Verified", Toast.LENGTH_SHORT).show();
                                edtABN_Number.setEnabled(false
                                );

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });

        etSuburb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                subV = stateAndSuburb.getValue();
                stateid = stateAndSuburb.getId();
                subType = stateAndSuburb.getType();
            }
        });

        etSuburb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                querySub = s.toString();
                getLoaderManager().initLoader(LOADER_ID_BUSVAL, null, businessSuburb);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        businessSuburb = new androidx.loader.app.LoaderManager.LoaderCallbacks<ArrayList<StateAndSuburb>>() {
            @NonNull
            @Override
            public Loader<ArrayList<StateAndSuburb>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderBusSuburb loaderBusSuburb = new LoaderBusSuburb(getContext(), querySub, "http://serv.kesbokar.com.au/jil.0.1/v2/product/search/cities");
                return loaderBusSuburb;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<StateAndSuburb>> loader, ArrayList<StateAndSuburb> data) {
                if (data.size() != 0) {
                    valsBus = data;
                    Log.i("Tag", valsBus + "");
                    ArrayAdapter<StateAndSuburb> adapter = new ArrayAdapter<StateAndSuburb>(getContext(), android.R.layout.simple_dropdown_item_1line, valsBus);
                    etState.setAdapter(adapter);
                    getLoaderManager().destroyLoader(LOADER_ID_BUSVAL);
                    etSuburb.setAdapter(adapter);
                    getLoaderManager().destroyLoader(LOADER_ID_BUSVAL);
                    etState.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etState.showDropDown();
                        }
                    });
                    etSuburb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etSuburb.showDropDown();
                        }
                    });
                    etState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                            stateid = stateAndSuburb.getId();
                        }
                    });
                    etSuburb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            StateAndSuburb stateAndSuburb = (StateAndSuburb) parent.getAdapter().getItem(position);
                            subUrbID = stateAndSuburb.getId();
                        }
                    });
                }

            }

            @Override
            public void onLoaderReset(@NonNull Loader<ArrayList<StateAndSuburb>> loader) {

            }
        };

        getLoaderManager().initLoader(LOADER_ID_BUSVAL, null, businessSuburb);

        btnDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                onLocationChanged(location);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                String url;
                if (edit1==1)
                {
                    url = "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/"+yellowpage_id;
                }
                else {
                    url = "http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage";
                }
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                final

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String yellowpage_id = jsonObject.getString("yellowpage_id");
                            SharedPreferences get_product_detail = getActivity().getSharedPreferences("product_detail", 0);
                            SharedPreferences.Editor editor = get_product_detail.edit();
                            editor.putString("new_name", edtCompanyTitle.getText().toString());
                            editor.putString("yellowpage_id", yellowpage_id);
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("user_id", "" + id);
                        params.put("name", edtCompanyTitle.getText().toString());
                        params.put("licence_no", etLicense.getText().toString()); //For EditText
                        params.put("website", etWebsite.getText().toString());
                        params.put("quote_message", etQuote.getText().toString());
                        params.put("phone", etPhone.getText().toString());
                        params.put("email", etEmail.getText().toString());
                        params.put("address", etStreet.getText().toString());
                        params.put("latitude", etLatitude.getText().toString());
                        params.put("longitude", etLongitude.getText().toString());
                        params.put("topcat_id", firstCat);   // For Textview
                        params.put("parentcat_id", secondCat);
                        params.put("category_id", thirdCat);
                        params.put("tags", tagsIds);
                        params.put("registration_no", edtABN_Number.getText().toString());
                        params.put("registration_name", "");


                        params.put("api_token", "FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                queue.add(stringRequest);
                int item = viewPager.getCurrentItem();
                View tab = tabLayout.getTabAt(item + 1).view;
                tab.setEnabled(true);
                viewPager.setCurrentItem(item + 1);  //For going to the next tab in viewPager
            }
        });

        return view;

    }


    @Override
    public void onLocationChanged(Location location) {
        double longitudeV = location.getLongitude();
        double latitude = location.getLatitude();
        Geocoder gc = new Geocoder(context);

        List<Address> list = null;
        try {

            list = gc.getFromLocation(latitude, longitudeV, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address address = list.get(0);

        StringBuffer str = new StringBuffer();
        str.append(address.getLocality() + " ");
        str.append(address.getSubAdminArea() + " ");
        str.append(address.getAdminArea() + " ");
        str.append(address.getCountryName() + " ");
        str.append(address.getCountryCode() + " ");

        String strAddress = str.toString();

        etLatitude.setText("" + latitude);
        etLongitude.setText("" + longitudeV);

        etLongitude.setEnabled(false);
        etLatitude.setEnabled(false);

        //Toast.makeText(this, "Longitude"+longitudeV+"     Latitude"+latitude +"   "+ strAddress, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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

        SharedPreferences basicInfoBusiness = getActivity().getSharedPreferences("business_edit", 0);
        edit1=basicInfoBusiness.getInt("edit",0);
        if (edit1 == 1) {

            name = basicInfoBusiness.getString("name", "");
            registration_no = basicInfoBusiness.getString("registration_no", "");
            license_no = basicInfoBusiness.getString("licence_no", "");
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
            topcat_id=basicInfoBusiness.getString("topcat_id","");
            parentcat_id=basicInfoBusiness.getString("parentcat_id","");
            category_name=basicInfoBusiness.getString("category_name","");
            topcat_name=basicInfoBusiness.getString("topcat_name","");
            parentcat_name=basicInfoBusiness.getString("parentcat_name","");
            yellowpage_id=basicInfoBusiness.getString("yellowpage_id","");

        }

    }
}

