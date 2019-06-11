package com.example.kesbokar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    int flag=0;
    EditText edtLoginId, edtLoginPass;
    String loginId, loginPass, full_name, email, image, phone_no;
    private static final int LOADER_LOGIN_ID = 35;
    private LoaderManager.LoaderCallbacks<LoginInfo> login_info_loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtLoginId = findViewById(R.id.edtLoginId);
        edtLoginPass = findViewById(R.id.edtLoginPass);
        loginId = "";
        loginPass = "";
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.kesbokar));
            //If true set flag =1
        }
        Button logbut=findViewById(R.id.logbut);
        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginId = edtLoginId.getText().toString();
                loginPass = edtLoginPass.getText().toString();
                getLoaderManager().restartLoader(LOADER_LOGIN_ID,null,login_info_loader);
            }
        });

        login_info_loader = new LoaderManager.LoaderCallbacks<LoginInfo>() {
            @Override
            public Loader<LoginInfo> onCreateLoader(int i, Bundle bundle) {
                LoaderLogin loaderLogin = new LoaderLogin(Login.this,loginId,loginPass,"http://serv.kesbokar.com.au/jil.0.1/auth/login");
                return loaderLogin;
            }

            @Override
            public void onLoadFinished(Loader<LoginInfo> loader, LoginInfo loginInfos) {
                if(loginInfos!=null) {
                    flag = 1;
                    full_name = loginInfos.getFullName();
                    email = loginInfos.getEmail_id();
                    image = loginInfos.getImage();
                    phone_no = loginInfos.getPhone_no();
                    Log.i("Login_data", loginInfos + "");
                }else{
                    flag=0;
                }
                Log.i("FlagValue",flag + "");
            }

            @Override
            public void onLoaderReset(Loader<LoginInfo> loader) {

            }
        };
        getLoaderManager().initLoader(LOADER_LOGIN_ID,null,login_info_loader);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Login.this,Navigation.class);
        intent.putExtra("Flag", flag);
        startActivity(intent);
        finish();
    }
}
