package com.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private Integer year;

    private Integer pages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIgnoreProperties({"books"})
    private Author author;
}
