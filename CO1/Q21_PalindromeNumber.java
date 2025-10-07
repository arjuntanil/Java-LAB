import java.util.Scanner;

public class Q21_PalindromeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        int originalNumber = number;
        int reversedNumber = 0;
        int temp = number;
        
        // Handle negative numbers (they can't be palindromes in typical definition)
        if (number < 0) {
            System.out.println("Negative numbers are not considered palindromes");
            scanner.close();
            return;
        }
        
        // Reverse the number
        while (temp > 0) {
            int digit = temp % 10;
            reversedNumber = reversedNumber * 10 + digit;
            temp /= 10;
        }
        
        System.out.println("Original number: " + originalNumber);
        System.out.println("Reversed number: " + reversedNumber);
        
        if (originalNumber == reversedNumber) {
            System.out.println(originalNumber + " is a Palindrome number");
        } else {
            System.out.println(originalNumber + " is not a Palindrome number");
        }
        
        System.out.println("\nExamples of Palindrome numbers: 121, 1331, 12321, 7, 11, 101");
        
        scanner.close();
    }
}