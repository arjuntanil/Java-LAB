import java.util.Scanner;

public class Q22_ReadPrintArrayElements {
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
        
        // Displaying array elements
        System.out.println("\nArray elements are:");
        for (int i = 0; i < n; i++) {
            System.out.println("array[" + i + "] = " + array[i]);
        }
        
        // Alternative display using enhanced for loop
        System.out.println("\nUsing enhanced for loop:");
        int index = 0;
        for (int element : array) {
            System.out.println("array[" + index + "] = " + element);
            index++;
        }
        
        scanner.close();
    }
}