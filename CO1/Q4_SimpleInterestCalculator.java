import java.util.Scanner;

public class Q4_SimpleInterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Principal amount: ");
        double principal = scanner.nextDouble();
        
        System.out.print("Enter Rate of interest: ");
        double rate = scanner.nextDouble();
        
        System.out.print("Enter Time period (in years): ");
        double time = scanner.nextDouble();
        
        // Simple Interest = (P × R × T) / 100
        double simpleInterest = (principal * rate * time) / 100;
        
        System.out.println("\nPrincipal: " + principal);
        System.out.println("Rate: " + rate + "%");
        System.out.println("Time: " + time + " years");
        System.out.println("Simple Interest: " + simpleInterest);
        System.out.println("Total Amount: " + (principal + simpleInterest));
        
        scanner.close();
    }
}