import java.util.Scanner;

public class Q30_CopyArrayElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        int[] sourceArray = new int[n];
        int[] destinationArray = new int[n];
        
        // Reading array elements
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            sourceArray[i] = scanner.nextInt();
        }
        
        // Display source array
        System.out.println("\nSource array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(sourceArray[i] + " ");
        }
        
        // Method 1: Manual copying using loop
        System.out.println("\n\nMethod 1: Manual copying using for loop");
        for (int i = 0; i < n; i++) {
            destinationArray[i] = sourceArray[i];
        }
        
        System.out.println("Destination array after copying: ");
        for (int i = 0; i < n; i++) {
            System.out.print(destinationArray[i] + " ");
        }
        
        // Method 2: Using System.arraycopy()
        System.out.println("\n\nMethod 2: Using System.arraycopy()");
        int[] destinationArray2 = new int[n];
        System.arraycopy(sourceArray, 0, destinationArray2, 0, n);
        
        System.out.println("Destination array 2 after copying: ");
        for (int i = 0; i < n; i++) {
            System.out.print(destinationArray2[i] + " ");
        }
        
        // Method 3: Using clone() method
        System.out.println("\n\nMethod 3: Using clone() method");
        int[] destinationArray3 = sourceArray.clone();
        
        System.out.println("Destination array 3 after copying: ");
        for (int i = 0; i < n; i++) {
            System.out.print(destinationArray3[i] + " ");
        }
        
        // Verify that arrays are independent (modify source and check copies)
        System.out.println("\n\nVerifying independence of arrays:");
        sourceArray[0] = 9999; // Modify first element of source array
        
        System.out.println("After modifying source array first element to 9999:");
        System.out.print("Source array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(sourceArray[i] + " ");
        }
        
        System.out.print("\nDestination array 1: ");
        for (int i = 0; i < n; i++) {
            System.out.print(destinationArray[i] + " ");
        }
        
        System.out.print("\nDestination array 2: ");
        for (int i = 0; i < n; i++) {
            System.out.print(destinationArray2[i] + " ");
        }
        
        System.out.print("\nDestination array 3: ");
        for (int i = 0; i < n; i++) {
            System.out.print(destinationArray3[i] + " ");
        }
        
        System.out.println("\n\nAll destination arrays remain unchanged, proving successful independent copying!");
        
        scanner.close();
    }
}