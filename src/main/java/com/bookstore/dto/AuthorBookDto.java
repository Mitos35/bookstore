package com.bookstore.dto;

public record AuthorBookDto(
        String authorFirstName,
        String authorLastName,
        String name,
        Integer year,
        Integer pages) {
}
