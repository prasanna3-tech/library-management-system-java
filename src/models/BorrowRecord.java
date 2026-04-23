package models;

public class BorrowRecord {

    public int studentId;
    public int bookId;
    public String issueDate;
    public String dueDate;
    public String returnDate;
    public boolean returned;
    public double fine;
    public int renewCount;
    public boolean finePaid;

    public BorrowRecord(int studentId, int bookId,
                        String issueDate, String dueDate) {

        this.studentId = studentId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = "";
        this.returned = false;
        this.fine = 0;
        this.renewCount = 0;
        this.finePaid = false;
    }

    public void displayBorrowRecord() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Book ID: " + bookId);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Returned: " + returned);
        System.out.println("Fine: Rs. " + fine);
        System.out.println("Renew Count: " + renewCount);
        System.out.println("Fine Paid: " + finePaid);
    }
}