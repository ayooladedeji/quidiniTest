package com.quidinitest;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.quidinitest.httpsRequests.CustomerRequestClient;
import com.quidinitest.httpsRequests.ProfileImageRequestClient;
import com.quidinitest.models.Customer;
import com.quidinitest.utilities.TimeUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TimeUtilsTest {

    private Context context;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testConvertingTimeStampToMilli() throws Exception {
        long millis = TimeUtils.convertTimeStampToMilli("2016-12-08T12:16:25.692Z");
        assertEquals(1481199385000L, millis);
    }

    @Test
    public void testConvertStartTimeToClockFormat()throws Exception{
        assertEquals("12:00 p.m.", TimeUtils.convertTimeToClockFormat(1479124800000L));
    }
}
