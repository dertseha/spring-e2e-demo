package com.example.e2edemo.integrationtest.fixtures;

import org.junit.jupiter.api.Assertions;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class WebServer {

    private int serverPort;

    void setServerPort(final int value) {
        this.serverPort = value;
    }

    public URI getBaseUri() {
        assertThat(this.serverPort).as("server port").isNotZero();
        final String baseUrl = "http://localhost:" + this.serverPort + "/";
        try {
            return new URI(baseUrl);
        } catch (final URISyntaxException ex) {
            Assertions.fail("URI not valid", ex);
            return null;
        }
    }
}
