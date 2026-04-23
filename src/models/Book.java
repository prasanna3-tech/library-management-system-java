package models;

public class Book {

    public int bookId;
    public String title;
    public String author;
    public String isbn;
    public String category;
    public int quantity;

    public Book(int bookId, String title, String author,
                String isbn, String category, int quantity) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.quantity = quantity;
    }

    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Category: " + category);
        System.out.println("Quantity: " + quantity);
    }
}