package com.quidinitest.models;

import android.widget.ImageView;

public class Customer {

    private String mName;
    private String mExpectedTime;
    private ImageView mProfileImage;
    private String mEmailAddress;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getExpectedTime() {
        return mExpectedTime;
    }

    public void setExpectedTime(String mExpectedTime) {
        this.mExpectedTime = mExpectedTime;
    }

    public ImageView getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(ImageView mProfileImage) {
        this.mProfileImage = mProfileImage;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setEmailAddress(String mEmailAddress) {
        this.mEmailAddress = mEmailAddress;
    }
}
