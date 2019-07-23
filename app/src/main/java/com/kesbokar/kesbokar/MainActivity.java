package com.kesbokar.kesbokar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    public static int SPLASH_SCREEN_TIME=1500;
    String versionUpdated,version_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (isNetworkAvailable()) {
            try {
                versionChecker VersionChecker = new versionChecker();
                versionUpdated = VersionChecker.execute().get().toString();
                Log.i("version code is", versionUpdated);


                PackageInfo packageInfo = null;
                try {
                    packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                int version_code = packageInfo.versionCode;
                version_name = packageInfo.versionName;
                Log.i("updated version code", String.valueOf(version_code) + "  " + version_name);
                if (!version_name.equals(versionUpdated)) {
                    String packageName = getApplicationContext().getPackageName();//
                    UpdateMeeDialog updateMeeDialog = new UpdateMeeDialog();
                    updateMeeDialog.showDialogAddRoute(MainActivity.this, packageName);
                    Toast.makeText(getApplicationContext(), "New Update is Available", Toast.LENGTH_LONG).show();
                } else {
                    setContentView(R.layout.activity_main);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(MainActivity.this, Navigation.class);
                            startActivity(i);
                            finish();
                        }
                    }, SPLASH_SCREEN_TIME);
                }
            } catch (Exception e) {
                e.getStackTrace();
                Log.i("Error", e.toString());
            }
        }
        else {
            setContentView(R.layout.activity_main);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, Navigation.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_SCREEN_TIME);
        }

    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
