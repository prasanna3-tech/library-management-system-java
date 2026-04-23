package menus;

import java.util.Scanner;
import models.Book;
import models.Student;
import services.BookService;
import services.BorrowService;
import services.StudentService;

public class StudentMenu {

    Scanner sc = new Scanner(System.in);

    BookService bookService;
    BorrowService borrowService;
    StudentService studentService;

    public StudentMenu(BookService bookService,
                       BorrowService borrowService,
                       StudentService studentService) {

        this.bookService = bookService;
        this.borrowService = borrowService;
        this.studentService = studentService;
    }

    public void showMenu(Student student) {

        while(true) {

            System.out.println("\n===== Student Menu =====");
            System.out.println("1. Search Book");
            System.out.println("2. View Borrowed Books");
            System.out.println("3. View Due Dates");
            System.out.println("4. View Overdue Books");
            System.out.println("5. View Fine Amount");
            System.out.println("6. Reserve Book");
            System.out.println("7. Renew Book");
            System.out.println("8. View Borrowing History");
            System.out.println("9. View Profile");
            System.out.println("10. Logout");

              int choice;

            while(true) {

                try {
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                }
                catch(Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine();
                }
            }

            switch(choice) {

                case 1: {
                    int bookId = 0;

                    while(true) {
                        try {
                            System.out.print("Enter Book ID: ");
                            bookId = sc.nextInt();
                            sc.nextLine();

                            if(bookId <= 0) {
                                System.out.println("Book ID must be greater than 0");
                                continue;
                            }

                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Invalid input. Please enter a valid Book ID.");
                            sc.nextLine();
                        }
                    }

                    Book book = bookService.searchBookById(bookId);

                    if(book != null) {
                        book.displayBookDetails();
                    }
                    else {
                        System.out.println("Book not found");
                    }
                    break;
                }

                case 2:
                    borrowService.displayBorrowedBooks(student.studentId);
                    break;

                case 3:
                    borrowService.displayDueDates(student.studentId);
                    break;

                case 4: {
                    System.out.print("Enter Today's Date (dd/MM/yyyy): ");
                    String todayDate = sc.nextLine();

                    borrowService.displayOverdueBooks(student.studentId, todayDate);
                    break;
                }

                case 5: {
                    System.out.print("Enter Today's Date (dd/MM/yyyy): ");
                    String currentDate = sc.nextLine();

                    double fine = borrowService.calculatePendingFine(
                            student.studentId,
                            currentDate
                    );

                    System.out.println("Pending Fine: Rs. " + fine);
                    break;
                }

                case 6: {
                    int reserveBookId = 0;

                    while(true) {
                        try {
                            System.out.print("Enter Book ID: ");
                            reserveBookId = sc.nextInt();
                            sc.nextLine();

                            if(reserveBookId <= 0) {
                                System.out.println("Book ID must be greater than 0");
                                continue;
                            }

                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Invalid input. Please enter a valid Book ID.");
                            sc.nextLine();
                        }
                    }

                    System.out.print("Enter Reservation Date (dd/MM/yyyy): ");
                    String reservationDate = sc.nextLine();

                    borrowService.reserveBook(
                            student.studentId,
                            reserveBookId,
                            reservationDate
                    );
                    break;
                }

                case 7: {
                    int renewBookId = 0;

                    while(true) {
                        try {
                            System.out.print("Enter Book ID: ");
                            renewBookId = sc.nextInt();
                            sc.nextLine();

                            if(renewBookId <= 0) {
                                System.out.println("Book ID must be greater than 0");
                                continue;
                            }

                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Invalid input. Please enter a valid Book ID.");
                            sc.nextLine();
                        }
                    }

                    System.out.print("Enter New Due Date (dd/MM/yyyy): ");
                    String newDueDate = sc.nextLine();

                    borrowService.renewBook(
                            student.studentId,
                            renewBookId,
                            newDueDate
                    );
                    break;
                }

                case 8:
                    borrowService.displayBorrowHistory(student.studentId);
                    break;

                case 9:
                    student.displayProfile();
                    break;

                case 10:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice");
}
        }
    }
}