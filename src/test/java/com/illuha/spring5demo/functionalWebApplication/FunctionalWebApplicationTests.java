package com.illuha.spring5demo.functionalWebApplication;

import com.illuha.spring5demo.functionalWebApplication.FunctionalWebApplication;
import com.illuha.spring5demo.functionalWebApplication.dto.Hello;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class FunctionalWebApplicationTests {

    private final WebTestClient webTestClient =
            WebTestClient
                    .bindToRouterFunction(
                            FunctionalWebApplication.getRouter())
                    .build();

    @Test
    public void indexPage_WhenRequested_SaysHello() {
        webTestClient.get().uri("/").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class)
                .isEqualTo("Hello");
    }

    @Test
    public void jsonPage_WhenRequested_SaysHello() {
        webTestClient.get().uri("/json").exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(Hello.class)
                .isEqualTo(new Hello("world"));
    }
}
