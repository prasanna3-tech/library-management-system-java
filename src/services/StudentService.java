package services;

import java.util.ArrayList;
import models.Student;

public class StudentService {

    ArrayList<Student> students;

    public StudentService(ArrayList<Student> students) {
        this.students = students;
    }

    public void registerStudent(Student student) {

    for(Student s : students) {
        if(s.studentId == student.studentId) {
            System.out.println("Student ID already exists");
            return;
        }
    }

    students.add(student);
    System.out.println("Student registered successfully");
}

    public Student loginStudent(int studentId, String password) {

        for(Student student : students) {
            if(student.studentId == studentId &&
               student.password.equals(password)) {

                return student;
            }
        }

        return null;
    }

    public Student searchStudentById(int studentId) {

        for(Student student : students) {
            if(student.studentId == studentId) {
                return student;
            }
        }

        return null;
    }

    public void displayAllStudents() {

        if(students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        for(Student student : students) {
            student.displayProfile();
            System.out.println();
        }
    }

    public void removeStudent(int studentId) {

        for(int i = 0; i < students.size(); i++) {

            if(students.get(i).studentId == studentId) {
                students.remove(i);
                System.out.println("Student removed successfully");
                return;
            }
        }

        System.out.println("Student not found");
    }

    public void updateStudentDetails(int studentId,
                                     String newName,
                                     String newDepartment) {

        Student student = searchStudentById(studentId);

        if(student == null) {
            System.out.println("Student not found");
            return;
        }

        student.name = newName;
        student.department = newDepartment;

        System.out.println("Student details updated successfully");
    }

    public void resetStudentPassword(int studentId, String newPassword) {

        Student student = searchStudentById(studentId);

        if(student == null) {
            System.out.println("Student not found");
            return;
        }

        student.password = newPassword;

        System.out.println("Student password reset successfully");
    }
}