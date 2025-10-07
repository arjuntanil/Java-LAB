import java.util.Scanner;

public class Q24_SumAverageArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        int[] array = new int[n];
        
        // Reading array elements
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }
        
        // Calculate sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        
        // Calculate average
        double average = (double) sum / n;
        
        // Display results
        System.out.println("\nArray elements: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        
        System.out.println("\n\nSum of elements: " + sum);
        System.out.println("Average of elements: " + average);
        System.out.println("Average (formatted): " + String.format("%.2f", average));
        
        scanner.close();
    }
}