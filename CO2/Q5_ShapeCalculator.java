package c02;
import java.util.Scanner;

// Shape Calculator - Demonstrating Method Overloading, Constructors, final keyword
class Shape {
    // Final static variable for PI
    public final static double PI = 3.14159;
    
    // Default constructor
    public Shape() {
        System.out.println("Shape Calculator Initialized");
    }
    
    // Overloaded method 1: Calculate area of square (one parameter)
    public double calculateArea(double side) {
        return side * side;
    }
    
    // Overloaded method 2: Calculate area of rectangle (two parameters)
    public double calculateArea(double length, double breadth) {
        return length * breadth;
    }
    
    // Overloaded method 3: Calculate area of circle (using radius and PI constant)
    public double calculateCircleArea(double radius) {
        return PI * radius * radius;
    }
    
    // Additional overloaded method: Calculate area of triangle
    public double calculateTriangleArea(double base, double height) {
        return 0.5 * base * height;
    }
}

public class Q5_ShapeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Shape Calculator Program ===");
        
        // Create Shape object (calls default constructor)
        Shape shape = new Shape();
        
        System.out.println("\nChoose shapes to calculate areas:");
        System.out.println("1. Square");
        System.out.println("2. Rectangle");
        System.out.println("3. Circle");
        System.out.println("4. Triangle");
        System.out.println("5. Calculate all with default values");
        
        System.out.print("\nEnter your choice (1-5): ");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                // Calculate area of square
                System.out.print("Enter side of square: ");
                double side = scanner.nextDouble();
                double squareArea = shape.calculateArea(side);
                System.out.println("Area of square (side = " + side + "): " + squareArea);
                break;
                
            case 2:
                // Calculate area of rectangle
                System.out.print("Enter length of rectangle: ");
                double length = scanner.nextDouble();
                System.out.print("Enter breadth of rectangle: ");
                double breadth = scanner.nextDouble();
                double rectangleArea = shape.calculateArea(length, breadth);
                System.out.println("Area of rectangle (" + length + " x " + breadth + "): " + rectangleArea);
                break;
                
            case 3:
                // Calculate area of circle
                System.out.print("Enter radius of circle: ");
                double radius = scanner.nextDouble();
                double circleArea = shape.calculateCircleArea(radius);
                System.out.println("Area of circle (radius = " + radius + "): " + String.format("%.3f", circleArea));
                System.out.println("Using PI constant: " + Shape.PI);
                break;
                
            case 4:
                // Calculate area of triangle
                System.out.print("Enter base of triangle: ");
                double base = scanner.nextDouble();
                System.out.print("Enter height of triangle: ");
                double height = scanner.nextDouble();
                double triangleArea = shape.calculateTriangleArea(base, height);
                System.out.println("Area of triangle (base = " + base + ", height = " + height + "): " + triangleArea);
                break;
                
            case 5:
                // Calculate all areas with predefined values
                System.out.println("\n=== Calculating all shapes with default values ===");
                
                // Square area
                double defaultSquareArea = shape.calculateArea(5.0);
                System.out.println("Area of square (side = 5): " + defaultSquareArea);
                
                // Rectangle area
                double defaultRectangleArea = shape.calculateArea(5.0, 10.0);
                System.out.println("Area of rectangle (5 x 10): " + defaultRectangleArea);
                
                // Circle area
                double defaultCircleArea = shape.calculateCircleArea(7.0);
                System.out.println("Area of circle (radius = 7): " + String.format("%.3f", defaultCircleArea));
                
                // Triangle area
                double defaultTriangleArea = shape.calculateTriangleArea(6.0, 8.0);
                System.out.println("Area of triangle (base = 6, height = 8): " + defaultTriangleArea);
                break;
                
            default:
                System.out.println("Invalid choice!");
                break;
        }
        
        // Demonstrate method overloading
        System.out.println("\n=== Method Overloading Demonstration ===");
        System.out.println("The calculateArea() method is overloaded:");
        System.out.println("1. calculateArea(double side) - for square");
        System.out.println("2. calculateArea(double length, double breadth) - for rectangle");
        System.out.println("3. calculateCircleArea(double radius) - for circle");
        System.out.println("4. calculateTriangleArea(double base, double height) - for triangle");
        
        // Demonstrate final constant
        System.out.println("\n=== Final Constant Demonstration ===");
        System.out.println("PI is a final static constant: " + Shape.PI);
        System.out.println("This value cannot be changed after initialization.");
        
        // The following line would cause a compile error if uncommented:
        // Shape.PI = 3.14;  // Compile error - cannot assign to final variable
        
        System.out.println("\nProgram completed successfully!");
        scanner.close();
    }
}