package com.ipap.articlesservice.model;

public record Article(
        Integer id,
        String title,
        String body
) {
}
