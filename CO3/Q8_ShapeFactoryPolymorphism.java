package c03;

import java.util.Scanner;

// Q8: Implement a Shape Factory Using Polymorphism and Interfaces
// Demonstrates Factory Pattern, Interfaces, and Polymorphism

// Shape interface defining contract for all shapes
interface Shape {
    // Constants
    String DEFAULT_COLOR = "Black";
    double PI = 3.14159;
    
    // Abstract methods
    void draw();
    double calculateArea();
    double calculatePerimeter();
    String getShapeInfo();
    
    // Default method for color operations
    default void setColor(String color) {
        System.out.println("Setting shape color to: " + color);
    }
    
    // Static utility method
    static void displayShapeTypes() {
        System.out.println("Available shapes: Circle, Square, Triangle, Rectangle");
    }
}

// Drawable interface for enhanced drawing capabilities
interface Drawable {
    void drawBorder();
    void fill();
    void rotate(double angle);
    
    default void highlight() {
        System.out.println("Highlighting shape with border");
    }
}

// Resizable interface for shapes that can be resized
interface Resizable {
    void resize(double factor);
    void setDimensions(double... dimensions);
    
    default boolean isValidSize(double... dimensions) {
        for (double dim : dimensions) {
            if (dim <= 0) return false;
        }
        return true;
    }
}

// Circle implementation
class Circle implements Shape, Drawable, Resizable {
    private double radius;
    private String color;
    private double x, y; // position
    
    public Circle(double radius) {
        this.radius = radius;
        this.color = DEFAULT_COLOR;
        this.x = 0;
        this.y = 0;
    }
    
    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
        this.x = 0;
        this.y = 0;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle");
        System.out.println("     🔵");
        System.out.println("Radius: " + radius + ", Color: " + color);
    }
    
    @Override
    public double calculateArea() {
        return PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * PI * radius;
    }
    
    @Override
    public String getShapeInfo() {
        return String.format("Circle - Radius: %.2f, Area: %.2f, Perimeter: %.2f", 
                           radius, calculateArea(), calculatePerimeter());
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
        System.out.println("Circle color set to: " + color);
    }
    
    // Drawable interface implementation
    @Override
    public void drawBorder() {
        System.out.println("Drawing circle border with thickness 2px");
    }
    
    @Override
    public void fill() {
        System.out.println("Filling circle with " + color + " color");
    }
    
    @Override
    public void rotate(double angle) {
        System.out.println("Rotating circle " + angle + " degrees (visual effect only)");
    }
    
    // Resizable interface implementation
    @Override
    public void resize(double factor) {
        if (factor > 0) {
            radius *= factor;
            System.out.println("Circle resized by factor " + factor + ". New radius: " + radius);
        }
    }
    
    @Override
    public void setDimensions(double... dimensions) {
        if (dimensions.length >= 1 && isValidSize(dimensions[0])) {
            radius = dimensions[0];
            System.out.println("Circle radius set to: " + radius);
        } else {
            System.out.println("Invalid radius for circle");
        }
    }
    
    public double getRadius() {
        return radius;
    }
}

// Square implementation
class Square implements Shape, Drawable, Resizable {
    private double side;
    private String color;
    
    public Square(double side) {
        this.side = side;
        this.color = DEFAULT_COLOR;
    }
    
    public Square(double side, String color) {
        this.side = side;
        this.color = color;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing square");
        System.out.println("    ⬜");
        System.out.println("Side: " + side + ", Color: " + color);
    }
    
    @Override
    public double calculateArea() {
        return side * side;
    }
    
    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }
    
    @Override
    public String getShapeInfo() {
        return String.format("Square - Side: %.2f, Area: %.2f, Perimeter: %.2f", 
                           side, calculateArea(), calculatePerimeter());
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
        System.out.println("Square color set to: " + color);
    }
    
    @Override
    public void drawBorder() {
        System.out.println("Drawing square border");
    }
    
    @Override
    public void fill() {
        System.out.println("Filling square with " + color + " color");
    }
    
    @Override
    public void rotate(double angle) {
        System.out.println("Rotating square " + angle + " degrees");
    }
    
    @Override
    public void resize(double factor) {
        if (factor > 0) {
            side *= factor;
            System.out.println("Square resized by factor " + factor + ". New side: " + side);
        }
    }
    
    @Override
    public void setDimensions(double... dimensions) {
        if (dimensions.length >= 1 && isValidSize(dimensions[0])) {
            side = dimensions[0];
            System.out.println("Square side set to: " + side);
        } else {
            System.out.println("Invalid side length for square");
        }
    }
}

// Triangle implementation
class Triangle implements Shape, Drawable, Resizable {
    private double base;
    private double height;
    private String color;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
        this.color = DEFAULT_COLOR;
    }
    
    public Triangle(double base, double height, String color) {
        this.base = base;
        this.height = height;
        this.color = color;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing triangle");
        System.out.println("      🔺");
        System.out.println("Base: " + base + ", Height: " + height + ", Color: " + color);
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
    
    @Override
    public double calculatePerimeter() {
        // Assuming isosceles triangle for simplicity
        double side = Math.sqrt((base/2) * (base/2) + height * height);
        return base + 2 * side;
    }
    
    @Override
    public String getShapeInfo() {
        return String.format("Triangle - Base: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f", 
                           base, height, calculateArea(), calculatePerimeter());
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
        System.out.println("Triangle color set to: " + color);
    }
    
    @Override
    public void drawBorder() {
        System.out.println("Drawing triangle border");
    }
    
    @Override
    public void fill() {
        System.out.println("Filling triangle with " + color + " color");
    }
    
    @Override
    public void rotate(double angle) {
        System.out.println("Rotating triangle " + angle + " degrees");
    }
    
    @Override
    public void resize(double factor) {
        if (factor > 0) {
            base *= factor;
            height *= factor;
            System.out.println("Triangle resized by factor " + factor);
            System.out.println("New base: " + base + ", New height: " + height);
        }
    }
    
    @Override
    public void setDimensions(double... dimensions) {
        if (dimensions.length >= 2 && isValidSize(dimensions)) {
            base = dimensions[0];
            height = dimensions[1];
            System.out.println("Triangle dimensions set - Base: " + base + ", Height: " + height);
        } else {
            System.out.println("Invalid dimensions for triangle (need base and height)");
        }
    }
}

// Rectangle implementation
class Rectangle implements Shape, Drawable, Resizable {
    private double length;
    private double width;
    private String color;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        this.color = DEFAULT_COLOR;
    }
    
    public Rectangle(double length, double width, String color) {
        this.length = length;
        this.width = width;
        this.color = color;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
        System.out.println("    ▬▬▬▬");
        System.out.println("    |    |");
        System.out.println("    ▬▬▬▬");
        System.out.println("Length: " + length + ", Width: " + width + ", Color: " + color);
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
    
    @Override
    public String getShapeInfo() {
        return String.format("Rectangle - Length: %.2f, Width: %.2f, Area: %.2f, Perimeter: %.2f", 
                           length, width, calculateArea(), calculatePerimeter());
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
        System.out.println("Rectangle color set to: " + color);
    }
    
    @Override
    public void drawBorder() {
        System.out.println("Drawing rectangle border");
    }
    
    @Override
    public void fill() {
        System.out.println("Filling rectangle with " + color + " color");
    }
    
    @Override
    public void rotate(double angle) {
        System.out.println("Rotating rectangle " + angle + " degrees");
    }
    
    @Override
    public void resize(double factor) {
        if (factor > 0) {
            length *= factor;
            width *= factor;
            System.out.println("Rectangle resized by factor " + factor);
            System.out.println("New length: " + length + ", New width: " + width);
        }
    }
    
    @Override
    public void setDimensions(double... dimensions) {
        if (dimensions.length >= 2 && isValidSize(dimensions)) {
            length = dimensions[0];
            width = dimensions[1];
            System.out.println("Rectangle dimensions set - Length: " + length + ", Width: " + width);
        } else {
            System.out.println("Invalid dimensions for rectangle (need length and width)");
        }
    }
}

// Shape Factory class
class ShapeFactory {
    
    // Factory method to create shapes based on type
    public static Shape createShape(String shapeType) {
        switch (shapeType.toLowerCase()) {
            case "circle":
                return new Circle(5.0);
            case "square":
                return new Square(4.0);
            case "triangle":
                return new Triangle(6.0, 8.0);
            case "rectangle":
                return new Rectangle(7.0, 3.0);
            default:
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
    
    // Overloaded factory method with parameters
    public static Shape createShape(String shapeType, double... dimensions) {
        switch (shapeType.toLowerCase()) {
            case "circle":
                if (dimensions.length >= 1) {
                    return new Circle(dimensions[0]);
                }
                return new Circle(5.0);
                
            case "square":
                if (dimensions.length >= 1) {
                    return new Square(dimensions[0]);
                }
                return new Square(4.0);
                
            case "triangle":
                if (dimensions.length >= 2) {
                    return new Triangle(dimensions[0], dimensions[1]);
                }
                return new Triangle(6.0, 8.0);
                
            case "rectangle":
                if (dimensions.length >= 2) {
                    return new Rectangle(dimensions[0], dimensions[1]);
                }
                return new Rectangle(7.0, 3.0);
                
            default:
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
    
    // Factory method with color
    public static Shape createColoredShape(String shapeType, String color, double... dimensions) {
        switch (shapeType.toLowerCase()) {
            case "circle":
                if (dimensions.length >= 1) {
                    return new Circle(dimensions[0], color);
                }
                return new Circle(5.0, color);
                
            case "square":
                if (dimensions.length >= 1) {
                    return new Square(dimensions[0], color);
                }
                return new Square(4.0, color);
                
            case "triangle":
                if (dimensions.length >= 2) {
                    return new Triangle(dimensions[0], dimensions[1], color);
                }
                return new Triangle(6.0, 8.0, color);
                
            case "rectangle":
                if (dimensions.length >= 2) {
                    return new Rectangle(dimensions[0], dimensions[1], color);
                }
                return new Rectangle(7.0, 3.0, color);
                
            default:
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
    
    // Method to get available shape types
    public static String[] getAvailableShapes() {
        return new String[]{"circle", "square", "triangle", "rectangle"};
    }
}

public class Q8_ShapeFactoryPolymorphism {
    
    // Method demonstrating polymorphism with Shape interface
    public static void processShape(Shape shape) {
        System.out.println("\n=== Processing Shape ===");
        shape.draw();
        System.out.println("Area: " + String.format("%.2f", shape.calculateArea()));
        System.out.println("Perimeter: " + String.format("%.2f", shape.calculatePerimeter()));
        System.out.println(shape.getShapeInfo());
    }
    
    // Method working with Drawable interface
    public static void enhanceDrawing(Drawable drawable) {
        System.out.println("\n=== Enhanced Drawing ===");
        drawable.drawBorder();
        drawable.fill();
        drawable.highlight();
        drawable.rotate(45);
    }
    
    // Method working with Resizable interface
    public static void modifySize(Resizable resizable) {
        System.out.println("\n=== Size Modification ===");
        resizable.resize(1.5);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Shape Factory Using Polymorphism and Interfaces ===");
        
        // 1. Display available shapes
        System.out.println("\n1. Available Shape Types:");
        Shape.displayShapeTypes(); // Static method from interface
        
        // 2. Create shapes using factory
        System.out.println("\n2. Creating Shapes Using Factory:");
        
        Shape[] shapes = {
            ShapeFactory.createShape("circle"),
            ShapeFactory.createShape("square"),
            ShapeFactory.createShape("triangle"),
            ShapeFactory.createShape("rectangle")
        };
        
        // Process all shapes polymorphically
        for (Shape shape : shapes) {
            processShape(shape);
        }
        
        // 3. Interactive shape creation
        System.out.println("\n\n3. Interactive Shape Creation:");
        System.out.print("Enter shape to draw (circle/square/triangle/rectangle): ");
        String userChoice = scanner.next().toLowerCase();
        
        try {
            Shape userShape = null;
            
            switch (userChoice) {
                case "circle":
                    System.out.print("Enter radius: ");
                    double radius = scanner.nextDouble();
                    userShape = ShapeFactory.createShape("circle", radius);
                    break;
                    
                case "square":
                    System.out.print("Enter side length: ");
                    double side = scanner.nextDouble();
                    userShape = ShapeFactory.createShape("square", side);
                    break;
                    
                case "triangle":
                    System.out.print("Enter base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Enter height: ");
                    double height = scanner.nextDouble();
                    userShape = ShapeFactory.createShape("triangle", base, height);
                    break;
                    
                case "rectangle":
                    System.out.print("Enter length: ");
                    double length = scanner.nextDouble();
                    System.out.print("Enter width: ");
                    double width = scanner.nextDouble();
                    userShape = ShapeFactory.createShape("rectangle", length, width);
                    break;
                    
                default:
                    System.out.println("Invalid shape type!");
                    userShape = ShapeFactory.createShape("circle"); // Default
            }
            
            if (userShape != null) {
                processShape(userShape);
                
                // Demonstrate multiple interface implementations
                if (userShape instanceof Drawable) {
                    enhanceDrawing((Drawable) userShape);
                }
                
                if (userShape instanceof Resizable) {
                    modifySize((Resizable) userShape);
                    processShape(userShape); // Show changes
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error creating shape: " + e.getMessage());
        }
        
        // 4. Colored shapes demonstration
        System.out.println("\n\n4. Colored Shapes:");
        Shape[] coloredShapes = {
            ShapeFactory.createColoredShape("circle", "Red", 3.0),
            ShapeFactory.createColoredShape("square", "Blue", 5.0),
            ShapeFactory.createColoredShape("triangle", "Green", 4.0, 6.0),
            ShapeFactory.createColoredShape("rectangle", "Yellow", 8.0, 4.0)
        };
        
        for (Shape shape : coloredShapes) {
            processShape(shape);
        }
        
        // 5. Interface-based operations
        System.out.println("\n5. Interface-Based Operations:");
        
        // All shapes implement Shape interface
        Shape[] allShapes = {shapes[0], coloredShapes[0]};
        System.out.println("\nCalculating total area:");
        double totalArea = 0;
        for (Shape shape : allShapes) {
            totalArea += shape.calculateArea();
        }
        System.out.println("Total area of all shapes: " + String.format("%.2f", totalArea));
        
        // Working with Drawable interface
        System.out.println("\nEnhanced drawing for all shapes:");
        for (Shape shape : allShapes) {
            if (shape instanceof Drawable) {
                ((Drawable) shape).highlight();
            }
        }
        
        // 6. Demonstrate interface constants
        System.out.println("\n6. Interface Constants:");
        System.out.println("Default color: " + Shape.DEFAULT_COLOR);
        System.out.println("PI value: " + Shape.PI);
        
        // 7. Factory pattern benefits
        System.out.println("\n7. Factory Pattern Benefits:");
        System.out.println("✓ Encapsulates object creation logic");
        System.out.println("✓ Provides flexibility in object creation");
        System.out.println("✓ Supports polymorphism");
        System.out.println("✓ Easy to extend with new shape types");
        System.out.println("✓ Client code doesn't depend on concrete classes");
        
        System.out.println("\n=== Summary ===");
        System.out.println("✓ Interfaces define contracts for behavior");
        System.out.println("✓ Factory pattern creates objects without exposing creation logic");
        System.out.println("✓ Polymorphism allows treating different objects uniformly");
        System.out.println("✓ Multiple interface implementation provides flexible design");
        System.out.println("✓ Default and static methods enhance interface capabilities");
        
        scanner.close();
    }
}