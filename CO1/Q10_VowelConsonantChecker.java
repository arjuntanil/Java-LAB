import java.util.Scanner;

public class Q10_VowelConsonantChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a character: ");
        char ch = scanner.next().charAt(0);
        
        // Convert to lowercase for easy checking
        ch = Character.toLowerCase(ch);
        
        // Method 1: Using if-else
        System.out.println("\nMethod 1: Using if-else");
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            System.out.println(ch + " is a Vowel");
        } else if (ch >= 'a' && ch <= 'z') {
            System.out.println(ch + " is a Consonant");
        } else {
            System.out.println(ch + " is not an alphabet");
        }
        
        // Method 2: Using switch-case
        System.out.println("\nMethod 2: Using switch-case");
        switch (ch) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                System.out.println(ch + " is a Vowel");
                break;
            default:
                if (ch >= 'a' && ch <= 'z') {
                    System.out.println(ch + " is a Consonant");
                } else {
                    System.out.println(ch + " is not an alphabet");
                }
                break;
        }
        
        scanner.close();
    }
}