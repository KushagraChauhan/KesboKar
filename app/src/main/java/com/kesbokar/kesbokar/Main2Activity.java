package com.kesbokar.kesbokar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
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
    String make_id,model_id,year,variant_id,vehicle_id,colour,airconditioning,registered,registration_state,registration_number,registration_expiry,name_title,product_condition,product_section,category_id1,price1,phone1,address1,description1,status1;
    int edit1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getData();

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
        }else{
//            Fragment fragment = new CarDetailsFragment();
//            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
//            tr.add(frag_id,fragment).commit();
            secondTabsAccessorAdapter = new SecondTabsAccessorAdapter(getSupportFragmentManager(),myViewPager,myTabLayout);
            myViewPager.setAdapter(secondTabsAccessorAdapter);
            View tab5= Objects.requireNonNull(myTabLayout.getTabAt(5)).view;
            if (edit1==0) {
                tab5.setEnabled(false);
            }
        }


        myViewPager.setPagingEnabled(false);
        View tab1= Objects.requireNonNull(myTabLayout.getTabAt(1)).view;
        View tab2= Objects.requireNonNull(myTabLayout.getTabAt(2)).view;
        View tab3= Objects.requireNonNull(myTabLayout.getTabAt(3)).view;
        View tab4= Objects.requireNonNull(myTabLayout.getTabAt(4)).view;
        View tab5= Objects.requireNonNull(myTabLayout.getTabAt(5)).view;
        if (edit1==0) {
            tab1.setEnabled(false);
            tab2.setEnabled(false);
            tab3.setEnabled(false);
            tab4.setEnabled(false);
        }
      tab5.setEnabled(false);


    }
    public void getData()
    {
        SharedPreferences business_edit=getSharedPreferences("market_edit",0);
        edit1=business_edit.getInt("edit",0);
        if (edit1==1)
        {
            make_id=business_edit.getString("make_id","");
            model_id=business_edit.getString("model_id","");
            year=business_edit.getString("year","");
            variant_id=business_edit.getString("variant_id","");
            vehicle_id=business_edit.getString("vehicle_id","");
            colour=business_edit.getString("colour","");
            airconditioning=business_edit.getString("airconditioning","");
            registered=business_edit.getString("registered","");
            registration_state=business_edit.getString("registration_state","");
            registration_number=business_edit.getString("registration_number","");
            registration_expiry=business_edit.getString("registration_expiry","");
        }
        name_title=business_edit.getString("name","");
        product_condition=business_edit.getString("product_condition","");
        product_section=business_edit.getString("product_section","");
        category_id1=business_edit.getString("category_id","");
        price1=business_edit.getString("price","");
        phone1=business_edit.getString("phone","");
        address1=business_edit.getString("address","");
        description1=business_edit.getString("description","");
        status1=business_edit.getString("status","");
    }

}
