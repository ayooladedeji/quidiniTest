package com.quidinitest.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quidinitest.R;
import com.quidinitest.adapters.CustomerListAdapter;
import com.quidinitest.dialogs.ProgressDialogFactory;
import com.quidinitest.httpsRequests.CustomerRequestClient;
import com.quidinitest.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String mTAG = "MainActivity";
    private RecyclerView mCustomerListRecyclerView;
    private CustomerListAdapter mCustomerListAdapter;
    private List<Customer> mCustomers;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = ProgressDialogFactory.createProgressDialog(this, null, getResources().getString(R.string.please_wait), false);

        mCustomerListRecyclerView = (RecyclerView) findViewById(R.id.customerListRecyclerView);
        setUpCustomerListAdapter();
        scheduleRefresh();
    }

    private void setUpCustomerListAdapter() {
        progressDialog.show();
        final Handler setUpCustomerListAdapterHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                mCustomerListRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mCustomerListAdapter = new CustomerListAdapter(mCustomers, getApplicationContext());
                mCustomerListRecyclerView.setHasFixedSize(true);
                mCustomerListRecyclerView.setAdapter(mCustomerListAdapter);
                progressDialog.dismiss();
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                mCustomers = new ArrayList<>();
                mCustomers = CustomerRequestClient.getCustomers(getApplicationContext());
                setUpCustomerListAdapterHandler.sendEmptyMessage(0);
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


}
