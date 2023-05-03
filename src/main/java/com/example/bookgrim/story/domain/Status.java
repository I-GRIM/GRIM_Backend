package com.example.bookgrim.story.domain;

import lombok.Getter;

@Getter
public enum Status {
    COMPLETED("COMPLETED"),
    INCOMPLETED("INCOMPLETED");


    private final String value;

    Status(String value) {
        this.value = value;
    }
}
