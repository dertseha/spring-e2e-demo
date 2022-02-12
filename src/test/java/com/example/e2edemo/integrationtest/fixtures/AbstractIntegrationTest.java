package com.example.e2edemo.integrationtest.fixtures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    private WebServerFixture webServer;

    @Autowired
    private WebClientFixture webClient;

    @Autowired
    private DatabaseFixture database;

    @BeforeEach
    public void setupFixtures() {
        this.webServer.setServerPort(this.serverPort);
    }

    @AfterEach
    public void resetFixtures() {
        this.database.reset();
    }

    protected WebClientFixture webClient() {
        return this.webClient;
    }

    protected DatabaseFixture database() {
        return this.database;
    }
}
