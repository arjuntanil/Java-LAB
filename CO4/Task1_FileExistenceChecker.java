package co4;
import java.io.File;
import java.util.Scanner;

public class Task1_FileExistenceChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter the filename: ");
            String filename = sc.nextLine();
            File file = new File(filename);
            System.out.println("File name: " + file.getName());
            System.out.println("File exists: " + file.exists());
            System.out.println("Absolute path: " + file.getAbsolutePath());
        } finally {
            sc.close();
        }
    }
}
