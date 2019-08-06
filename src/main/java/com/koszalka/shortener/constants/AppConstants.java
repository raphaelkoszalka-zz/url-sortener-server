package com.koszalka.shortener.constants;

import lombok.Getter;

@Getter
public enum AppConstants {

    ALREADY_EXISTS("exists"),
    SAVED("saved"),
    BAD_REQUEST("bad_request"),
    LOCALHOST("http://localhost:8080/v1/");

    private final String value;

    AppConstants(String value) {
        this.value = value;
    }

}
