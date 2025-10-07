package co4;
public class Task5_FinallyClauseDemo {
    public static void main(String[] args) {
        System.out.println("Resource opened");
        try {
            int x = 10 / 0;
            System.out.println("This will not print");
        } catch (Exception e) {
            System.out.println("Exception occurred!");
        } finally {
            System.out.println("Resource closed");
        }
        System.out.println("Program continues after try-catch-finally.");
    }
}
