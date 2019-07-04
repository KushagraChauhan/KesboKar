package com.kesbokar.kesbokar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class Main2Activity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CustomViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabsAccessorAdapter myTabsAccessorAdapter;
    private SecondTabsAccessorAdapter secondTabsAccessorAdapter;
    private static final int frag_id = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Kesbokar");
        Bundle extras = getIntent().getExtras();
        boolean car_yes_or_no = extras.getBoolean("CAR_YES_OR_NO");

        myViewPager = (CustomViewPager) findViewById(R.id.main_tabs_pager);
        if(!car_yes_or_no) {
            myTabsAccessorAdapter = new TabsAccessorAdapter(getSupportFragmentManager(),myViewPager);
            myViewPager.setAdapter(myTabsAccessorAdapter);
        }else{
//            Fragment fragment = new CarDetailsFragment();
//            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
//            tr.add(frag_id,fragment).commit();
            secondTabsAccessorAdapter = new SecondTabsAccessorAdapter(getSupportFragmentManager(),myViewPager);
            myViewPager.setAdapter(secondTabsAccessorAdapter);
        }
        myTabLayout =  findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);
        myViewPager.setPagingEnabled(false);
        View tab= Objects.requireNonNull(myTabLayout.getTabAt(4)).view;
        tab.setEnabled(false);

    }

}
