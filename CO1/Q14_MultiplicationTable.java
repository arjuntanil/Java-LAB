import java.util.Scanner;

public class Q14_MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        System.out.println("\nMultiplication Table of " + number + ":");
        System.out.println("========================");
        
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " × " + i + " = " + (number * i));
        }
        
        scanner.close();
    }
}