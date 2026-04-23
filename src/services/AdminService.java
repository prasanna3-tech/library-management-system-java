package services;

import java.util.ArrayList;

import models.Admin;
import models.Student;
import models.SystemSettings;

public class AdminService {

    ArrayList<Admin> admins;
    ArrayList<Student> students;
    SystemSettings settings;

    public AdminService(ArrayList<Admin> admins,
                        ArrayList<Student> students,
                        SystemSettings settings) {

        this.admins = admins;
        this.students = students;
        this.settings = settings;
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
        System.out.println("Admin added successfully");
    }

    public Admin loginAdmin(String username, String password) {

        for(Admin admin : admins) {
            if(admin.username.equals(username) &&
               admin.password.equals(password)) {

                return admin;
            }
        }

        return null;
    }

    public void removeAdmin(int adminId) {

        for(int i = 0; i < admins.size(); i++) {

            if(admins.get(i).adminId == adminId) {
                admins.remove(i);
                System.out.println("Admin removed successfully");
                return;
            }
        }

        System.out.println("Admin not found");
    }

    public void displayAllAdmins() {

        if(admins.isEmpty()) {
            System.out.println("No admins found");
            return;
        }

        for(Admin admin : admins) {
            admin.displayAdminDetails();
            System.out.println();
        }
    }

    public void resetStudentPassword(int studentId, String newPassword) {

        for(Student student : students) {

            if(student.studentId == studentId) {
                student.password = newPassword;
                System.out.println("Student password reset successfully");
                return;
            }
        }

        System.out.println("Student not found");
    }

    public void updateSystemSettings(double finePerDay,
                                     int maxBooksAllowed,
                                     int borrowDurationDays,
                                     int maxRenewCount) {

        settings.finePerDay = finePerDay;
        settings.maxBooksAllowed = maxBooksAllowed;
        settings.borrowDurationDays = borrowDurationDays;
        settings.maxRenewCount = maxRenewCount;

        System.out.println("System settings updated successfully");
    }
}