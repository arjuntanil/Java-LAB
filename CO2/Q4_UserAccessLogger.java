// User Access Logger - Demonstrating Access Modifiers, final keyword
package c02;
class User {
    // Different access modifiers demonstration
    private String password;           // Private - accessible only within this class
    protected String role;             // Protected - accessible within package and subclasses
    public String username;            // Public - accessible from anywhere
    
    // Final static constant
    public final static String SYSTEM_NAME = "AccessLogger";
    
    // Public constructor to initialize all fields
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Public method to show user details (proper way to access private data)
    public void showUserDetails() {
        System.out.println("\n--- User Details (via showUserDetails method) ---");
        System.out.println("System: " + SYSTEM_NAME);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);    // Can access private field within class
        System.out.println("Role: " + role);
    }
    
    // Getter method for password (controlled access to private field)
    public String getPassword() {
        return "***HIDDEN***"; // Don't reveal actual password
    }
    
    // Setter method for password (controlled modification of private field)
    public void setPassword(String newPassword) {
        if (newPassword != null && newPassword.length() >= 6) {
            this.password = newPassword;
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Password must be at least 6 characters long!");
        }
    }
}

public class Q4_UserAccessLogger {
    public static void main(String[] args) {
        System.out.println("=== User Access Logger Program ===");
        System.out.println("Demonstrating Access Modifiers and final keyword\n");
        
        // Create a User object
        User user = new User("john_doe", "secret123", "Administrator");
        
        System.out.println("=== TESTING ACCESS MODIFIERS ===");
        
        // 1. Accessing public field - ALLOWED
        System.out.println("1. Accessing public field (username):");
        System.out.println("   user.username = " + user.username);
        System.out.println("   ✓ SUCCESS: Public fields are accessible from other classes\n");
        
        // 2. Accessing final static constant - ALLOWED
        System.out.println("2. Accessing final static constant:");
        System.out.println("   User.SYSTEM_NAME = " + User.SYSTEM_NAME);
        System.out.println("   ✓ SUCCESS: Final static constants are accessible\n");
        
        // 3. Accessing protected field - ALLOWED (same package)
        System.out.println("3. Accessing protected field (role):");
        System.out.println("   user.role = " + user.role);
        System.out.println("   ✓ SUCCESS: Protected fields are accessible within same package\n");
        
        // 4. Attempting to access private field - WOULD CAUSE COMPILE ERROR
        System.out.println("4. Attempting to access private field (password):");
        System.out.println("   // user.password - This would cause compile-time error!");
        System.out.println("   ✗ BLOCKED: Private fields are not accessible from other classes\n");
        
        // The following line would cause a compile-time error if uncommented:
        // System.out.println("Password: " + user.password);  // Compile error!
        
        // 5. Proper way to access private data - through public methods
        System.out.println("5. Proper access to private data via public methods:");
        System.out.println("   user.getPassword() = " + user.getPassword());
        System.out.println("   ✓ SUCCESS: Private data accessed through public methods\n");
        
        // 6. Modifying fields
        System.out.println("=== TESTING FIELD MODIFICATION ===");
        
        // Modify public field
        user.username = "jane_doe";
        System.out.println("Modified username: " + user.username);
        
        // Modify protected field
        user.role = "User";
        System.out.println("Modified role: " + user.role);
        
        // Modify private field through public method
        user.setPassword("newpassword123");
        
        // Attempt to modify final constant - WOULD CAUSE COMPILE ERROR
        System.out.println("\nAttempting to modify final constant:");
        System.out.println("// User.SYSTEM_NAME = \"NewSystem\"; - This would cause compile error!");
        System.out.println("✗ BLOCKED: Final constants cannot be modified\n");
        
        // The following line would cause a compile-time error if uncommented:
        // User.SYSTEM_NAME = "NewSystem";  // Compile error!
        
        // 7. Display all user details using the proper method
        System.out.println("=== COMPLETE USER DETAILS ===");
        user.showUserDetails();
        
        // 8. Summary of access control
        System.out.println("\n=== ACCESS CONTROL SUMMARY ===");
        System.out.println("✓ public fields/methods    - Accessible from anywhere");
        System.out.println("✓ protected fields/methods - Accessible within package & subclasses");
        System.out.println("✗ private fields/methods   - Accessible only within the same class");
        System.out.println("✓ final static constants   - Accessible but not modifiable");
        System.out.println("\nBest Practice: Use private fields with public getter/setter methods!");
    }
}