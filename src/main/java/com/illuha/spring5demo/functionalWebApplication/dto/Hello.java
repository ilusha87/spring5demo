package com.illuha.spring5demo.functionalWebApplication.dto;

import lombok.Data;

@Data
public class Hello {
    private final String name;

    public Hello(String name) {
        this.name = name;
    }
}
