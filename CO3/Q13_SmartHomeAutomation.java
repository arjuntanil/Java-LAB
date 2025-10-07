package c03;

// Q13: Smart Home Automation System
// Comprehensive smart home system demonstrating advanced OOP concepts

// Abstract base class for all smart devices
abstract class SmartDevice {
    protected String deviceId;
    protected String deviceName;
    protected String location;
    protected boolean isPoweredOn;
    protected boolean isConnected;
    protected String manufacturer;
    protected double powerConsumption; // in watts
    protected java.util.Date installationDate;
    
    public SmartDevice(String deviceId, String deviceName, String location, String manufacturer) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.location = location;
        this.manufacturer = manufacturer;
        this.isPoweredOn = false;
        this.isConnected = false;
        this.installationDate = new java.util.Date();
    }
    
    // Abstract methods that must be implemented by all devices
    public abstract void turnOn();
    public abstract void turnOff();
    public abstract void displayStatus();
    public abstract String getDeviceType();
    
    // Concrete methods available to all devices
    public final void connect() {
        if (!isConnected) {
            isConnected = true;
            System.out.println("Device " + deviceName + " connected to network");
        } else {
            System.out.println("Device " + deviceName + " is already connected");
        }
    }
    
    public final void disconnect() {
        if (isConnected) {
            isConnected = false;
            isPoweredOn = false; // Can't be on if not connected
            System.out.println("Device " + deviceName + " disconnected from network");
        } else {
            System.out.println("Device " + deviceName + " is already disconnected");
        }
    }
    
    public final void displayBasicInfo() {
        System.out.println("Device ID: " + deviceId);
        System.out.println("Name: " + deviceName);
        System.out.println("Type: " + getDeviceType());
        System.out.println("Location: " + location);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Power Status: " + (isPoweredOn ? "ON" : "OFF"));
        System.out.println("Connection Status: " + (isConnected ? "Connected" : "Disconnected"));
        System.out.println("Power Consumption: " + powerConsumption + "W");
        System.out.println("Installation Date: " + installationDate);
    }
    
    // Getters
    public String getDeviceId() { return deviceId; }
    public String getDeviceName() { return deviceName; }
    public String getLocation() { return location; }
    public boolean isPoweredOn() { return isPoweredOn; }
    public boolean isConnected() { return isConnected; }
    public double getPowerConsumption() { return powerConsumption; }
}

// Interface for devices that can be dimmed
interface Dimmable {
    void setBrightness(int level);
    int getBrightness();
    void dim();
    void brighten();
    
    default boolean isValidBrightness(int level) {
        return level >= 0 && level <= 100;
    }
    
    default void displayBrightnessInfo() {
        System.out.println("Brightness Level: " + getBrightness() + "%");
    }
}

// Interface for devices with temperature control
interface TemperatureControllable {
    void setTemperature(double temperature);
    double getTemperature();
    void increaseTemperature();
    void decreaseTemperature();
    
    default boolean isValidTemperature(double temp) {
        return temp >= -10 && temp <= 50; // Celsius range for home devices
    }
    
    default void displayTemperatureInfo() {
        System.out.println("Current Temperature: " + getTemperature() + "°C");
    }
}

// Interface for devices that can play media
interface MediaPlayable {
    void play();
    void pause();
    void stop();
    void setVolume(int volume);
    int getVolume();
    
    default boolean isValidVolume(int volume) {
        return volume >= 0 && volume <= 100;
    }
    
    default void displayMediaInfo() {
        System.out.println("Volume Level: " + getVolume() + "%");
    }
}

// Interface for devices that can be scheduled
interface Schedulable {
    void setSchedule(String schedule);
    void enableSchedule();
    void disableSchedule();
    boolean isScheduleEnabled();
    
    default String getScheduleStatus() {
        return isScheduleEnabled() ? "Schedule Active" : "Schedule Inactive";
    }
}

// Interface for security devices
interface SecurityEnabled {
    void armSecurity();
    void disarmSecurity();
    boolean isArmed();
    void triggerAlert();
    
    default void displaySecurityStatus() {
        System.out.println("Security Status: " + (isArmed() ? "ARMED" : "DISARMED"));
    }
}

// Smart Light class implementing Dimmable and Schedulable
class SmartLight extends SmartDevice implements Dimmable, Schedulable {
    private int brightness;
    private String colorMode;
    private boolean isScheduleEnabled;
    private String schedule;
    
    public SmartLight(String deviceId, String deviceName, String location, String manufacturer) {
        super(deviceId, deviceName, location, manufacturer);
        this.brightness = 100;
        this.colorMode = "Warm White";
        this.powerConsumption = 12.0;
        this.isScheduleEnabled = false;
    }
    
    @Override
    public void turnOn() {
        if (isConnected) {
            isPoweredOn = true;
            System.out.println("💡 Smart light " + deviceName + " turned ON");
            displayBrightnessInfo();
        } else {
            System.out.println("Cannot turn on - device not connected");
        }
    }
    
    @Override
    public void turnOff() {
        isPoweredOn = false;
        System.out.println("💡 Smart light " + deviceName + " turned OFF");
    }
    
    @Override
    public void displayStatus() {
        System.out.println("\n=== Smart Light Status ===");
        displayBasicInfo();
        if (isPoweredOn) {
            displayBrightnessInfo();
            System.out.println("Color Mode: " + colorMode);
        }
        System.out.println("Schedule: " + getScheduleStatus());
    }
    
    @Override
    public String getDeviceType() {
        return "Smart Light";
    }
    
    // Dimmable implementation
    @Override
    public void setBrightness(int level) {
        if (isValidBrightness(level)) {
            brightness = level;
            if (isPoweredOn) {
                System.out.println("Brightness set to " + level + "%");
            }
        } else {
            System.out.println("Invalid brightness level");
        }
    }
    
    @Override
    public int getBrightness() {
        return brightness;
    }
    
    @Override
    public void dim() {
        setBrightness(Math.max(0, brightness - 10));
    }
    
    @Override
    public void brighten() {
        setBrightness(Math.min(100, brightness + 10));
    }
    
    // Schedulable implementation
    @Override
    public void setSchedule(String schedule) {
        this.schedule = schedule;
        System.out.println("Schedule set for " + deviceName + ": " + schedule);
    }
    
    @Override
    public void enableSchedule() {
        isScheduleEnabled = true;
        System.out.println("Schedule enabled for " + deviceName);
    }
    
    @Override
    public void disableSchedule() {
        isScheduleEnabled = false;
        System.out.println("Schedule disabled for " + deviceName);
    }
    
    @Override
    public boolean isScheduleEnabled() {
        return isScheduleEnabled;
    }
    
    public void setColorMode(String colorMode) {
        this.colorMode = colorMode;
        System.out.println("Color mode set to: " + colorMode);
    }
    
    public String getColorMode() {
        return colorMode;
    }
}

// Smart Thermostat class implementing TemperatureControllable and Schedulable
class SmartThermostat extends SmartDevice implements TemperatureControllable, Schedulable {
    private double currentTemperature;
    private double targetTemperature;
    private String mode; // heating, cooling, auto
    private boolean isScheduleEnabled;
    private String schedule;
    
    public SmartThermostat(String deviceId, String deviceName, String location, String manufacturer) {
        super(deviceId, deviceName, location, manufacturer);
        this.currentTemperature = 22.0;
        this.targetTemperature = 22.0;
        this.mode = "auto";
        this.powerConsumption = 25.0;
        this.isScheduleEnabled = false;
    }
    
    @Override
    public void turnOn() {
        if (isConnected) {
            isPoweredOn = true;
            System.out.println("🌡️ Smart thermostat " + deviceName + " turned ON");
            displayTemperatureInfo();
        } else {
            System.out.println("Cannot turn on - device not connected");
        }
    }
    
    @Override
    public void turnOff() {
        isPoweredOn = false;
        System.out.println("🌡️ Smart thermostat " + deviceName + " turned OFF");
    }
    
    @Override
    public void displayStatus() {
        System.out.println("\n=== Smart Thermostat Status ===");
        displayBasicInfo();
        if (isPoweredOn) {
            displayTemperatureInfo();
            System.out.println("Target Temperature: " + targetTemperature + "°C");
            System.out.println("Mode: " + mode);
        }
        System.out.println("Schedule: " + getScheduleStatus());
    }
    
    @Override
    public String getDeviceType() {
        return "Smart Thermostat";
    }
    
    // TemperatureControllable implementation
    @Override
    public void setTemperature(double temperature) {
        if (isValidTemperature(temperature)) {
            targetTemperature = temperature;
            if (isPoweredOn) {
                System.out.println("Target temperature set to " + temperature + "°C");
                simulateTemperatureChange();
            }
        } else {
            System.out.println("Invalid temperature setting");
        }
    }
    
    @Override
    public double getTemperature() {
        return currentTemperature;
    }
    
    @Override
    public void increaseTemperature() {
        setTemperature(targetTemperature + 1);
    }
    
    @Override
    public void decreaseTemperature() {
        setTemperature(targetTemperature - 1);
    }
    
    private void simulateTemperatureChange() {
        // Simulate gradual temperature change
        if (currentTemperature < targetTemperature) {
            currentTemperature = Math.min(targetTemperature, currentTemperature + 0.5);
            System.out.println("Heating... Current: " + currentTemperature + "°C");
        } else if (currentTemperature > targetTemperature) {
            currentTemperature = Math.max(targetTemperature, currentTemperature - 0.5);
            System.out.println("Cooling... Current: " + currentTemperature + "°C");
        }
    }
    
    // Schedulable implementation
    @Override
    public void setSchedule(String schedule) {
        this.schedule = schedule;
        System.out.println("Temperature schedule set: " + schedule);
    }
    
    @Override
    public void enableSchedule() {
        isScheduleEnabled = true;
        System.out.println("Temperature schedule enabled");
    }
    
    @Override
    public void disableSchedule() {
        isScheduleEnabled = false;
        System.out.println("Temperature schedule disabled");
    }
    
    @Override
    public boolean isScheduleEnabled() {
        return isScheduleEnabled;
    }
    
    public void setMode(String mode) {
        if (mode.equals("heating") || mode.equals("cooling") || mode.equals("auto")) {
            this.mode = mode;
            System.out.println("Thermostat mode set to: " + mode);
        } else {
            System.out.println("Invalid mode. Use: heating, cooling, or auto");
        }
    }
    
    public String getMode() {
        return mode;
    }
}

// Smart Speaker class implementing MediaPlayable
class SmartSpeaker extends SmartDevice implements MediaPlayable {
    private int volume;
    private boolean isPlaying;
    private String currentTrack;
    private String[] playlist;
    private int currentTrackIndex;
    
    public SmartSpeaker(String deviceId, String deviceName, String location, String manufacturer) {
        super(deviceId, deviceName, location, manufacturer);
        this.volume = 50;
        this.isPlaying = false;
        this.powerConsumption = 15.0;
        this.playlist = new String[]{"Song 1", "Song 2", "Song 3", "Podcast Episode"};
        this.currentTrackIndex = 0;
        this.currentTrack = playlist[0];
    }
    
    @Override
    public void turnOn() {
        if (isConnected) {
            isPoweredOn = true;
            System.out.println("🔊 Smart speaker " + deviceName + " turned ON");
            System.out.println("Ready to play music");
        } else {
            System.out.println("Cannot turn on - device not connected");
        }
    }
    
    @Override
    public void turnOff() {
        isPoweredOn = false;
        isPlaying = false;
        System.out.println("🔊 Smart speaker " + deviceName + " turned OFF");
    }
    
    @Override
    public void displayStatus() {
        System.out.println("\n=== Smart Speaker Status ===");
        displayBasicInfo();
        if (isPoweredOn) {
            displayMediaInfo();
            System.out.println("Playing: " + (isPlaying ? "Yes" : "No"));
            if (isPlaying) {
                System.out.println("Current Track: " + currentTrack);
            }
        }
    }
    
    @Override
    public String getDeviceType() {
        return "Smart Speaker";
    }
    
    // MediaPlayable implementation
    @Override
    public void play() {
        if (isPoweredOn) {
            isPlaying = true;
            System.out.println("▶️ Playing: " + currentTrack);
            System.out.println("Volume: " + volume + "%");
        } else {
            System.out.println("Cannot play - device is off");
        }
    }
    
    @Override
    public void pause() {
        if (isPoweredOn && isPlaying) {
            isPlaying = false;
            System.out.println("⏸️ Paused: " + currentTrack);
        } else {
            System.out.println("Nothing is currently playing");
        }
    }
    
    @Override
    public void stop() {
        if (isPoweredOn) {
            isPlaying = false;
            System.out.println("⏹️ Stopped playback");
        }
    }
    
    @Override
    public void setVolume(int volume) {
        if (isValidVolume(volume)) {
            this.volume = volume;
            if (isPoweredOn) {
                System.out.println("Volume set to " + volume + "%");
            }
        } else {
            System.out.println("Invalid volume level");
        }
    }
    
    @Override
    public int getVolume() {
        return volume;
    }
    
    public void nextTrack() {
        if (isPoweredOn) {
            currentTrackIndex = (currentTrackIndex + 1) % playlist.length;
            currentTrack = playlist[currentTrackIndex];
            System.out.println("⏭️ Next track: " + currentTrack);
            if (isPlaying) {
                play();
            }
        }
    }
    
    public void previousTrack() {
        if (isPoweredOn) {
            currentTrackIndex = (currentTrackIndex - 1 + playlist.length) % playlist.length;
            currentTrack = playlist[currentTrackIndex];
            System.out.println("⏮️ Previous track: " + currentTrack);
            if (isPlaying) {
                play();
            }
        }
    }
}

// Smart Security Camera class implementing SecurityEnabled and Schedulable
class SmartSecurityCamera extends SmartDevice implements SecurityEnabled, Schedulable {
    private boolean isRecording;
    private boolean isArmed;
    private String recordingQuality;
    private boolean motionDetection;
    private boolean nightVision;
    private boolean isScheduleEnabled;
    private String schedule;
    
    public SmartSecurityCamera(String deviceId, String deviceName, String location, String manufacturer) {
        super(deviceId, deviceName, location, manufacturer);
        this.isRecording = false;
        this.isArmed = false;
        this.recordingQuality = "1080p";
        this.motionDetection = true;
        this.nightVision = true;
        this.powerConsumption = 8.0;
        this.isScheduleEnabled = false;
    }
    
    @Override
    public void turnOn() {
        if (isConnected) {
            isPoweredOn = true;
            System.out.println("📹 Security camera " + deviceName + " turned ON");
            System.out.println("Camera ready for monitoring");
        } else {
            System.out.println("Cannot turn on - device not connected");
        }
    }
    
    @Override
    public void turnOff() {
        isPoweredOn = false;
        isRecording = false;
        isArmed = false;
        System.out.println("📹 Security camera " + deviceName + " turned OFF");
    }
    
    @Override
    public void displayStatus() {
        System.out.println("\n=== Smart Security Camera Status ===");
        displayBasicInfo();
        if (isPoweredOn) {
            displaySecurityStatus();
            System.out.println("Recording: " + (isRecording ? "Yes" : "No"));
            System.out.println("Quality: " + recordingQuality);
            System.out.println("Motion Detection: " + (motionDetection ? "Enabled" : "Disabled"));
            System.out.println("Night Vision: " + (nightVision ? "Enabled" : "Disabled"));
        }
        System.out.println("Schedule: " + getScheduleStatus());
    }
    
    @Override
    public String getDeviceType() {
        return "Smart Security Camera";
    }
    
    // SecurityEnabled implementation
    @Override
    public void armSecurity() {
        if (isPoweredOn) {
            isArmed = true;
            startRecording();
            System.out.println("🔒 Security camera armed");
        } else {
            System.out.println("Cannot arm - camera is off");
        }
    }
    
    @Override
    public void disarmSecurity() {
        isArmed = false;
        stopRecording();
        System.out.println("🔓 Security camera disarmed");
    }
    
    @Override
    public boolean isArmed() {
        return isArmed;
    }
    
    @Override
    public void triggerAlert() {
        if (isArmed && isPoweredOn) {
            System.out.println("🚨 SECURITY ALERT! Motion detected by " + deviceName);
            System.out.println("📹 Recording incident");
            System.out.println("📧 Notification sent to homeowner");
        }
    }
    
    public void startRecording() {
        if (isPoweredOn) {
            isRecording = true;
            System.out.println("🔴 Recording started in " + recordingQuality);
        }
    }
    
    public void stopRecording() {
        isRecording = false;
        System.out.println("⏹️ Recording stopped");
    }
    
    // Schedulable implementation
    @Override
    public void setSchedule(String schedule) {
        this.schedule = schedule;
        System.out.println("Recording schedule set: " + schedule);
    }
    
    @Override
    public void enableSchedule() {
        isScheduleEnabled = true;
        System.out.println("Recording schedule enabled");
    }
    
    @Override
    public void disableSchedule() {
        isScheduleEnabled = false;
        System.out.println("Recording schedule disabled");
    }
    
    @Override
    public boolean isScheduleEnabled() {
        return isScheduleEnabled;
    }
    
    public void setRecordingQuality(String quality) {
        if (quality.equals("720p") || quality.equals("1080p") || quality.equals("4K")) {
            this.recordingQuality = quality;
            System.out.println("Recording quality set to: " + quality);
        } else {
            System.out.println("Invalid quality. Use: 720p, 1080p, or 4K");
        }
    }
    
    public void enableMotionDetection() {
        motionDetection = true;
        System.out.println("Motion detection enabled");
    }
    
    public void disableMotionDetection() {
        motionDetection = false;
        System.out.println("Motion detection disabled");
    }
}

// Smart Door Lock class implementing SecurityEnabled
class SmartDoorLock extends SmartDevice implements SecurityEnabled {
    private boolean isLocked;
    private boolean isArmed;
    private String accessCode;
    private boolean autoLock;
    private int autoLockDelay; // in seconds
    
    public SmartDoorLock(String deviceId, String deviceName, String location, String manufacturer) {
        super(deviceId, deviceName, location, manufacturer);
        this.isLocked = true;
        this.isArmed = false;
        this.accessCode = "1234";
        this.autoLock = true;
        this.autoLockDelay = 30;
        this.powerConsumption = 5.0;
    }
    
    @Override
    public void turnOn() {
        if (isConnected) {
            isPoweredOn = true;
            System.out.println("🚪 Smart door lock " + deviceName + " activated");
            System.out.println("Lock status: " + (isLocked ? "LOCKED" : "UNLOCKED"));
        } else {
            System.out.println("Cannot activate - device not connected");
        }
    }
    
    @Override
    public void turnOff() {
        isPoweredOn = false;
        System.out.println("🚪 Smart door lock " + deviceName + " deactivated");
        System.out.println("⚠️ Manual key required for access");
    }
    
    @Override
    public void displayStatus() {
        System.out.println("\n=== Smart Door Lock Status ===");
        displayBasicInfo();
        if (isPoweredOn) {
            displaySecurityStatus();
            System.out.println("Lock Status: " + (isLocked ? "LOCKED" : "UNLOCKED"));
            System.out.println("Auto Lock: " + (autoLock ? "Enabled (" + autoLockDelay + "s)" : "Disabled"));
        }
    }
    
    @Override
    public String getDeviceType() {
        return "Smart Door Lock";
    }
    
    // SecurityEnabled implementation
    @Override
    public void armSecurity() {
        if (isPoweredOn) {
            isArmed = true;
            if (!isLocked) {
                lock();
            }
            System.out.println("🔒 Door security armed");
        } else {
            System.out.println("Cannot arm - lock is off");
        }
    }
    
    @Override
    public void disarmSecurity() {
        isArmed = false;
        System.out.println("🔓 Door security disarmed");
    }
    
    @Override
    public boolean isArmed() {
        return isArmed;
    }
    
    @Override
    public void triggerAlert() {
        if (isArmed && isPoweredOn) {
            System.out.println("🚨 SECURITY ALERT! Unauthorized access attempt at " + deviceName);
            System.out.println("🔒 Lock automatically engaged");
            System.out.println("📧 Alert sent to homeowner");
            lock();
        }
    }
    
    public void lock() {
        if (isPoweredOn) {
            isLocked = true;
            System.out.println("🔒 Door locked");
        }
    }
    
    public void unlock(String code) {
        if (isPoweredOn) {
            if (code.equals(accessCode)) {
                isLocked = false;
                System.out.println("🔓 Door unlocked");
                if (autoLock) {
                    System.out.println("⏰ Auto-lock will engage in " + autoLockDelay + " seconds");
                }
            } else {
                System.out.println("❌ Invalid access code");
                if (isArmed) {
                    triggerAlert();
                }
            }
        } else {
            System.out.println("Cannot unlock - device is off");
        }
    }
    
    public void setAccessCode(String newCode) {
        if (newCode.length() >= 4) {
            this.accessCode = newCode;
            System.out.println("Access code updated");
        } else {
            System.out.println("Access code must be at least 4 digits");
        }
    }
    
    public void setAutoLock(boolean autoLock, int delay) {
        this.autoLock = autoLock;
        this.autoLockDelay = delay;
        System.out.println("Auto-lock " + (autoLock ? "enabled" : "disabled") + 
                         (autoLock ? " with " + delay + "s delay" : ""));
    }
}

// Smart Home Hub - Central control system
class SmartHomeHub {
    private SmartDevice[] devices;
    private int deviceCount;
    private boolean securityMode;
    private boolean awayMode;
    private static final int MAX_DEVICES = 50;
    
    public SmartHomeHub() {
        devices = new SmartDevice[MAX_DEVICES];
        deviceCount = 0;
        securityMode = false;
        awayMode = false;
    }
    
    public void addDevice(SmartDevice device) {
        if (deviceCount < MAX_DEVICES) {
            devices[deviceCount++] = device;
            device.connect();
            System.out.println("Device added to Smart Home Hub: " + device.getDeviceName());
        } else {
            System.out.println("Maximum device limit reached");
        }
    }
    
    public void removeDevice(String deviceId) {
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i].getDeviceId().equals(deviceId)) {
                devices[i].disconnect();
                // Shift devices down
                for (int j = i; j < deviceCount - 1; j++) {
                    devices[j] = devices[j + 1];
                }
                deviceCount--;
                System.out.println("Device removed from Smart Home Hub");
                return;
            }
        }
        System.out.println("Device not found");
    }
    
    public SmartDevice findDevice(String deviceId) {
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i].getDeviceId().equals(deviceId)) {
                return devices[i];
            }
        }
        return null;
    }
    
    public void displayAllDevices() {
        System.out.println("\n=== Smart Home Devices ===");
        for (int i = 0; i < deviceCount; i++) {
            devices[i].displayStatus();
        }
    }
    
    public void turnOnAllDevices() {
        System.out.println("\n🏠 Turning on all devices...");
        for (int i = 0; i < deviceCount; i++) {
            devices[i].turnOn();
        }
    }
    
    public void turnOffAllDevices() {
        System.out.println("\n🏠 Turning off all devices...");
        for (int i = 0; i < deviceCount; i++) {
            devices[i].turnOff();
        }
    }
    
    public void enableSecurityMode() {
        securityMode = true;
        System.out.println("\n🔒 Security mode ENABLED");
        
        // Arm all security devices
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i] instanceof SecurityEnabled) {
                ((SecurityEnabled) devices[i]).armSecurity();
            }
        }
    }
    
    public void disableSecurityMode() {
        securityMode = false;
        System.out.println("\n🔓 Security mode DISABLED");
        
        // Disarm all security devices
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i] instanceof SecurityEnabled) {
                ((SecurityEnabled) devices[i]).disarmSecurity();
            }
        }
    }
    
    public void enableAwayMode() {
        awayMode = true;
        System.out.println("\n✈️ Away mode ENABLED");
        
        // Turn off lights and media devices, enable security
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i] instanceof SmartLight || devices[i] instanceof SmartSpeaker) {
                devices[i].turnOff();
            }
            if (devices[i] instanceof SecurityEnabled) {
                ((SecurityEnabled) devices[i]).armSecurity();
            }
        }
        
        // Set thermostat to energy saving mode
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i] instanceof SmartThermostat) {
                ((SmartThermostat) devices[i]).setTemperature(18.0); // Energy saving temp
            }
        }
    }
    
    public void disableAwayMode() {
        awayMode = false;
        System.out.println("\n🏠 Welcome home! Away mode DISABLED");
        
        // Turn on lights, disable security
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i] instanceof SmartLight) {
                devices[i].turnOn();
            }
            if (devices[i] instanceof SecurityEnabled) {
                ((SecurityEnabled) devices[i]).disarmSecurity();
            }
        }
        
        // Reset thermostat to comfortable temperature
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i] instanceof SmartThermostat) {
                ((SmartThermostat) devices[i]).setTemperature(22.0);
            }
        }
    }
    
    public void generateEnergyReport() {
        System.out.println("\n=== Energy Consumption Report ===");
        double totalConsumption = 0;
        int activeDevices = 0;
        
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i].isPoweredOn()) {
                double consumption = devices[i].getPowerConsumption();
                totalConsumption += consumption;
                activeDevices++;
                System.out.println(devices[i].getDeviceName() + " (" + 
                                 devices[i].getLocation() + "): " + consumption + "W");
            }
        }
        
        System.out.println("\nTotal Active Devices: " + activeDevices);
        System.out.println("Total Power Consumption: " + String.format("%.1f", totalConsumption) + "W");
        System.out.println("Estimated Daily Cost: $" + String.format("%.2f", totalConsumption * 24 * 0.12 / 1000));
    }
    
    public void executeScene(String sceneName) {
        System.out.println("\n🎬 Executing scene: " + sceneName);
        
        switch (sceneName.toLowerCase()) {
            case "movie night":
                // Dim lights, turn on speaker
                for (int i = 0; i < deviceCount; i++) {
                    if (devices[i] instanceof SmartLight) {
                        SmartLight light = (SmartLight) devices[i];
                        light.turnOn();
                        light.setBrightness(20);
                    }
                    if (devices[i] instanceof SmartSpeaker) {
                        devices[i].turnOn();
                        ((SmartSpeaker) devices[i]).setVolume(60);
                    }
                }
                break;
                
            case "bedtime":
                // Turn off all lights and media, arm security
                for (int i = 0; i < deviceCount; i++) {
                    if (devices[i] instanceof SmartLight || devices[i] instanceof SmartSpeaker) {
                        devices[i].turnOff();
                    }
                    if (devices[i] instanceof SecurityEnabled) {
                        ((SecurityEnabled) devices[i]).armSecurity();
                    }
                    if (devices[i] instanceof SmartThermostat) {
                        ((SmartThermostat) devices[i]).setTemperature(20.0);
                    }
                }
                break;
                
            case "wake up":
                // Turn on lights gradually, set comfortable temperature
                for (int i = 0; i < deviceCount; i++) {
                    if (devices[i] instanceof SmartLight) {
                        SmartLight light = (SmartLight) devices[i];
                        light.turnOn();
                        light.setBrightness(70);
                    }
                    if (devices[i] instanceof SmartThermostat) {
                        ((SmartThermostat) devices[i]).setTemperature(23.0);
                    }
                    if (devices[i] instanceof SecurityEnabled) {
                        ((SecurityEnabled) devices[i]).disarmSecurity();
                    }
                }
                break;
                
            default:
                System.out.println("Unknown scene: " + sceneName);
        }
    }
    
    public void displaySystemStatus() {
        System.out.println("\n=== Smart Home System Status ===");
        System.out.println("Total Devices: " + deviceCount);
        System.out.println("Security Mode: " + (securityMode ? "ENABLED" : "DISABLED"));
        System.out.println("Away Mode: " + (awayMode ? "ENABLED" : "DISABLED"));
        
        int connectedDevices = 0, activeDevices = 0;
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i].isConnected()) connectedDevices++;
            if (devices[i].isPoweredOn()) activeDevices++;
        }
        
        System.out.println("Connected Devices: " + connectedDevices + "/" + deviceCount);
        System.out.println("Active Devices: " + activeDevices + "/" + deviceCount);
    }
}

public class Q13_SmartHomeAutomation {
    
    public static void main(String[] args) {
        System.out.println("=== Smart Home Automation System ===");
        
        // Create smart home hub
        SmartHomeHub hub = new SmartHomeHub();
        
        // 1. Create different smart devices
        System.out.println("\n1. Creating Smart Devices:");
        
        SmartLight livingRoomLight = new SmartLight("LT001", "Living Room Light", "Living Room", "Philips");
        SmartLight bedroomLight = new SmartLight("LT002", "Bedroom Light", "Bedroom", "Philips");
        SmartLight kitchenLight = new SmartLight("LT003", "Kitchen Light", "Kitchen", "IKEA");
        
        SmartThermostat mainThermostat = new SmartThermostat("TH001", "Main Thermostat", "Hallway", "Nest");
        
        SmartSpeaker livingRoomSpeaker = new SmartSpeaker("SP001", "Living Room Speaker", "Living Room", "Amazon");
        SmartSpeaker kitchenSpeaker = new SmartSpeaker("SP002", "Kitchen Speaker", "Kitchen", "Google");
        
        SmartSecurityCamera frontCamera = new SmartSecurityCamera("CAM001", "Front Door Camera", "Front Door", "Ring");
        SmartSecurityCamera backCamera = new SmartSecurityCamera("CAM002", "Backyard Camera", "Backyard", "Arlo");
        
        SmartDoorLock frontDoor = new SmartDoorLock("LOCK001", "Front Door Lock", "Front Door", "August");
        SmartDoorLock backDoor = new SmartDoorLock("LOCK002", "Back Door Lock", "Back Door", "Yale");
        
        // 2. Add devices to hub
        System.out.println("\n2. Adding Devices to Hub:");
        hub.addDevice(livingRoomLight);
        hub.addDevice(bedroomLight);
        hub.addDevice(kitchenLight);
        hub.addDevice(mainThermostat);
        hub.addDevice(livingRoomSpeaker);
        hub.addDevice(kitchenSpeaker);
        hub.addDevice(frontCamera);
        hub.addDevice(backCamera);
        hub.addDevice(frontDoor);
        hub.addDevice(backDoor);
        
        // 3. Turn on devices and display status
        System.out.println("\n3. Initial System Setup:");
        hub.turnOnAllDevices();
        hub.displaySystemStatus();
        
        // 4. Demonstrate polymorphism
        System.out.println("\n4. Polymorphic Device Operations:");
        SmartDevice[] devices = {livingRoomLight, mainThermostat, livingRoomSpeaker, frontCamera};
        
        for (SmartDevice device : devices) {
            System.out.println("\nDevice: " + device.getDeviceName());
            device.displayStatus();
        }
        
        // 5. Interface-specific operations
        System.out.println("\n5. Interface-Specific Operations:");
        
        // Dimmable operations
        System.out.println("\nDimmable Devices:");
        livingRoomLight.setBrightness(75);
        livingRoomLight.dim();
        livingRoomLight.brighten();
        
        // TemperatureControllable operations
        System.out.println("\nTemperature Control:");
        mainThermostat.setTemperature(24.0);
        mainThermostat.increaseTemperature();
        mainThermostat.setMode("cooling");
        
        // MediaPlayable operations
        System.out.println("\nMedia Playable Devices:");
        livingRoomSpeaker.play();
        livingRoomSpeaker.setVolume(70);
        livingRoomSpeaker.nextTrack();
        livingRoomSpeaker.pause();
        
        // SecurityEnabled operations
        System.out.println("\nSecurity Devices:");
        frontCamera.armSecurity();
        frontDoor.lock();
        frontDoor.unlock("1234");
        
        // 6. Schedulable devices
        System.out.println("\n6. Device Scheduling:");
        livingRoomLight.setSchedule("Turn on at 7:00 PM, turn off at 11:00 PM");
        livingRoomLight.enableSchedule();
        
        mainThermostat.setSchedule("22°C during day, 20°C at night");
        mainThermostat.enableSchedule();
        
        frontCamera.setSchedule("Record from 10:00 PM to 6:00 AM");
        frontCamera.enableSchedule();
        
        // 7. Smart home scenes
        System.out.println("\n7. Smart Home Scenes:");
        hub.executeScene("Movie Night");
        
        try {
            Thread.sleep(2000); // Simulate time passing
        } catch (InterruptedException e) {
            // Handle interruption
        }
        
        hub.executeScene("Bedtime");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle interruption
        }
        
        hub.executeScene("Wake Up");
        
        // 8. Security mode operations
        System.out.println("\n8. Security Mode Operations:");
        hub.enableSecurityMode();
        
        // Simulate security alert
        frontCamera.triggerAlert();
        frontDoor.unlock("wrong_code"); // This will trigger alert
        
        hub.disableSecurityMode();
        
        // 9. Away mode
        System.out.println("\n9. Away Mode:");
        hub.enableAwayMode();
        hub.displaySystemStatus();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle interruption
        }
        
        hub.disableAwayMode();
        
        // 10. Advanced device features
        System.out.println("\n10. Advanced Device Features:");
        
        // Smart light color and brightness
        livingRoomLight.setColorMode("Cool Blue");
        bedroomLight.setColorMode("Warm Orange");
        
        // Speaker controls
        kitchenSpeaker.turnOn();
        kitchenSpeaker.play();
        kitchenSpeaker.previousTrack();
        
        // Camera settings
        backCamera.setRecordingQuality("4K");
        backCamera.enableMotionDetection();
        
        // Door lock settings
        backDoor.setAccessCode("5678");
        backDoor.setAutoLock(true, 45);
        
        // 11. Energy monitoring
        System.out.println("\n11. Energy Monitoring:");
        hub.generateEnergyReport();
        
        // 12. Interface type checking
        System.out.println("\n12. Dynamic Interface Detection:");
        SmartDevice[] allDevices = {livingRoomLight, mainThermostat, livingRoomSpeaker, 
                                   frontCamera, frontDoor};
        
        for (SmartDevice device : allDevices) {
            System.out.println("\n" + device.getDeviceName() + " (" + device.getDeviceType() + "):");
            
            if (device instanceof Dimmable) {
                System.out.println("  ✓ Supports dimming");
            }
            if (device instanceof TemperatureControllable) {
                System.out.println("  ✓ Controls temperature");
            }
            if (device instanceof MediaPlayable) {
                System.out.println("  ✓ Plays media");
            }
            if (device instanceof SecurityEnabled) {
                System.out.println("  ✓ Security features");
            }
            if (device instanceof Schedulable) {
                System.out.println("  ✓ Supports scheduling");
            }
        }
        
        // 13. Final system status
        System.out.println("\n13. Final System Status:");
        hub.displayAllDevices();
        hub.displaySystemStatus();
        
        System.out.println("\n=== Smart Home Automation Summary ===");
        System.out.println("✓ Abstract classes provide device structure");
        System.out.println("✓ Multiple interfaces enable specialized capabilities");
        System.out.println("✓ Polymorphism enables uniform device management");
        System.out.println("✓ Inheritance supports device type specialization");
        System.out.println("✓ Encapsulation protects device state");
        System.out.println("✓ Final methods ensure core functionality");
        System.out.println("✓ Interface default methods provide common utilities");
        System.out.println("✓ Centralized hub manages all devices");
        System.out.println("✓ Security integration across multiple device types");
        System.out.println("✓ Energy monitoring and automation scenes");
        System.out.println("✓ System demonstrates real-world IoT architecture");
    }
}