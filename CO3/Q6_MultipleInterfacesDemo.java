package c03;

// Q6: Implement Multiple Interfaces for a Smart Device
// Demonstrates multiple interface implementation and interface features

// Camera interface
interface Camera {
    // Constants in interface (public, static, final by default)
    int MAX_PHOTOS = 10000;
    String DEFAULT_RESOLUTION = "1080p";
    
    // Abstract methods (public by default)
    void takePhoto();
    void recordVideo();
    void switchToFrontCamera();
    void switchToBackCamera();
    
    // Default method (Java 8 feature)
    default void applyFilter(String filterName) {
        System.out.println("Applying " + filterName + " filter to camera");
    }
    
    // Static method (Java 8 feature)
    static void displayCameraSpecs() {
        System.out.println("Camera Specifications:");
        System.out.println("Max Photos: " + MAX_PHOTOS);
        System.out.println("Default Resolution: " + DEFAULT_RESOLUTION);
    }
}

// Phone interface
interface Phone {
    // Constants
    int MAX_CONTACTS = 5000;
    String DEFAULT_RINGTONE = "Standard Ring";
    
    // Abstract methods
    void makeCall(String number);
    void receiveCall(String number);
    void sendSMS(String number, String message);
    void endCall();
    
    // Default method
    default void playRingtone() {
        System.out.println("Playing ringtone: " + DEFAULT_RINGTONE);
    }
    
    // Static method
    static void displayPhoneFeatures() {
        System.out.println("Phone Features:");
        System.out.println("Max Contacts: " + MAX_CONTACTS);
        System.out.println("Default Ringtone: " + DEFAULT_RINGTONE);
    }
}

// GPS interface
interface GPS {
    String DEFAULT_MAP_PROVIDER = "OpenMaps";
    
    void getCurrentLocation();
    void navigateTo(String destination);
    void searchNearby(String type);
    
    default void enableGPS() {
        System.out.println("GPS enabled using " + DEFAULT_MAP_PROVIDER);
    }
}

// MediaPlayer interface
interface MediaPlayer {
    void playMusic(String song);
    void pauseMusic();
    void stopMusic();
    void adjustVolume(int level);
    
    default void shuffle() {
        System.out.println("Shuffle mode enabled");
    }
}

// Smartphone class implementing multiple interfaces
class Smartphone implements Camera, Phone, GPS, MediaPlayer {
    private String brand;
    private String model;
    private boolean isCallActive;
    private boolean isCameraOn;
    private boolean isGPSEnabled;
    private boolean isMusicPlaying;
    private String currentSong;
    private int batteryLevel;
    
    public Smartphone(String brand, String model, int batteryLevel) {
        this.brand = brand;
        this.model = model;
        this.batteryLevel = batteryLevel;
        this.isCallActive = false;
        this.isCameraOn = false;
        this.isGPSEnabled = false;
        this.isMusicPlaying = false;
    }
    
    // Camera interface implementation
    @Override
    public void takePhoto() {
        if (batteryLevel > 10) {
            System.out.println("Photo taken.");
            System.out.println("📸 Picture saved to gallery");
            batteryLevel -= 2;
        } else {
            System.out.println("Battery too low to take photo");
        }
    }
    
    @Override
    public void recordVideo() {
        if (batteryLevel > 15) {
            System.out.println("Recording video...");
            System.out.println("🎥 Video recording started");
            batteryLevel -= 5;
        } else {
            System.out.println("Battery too low to record video");
        }
    }
    
    @Override
    public void switchToFrontCamera() {
        System.out.println("Switched to front camera for selfies");
        isCameraOn = true;
    }
    
    @Override
    public void switchToBackCamera() {
        System.out.println("Switched to back camera for photos");
        isCameraOn = true;
    }
    
    // Phone interface implementation
    @Override
    public void makeCall(String number) {
        if (batteryLevel > 5) {
            System.out.println("Calling 123-456-7890");
            System.out.println("📞 Call connected to " + number);
            isCallActive = true;
            batteryLevel -= 3;
        } else {
            System.out.println("Battery too low to make call");
        }
    }
    
    @Override
    public void receiveCall(String number) {
        System.out.println("📱 Incoming call from " + number);
        playRingtone(); // Using default method
        isCallActive = true;
    }
    
    @Override
    public void sendSMS(String number, String message) {
        if (batteryLevel > 1) {
            System.out.println("💬 SMS sent to " + number + ": " + message);
            batteryLevel -= 1;
        } else {
            System.out.println("Battery too low to send SMS");
        }
    }
    
    @Override
    public void endCall() {
        if (isCallActive) {
            System.out.println("Call ended");
            isCallActive = false;
        } else {
            System.out.println("No active call to end");
        }
    }
    
    // GPS interface implementation
    @Override
    public void getCurrentLocation() {
        if (batteryLevel > 5) {
            System.out.println("📍 Current location: New York, NY");
            isGPSEnabled = true;
            batteryLevel -= 2;
        } else {
            System.out.println("Battery too low for GPS");
        }
    }
    
    @Override
    public void navigateTo(String destination) {
        if (isGPSEnabled && batteryLevel > 10) {
            System.out.println("🗺️ Navigating to " + destination);
            System.out.println("Turn-by-turn directions activated");
            batteryLevel -= 5;
        } else {
            System.out.println("GPS not enabled or battery too low");
        }
    }
    
    @Override
    public void searchNearby(String type) {
        if (isGPSEnabled) {
            System.out.println("🔍 Searching for nearby " + type);
            System.out.println("Found 5 " + type + " locations within 1 mile");
        } else {
            System.out.println("Please enable GPS first");
        }
    }
    
    // MediaPlayer interface implementation
    @Override
    public void playMusic(String song) {
        if (batteryLevel > 3) {
            System.out.println("🎵 Now playing: " + song);
            currentSong = song;
            isMusicPlaying = true;
            batteryLevel -= 2;
        } else {
            System.out.println("Battery too low to play music");
        }
    }
    
    @Override
    public void pauseMusic() {
        if (isMusicPlaying) {
            System.out.println("⏸️ Music paused");
            isMusicPlaying = false;
        } else {
            System.out.println("No music is currently playing");
        }
    }
    
    @Override
    public void stopMusic() {
        if (isMusicPlaying) {
            System.out.println("⏹️ Music stopped");
            isMusicPlaying = false;
            currentSong = null;
        } else {
            System.out.println("No music is currently playing");
        }
    }
    
    @Override
    public void adjustVolume(int level) {
        if (level >= 0 && level <= 100) {
            System.out.println("🔊 Volume set to " + level + "%");
        } else {
            System.out.println("Invalid volume level. Use 0-100");
        }
    }
    
    // Additional smartphone methods
    public void displayDeviceInfo() {
        System.out.println("\n--- Device Information ---");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Call Active: " + isCallActive);
        System.out.println("Camera On: " + isCameraOn);
        System.out.println("GPS Enabled: " + isGPSEnabled);
        System.out.println("Music Playing: " + isMusicPlaying);
        if (currentSong != null) {
            System.out.println("Current Song: " + currentSong);
        }
    }
    
    public void chargeBattery(int amount) {
        batteryLevel = Math.min(100, batteryLevel + amount);
        System.out.println("🔋 Battery charged to " + batteryLevel + "%");
    }
}

// Tablet class implementing some interfaces
class Tablet implements Camera, MediaPlayer, GPS {
    private String brand;
    private String model;
    private boolean hasLTE;
    
    public Tablet(String brand, String model, boolean hasLTE) {
        this.brand = brand;
        this.model = model;
        this.hasLTE = hasLTE;
    }
    
    // Camera implementation
    @Override
    public void takePhoto() {
        System.out.println("📸 Tablet photo taken with high resolution");
    }
    
    @Override
    public void recordVideo() {
        System.out.println("🎥 Tablet recording 4K video");
    }
    
    @Override
    public void switchToFrontCamera() {
        System.out.println("Tablet front camera for video calls");
    }
    
    @Override
    public void switchToBackCamera() {
        System.out.println("Tablet back camera for documents");
    }
    
    // MediaPlayer implementation
    @Override
    public void playMusic(String song) {
        System.out.println("🎵 Tablet playing " + song + " with stereo speakers");
    }
    
    @Override
    public void pauseMusic() {
        System.out.println("⏸️ Tablet music paused");
    }
    
    @Override
    public void stopMusic() {
        System.out.println("⏹️ Tablet music stopped");
    }
    
    @Override
    public void adjustVolume(int level) {
        System.out.println("🔊 Tablet volume: " + level + "% (Enhanced audio)");
    }
    
    // GPS implementation
    @Override
    public void getCurrentLocation() {
        if (hasLTE) {
            System.out.println("📍 Tablet GPS location found via LTE");
        } else {
            System.out.println("📍 Tablet GPS location via WiFi positioning");
        }
    }
    
    @Override
    public void navigateTo(String destination) {
        System.out.println("🗺️ Tablet navigation to " + destination + " (Large screen view)");
    }
    
    @Override
    public void searchNearby(String type) {
        System.out.println("🔍 Tablet found nearby " + type + " with detailed reviews");
    }
    
    public void displayTabletInfo() {
        System.out.println("\n--- Tablet Information ---");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("LTE Support: " + hasLTE);
    }
}

public class Q6_MultipleInterfacesDemo {
    
    // Method using Camera interface
    public static void useCamera(Camera camera) {
        System.out.println("\n=== Camera Operations ===");
        camera.switchToBackCamera();
        camera.takePhoto();
        camera.recordVideo();
        camera.applyFilter("Vintage"); // Default method
    }
    
    // Method using Phone interface
    public static void usePhone(Phone phone) {
        System.out.println("\n=== Phone Operations ===");
        phone.makeCall("555-0123");
        phone.sendSMS("555-0456", "Hello from Java!");
        phone.endCall();
    }
    
    // Method using MediaPlayer interface
    public static void useMediaPlayer(MediaPlayer player) {
        System.out.println("\n=== Media Player Operations ===");
        player.playMusic("Bohemian Rhapsody");
        player.adjustVolume(75);
        player.shuffle(); // Default method
        player.pauseMusic();
    }
    
    // Method using GPS interface
    public static void useGPS(GPS gps) {
        System.out.println("\n=== GPS Operations ===");
        gps.enableGPS(); // Default method
        gps.getCurrentLocation();
        gps.navigateTo("Central Park");
        gps.searchNearby("restaurants");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Multiple Interfaces Implementation Demo ===");
        
        // Create smartphone implementing all interfaces
        Smartphone phone = new Smartphone("Apple", "iPhone 15", 80);
        
        // Create tablet implementing some interfaces
        Tablet tablet = new Tablet("Samsung", "Galaxy Tab S9", true);
        
        // 1. Display interface constants and static methods
        System.out.println("\n1. Interface Constants and Static Methods:");
        Camera.displayCameraSpecs();
        Phone.displayPhoneFeatures();
        System.out.println("GPS Default Provider: " + GPS.DEFAULT_MAP_PROVIDER);
        
        // 2. Use smartphone as different interface types
        System.out.println("\n2. Smartphone Multi-Interface Usage:");
        phone.displayDeviceInfo();
        
        useCamera(phone);     // Smartphone as Camera
        usePhone(phone);      // Smartphone as Phone
        useMediaPlayer(phone); // Smartphone as MediaPlayer
        useGPS(phone);        // Smartphone as GPS
        
        phone.displayDeviceInfo();
        
        // 3. Use tablet with its supported interfaces
        System.out.println("\n3. Tablet Interface Usage:");
        tablet.displayTabletInfo();
        
        useCamera(tablet);     // Tablet as Camera
        useMediaPlayer(tablet); // Tablet as MediaPlayer
        useGPS(tablet);        // Tablet as GPS
        
        // Note: Cannot use tablet as Phone since it doesn't implement Phone interface
        // usePhone(tablet); // This would cause compilation error
        
        // 4. Demonstrate interface arrays and polymorphism
        System.out.println("\n4. Interface Arrays and Polymorphism:");
        
        Camera[] cameras = {phone, tablet};
        for (Camera cam : cameras) {
            cam.takePhoto(); // Polymorphic behavior
        }
        
        MediaPlayer[] players = {phone, tablet};
        for (MediaPlayer player : players) {
            player.playMusic("Interface Demo Song");
        }
        
        // 5. Demonstrate default and static methods
        System.out.println("\n5. Default and Static Methods:");
        
        // Default methods can be overridden or used as-is
        phone.applyFilter("Black & White");
        tablet.applyFilter("Sepia");
        
        phone.playRingtone();
        
        // Static methods called on interface
        Camera.displayCameraSpecs();
        
        // 6. Interface type checking
        System.out.println("\n6. Interface Type Checking:");
        
        Object[] devices = {phone, tablet};
        
        for (Object device : devices) {
            System.out.println("\nChecking device: " + device.getClass().getSimpleName());
            
            if (device instanceof Camera) {
                System.out.println("✓ Supports Camera functionality");
            }
            if (device instanceof Phone) {
                System.out.println("✓ Supports Phone functionality");
            }
            if (device instanceof GPS) {
                System.out.println("✓ Supports GPS functionality");
            }
            if (device instanceof MediaPlayer) {
                System.out.println("✓ Supports MediaPlayer functionality");
            }
        }
        
        // 7. Battery management for smartphone
        System.out.println("\n7. Battery Management:");
        phone.chargeBattery(20);
        phone.displayDeviceInfo();
        
        System.out.println("\n=== Multiple Interfaces Summary ===");
        System.out.println("✓ A class can implement multiple interfaces");
        System.out.println("✓ Interface constants are public, static, final by default");
        System.out.println("✓ Interface methods are public and abstract by default");
        System.out.println("✓ Default methods provide implementation in interfaces (Java 8+)");
        System.out.println("✓ Static methods belong to the interface, not implementations");
        System.out.println("✓ Enables multiple inheritance of type");
        System.out.println("✓ Promotes loose coupling and high cohesion");
    }
}