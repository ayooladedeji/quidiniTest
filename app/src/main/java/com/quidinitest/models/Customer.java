package com.quidinitest.models;

public class Customer {

    private String mName;
    private String mExpectedTime;
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

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setEmailAddress(String mEmailAddress) {
        this.mEmailAddress = mEmailAddress;
    }
}
