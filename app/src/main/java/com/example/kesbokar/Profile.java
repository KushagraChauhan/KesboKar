package com.example.kesbokar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    TextView tvName, tvEmail, tvPhone, tvCreatedAt, tvUpdatedAt;
    ImageView ivImage;
    String loginId, loginPass, full_name, email, image, phone_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        full_name=extras.getString("Name");
        email=extras.getString("mail");
        image=extras.getString("image");
        phone_no=extras.getString("phone");
        initialiseIds();
        tvName.setText(full_name);
        tvEmail.setText(email);
        tvPhone.setText(phone_no);
        Picasso.with(Profile.this).load("https://www.kesbokar.com.au/uploads/profile/"+image).into(ivImage);
    }

    private void initialiseIds(){
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        ivImage = findViewById(R.id.ivImage);
        tvPhone = findViewById(R.id.tvPhone);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        tvUpdatedAt = findViewById(R.id.tvUpdatedAt);
    }
}

