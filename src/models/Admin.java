package models;

public class Admin {

    public int adminId;
    public String username;
    public String password;

    public Admin(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public void displayAdminDetails() {
        System.out.println("Admin ID: " + adminId);
        System.out.println("Username: " + username);
    }
}