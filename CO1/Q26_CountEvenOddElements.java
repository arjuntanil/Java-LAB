import java.util.Scanner;

public class Q26_CountEvenOddElements {
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
        
        // Count even and odd elements
        int evenCount = 0;
        int oddCount = 0;
        
        System.out.println("\nAnalyzing elements:");
        for (int i = 0; i < n; i++) {
            if (array[i] % 2 == 0) {
                System.out.println(array[i] + " is Even");
                evenCount++;
            } else {
                System.out.println(array[i] + " is Odd");
                oddCount++;
            }
        }
        
        // Display array
        System.out.println("\nArray elements: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        
        // Display counts
        System.out.println("\n\nCount Summary:");
        System.out.println("Even elements: " + evenCount);
        System.out.println("Odd elements: " + oddCount);
        System.out.println("Total elements: " + (evenCount + oddCount));
        
        // Display even and odd elements separately
        System.out.println("\nEven elements in array:");
        for (int i = 0; i < n; i++) {
            if (array[i] % 2 == 0) {
                System.out.print(array[i] + " ");
            }
        }
        
        System.out.println("\n\nOdd elements in array:");
        for (int i = 0; i < n; i++) {
            if (array[i] % 2 != 0) {
                System.out.print(array[i] + " ");
            }
        }
        
        scanner.close();
    }
}