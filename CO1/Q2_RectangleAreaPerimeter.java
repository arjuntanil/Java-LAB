import java.util.Scanner;

public class Q2_RectangleAreaPerimeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter length of rectangle: ");
        double length = scanner.nextDouble();
        
        System.out.print("Enter breadth of rectangle: ");
        double breadth = scanner.nextDouble();
        
        double area = length * breadth;
        double perimeter = 2 * (length + breadth);
        
        System.out.println("Area of rectangle: " + area);
        System.out.println("Perimeter of rectangle: " + perimeter);
        
        scanner.close();
    }
}