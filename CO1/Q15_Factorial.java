import java.util.Scanner;

public class Q15_Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        
        if (n < 0) {
            System.out.println("Factorial is not defined for negative numbers");
        } else {
            long factorial = 1;
            
            // Using for loop
            for (int i = 1; i <= n; i++) {
                factorial *= i;
            }
            
            System.out.println("Factorial of " + n + " = " + factorial);
            
            // Alternative: Using while loop
            System.out.println("\nUsing while loop:");
            long factorial2 = 1;
            int i = 1;
            while (i <= n) {
                factorial2 *= i;
                i++;
            }
            System.out.println("Factorial of " + n + " = " + factorial2);
        }
        
        scanner.close();
    }
}