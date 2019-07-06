package com.kesbokar.kesbokar;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class UpdateMeeDialog {
    ActivityManager am;
    TextView rootName;
    Context context;
    Activity activity;
    Dialog dialog;
    String key1,schoolId;
    public void showDialogAddRoute(final Activity activity, final String packageName){
        context=activity;
        this.activity=activity;
        dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_update);
        am = (ActivityManager)activity.getSystemService(Context.ACTIVITY_SERVICE);

        Button update=(Button)dialog.findViewById(R.id.buttonUpdate);
        Button cancel=(Button) dialog.findViewById(R.id.remindlater);
        Log.i("package name",packageName);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.kesbokar.kesbokar"));
                context.startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Navigation.class);
                context.startActivity(intent);
                activity.finish();

            }
        });

        dialog.show();
    }
}
