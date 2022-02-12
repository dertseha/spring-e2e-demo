package com.example.e2edemo.integrationtest.sample;

import com.example.e2edemo.integrationtest.fixtures.AbstractIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class SampleIntegrationTest extends AbstractIntegrationTest {
    @Test
    public void canQueryHello() {
        webClient().shouldReceiveBodyFor("sample/hello", String.class,
                (SoftAssertions softly, String body) -> softly.assertThat(body).isEqualTo("hello"));
    }
}
