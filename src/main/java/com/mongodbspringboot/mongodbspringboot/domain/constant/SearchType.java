package com.mongodbspringboot.mongodbspringboot.domain.constant;

import lombok.Getter;

public enum SearchType {
    GRADE("grade"),
    EMAIL("email"),
    HOBBY("hobby");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
