import java.util.Scanner;

public class Q5_FahrenheitToCelsius {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter temperature in Fahrenheit: ");
        double fahrenheit = scanner.nextDouble();
        
        // Celsius = (Fahrenheit - 32) * 5/9
        double celsius = (fahrenheit - 32) * 5.0 / 9.0;
        
        System.out.println(fahrenheit + "°F = " + celsius + "°C");
        
        scanner.close();
    }
}