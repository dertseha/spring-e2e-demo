package com.example.e2edemo.test.samples;

import com.example.e2edemo.dto.NewPersonDto;

public class Person {
    public static String someName() {
        return Random.entryOf(new String[]{"Alice", "Bob", "Carol", "Dave"});
    }

    public static NewPersonDto someNewPerson() {

        return NewPersonDto.builder().name(someName()).build();
    }
}
