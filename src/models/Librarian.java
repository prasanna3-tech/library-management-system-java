package models;

public class Librarian {

    public int librarianId;
    public String name;
    public String username;
    public String password;

    public Librarian(int librarianId, String name,
                     String username, String password) {

        this.librarianId = librarianId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public void displayLibrarianDetails() {
        System.out.println("Librarian ID: " + librarianId);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
    }
}