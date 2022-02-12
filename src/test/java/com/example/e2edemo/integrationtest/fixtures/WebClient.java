package com.example.e2edemo.integrationtest.fixtures;

import com.example.e2edemo.dto.NewPersonDto;
import com.example.e2edemo.dto.PersonDto;
import org.assertj.core.api.SoftAssertions;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class WebClient {
    private final WebServer webServer;

    public WebClient(final WebServer webServer) {
        this.webServer = webServer;
    }

    public interface BodyAsserter<Type> {
        void assertFor(SoftAssertions softly, Type body);
    }

    public <Type> void shouldGetBodyFor(final String path, final Class<Type> typeClass, final BodyAsserter<Type> asserter) {
        final URI uri = this.webServer.getBaseUri().resolve(path);
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<Void> request = new HttpEntity<>(null, headers);
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Type> result = restTemplate.exchange(uri, HttpMethod.GET, request, typeClass);
        assertThat(result.getStatusCode().series()).isEqualTo(HttpStatus.Series.SUCCESSFUL);
        final SoftAssertions softly = new SoftAssertions();
        asserter.assertFor(softly, result.getBody());
        softly.assertAll();
    }

    public long creates(final NewPersonDto dto) {
        final URI uri = this.webServer.getBaseUri().resolve("person");
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        final HttpEntity<NewPersonDto> request = new HttpEntity<>(dto, headers);
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PersonDto> result = restTemplate.exchange(uri, HttpMethod.POST, request, PersonDto.class);
        assertThat(result.getStatusCode().series()).isEqualTo(HttpStatus.Series.SUCCESSFUL);
        return result.getBody().getId();
    }

    public void shouldRetrievePersonThat(final long id, final BodyAsserter<PersonDto> asserter) {
        final URI uri = this.webServer.getBaseUri().resolve("person/" + id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        final HttpEntity<Void> request = new HttpEntity<>(null, headers);
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PersonDto> result = restTemplate.exchange(uri, HttpMethod.GET, request, PersonDto.class);
        assertThat(result.getStatusCode().series()).isEqualTo(HttpStatus.Series.SUCCESSFUL);
        final SoftAssertions softly = new SoftAssertions();
        asserter.assertFor(softly, result.getBody());
        softly.assertAll();
    }
}
