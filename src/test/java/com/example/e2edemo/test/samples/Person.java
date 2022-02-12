package com.example.e2edemo.test.samples;

import com.example.e2edemo.dto.NewPersonDto;

import static com.example.e2edemo.test.samples.Random.aRandomIndexFor;

public class Person {
    public static String aRandomName() {
        return someGivenName() + " Doe";
    }

    public static String someGivenName() {
        final String[] names = new String[]{"Alice", "Bob", "Carol", "David"};
        return names[aRandomIndexFor(names.length)];
    }

    public static NewPersonDto someNewPerson() {
        return NewPersonDto.builder().name(aRandomName()).build();
    }
}
