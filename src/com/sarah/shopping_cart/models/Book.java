package com.sarah.shopping_cart.models;

public class Book extends Item {

    private final String author;
    private final String bookFilePath;

    private Book(long id, String title, int price, String author, String bookFilePath) {
        super(id, title, price);
        this.author = author;
        this.bookFilePath = bookFilePath;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookFilePath() {
        return bookFilePath;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Item.Builder<Builder, Book> {

        private String author;
        private String bookFilePath;

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setBookFilePath(String bookFilePath) {
            this.bookFilePath = bookFilePath;
            return this;
        }

        @Override
        public Book build() {
            return new Book(id, name, price, author, bookFilePath);
        }
    }
}
