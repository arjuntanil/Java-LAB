package c02;
import java.util.Scanner;

// Program demonstrating 'this' keyword to resolve variable shadowing
class Car {
    private String model;
    private int year;
    
    // Constructor demonstrating 'this' keyword
    public Car(String model, int year) {
        this.model = model;  // 'this.model' refers to instance variable
        this.year = year;    // 'this.year' refers to instance variable
        System.out.println("Car created using 'this' keyword in constructor");
    }
    
    // Method demonstrating 'this' keyword with parameter shadowing
    public void setDetails(String model, int year) {
        System.out.println("Before assignment:");
        System.out.println("  Parameter model: " + model);
        System.out.println("  Parameter year: " + year);
        System.out.println("  Instance model: " + this.model);
        System.out.println("  Instance year: " + this.year);
        
        this.model = model;  // Without 'this', it would be parameter = parameter
        this.year = year;    // 'this' differentiates instance variable from parameter
        
        System.out.println("After assignment using 'this':");
        System.out.println("  Instance model: " + this.model);
        System.out.println("  Instance year: " + this.year);
    }
    
    // Method demonstrating method call using 'this'
    public void displayCar() {
        System.out.println("\n--- Car Details ---");
        System.out.println("Model: " + this.model);  // 'this' is optional here but shows explicit reference
        System.out.println("Year: " + this.year);
    }
    
    // Method demonstrating 'this' for method chaining
    public Car setModel(String model) {
        this.model = model;
        return this;  // Returning 'this' allows method chaining
    }
    
    public Car setYear(int year) {
        this.year = year;
        return this;  // Returning 'this' allows method chaining
    }
    
    // Method demonstrating calling another method using 'this'
    public void showCarInfo() {
        System.out.println("Calling displayCar() using 'this':");
        this.displayCar();  // 'this' explicitly calls method of current object
    }
    
    // Demonstrating constructor chaining with 'this'
    public Car() {
        this("Unknown Model", 2000);  // Calls the parameterized constructor
        System.out.println("Default constructor called, which calls parameterized constructor using 'this'");
    }
}

public class Q8_ThisKeywordDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== 'this' Keyword Demonstration Program ===");
        
        // 1. Creating car using parameterized constructor
        System.out.println("\n1. Creating car with parameterized constructor:");
        Car car1 = new Car("Toyota Camry", 2023);
        car1.displayCar();
        
        // 2. Creating car using default constructor (demonstrates constructor chaining)
        System.out.println("\n2. Creating car with default constructor:");
        Car car2 = new Car();
        car2.displayCar();
        
        // 3. Demonstrating variable shadowing resolution
        System.out.println("\n3. Demonstrating variable shadowing with 'this':");
        System.out.print("Enter new car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter new car year: ");
        int year = scanner.nextInt();
        
        car1.setDetails(model, year);
        car1.displayCar();
        
        // 4. Demonstrating method chaining using 'this'
        System.out.println("\n4. Demonstrating method chaining with 'this':");
        car2.setModel("Honda Civic").setYear(2024);  // Method chaining
        System.out.println("Car2 updated using method chaining:");
        car2.displayCar();
        
        // 5. Demonstrating explicit method call using 'this'
        System.out.println("\n5. Demonstrating method call using 'this':");
        car1.showCarInfo();
        
        // 6. Demonstrating problem without 'this' keyword
        System.out.println("\n6. Problem demonstration - without 'this' keyword:");
        System.out.println("If we don't use 'this' in setDetails method:");
        System.out.println("  model = model;  // This assigns parameter to itself!");
        System.out.println("  year = year;    // This assigns parameter to itself!");
        System.out.println("  The instance variables remain unchanged!");
        
        // 7. Create a class to show the problem without 'this'
        BadCar badCar = new BadCar("Initial Model", 2020);
        System.out.println("\nBadCar before setDetails:");
        badCar.displayCar();
        
        badCar.setDetails("New Model", 2024);
        System.out.println("BadCar after setDetails (without 'this'):");
        badCar.displayCar();  // Values won't change!
        
        System.out.println("\n=== 'this' Keyword Summary ===");
        System.out.println("✓ 'this' refers to the current object instance");
        System.out.println("✓ 'this' resolves variable shadowing between parameters and instance variables");
        System.out.println("✓ 'this' can be used for method chaining by returning the current object");
        System.out.println("✓ 'this()' can call other constructors in the same class");
        System.out.println("✓ 'this' makes code more readable and explicit");
        
        scanner.close();
    }
}

// Helper class to demonstrate the problem without 'this'
class BadCar {
    private String model;
    private int year;
    
    public BadCar(String model, int year) {
        this.model = model;
        this.year = year;
    }
    
    // Method WITHOUT 'this' keyword - demonstrates the problem
    public void setDetails(String model, int year) {
        model = model;  // This assigns parameter to itself, not to instance variable!
        year = year;    // This assigns parameter to itself, not to instance variable!
        // Instance variables remain unchanged!
    }
    
    public void displayCar() {
        System.out.println("Model: " + model + ", Year: " + year);
    }
}