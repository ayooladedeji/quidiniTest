package com.quidinitest.dialogs;

import android.app.ProgressDialog;
import android.content.Context;


public class ProgressDialogFactory {

    /**
     * Creates a progress dialog
     * @param context Context of the current activity
     * @param title Title of the progress dialog, this can be null
     * @param message Message to be displayed on the progress dialog, this can be null
     * @param cancelable Determains whether the dialog can be dismissed by the user
     * @return A ProgressDialog object
     */

    public static ProgressDialog createProgressDialog(Context context, String title, String message, boolean cancelable){

        ProgressDialog pDialog = new ProgressDialog(context);
        if(title != null)
            pDialog.setTitle(title);
        if(message != null)
            pDialog.setMessage(message);
        pDialog.setCancelable(cancelable);


        return pDialog;
    }

}

