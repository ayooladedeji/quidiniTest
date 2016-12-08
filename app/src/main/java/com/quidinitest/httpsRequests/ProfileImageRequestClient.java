package com.quidinitest.httpsRequests;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.quidinitest.R;
import com.quidinitest.permissions.Permissions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.security.MessageDigest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ProfileImageRequestClient {

    private static final String mTAG = "ProfileImageRequest";
    private static final String URL = "http://www.gravatar.com/avatar/";

    //private static ExecutorService executorService = Executors.newFixedThreadPool(2);

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
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.i(mTAG, "Getting ready to get the image");
                //Here you should place a loading gif in the ImageView to
                //while image is being obtained.
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
