package com.kesbokar.kesbokar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesThirdAdapter extends BaseAdapter {
    private ArrayList<CategoryThird> categoryThirdArrayList;
    private Context context;
    LayoutInflater layoutInflater;
    TextView txtCategoriesBase;
    public CategoriesThirdAdapter(Context context, ArrayList<CategoryThird> categoryThirdArrayList){
        this.context = context;
        this.categoryThirdArrayList = categoryThirdArrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categoryThirdArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoryThirdArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.category_base_dialog_adapter, null);
        txtCategoriesBase = view.findViewById(R.id.txtCategoriesBase);
        txtCategoriesBase.setText(categoryThirdArrayList.get(i).getTitle());
        return view;
    }
}
