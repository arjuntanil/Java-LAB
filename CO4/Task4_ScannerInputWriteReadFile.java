package co4;
import java.io.*;
import java.util.Scanner;

public class Task4_ScannerInputWriteReadFile {
    public static void main(String[] args) {
        String filename = "user_input.txt";
        Scanner sc = new Scanner(System.in);
        String[] lines = new String[3];
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter line " + (i+1) + ": ");
                lines[i] = sc.nextLine();
            }
        } finally {
            sc.close();
        }
        // Write to file
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (String line : lines) {
                pw.println(line);
            }
            System.out.println("Wrote input to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        // Read and display file contents
        System.out.println("Reading file contents:");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
