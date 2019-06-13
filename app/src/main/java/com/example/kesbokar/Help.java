package com.example.kesbokar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Help extends AppCompatActivity {
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button send=(Button)findViewById(R.id.send);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        flag = extras.getInt("Flag");
        full_name=extras.getString("Name");
        email=extras.getString("mail");
        image=extras.getString("image");
        phone_no=extras.getString("phone");
        id=extras.getInt("id");
        created=extras.getString("create");
        updated=extras.getString("update");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=((EditText)findViewById(R.id.email)).getText().toString();
                String message=((EditText)findViewById(R.id.mess)).getText().toString();
                Intent mail=new Intent(Intent.ACTION_SENDTO);
                mail.setType("text/plain");
                //mail.putExtra(Intent.EXTRA_EMAIL,new String[]{"ashubansal.ashishbansal@gmail.com"});
                mail.setData(Uri.parse("mailto:ashubansal.ashishbansal@gmail.com"));
                mail.putExtra(Intent.EXTRA_SUBJECT, email);
                mail.putExtra(Intent.EXTRA_TEXT, message);
                //mail.setType("message/rfc822");
                mail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mail);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Help.this,Navigation.class);
        intent.putExtra("Flag", flag);
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
}
