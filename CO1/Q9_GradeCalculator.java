import java.util.Scanner;

public class Q9_GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter marks (0-100): ");
        int marks = scanner.nextInt();
        
        char grade;
        
        if (marks >= 90) {
            grade = 'A';
        } else if (marks >= 80) {
            grade = 'B';
        } else if (marks >= 70) {
            grade = 'C';
        } else if (marks >= 60) {
            grade = 'D';
        } else if (marks >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }
        
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        
        // Additional feedback
        if (grade == 'F') {
            System.out.println("Status: Fail");
        } else {
            System.out.println("Status: Pass");
        }
        
        scanner.close();
    }
}