package co4;
import java.io.*;

public class Task2_FileReadingWithExceptionHandling {
    public static void readFile() throws IOException {
        System.out.println("Trying to open 'data.txt'");
        BufferedReader br = new BufferedReader(new FileReader("data.txt"));
        String line = br.readLine();
        if (line != null) {
            System.out.println("File contains: " + line);
        }
        br.close();
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }
}
