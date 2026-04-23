package models;

public class SystemSettings {

    public double finePerDay;
    public int maxBooksAllowed;
    public int borrowDurationDays;
    public int maxRenewCount;

    public SystemSettings(double finePerDay, int maxBooksAllowed,
                          int borrowDurationDays, int maxRenewCount) {

        this.finePerDay = finePerDay;
        this.maxBooksAllowed = maxBooksAllowed;
        this.borrowDurationDays = borrowDurationDays;
        this.maxRenewCount = maxRenewCount;
    }

    public void displaySettings() {
        System.out.println("Fine Per Day: Rs. " + finePerDay);
        System.out.println("Maximum Books Allowed: " + maxBooksAllowed);
        System.out.println("Borrow Duration Days: " + borrowDurationDays);
        System.out.println("Maximum Renew Count: " + maxRenewCount);
    }
}