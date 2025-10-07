import java.util.Scanner;

public class Q25_LinearSearchArray {
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
        
        System.out.print("Enter the element to search: ");
        int searchElement = scanner.nextInt();
        
        // Linear search
        boolean found = false;
        int position = -1;
        
        for (int i = 0; i < n; i++) {
            if (array[i] == searchElement) {
                found = true;
                position = i;
                break; // Stop at first occurrence
            }
        }
        
        // Display array
        System.out.println("\nArray elements: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        
        // Display search result
        if (found) {
            System.out.println("\n\nElement " + searchElement + " found at index " + position + " (position " + (position + 1) + ")");
        } else {
            System.out.println("\n\nElement " + searchElement + " not found in the array");
        }
        
        // Find all occurrences
        System.out.println("\nAll occurrences of " + searchElement + ":");
        boolean anyFound = false;
        for (int i = 0; i < n; i++) {
            if (array[i] == searchElement) {
                System.out.println("Found at index " + i + " (position " + (i + 1) + ")");
                anyFound = true;
            }
        }
        
        if (!anyFound) {
            System.out.println("No occurrences found");
        }
        
        scanner.close();
    }
}