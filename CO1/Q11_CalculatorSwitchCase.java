import java.util.Scanner;

public class Q11_CalculatorSwitchCase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        
        System.out.print("Enter operator (+, -, *, /): ");
        char operator = scanner.next().charAt(0);
        
        double result = 0;
        boolean validOperation = true;
        
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero is not allowed");
                    validOperation = false;
                }
                break;
            default:
                System.out.println("Error: Invalid operator");
                validOperation = false;
                break;
        }
        
        if (validOperation) {
            System.out.println(num1 + " " + operator + " " + num2 + " = " + result);
        }
        
        scanner.close();
    }
}