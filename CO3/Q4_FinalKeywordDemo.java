package c03;

// Q4: Prevent Extending and Overriding with final Keyword
// Demonstrates final classes, final methods, and compilation restrictions

// Final class - cannot be extended
final class FinalClass {
    private String data;
    
    public FinalClass(String data) {
        this.data = data;
    }
    
    public void display() {
        System.out.println("Final class data: " + data);
    }
    
    public String getData() {
        return data;
    }
}

// Regular class with final methods
class ParentClass {
    protected String name;
    
    public ParentClass(String name) {
        this.name = name;
    }
    
    // Final method - cannot be overridden
    public final void finalMethod() {
        System.out.println("This is a final method in ParentClass");
        System.out.println("It cannot be overridden by subclasses");
    }
    
    // Regular method - can be overridden
    public void regularMethod() {
        System.out.println("This is a regular method that can be overridden");
    }
    
    // Final method with parameters
    public final String getFinalInfo(String prefix) {
        return prefix + ": " + name + " (from final method)";
    }
    
    public void displayInfo() {
        System.out.println("Parent class name: " + name);
    }
}

// Child class extending ParentClass
class ChildClass extends ParentClass {
    private int age;
    
    public ChildClass(String name, int age) {
        super(name);
        this.age = age;
    }
    
    // This override is allowed - regularMethod is not final
    @Override
    public void regularMethod() {
        System.out.println("Overridden regular method in ChildClass");
    }
    
    // This would cause compilation error if uncommented:
    /*
    @Override
    public void finalMethod() {
        System.out.println("Trying to override final method"); // COMPILATION ERROR!
    }
    */
    
    // This would also cause compilation error if uncommented:
    /*
    @Override
    public String getFinalInfo(String prefix) {
        return "Overridden: " + prefix; // COMPILATION ERROR!
    }
    */
    
    @Override
    public void displayInfo() {
        System.out.println("Child class name: " + name + ", age: " + age);
    }
    
    // New method specific to child class
    public void childSpecificMethod() {
        System.out.println("This method is specific to ChildClass");
    }
}

// Attempting to extend a final class would cause compilation error:
/*
class AttemptToExtendFinal extends FinalClass {  // COMPILATION ERROR!
    public AttemptToExtendFinal(String data) {
        super(data);
    }
}
*/

// Class demonstrating final variables
class FinalVariableDemo {
    // Final instance variable - must be initialized
    private final String CONSTANT_VALUE = "This cannot be changed";
    
    // Final instance variable initialized in constructor
    private final int finalNumber;
    
    // Final static variable (class constant)
    public static final double PI = 3.14159;
    public static final String APP_NAME = "Final Demo Application";
    
    public FinalVariableDemo(int number) {
        this.finalNumber = number; // Can be set only once in constructor
    }
    
    public void demonstrateFinalVariables() {
        System.out.println("\n=== Final Variables Demonstration ===");
        System.out.println("Constant value: " + CONSTANT_VALUE);
        System.out.println("Final number: " + finalNumber);
        System.out.println("PI constant: " + PI);
        System.out.println("App name: " + APP_NAME);
        
        // The following would cause compilation errors if uncommented:
        // CONSTANT_VALUE = "New value";  // Error: cannot assign to final variable
        // finalNumber = 100;            // Error: cannot assign to final variable
        // PI = 3.14;                    // Error: cannot assign to final variable
        
        // Final local variable
        final String localFinal = "Local final variable";
        System.out.println("Local final: " + localFinal);
        // localFinal = "New value";     // Error: cannot assign to final variable
    }
}

// Utility class with final methods (common pattern)
class MathUtils {
    
    // Final method to ensure the algorithm cannot be changed
    public static final double calculateCircleArea(double radius) {
        return FinalVariableDemo.PI * radius * radius;
    }
    
    // Final method for critical calculation
    public static final boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}

public class Q4_FinalKeywordDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Final Keyword Demonstration ===");
        
        // 1. Final class demonstration
        System.out.println("\n1. Final Class Usage:");
        FinalClass finalObj = new FinalClass("Sample Data");
        finalObj.display();
        System.out.println("✓ Can create objects of final class");
        System.out.println("✗ Cannot extend final class (would cause compilation error)");
        
        // 2. Final method demonstration
        System.out.println("\n2. Final Method Usage:");
        ParentClass parent = new ParentClass("Parent");
        ChildClass child = new ChildClass("Child", 25);
        
        // Call final methods (cannot be overridden)
        parent.finalMethod();
        child.finalMethod(); // Inherited final method
        
        System.out.println(parent.getFinalInfo("Info"));
        System.out.println(child.getFinalInfo("Data"));
        
        // Call regular methods (can be overridden)
        System.out.println("\nRegular methods (can be overridden):");
        parent.regularMethod();
        child.regularMethod(); // This is overridden
        
        parent.displayInfo();
        child.displayInfo(); // This is overridden
        
        // 3. Final variable demonstration
        System.out.println("\n3. Final Variables:");
        FinalVariableDemo finalVarDemo = new FinalVariableDemo(42);
        finalVarDemo.demonstrateFinalVariables();
        
        // 4. Using final static constants
        System.out.println("\n4. Final Static Constants:");
        System.out.println("π = " + FinalVariableDemo.PI);
        System.out.println("Application: " + FinalVariableDemo.APP_NAME);
        
        double radius = 5.0;
        double area = MathUtils.calculateCircleArea(radius);
        System.out.println("Circle area (radius " + radius + "): " + String.format("%.2f", area));
        
        // 5. Final method in utility class
        System.out.println("\n5. Final Utility Methods:");
        int[] testNumbers = {2, 3, 4, 17, 25, 29};
        for (int num : testNumbers) {
            boolean isPrime = MathUtils.isPrime(num);
            System.out.println(num + " is " + (isPrime ? "prime" : "not prime"));
        }
        
        // 6. Polymorphism with final methods
        System.out.println("\n6. Polymorphism with Final Methods:");
        ParentClass[] objects = {
            new ParentClass("Parent1"),
            new ChildClass("Child1", 30),
            new ChildClass("Child2", 35)
        };
        
        for (ParentClass obj : objects) {
            obj.finalMethod();     // Same final method called for all
            obj.regularMethod();   // Different implementations called
            obj.displayInfo();     // Different implementations called
            System.out.println();
        }
        
        // 7. Demonstration of compilation errors
        System.out.println("7. Compilation Error Examples:");
        System.out.println("The following would cause compilation errors:");
        System.out.println("✗ class MyClass extends FinalClass { }");
        System.out.println("✗ Overriding final methods in subclasses");
        System.out.println("✗ Reassigning final variables after initialization");
        System.out.println("✗ Modifying final static constants");
        
        System.out.println("\n=== Final Keyword Summary ===");
        System.out.println("✓ final classes: Cannot be extended (e.g., String, Integer)");
        System.out.println("✓ final methods: Cannot be overridden in subclasses");
        System.out.println("✓ final variables: Cannot be reassigned after initialization");
        System.out.println("✓ final parameters: Cannot be modified within the method");
        System.out.println("✓ Ensures immutability and prevents unwanted inheritance/overriding");
        System.out.println("✓ Used for constants, critical algorithms, and security");
    }
}