package com.example.e2edemo.test.samples;

import java.security.SecureRandom;

public class Random {
    private static final SecureRandom RAND = new SecureRandom();

    public static int aRandomIndexFor(final int limit) {
        return (int) Math.abs(aRandomNumber() % (long) limit);
    }

    public static long aRandomNumber() {
        return RAND.nextLong();
    }
}
