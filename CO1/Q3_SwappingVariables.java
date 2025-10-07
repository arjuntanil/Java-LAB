import java.util.Scanner;

public class Q3_SwappingVariables {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        int a = scanner.nextInt();
        
        System.out.print("Enter second number: ");
        int b = scanner.nextInt();
        
        System.out.println("\nBefore swapping:");
        System.out.println("a = " + a + ", b = " + b);
        
        // Method 1: Using third variable
        System.out.println("\nMethod 1: Using third variable");
        int temp = a;
        a = b;
        b = temp;
        System.out.println("After swapping: a = " + a + ", b = " + b);
        
        // Reset values for second method
        temp = a;
        a = b;
        b = temp;
        
        // Method 2: Without using third variable
        System.out.println("\nMethod 2: Without using third variable");
        System.out.println("Before: a = " + a + ", b = " + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("After swapping: a = " + a + ", b = " + b);
        
        scanner.close();
    }
}