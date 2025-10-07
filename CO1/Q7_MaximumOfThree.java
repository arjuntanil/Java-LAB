import java.util.Scanner;

public class Q7_MaximumOfThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();
        
        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();
        
        System.out.print("Enter third number: ");
        int num3 = scanner.nextInt();
        
        // Using conditional (ternary) operator
        int max = (num1 > num2) ? ((num1 > num3) ? num1 : num3) : ((num2 > num3) ? num2 : num3);
        
        System.out.println("Maximum of " + num1 + ", " + num2 + " and " + num3 + " is: " + max);
        
        scanner.close();
    }
}