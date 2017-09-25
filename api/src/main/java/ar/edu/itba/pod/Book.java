package ar.edu.itba.pod;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

    private String isbn;
    private String name;
    private LocalDate published;
    private Author author;

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", published=" + published +
                ", author=" + author +
                '}';
    }

    public Book(String isbn, String name, LocalDate published, Author author) {
        this.isbn = isbn;
        this.name = name;
        this.published = published;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
