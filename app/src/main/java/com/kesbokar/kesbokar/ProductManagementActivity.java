package com.kesbokar.kesbokar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

import org.jsoup.select.Evaluator;

public class ProductManagementActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;

    private EditText editText;
    private Button button, postACarAdd;

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getData();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        editText = findViewById(R.id.edtProductTitle);
        button = findViewById(R.id.btnGo);
        postACarAdd = findViewById(R.id.btnPostACarAdd);
        relativeLayout = findViewById(R.id.productTitle);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String data = editText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("EDITTEXT_VALUE", data);
                BasicInfoFragment basicInfoFragment = new BasicInfoFragment();
                basicInfoFragment.setArguments(bundle);*/
                Intent intent = new Intent(ProductManagementActivity.this, Main2Activity.class);
                SharedPreferences sharedPreferences=getSharedPreferences("market_edit",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("edit",0);
                editor.commit();
                intent.putExtra("CAR_YES_OR_NO", false);
                startActivity(intent);
            }
        });


        postACarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagementActivity.this, Main2Activity.class);
                intent.putExtra("CAR_YES_OR_NO", true);
                SharedPreferences sharedPreferences=getSharedPreferences("market_edit",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("edit",0);
                editor.commit();
                startActivity(intent);


            }
        });

        editText.addTextChangedListener(inputTextWatcher);


    }

    private TextWatcher inputTextWatcher = new TextWatcher()
    {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            String inputEditText = editText.getText().toString();
            button.setEnabled(!inputEditText.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }
    };


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int Id = item.getItemId();

        if (Id == R.id.nav_home) {
            // Handle the camera action
        } else if (Id == R.id.dashboard) {
            Intent intent = new Intent(ProductManagementActivity.this, LoginData.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        }else if (Id==R.id.nav_share){
            Intent intent = new Intent(ProductManagementActivity.this, Main3BusinessActivity.class);
            startActivity(intent);
        }
        else if (Id == R.id.profile) {
            Intent intent = new Intent(ProductManagementActivity.this, Profile.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        } else if (Id == R.id.business_lg_page) {
            Intent intent=new Intent(ProductManagementActivity.this,ProfileBusinessListing.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        } else if (Id == R.id.market_lg_page) {
            Intent intent=new Intent(ProductManagementActivity.this,ProfileMarket.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();
        } else if (Id == R.id.business_in) {
            Intent intent=new Intent(ProductManagementActivity.this,inbox_business.class);
            startActivity(intent);
            finish();

        } else if (Id == R.id.market_in) {
            Intent intent=new Intent(ProductManagementActivity.this,inbox_market.class);
            intent.putExtra("Flag",flag);
            intent.putExtra("Name",full_name);
            intent.putExtra("mail",email);
            intent.putExtra("image",image);
            intent.putExtra("phone",phone_no);
            intent.putExtra("create",created);
            intent.putExtra("update",updated);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);
            finish();

        }else if(Id == R.id.manage_help_desk){
            Intent intent = new Intent(ProductManagementActivity.this, ManageHelpDeskActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getData() {
        SharedPreferences loginData = getSharedPreferences("data", 0);
        flag = loginData.getInt("Flag", 0);
        full_name = loginData.getString("Name", "");
        email = loginData.getString("mail", "");
        image = loginData.getString("image", "");
        phone_no = loginData.getString("phone", "");
        id = loginData.getInt("id", 0);
        created = loginData.getString("create", "");
        updated = loginData.getString("update", "");

    }
}
