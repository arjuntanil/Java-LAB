import java.util.Scanner;

public class Q23_MaxMinInArray {
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
        
        // Initialize max and min with first element
        int max = array[0];
        int min = array[0];
        int maxIndex = 0;
        int minIndex = 0;
        
        // Find maximum and minimum
        for (int i = 1; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }
        
        // Display results
        System.out.println("\nArray elements: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        
        System.out.println("\n\nMaximum element: " + max + " at index " + maxIndex);
        System.out.println("Minimum element: " + min + " at index " + minIndex);
        
        scanner.close();
    }
}