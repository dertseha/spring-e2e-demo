package com.example.e2edemo.controller;

import com.example.e2edemo.dto.NewPersonDto;
import com.example.e2edemo.dto.PersonDto;
import com.example.e2edemo.entity.PersonEntity;
import com.example.e2edemo.repository.PersonRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "person")
public class PersonController {
    private final PersonRepository personRepository;

    PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto createNewPerson(@RequestBody final NewPersonDto newPerson) {
        final PersonEntity entity = PersonEntity.builder().name(newPerson.getName()).build();
        final var storedEntity = this.personRepository.save(entity);
        return PersonDto.builder().id(storedEntity.getId()).name(storedEntity.getName()).build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> getPerson(@PathVariable final long id) {
        final var storedEntity = this.personRepository.findById(id);
        return ResponseEntity.of(storedEntity.map(e -> PersonDto.builder().id(e.getId()).name(e.getName()).build()));
    }
}
