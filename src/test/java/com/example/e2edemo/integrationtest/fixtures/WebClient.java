package com.example.e2edemo.integrationtest.fixtures;

import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class WebClient {
    @Autowired
    private WebServer webServer;

    public interface BodyAsserter<Type> {
        void assertFor(SoftAssertions softly, Type body);
    }

    public <Type> void shouldReceiveBodyFor(final String path, final Class<Type> typeClass, final BodyAsserter<Type> asserter) {
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
}
