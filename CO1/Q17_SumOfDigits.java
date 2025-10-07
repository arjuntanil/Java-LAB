import java.util.Scanner;

public class Q17_SumOfDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        int originalNumber = number;
        int sum = 0;
        
        // Handle negative numbers
        if (number < 0) {
            number = -number;
        }
        
        while (number > 0) {
            int digit = number % 10;
            sum += digit;
            System.out.println("Digit: " + digit + ", Running sum: " + sum);
            number = number / 10;
        }
        
        System.out.println("\nOriginal number: " + originalNumber);
        System.out.println("Sum of digits: " + sum);
        
        scanner.close();
    }
}