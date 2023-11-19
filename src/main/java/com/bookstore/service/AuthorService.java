package com.bookstore.service;

import com.bookstore.dto.AuthorBookDto;
import com.bookstore.entity.Author;
import com.bookstore.mapper.AuthorBookMapper;
import com.bookstore.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private static final int LENGTH_TITLE_NAME_FOR_CHECK = 10;
    private static final String SORT_NAME_BOOK_ASC = "ASC";
    private static final String SORT_NAME_BOOK_DESC = "DESC";

    private final AuthorRepository authorRepository;
    private final AuthorBookMapper authorBookMapper;

    @Transactional
    public void addAuthorAndBook(AuthorBookDto authorBookDto) {
        Author author = authorBookMapper.mapFromAuthor(authorBookDto);
        var book = authorBookMapper.mapFromBook(authorBookDto);

        if (book.getName().length() > LENGTH_TITLE_NAME_FOR_CHECK) {
            throw new IllegalArgumentException("Long title of the book");
        }

        author.addBook(book);

        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public List<Author> getAllAuthors(String sortBy) {
        List<Author> authors;

        if (SORT_NAME_BOOK_ASC.equalsIgnoreCase(sortBy)) {
            authors = authorRepository.findAll(Sort.by(Sort.Direction.ASC, "books.name"));
        } else if (SORT_NAME_BOOK_DESC.equalsIgnoreCase(sortBy)){
            authors = authorRepository.findAll(Sort.by(Sort.Direction.DESC, "books.name"));
        } else {
            authors = authorRepository.findAll();
        }

        return authors;
    }
}
