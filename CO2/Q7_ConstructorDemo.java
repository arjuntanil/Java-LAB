package c02;
import java.util.Scanner;

// Program demonstrating Constructors (default and parameterized)
class Student {
    private String name;
    private int age;
    
    // Default constructor - assigns default values
    public Student() {
        this.name = "Unknown Student";
        this.age = 18;
        System.out.println("Default constructor called - Student created with default values");
    }
    
    // Parameterized constructor - assigns user-provided values
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Parameterized constructor called - Student created with provided values");
    }
    
    // Method to display student details
    public void displayStudent() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println();
    }
    
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}

public class Q7_ConstructorDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Constructor Demonstration Program ===");
        
        // Create student using default constructor
        System.out.println("\n1. Creating student using DEFAULT constructor:");
        Student student1 = new Student();
        System.out.println("Student 1 details:");
        student1.displayStudent();
        
        // Create student using parameterized constructor
        System.out.println("2. Creating student using PARAMETERIZED constructor:");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        
        Student student2 = new Student(name, age);
        System.out.println("Student 2 details:");
        student2.displayStudent();
        
        // Modify student1 using setter methods
        scanner.nextLine(); // Consume newline
        System.out.println("3. Modifying Student 1 using setter methods:");
        System.out.print("Enter new name for Student 1: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age for Student 1: ");
        int newAge = scanner.nextInt();
        
        student1.setName(newName);
        student1.setAge(newAge);
        
        System.out.println("\nUpdated Student 1 details:");
        student1.displayStudent();
        
        // Create multiple students to demonstrate constructor usage
        System.out.println("4. Creating multiple students:");
        Student student3 = new Student(); // Default constructor
        Student student4 = new Student("Alice", 20); // Parameterized constructor
        Student student5 = new Student("Bob", 22);   // Parameterized constructor
        
        System.out.println("All students:");
        System.out.println("Student 3 (default):");
        student3.displayStudent();
        
        System.out.println("Student 4 (parameterized):");
        student4.displayStudent();
        
        System.out.println("Student 5 (parameterized):");
        student5.displayStudent();
        
        System.out.println("=== Constructor Summary ===");
        System.out.println("✓ Default constructor: Initializes objects with default values");
        System.out.println("✓ Parameterized constructor: Initializes objects with user-provided values");
        System.out.println("✓ Constructors are called automatically when objects are created");
        System.out.println("✓ Constructor overloading allows multiple ways to create objects");
        
        scanner.close();
    }
}