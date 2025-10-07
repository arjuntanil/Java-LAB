import java.util.Scanner;

public class Q20_ArmstrongNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        int originalNumber = number;
        int numDigits = 0;
        int temp = number;
        
        // Count number of digits
        while (temp > 0) {
            numDigits++;
            temp /= 10;
        }
        
        int sum = 0;
        temp = number;
        
        // Calculate sum of digits raised to power of number of digits
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, numDigits);
            temp /= 10;
        }
        
        System.out.println("Number: " + originalNumber);
        System.out.println("Number of digits: " + numDigits);
        System.out.println("Sum of digits^" + numDigits + ": " + sum);
        
        if (sum == originalNumber) {
            System.out.println(originalNumber + " is an Armstrong number");
        } else {
            System.out.println(originalNumber + " is not an Armstrong number");
        }
        
        // Example: 153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153
        System.out.println("\nNote: Examples of Armstrong numbers:");
        System.out.println("153 (3-digit): 1³ + 5³ + 3³ = 1 + 125 + 27 = 153");
        System.out.println("9474 (4-digit): 9⁴ + 4⁴ + 7⁴ + 4⁴ = 6561 + 256 + 2401 + 256 = 9474");
        
        scanner.close();
    }
}