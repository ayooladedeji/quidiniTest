package com.quidinitest.httpsRequests;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.quidinitest.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.security.MessageDigest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ProfileImageRequestClient {

    private static final String mTAG = "ProfileImageRequest";
    private static final String URL = "http://www.gravatar.com/avatar/";


    public static void loadImageView(final ImageView imageView, final String email, final Context context) {

        Picasso.with(context).load(URL+emailToHash(email)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.i(mTAG, "The image was obtained correctly");
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.e(mTAG, "The image was not obtained");

                final Handler sendErrorToast = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message message) {
                        Toast.makeText(context, context.getResources().getString(R.string.unableToLoadProfileImages), Toast.LENGTH_LONG).show();

                    }
                };
                sendErrorToast.sendEmptyMessage(0);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.i(mTAG, "Getting ready to get the image");
            }
        });

    }

    public static String emailToHash(String email){
        final MessageDigest messageDigest;
        StringBuilder sb = new StringBuilder();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            byte[] digest = messageDigest.digest(email.getBytes("UTF-8"));
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (Exception e) {
            sb.setLength(0);
            sb.append(email);
        }
        return sb.toString();
    }


}
