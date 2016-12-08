package com.quidinitest.httpsRequests;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.security.MessageDigest;


public class ProfileImageRequestClient {

    private static final String mTAG = "ProfileImageRequest";
    private static final String URL = "http://www.gravatar.com/avatar/";

    public static void loadImageView(final ImageView imageView, final String email, final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Picasso.with(context)
                            .load(URL+emailToHash(email))
                            .into(imageView);
                } catch(Exception e){
                    Log.d(mTAG, e.getMessage());
                }
            }
        }).start();

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
