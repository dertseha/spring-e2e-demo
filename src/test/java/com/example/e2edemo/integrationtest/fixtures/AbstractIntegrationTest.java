package com.example.e2edemo.integrationtest.fixtures;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    private WebServer webServer;

    @Autowired
    private WebClient webClient;

    @BeforeEach
    public void setupFixtures() {
        this.webServer.setServerPort(this.serverPort);
    }

    protected WebClient webClient() {
        return this.webClient;
    }
}
