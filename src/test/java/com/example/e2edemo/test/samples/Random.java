package com.example.e2edemo.test.samples;

public class Random {
    private final static java.util.Random rand = new java.util.Random();

    public static <Type> Type entryOf(final Type[] entries) {
        return entries[rand.nextInt(entries.length)];
    }
}
