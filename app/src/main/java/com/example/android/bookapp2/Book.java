package com.example.android.bookapp2;

import static android.R.attr.author;

/**
 * {@Book} represents an earthquake event.
 */
public class Book {

    /** Name of the author event */
    public final String authors;

    /** Book Title */
    public final String title;

    /**
     * Constructs a new {@link Book}.
     *
     * @param eventAuthor is the name of the author
     * @param eventBookTitle is the name of the book title
     */
    public Book(String eventAuthor, String eventBookTitle) {
        authors = eventAuthor;
        title = eventBookTitle;
    }

}
