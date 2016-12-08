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

import java.util.ArrayList;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private List<Customer> customerList = new ArrayList<>();
    private Context mContext;

    public CustomerListAdapter(List<Customer> customerList, Context context){
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
        holder.name.setText(customerList.get(position).getName());
        holder.expectedTime.setText(customerList.get(position).getExpectedTime());
        ProfileImageRequestClient.loadImageView(holder.profileImage, customerList.get(position).getEmailAddress(), mContext);
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void update(ArrayList<Customer> customers){
        customerList.clear();
        customerList.addAll(customers);
        notifyDataSetChanged();
    }


}
