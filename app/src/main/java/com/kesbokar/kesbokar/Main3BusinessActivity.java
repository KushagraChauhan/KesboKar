package com.kesbokar.kesbokar;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;


public class Main3BusinessActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CustomViewPager myViewPager;
    private TabLayout myTabLayout;
    private ThirdTabAccessorAdapter ThirdTabAccessorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_business);

       mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar_2);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Kesbokar");

        myViewPager = (CustomViewPager) findViewById(R.id.main_tabs_pager_2);

        ThirdTabAccessorAdapter = new ThirdTabAccessorAdapter(getSupportFragmentManager(),myViewPager,myTabLayout);
        myViewPager.setAdapter(ThirdTabAccessorAdapter);

        myTabLayout = (TabLayout) findViewById(R.id.main_tabs_2);
        myTabLayout.setupWithViewPager(myViewPager);

        myViewPager.setPagingEnabled(false);
        View tab1= Objects.requireNonNull(myTabLayout.getTabAt(1)).view;
        View tab2= Objects.requireNonNull(myTabLayout.getTabAt(2)).view;
        View tab3= Objects.requireNonNull(myTabLayout.getTabAt(3)).view;
        View tab4= Objects.requireNonNull(myTabLayout.getTabAt(4)).view;
//        View tab5= Objects.requireNonNull(myTabLayout.getTabAt(5)).view;

        tab1.setEnabled(false);
        tab2.setEnabled(false);
        tab3.setEnabled(false);
        tab4.setEnabled(false);
//        tab5.setEnabled(false);
    }

}

