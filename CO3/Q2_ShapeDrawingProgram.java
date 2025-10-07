package c03;

import java.util.Scanner;

// Q2: Create a Shape Drawing Program Using Dynamic Binding
// Demonstrates dynamic method dispatch and polymorphism with user interaction

// Abstract base class for shapes
abstract class Shape {
    protected String name;
    protected String color;
    
    public Shape(String name, String color) {
        this.name = name;
        this.color = color;
    }
    
    // Abstract method to be implemented by subclasses
    public abstract void draw();
    
    // Concrete method shared by all shapes
    public void displayInfo() {
        System.out.println("Shape: " + name + ", Color: " + color);
    }
    
    // Abstract method for calculating area
    public abstract double calculateArea();
    
    public String getName() {
        return name;
    }
}

// Circle class
class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super("Circle", color);
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle");
        System.out.println("   ⭕");
        System.out.println("   Radius: " + radius);
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Square class
class Square extends Shape {
    private double side;
    
    public Square(String color, double side) {
        super("Square", color);
        this.side = side;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing square");
        System.out.println("   ⬜");
        System.out.println("   Side: " + side);
    }
    
    @Override
    public double calculateArea() {
        return side * side;
    }
}

// Triangle class
class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(String color, double base, double height) {
        super("Triangle", color);
        this.base = base;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing triangle");
        System.out.println("     🔺");
        System.out.println("   Base: " + base + ", Height: " + height);
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

// Rectangle class
class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(String color, double length, double width) {
        super("Rectangle", color);
        this.length = length;
        this.width = width;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
        System.out.println("   ▬▬▬▬");
        System.out.println("   |    |");
        System.out.println("   ▬▬▬▬");
        System.out.println("   Length: " + length + ", Width: " + width);
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
}

// Shape factory to create shapes based on user input
class ShapeFactory {
    public static Shape createShape(String shapeType, Scanner scanner) {
        System.out.print("Enter color: ");
        String color = scanner.next();
        
        switch (shapeType.toLowerCase()) {
            case "circle":
                System.out.print("Enter radius: ");
                double radius = scanner.nextDouble();
                return new Circle(color, radius);
                
            case "square":
                System.out.print("Enter side length: ");
                double side = scanner.nextDouble();
                return new Square(color, side);
                
            case "triangle":
                System.out.print("Enter base: ");
                double base = scanner.nextDouble();
                System.out.print("Enter height: ");
                double height = scanner.nextDouble();
                return new Triangle(color, base, height);
                
            case "rectangle":
                System.out.print("Enter length: ");
                double length = scanner.nextDouble();
                System.out.print("Enter width: ");
                double width = scanner.nextDouble();
                return new Rectangle(color, length, width);
                
            default:
                return null;
        }
    }
}

public class Q2_ShapeDrawingProgram {
    
    // Method demonstrating dynamic binding
    public static void drawShape(Shape shape) {
        if (shape != null) {
            shape.displayInfo();
            shape.draw(); // Dynamic binding - correct draw() method is called at runtime
            System.out.println("Area: " + String.format("%.2f", shape.calculateArea()));
        } else {
            System.out.println("Invalid shape!");
        }
    }
    
    // Method to draw multiple shapes
    public static void drawMultipleShapes(Shape[] shapes) {
        System.out.println("\n=== Drawing All Shapes ===");
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] != null) {
                System.out.println("\nShape " + (i + 1) + ":");
                drawShape(shapes[i]);
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Shape Drawing Program Using Dynamic Binding ===");
        
        // Demonstration with predefined shapes
        System.out.println("\n1. Predefined Shapes Demonstration:");
        Shape[] predefinedShapes = {
            new Circle("Red", 5.0),
            new Square("Blue", 4.0),
            new Triangle("Green", 6.0, 8.0),
            new Rectangle("Yellow", 7.0, 3.0)
        };
        
        drawMultipleShapes(predefinedShapes);
        
        // Interactive shape creation
        System.out.println("\n\n2. Interactive Shape Creation:");
        Shape[] userShapes = new Shape[3];
        
        for (int i = 0; i < userShapes.length; i++) {
            System.out.println("\nCreating shape " + (i + 1) + ":");
            System.out.println("Available shapes: circle, square, triangle, rectangle");
            System.out.print("Enter shape type: ");
            String shapeType = scanner.next();
            
            userShapes[i] = ShapeFactory.createShape(shapeType, scanner);
            
            if (userShapes[i] != null) {
                System.out.println("\nShape created successfully!");
                drawShape(userShapes[i]);
            } else {
                System.out.println("Invalid shape type!");
                i--; // Retry for this position
            }
        }
        
        // Menu-driven interface
        System.out.println("\n\n3. Menu-Driven Shape Drawing:");
        boolean continueDrawing = true;
        
        while (continueDrawing) {
            System.out.println("\n=== Shape Drawing Menu ===");
            System.out.println("1. Draw Circle");
            System.out.println("2. Draw Square");
            System.out.println("3. Draw Triangle");
            System.out.println("4. Draw Rectangle");
            System.out.println("5. Compare Areas of Two Shapes");
            System.out.println("6. Exit");
            System.out.print("Enter choice (1-6): ");
            
            int choice = scanner.nextInt();
            Shape selectedShape = null;
            
            switch (choice) {
                case 1:
                    selectedShape = ShapeFactory.createShape("circle", scanner);
                    break;
                case 2:
                    selectedShape = ShapeFactory.createShape("square", scanner);
                    break;
                case 3:
                    selectedShape = ShapeFactory.createShape("triangle", scanner);
                    break;
                case 4:
                    selectedShape = ShapeFactory.createShape("rectangle", scanner);
                    break;
                case 5:
                    System.out.println("\nFirst shape:");
                    Shape shape1 = ShapeFactory.createShape(
                        scanner.next(), scanner);
                    System.out.println("\nSecond shape:");
                    Shape shape2 = ShapeFactory.createShape(
                        scanner.next(), scanner);
                    
                    if (shape1 != null && shape2 != null) {
                        System.out.println("\n=== Shape Comparison ===");
                        System.out.println("Shape 1:");
                        drawShape(shape1);
                        System.out.println("\nShape 2:");
                        drawShape(shape2);
                        
                        double area1 = shape1.calculateArea();
                        double area2 = shape2.calculateArea();
                        
                        System.out.println("\n=== Area Comparison ===");
                        if (area1 > area2) {
                            System.out.println(shape1.getName() + " has larger area");
                        } else if (area2 > area1) {
                            System.out.println(shape2.getName() + " has larger area");
                        } else {
                            System.out.println("Both shapes have equal area");
                        }
                    }
                    continue;
                case 6:
                    continueDrawing = false;
                    continue;
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }
            
            if (selectedShape != null) {
                System.out.println("\n=== Drawing Selected Shape ===");
                drawShape(selectedShape);
            }
        }
        
        System.out.println("\n=== Dynamic Binding Summary ===");
        System.out.println("✓ Method resolution happens at runtime, not compile time");
        System.out.println("✓ Same method call produces different behaviors based on object type");
        System.out.println("✓ Enables flexible and extensible code design");
        System.out.println("✓ New shape types can be added without modifying existing code");
        
        scanner.close();
    }
}