package com.quidinitest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quidinitest.R;
import com.quidinitest.httpsRequests.ProfileImageRequestClient;
import com.quidinitest.models.Customer;
import com.quidinitest.utilities.TimeUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private List<Customer> customerList = new ArrayList<>();
    private Context mContext;

    public CustomerListAdapter(List<Customer> customerList, Context context){
        sortCustomerList(customerList);
        this.customerList = customerList;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout customerRow;
        ImageView profileImage;
        TextView name;
        TextView expectedTime;


        public ViewHolder(View itemView) {
            super(itemView);
            customerRow = (RelativeLayout) itemView.findViewById(R.id.customer_row_layout);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            name = (TextView) itemView.findViewById(R.id.name);
            expectedTime = (TextView) itemView.findViewById(R.id.expectedTimeVal);

        }

    }

    @Override
    public CustomerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomerListAdapter.ViewHolder holder, int position) {
        try {
            holder.name.setText(customerList.get(position).getName());
            long expectedTimeInMillis = TimeUtils.convertTimeStampToMilli(customerList.get(position).getExpectedTime());
            String expectedTimeHumanReadable = TimeUtils.convertTimeToClockFormat(expectedTimeInMillis);

            String expectedTimeText = String.format(Locale.getDefault(), "%s %s",
                    mContext.getResources().getString(R.string.expected_time), expectedTimeHumanReadable);
            holder.expectedTime.setText(expectedTimeText);
            ProfileImageRequestClient.loadImageView(holder.profileImage, customerList.get(position).getEmailAddress(), mContext);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void update(List<Customer> customers){
        customerList.clear();
        customerList.addAll(customers);
        notifyDataSetChanged();
    }

    public void sortCustomerList(List<Customer> customers){
        Collections.sort(customerList, new Comparator<Customer>() {
            @Override public int compare(Customer c1, Customer c2) {
                int r = 0;
                try {
                    r = TimeUtils.convertTimeStampToMilli(c1.getExpectedTime()).compareTo(TimeUtils.convertTimeStampToMilli(c2.getExpectedTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return r;
            }

        });
    }


}
