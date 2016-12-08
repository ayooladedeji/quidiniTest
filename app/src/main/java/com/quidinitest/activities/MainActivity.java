package com.quidinitest.activities;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.quidinitest.R;
import com.quidinitest.adapters.CustomerListAdapter;
import com.quidinitest.httpsRequests.CustomerRequestClient;
import com.quidinitest.models.Customer;
import com.quidinitest.permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String mTAG = "MainActivity";
    private RecyclerView mCustomerListRecyclerView;
    private CustomerListAdapter mCustomerListAdapter;
    private List<Customer> mCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Permissions.checkPermission(this, Manifest.permission.INTERNET))
            Permissions.requestPermission(this,
                    this.getResources().getString(R.string.alert_message_write_permission),
                    Manifest.permission.INTERNET,
                    Permissions.REQUEST_INTERNET_PERMISSION);

        mCustomerListRecyclerView = (RecyclerView) findViewById(R.id.customerListRecyclerView);
        setUpCustomerListAdapter();
        scheduleRefresh();
    }

    private void setUpCustomerListAdapter() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    mCustomers = new ArrayList<>();
                    mCustomers = CustomerRequestClient.getCustomers();
                    setUpCustomerListAdapterHandler.sendEmptyMessage(0);
                } catch(Exception e){
                    Log.d(mTAG, e.getMessage());
                }
            }
        }).start();

    }

    public void scheduleRefresh() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                setUpCustomerListAdapter();
                handler.postDelayed(this, 30000);
            }
        }, 30000);
    }



    final Handler setUpCustomerListAdapterHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            mCustomerListRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mCustomerListAdapter = new CustomerListAdapter(mCustomers, getApplicationContext());
            mCustomerListRecyclerView.setHasFixedSize(true);
            mCustomerListRecyclerView.setAdapter(mCustomerListAdapter);
        }
    };



}
