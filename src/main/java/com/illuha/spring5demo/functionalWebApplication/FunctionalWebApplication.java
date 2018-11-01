package com.illuha.spring5demo.functionalWebApplication;

import com.illuha.spring5demo.functionalWebApplication.dto.Hello;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class FunctionalWebApplication {
    public static void main(String[] args) throws InterruptedException {
        HandlerFunction hello = request -> ServerResponse.ok().body(fromObject("Hello"));


        HttpHandler httpHandler = RouterFunctions.toHttpHandler(getRouter());

        HttpServer
                .create("localhost", 9999)
                .newHandler(new ReactorHttpHandlerAdapter(httpHandler))
                .block();

        Thread.currentThread().join();	}

    static RouterFunction getRouter() {
        HandlerFunction hello = request -> ServerResponse.ok().body(fromObject("Hello"));

        return
                route(
                        GET("/"), hello)
                        .andRoute(
                                GET("/json"), req ->
                                        ServerResponse.ok()
                                                .contentType(APPLICATION_JSON)
                                                .body(fromObject(new Hello("world"))));
    }

}
