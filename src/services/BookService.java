package services;

import java.util.ArrayList;
import models.Book;
import models.BorrowRecord;

public class BookService {

    ArrayList<Book> books;
    ArrayList<BorrowRecord> borrowRecords;

    public BookService(ArrayList<Book> books,
                       ArrayList<BorrowRecord> borrowRecords) {

        this.books = books;
        this.borrowRecords = borrowRecords;
    }

    public void addBook(Book newBook) {

        for(Book book : books) {
            if(book.isbn.equals(newBook.isbn)) {
                book.quantity += newBook.quantity;

                System.out.println("Book already exists. Quantity updated successfully");
                return;
            }
        }

        books.add(newBook);
        System.out.println("Book added successfully");
    }

    public Book searchBookById(int bookId) {

        for(Book book : books) {
            if(book.bookId == bookId) {
                return book;
            }
        }

        return null;
    }

    public ArrayList<Book> searchBooksByKeyword(String keyword) {

        ArrayList<Book> matchedBooks = new ArrayList<>();

        for(Book book : books) {
            if(book.title.toLowerCase().contains(keyword.toLowerCase()) ||
               book.author.toLowerCase().contains(keyword.toLowerCase()) ||
               book.category.toLowerCase().contains(keyword.toLowerCase())) {

                matchedBooks.add(book);
            }
        }

        return matchedBooks;
    }

    public void removeBook(int bookId) {

        for(int i = 0; i < books.size(); i++) {

            Book book = books.get(i);

            if(book.bookId == bookId) {

                boolean isBorrowed = false;

                for(BorrowRecord record : borrowRecords) {
                    if(record.bookId == bookId && !record.returned) {
                        isBorrowed = true;
                        break;
                    }
                }

                if(isBorrowed) {
                    System.out.println("Cannot remove book because it is currently borrowed");
                    return;
                }

                books.remove(i);
                System.out.println("Book removed successfully");
                return;
            }
        }

        System.out.println("Book not found");
    }

  

    public void updateBook(int bookId, String newTitle, String newAuthor,
                           String newIsbn, String newCategory, int newQuantity) {

        Book book = searchBookById(bookId);

        if(book == null) {
            System.out.println("Book not found");
            return;
        }

        book.title = newTitle;
        book.author = newAuthor;
        book.isbn = newIsbn;
        book.category = newCategory;
        book.quantity = newQuantity;

        System.out.println("Book updated successfully");
    }

    public void displayAllBooks() {

        if(books.isEmpty()) {
            System.out.println("No books found");
            return;
        }

        for(Book book : books) {
            book.displayBookDetails();
            System.out.println();
        }
    }
}