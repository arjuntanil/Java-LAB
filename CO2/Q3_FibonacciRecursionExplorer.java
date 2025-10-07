package c02;
import java.util.Scanner;

// Fibonacci Recursion Explorer - Demonstrating Recursion, Static Variables
public class Q3_FibonacciRecursionExplorer {
    private static int callCount = 0; // Static variable to track recursive calls
    
    // Recursive method to calculate Fibonacci number
    public static int fibonacci(int n) {
        callCount++; // Increment call count each time method is invoked
        
        // Base cases
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        
        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Method to reset call count
    public static void resetCallCount() {
        callCount = 0;
    }
    
    // Method to get current call count
    public static int getCallCount() {
        return callCount;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Fibonacci Recursion Explorer ===");
        
        // Accept number of Fibonacci numbers to generate
        System.out.print("Enter the number of Fibonacci numbers to generate: ");
        int n = scanner.nextInt();
        
        if (n <= 0) {
            System.out.println("Please enter a positive number.");
            scanner.close();
            return;
        }
        
        System.out.println("\nGenerating first " + n + " Fibonacci numbers:");
        System.out.println("===========================================");
        
        // Reset call count before starting
        resetCallCount();
        
        // Generate and display Fibonacci numbers
        for (int i = 0; i < n; i++) {
            int callCountBefore = getCallCount();
            int fibNumber = fibonacci(i);
            int callCountAfter = getCallCount();
            
            System.out.println("F(" + i + ") = " + fibNumber + " [Calls made for this number: " + (callCountAfter - callCountBefore) + "]");
        }
        
        // Display total recursive calls
        System.out.println("\n=== RECURSION ANALYSIS ===");
        System.out.println("Total recursive calls made: " + getCallCount());
        System.out.println("Average calls per number: " + String.format("%.2f", (double)getCallCount() / n));
        
        // Demonstrate the inefficiency of naive recursion
        System.out.println("\n=== EFFICIENCY DEMONSTRATION ===");
        if (n >= 5) {
            resetCallCount();
            System.out.println("Calculating F(" + (n-1) + ") alone...");
            int lastFib = fibonacci(n-1);
            System.out.println("F(" + (n-1) + ") = " + lastFib);
            System.out.println("Calls made for just F(" + (n-1) + "): " + getCallCount());
        }
        
        // Show the exponential growth of calls
        System.out.println("\n=== CALL COUNT ANALYSIS ===");
        System.out.println("Notice how the number of recursive calls grows exponentially!");
        System.out.println("This demonstrates why naive recursive Fibonacci is inefficient for large numbers.");
        
        scanner.close();
    }
}