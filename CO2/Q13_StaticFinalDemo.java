package c02;

// Program demonstrating static and final keywords
class Counter {
    // Static variable - shared among all instances of the class
    private static int count = 0;
    
    // Final constant - cannot be changed after initialization
    public final static int MAX = 100;
    
    // Instance variable
    private int instanceId;
    
    // Final instance variable - must be initialized in constructor
    private final String creationTime;
    
    // Constructor
    public Counter() {
        count++;  // Increment static variable for each object created
        this.instanceId = count;
        this.creationTime = java.time.LocalTime.now().toString();
        
        if (count > MAX) {
            System.out.println("Warning: Maximum counter limit (" + MAX + ") exceeded!");
        }
        
        System.out.println("Counter #" + instanceId + " created at " + creationTime);
    }
    
    // Constructor with custom message
    public Counter(String message) {
        this(); // Call default constructor
        System.out.println("Custom message: " + message);
    }
    
    // Static method to get current count
    public static int getCount() {
        return count;
    }
    
    // Static method to reset count
    public static void resetCount() {
        count = 0;
        System.out.println("Counter reset to 0");
    }
    
    // Static method to check if limit is reached
    public static boolean isLimitReached() {
        return count >= MAX;
    }
    
    // Instance method to get instance ID
    public int getInstanceId() {
        return instanceId;
    }
    
    // Instance method to get creation time (final variable)
    public String getCreationTime() {
        return creationTime;
    }
    
    // Instance method that uses static variable
    public void displayCounterInfo() {
        System.out.println("Instance ID: " + instanceId);
        System.out.println("Creation Time: " + creationTime);
        System.out.println("Total Counters Created: " + count);
        System.out.println("Maximum Allowed: " + MAX);
    }
    
    // Static method to display statistics
    public static void displayStatistics() {
        System.out.println("\n--- Counter Statistics ---");
        System.out.println("Total counters created: " + count);
        System.out.println("Maximum limit: " + MAX);
        System.out.println("Remaining capacity: " + (MAX - count));
        System.out.println("Limit reached: " + isLimitReached());
    }
}

// Class demonstrating final class (cannot be inherited)
final class FinalExample {
    // Final method (cannot be overridden)
    public final void finalMethod() {
        System.out.println("This method cannot be overridden");
    }
    
    // Final variable (constant)
    public final int FINAL_VALUE = 42;
    
    public void displayFinalValue() {
        System.out.println("Final value: " + FINAL_VALUE);
        // FINAL_VALUE = 50; // This would cause compile error
    }
}

// Utility class with static methods (common pattern)
class CounterUtils {
    // Static final constant
    public static final String VERSION = "1.0";
    
    // Static method to create multiple counters
    public static Counter[] createMultipleCounters(int num) {
        Counter[] counters = new Counter[num];
        for (int i = 0; i < num; i++) {
            counters[i] = new Counter("Batch created counter " + (i + 1));
        }
        return counters;
    }
    
    // Static method to find counter with specific ID
    public static Counter findCounterById(Counter[] counters, int id) {
        for (Counter counter : counters) {
            if (counter.getInstanceId() == id) {
                return counter;
            }
        }
        return null;
    }
}

public class Q13_StaticFinalDemo {
    
    // Static variable in main class
    private static int mainClassCount = 0;
    
    // Final static constant
    public static final String PROGRAM_NAME = "Static Final Demo";
    
    // Static block - executed once when class is first loaded
    static {
        System.out.println("Static block executed - Class loaded");
        mainClassCount = 1;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Static and Final Keywords Demonstration ===");
        System.out.println("Program: " + PROGRAM_NAME);
        
        // Demonstrate static variable access without creating objects
        System.out.println("\n1. Static Variable Access (before creating objects):");
        System.out.println("Current count: " + Counter.getCount());
        System.out.println("Maximum limit: " + Counter.MAX);
        
        // Create counter objects
        System.out.println("\n2. Creating Counter Objects:");
        Counter counter1 = new Counter();
        Counter counter2 = new Counter("Second counter");
        Counter counter3 = new Counter("Third counter");
        
        // Display count using static method
        System.out.println("\nAfter creating 3 counters:");
        System.out.println("Total count: " + Counter.getCount());
        
        // Display individual counter information
        System.out.println("\n3. Individual Counter Information:");
        counter1.displayCounterInfo();
        System.out.println();
        counter2.displayCounterInfo();
        
        // Demonstrate static method call
        System.out.println("\n4. Static Method Call:");
        Counter.displayStatistics();
        
        // Create more counters to test the limit
        System.out.println("\n5. Testing Maximum Limit:");
        System.out.println("Creating many counters...");
        
        // Create counters in a loop
        for (int i = 4; i <= 10; i++) {
            new Counter("Counter " + i);
            if (i % 3 == 0) {
                System.out.println("Current count: " + Counter.getCount());
            }
        }
        
        // Check if limit is reached
        if (Counter.isLimitReached()) {
            System.out.println("Limit reached!");
        }
        
        // Demonstrate final keyword
        System.out.println("\n6. Final Keyword Demonstration:");
        FinalExample finalEx = new FinalExample();
        finalEx.finalMethod();
        finalEx.displayFinalValue();
        
        // Demonstrate that final constants cannot be changed
        System.out.println("Attempting to change final constant:");
        System.out.println("// Counter.MAX = 200; - This would cause compile error!");
        
        // The following line would cause compile error if uncommented:
        // Counter.MAX = 200; // Compile error!
        
        // Demonstrate utility class with static methods
        System.out.println("\n7. Utility Class with Static Methods:");
        Counter[] batchCounters = CounterUtils.createMultipleCounters(3);
        System.out.println("Created batch of " + batchCounters.length + " counters");
        
        // Find specific counter
        Counter foundCounter = CounterUtils.findCounterById(batchCounters, 12);
        if (foundCounter != null) {
            System.out.println("Found counter with ID 12:");
            foundCounter.displayCounterInfo();
        }
        
        // Final statistics
        System.out.println("\n8. Final Statistics:");
        Counter.displayStatistics();
        
        // Demonstrate static variable behavior
        System.out.println("\n9. Static Variable Behavior:");
        System.out.println("All Counter objects share the same static variable 'count'");
        System.out.println("Each new Counter increments the shared count");
        System.out.println("Static methods can be called without creating objects");
        
        // Reset demonstration
        System.out.println("\n10. Reset Demonstration:");
        Counter.resetCount();
        System.out.println("After reset - Count: " + Counter.getCount());
        
        Counter newCounter = new Counter("After reset");
        System.out.println("New counter created - Count: " + Counter.getCount());
        
        System.out.println("\n=== Static and Final Summary ===");
        System.out.println("STATIC keyword:");
        System.out.println("✓ static variables - shared among all instances");
        System.out.println("✓ static methods - can be called without creating objects");
        System.out.println("✓ static blocks - executed when class is first loaded");
        System.out.println("✓ Memory efficient - only one copy exists");
        
        System.out.println("\nFINAL keyword:");
        System.out.println("✓ final variables - constants, cannot be changed");
        System.out.println("✓ final methods - cannot be overridden");
        System.out.println("✓ final classes - cannot be inherited");
        System.out.println("✓ Ensures immutability and security");
    }
}