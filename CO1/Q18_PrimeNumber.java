import java.util.Scanner;

public class Q18_PrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (number <= 1) {
            System.out.println(number + " is not a prime number");
        } else if (number == 2) {
            System.out.println(number + " is a prime number");
        } else if (number % 2 == 0) {
            System.out.println(number + " is not a prime number (divisible by 2)");
        } else {
            boolean isPrime = true;
            
            // Check for factors from 3 to sqrt(number)
            for (int i = 3; i * i <= number; i += 2) {
                if (number % i == 0) {
                    isPrime = false;
                    System.out.println(number + " is not a prime number (divisible by " + i + ")");
                    break;
                }
            }
            
            if (isPrime) {
                System.out.println(number + " is a prime number");
            }
        }
        
        scanner.close();
    }
}