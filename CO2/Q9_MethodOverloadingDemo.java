package c02;
import java.util.Scanner;

// Program demonstrating Method Overloading
class Calculator {
    
    // Overloaded method 1: Add two integers
    public int add(int a, int b) {
        System.out.println("add(int, int) called");
        return a + b;
    }
    
    // Overloaded method 2: Add two double values
    public double add(double a, double b) {
        System.out.println("add(double, double) called");
        return a + b;
    }
    
    // Overloaded method 3: Add three integers
    public int add(int a, int b, int c) {
        System.out.println("add(int, int, int) called");
        return a + b + c;
    }
    
    // Overloaded method 4: Add array of integers
    public int add(int[] numbers) {
        System.out.println("add(int[]) called");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
    
    // Additional overloaded methods for other operations
    
    // Multiply - two integers
    public int multiply(int a, int b) {
        System.out.println("multiply(int, int) called");
        return a * b;
    }
    
    // Multiply - two doubles
    public double multiply(double a, double b) {
        System.out.println("multiply(double, double) called");
        return a * b;
    }
    
    // Multiply - three integers
    public int multiply(int a, int b, int c) {
        System.out.println("multiply(int, int, int) called");
        return a * b * c;
    }
    
    // Calculate power - integer base and exponent
    public long power(int base, int exponent) {
        System.out.println("power(int, int) called");
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    
    // Calculate power - double base and integer exponent
    public double power(double base, int exponent) {
        System.out.println("power(double, int) called");
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}

public class Q9_MethodOverloadingDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();
        
        System.out.println("=== Method Overloading Demonstration ===");
        
        // Demonstrate add method overloading
        System.out.println("\n1. Addition Method Overloading:");
        
        // Call add(int, int)
        int result1 = calc.add(10, 20);
        System.out.println("Result: " + result1);
        System.out.println();
        
        // Call add(double, double)
        double result2 = calc.add(10.5, 20.7);
        System.out.println("Result: " + result2);
        System.out.println();
        
        // Call add(int, int, int)
        int result3 = calc.add(10, 20, 30);
        System.out.println("Result: " + result3);
        System.out.println();
        
        // Call add(int[])
        int[] numbers = {1, 2, 3, 4, 5};
        int result4 = calc.add(numbers);
        System.out.println("Result: " + result4);
        System.out.println();
        
        // Interactive demonstration
        System.out.println("2. Interactive Method Overloading:");
        System.out.println("Choose operation:");
        System.out.println("1. Add two integers");
        System.out.println("2. Add two doubles");
        System.out.println("3. Add three integers");
        System.out.println("4. Multiply two integers");
        System.out.println("5. Multiply two doubles");
        System.out.println("6. Calculate power (int^int)");
        System.out.println("7. Calculate power (double^int)");
        
        System.out.print("Enter choice (1-7): ");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                System.out.print("Enter first integer: ");
                int a1 = scanner.nextInt();
                System.out.print("Enter second integer: ");
                int b1 = scanner.nextInt();
                int addResult1 = calc.add(a1, b1);
                System.out.println("Result: " + addResult1);
                break;
                
            case 2:
                System.out.print("Enter first double: ");
                double a2 = scanner.nextDouble();
                System.out.print("Enter second double: ");
                double b2 = scanner.nextDouble();
                double addResult2 = calc.add(a2, b2);
                System.out.println("Result: " + addResult2);
                break;
                
            case 3:
                System.out.print("Enter first integer: ");
                int a3 = scanner.nextInt();
                System.out.print("Enter second integer: ");
                int b3 = scanner.nextInt();
                System.out.print("Enter third integer: ");
                int c3 = scanner.nextInt();
                int addResult3 = calc.add(a3, b3, c3);
                System.out.println("Result: " + addResult3);
                break;
                
            case 4:
                System.out.print("Enter first integer: ");
                int m1 = scanner.nextInt();
                System.out.print("Enter second integer: ");
                int m2 = scanner.nextInt();
                int mulResult1 = calc.multiply(m1, m2);
                System.out.println("Result: " + mulResult1);
                break;
                
            case 5:
                System.out.print("Enter first double: ");
                double md1 = scanner.nextDouble();
                System.out.print("Enter second double: ");
                double md2 = scanner.nextDouble();
                double mulResult2 = calc.multiply(md1, md2);
                System.out.println("Result: " + mulResult2);
                break;
                
            case 6:
                System.out.print("Enter base (integer): ");
                int base1 = scanner.nextInt();
                System.out.print("Enter exponent: ");
                int exp1 = scanner.nextInt();
                long powResult1 = calc.power(base1, exp1);
                System.out.println("Result: " + powResult1);
                break;
                
            case 7:
                System.out.print("Enter base (double): ");
                double base2 = scanner.nextDouble();
                System.out.print("Enter exponent: ");
                int exp2 = scanner.nextInt();
                double powResult2 = calc.power(base2, exp2);
                System.out.println("Result: " + powResult2);
                break;
                
            default:
                System.out.println("Invalid choice!");
                break;
        }
        
        // Demonstrate automatic method selection based on parameters
        System.out.println("\n3. Automatic Method Selection:");
        System.out.println("Java automatically selects the correct overloaded method based on:");
        System.out.println("- Number of parameters");
        System.out.println("- Type of parameters");
        System.out.println("- Order of parameters");
        
        // Examples of automatic selection
        System.out.println("\nExamples:");
        calc.add(5, 10);           // Calls add(int, int)
        calc.add(5.5, 10.3);       // Calls add(double, double)
        calc.add(1, 2, 3);         // Calls add(int, int, int)
        
        // Type promotion example
        System.out.println("\n4. Type Promotion in Overloading:");
        calc.multiply(5, 10);      // int, int -> calls multiply(int, int)
        calc.multiply(5.0, 10.0);  // double, double -> calls multiply(double, double)
        // calc.multiply(5, 10.0); // This would cause ambiguity without explicit casting
        
        System.out.println("\n=== Method Overloading Summary ===");
        System.out.println("✓ Same method name, different parameters (number, type, or order)");
        System.out.println("✓ Compile-time polymorphism (resolved at compile time)");
        System.out.println("✓ Return type alone cannot distinguish overloaded methods");
        System.out.println("✓ Helps in creating methods that perform similar operations on different data types");
        
        scanner.close();
    }
}