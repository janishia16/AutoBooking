package com.cognizant.utility;

import java.time.Instant;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class Generator {

    public static String generateCustomerId() {
        String randomString = RandomStringUtils.randomAlphanumeric(4);
        long timestamp = Instant.now().toEpochMilli();
        String customerId = (randomString + (timestamp % 100)).toUpperCase();

        return customerId;
    }

//    UUID (Universally Unique Identifier) is a 128-bit value used to uniquely
//    identify information in a system.
    public static String generatePaymentId() {
        return UUID.randomUUID().toString();
    }
}
