package com.example.kesbokar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class Career extends AppCompatActivity {
    WebView career;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.kesbokar));
        }
        career=(WebView)findViewById(R.id.career);
        career.getSettings().setJavaScriptEnabled(true);
        career.loadUrl("https://www.kesbokar.com.au/career");
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(Career.this,Navigation.class);
        startActivity(intent);
        finish();
    }
}
