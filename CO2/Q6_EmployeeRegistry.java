package c02;
import java.util.Scanner;

// Employee Registry - Demonstrating Constructors, Object Arrays, Passing Objects
class Employee {
    private int empId;
    private String name;
    private double salary;
    
    // Constructor that accepts all three values
    public Employee(int empId, String name, double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }
    
    // Method to display employee details
    public void displayEmployee() {
        System.out.println("\nEmployee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Salary: $" + String.format("%.2f", salary));
    }
    
    // Method to update salary
    public void updateSalary(double newSalary) {
        if (newSalary > 0) {
            this.salary = newSalary;
            System.out.println("Salary updated for " + name + " to $" + String.format("%.2f", newSalary));
        } else {
            System.out.println("Invalid salary amount!");
        }
    }
    
    // Getter methods
    public int getEmpId() {
        return empId;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
}

public class Q6_EmployeeRegistry {
    
    // Static method to promote employee (demonstrating object passing)
    public static void promote(Employee emp, double bonus) {
        double currentSalary = emp.getSalary();
        double newSalary = currentSalary + bonus;
        emp.updateSalary(newSalary);
        System.out.println("Employee " + emp.getName() + " promoted with $" + String.format("%.2f", bonus) + " bonus!");
    }
    
    // Static method to give raise to all employees
    public static void giveRaiseToAll(Employee[] employees, double raisePercentage) {
        System.out.println("\nGiving " + raisePercentage + "% raise to all employees:");
        for (Employee emp : employees) {
            if (emp != null) {
                double currentSalary = emp.getSalary();
                double newSalary = currentSalary * (1 + raisePercentage / 100);
                emp.updateSalary(newSalary);
            }
        }
    }
    
    // Method to find employee by ID
    public static Employee findEmployeeById(Employee[] employees, int empId) {
        for (Employee emp : employees) {
            if (emp != null && emp.getEmpId() == empId) {
                return emp;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Employee Registry Management System ===");
        
        // Get number of employees
        System.out.print("Enter number of employees to register: ");
        int numEmployees = scanner.nextInt();
        
        // Create array of Employee objects
        Employee[] employees = new Employee[numEmployees];
        
        // Accept details for each employee
        System.out.println("\nEnter employee details:");
        for (int i = 0; i < numEmployees; i++) {
            System.out.println("\nEmployee " + (i + 1) + ":");
            System.out.print("Enter Employee ID: ");
            int empId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Salary: $");
            double salary = scanner.nextDouble();
            
            // Create Employee object using constructor
            employees[i] = new Employee(empId, name, salary);
        }
        
        // Display all employee details
        System.out.println("\n=== INITIAL EMPLOYEE REGISTRY ===");
        for (int i = 0; i < employees.length; i++) {
            System.out.println("\nEmployee " + (i + 1) + ":");
            employees[i].displayEmployee();
        }
        
        // Update salary of a specific employee
        System.out.println("\n=== SALARY UPDATE ===");
        System.out.print("Enter Employee ID to update salary: ");
        int updateId = scanner.nextInt();
        
        Employee empToUpdate = findEmployeeById(employees, updateId);
        if (empToUpdate != null) {
            System.out.print("Enter new salary for " + empToUpdate.getName() + ": $");
            double newSalary = scanner.nextDouble();
            empToUpdate.updateSalary(newSalary);
        } else {
            System.out.println("Employee with ID " + updateId + " not found!");
        }
        
        // Demonstrate object passing to static method (promotion)
        System.out.println("\n=== EMPLOYEE PROMOTION ===");
        System.out.print("Enter Employee ID to promote: ");
        int promoteId = scanner.nextInt();
        
        Employee empToPromote = findEmployeeById(employees, promoteId);
        if (empToPromote != null) {
            System.out.print("Enter promotion bonus: $");
            double bonus = scanner.nextDouble();
            promote(empToPromote, bonus); // Passing object to static method
        } else {
            System.out.println("Employee with ID " + promoteId + " not found!");
        }
        
        // Give raise to all employees
        System.out.println("\n=== COMPANY-WIDE RAISE ===");
        System.out.print("Enter raise percentage for all employees: ");
        double raisePercentage = scanner.nextDouble();
        giveRaiseToAll(employees, raisePercentage);
        
        // Display final employee details
        System.out.println("\n=== FINAL EMPLOYEE REGISTRY ===");
        for (int i = 0; i < employees.length; i++) {
            System.out.println("\nEmployee " + (i + 1) + ":");
            employees[i].displayEmployee();
        }
        
        // Calculate and display statistics
        System.out.println("\n=== EMPLOYEE STATISTICS ===");
        double totalSalary = 0;
        double maxSalary = 0;
        String highestPaidEmployee = "";
        
        for (Employee emp : employees) {
            totalSalary += emp.getSalary();
            if (emp.getSalary() > maxSalary) {
                maxSalary = emp.getSalary();
                highestPaidEmployee = emp.getName();
            }
        }
        
        double averageSalary = totalSalary / employees.length;
        
        System.out.println("Total number of employees: " + employees.length);
        System.out.println("Total salary expense: $" + String.format("%.2f", totalSalary));
        System.out.println("Average salary: $" + String.format("%.2f", averageSalary));
        System.out.println("Highest paid employee: " + highestPaidEmployee + " ($" + String.format("%.2f", maxSalary) + ")");
        
        scanner.close();
    }
}