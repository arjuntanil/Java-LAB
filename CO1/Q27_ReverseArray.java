import java.util.Scanner;

public class Q27_ReverseArray {
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
        
        // Method 1: Display in reverse order (without modifying original array)
        System.out.println("\n\nMethod 1 - Display in reverse order:");
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        
        // Method 2: Actually reverse the array elements
        System.out.println("\n\nMethod 2 - Reverse the array elements:");
        for (int i = 0; i < n / 2; i++) {
            // Swap elements from start and end
            int temp = array[i];
            array[i] = array[n - 1 - i];
            array[n - 1 - i] = temp;
        }
        
        System.out.println("Array after reversing:");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        
        // Method 3: Using a new array for reversed elements
        int[] reversedArray = new int[n];
        for (int i = 0; i < n; i++) {
            reversedArray[i] = array[n - 1 - i];
        }
        
        System.out.println("\n\nMethod 3 - Using new array:");
        System.out.print("Reversed array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(reversedArray[i] + " ");
        }
        
        scanner.close();
    }
}