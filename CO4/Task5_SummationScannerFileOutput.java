package co4;
import java.io.*;
import java.util.Scanner;

public class Task5_SummationScannerFileOutput {
    public static void main(String[] args) {
        String filename = "sum.txt";
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        try {
            System.out.print("Enter 5 integers: ");
            for (int i = 0; i < 5; i++) {
                sum += sc.nextInt();
            }
        } finally {
            sc.close();
        }
        // Write sum to file
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(sum);
            System.out.println("Sum written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        // Read sum from file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            System.out.println("Sum read from file: " + line);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
