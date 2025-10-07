import java.util.Scanner;

public class Q16_ReverseNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        int originalNumber = number;
        int reversedNumber = 0;
        
        // Handle negative numbers
        boolean isNegative = number < 0;
        if (isNegative) {
            number = -number;
        }
        
        while (number > 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number = number / 10;
        }
        
        if (isNegative) {
            reversedNumber = -reversedNumber;
        }
        
        System.out.println("Original number: " + originalNumber);
        System.out.println("Reversed number: " + reversedNumber);
        
        scanner.close();
    }
}