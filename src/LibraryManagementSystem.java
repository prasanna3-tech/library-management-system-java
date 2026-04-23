import java.util.ArrayList;
import java.util.Scanner;
import menus.AdminMenu;
import menus.LibrarianMenu;
import menus.StudentMenu;
import models.Admin;
import models.Book;
import models.BorrowRecord;
import models.Librarian;
import models.Reservation;
import models.Student;
import models.SystemSettings;
import services.AdminService;
import services.BookService;
import services.BorrowService;
import services.LibrarianService;
import services.ReportService;
import services.StudentService;

public class LibraryManagementSystem {

    Scanner sc = new Scanner(System.in);

    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Librarian> librarians = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();
    ArrayList<Reservation> reservations = new ArrayList<>();

    SystemSettings settings = new SystemSettings(10, 3, 7, 2);

    BookService bookService;
    StudentService studentService;
    LibrarianService librarianService;
    AdminService adminService;
    BorrowService borrowService;
    ReportService reportService;

    StudentMenu studentMenu;
    LibrarianMenu librarianMenu;
    AdminMenu adminMenu;

    public LibraryManagementSystem() {

        bookService = new BookService(books, borrowRecords);

        studentService = new StudentService(students);

        librarianService = new LibrarianService(librarians);

        adminService = new AdminService(admins, students, settings);

        borrowService = new BorrowService(
                books,
                students,
                borrowRecords,
                reservations,
                settings
        );

        reportService = new ReportService(
                books,
                students,
                librarians,
                borrowRecords
        );

        studentMenu = new StudentMenu(
                bookService,
                borrowService,
                studentService
        );

        librarianMenu = new LibrarianMenu(
                bookService,
                studentService,
                borrowService
        );

        adminMenu = new AdminMenu(
                adminService,
                librarianService,
                studentService,
                reportService
        );

        loadSampleData();
    }

    public void start() {

        while(true) {

            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Student Login");
            System.out.println("2. Librarian Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");

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

                case 1:
                    studentLoginMenu();
                    break;

                case 2:
                    librarianLoginMenu();
                    break;

                case 3:
                    adminLoginMenu();
                    break;

                case 4:
                    System.out.println("Thank you for using LMS");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

   public void studentLoginMenu() {

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
            System.out.println("Invalid input. Please enter a valid Student ID.");
            sc.nextLine();
        }
    }

    System.out.print("Enter Password: ");
    String password = sc.nextLine();

    while(password.trim().isEmpty()) {
        System.out.println("Password cannot be empty");
        System.out.print("Enter Password: ");
        password = sc.nextLine();
    }
    Student student = studentService.loginStudent(studentId, password);

    if(student != null) {
        System.out.println("Login successful");
        studentMenu.showMenu(student);
    }
    else {
        System.out.println("Invalid Student ID or Password");
    }
}

public void librarianLoginMenu() {

    System.out.print("Enter Username: ");
    String username = sc.nextLine();

    while(username.trim().isEmpty()) {
        System.out.println("Username cannot be empty");
        System.out.print("Enter Username: ");
        username = sc.nextLine();
    }

    System.out.print("Enter Password: ");
    String password = sc.nextLine();

    while(password.trim().isEmpty()) {
        System.out.println("Password cannot be empty");
        System.out.print("Enter Password: ");
        password = sc.nextLine();
    }

    Librarian librarian = librarianService.loginLibrarian(username, password);

    if(librarian != null) {
        System.out.println("Login successful");
        librarianMenu.showMenu(librarian);
    }
    else {
        System.out.println("Invalid Username or Password");
    }
}

public void adminLoginMenu() {

    System.out.print("Enter Username: ");
    String username = sc.nextLine();

    while(username.trim().isEmpty()) {
        System.out.println("Username cannot be empty");
        System.out.print("Enter Username: ");
        username = sc.nextLine();
    }

    System.out.print("Enter Password: ");
    String password = sc.nextLine();

    while(password.trim().isEmpty()) {
        System.out.println("Password cannot be empty");
        System.out.print("Enter Password: ");
        password = sc.nextLine();
    }

    Admin admin = adminService.loginAdmin(username, password);

    if(admin != null) {
        System.out.println("Login successful");
        adminMenu.showMenu(admin);
    }
    else {
        System.out.println("Invalid Username or Password");
    }
}
    public void loadSampleData() {

    // Books
    books.add(new Book(1, "Java Programming", "James Gosling", "ISBN101", "Programming", 5));
    books.add(new Book(2, "Database Systems", "Navathe", "ISBN102", "Database", 3));
    books.add(new Book(3, "Operating Systems", "Galvin", "ISBN103", "Computer Science", 4));
    books.add(new Book(4, "Computer Networks", "Andrew Tanenbaum", "ISBN104", "Networking", 2));
    books.add(new Book(5, "Data Structures", "Mark Allen Weiss", "ISBN105", "Programming", 6));
    books.add(new Book(6, "Artificial Intelligence", "Stuart Russell", "ISBN106", "AI", 3));
    books.add(new Book(7, "Machine Learning", "Tom Mitchell", "ISBN107", "AI", 4));
    books.add(new Book(8, "Python Basics", "Guido van Rossum", "ISBN108", "Programming", 7));
    books.add(new Book(9, "C Programming", "Dennis Ritchie", "ISBN109", "Programming", 5));
    books.add(new Book(10, "Software Engineering", "Ian Sommerville", "ISBN110", "Software", 4));
    books.add(new Book(11, "Cloud Computing", "Rajkumar Buyya", "ISBN111", "Cloud", 3));
    books.add(new Book(12, "Cyber Security", "William Stallings", "ISBN112", "Security", 2));
    books.add(new Book(13, "Digital Logic Design", "Morris Mano", "ISBN113", "Electronics", 5));
    books.add(new Book(14, "Microprocessors", "Ramesh Gaonkar", "ISBN114", "Electronics", 4));
    books.add(new Book(15, "Compiler Design", "Aho", "ISBN115", "Programming", 2));

    // Students
    students.add(new Student(101, "Prasanna", "CSE", "1234"));
    students.add(new Student(102, "Arun", "IT", "5678"));
    students.add(new Student(103, "Vikram", "CSE", "1111"));
    students.add(new Student(104, "Priya", "ECE", "2222"));
    students.add(new Student(105, "Rahul", "EEE", "3333"));
    students.add(new Student(106, "Sneha", "IT", "4444"));
    students.add(new Student(107, "Karthik", "CSE", "5555"));
    students.add(new Student(108, "Divya", "ECE", "6666"));
    students.add(new Student(109, "Rohit", "Mechanical", "7777"));
    students.add(new Student(110, "Anjali", "Civil", "8888"));

    // Librarians
    librarians.add(new Librarian(1, "Kumar", "lib1", "1234"));
    librarians.add(new Librarian(2, "Suresh", "lib2", "5678"));
    librarians.add(new Librarian(3, "Meena", "lib3", "abcd"));

    // Admins
    admins.add(new Admin(1, "admin", "admin123"));
    admins.add(new Admin(2, "superadmin", "super123"));

    // Borrow Records
    BorrowRecord r1 = new BorrowRecord(101, 1, "01/04/2026", "08/04/2026");
    r1.returned = true;
    r1.returnDate = "10/04/2026";
    r1.fine = 20;
    r1.finePaid = true;
    borrowRecords.add(r1);

    BorrowRecord r2 = new BorrowRecord(102, 2, "05/04/2026", "12/04/2026");
    r2.returned = false;
    borrowRecords.add(r2);

    BorrowRecord r3 = new BorrowRecord(103, 3, "02/04/2026", "09/04/2026");
    r3.returned = true;
    r3.returnDate = "09/04/2026";
    borrowRecords.add(r3);

    BorrowRecord r4 = new BorrowRecord(104, 4, "01/04/2026", "08/04/2026");
    r4.returned = false;
    borrowRecords.add(r4);

    BorrowRecord r5 = new BorrowRecord(105, 5, "03/04/2026", "10/04/2026");
    r5.returned = true;
    r5.returnDate = "15/04/2026";
    r5.fine = 50;
    r5.finePaid = false;
    borrowRecords.add(r5);

    BorrowRecord r6 = new BorrowRecord(106, 6, "04/04/2026", "11/04/2026");
    r6.returned = false;
    borrowRecords.add(r6);

    BorrowRecord r7 = new BorrowRecord(107, 7, "06/04/2026", "13/04/2026");
    r7.returned = false;
    borrowRecords.add(r7);

    // Reduce quantity for borrowed books
    for(BorrowRecord record : borrowRecords) {
        if(!record.returned) {
            for(Book book : books) {
                if(book.bookId == record.bookId) {
                    book.quantity--;
                    break;
                }
            }
        }
    }

    // Reservations
    reservations.add(new Reservation(108, 2, "15/04/2026"));
    reservations.add(new Reservation(109, 4, "16/04/2026"));
    reservations.add(new Reservation(110, 6, "17/04/2026"));
}
}