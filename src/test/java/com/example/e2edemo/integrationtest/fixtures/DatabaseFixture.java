package com.example.e2edemo.integrationtest.fixtures;

import com.example.e2edemo.repository.PersonRepository;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class DatabaseFixture {
    private final PersonRepository personRepository;


    public DatabaseFixture(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    void reset() {
        this.personRepository.deleteAll();
    }

    public void shouldHavePeopleStored() {
        assertThat(this.personRepository.count()).as("number of rows").isNotZero();
    }
}
