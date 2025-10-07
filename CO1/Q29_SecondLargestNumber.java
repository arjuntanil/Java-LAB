import java.util.Scanner;

public class Q29_SecondLargestNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        if (n < 2) {
            System.out.println("Array must have at least 2 elements to find second largest");
            scanner.close();
            return;
        }
        
        int[] array = new int[n];
        
        // Reading array elements
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }
        
        // Display original array
        System.out.println("\nOriginal array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        
        // Find largest and second largest
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        // First pass: find the largest
        for (int i = 0; i < n; i++) {
            if (array[i] > largest) {
                largest = array[i];
            }
        }
        
        // Second pass: find the second largest (must be different from largest)
        for (int i = 0; i < n; i++) {
            if (array[i] > secondLargest && array[i] < largest) {
                secondLargest = array[i];
            }
        }
        
        // Alternative method: Find both in single pass
        int largest2 = Integer.MIN_VALUE;
        int secondLargest2 = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            if (array[i] > largest2) {
                secondLargest2 = largest2;
                largest2 = array[i];
            } else if (array[i] > secondLargest2 && array[i] != largest2) {
                secondLargest2 = array[i];
            }
        }
        
        // Display results
        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("\n\nAll elements are the same. No second largest element exists.");
        } else {
            System.out.println("\n\nLargest element: " + largest);
            System.out.println("Second largest element: " + secondLargest);
            
            System.out.println("\nVerification (Single pass method):");
            System.out.println("Largest: " + largest2);
            System.out.println("Second largest: " + secondLargest2);
        }
        
        scanner.close();
    }
}