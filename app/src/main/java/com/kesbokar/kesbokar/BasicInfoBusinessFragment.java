package com.kesbokar.kesbokar;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class BasicInfoBusinessFragment extends Fragment {

    ProgressDialog progressDialog;

    private EditText edtCompanyTitle;
    private MultiAutoCompleteTextView mltAutoKeyWords;
    private TextView txtCatFirst,txtCatSecond, txtCatThird;

    Button cancel_tag;

    Context context;

    CategoriesBaseAdapter categoriesBaseAdapter;
    CategoriesSecondAdapter categoriesSecondAdapter;
    CategoriesThirdAdapter categoriesThirdAdapter;

    private String parent_id = "";
    String tagsIds = "";
    String firstCat,secondCat, thirdCat;
    private String tags;
    int count = 0;

    private ArrayList<CategoryBase> categoryBaseArrayList;
    private ArrayList<CategorySecond> categorySecondArrayList;
    private ArrayList<CategoryThird> categoryThirdArrayList;

    private ArrayList<String>tagsName;
    private ArrayList<TagsObject> tagsObjectArrayList;
    private ArrayList<TagsObject> tagsSelectedArrayList;
    ArrayAdapter<TagsObject> tagsObjectArrayAdapter;

    //Loaders
    private static final int LOADER_FIRST_CATEGORY = 10101;
    private static final int LOADER_SECOND_CATEGORY = 10102;
    private static final int LOADER_THIRD_CATEGORY = 10103;
    private static final int LOADER_TAGS = 10104;

    private LoaderCallbacks<ArrayList<CategoryBase>> firstCategoryLoader;
    private LoaderCallbacks<ArrayList<CategorySecond>> secondCategoryLoader;
    private LoaderCallbacks<ArrayList<CategoryThird>> thirdCategoryLoader;
    private LoaderCallbacks<ArrayList<TagsObject>> tagsObjectLoader;

    private ListView listCategoriesBase,listCategoriesSecond,listCategoriesThird;


    public BasicInfoBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_info_business, container, false);

        progressDialog = new ProgressDialog(getActivity());
        edtCompanyTitle =  view.findViewById(R.id.edtCompanyTitle);
        mltAutoKeyWords = (MultiAutoCompleteTextView) view.findViewById(R.id.mltAutoKeyWords_business);

        cancel_tag= view.findViewById(R.id.cancel_tag);

        txtCatFirst =  view.findViewById(R.id.txtCatFirst);
        txtCatSecond = view.findViewById(R.id.txtCatSecond);
        txtCatThird = view.findViewById(R.id.txtCatThird);

        categoryBaseArrayList = new ArrayList<>();
        categorySecondArrayList = new ArrayList<>();
        categoryThirdArrayList = new ArrayList<>();

        tagsName = new ArrayList<>();
        tagsSelectedArrayList = new ArrayList<>();
        tagsObjectArrayList = new ArrayList<>();



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
                                //getLoaderManager().initLoader(LOADER_SECOND_CATEGORY, null, secondCategoryLoader);
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                },1800);
            }
        });
        firstCategoryLoader = new LoaderCallbacks<ArrayList<CategoryBase>>(){
            @Override
            public Loader<ArrayList<CategoryBase>> onCreateLoader(int i, Bundle bundle) {
                LoaderFirstCategory loaderFirstCategory = new LoaderFirstCategory(getContext(), "http://serv.kesbokar.com.au/jil.0.1/v1/category?parent_id=0&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
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
        secondCategoryLoader = new LoaderCallbacks<ArrayList<CategorySecond>>() {
            @NonNull
            @Override
            public Loader<ArrayList<CategorySecond>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderCategorySecond loaderCategorySecond = new LoaderCategorySecond(getContext(), "http://serv.kesbokar.com.au/jil.0.1/v1/category?parent_id=" + parent_id + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
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
        thirdCategoryLoader = new LoaderCallbacks<ArrayList<CategoryThird>>() {
            @NonNull
            @Override
            public Loader<ArrayList<CategoryThird>> onCreateLoader(int id, @Nullable Bundle args) {
                LoaderCategoriesThird loaderCategoriesThird = new LoaderCategoriesThird(getContext(), "http://serv.kesbokar.com.au/jil.0.1/v1/category?parent_id=" + parent_id + "&api_token=FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
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
                LoaderGetTags loaderGetTags = new LoaderGetTags(getActivity(), tags, tagsName, "http://serv.kesbokar.com.au/jil.0.1/v1/tags/dd");
                return loaderGetTags;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<ArrayList<TagsObject>> loader, ArrayList<TagsObject> data) {
                if(data!=null){
                    tagsObjectArrayList.clear();
                    tagsSelectedArrayList.clear();
                    Toast.makeText(getActivity(), ""+tagsSelectedArrayList.size(), Toast.LENGTH_SHORT).show();
                    tagsObjectArrayList = data;
                    tagsObjectArrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,tagsObjectArrayList);
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
                                StringTokenizer stringTokenizer = new StringTokenizer(tags,"");
                                while(stringTokenizer.hasMoreTokens()){
                                    tagsName.add(stringTokenizer.nextToken());
                                }
                                txtCatThird.setText(categoryThird.getTitle());
                                Log.i("Parent id", parent_id);
                                Log.i("tags", "onItemClick: "+tags + "**********"+tagsName);
                                txtCatThird.setEnabled(false);
                                getLoaderManager().initLoader(LOADER_TAGS, null, tagsObjectLoader);
                                dialog.dismiss();

                            }
                        });
                        dialog.show();
                    }
                },1800);
            }
        });

        return view;

    }
}




