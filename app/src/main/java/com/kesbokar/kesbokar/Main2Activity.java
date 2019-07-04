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
        myTabLayout =  findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);

        if(!car_yes_or_no) {
            myTabsAccessorAdapter = new TabsAccessorAdapter(getSupportFragmentManager(),myViewPager,myTabLayout);
            myViewPager.setAdapter(myTabsAccessorAdapter);
            myTabLayout.removeTabAt(3);
            myTabLayout.removeTabAt(4);
        }else{
//            Fragment fragment = new CarDetailsFragment();
//            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
//            tr.add(frag_id,fragment).commit();
            secondTabsAccessorAdapter = new SecondTabsAccessorAdapter(getSupportFragmentManager(),myViewPager,myTabLayout);
            myViewPager.setAdapter(secondTabsAccessorAdapter);
            View tab6= Objects.requireNonNull(myTabLayout.getTabAt(6)).view;
            tab6.setEnabled(false);
            myTabLayout.removeTabAt(3);
            myTabLayout.removeTabAt(4);
        }


        myViewPager.setPagingEnabled(false);
        View tab1= Objects.requireNonNull(myTabLayout.getTabAt(1)).view;
        View tab2= Objects.requireNonNull(myTabLayout.getTabAt(2)).view;
        View tab3= Objects.requireNonNull(myTabLayout.getTabAt(3)).view;
//        View tab4= Objects.requireNonNull(myTabLayout.getTabAt(4)).view;
//        View tab5= Objects.requireNonNull(myTabLayout.getTabAt(5)).view;

        tab1.setEnabled(false);
        tab2.setEnabled(false);
        tab3.setEnabled(false);
//        tab4.setEnabled(false);
//        tab5.setEnabled(false);


    }

}
