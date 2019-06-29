package com.kesbokar.kesbokar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesBaseAdapter extends BaseAdapter {
    private ArrayList<CategoryBase> categoryBaseArrayList;
    private Context context;
    LayoutInflater layoutInflater;
    TextView txtCategoriesBase;
    public CategoriesBaseAdapter(Context context, ArrayList<CategoryBase> categoryBaseArrayList){
        this.context = context;
        this.categoryBaseArrayList = categoryBaseArrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categoryBaseArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoryBaseArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.category_base_dialog_adapter, null);
        txtCategoriesBase = view.findViewById(R.id.txtCategoriesBase);
        txtCategoriesBase.setText(categoryBaseArrayList.get(i).getTitle());
        return view;
    }
}
