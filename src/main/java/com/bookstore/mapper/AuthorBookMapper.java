package com.bookstore.mapper;

import com.bookstore.dto.AuthorBookDto;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthorBookMapper {

    public Author mapFromAuthor(AuthorBookDto authorBookDto) {
        Author author = new Author();
        author.setFirstName(authorBookDto.authorFirstName());
        author.setLastName(authorBookDto.authorLastName());
        return author;
    }

    public Book mapFromBook(AuthorBookDto authorBookDto) {
        Book book = new Book();
        book.setName(authorBookDto.name());
        book.setYear(authorBookDto.year());
        book.setPages(authorBookDto.pages());
        return book;
    }
}
