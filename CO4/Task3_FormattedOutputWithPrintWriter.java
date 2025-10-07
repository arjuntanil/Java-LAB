package co4;
import java.io.*;

public class Task3_FormattedOutputWithPrintWriter {
    public static void main(String[] args) {
        String filename = "report.txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("Report Title: Student Scores");
            pw.printf("Student 1: Score = %d\n", 85);
            pw.printf("Student 2: Score = %d\n", 93);
            System.out.println("Data written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        // Display file contents
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
