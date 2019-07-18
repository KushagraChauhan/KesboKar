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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvName, tvEmail, tvPhone, tvCreatedAt, tvUpdatedAt;
    ImageView ivImage;
    Button change_password,edit_profile;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getData();
        initialiseIds();
        change_password=findViewById(R.id.change_password);
        tvName.setText(full_name);
        tvEmail.setText(email);
        tvPhone.setText(phone_no);
        tvCreatedAt.setText(created);
        tvUpdatedAt.setText(updated);
        edit_profile=findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
        Picasso.with(Profile.this).load("https://www.kesbokar.com.au/uploads/profile/"+image).into(ivImage);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,ChangePassword.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0, 0);
            }
        });
    }
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        Intent intent = new Intent(Profile.this,LoginData.class );
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
        finish();;

    }

    private void initialiseIds(){
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        ivImage = findViewById(R.id.ivImage);
        tvPhone = findViewById(R.id.tvPhone);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        tvUpdatedAt = findViewById(R.id.tvUpdatedAt);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int Id = menuItem.getItemId();

        if (Id == R.id.nav_home) {
            // Handle the camera action
        } else if (Id == R.id.dashboard) {
            Intent intent=new Intent(Profile.this,LoginData.class);
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

        } else if (Id == R.id.profile) {

        } else if (Id == R.id.manage_help_desk){
            Intent intent=new Intent(Profile.this,ManageHelpDeskActivity.class);
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
            Intent intent=new Intent(Profile.this,ProfileBusinessListing.class);
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

        }else if (Id == R.id.market_lg_page) {
            Intent intent=new Intent(Profile.this,ProfileMarket.class);
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
            Intent intent = new Intent(Profile.this, inbox_business.class);
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

        } else if (Id == R.id.market_in) {
            Intent intent = new Intent(Profile.this, inbox_market.class);
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

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void getData()
    {
        SharedPreferences loginData=getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");

    }

}

