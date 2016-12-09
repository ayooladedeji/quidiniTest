package com.quidinitest.httpsRequests;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.quidinitest.R;
import com.quidinitest.adapters.CustomerListAdapter;
import com.quidinitest.models.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CustomerRequestClient {

    private static final String mTAG = "CustomerRequestClient";
    private static final String endPoint = "https://app.qudini.com/api/queue/gj9fs?username=codetest1&password=codetest100";

    public static List<Customer> getCustomers(Context context) {
        return sendRequestForCustomers(context);
    }

    public static List<Customer> sendRequestForCustomers(final Context context) {
        List<Customer> customersList = new ArrayList<>();

        HttpURLConnection connection;

        try {
            String basicAuth = "Basic " + new String(Base64.encode("codetest1:codetest100".getBytes(), Base64.NO_WRAP));
            connection = (HttpURLConnection) new URL(endPoint).openConnection();
            connection.setRequestProperty("Authorization", basicAuth);

            connection.setConnectTimeout(10000);


            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                String responseString = response.toString();
                customersList = readResponse(responseString);
            }


        } catch (Exception e) {
            Log.d(mTAG, e.getMessage());
            final Handler sendErrorToast = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message message) {
                    Toast.makeText(context, context.getResources().getString(R.string.unableToLoadCustomers), Toast.LENGTH_LONG).show();

                }
            };
            sendErrorToast.sendEmptyMessage(0);

        }

        return customersList;
    }

    public static List<Customer> readResponse(String response) throws JSONException {
        List<Customer> customersList = new ArrayList<>();

        JSONArray customersToday = new JSONObject(response).getJSONObject("queueData").getJSONObject("queue").getJSONArray("customersToday");
        int customerCount = customersToday.length();

        for(int i = 0 ; i < customerCount - 1 ; i++){
            Customer customer = new Customer();
            customer.setExpectedTime(customersToday.getJSONObject(i).getString("expectedTime"));
            customer.setName(customersToday.getJSONObject(i).getJSONObject("customer").getString("firstName"));
            customer.setEmailAddress(customersToday.getJSONObject(i).getJSONObject("customer").getString("emailAddress"));
            customersList.add(customer);
        }

        Log.d(mTAG, "SLASH");
        return customersList;
    }

}
