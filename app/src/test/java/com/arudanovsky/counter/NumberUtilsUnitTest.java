package com.arudanovsky.counter;

import com.arudanovsky.counter.common.NumberUtils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by arudanovskiy on 8/7/17.
 * Тесты для методов {@link com.arudanovsky.counter.common.NumberUtils}
 */

public class NumberUtilsUnitTest {

    @Test
    public void incrementTest() throws Exception {
        assertEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1), new BigDecimal(3)));
        assertEquals(new BigDecimal(0), NumberUtils.increment(new BigDecimal(0), new BigDecimal(0)));
        assertEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1.5), new BigDecimal(2.5)).setScale(0, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1.9999999999), new BigDecimal(2.0000000001)).setScale(0, RoundingMode.HALF_UP));
        assertNotEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1), new BigDecimal(2)));
        assertNotEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1), new BigDecimal(4)));
        assertEquals(new BigDecimal(1111111111.123456789), NumberUtils.increment(new BigDecimal(1111111111.123456789), new BigDecimal(0)));
    }

    @Test
    public void incrementWithMaxTest() throws Exception {
        assertEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1), new BigDecimal(3), new BigDecimal(4)));
        assertEquals(new BigDecimal(0), NumberUtils.increment(new BigDecimal(0), new BigDecimal(0), new BigDecimal(4)));
        assertEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1.5), new BigDecimal(2.5), new BigDecimal(4)).setScale(0, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1.9999999999), new BigDecimal(2.0000000001), new BigDecimal(4)).setScale(0, RoundingMode.HALF_UP));
        assertNotEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1), new BigDecimal(2), new BigDecimal(4)));
        assertNotEquals(new BigDecimal(4), NumberUtils.increment(new BigDecimal(1), new BigDecimal(4), new BigDecimal(4)));
        assertEquals(new BigDecimal(1111111111.123456789), NumberUtils.increment(new BigDecimal(1111111111.123456789), new BigDecimal(0), new BigDecimal(1111111113)));
        assertEquals(new BigDecimal(0), NumberUtils.increment(new BigDecimal(1), new BigDecimal(3), new BigDecimal(3)));
        assertEquals(new BigDecimal(0), NumberUtils.increment(new BigDecimal(0), new BigDecimal(0), new BigDecimal(4)));
        assertNotEquals(new BigDecimal(0), NumberUtils.increment(new BigDecimal(0), new BigDecimal(4), new BigDecimal(4)));
        assertEquals(new BigDecimal(0), NumberUtils.increment(new BigDecimal(1.5), new BigDecimal(2.5), new BigDecimal(3.9)).setScale(0, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal(0), NumberUtils.increment(new BigDecimal(1111111111.123456789), new BigDecimal(0), new BigDecimal(1111111111)));
    }
}
