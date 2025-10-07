package co4;
import java.util.Scanner;

public class Task3_CatchingMultipleExceptions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        String input = sc.nextLine();
        try {
            int num = Integer.parseInt(input);
            int result = 100 / num;
            System.out.println("Result is: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format!");
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
        }
    }
}
