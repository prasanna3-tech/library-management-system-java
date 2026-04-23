package models;

public class Reservation {

    public int studentId;
    public int bookId;
    public String reservationDate;
    public boolean notified;

    public Reservation(int studentId, int bookId, String reservationDate) {

        this.studentId = studentId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.notified = false;
    }

    public void displayReservationDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Book ID: " + bookId);
        System.out.println("Reservation Date: " + reservationDate);
        System.out.println("Notified: " + notified);
    }
}