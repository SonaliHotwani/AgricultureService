package com.apple.agriculture.controller;

import com.apple.agriculture.domain.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StateControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnAllSortedStates() {
        ResponseEntity<List<State>> responseEntity = testRestTemplate.exchange(URI.create("http://localhost:" + port + "/states"), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        List<State> states = responseEntity.getBody();
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(Arrays.stream(State.values()).sorted().toList(), states);
    }
}