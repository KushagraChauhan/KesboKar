package com.kesbokar.kesbokar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class Main3BusinessActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private ThirdTabAccessorAdapter ThirdTabAccessorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_business);

       mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar_2);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Kesbokar");

        myViewPager = (ViewPager) findViewById(R.id.main_tabs_pager_2);

        ThirdTabAccessorAdapter = new ThirdTabAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(ThirdTabAccessorAdapter);

        myTabLayout = (TabLayout) findViewById(R.id.main_tabs_2);
        myTabLayout.setupWithViewPager(myViewPager);

    }

}

