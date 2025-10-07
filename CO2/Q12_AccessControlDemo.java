package c02;
// Program demonstrating Access Control (Visibility Modifiers)

// BankAccount class demonstrating different access modifiers
class BankAccount {
    private double balance;           // Private - accessible only within this class
    protected String accountType;    // Protected - accessible within package and subclasses
    public String accountHolder;     // Public - accessible from anywhere
    
    // Public constructor
    public BankAccount(String accountHolder, String accountType, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = initialBalance;
        System.out.println("Bank account created for: " + accountHolder);
    }
    
    // Public method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    // Public method to withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", balance));
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
            return false;
        }
    }
    
    // Protected method to check balance (accessible within package and subclasses)
    protected double checkBalance() {
        return balance;
    }
    
    // Private method for internal validation (accessible only within this class)
    private boolean validateTransaction(double amount) {
        return amount > 0 && amount <= balance;
    }
    
    // Public method that uses private method internally
    public boolean performSecureWithdrawal(double amount) {
        if (validateTransaction(amount)) { // Using private method
            balance -= amount;
            logTransaction("Withdrawal", amount); // Using private method
            return true;
        }
        return false;
    }
    
    // Private method for logging (accessible only within this class)
    private void logTransaction(String type, double amount) {
        System.out.println("LOG: " + type + " of $" + String.format("%.2f", amount) + 
                          " by " + accountHolder);
    }
    
    // Public method to display account info
    public void displayAccountInfo() {
        System.out.println("\n--- Account Information ---");
        System.out.println("Account Holder: " + accountHolder);    // Public field
        System.out.println("Account Type: " + accountType);        // Protected field
        System.out.println("Balance: $" + String.format("%.2f", balance)); // Private field accessed within class
    }
    
    // Public getter for balance (controlled access to private field)
    public double getBalance() {
        return balance;
    }
}

// Subclass demonstrating protected access
class SavingsAccount extends BankAccount {
    private double interestRate;
    
    public SavingsAccount(String accountHolder, double initialBalance, double interestRate) {
        super(accountHolder, "Savings", initialBalance); // Call parent constructor
        this.interestRate = interestRate;
    }
    
    // Method demonstrating access to protected member from subclass
    public void addInterest() {
        double currentBalance = checkBalance(); // Accessing protected method from parent
        double interest = currentBalance * interestRate / 100;
        deposit(interest); // Public method
        System.out.println("Interest added: $" + String.format("%.2f", interest));
    }
    
    // Method demonstrating access to protected field from subclass
    public void displayAccountType() {
        System.out.println("This is a " + accountType + " account"); // Accessing protected field
    }
}

// Separate class demonstrating access from outside
public class Q12_AccessControlDemo {
    
    // Method to demonstrate access control from external class
    public static void demonstrateAccess(BankAccount account) {
        System.out.println("\n=== Testing Access from External Class ===");
        
        // 1. Accessing public field - ALLOWED
        System.out.println("1. Accessing public field:");
        System.out.println("   Account holder: " + account.accountHolder);
        System.out.println("   ✓ SUCCESS: Public fields are accessible");
        
        // 2. Accessing protected field - ALLOWED (same package)
        System.out.println("\n2. Accessing protected field:");
        System.out.println("   Account type: " + account.accountType);
        System.out.println("   ✓ SUCCESS: Protected fields are accessible within same package");
        
        // 3. Accessing private field - WOULD CAUSE COMPILE ERROR
        System.out.println("\n3. Attempting to access private field:");
        System.out.println("   // account.balance - This would cause compile error!");
        System.out.println("   ✗ BLOCKED: Private fields are not accessible");
        
        // The following line would cause compile error if uncommented:
        // System.out.println("Balance: " + account.balance); // Compile error!
        
        // 4. Using public methods - ALLOWED
        System.out.println("\n4. Using public methods:");
        account.deposit(100);
        System.out.println("   ✓ SUCCESS: Public methods are accessible");
        
        // 5. Using protected method - ALLOWED (same package)
        System.out.println("\n5. Using protected method:");
        double balance = account.checkBalance();
        System.out.println("   Balance via protected method: $" + String.format("%.2f", balance));
        System.out.println("   ✓ SUCCESS: Protected methods are accessible within same package");
        
        // 6. Using private method - WOULD CAUSE COMPILE ERROR
        System.out.println("\n6. Attempting to use private method:");
        System.out.println("   // account.validateTransaction(50) - This would cause compile error!");
        System.out.println("   ✗ BLOCKED: Private methods are not accessible");
        
        // The following line would cause compile error if uncommented:
        // account.validateTransaction(50); // Compile error!
    }
    
    public static void main(String[] args) {
        System.out.println("=== Access Control Demonstration ===");
        
        // Create a bank account
        BankAccount account = new BankAccount("John Doe", "Checking", 1000.0);
        
        // Display initial account info
        account.displayAccountInfo();
        
        // Perform some operations using public methods
        System.out.println("\n=== Public Method Operations ===");
        account.deposit(250);
        account.withdraw(150);
        
        // Demonstrate access control from external method
        demonstrateAccess(account);
        
        // Create a savings account to demonstrate inheritance and protected access
        System.out.println("\n=== Inheritance and Protected Access ===");
        SavingsAccount savings = new SavingsAccount("Jane Smith", 2000.0, 5.0);
        savings.displayAccountInfo();
        savings.displayAccountType(); // Accessing protected field from subclass
        savings.addInterest();        // Accessing protected method from subclass
        
        // Demonstrate proper encapsulation
        System.out.println("\n=== Proper Encapsulation ===");
        System.out.println("Getting balance through public getter: $" + 
                          String.format("%.2f", account.getBalance()));
        
        // Demonstrate secure operations using private methods internally
        System.out.println("\n=== Secure Operations (Using Private Methods Internally) ===");
        boolean success = account.performSecureWithdrawal(100);
        System.out.println("Secure withdrawal successful: " + success);
        
        // Summary of access control
        System.out.println("\n=== Access Control Summary ===");
        System.out.println("✓ public    - Accessible from anywhere");
        System.out.println("✓ protected - Accessible within package and subclasses");
        System.out.println("✗ private   - Accessible only within the same class");
        System.out.println("✓ package   - Default access, accessible within same package");
        
        System.out.println("\n=== Best Practices ===");
        System.out.println("• Make fields private and provide public getters/setters");
        System.out.println("• Use protected for members that subclasses need to access");
        System.out.println("• Keep implementation details private");
        System.out.println("• Design clear public interfaces for your classes");
    }
}