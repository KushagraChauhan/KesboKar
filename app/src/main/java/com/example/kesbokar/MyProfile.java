package com.example.kesbokar;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyProfile extends AppCompatActivity {
    TextView tvName, tvEmail, tvPhone, tvCreatedAt, tvUpdatedAt;
    ImageView ivImage;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_my_profile);
        initialiseIds();
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

