package com.book.bookshelf.config;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static final String openlibrary_url ="https://openlibrary.org";
    public static final String get_book_by_isbn_url = openlibrary_url + "/isbn/";
}
