package services;

import java.util.ArrayList;

import models.Librarian;

public class LibrarianService {

    ArrayList<Librarian> librarians;

    public LibrarianService(ArrayList<Librarian> librarians) {
        this.librarians = librarians;
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
        System.out.println("Librarian added successfully");
    }

    public Librarian loginLibrarian(String username, String password) {

        for(Librarian librarian : librarians) {
            if(librarian.username.equals(username) &&
               librarian.password.equals(password)) {

                return librarian;
            }
        }

        return null;
    }

    public void removeLibrarian(int librarianId) {

        for(int i = 0; i < librarians.size(); i++) {

            if(librarians.get(i).librarianId == librarianId) {
                librarians.remove(i);
                System.out.println("Librarian removed successfully");
                return;
            }
        }

        System.out.println("Librarian not found");
    }

    public void updateLibrarianDetails(int librarianId,
                                       String newName,
                                       String newUsername,
                                       String newPassword) {

        for(Librarian librarian : librarians) {

            if(librarian.librarianId == librarianId) {
                librarian.name = newName;
                librarian.username = newUsername;
                librarian.password = newPassword;

                System.out.println("Librarian details updated successfully");
                return;
            }
        }

        System.out.println("Librarian not found");
    }

    public void displayAllLibrarians() {

        if(librarians.isEmpty()) {
            System.out.println("No librarians found");
            return;
        }

        for(Librarian librarian : librarians) {
            librarian.displayLibrarianDetails();
            System.out.println();
        }
    }
}