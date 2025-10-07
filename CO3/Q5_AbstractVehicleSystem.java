package c03;

// Q5: Design an Abstract Class for Vehicles
// Demonstrates abstract classes, abstract methods, and concrete methods

// Abstract Vehicle class
abstract class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected String fuelType;
    protected boolean isRunning;
    
    // Constructor for abstract class
    public Vehicle(String brand, String model, int year, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.isRunning = false;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void startEngine();
    
    // Abstract method for vehicle-specific acceleration
    public abstract void accelerate();
    
    // Abstract method for braking
    public abstract void brake();
    
    // Concrete method - shared implementation for all vehicles
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Vehicle engine stopped.");
            System.out.println(brand + " " + model + " is now turned off.");
        } else {
            System.out.println("Engine is already stopped.");
        }
    }
    
    // Concrete method for displaying vehicle info
    public void displayVehicleInfo() {
        System.out.println("\n--- Vehicle Information ---");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Status: " + (isRunning ? "Running" : "Stopped"));
    }
    
    // Concrete method for honking
    public void honk() {
        System.out.println("Beep beep! " + brand + " " + model + " is honking.");
    }
    
    // Abstract method for maintenance
    public abstract void performMaintenance();
    
    // Getters
    public boolean isRunning() {
        return isRunning;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getModel() {
        return model;
    }
}

// Car class extending Vehicle
class Car extends Vehicle {
    private int numberOfDoors;
    private String transmissionType;
    
    public Car(String brand, String model, int year, String fuelType, 
               int numberOfDoors, String transmissionType) {
        super(brand, model, year, fuelType);
        this.numberOfDoors = numberOfDoors;
        this.transmissionType = transmissionType;
    }
    
    // Implementation of abstract method
    @Override
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Car engine started.");
            System.out.println(brand + " " + model + " is ready to drive!");
        } else {
            System.out.println("Car engine is already running.");
        }
    }
    
    @Override
    public void accelerate() {
        if (isRunning) {
            System.out.println("Car is accelerating smoothly on the road.");
            System.out.println("Speed increasing... 🚗💨");
        } else {
            System.out.println("Cannot accelerate. Please start the engine first.");
        }
    }
    
    @Override
    public void brake() {
        if (isRunning) {
            System.out.println("Car brakes applied. Slowing down safely.");
        } else {
            System.out.println("Car is already stopped.");
        }
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("Performing car maintenance:");
        System.out.println("- Checking engine oil");
        System.out.println("- Inspecting brakes and tires");
        System.out.println("- Testing " + transmissionType + " transmission");
        System.out.println("- Checking all " + numberOfDoors + " doors");
    }
    
    // Car-specific method
    public void openTrunk() {
        System.out.println("Car trunk opened.");
    }
    
    public void displayCarInfo() {
        displayVehicleInfo();
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Transmission: " + transmissionType);
    }
}

// Bike class extending Vehicle
class Bike extends Vehicle {
    private String bikeType; // "Sport", "Cruiser", "Standard"
    private boolean hasKickstand;
    
    public Bike(String brand, String model, int year, String fuelType, 
                String bikeType, boolean hasKickstand) {
        super(brand, model, year, fuelType);
        this.bikeType = bikeType;
        this.hasKickstand = hasKickstand;
    }
    
    // Implementation of abstract method
    @Override
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Bike engine started.");
            System.out.println(brand + " " + model + " is ready to ride!");
        } else {
            System.out.println("Bike engine is already running.");
        }
    }
    
    @Override
    public void accelerate() {
        if (isRunning) {
            System.out.println("Bike is accelerating on the open road.");
            System.out.println("Feel the wind! 🏍️💨");
        } else {
            System.out.println("Cannot accelerate. Please start the engine first.");
        }
    }
    
    @Override
    public void brake() {
        if (isRunning) {
            System.out.println("Bike brakes engaged. Slowing down carefully.");
        } else {
            System.out.println("Bike is already stopped.");
        }
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("Performing bike maintenance:");
        System.out.println("- Checking chain and sprockets");
        System.out.println("- Inspecting brake pads");
        System.out.println("- Checking tire pressure");
        if (hasKickstand) {
            System.out.println("- Testing kickstand mechanism");
        }
        System.out.println("- Verifying " + bikeType + " bike specific components");
    }
    
    // Bike-specific methods
    public void wheelie() {
        if (isRunning) {
            System.out.println("Performing a wheelie! 🏍️");
        } else {
            System.out.println("Cannot perform wheelie. Engine not running.");
        }
    }
    
    public void displayBikeInfo() {
        displayVehicleInfo();
        System.out.println("Type: " + bikeType);
        System.out.println("Kickstand: " + (hasKickstand ? "Yes" : "No"));
    }
}

// Truck class extending Vehicle
class Truck extends Vehicle {
    private double cargoCapacity; // in tons
    private int numberOfAxles;
    
    public Truck(String brand, String model, int year, String fuelType, 
                 double cargoCapacity, int numberOfAxles) {
        super(brand, model, year, fuelType);
        this.cargoCapacity = cargoCapacity;
        this.numberOfAxles = numberOfAxles;
    }
    
    @Override
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Truck engine started.");
            System.out.println("Heavy-duty " + brand + " " + model + " is ready for hauling!");
        } else {
            System.out.println("Truck engine is already running.");
        }
    }
    
    @Override
    public void accelerate() {
        if (isRunning) {
            System.out.println("Truck is accelerating with heavy cargo.");
            System.out.println("Steady and powerful acceleration. 🚛");
        } else {
            System.out.println("Cannot accelerate. Please start the engine first.");
        }
    }
    
    @Override
    public void brake() {
        if (isRunning) {
            System.out.println("Truck air brakes activated. Heavy vehicle stopping.");
        } else {
            System.out.println("Truck is already stopped.");
        }
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("Performing truck maintenance:");
        System.out.println("- Checking air brake system");
        System.out.println("- Inspecting cargo bed capacity: " + cargoCapacity + " tons");
        System.out.println("- Examining all " + numberOfAxles + " axles");
        System.out.println("- Testing hydraulic systems");
        System.out.println("- Checking diesel engine components");
    }
    
    // Truck-specific methods
    public void loadCargo(double weight) {
        if (weight <= cargoCapacity) {
            System.out.println("Loading " + weight + " tons of cargo.");
            System.out.println("Cargo loaded successfully.");
        } else {
            System.out.println("Cannot load " + weight + " tons. Max capacity: " + cargoCapacity + " tons");
        }
    }
    
    public void displayTruckInfo() {
        displayVehicleInfo();
        System.out.println("Cargo Capacity: " + cargoCapacity + " tons");
        System.out.println("Number of Axles: " + numberOfAxles);
    }
}

public class Q5_AbstractVehicleSystem {
    
    // Method demonstrating polymorphism with abstract class
    public static void operateVehicle(Vehicle vehicle) {
        System.out.println("\n=== Operating " + vehicle.getBrand() + " " + vehicle.getModel() + " ===");
        
        // Call abstract methods (implemented differently in each subclass)
        vehicle.startEngine();
        vehicle.accelerate();
        vehicle.honk(); // Concrete method - same for all
        vehicle.brake();
        vehicle.stopEngine(); // Concrete method - same for all
    }
    
    // Method to perform maintenance on all vehicles
    public static void performFleetMaintenance(Vehicle[] fleet) {
        System.out.println("\n=== Fleet Maintenance Day ===");
        
        for (int i = 0; i < fleet.length; i++) {
            if (fleet[i] != null) {
                System.out.println("\nMaintaining Vehicle " + (i + 1) + ":");
                fleet[i].displayVehicleInfo();
                fleet[i].performMaintenance();
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Abstract Vehicle System Demonstration ===");
        
        // Create different types of vehicles
        Car car = new Car("Toyota", "Camry", 2023, "Gasoline", 4, "Automatic");
        Bike bike = new Bike("Harley-Davidson", "Street 750", 2022, "Gasoline", "Cruiser", true);
        Truck truck = new Truck("Ford", "F-150", 2023, "Diesel", 2.5, 2);
        
        // 1. Individual vehicle operations
        System.out.println("\n1. Individual Vehicle Operations:");
        
        car.displayCarInfo();
        operateVehicle(car);
        car.openTrunk();
        
        bike.displayBikeInfo();
        operateVehicle(bike);
        bike.wheelie();
        
        truck.displayTruckInfo();
        operateVehicle(truck);
        truck.loadCargo(2.0);
        truck.loadCargo(3.0); // This will exceed capacity
        
        // 2. Polymorphic behavior with array
        System.out.println("\n\n2. Polymorphic Fleet Operations:");
        Vehicle[] fleet = {car, bike, truck};
        
        for (Vehicle vehicle : fleet) {
            operateVehicle(vehicle);
        }
        
        // 3. Fleet maintenance
        performFleetMaintenance(fleet);
        
        // 4. Demonstrate that we cannot instantiate abstract class
        System.out.println("\n3. Abstract Class Restrictions:");
        System.out.println("✗ Cannot create instance: new Vehicle(...)");
        System.out.println("✓ Can use Vehicle reference for polymorphism");
        System.out.println("✓ Subclasses must implement all abstract methods");
        
        // The following would cause compilation error:
        // Vehicle vehicle = new Vehicle("Brand", "Model", 2023, "Gas"); // ERROR!
        
        // 5. Advanced polymorphism demonstration
        System.out.println("\n4. Advanced Polymorphism:");
        
        // Create array with Vehicle references
        Vehicle[] mixedFleet = {
            new Car("Honda", "Civic", 2022, "Gasoline", 2, "Manual"),
            new Bike("Yamaha", "R6", 2023, "Gasoline", "Sport", false),
            new Truck("Volvo", "VNL", 2021, "Diesel", 40.0, 5),
            new Car("Tesla", "Model 3", 2023, "Electric", 4, "Automatic")
        };
        
        // Same method calls, different behaviors
        for (Vehicle v : mixedFleet) {
            v.startEngine(); // Each implements differently
            System.out.println();
        }
        
        // Stop all engines using concrete method
        System.out.println("Stopping all vehicles:");
        for (Vehicle v : mixedFleet) {
            v.stopEngine(); // Same implementation for all
        }
        
        System.out.println("\n=== Abstract Class Summary ===");
        System.out.println("✓ Abstract classes cannot be instantiated");
        System.out.println("✓ Can contain both abstract and concrete methods");
        System.out.println("✓ Subclasses must implement all abstract methods");
        System.out.println("✓ Provides common interface while allowing different implementations");
        System.out.println("✓ Supports polymorphism and code reuse");
        System.out.println("✓ Can have constructors, instance variables, and concrete methods");
    }
}