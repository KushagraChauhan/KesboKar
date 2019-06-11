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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button send=(Button)findViewById(R.id.send);
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
        startActivity(intent);
        finish();
    }
}
