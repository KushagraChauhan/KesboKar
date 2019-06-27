package com.example.kesbokar;


import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import android.content.Context;

import android.app.Dialog;

import android.content.DialogInterface;

import androidx.loader.content.Loader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicInfoFragment extends Fragment {

    private TextView txtCatFirst, txtCatSecond, txtCatThird;
    String condition1, condition2;
    RadioGroup rgProductCondition, rgProductSelection;
    private Context context;
    private String parent_id = "";
    private static final int LOADER_FIRST_CATEGORY = 101;
    private static final int LOADER_SECOND_CATEGORY = 102;
    private static final int LOADER_THIRD_CATEGORY = 103;

    private LoaderManager.LoaderCallbacks<ArrayList<CategoryBase>> firstCategoryLoader;
    private LoaderManager.LoaderCallbacks<ArrayList<CategorySecond>> secondCategoryLoader;
    private LoaderManager.LoaderCallbacks<ArrayList<CategoryThird>> thirdCategoryLoader;

    private ListView listCategoriesBase;
    private ArrayList<CategoryBase> categoryBaseArrayList;
    private ArrayList<CategorySecond> categorySecondArrayList;
    private ArrayList<CategoryThird> categoryThirdArrayList;

    EditText edtProductTitle;
    public BasicInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_info, container, false);


        rgProductCondition = view.findViewById(R.id.rgProductCondition);
        rgProductSelection = view.findViewById(R.id.rgProductSelection);
        context = view.getContext();
        // Categories
        final String[] value = new String[3];
        txtCatFirst =(TextView) view.findViewById(R.id.txtCatFirst);
        txtCatSecond =(TextView) view.findViewById(R.id.txtCatSecond);
        txtCatThird =(TextView) view.findViewById(R.id.txtCatThird);

        txtCatSecond.setVisibility(View.GONE);
        txtCatThird.setVisibility(View.GONE);

        final String[] firstValueArray = {"API1", "API1", "API1"};
        final String[] secondValueArray;
        final String[] thirdValueArray;
        categoryBaseArrayList = new ArrayList<>();
        categorySecondArrayList = new ArrayList<>();
        categoryThirdArrayList = new ArrayList<>();

        txtCatFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(LOADER_FIRST_CATEGORY, null, firstCategoryLoader);
                final Dialog dialog = new Dialog(getActivity());
                View view1 = getActivity().getLayoutInflater().inflate(R.layout.category_base_dialog, null);
                dialog.setContentView(view1);
                listCategoriesBase = view1.findViewById(R.id.categoriesBaseListView);
                CategoriesBaseAdapter categoriesBaseAdapter = new CategoriesBaseAdapter(getActivity(), categoryBaseArrayList);
                listCategoriesBase.setAdapter(categoriesBaseAdapter);
                listCategoriesBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        CategoryBase categoryBase = (CategoryBase) adapterView.getAdapter().getItem(i);
                        parent_id = categoryBase.getId();
                        txtCatFirst.setText(categoryBase.getTitle());
                        getLoaderManager().initLoader(LOADER_SECOND_CATEGORY, null, secondCategoryLoader);
                        Log.i("Parent id", parent_id);
                        txtCatSecond.setVisibility(View.VISIBLE);
                        txtCatFirst.setEnabled(false);
                        dialog.dismiss();
                    }
                });
                dialog.show();
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
                    Log.i("API SECOND", data.get(0).getTitle());
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<ArrayList<CategorySecond>> loader) {

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

            }
        };

        getLoaderManager().initLoader(LOADER_FIRST_CATEGORY, null, firstCategoryLoader);
        txtCatSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(LOADER_SECOND_CATEGORY, null, secondCategoryLoader);
                final Dialog dialog = new Dialog(getActivity());
                View view1 = getActivity().getLayoutInflater().inflate(R.layout.category_base_dialog, null);
                dialog.setContentView(view1);
                listCategoriesBase = view1.findViewById(R.id.categoriesBaseListView);
                CategoriesSecondAdapter categoriesSecondAdapter = new CategoriesSecondAdapter(getActivity(), categorySecondArrayList);
                listCategoriesBase.setAdapter(categoriesSecondAdapter);
                listCategoriesBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        CategorySecond categorySecond = (CategorySecond) adapterView.getAdapter().getItem(i);
                        parent_id = categorySecond.getId();
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
        });

        thirdValueArray = new String[]{"API3", "API3", "API3"};



        txtCatThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(LOADER_THIRD_CATEGORY, null, thirdCategoryLoader);
                final Dialog dialog = new Dialog(getActivity());
                View view1 = getActivity().getLayoutInflater().inflate(R.layout.category_base_dialog, null);
                dialog.setContentView(view1);
                listCategoriesBase = view1.findViewById(R.id.categoriesBaseListView);
                CategoriesThirdAdapter categoriesThirdAdapter = new CategoriesThirdAdapter(getActivity(), categoryThirdArrayList);
                listCategoriesBase.setAdapter(categoriesThirdAdapter);
                listCategoriesBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        CategoryThird categoryThird = (CategoryThird) adapterView.getAdapter().getItem(i);
                        parent_id = categoryThird.getId();
                        txtCatThird.setText(categoryThird.getTitle());
                        Log.i("Parent id", parent_id);
                        txtCatThird.setEnabled(false);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });



        rgProductCondition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbNew:condition1="New";
                        break;

                    case R.id.rbUsed:condition1="used";
                }
            }
        });


        rgProductSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.rbSell:condition2="rbSell";
                        break;

                    case R.id.rbRent:condition2="rbRent";
                }
            }
        });




//        edtProductTitle = view.findViewById(R.id.etProductTitle);
//        String data = "fail";
//        if(savedInstanceState!=null)
//            data = getArguments().getString("EDITTEXT_VALUE");
//        Log.i("DATA KI MA KI CHOOT", data);
//        edtProductTitle.setText(data);
        return view;
    }
}
