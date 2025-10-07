package c03;

import java.util.Scanner;

// Q10: Design a Plugin System Using Abstract Classes and Interfaces
// Demonstrates plugin architecture with abstract classes and interfaces

// Abstract base class for all plugins
abstract class Plugin {
    protected String pluginName;
    protected String version;
    protected String author;
    protected boolean isEnabled;
    protected long loadTime;
    
    public Plugin(String pluginName, String version, String author) {
        this.pluginName = pluginName;
        this.version = version;
        this.author = author;
        this.isEnabled = false;
        this.loadTime = System.currentTimeMillis();
    }
    
    // Abstract method that all plugins must implement
    public abstract void execute();
    
    // Abstract method for plugin initialization
    public abstract boolean initialize();
    
    // Abstract method for cleanup
    public abstract void cleanup();
    
    // Concrete method for plugin lifecycle management
    public final void load() {
        System.out.println("Loading plugin: " + pluginName);
        if (initialize()) {
            isEnabled = true;
            System.out.println("✓ Plugin " + pluginName + " loaded successfully");
        } else {
            System.out.println("✗ Failed to load plugin " + pluginName);
        }
    }
    
    // Concrete method for unloading
    public final void unload() {
        System.out.println("Unloading plugin: " + pluginName);
        cleanup();
        isEnabled = false;
        System.out.println("✓ Plugin " + pluginName + " unloaded");
    }
    
    // Concrete method for displaying plugin info
    public void displayPluginInfo() {
        System.out.println("\n--- Plugin Information ---");
        System.out.println("Name: " + pluginName);
        System.out.println("Version: " + version);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (isEnabled ? "Enabled" : "Disabled"));
        System.out.println("Load Time: " + new java.util.Date(loadTime));
    }
    
    // Getters
    public String getPluginName() {
        return pluginName;
    }
    
    public boolean isEnabled() {
        return isEnabled;
    }
}

// Interface for configurable plugins
interface Configurable {
    void configure();
    void resetToDefaults();
    void saveConfiguration();
    void loadConfiguration();
    
    // Default method for common configuration operations
    default void displayConfiguration() {
        System.out.println("Displaying plugin configuration...");
    }
    
    // Static method for configuration validation
    static boolean validateConfiguration(String config) {
        return config != null && !config.trim().isEmpty();
    }
}

// Interface for plugins that can be scheduled
interface Schedulable {
    void setSchedule(String schedule);
    void startSchedule();
    void stopSchedule();
    
    default String getNextRunTime() {
        return "Next run: Not scheduled";
    }
}

// Interface for plugins that need network access
interface NetworkEnabled {
    void connectToNetwork();
    void disconnectFromNetwork();
    boolean isConnected();
    
    default void checkConnection() {
        System.out.println("Checking network connection: " + 
                         (isConnected() ? "Connected" : "Disconnected"));
    }
}

// Logging Plugin
class LoggingPlugin extends Plugin implements Configurable {
    private String logLevel;
    private String logFile;
    private boolean enableConsoleOutput;
    
    public LoggingPlugin() {
        super("Logger", "1.0", "System Admin");
        this.logLevel = "INFO";
        this.logFile = "application.log";
        this.enableConsoleOutput = true;
    }
    
    @Override
    public boolean initialize() {
        System.out.println("Initializing logging system...");
        System.out.println("Log file: " + logFile);
        System.out.println("Log level: " + logLevel);
        return true;
    }
    
    @Override
    public void execute() {
        if (isEnabled) {
            System.out.println("Logging plugin executing...");
            System.out.println("📝 Writing logs to " + logFile);
            System.out.println("Log level: " + logLevel);
            if (enableConsoleOutput) {
                System.out.println("Console output enabled");
            }
        } else {
            System.out.println("Logging plugin is not enabled");
        }
    }
    
    @Override
    public void cleanup() {
        System.out.println("Closing log files and flushing buffers...");
    }
    
    // Configurable interface implementation
    @Override
    public void configure() {
        System.out.println("Configuring logging plugin...");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter log level (DEBUG/INFO/WARN/ERROR): ");
        String newLogLevel = scanner.next();
        if (newLogLevel.matches("DEBUG|INFO|WARN|ERROR")) {
            logLevel = newLogLevel;
            System.out.println("Log level set to: " + logLevel);
        }
        
        System.out.print("Enter log file name: ");
        String newLogFile = scanner.next();
        if (Configurable.validateConfiguration(newLogFile)) {
            logFile = newLogFile;
            System.out.println("Log file set to: " + logFile);
        }
    }
    
    @Override
    public void resetToDefaults() {
        logLevel = "INFO";
        logFile = "application.log";
        enableConsoleOutput = true;
        System.out.println("Logging configuration reset to defaults");
    }
    
    @Override
    public void saveConfiguration() {
        System.out.println("Saving logging configuration...");
        System.out.println("Config saved: LogLevel=" + logLevel + ", LogFile=" + logFile);
    }
    
    @Override
    public void loadConfiguration() {
        System.out.println("Loading logging configuration from file...");
        // Simulate loading from config file
        System.out.println("Configuration loaded successfully");
    }
}

// Data Backup Plugin
class DataBackupPlugin extends Plugin implements Configurable, Schedulable, NetworkEnabled {
    private String backupDirectory;
    private String schedule;
    private boolean isScheduled;
    private boolean isNetworkConnected;
    private String remoteBackupServer;
    
    public DataBackupPlugin() {
        super("DataBackup", "2.1", "Backup Solutions Inc.");
        this.backupDirectory = "/backup";
        this.schedule = "daily";
        this.isScheduled = false;
        this.isNetworkConnected = false;
        this.remoteBackupServer = "backup.server.com";
    }
    
    @Override
    public boolean initialize() {
        System.out.println("Initializing backup system...");
        System.out.println("Backup directory: " + backupDirectory);
        System.out.println("Remote server: " + remoteBackupServer);
        return true;
    }
    
    @Override
    public void execute() {
        if (isEnabled) {
            System.out.println("Backup plugin executing...");
            System.out.println("💾 Starting backup process");
            System.out.println("Backup location: " + backupDirectory);
            
            if (isNetworkConnected) {
                System.out.println("Uploading to remote server: " + remoteBackupServer);
            } else {
                System.out.println("Local backup only (no network connection)");
            }
            
            System.out.println("Backup completed successfully");
        } else {
            System.out.println("Backup plugin is not enabled");
        }
    }
    
    @Override
    public void cleanup() {
        System.out.println("Cleaning up temporary backup files...");
        if (isNetworkConnected) {
            disconnectFromNetwork();
        }
    }
    
    // Configurable implementation
    @Override
    public void configure() {
        System.out.println("Configuring backup plugin...");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter backup directory: ");
        String newBackupDir = scanner.next();
        if (Configurable.validateConfiguration(newBackupDir)) {
            backupDirectory = newBackupDir;
            System.out.println("Backup directory set to: " + backupDirectory);
        }
        
        System.out.print("Enter remote server: ");
        String newServer = scanner.next();
        if (Configurable.validateConfiguration(newServer)) {
            remoteBackupServer = newServer;
            System.out.println("Remote server set to: " + remoteBackupServer);
        }
    }
    
    @Override
    public void resetToDefaults() {
        backupDirectory = "/backup";
        remoteBackupServer = "backup.server.com";
        schedule = "daily";
        System.out.println("Backup configuration reset to defaults");
    }
    
    @Override
    public void saveConfiguration() {
        System.out.println("Saving backup configuration...");
        System.out.println("Config saved: BackupDir=" + backupDirectory + 
                         ", Server=" + remoteBackupServer);
    }
    
    @Override
    public void loadConfiguration() {
        System.out.println("Loading backup configuration...");
        System.out.println("Configuration loaded successfully");
    }
    
    // Schedulable implementation
    @Override
    public void setSchedule(String schedule) {
        this.schedule = schedule;
        System.out.println("Backup schedule set to: " + schedule);
    }
    
    @Override
    public void startSchedule() {
        isScheduled = true;
        System.out.println("⏰ Backup schedule started: " + schedule);
    }
    
    @Override
    public void stopSchedule() {
        isScheduled = false;
        System.out.println("Backup schedule stopped");
    }
    
    @Override
    public String getNextRunTime() {
        if (isScheduled) {
            return "Next backup: Tomorrow at 2:00 AM";
        }
        return "No backup scheduled";
    }
    
    // NetworkEnabled implementation
    @Override
    public void connectToNetwork() {
        System.out.println("Connecting to backup server...");
        isNetworkConnected = true;
        System.out.println("✓ Connected to " + remoteBackupServer);
    }
    
    @Override
    public void disconnectFromNetwork() {
        System.out.println("Disconnecting from backup server...");
        isNetworkConnected = false;
        System.out.println("✓ Disconnected from " + remoteBackupServer);
    }
    
    @Override
    public boolean isConnected() {
        return isNetworkConnected;
    }
}

// Security Scanner Plugin
class SecurityScannerPlugin extends Plugin implements Configurable, Schedulable {
    private String scanType;
    private boolean realTimeProtection;
    private String threatDatabase;
    
    public SecurityScannerPlugin() {
        super("SecurityScanner", "3.0", "CyberSecurity Corp.");
        this.scanType = "full";
        this.realTimeProtection = true;
        this.threatDatabase = "latest";
    }
    
    @Override
    public boolean initialize() {
        System.out.println("Initializing security scanner...");
        System.out.println("Loading threat database: " + threatDatabase);
        System.out.println("Real-time protection: " + realTimeProtection);
        return true;
    }
    
    @Override
    public void execute() {
        if (isEnabled) {
            System.out.println("Security scanner executing...");
            System.out.println("🛡️ Running " + scanType + " security scan");
            System.out.println("Scanning for malware, viruses, and threats...");
            System.out.println("Scan completed. No threats detected.");
        } else {
            System.out.println("Security scanner is not enabled");
        }
    }
    
    @Override
    public void cleanup() {
        System.out.println("Saving scan results and cleaning up...");
    }
    
    @Override
    public void configure() {
        System.out.println("Configuring security scanner...");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter scan type (quick/full/custom): ");
        String newScanType = scanner.next();
        if (newScanType.matches("quick|full|custom")) {
            scanType = newScanType;
            System.out.println("Scan type set to: " + scanType);
        }
    }
    
    @Override
    public void resetToDefaults() {
        scanType = "full";
        realTimeProtection = true;
        threatDatabase = "latest";
        System.out.println("Security scanner configuration reset to defaults");
    }
    
    @Override
    public void saveConfiguration() {
        System.out.println("Saving security configuration...");
    }
    
    @Override
    public void loadConfiguration() {
        System.out.println("Loading security configuration...");
    }
    
    @Override
    public void setSchedule(String schedule) {
        System.out.println("Security scan schedule set to: " + schedule);
    }
    
    @Override
    public void startSchedule() {
        System.out.println("⏰ Security scan schedule started");
    }
    
    @Override
    public void stopSchedule() {
        System.out.println("Security scan schedule stopped");
    }
}

// Plugin Manager class
class PluginManager {
    private Plugin[] plugins;
    private int pluginCount;
    private static final int MAX_PLUGINS = 10;
    
    public PluginManager() {
        plugins = new Plugin[MAX_PLUGINS];
        pluginCount = 0;
    }
    
    public void registerPlugin(Plugin plugin) {
        if (pluginCount < MAX_PLUGINS) {
            plugins[pluginCount++] = plugin;
            System.out.println("Plugin registered: " + plugin.getPluginName());
        } else {
            System.out.println("Maximum plugin limit reached");
        }
    }
    
    public void loadAllPlugins() {
        System.out.println("\n=== Loading All Plugins ===");
        for (int i = 0; i < pluginCount; i++) {
            plugins[i].load();
        }
    }
    
    public void executeAllPlugins() {
        System.out.println("\n=== Executing All Plugins ===");
        for (int i = 0; i < pluginCount; i++) {
            if (plugins[i].isEnabled()) {
                plugins[i].execute();
                System.out.println();
            }
        }
    }
    
    public void configurePlugin(String pluginName) {
        Plugin plugin = findPlugin(pluginName);
        if (plugin != null && plugin instanceof Configurable) {
            ((Configurable) plugin).configure();
        } else {
            System.out.println("Plugin not found or not configurable: " + pluginName);
        }
    }
    
    public void schedulePlugin(String pluginName, String schedule) {
        Plugin plugin = findPlugin(pluginName);
        if (plugin != null && plugin instanceof Schedulable) {
            Schedulable schedulable = (Schedulable) plugin;
            schedulable.setSchedule(schedule);
            schedulable.startSchedule();
        } else {
            System.out.println("Plugin not found or not schedulable: " + pluginName);
        }
    }
    
    public void connectNetworkPlugin(String pluginName) {
        Plugin plugin = findPlugin(pluginName);
        if (plugin != null && plugin instanceof NetworkEnabled) {
            ((NetworkEnabled) plugin).connectToNetwork();
        } else {
            System.out.println("Plugin not found or not network-enabled: " + pluginName);
        }
    }
    
    private Plugin findPlugin(String pluginName) {
        for (int i = 0; i < pluginCount; i++) {
            if (plugins[i].getPluginName().equals(pluginName)) {
                return plugins[i];
            }
        }
        return null;
    }
    
    public void listPlugins() {
        System.out.println("\n=== Registered Plugins ===");
        for (int i = 0; i < pluginCount; i++) {
            plugins[i].displayPluginInfo();
        }
    }
    
    public void unloadAllPlugins() {
        System.out.println("\n=== Unloading All Plugins ===");
        for (int i = 0; i < pluginCount; i++) {
            if (plugins[i].isEnabled()) {
                plugins[i].unload();
            }
        }
    }
}

public class Q10_PluginSystemDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Plugin System Using Abstract Classes and Interfaces ===");
        
        // Create plugin manager
        PluginManager manager = new PluginManager();
        
        // Create different types of plugins
        LoggingPlugin logger = new LoggingPlugin();
        DataBackupPlugin backup = new DataBackupPlugin();
        SecurityScannerPlugin scanner = new SecurityScannerPlugin();
        
        // Register plugins with manager
        System.out.println("\n1. Registering Plugins:");
        manager.registerPlugin(logger);
        manager.registerPlugin(backup);
        manager.registerPlugin(scanner);
        
        // Display all registered plugins
        manager.listPlugins();
        
        // Load all plugins
        manager.loadAllPlugins();
        
        // Execute all plugins
        manager.executeAllPlugins();
        
        // Demonstrate interface-specific operations
        System.out.println("\n2. Interface-Specific Operations:");
        
        // Configure plugins
        System.out.println("\nConfiguring plugins:");
        if (logger instanceof Configurable) {
            logger.displayConfiguration();
            logger.resetToDefaults();
            logger.saveConfiguration();
        }
        
        if (backup instanceof Configurable) {
            backup.displayConfiguration();
            backup.saveConfiguration();
        }
        
        // Network operations
        System.out.println("\nNetwork operations:");
        if (backup instanceof NetworkEnabled) {
            backup.connectToNetwork();
            backup.checkConnection();
        }
        
        // Scheduling operations
        System.out.println("\nScheduling operations:");
        if (backup instanceof Schedulable) {
            backup.setSchedule("weekly");
            backup.startSchedule();
            System.out.println(backup.getNextRunTime());
        }
        
        if (scanner instanceof Schedulable) {
            scanner.setSchedule("daily");
            scanner.startSchedule();
        }
        
        // 3. Polymorphic operations through manager
        System.out.println("\n3. Manager Operations:");
        manager.connectNetworkPlugin("DataBackup");
        manager.schedulePlugin("SecurityScanner", "twice-daily");
        
        // 4. Execute plugins again to see changes
        System.out.println("\n4. Execute Plugins After Configuration:");
        manager.executeAllPlugins();
        
        // 5. Demonstrate multiple interface implementation
        System.out.println("\n5. Multiple Interface Implementation:");
        System.out.println("DataBackup plugin implements:");
        System.out.println("- Configurable: ✓");
        System.out.println("- Schedulable: ✓");
        System.out.println("- NetworkEnabled: ✓");
        
        System.out.println("\nSecurityScanner plugin implements:");
        System.out.println("- Configurable: ✓");
        System.out.println("- Schedulable: ✓");
        System.out.println("- NetworkEnabled: ✗");
        
        // 6. Interface type checking and operations
        System.out.println("\n6. Dynamic Interface Detection:");
        Plugin[] allPlugins = {logger, backup, scanner};
        
        for (Plugin plugin : allPlugins) {
            System.out.println("\nPlugin: " + plugin.getPluginName());
            
            if (plugin instanceof Configurable) {
                System.out.println("  ✓ Supports configuration");
            }
            if (plugin instanceof Schedulable) {
                System.out.println("  ✓ Supports scheduling");
            }
            if (plugin instanceof NetworkEnabled) {
                System.out.println("  ✓ Supports networking");
            }
        }
        
        // 7. Cleanup and unload
        System.out.println("\n7. System Shutdown:");
        manager.unloadAllPlugins();
        
        System.out.println("\n=== Plugin System Summary ===");
        System.out.println("✓ Abstract classes provide common plugin structure");
        System.out.println("✓ Interfaces enable optional capabilities");
        System.out.println("✓ Multiple interface implementation provides flexibility");
        System.out.println("✓ Polymorphism enables uniform plugin management");
        System.out.println("✓ Plugin architecture supports extensibility");
        System.out.println("✓ Separation of concerns through interface segregation");
    }
}