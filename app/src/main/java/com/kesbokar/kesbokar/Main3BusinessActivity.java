package com.kesbokar.kesbokar;

import android.content.SharedPreferences;
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

    private int edit1=0;

    private String name, registration_no, license_no, website, category_id, phone, address, description, latitude, longitude, email1,yellowpage_id,
            quote_message, short_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_business);
        getData();

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar_2);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Kesbokar");

        myViewPager = (CustomViewPager) findViewById(R.id.main_tabs_pager_2);
        myTabLayout = (TabLayout) findViewById(R.id.main_tabs_2);
        myTabLayout.setupWithViewPager(myViewPager);
        ThirdTabAccessorAdapter = new ThirdTabAccessorAdapter(getSupportFragmentManager(), myViewPager, myTabLayout);
        myViewPager.setAdapter(ThirdTabAccessorAdapter);


        myViewPager.setPagingEnabled(false);
        View tab1 = Objects.requireNonNull(myTabLayout.getTabAt(1)).view;
        View tab2 = Objects.requireNonNull(myTabLayout.getTabAt(2)).view;
        View tab3 = Objects.requireNonNull(myTabLayout.getTabAt(3)).view;
        View tab4 = Objects.requireNonNull(myTabLayout.getTabAt(4)).view;
        View tab5= Objects.requireNonNull(myTabLayout.getTabAt(5)).view;
        if (edit1==0) {
            tab1.setEnabled(false);
            tab2.setEnabled(false);
            tab3.setEnabled(false);
            tab4.setEnabled(false);
        }
        tab5.setEnabled(false);
    }

    public void getData() {
        SharedPreferences basicInfoBusiness = getSharedPreferences("business_edit", 0);
        edit1=basicInfoBusiness.getInt("edit", 0);
        if (edit1 == 1) {

            name = basicInfoBusiness.getString("name", "");
            registration_no = basicInfoBusiness.getString("registration_no", "");
            license_no = basicInfoBusiness.getString("license_no", "");
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
            yellowpage_id=basicInfoBusiness.getString("yellowpage_id","");

        }

    }
}
