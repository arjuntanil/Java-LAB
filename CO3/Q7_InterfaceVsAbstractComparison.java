package c03;

// Q7: Create an Interface vs Abstract Class Comparison Program
// Demonstrates differences between interfaces and abstract classes

// Abstract class for Appliance
abstract class Appliance {
    protected String brand;
    protected String model;
    protected boolean isOn;
    protected int powerConsumption; // watts
    
    // Constructor in abstract class
    public Appliance(String brand, String model, int powerConsumption) {
        this.brand = brand;
        this.model = model;
        this.powerConsumption = powerConsumption;
        this.isOn = false;
    }
    
    // Concrete method - same implementation for all appliances
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Appliance is on.");
            System.out.println(brand + " " + model + " started (Power: " + powerConsumption + "W)");
        } else {
            System.out.println("Appliance is already on.");
        }
    }
    
    // Concrete method
    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Appliance is off.");
            System.out.println(brand + " " + model + " stopped");
        } else {
            System.out.println("Appliance is already off.");
        }
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void performMainFunction();
    
    // Abstract method for maintenance
    public abstract void performMaintenance();
    
    // Concrete method to display common info
    public void displayInfo() {
        System.out.println("\n--- Appliance Information ---");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Power Consumption: " + powerConsumption + "W");
        System.out.println("Status: " + (isOn ? "ON" : "OFF"));
    }
    
    // Protected method accessible to subclasses
    protected int calculateDailyCost(double electricityRate) {
        // Assuming 8 hours usage per day
        return (int)(powerConsumption * 8 * electricityRate / 1000);
    }
    
    public boolean isOn() {
        return isOn;
    }
}

// Interface for RemoteControl
interface RemoteControl {
    // Constants (public, static, final by default)
    int MAX_CHANNELS = 1000;
    int DEFAULT_VOLUME = 50;
    String MANUFACTURER = "Universal Remote Corp";
    
    // Abstract methods (public by default)
    void powerButton();
    void volumeUp();
    void volumeDown();
    void changeChannel(int channel);
    void mute();
    
    // Default method (Java 8+)
    default void displayRemoteInfo() {
        System.out.println("Remote controlling device...");
        System.out.println("Manufacturer: " + MANUFACTURER);
        System.out.println("Max Channels: " + MAX_CHANNELS);
    }
    
    // Static method (Java 8+)
    static void checkBatteries() {
        System.out.println("Remote battery level: OK");
    }
}

// Interface for SmartFeatures
interface SmartFeatures {
    void connectToWiFi(String networkName);
    void updateFirmware();
    void enableVoiceControl();
    
    // Default method
    default void syncWithCloud() {
        System.out.println("Syncing device settings with cloud storage");
    }
}

// TV class extending abstract class AND implementing interfaces
class TV extends Appliance implements RemoteControl, SmartFeatures {
    private int currentChannel;
    private int volume;
    private boolean isMuted;
    private int screenSize;
    private String resolution;
    private boolean isConnectedToWiFi;
    
    public TV(String brand, String model, int powerConsumption, int screenSize, String resolution) {
        super(brand, model, powerConsumption); // Call abstract class constructor
        this.screenSize = screenSize;
        this.resolution = resolution;
        this.currentChannel = 1;
        this.volume = DEFAULT_VOLUME;
        this.isMuted = false;
        this.isConnectedToWiFi = false;
    }
    
    // Implementation of abstract method from Appliance
    @Override
    public void performMainFunction() {
        if (isOn) {
            System.out.println("TV is displaying channel " + currentChannel);
            System.out.println("Playing content in " + resolution + " on " + screenSize + "\" screen");
        } else {
            System.out.println("TV is off. Please turn it on first.");
        }
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("Performing TV maintenance:");
        System.out.println("- Cleaning " + screenSize + "\" screen");
        System.out.println("- Checking internal cooling system");
        System.out.println("- Updating channel lineup");
        System.out.println("- Testing remote control functionality");
    }
    
    // Implementation of RemoteControl interface methods
    @Override
    public void powerButton() {
        if (isOn) {
            turnOff();
        } else {
            turnOn();
        }
    }
    
    @Override
    public void volumeUp() {
        if (isOn && !isMuted && volume < 100) {
            volume += 5;
            System.out.println("Volume increased to " + volume);
        } else if (isMuted) {
            System.out.println("TV is muted. Unmute first.");
        } else if (volume >= 100) {
            System.out.println("Volume is already at maximum");
        }
    }
    
    @Override
    public void volumeDown() {
        if (isOn && !isMuted && volume > 0) {
            volume -= 5;
            System.out.println("Volume decreased to " + volume);
        } else if (isMuted) {
            System.out.println("TV is muted. Unmute first.");
        } else if (volume <= 0) {
            System.out.println("Volume is already at minimum");
        }
    }
    
    @Override
    public void changeChannel(int channel) {
        if (isOn && channel > 0 && channel <= MAX_CHANNELS) {
            currentChannel = channel;
            System.out.println("Changed to channel " + channel);
        } else if (!isOn) {
            System.out.println("Please turn on the TV first");
        } else {
            System.out.println("Invalid channel. Range: 1-" + MAX_CHANNELS);
        }
    }
    
    @Override
    public void mute() {
        if (isOn) {
            isMuted = !isMuted;
            System.out.println("TV " + (isMuted ? "muted" : "unmuted"));
        }
    }
    
    // Implementation of SmartFeatures interface methods
    @Override
    public void connectToWiFi(String networkName) {
        isConnectedToWiFi = true;
        System.out.println("TV connected to WiFi network: " + networkName);
    }
    
    @Override
    public void updateFirmware() {
        if (isConnectedToWiFi) {
            System.out.println("Downloading and installing firmware update...");
            System.out.println("TV firmware updated successfully");
        } else {
            System.out.println("Connect to WiFi first to update firmware");
        }
    }
    
    @Override
    public void enableVoiceControl() {
        System.out.println("Voice control enabled. Say 'Hey TV' to start");
    }
    
    // TV-specific methods
    public void displayTVInfo() {
        displayInfo(); // Method from abstract class
        System.out.println("Screen Size: " + screenSize + "\"");
        System.out.println("Resolution: " + resolution);
        System.out.println("Current Channel: " + currentChannel);
        System.out.println("Volume: " + volume + (isMuted ? " (Muted)" : ""));
        System.out.println("WiFi Connected: " + isConnectedToWiFi);
    }
    
    public void showDailyCost(double electricityRate) {
        int cost = calculateDailyCost(electricityRate); // Protected method from abstract class
        System.out.println("Daily electricity cost: $" + cost);
    }
}

// Another appliance for comparison
class WashingMachine extends Appliance {
    private boolean isWashing;
    private String washCycle;
    
    public WashingMachine(String brand, String model, int powerConsumption) {
        super(brand, model, powerConsumption);
        this.isWashing = false;
        this.washCycle = "Normal";
    }
    
    @Override
    public void performMainFunction() {
        if (isOn && !isWashing) {
            isWashing = true;
            System.out.println("Washing machine started " + washCycle + " cycle");
        } else if (isWashing) {
            System.out.println("Washing machine is already running");
        } else {
            System.out.println("Turn on washing machine first");
        }
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("Performing washing machine maintenance:");
        System.out.println("- Cleaning lint filter");
        System.out.println("- Checking water connections");
        System.out.println("- Testing motor functionality");
    }
    
    public void setWashCycle(String cycle) {
        if (!isWashing) {
            this.washCycle = cycle;
            System.out.println("Wash cycle set to: " + cycle);
        } else {
            System.out.println("Cannot change cycle while washing");
        }
    }
}

public class Q7_InterfaceVsAbstractComparison {
    
    // Method accepting abstract class parameter
    public static void operateAppliance(Appliance appliance) {
        System.out.println("\n=== Operating Appliance ===");
        appliance.displayInfo();
        appliance.turnOn(); // Concrete method from abstract class
        appliance.performMainFunction(); // Abstract method implemented by subclass
    }
    
    // Method accepting interface parameter
    public static void useRemoteControl(RemoteControl remote) {
        System.out.println("\n=== Using Remote Control ===");
        remote.displayRemoteInfo(); // Default method from interface
        remote.powerButton();
        remote.changeChannel(25);
        remote.volumeUp();
        remote.mute();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Interface vs Abstract Class Comparison ===");
        
        // Create TV object that extends abstract class and implements interfaces
        TV smartTV = new TV("Samsung", "QLED 55\"", 150, 55, "4K Ultra HD");
        
        // Create washing machine that only extends abstract class
        WashingMachine washer = new WashingMachine("LG", "TurboWash", 500);
        
        // 1. Demonstrate abstract class features
        System.out.println("\n1. Abstract Class Features:");
        System.out.println("✓ Can have constructors");
        System.out.println("✓ Can have instance variables");
        System.out.println("✓ Can have concrete methods");
        System.out.println("✓ Can have abstract methods");
        System.out.println("✓ Supports single inheritance only");
        
        operateAppliance(smartTV);
        operateAppliance(washer);
        
        // 2. Demonstrate interface features
        System.out.println("\n\n2. Interface Features:");
        System.out.println("✓ Only constants (public, static, final)");
        System.out.println("✓ Abstract methods (public by default)");
        System.out.println("✓ Default methods (Java 8+)");
        System.out.println("✓ Static methods (Java 8+)");
        System.out.println("✓ Supports multiple inheritance");
        
        useRemoteControl(smartTV);
        
        // 3. Multiple interface implementation
        System.out.println("\n\n3. Multiple Interface Implementation:");
        smartTV.connectToWiFi("HomeNetwork");
        smartTV.enableVoiceControl();
        smartTV.syncWithCloud(); // Default method from SmartFeatures
        smartTV.updateFirmware();
        
        // 4. Interface constants and static methods
        System.out.println("\n4. Interface Constants and Static Methods:");
        System.out.println("Max channels supported: " + RemoteControl.MAX_CHANNELS);
        System.out.println("Default volume: " + RemoteControl.DEFAULT_VOLUME);
        RemoteControl.checkBatteries(); // Static method
        
        // 5. Polymorphism comparison
        System.out.println("\n5. Polymorphism Comparison:");
        
        // Abstract class polymorphism
        Appliance[] appliances = {smartTV, washer};
        System.out.println("\nAbstract class polymorphism:");
        for (Appliance app : appliances) {
            app.performMainFunction(); // Different implementations
        }
        
        // Interface polymorphism
        RemoteControl[] remotes = {smartTV}; // Only TV implements RemoteControl
        System.out.println("\nInterface polymorphism:");
        for (RemoteControl remote : remotes) {
            remote.volumeUp(); // Interface method
        }
        
        // 6. Demonstrate inheritance vs implementation
        System.out.println("\n6. Inheritance vs Implementation:");
        smartTV.displayTVInfo();
        smartTV.showDailyCost(0.12); // Using inherited protected method
        
        // 7. What we cannot do
        System.out.println("\n7. Restrictions:");
        System.out.println("✗ Cannot instantiate abstract class: new Appliance(...)");
        System.out.println("✗ Cannot instantiate interface: new RemoteControl()");
        System.out.println("✗ Abstract class: only single inheritance");
        System.out.println("✗ Interface: cannot have instance variables (before Java 8)");
        
        // The following would cause compilation errors:
        // Appliance app = new Appliance("Brand", "Model", 100); // Error!
        // RemoteControl remote = new RemoteControl(); // Error!
        
        // 8. Summary comparison
        System.out.println("\n=== Summary Comparison ===");
        System.out.println("\nAbstract Class:");
        System.out.println("✓ Partial implementation (concrete + abstract methods)");
        System.out.println("✓ Can have constructors and instance variables");
        System.out.println("✓ Single inheritance (extends one class)");
        System.out.println("✓ Can have any access modifiers");
        System.out.println("✓ Represents 'IS-A' relationship");
        
        System.out.println("\nInterface:");
        System.out.println("✓ Contract specification (what to do, not how)");
        System.out.println("✓ Multiple inheritance (implements multiple interfaces)");
        System.out.println("✓ All methods public by default");
        System.out.println("✓ Constants only (public, static, final)");
        System.out.println("✓ Represents 'CAN-DO' relationship");
        System.out.println("✓ Default and static methods (Java 8+)");
        
        System.out.println("\n✓ Use abstract classes for related classes with common code");
        System.out.println("✓ Use interfaces for unrelated classes with common behavior");
    }
}