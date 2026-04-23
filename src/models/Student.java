package models;

public class Student {

    public int studentId;
    public String name;
    public String department;
    public String password;
    public double fineAmount;

    public Student(int studentId, String name,
                   String department, String password) {

        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.password = password;
        this.fineAmount = 0;
    }

    public void displayProfile() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Fine Amount: Rs. " + fineAmount);
    }
}