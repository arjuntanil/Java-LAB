package c03;

// Q3: Access and Modify Protected Data Across Classes
// Demonstrates protected access modifier and inheritance

// Base Employee class with protected fields
class Employee {
    protected String name;      // Protected - accessible in subclasses and same package
    protected double salary;    // Protected - accessible in subclasses and same package
    protected int employeeId;   // Protected - accessible in subclasses and same package
    private String department;  // Private - only accessible within this class
    
    // Constructor
    public Employee(String name, double salary, int employeeId, String department) {
        this.name = name;
        this.salary = salary;
        this.employeeId = employeeId;
        this.department = department;
    }
    
    // Protected method - accessible in subclasses
    protected void displayBasicInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
    }
    
    // Public method to get department (since it's private)
    public String getDepartment() {
        return department;
    }
    
    // Protected method to calculate basic salary
    protected double calculateBasicSalary() {
        return salary;
    }
    
    // Method to give raise
    protected void giveRaise(double percentage) {
        salary += salary * (percentage / 100);
        System.out.println("Salary increased by " + percentage + "%");
    }
}

// Manager subclass that accesses protected data
class Manager extends Employee {
    private double bonus;
    private int teamSize;
    
    public Manager(String name, double salary, int employeeId, String department, 
                  double bonus, int teamSize) {
        super(name, salary, employeeId, department);
        this.bonus = bonus;
        this.teamSize = teamSize;
    }
    
    // Method that accesses and modifies protected fields from parent class
    public void displayManagerInfo() {
        System.out.println("\n=== Manager Information ===");
        
        // Accessing protected fields directly
        System.out.println("Manager Name: " + name);        // Protected field access
        System.out.println("Salary: " + (int)salary);       // Protected field access
        System.out.println("Employee ID: " + employeeId);   // Protected field access
        System.out.println("Department: " + getDepartment()); // Using public method for private field
        System.out.println("Bonus: " + bonus);
        System.out.println("Team Size: " + teamSize);
        
        // Calculate total compensation using protected field
        double totalCompensation = salary + bonus;
        System.out.println("Total Compensation: " + (int)totalCompensation);
    }
    
    // Method that modifies protected fields
    public void adjustSalary(double newSalary) {
        System.out.println("\nAdjusting manager's salary:");
        System.out.println("Previous salary: " + (int)salary);
        
        // Direct modification of protected field
        salary = newSalary;  // This is allowed because salary is protected
        
        System.out.println("New salary: " + (int)salary);
    }
    
    // Method using protected method from parent
    public void giveTeamRaise(double percentage) {
        System.out.println("\nManager " + name + " giving team raise of " + percentage + "%");
        // Using protected method from parent class
        giveRaise(percentage);  // This calls the protected method from Employee class
    }
    
    // Method that uses protected method
    public void showBasicDetails() {
        displayBasicInfo(); // Calling protected method from parent class
    }
    
    // Override to add manager-specific functionality
    @Override
    protected double calculateBasicSalary() {
        return salary + bonus; // Manager's total includes bonus
    }
}

// Developer subclass that also accesses protected data
class Developer extends Employee {
    private String programmingLanguage;
    private int projectsCompleted;
    
    public Developer(String name, double salary, int employeeId, String department,
                    String programmingLanguage, int projectsCompleted) {
        super(name, salary, employeeId, department);
        this.programmingLanguage = programmingLanguage;
        this.projectsCompleted = projectsCompleted;
    }
    
    public void displayDeveloperInfo() {
        System.out.println("\n=== Developer Information ===");
        
        // Accessing protected fields from parent class
        System.out.println("Developer Name: " + name);      // Protected access
        System.out.println("Salary: " + (int)salary);       // Protected access
        System.out.println("Employee ID: " + employeeId);   // Protected access
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Projects Completed: " + projectsCompleted);
    }
    
    // Method that calculates performance bonus based on protected salary
    public void calculatePerformanceBonus() {
        // Using protected field in calculation
        double performanceBonus = salary * 0.1 * projectsCompleted;
        System.out.println("Performance bonus based on " + projectsCompleted + 
                          " projects: " + (int)performanceBonus);
        
        // Modifying protected field
        salary += performanceBonus;
        System.out.println("Updated salary with bonus: " + (int)salary);
    }
}

// Another class in same package (to demonstrate package-level access)
class HRDepartment {
    
    // Method that works with Employee objects
    public void processEmployeeData(Employee emp) {
        System.out.println("\n=== HR Processing Employee Data ===");
        
        // Can access protected fields because it's in the same package
        System.out.println("Processing employee: " + emp.name);          // Protected access
        System.out.println("Employee ID: " + emp.employeeId);            // Protected access
        System.out.println("Current salary: " + (int)emp.salary);        // Protected access
        
        // Can call protected methods
        emp.displayBasicInfo();  // Protected method access
        
        // Calculate and display annual salary
        double annualSalary = emp.calculateBasicSalary() * 12;
        System.out.println("Annual salary: " + (int)annualSalary);
    }
    
    // Method to give company-wide raise
    public void giveCompanyWideRaise(Employee[] employees, double percentage) {
        System.out.println("\n=== Company-wide Raise of " + percentage + "% ===");
        
        for (Employee emp : employees) {
            if (emp != null) {
                double oldSalary = emp.salary;
                emp.giveRaise(percentage);  // Calling protected method
                System.out.println(emp.name + ": " + (int)oldSalary + " → " + (int)emp.salary);
            }
        }
    }
}

public class Q3_ProtectedDataAccess {
    
    public static void main(String[] args) {
        System.out.println("=== Protected Data Access Demonstration ===");
        
        // Create Employee objects
        Manager manager = new Manager("Alice", 90000, 101, "IT", 15000, 5);
        Developer developer = new Developer("Bob", 75000, 102, "IT", "Java", 8);
        Employee regularEmployee = new Employee("Charlie", 50000, 103, "HR");
        
        // Demonstrate protected field access in Manager subclass
        System.out.println("1. Manager accessing protected data:");
        manager.displayManagerInfo();
        
        // Demonstrate protected field modification
        System.out.println("\n2. Modifying protected fields in subclass:");
        manager.adjustSalary(95000);
        manager.displayManagerInfo();
        
        // Demonstrate protected method usage
        System.out.println("\n3. Using protected methods:");
        manager.giveTeamRaise(5.0);
        manager.showBasicDetails();
        
        // Demonstrate protected access in Developer subclass
        System.out.println("\n4. Developer accessing protected data:");
        developer.displayDeveloperInfo();
        developer.calculatePerformanceBonus();
        
        // Demonstrate package-level access to protected members
        System.out.println("\n5. Package-level access (HR Department):");
        HRDepartment hr = new HRDepartment();
        hr.processEmployeeData(manager);
        hr.processEmployeeData(developer);
        
        // Demonstrate protected access with array of employees
        System.out.println("\n6. Batch processing with protected access:");
        Employee[] employees = {manager, developer, regularEmployee};
        hr.giveCompanyWideRaise(employees, 3.0);
        
        // Show final state
        System.out.println("\n7. Final employee states:");
        if (manager instanceof Manager) {
            ((Manager) manager).displayManagerInfo();
        }
        if (developer instanceof Developer) {
            ((Developer) developer).displayDeveloperInfo();
        }
        
        // Demonstrate what we CANNOT do
        System.out.println("\n8. Access Level Restrictions:");
        System.out.println("✓ Can access protected fields/methods from subclasses");
        System.out.println("✓ Can access protected fields/methods from same package");
        System.out.println("✗ Cannot access private fields directly from subclasses");
        System.out.println("✗ Cannot access protected members from different packages (without inheritance)");
        
        // The following would cause compilation errors if uncommented:
        // System.out.println(manager.department);  // Error: department is private
        
        System.out.println("\n=== Protected Access Summary ===");
        System.out.println("✓ Protected members are accessible in subclasses");
        System.out.println("✓ Protected members are accessible within the same package");
        System.out.println("✓ Provides controlled access - more than private, less than public");
        System.out.println("✓ Enables inheritance while maintaining some encapsulation");
    }
}