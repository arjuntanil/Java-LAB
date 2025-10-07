package c02;

import java.util.Scanner;

// Student Ranker Program - Demonstrating Constructors, this keyword, Method Overloading
class Student {
    private String name;
    private int marks;
    
    // Constructor 1: Accepts only name, initializes marks to 0
    public Student(String name) {
        this.name = name;
        this.marks = 0;
    }
    
    // Constructor 2: Accepts both name and marks
    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
    
    // Method to set marks after object creation
    public void setMarks(int marks) {
        this.marks = marks;
    }
    
    // Method to calculate grade based on marks
    public char calculateGrade() {
        if (marks >= 90) {
            return 'A';
        } else if (marks >= 75) {
            return 'B';
        } else if (marks >= 60) {
            return 'C';
        } else if (marks >= 40) {
            return 'D';
        } else {
            return 'F';
        }
    }
    
    // Method to display student result
    public void displayResult() {
        System.out.println("\n--- Student Result ---");
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + calculateGrade());
    }
}

public class Q1_StudentRanker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Student Ranker Program ===");
        
        // Creating student using constructor with name only
        System.out.print("Enter first student's name: ");
        String name1 = scanner.nextLine();
        Student student1 = new Student(name1);
        
        System.out.print("Enter marks for " + name1 + ": ");
        int marks1 = scanner.nextInt();
        student1.setMarks(marks1);
        
        scanner.nextLine(); // Consume newline
        
        // Creating student using constructor with both name and marks
        System.out.print("Enter second student's name: ");
        String name2 = scanner.nextLine();
        System.out.print("Enter marks for " + name2 + ": ");
        int marks2 = scanner.nextInt();
        Student student2 = new Student(name2, marks2);
        
        // Display results
        System.out.println("\n=== STUDENT RESULTS ===");
        student1.displayResult();
        student2.displayResult();
        
        // Demonstrate constructor overloading
        System.out.println("\n=== Constructor Overloading Demo ===");
        System.out.println("Student 1 created using: Student(String name)");
        System.out.println("Student 2 created using: Student(String name, int marks)");
        
        scanner.close();
    }
}