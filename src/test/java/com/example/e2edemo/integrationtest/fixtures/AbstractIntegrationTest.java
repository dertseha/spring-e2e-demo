package com.example.e2edemo.integrationtest.fixtures;

import com.example.e2edemo.test.Steps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest implements Steps<AbstractIntegrationTest> {
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

    public WebClientFixture webClient() {
        return this.webClient;
    }

    public DatabaseFixture database() {
        return this.database;
    }
}
