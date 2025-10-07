import java.util.Scanner;

public class Q28_SortArrayAscending {
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
        
        // Display original array
        System.out.println("\nOriginal array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        
        // Bubble Sort Algorithm
        System.out.println("\n\nSorting using Bubble Sort...");
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // If no swapping occurred, array is already sorted
            if (!swapped) {
                System.out.println("Array sorted after " + (i + 1) + " pass(es)");
                break;
            }
            
            // Show array after each pass
            System.out.print("After pass " + (i + 1) + ": ");
            for (int k = 0; k < n; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
        
        // Display sorted array
        System.out.println("\nSorted array (Ascending order): ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        
        scanner.close();
    }
}