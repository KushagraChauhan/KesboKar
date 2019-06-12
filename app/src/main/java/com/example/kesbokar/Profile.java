package com.example.kesbokar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    TextView tvName, tvEmail, tvPhone, tvCreatedAt, tvUpdatedAt;
    ImageView ivImage;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
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
        id=extras.getInt("id");
        created=extras.getString("create");
        updated=extras.getString("update");
        flag=extras.getInt("Flag");
        initialiseIds();
        tvName.setText(full_name);
        tvEmail.setText(email);
        tvPhone.setText(phone_no);
        tvCreatedAt.setText(created);
        tvUpdatedAt.setText(updated);
        Picasso.with(Profile.this).load("https://www.kesbokar.com.au/uploads/profile/"+image).into(ivImage);
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
}

