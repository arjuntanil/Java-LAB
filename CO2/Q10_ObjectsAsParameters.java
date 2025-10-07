package c02;
import java.util.Scanner;

// Program demonstrating Objects as Parameters
class Rectangle {
    private double length;
    private double breadth;
    
    // Constructor
    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }
    
    // Method to calculate area
    public double calculateArea() {
        return length * breadth;
    }
    
    // Method to compare area with another rectangle (object as parameter)
    public void compareArea(Rectangle other) {
        double thisArea = this.calculateArea();
        double otherArea = other.calculateArea();
        
        System.out.println("\n--- Area Comparison ---");
        System.out.println("Rectangle 1 (" + this.length + " x " + this.breadth + ") Area: " + thisArea);
        System.out.println("Rectangle 2 (" + other.length + " x " + other.breadth + ") Area: " + otherArea);
        
        if (thisArea > otherArea) {
            System.out.println("Rectangle 1 has larger area");
        } else if (thisArea < otherArea) {
            System.out.println("Rectangle 2 has larger area");
        } else {
            System.out.println("Both rectangles have equal area");
        }
        
        double difference = Math.abs(thisArea - otherArea);
        System.out.println("Area difference: " + String.format("%.2f", difference));
    }
    
    // Method to check if this rectangle can fit inside another (object as parameter)
    public boolean canFitInside(Rectangle container) {
        return (this.length <= container.length && this.breadth <= container.breadth) ||
               (this.length <= container.breadth && this.breadth <= container.length);
    }
    
    // Method to find the combined area when placed side by side (object as parameter)
    public double combinedAreaSideBySide(Rectangle other) {
        // Assuming rectangles are placed side by side along length
        double combinedLength = this.length + other.length;
        double maxBreadth = Math.max(this.breadth, other.breadth);
        return combinedLength * maxBreadth;
    }
    
    // Method to display rectangle details
    public void displayRectangle() {
        System.out.println("Length: " + length + ", Breadth: " + breadth + ", Area: " + calculateArea());
    }
    
    // Getters
    public double getLength() {
        return length;
    }
    
    public double getBreadth() {
        return breadth;
    }
}

// Helper class with static methods that take objects as parameters
class RectangleUtils {
    
    // Static method to find rectangle with larger area (objects as parameters)
    public static Rectangle getLargerRectangle(Rectangle rect1, Rectangle rect2) {
        if (rect1.calculateArea() >= rect2.calculateArea()) {
            return rect1;
        } else {
            return rect2;
        }
    }
    
    // Static method to calculate total area of multiple rectangles (array of objects)
    public static double calculateTotalArea(Rectangle[] rectangles) {
        double totalArea = 0;
        for (Rectangle rect : rectangles) {
            totalArea += rect.calculateArea();
        }
        return totalArea;
    }
    
    // Static method to swap dimensions of two rectangles (objects as parameters)
    public static void swapDimensions(Rectangle rect1, Rectangle rect2) {
        // Note: This demonstrates that object references are passed by value
        // We can modify the object's state, but we cannot change what the reference points to
        
        double tempLength1 = rect1.getLength();
        double tempBreadth1 = rect1.getBreadth();
        
        // We would need setter methods to actually swap, but this demonstrates the concept
        System.out.println("Swapping dimensions concept demonstrated (would need setters to implement)");
    }
}

public class Q10_ObjectsAsParameters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Objects as Parameters Demonstration ===");
        
        // Create first rectangle
        System.out.println("\nCreating Rectangle 1:");
        System.out.print("Enter length: ");
        double length1 = scanner.nextDouble();
        System.out.print("Enter breadth: ");
        double breadth1 = scanner.nextDouble();
        Rectangle rect1 = new Rectangle(length1, breadth1);
        
        // Create second rectangle
        System.out.println("\nCreating Rectangle 2:");
        System.out.print("Enter length: ");
        double length2 = scanner.nextDouble();
        System.out.print("Enter breadth: ");
        double breadth2 = scanner.nextDouble();
        Rectangle rect2 = new Rectangle(length2, breadth2);
        
        // Display both rectangles
        System.out.println("\n=== Rectangle Details ===");
        System.out.print("Rectangle 1: ");
        rect1.displayRectangle();
        System.out.print("Rectangle 2: ");
        rect2.displayRectangle();
        
        // Demonstrate object as parameter - compare areas
        System.out.println("\n1. Comparing Areas (Object as Parameter):");
        rect1.compareArea(rect2);  // Passing rect2 as parameter to rect1's method
        
        // Demonstrate reverse comparison
        System.out.println("\n2. Reverse Comparison:");
        rect2.compareArea(rect1);  // Passing rect1 as parameter to rect2's method
        
        // Check if one rectangle can fit inside another
        System.out.println("\n3. Checking if Rectangles Fit Inside Each Other:");
        if (rect1.canFitInside(rect2)) {
            System.out.println("Rectangle 1 can fit inside Rectangle 2");
        } else {
            System.out.println("Rectangle 1 cannot fit inside Rectangle 2");
        }
        
        if (rect2.canFitInside(rect1)) {
            System.out.println("Rectangle 2 can fit inside Rectangle 1");
        } else {
            System.out.println("Rectangle 2 cannot fit inside Rectangle 1");
        }
        
        // Calculate combined area when placed side by side
        System.out.println("\n4. Combined Area When Placed Side by Side:");
        double combinedArea = rect1.combinedAreaSideBySide(rect2);
        System.out.println("Combined area: " + String.format("%.2f", combinedArea));
        
        // Demonstrate static methods with object parameters
        System.out.println("\n5. Using Static Methods with Object Parameters:");
        Rectangle largerRect = RectangleUtils.getLargerRectangle(rect1, rect2);
        System.out.print("Larger rectangle: ");
        largerRect.displayRectangle();
        
        // Create array of rectangles to demonstrate array of objects as parameter
        System.out.println("\n6. Array of Objects as Parameter:");
        Rectangle[] rectangles = {rect1, rect2, new Rectangle(3, 4), new Rectangle(5, 2)};
        
        System.out.println("All rectangles:");
        for (int i = 0; i < rectangles.length; i++) {
            System.out.print("Rectangle " + (i + 1) + ": ");
            rectangles[i].displayRectangle();
        }
        
        double totalArea = RectangleUtils.calculateTotalArea(rectangles);
        System.out.println("Total area of all rectangles: " + String.format("%.2f", totalArea));
        
        // Demonstrate method chaining with object parameters
        System.out.println("\n7. Method Chaining with Objects:");
        Rectangle rect3 = new Rectangle(2, 3);
        rect3.compareArea(rect1);
        rect3.compareArea(rect2);
        
        System.out.println("\n=== Objects as Parameters Summary ===");
        System.out.println("✓ Objects can be passed as parameters to methods");
        System.out.println("✓ The object reference is passed by value (copy of reference)");
        System.out.println("✓ Changes to object state inside method affect the original object");
        System.out.println("✓ Enables powerful operations like comparisons and combinations");
        System.out.println("✓ Static methods can also accept objects as parameters");
        System.out.println("✓ Arrays of objects can be passed as parameters");
        
        scanner.close();
    }
}