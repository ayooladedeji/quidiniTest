package com.quidinitest.permissions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.quidinitest.R;

/**
 * Created by ayoola on 05/09/2016.
 */
public class Permissions {

    public static final int REQUEST_INTERNET_PERMISSION = 1;


    public static void requestPermission(final Context context, String message, final String permission, final int requestCode) {

        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setCancelable(false);
            alertDialog.setTitle(context.getResources().getString(R.string.title_warning));
            alertDialog.setMessage(message);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getResources().getString(R.string.OK_text), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, requestCode);
                }
            });
            alertDialog.show();

        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, requestCode);

        }
    }

    public static boolean checkPermission(Context context, String permission){
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }






}
