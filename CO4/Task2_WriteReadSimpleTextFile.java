package co4;
import java.io.*;

public class Task2_WriteReadSimpleTextFile {
    public static void main(String[] args) {
        String filename = "welcome.txt";
        // Write to file
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("Welcome to Java File I/O!\n");
            System.out.println("Wrote to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        // Read from file
        System.out.println("Reading from file: " + filename);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("From file: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
