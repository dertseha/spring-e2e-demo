package com.example.e2edemo.integrationtest.person;

import com.example.e2edemo.integrationtest.fixtures.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import static com.example.e2edemo.integrationtest.samples.Person.someNewPerson;

public class PersonIntegrationTest extends AbstractIntegrationTest {
    @Test
    public void canStoreANewPerson() {
        webClient().creates(someNewPerson());
        database().shouldHavePeopleStored();
    }

    @Test
    public void storedPersonCanBeRetrieved() {
        final var newPerson = someNewPerson();
        final var personId = webClient().creates(newPerson);
        webClient().shouldRetrievePersonThat(personId, (softly, person) -> {
            softly.assertThat(person.getId()).as("id").isEqualTo(personId);
            softly.assertThat(person.getName()).as("name").isEqualTo(newPerson.getName());
        });
    }
}
