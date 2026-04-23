package menus;

import java.util.Scanner;
import models.Book;
import models.Librarian;
import models.Student;
import services.BookService;
import services.BorrowService;
import services.StudentService;

public class LibrarianMenu {

    Scanner sc = new Scanner(System.in);

    BookService bookService;
    StudentService studentService;
    BorrowService borrowService;

    public LibrarianMenu(BookService bookService,
                         StudentService studentService,
                         BorrowService borrowService) {

        this.bookService = bookService;
        this.studentService = studentService;
        this.borrowService = borrowService;
    }

    public void showMenu(Librarian librarian) {

        while(true) {

            System.out.println("\n===== Librarian Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Update Book");
            System.out.println("4. Register Student");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Student Details");
            System.out.println("8. PayFine");
            System.out.println("9. Logout");

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
        int quantity = 0;

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
                System.out.println("Invalid Book ID");
                sc.nextLine();
            }
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        while(true) {
            try {
                System.out.print("Enter Quantity: ");
                quantity = sc.nextInt();
                sc.nextLine();

                if(quantity < 0) {
                    System.out.println("Quantity cannot be negative");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Quantity");
                sc.nextLine();
            }
        }

        Book newBook = new Book(
                bookId,
                title,
                author,
                isbn,
                category,
                quantity
        );

        bookService.addBook(newBook);
        break;
    }

    case 2: {
        int removeBookId = 0;

        while(true) {
            try {
                System.out.print("Enter Book ID to Remove: ");
                removeBookId = sc.nextInt();
                sc.nextLine();

                if(removeBookId <= 0) {
                    System.out.println("Book ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Book ID");
                sc.nextLine();
            }
        }

        bookService.removeBook(removeBookId);
        break;
    }

    case 3: {
        int updateBookId = 0;
        int newQuantity = 0;

        while(true) {
            try {
                System.out.print("Enter Book ID to Update: ");
                updateBookId = sc.nextInt();
                sc.nextLine();

                if(updateBookId <= 0) {
                    System.out.println("Book ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Book ID");
                sc.nextLine();
            }
        }

        System.out.print("Enter New Title: ");
        String newTitle = sc.nextLine();

        System.out.print("Enter New Author: ");
        String newAuthor = sc.nextLine();

        System.out.print("Enter New ISBN: ");
        String newIsbn = sc.nextLine();

        System.out.print("Enter New Category: ");
        String newCategory = sc.nextLine();

        while(true) {
            try {
                System.out.print("Enter New Quantity: ");
                newQuantity = sc.nextInt();
                sc.nextLine();

                if(newQuantity < 0) {
                    System.out.println("Quantity cannot be negative");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Quantity");
                sc.nextLine();
            }
        }

        bookService.updateBook(
                updateBookId,
                newTitle,
                newAuthor,
                newIsbn,
                newCategory,
                newQuantity
        );
        break;
    }

    case 4: {
        int studentId = 0;

        while(true) {
            try {
                System.out.print("Enter Student ID: ");
                studentId = sc.nextInt();
                sc.nextLine();

                if(studentId <= 0) {
                    System.out.println("Student ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Student ID");
                sc.nextLine();
            }
        }

        System.out.print("Enter Name: ");
        String studentName = sc.nextLine();

        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Student student = new Student(
                studentId,
                studentName,
                department,
                password
        );

        studentService.registerStudent(student);
        break;
    }

    case 5: {
        int issueStudentId = 0;
        int issueBookId = 0;

        while(true) {
            try {
                System.out.print("Enter Student ID: ");
                issueStudentId = sc.nextInt();
                sc.nextLine();

                if(issueStudentId <= 0) {
                    System.out.println("Student ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Student ID");
                sc.nextLine();
            }
        }

        while(true) {
            try {
                System.out.print("Enter Book ID: ");
                issueBookId = sc.nextInt();
                sc.nextLine();

                if(issueBookId <= 0) {
                    System.out.println("Book ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Book ID");
                sc.nextLine();
            }
        }

        System.out.print("Enter Issue Date (dd/MM/yyyy): ");
        String issueDate = sc.nextLine();

        System.out.print("Enter Due Date (dd/MM/yyyy): ");
        String dueDate = sc.nextLine();

        borrowService.issueBook(
                issueStudentId,
                issueBookId,
                issueDate,
                dueDate
        );
        break;
    }

    case 6: {
        int returnStudentId = 0;
        int returnBookId = 0;

        while(true) {
            try {
                System.out.print("Enter Student ID: ");
                returnStudentId = sc.nextInt();
                sc.nextLine();

                if(returnStudentId <= 0) {
                    System.out.println("Student ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Student ID");
                sc.nextLine();
            }
        }

        while(true) {
            try {
                System.out.print("Enter Book ID: ");
                returnBookId = sc.nextInt();
                sc.nextLine();

                if(returnBookId <= 0) {
                    System.out.println("Book ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Book ID");
                sc.nextLine();
            }
        }

        System.out.print("Enter Return Date (dd/MM/yyyy): ");
        String returnDate = sc.nextLine();

        borrowService.returnBook(
                returnStudentId,
                returnBookId,
                returnDate
        );
        break;
    }

    case 7: {
        int searchStudentId = 0;

        while(true) {
            try {
                System.out.print("Enter Student ID: ");
                searchStudentId = sc.nextInt();
                sc.nextLine();

                if(searchStudentId <= 0) {
                    System.out.println("Student ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Student ID");
                sc.nextLine();
            }
        }

        Student foundStudent = studentService.searchStudentById(searchStudentId);

        if(foundStudent != null) {
            foundStudent.displayProfile();
        }
        else {
            System.out.println("Student not found");
        }
        break;
    }

    case 8: {
        int fineStudentId = 0;
        double amount = 0;

        while(true) {
            try {
                System.out.print("Enter Student ID: ");
                fineStudentId = sc.nextInt();
                sc.nextLine();

                if(fineStudentId <= 0) {
                    System.out.println("Student ID must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Student ID");
                sc.nextLine();
            }
        }

        while(true) {
            try {
                System.out.print("Enter Amount to Pay: ");
                amount = sc.nextDouble();
                sc.nextLine();

                if(amount <= 0) {
                    System.out.println("Amount must be greater than 0");
                    continue;
                }

                break;
            }
            catch(Exception e) {
                System.out.println("Invalid Amount");
                sc.nextLine();
            }
        }

        borrowService.payFine(fineStudentId, amount);
        break;
    }

    case 9:
        System.out.println("Logging out...");
        return;

    default:
        System.out.println("Invalid choice");
}

            }
        }
    }