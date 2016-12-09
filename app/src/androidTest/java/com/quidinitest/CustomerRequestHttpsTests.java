package com.quidinitest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;
import android.widget.Toast;

import com.quidinitest.httpsRequests.CustomerRequestClient;
import com.quidinitest.httpsRequests.ProfileImageRequestClient;
import com.quidinitest.models.Customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CustomerRequestHttpsTests {

    private Context context;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testGettingCustomerListFromEndPoint() throws Exception {
        List<Customer> customersList = CustomerRequestClient.getCustomers(context);
        assertTrue(!customersList.isEmpty());
        assertNotNull(customersList);
    }

    @Test
    public void testEmailToHash() throws Exception {
        String hashCode = ProfileImageRequestClient.emailToHash("ayooladedeji@live.com");
        assertEquals("35a7020447b341873bba6efa675ffc6a", hashCode);
    }
}
