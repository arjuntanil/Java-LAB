package c03;

// Q9: Simulate a Banking System with Protected Data and Final Methods
// Demonstrates protected access, final methods, and inheritance in banking context

// Base Account class with protected data and final methods
abstract class Account {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;
    protected static int accountCounter = 1000;
    protected final String bankName = "SecureBank";
    protected final String branchCode;
    
    // Constructor
    public Account(String accountHolder, String branchCode, double initialBalance) {
        this.accountNumber = generateAccountNumber();
        this.accountHolder = accountHolder;
        this.branchCode = branchCode;
        this.balance = initialBalance;
    }
    
    // Protected method to generate account number
    protected String generateAccountNumber() {
        return "ACC" + (++accountCounter);
    }
    
    // Final method - cannot be overridden (security measure)
    public final String getAccountType() {
        return this.getClass().getSimpleName();
    }
    
    // Final method for security - basic account validation
    protected final boolean validateAccount(String inputAccountNumber) {
        return this.accountNumber.equals(inputAccountNumber);
    }
    
    // Final method to ensure consistent interest calculation across all accounts
    protected final double calculateCompoundInterest(double principal, double rate, int years) {
        return principal * Math.pow(1 + rate/100, years) - principal;
    }
    
    // Abstract methods to be implemented by subclasses
    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);
    public abstract double getMinimumBalance();
    public abstract void calculateInterest();
    
    // Protected method accessible to subclasses
    protected void updateBalance(double amount) {
        balance += amount;
        System.out.println("Balance updated. New balance: $" + String.format("%.2f", balance));
    }
    
    // Final method for displaying basic account info (cannot be overridden)
    public final void displayBasicAccountInfo() {
        System.out.println("\n--- Account Information ---");
        System.out.println("Bank: " + bankName);
        System.out.println("Branch: " + branchCode);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + getAccountType());
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
    }
    
    // Protected method for transaction logging
    protected void logTransaction(String transactionType, double amount, boolean success) {
        System.out.println("TRANSACTION LOG:");
        System.out.println("Type: " + transactionType);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Status: " + (success ? "SUCCESS" : "FAILED"));
        System.out.println("Account: " + accountNumber);
        System.out.println("Time: " + java.time.LocalDateTime.now().toString().substring(0, 19));
    }
    
    // Final getter methods (cannot be overridden for security)
    public final String getAccountNumber() {
        return accountNumber;
    }
    
    public final String getAccountHolder() {
        return accountHolder;
    }
    
    public final double getBalance() {
        return balance;
    }
    
    public final String getBankName() {
        return bankName;
    }
}

// SavingsAccount subclass
class SavingsAccount extends Account {
    private double interestRate;
    private int withdrawalCount;
    private final int maxWithdrawalsPerMonth = 6;
    private final double minimumBalance = 100.0;
    
    public SavingsAccount(String accountHolder, String branchCode, double initialBalance, double interestRate) {
        super(accountHolder, branchCode, initialBalance);
        this.interestRate = interestRate;
        this.withdrawalCount = 0;
    }
    
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            // Using protected method from parent class
            updateBalance(amount);
            
            // Using protected method for logging
            logTransaction("DEPOSIT", amount, true);
            
            System.out.println("Deposit successful to Savings Account");
        } else {
            System.out.println("Invalid deposit amount");
            logTransaction("DEPOSIT", amount, false);
        }
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            logTransaction("WITHDRAWAL", amount, false);
            return false;
        }
        
        if (withdrawalCount >= maxWithdrawalsPerMonth) {
            System.out.println("Maximum withdrawals per month exceeded");
            logTransaction("WITHDRAWAL", amount, false);
            return false;
        }
        
        // Accessing protected field directly
        if (balance - amount < minimumBalance) {
            System.out.println("Withdrawal would violate minimum balance requirement");
            logTransaction("WITHDRAWAL", amount, false);
            return false;
        }
        
        // Using protected method
        updateBalance(-amount);
        withdrawalCount++;
        
        logTransaction("WITHDRAWAL", amount, true);
        System.out.println("Withdrawal successful from Savings Account");
        System.out.println("Remaining withdrawals this month: " + (maxWithdrawalsPerMonth - withdrawalCount));
        
        return true;
    }
    
    @Override
    public double getMinimumBalance() {
        return minimumBalance;
    }
    
    @Override
    public void calculateInterest() {
        // Using final method from parent class
        double interest = calculateCompoundInterest(balance, interestRate, 1);
        System.out.println("Annual interest earned: $" + String.format("%.2f", interest));
        
        // Monthly interest
        double monthlyInterest = balance * (interestRate / 12 / 100);
        updateBalance(monthlyInterest);
        System.out.println("Monthly interest added: $" + String.format("%.2f", monthlyInterest));
    }
    
    // Savings-specific method
    public void displaySavingsInfo() {
        displayBasicAccountInfo(); // Final method from parent
        System.out.println("Interest Rate: " + interestRate + "% per annum");
        System.out.println("Minimum Balance: $" + String.format("%.2f", minimumBalance));
        System.out.println("Withdrawals this month: " + withdrawalCount + "/" + maxWithdrawalsPerMonth);
    }
    
    public void resetWithdrawalCount() {
        withdrawalCount = 0;
        System.out.println("Monthly withdrawal count reset");
    }
}

// CurrentAccount subclass
class CurrentAccount extends Account {
    private double overdraftLimit;
    private final double minimumBalance = 0.0;
    private final double maintenanceFee = 25.0;
    
    public CurrentAccount(String accountHolder, String branchCode, double initialBalance, double overdraftLimit) {
        super(accountHolder, branchCode, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }
    
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            // Accessing protected field and method
            updateBalance(amount);
            logTransaction("DEPOSIT", amount, true);
            System.out.println("Deposit successful to Current Account");
        } else {
            System.out.println("Invalid deposit amount");
            logTransaction("DEPOSIT", amount, false);
        }
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            logTransaction("WITHDRAWAL", amount, false);
            return false;
        }
        
        // Current account allows overdraft
        if (balance - amount < -overdraftLimit) {
            System.out.println("Withdrawal exceeds overdraft limit");
            System.out.println("Available balance including overdraft: $" + 
                             String.format("%.2f", balance + overdraftLimit));
            logTransaction("WITHDRAWAL", amount, false);
            return false;
        }
        
        updateBalance(-amount);
        logTransaction("WITHDRAWAL", amount, true);
        System.out.println("Withdrawal successful from Current Account");
        
        if (balance < 0) {
            System.out.println("Account is now in overdraft: $" + String.format("%.2f", Math.abs(balance)));
        }
        
        return true;
    }
    
    @Override
    public double getMinimumBalance() {
        return minimumBalance;
    }
    
    @Override
    public void calculateInterest() {
        // Current accounts typically don't earn interest, but may charge overdraft fees
        if (balance < 0) {
            double overdraftFee = Math.abs(balance) * 0.02; // 2% monthly overdraft fee
            updateBalance(-overdraftFee);
            System.out.println("Overdraft fee charged: $" + String.format("%.2f", overdraftFee));
        } else {
            System.out.println("No interest calculation for current account with positive balance");
        }
    }
    
    // Current account specific methods
    public void displayCurrentAccountInfo() {
        displayBasicAccountInfo(); // Final method from parent
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
        System.out.println("Available Balance: $" + String.format("%.2f", balance + overdraftLimit));
        System.out.println("Monthly Maintenance Fee: $" + String.format("%.2f", maintenanceFee));
        
        if (balance < 0) {
            System.out.println("⚠️  Account is in OVERDRAFT");
        }
    }
    
    public void chargeMaintenanceFee() {
        updateBalance(-maintenanceFee);
        logTransaction("MAINTENANCE_FEE", maintenanceFee, true);
        System.out.println("Monthly maintenance fee charged");
    }
    
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}

// Fixed Deposit Account (another subclass)
class FixedDepositAccount extends Account {
    private final double interestRate;
    private final int termInMonths;
    private final java.time.LocalDate maturityDate;
    private final double penaltyRate = 2.0; // 2% penalty for early withdrawal
    
    public FixedDepositAccount(String accountHolder, String branchCode, double initialBalance, 
                              double interestRate, int termInMonths) {
        super(accountHolder, branchCode, initialBalance);
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.maturityDate = java.time.LocalDate.now().plusMonths(termInMonths);
    }
    
    @Override
    public void deposit(double amount) {
        System.out.println("Additional deposits not allowed in Fixed Deposit Account");
        logTransaction("DEPOSIT", amount, false);
    }
    
    @Override
    public boolean withdraw(double amount) {
        java.time.LocalDate today = java.time.LocalDate.now();
        
        if (today.isBefore(maturityDate)) {
            System.out.println("Early withdrawal from Fixed Deposit");
            System.out.println("Penalty will be applied: " + penaltyRate + "%");
            
            double penalty = balance * (penaltyRate / 100);
            double availableAmount = balance - penalty;
            
            if (amount > availableAmount) {
                System.out.println("Insufficient funds after penalty");
                logTransaction("EARLY_WITHDRAWAL", amount, false);
                return false;
            }
            
            updateBalance(-penalty);
            updateBalance(-amount);
            
            System.out.println("Penalty charged: $" + String.format("%.2f", penalty));
            logTransaction("EARLY_WITHDRAWAL", amount, true);
            
        } else {
            // Matured FD
            if (amount > balance) {
                System.out.println("Insufficient funds");
                logTransaction("WITHDRAWAL", amount, false);
                return false;
            }
            
            updateBalance(-amount);
            logTransaction("MATURED_WITHDRAWAL", amount, true);
            System.out.println("Withdrawal from matured Fixed Deposit");
        }
        
        return true;
    }
    
    @Override
    public double getMinimumBalance() {
        return balance; // Cannot go below initial deposit
    }
    
    @Override
    public void calculateInterest() {
        // Using final method from parent class
        double maturityAmount = calculateCompoundInterest(balance, interestRate, termInMonths / 12);
        System.out.println("Maturity amount: $" + String.format("%.2f", balance + maturityAmount));
        System.out.println("Interest to be earned: $" + String.format("%.2f", maturityAmount));
    }
    
    public void displayFDInfo() {
        displayBasicAccountInfo(); // Final method from parent
        System.out.println("Interest Rate: " + interestRate + "% per annum");
        System.out.println("Term: " + termInMonths + " months");
        System.out.println("Maturity Date: " + maturityDate);
        
        if (java.time.LocalDate.now().isAfter(maturityDate)) {
            System.out.println("✅ Fixed Deposit has MATURED");
        } else {
            System.out.println("⏳ Days to maturity: " + 
                             java.time.temporal.ChronoUnit.DAYS.between(java.time.LocalDate.now(), maturityDate));
        }
    }
}

public class Q9_BankingSystemProtectedFinal {
    
    // Method demonstrating polymorphism with Account array
    public static void processMonthlyOperations(Account[] accounts) {
        System.out.println("\n=== Monthly Operations Processing ===");
        
        for (Account account : accounts) {
            System.out.println("\nProcessing: " + account.getAccountType());
            account.calculateInterest(); // Polymorphic call
            
            // Type-specific operations
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).resetWithdrawalCount();
            } else if (account instanceof CurrentAccount) {
                ((CurrentAccount) account).chargeMaintenanceFee();
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Banking System with Protected Data and Final Methods ===");
        
        // Create different types of accounts
        SavingsAccount savings = new SavingsAccount("John Doe", "BR001", 1000.0, 4.5);
        CurrentAccount current = new CurrentAccount("Jane Smith", "BR001", 2000.0, 5000.0);
        FixedDepositAccount fd = new FixedDepositAccount("Bob Johnson", "BR002", 10000.0, 7.0, 12);
        
        // 1. Demonstrate final method getAccountType()
        System.out.println("\n1. Final Method Demonstration:");
        System.out.println("Account types (using final method):");
        System.out.println("- " + savings.getAccountType());
        System.out.println("- " + current.getAccountType());
        System.out.println("- " + fd.getAccountType());
        
        // Note: These methods cannot be overridden in subclasses
        
        // 2. Display initial account information
        System.out.println("\n2. Initial Account Information:");
        savings.displaySavingsInfo();
        current.displayCurrentAccountInfo();
        fd.displayFDInfo();
        
        // 3. Perform deposit operations
        System.out.println("\n3. Deposit Operations:");
        savings.deposit(500.0);
        current.deposit(1000.0);
        fd.deposit(500.0); // This will be rejected
        
        // 4. Perform withdrawal operations
        System.out.println("\n4. Withdrawal Operations:");
        savings.withdraw(200.0);
        current.withdraw(6000.0); // This will use overdraft
        fd.withdraw(1000.0); // Early withdrawal with penalty
        
        // 5. Demonstrate protected field access through inheritance
        System.out.println("\n5. Protected Data Access:");
        System.out.println("All subclasses can access protected fields:");
        System.out.println("- Account numbers, balances, account holders");
        System.out.println("- Bank name: " + savings.getBankName()); // Using final getter
        
        // 6. Test minimum balance validation
        System.out.println("\n6. Minimum Balance Testing:");
        System.out.println("Savings minimum balance: $" + savings.getMinimumBalance());
        System.out.println("Current minimum balance: $" + current.getMinimumBalance());
        System.out.println("FD minimum balance: $" + fd.getMinimumBalance());
        
        // Try to violate minimum balance
        savings.withdraw(1500.0); // Should fail due to minimum balance requirement
        
        // 7. Monthly operations (polymorphism)
        Account[] accounts = {savings, current, fd};
        processMonthlyOperations(accounts);
        
        // 8. Display final state
        System.out.println("\n8. Final Account States:");
        savings.displaySavingsInfo();
        current.displayCurrentAccountInfo();
        fd.displayFDInfo();
        
        // 9. Demonstrate final method security
        System.out.println("\n9. Security Features (Final Methods):");
        System.out.println("✓ getAccountType() cannot be overridden");
        System.out.println("✓ Account validation methods are final");
        System.out.println("✓ Interest calculation formula is standardized");
        System.out.println("✓ Basic account info display is consistent");
        
        // The following would cause compilation errors if uncommented:
        /*
        class HackedSavingsAccount extends SavingsAccount {
            @Override
            public final String getAccountType() { // Cannot override final method
                return "Hacked Account";
            }
        }
        */
        
        System.out.println("\n=== Summary ===");
        System.out.println("✓ Protected fields accessible in subclasses within same package");
        System.out.println("✓ Final methods ensure security and consistency");
        System.out.println("✓ Abstract methods provide customization points");
        System.out.println("✓ Inheritance enables code reuse while maintaining control");
        System.out.println("✓ Banking operations demonstrate real-world OOP benefits");
    }
}