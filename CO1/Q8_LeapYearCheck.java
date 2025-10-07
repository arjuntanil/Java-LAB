import java.util.Scanner;

public class Q8_LeapYearCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        
        // A year is leap if:
        // 1. It is divisible by 4 AND not divisible by 100
        // 2. OR it is divisible by 400
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        
        if (isLeap) {
            System.out.println(year + " is a Leap Year");
        } else {
            System.out.println(year + " is not a Leap Year");
        }
        
        scanner.close();
    }
}