package services;

import java.util.ArrayList;

import models.Book;
import models.BorrowRecord;
import models.Librarian;
import models.Student;
import utils.DateUtil;

public class ReportService {

    ArrayList<Book> books;
    ArrayList<Student> students;
    ArrayList<Librarian> librarians;
    ArrayList<BorrowRecord> borrowRecords;

    public ReportService(ArrayList<Book> books,
                         ArrayList<Student> students,
                         ArrayList<Librarian> librarians,
                         ArrayList<BorrowRecord> borrowRecords) {

        this.books = books;
        this.students = students;
        this.librarians = librarians;
        this.borrowRecords = borrowRecords;
    }

    public void displayLibraryReport(String todayDate) {

        int totalBooks = books.size();
        int totalStudents = students.size();
        int totalLibrarians = librarians.size();
        int totalBorrowedBooks = 0;
        int totalOverdueBooks = 0;
        double totalFineCollected = 0;

        for(BorrowRecord record : borrowRecords) {

            if(!record.returned) {
                totalBorrowedBooks++;
            }

            if(!record.returned &&
               DateUtil.getLateDays(record.dueDate, todayDate) > 0) {

                totalOverdueBooks++;
            }

            if(record.finePaid) {
                totalFineCollected += record.fine;
            }
        }

        System.out.println("Total Books: " + totalBooks);
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Total Librarians: " + totalLibrarians);
        System.out.println("Total Borrowed Books: " + totalBorrowedBooks);
        System.out.println("Total Overdue Books: " + totalOverdueBooks);
        System.out.println("Total Fine Collected: Rs. " + totalFineCollected);
    }

    public void displayAllBorrowRecords() {

        if(borrowRecords.isEmpty()) {
            System.out.println("No borrow records found");
            return;
        }

        for(BorrowRecord record : borrowRecords) {

            System.out.println("Student ID: " + record.studentId);
            System.out.println("Book ID: " + record.bookId);
            System.out.println("Issue Date: " + record.issueDate);
            System.out.println("Due Date: " + record.dueDate);
            System.out.println("Return Date: " + record.returnDate);
            System.out.println("Returned: " + record.returned);
            System.out.println("Fine: Rs. " + record.fine);
            System.out.println();
        }
    }

    public void searchBorrowRecordsByStudentId(int studentId) {

        boolean found = false;

        for(BorrowRecord record : borrowRecords) {

            if(record.studentId == studentId) {
                record.displayBorrowRecord();
                System.out.println();
                found = true;
            }
        }

        if(!found) {
            System.out.println("No records found for this student");
        }
    }

    public void searchBorrowRecordsByBookId(int bookId) {

        boolean found = false;

        for(BorrowRecord record : borrowRecords) {

            if(record.bookId == bookId) {
                record.displayBorrowRecord();
                System.out.println();
                found = true;
            }
        }

        if(!found) {
            System.out.println("No records found for this book");
        }
    }

    public void searchBorrowRecordsByStatus(boolean returned) {

        boolean found = false;

        for(BorrowRecord record : borrowRecords) {

            if(record.returned == returned) {
                record.displayBorrowRecord();
                System.out.println();
                found = true;
            }
        }

        if(!found) {
            System.out.println("No matching records found");
        }
    }
}