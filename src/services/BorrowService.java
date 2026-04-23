package services;

import java.util.ArrayList;
import models.Book;
import models.BorrowRecord;
import models.Reservation;
import models.Student;
import models.SystemSettings;
import utils.DateUtil;

public class BorrowService {

    ArrayList<Book> books;
    ArrayList<Student> students;
    ArrayList<BorrowRecord> borrowRecords;
    ArrayList<Reservation> reservations;
    SystemSettings settings;

    public BorrowService(ArrayList<Book> books,
                         ArrayList<Student> students,
                         ArrayList<BorrowRecord> borrowRecords,
                         ArrayList<Reservation> reservations,
                         SystemSettings settings) {

        this.books = books;
        this.students = students;
        this.borrowRecords = borrowRecords;
        this.reservations = reservations;
        this.settings = settings;
    }

    public void issueBook(int studentId, int bookId,
                          String issueDate, String dueDate) {

        Student student = null;
        Book book = null;

        for(Student s : students) {
            if(s.studentId == studentId) {
                student = s;
                break;
            }
        }

        for(Book b : books) {
            if(b.bookId == bookId) {
                book = b;
                break;
            }
        }

        if(student == null) {
            System.out.println("Student not found");
            return;
        }

        if(book == null) {
            System.out.println("Book not found");
            return;
        }

        if(book.quantity <= 0) {
            System.out.println("Book is not available");
            return;
        }

        int borrowedCount = 0;

        for(BorrowRecord record : borrowRecords) {

            if(record.studentId == studentId && !record.returned) {
                borrowedCount++;

                if(record.bookId == bookId) {
                    System.out.println("Student already borrowed this book");
                    return;
                }
            }
        }

        if(borrowedCount >= settings.maxBooksAllowed) {
            System.out.println("Student reached maximum borrow limit");
            return;
        }

        if(student.fineAmount > 0) {
            System.out.println("Student has pending fine");
            return;
        }

        book.quantity--;

        BorrowRecord record = new BorrowRecord(
                studentId,
                bookId,
                issueDate,
                dueDate
        );

        borrowRecords.add(record);

        System.out.println("Book issued successfully");
    }

    

        public void displayBorrowedBooks(int studentId) {

    boolean found = false;

    for(BorrowRecord record : borrowRecords) {

        if(record.studentId == studentId && !record.returned) {

            for(Book book : books) {

                if(book.bookId == record.bookId) {
                    System.out.println("Book ID: " + book.bookId);
                    System.out.println("Title: " + book.title);
                    System.out.println("Author: " + book.author);
                    System.out.println();

                    found = true;
                }
            }
        }
    }

    if(!found) {
        System.out.println("No borrowed books found");
    }
}

public void displayDueDates(int studentId) {

    boolean found = false;

    for(BorrowRecord record : borrowRecords) {

        if(record.studentId == studentId && !record.returned) {

            for(Book book : books) {

                if(book.bookId == record.bookId) {
                    System.out.println("Book: " + book.title);
                    System.out.println("Due Date: " + record.dueDate);
                    System.out.println();

                    found = true;
                }
            }
        }
    }

    if(!found) {
        System.out.println("No borrowed books found");
    }
}

public void displayOverdueBooks(int studentId, String todayDate) {

    boolean found = false;

    for(BorrowRecord record : borrowRecords) {

        if(record.studentId == studentId &&
           !record.returned &&
           DateUtil.getLateDays(record.dueDate, todayDate) > 0) {

            for(Book book : books) {

                if(book.bookId == record.bookId) {
                    int lateDays = DateUtil.getLateDays(record.dueDate, todayDate);
                    double fine = lateDays * settings.finePerDay;

                    System.out.println("Book: " + book.title);
                    System.out.println("Due Date: " + record.dueDate);
                    System.out.println("Late Days: " + lateDays);
                    System.out.println("Current Fine: Rs. " + fine);
                    System.out.println();

                    found = true;
                }
            }
        }
    }

    if(!found) {
        System.out.println("No overdue books found");
    }
}

public double calculatePendingFine(int studentId, String todayDate) {

    double totalFine = 0;

    for(BorrowRecord record : borrowRecords) {

        if(record.studentId == studentId && !record.returned) {

            int lateDays = DateUtil.getLateDays(record.dueDate, todayDate);

            if(lateDays > 0) {
                totalFine += lateDays * settings.finePerDay;
            }
        }
    }

    return totalFine;
}

  public void payFine(int studentId, double amount) {

    for(Student student : students) {

        if(student.studentId == studentId) {
            if(amount > student.fineAmount) {
               System.out.println("Amount is greater than pending fine");
              return;
            }

             student.fineAmount -= amount;

            System.out.println("Remaining Fine: Rs. " + student.fineAmount);
            return;
        }
    }
}

public void displayBorrowHistory(int studentId) {

    boolean found = false;

    for(BorrowRecord record : borrowRecords) {

        if(record.studentId == studentId) {

            System.out.println("Book ID: " + record.bookId);
            System.out.println("Issue Date: " + record.issueDate);
            System.out.println("Due Date: " + record.dueDate);
            System.out.println("Return Date: " + record.returnDate);
            System.out.println("Returned: " + record.returned);
            System.out.println("Fine: Rs. " + record.fine);
            System.out.println();

            found = true;
        }
    }

    if(!found) {
        System.out.println("No borrow history found");
    }
}
    public void returnBook(int studentId, int bookId, String returnDate) {

        for(BorrowRecord record : borrowRecords) {

            if(record.studentId == studentId &&
               record.bookId == bookId &&
               !record.returned) {

                record.returned = true;
                record.returnDate = returnDate;

                int lateDays = DateUtil.getLateDays(record.dueDate, returnDate);

                if(lateDays > 0) {
                    record.fine = lateDays * settings.finePerDay;

                    for(Student student : students) {
                        if(student.studentId == studentId) {
                            student.fineAmount += record.fine;
                            break;
                        }
                    }
                }

                for(Book book : books) {
                    if(book.bookId == bookId) {
                        book.quantity++;
                        break;
                    }
                }

                for(Reservation reservation : reservations) {
                    if(reservation.bookId == bookId) {
                        reservation.notified = true;
                    }
                }

                System.out.println("Book returned successfully");

                if(record.fine > 0) {
                    System.out.println("Late Days: " + lateDays);
                    System.out.println("Fine: Rs. " + record.fine);
                }

                return;
            }
        }

        System.out.println("Borrow record not found");
    }

    public void renewBook(int studentId, int bookId, String newDueDate) {

        for(BorrowRecord record : borrowRecords) {

            if(record.studentId == studentId &&
               record.bookId == bookId &&
               !record.returned) {

                if(record.renewCount >= settings.maxRenewCount) {
                    System.out.println("Maximum renew limit reached");
                    return;
                }

                for(Reservation reservation : reservations) {
                    if(reservation.bookId == bookId) {
                        System.out.println("Cannot renew because another student reserved this book");
                        return;
                    }
                }

                record.dueDate = newDueDate;
                record.renewCount++;

                System.out.println("Book renewed successfully");
                System.out.println("New Due Date: " + record.dueDate);
                System.out.println("Renew Count: " + record.renewCount);
                return;
            }
        }

        System.out.println("Borrow record not found");
    }

    public void reserveBook(int studentId, int bookId,
                            String reservationDate) {

        Book book = null;

        for(Book b : books) {
            if(b.bookId == bookId) {
                book = b;
                break;
            }
        }

        if(book == null) {
            System.out.println("Book not found");
            return;
        }

        if(book.quantity > 0) {
            System.out.println("Book is already available. No need to reserve.");
            return;
        }

        for(Reservation reservation : reservations) {
            if(reservation.studentId == studentId &&
               reservation.bookId == bookId) {

                System.out.println("Student already reserved this book");
                return;
            }
        }

        Reservation reservation = new Reservation(
                studentId,
                bookId,
                reservationDate
        );

        reservations.add(reservation);

        System.out.println("Book reserved successfully");
    }
}