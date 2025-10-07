package c03;

// Q12: Advanced Bank Account System
// Comprehensive banking system demonstrating advanced OOP concepts

// Abstract base class for all bank accounts
abstract class BankAccount {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected String accountType;
    protected java.util.Date dateCreated;
    protected boolean isActive;
    protected double interestRate;
    
    // Static variable to track total number of accounts
    protected static int totalAccounts = 0;
    
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.dateCreated = new java.util.Date();
        this.isActive = true;
        totalAccounts++;
    }
    
    // Abstract methods that must be implemented by subclasses
    public abstract void calculateInterest();
    public abstract boolean withdraw(double amount);
    public abstract void displayAccountDetails();
    public abstract double getMinimumBalance();
    
    // Concrete methods available to all account types
    public final void deposit(double amount) {
        if (amount > 0 && isActive) {
            balance += amount;
            System.out.println("Deposited $" + String.format("%.2f", amount) + 
                             " to account " + accountNumber);
            System.out.println("New balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("Invalid deposit amount or account is inactive");
        }
    }
    
    public final double getBalance() {
        return balance;
    }
    
    public final void closeAccount() {
        isActive = false;
        System.out.println("Account " + accountNumber + " has been closed");
    }
    
    public final void displayBasicInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        System.out.println("Status: " + (isActive ? "Active" : "Closed"));
        System.out.println("Date Created: " + dateCreated);
    }
    
    // Static method
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public boolean isActive() { return isActive; }
}

// Interface for accounts that can earn interest
interface InterestEarning {
    void compoundInterest();
    void setInterestRate(double rate);
    double getInterestRate();
    
    default void displayInterestInfo() {
        System.out.println("Interest Rate: " + getInterestRate() + "%");
    }
    
    // Static method for interest calculation
    static double calculateCompoundInterest(double principal, double rate, int time, int frequency) {
        return principal * Math.pow((1 + rate / frequency), frequency * time);
    }
}

// Interface for accounts with overdraft protection
interface OverdraftProtection {
    void setOverdraftLimit(double limit);
    double getOverdraftLimit();
    double getAvailableCredit();
    boolean isOverdrawn();
    
    default void displayOverdraftInfo() {
        System.out.println("Overdraft Limit: $" + String.format("%.2f", getOverdraftLimit()));
        System.out.println("Available Credit: $" + String.format("%.2f", getAvailableCredit()));
        System.out.println("Overdrawn: " + (isOverdrawn() ? "Yes" : "No"));
    }
}

// Interface for premium account features
interface PremiumFeatures {
    void enablePremiumFeatures();
    void disablePremiumFeatures();
    boolean hasPremiumFeatures();
    void displayPremiumBenefits();
    
    default double getPremiumFee() {
        return 25.0; // Default monthly premium fee
    }
}

// Interface for transaction tracking
interface TransactionTracking {
    void addTransaction(String transactionType, double amount, String description);
    void displayTransactionHistory();
    void generateMonthlyStatement();
    
    default String formatTransaction(String type, double amount, String description) {
        return String.format("[%s] %s: $%.2f - %s", 
                           new java.util.Date().toString(), type, amount, description);
    }
}

// Savings Account class
class SavingsAccount extends BankAccount implements InterestEarning, TransactionTracking {
    private final double MINIMUM_BALANCE = 100.0;
    private final int MAX_WITHDRAWALS_PER_MONTH = 6;
    private int withdrawalsThisMonth;
    private String[] transactionHistory;
    private int transactionCount;
    private static final int MAX_TRANSACTIONS = 100;
    
    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
        this.accountType = "Savings";
        this.interestRate = 2.5; // 2.5% annual interest
        this.withdrawalsThisMonth = 0;
        this.transactionHistory = new String[MAX_TRANSACTIONS];
        this.transactionCount = 0;
        addTransaction("ACCOUNT_OPENING", initialBalance, "Initial deposit");
    }
    
    @Override
    public void calculateInterest() {
        if (isActive && balance >= MINIMUM_BALANCE) {
            double monthlyInterest = (balance * interestRate / 100) / 12;
            balance += monthlyInterest;
            addTransaction("INTEREST", monthlyInterest, "Monthly interest credit");
            System.out.println("Interest calculated and credited: $" + 
                             String.format("%.2f", monthlyInterest));
        }
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (!isActive) {
            System.out.println("Account is not active");
            return false;
        }
        
        if (withdrawalsThisMonth >= MAX_WITHDRAWALS_PER_MONTH) {
            System.out.println("Monthly withdrawal limit exceeded");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            return false;
        }
        
        if (balance - amount < MINIMUM_BALANCE) {
            System.out.println("Insufficient funds. Minimum balance required: $" + 
                             String.format("%.2f", MINIMUM_BALANCE));
            return false;
        }
        
        balance -= amount;
        withdrawalsThisMonth++;
        addTransaction("WITHDRAWAL", amount, "ATM/Branch withdrawal");
        System.out.println("Withdrew $" + String.format("%.2f", amount) + 
                         " from savings account " + accountNumber);
        System.out.println("New balance: $" + String.format("%.2f", balance));
        System.out.println("Remaining withdrawals this month: " + 
                         (MAX_WITHDRAWALS_PER_MONTH - withdrawalsThisMonth));
        return true;
    }
    
    @Override
    public void displayAccountDetails() {
        System.out.println("\n=== Savings Account Details ===");
        displayBasicInfo();
        System.out.println("Minimum Balance: $" + String.format("%.2f", MINIMUM_BALANCE));
        System.out.println("Interest Rate: " + interestRate + "% per annum");
        System.out.println("Withdrawals this month: " + withdrawalsThisMonth + "/" + 
                         MAX_WITHDRAWALS_PER_MONTH);
        displayInterestInfo();
    }
    
    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    // InterestEarning implementation
    @Override
    public void compoundInterest() {
        calculateInterest();
    }
    
    @Override
    public void setInterestRate(double rate) {
        if (rate >= 0 && rate <= 10) {
            this.interestRate = rate;
            System.out.println("Interest rate updated to " + rate + "%");
        } else {
            System.out.println("Invalid interest rate");
        }
    }
    
    @Override
    public double getInterestRate() {
        return interestRate;
    }
    
    // TransactionTracking implementation
    @Override
    public void addTransaction(String transactionType, double amount, String description) {
        if (transactionCount < MAX_TRANSACTIONS) {
            transactionHistory[transactionCount] = formatTransaction(transactionType, amount, description);
            transactionCount++;
        }
    }
    
    @Override
    public void displayTransactionHistory() {
        System.out.println("\n=== Transaction History for Account " + accountNumber + " ===");
        for (int i = Math.max(0, transactionCount - 10); i < transactionCount; i++) {
            System.out.println(transactionHistory[i]);
        }
        if (transactionCount > 10) {
            System.out.println("... showing last 10 transactions");
        }
    }
    
    @Override
    public void generateMonthlyStatement() {
        System.out.println("\n=== Monthly Statement - Savings Account ===");
        displayBasicInfo();
        System.out.println("Withdrawals this month: " + withdrawalsThisMonth);
        displayTransactionHistory();
    }
    
    public void resetMonthlyLimits() {
        withdrawalsThisMonth = 0;
        System.out.println("Monthly withdrawal limit reset for account " + accountNumber);
    }
}

// Checking Account class
class CheckingAccount extends BankAccount implements OverdraftProtection, TransactionTracking, PremiumFeatures {
    private final double MINIMUM_BALANCE = 25.0;
    private double overdraftLimit;
    private double monthlyFee;
    private boolean hasPremium;
    private String[] transactionHistory;
    private int transactionCount;
    private static final int MAX_TRANSACTIONS = 200;
    
    public CheckingAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
        this.accountType = "Checking";
        this.overdraftLimit = 500.0;
        this.monthlyFee = 12.0;
        this.hasPremium = false;
        this.transactionHistory = new String[MAX_TRANSACTIONS];
        this.transactionCount = 0;
        addTransaction("ACCOUNT_OPENING", initialBalance, "Initial deposit");
    }
    
    @Override
    public void calculateInterest() {
        // Checking accounts typically don't earn interest
        System.out.println("Checking accounts do not earn interest");
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (!isActive) {
            System.out.println("Account is not active");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            return false;
        }
        
        double availableBalance = balance + overdraftLimit;
        
        if (amount > availableBalance) {
            System.out.println("Insufficient funds including overdraft protection");
            return false;
        }
        
        balance -= amount;
        
        if (balance < 0) {
            double overdraftFee = 35.0;
            if (!hasPremium) {
                balance -= overdraftFee;
                addTransaction("OVERDRAFT_FEE", overdraftFee, "Overdraft fee charged");
                System.out.println("Overdraft fee charged: $" + String.format("%.2f", overdraftFee));
            }
        }
        
        addTransaction("WITHDRAWAL", amount, "Debit/Check withdrawal");
        System.out.println("Withdrew $" + String.format("%.2f", amount) + 
                         " from checking account " + accountNumber);
        System.out.println("New balance: $" + String.format("%.2f", balance));
        
        if (isOverdrawn()) {
            System.out.println("⚠️ Account is overdrawn");
            displayOverdraftInfo();
        }
        
        return true;
    }
    
    @Override
    public void displayAccountDetails() {
        System.out.println("\n=== Checking Account Details ===");
        displayBasicInfo();
        System.out.println("Monthly Fee: $" + String.format("%.2f", monthlyFee));
        System.out.println("Premium Features: " + (hasPremium ? "Enabled" : "Disabled"));
        displayOverdraftInfo();
        if (hasPremium) {
            displayPremiumBenefits();
        }
    }
    
    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    // OverdraftProtection implementation
    @Override
    public void setOverdraftLimit(double limit) {
        if (limit >= 0 && limit <= 2000) {
            this.overdraftLimit = limit;
            System.out.println("Overdraft limit set to $" + String.format("%.2f", limit));
        } else {
            System.out.println("Invalid overdraft limit");
        }
    }
    
    @Override
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    @Override
    public double getAvailableCredit() {
        return balance < 0 ? overdraftLimit + balance : overdraftLimit;
    }
    
    @Override
    public boolean isOverdrawn() {
        return balance < 0;
    }
    
    // PremiumFeatures implementation
    @Override
    public void enablePremiumFeatures() {
        hasPremium = true;
        monthlyFee += getPremiumFee();
        overdraftLimit += 500; // Increased overdraft limit
        System.out.println("Premium features enabled");
        displayPremiumBenefits();
    }
    
    @Override
    public void disablePremiumFeatures() {
        hasPremium = false;
        monthlyFee -= getPremiumFee();
        overdraftLimit -= 500;
        System.out.println("Premium features disabled");
    }
    
    @Override
    public boolean hasPremiumFeatures() {
        return hasPremium;
    }
    
    @Override
    public void displayPremiumBenefits() {
        System.out.println("Premium Benefits:");
        System.out.println("  ✓ No overdraft fees");
        System.out.println("  ✓ Increased overdraft limit");
        System.out.println("  ✓ Free wire transfers");
        System.out.println("  ✓ 24/7 premium customer support");
    }
    
    // TransactionTracking implementation
    @Override
    public void addTransaction(String transactionType, double amount, String description) {
        if (transactionCount < MAX_TRANSACTIONS) {
            transactionHistory[transactionCount] = formatTransaction(transactionType, amount, description);
            transactionCount++;
        }
    }
    
    @Override
    public void displayTransactionHistory() {
        System.out.println("\n=== Transaction History for Account " + accountNumber + " ===");
        for (int i = Math.max(0, transactionCount - 15); i < transactionCount; i++) {
            System.out.println(transactionHistory[i]);
        }
        if (transactionCount > 15) {
            System.out.println("... showing last 15 transactions");
        }
    }
    
    @Override
    public void generateMonthlyStatement() {
        System.out.println("\n=== Monthly Statement - Checking Account ===");
        displayBasicInfo();
        System.out.println("Monthly Fee: $" + String.format("%.2f", monthlyFee));
        displayTransactionHistory();
    }
    
    public void chargeMonthlyFee() {
        if (isActive) {
            balance -= monthlyFee;
            addTransaction("MONTHLY_FEE", monthlyFee, "Monthly maintenance fee");
            System.out.println("Monthly fee charged: $" + String.format("%.2f", monthlyFee));
        }
    }
}

// Certificate of Deposit (CD) Account
class CertificateOfDeposit extends BankAccount implements InterestEarning {
    private final double MINIMUM_BALANCE = 1000.0;
    private int termInMonths;
    private java.util.Date maturityDate;
    private boolean isMatured;
    private double penaltyRate;
    
    public CertificateOfDeposit(String accountNumber, String accountHolderName, 
                               double initialBalance, int termInMonths) {
        super(accountNumber, accountHolderName, initialBalance);
        this.accountType = "Certificate of Deposit";
        this.termInMonths = termInMonths;
        this.isMatured = false;
        this.penaltyRate = 0.5; // 0.5% penalty for early withdrawal
        
        // Set interest rate based on term
        if (termInMonths <= 6) {
            this.interestRate = 3.0;
        } else if (termInMonths <= 12) {
            this.interestRate = 3.5;
        } else if (termInMonths <= 24) {
            this.interestRate = 4.0;
        } else {
            this.interestRate = 4.5;
        }
        
        // Calculate maturity date
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(dateCreated);
        cal.add(java.util.Calendar.MONTH, termInMonths);
        this.maturityDate = cal.getTime();
    }
    
    @Override
    public void calculateInterest() {
        if (isActive) {
            double monthlyInterest = (balance * interestRate / 100) / 12;
            balance += monthlyInterest;
            System.out.println("Interest calculated and credited: $" + 
                             String.format("%.2f", monthlyInterest));
        }
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (!isActive) {
            System.out.println("Account is not active");
            return false;
        }
        
        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid withdrawal amount");
            return false;
        }
        
        if (!isMatured) {
            System.out.println("⚠️ Early withdrawal penalty applies!");
            double penalty = balance * penaltyRate / 100;
            balance -= penalty;
            System.out.println("Early withdrawal penalty: $" + String.format("%.2f", penalty));
        }
        
        balance -= amount;
        System.out.println("Withdrew $" + String.format("%.2f", amount) + 
                         " from CD account " + accountNumber);
        System.out.println("New balance: $" + String.format("%.2f", balance));
        
        return true;
    }
    
    @Override
    public void displayAccountDetails() {
        System.out.println("\n=== Certificate of Deposit Details ===");
        displayBasicInfo();
        System.out.println("Minimum Balance: $" + String.format("%.2f", MINIMUM_BALANCE));
        System.out.println("Term: " + termInMonths + " months");
        System.out.println("Interest Rate: " + interestRate + "% per annum");
        System.out.println("Maturity Date: " + maturityDate);
        System.out.println("Matured: " + (isMatured ? "Yes" : "No"));
        System.out.println("Early Withdrawal Penalty: " + penaltyRate + "%");
    }
    
    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    // InterestEarning implementation
    @Override
    public void compoundInterest() {
        calculateInterest();
    }
    
    @Override
    public void setInterestRate(double rate) {
        System.out.println("Interest rate for CDs cannot be changed after opening");
    }
    
    @Override
    public double getInterestRate() {
        return interestRate;
    }
    
    public void checkMaturity() {
        java.util.Date now = new java.util.Date();
        if (now.after(maturityDate) && !isMatured) {
            isMatured = true;
            System.out.println("🎉 CD account " + accountNumber + " has matured!");
            System.out.println("You can now withdraw without penalty");
        }
    }
    
    public boolean isMatured() {
        return isMatured;
    }
    
    public java.util.Date getMaturityDate() {
        return maturityDate;
    }
}

// Money Market Account
class MoneyMarketAccount extends BankAccount implements InterestEarning, OverdraftProtection, TransactionTracking {
    private final double MINIMUM_BALANCE = 2500.0;
    private final int MAX_TRANSACTIONS_PER_MONTH = 10;
    private int transactionsThisMonth;
    private double overdraftLimit;
    private String[] transactionHistory;
    private int transactionCount;
    private static final int MAX_TRANSACTION_RECORDS = 150;
    
    public MoneyMarketAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
        this.accountType = "Money Market";
        this.interestRate = 3.2; // Higher interest rate
        this.transactionsThisMonth = 0;
        this.overdraftLimit = 1000.0;
        this.transactionHistory = new String[MAX_TRANSACTION_RECORDS];
        this.transactionCount = 0;
        addTransaction("ACCOUNT_OPENING", initialBalance, "Initial deposit");
    }
    
    @Override
    public void calculateInterest() {
        if (isActive && balance >= MINIMUM_BALANCE) {
            double monthlyInterest = (balance * interestRate / 100) / 12;
            balance += monthlyInterest;
            addTransaction("INTEREST", monthlyInterest, "Monthly interest credit");
            System.out.println("Interest calculated and credited: $" + 
                             String.format("%.2f", monthlyInterest));
        } else if (balance < MINIMUM_BALANCE) {
            System.out.println("No interest earned - balance below minimum");
        }
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (!isActive) {
            System.out.println("Account is not active");
            return false;
        }
        
        if (transactionsThisMonth >= MAX_TRANSACTIONS_PER_MONTH) {
            System.out.println("Monthly transaction limit exceeded");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            return false;
        }
        
        double availableBalance = balance + overdraftLimit;
        
        if (amount > availableBalance) {
            System.out.println("Insufficient funds including overdraft protection");
            return false;
        }
        
        balance -= amount;
        transactionsThisMonth++;
        
        if (balance < 0) {
            double overdraftFee = 25.0;
            balance -= overdraftFee;
            addTransaction("OVERDRAFT_FEE", overdraftFee, "Overdraft fee charged");
            System.out.println("Overdraft fee charged: $" + String.format("%.2f", overdraftFee));
        }
        
        addTransaction("WITHDRAWAL", amount, "Money market withdrawal");
        System.out.println("Withdrew $" + String.format("%.2f", amount) + 
                         " from money market account " + accountNumber);
        System.out.println("New balance: $" + String.format("%.2f", balance));
        System.out.println("Remaining transactions this month: " + 
                         (MAX_TRANSACTIONS_PER_MONTH - transactionsThisMonth));
        
        return true;
    }
    
    @Override
    public void displayAccountDetails() {
        System.out.println("\n=== Money Market Account Details ===");
        displayBasicInfo();
        System.out.println("Minimum Balance: $" + String.format("%.2f", MINIMUM_BALANCE));
        System.out.println("Interest Rate: " + interestRate + "% per annum");
        System.out.println("Transactions this month: " + transactionsThisMonth + "/" + 
                         MAX_TRANSACTIONS_PER_MONTH);
        displayOverdraftInfo();
        displayInterestInfo();
    }
    
    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    // InterestEarning implementation
    @Override
    public void compoundInterest() {
        calculateInterest();
    }
    
    @Override
    public void setInterestRate(double rate) {
        if (rate >= 0 && rate <= 8) {
            this.interestRate = rate;
            System.out.println("Interest rate updated to " + rate + "%");
        } else {
            System.out.println("Invalid interest rate");
        }
    }
    
    @Override
    public double getInterestRate() {
        return interestRate;
    }
    
    // OverdraftProtection implementation
    @Override
    public void setOverdraftLimit(double limit) {
        if (limit >= 0 && limit <= 5000) {
            this.overdraftLimit = limit;
            System.out.println("Overdraft limit set to $" + String.format("%.2f", limit));
        } else {
            System.out.println("Invalid overdraft limit");
        }
    }
    
    @Override
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    @Override
    public double getAvailableCredit() {
        return balance < 0 ? overdraftLimit + balance : overdraftLimit;
    }
    
    @Override
    public boolean isOverdrawn() {
        return balance < 0;
    }
    
    // TransactionTracking implementation
    @Override
    public void addTransaction(String transactionType, double amount, String description) {
        if (transactionCount < MAX_TRANSACTION_RECORDS) {
            transactionHistory[transactionCount] = formatTransaction(transactionType, amount, description);
            transactionCount++;
        }
    }
    
    @Override
    public void displayTransactionHistory() {
        System.out.println("\n=== Transaction History for Account " + accountNumber + " ===");
        for (int i = Math.max(0, transactionCount - 12); i < transactionCount; i++) {
            System.out.println(transactionHistory[i]);
        }
        if (transactionCount > 12) {
            System.out.println("... showing last 12 transactions");
        }
    }
    
    @Override
    public void generateMonthlyStatement() {
        System.out.println("\n=== Monthly Statement - Money Market Account ===");
        displayBasicInfo();
        System.out.println("Transactions this month: " + transactionsThisMonth);
        displayTransactionHistory();
    }
    
    public void resetMonthlyLimits() {
        transactionsThisMonth = 0;
        System.out.println("Monthly transaction limit reset for account " + accountNumber);
    }
}

// Bank Management System
class BankManagementSystem {
    private BankAccount[] accounts;
    private int accountCount;
    private static final int MAX_ACCOUNTS = 1000;
    
    public BankManagementSystem() {
        accounts = new BankAccount[MAX_ACCOUNTS];
        accountCount = 0;
    }
    
    public void addAccount(BankAccount account) {
        if (accountCount < MAX_ACCOUNTS) {
            accounts[accountCount++] = account;
            System.out.println("Account added to bank system: " + account.getAccountNumber());
        } else {
            System.out.println("Maximum account limit reached");
        }
    }
    
    public BankAccount findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }
    
    public void displayAllAccounts() {
        System.out.println("\n=== All Bank Accounts ===");
        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccountDetails();
        }
    }
    
    public void calculateAllInterests() {
        System.out.println("\n=== Calculating Interest for All Eligible Accounts ===");
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] instanceof InterestEarning) {
                System.out.println("Calculating interest for account: " + 
                                 accounts[i].getAccountNumber());
                ((InterestEarning) accounts[i]).compoundInterest();
            }
        }
    }
    
    public void generateBankReport() {
        System.out.println("\n=== Bank Management Report ===");
        System.out.println("Total Accounts: " + accountCount);
        System.out.println("System Total Accounts Ever Created: " + BankAccount.getTotalAccounts());
        
        double totalBalance = 0;
        int savingsCount = 0, checkingCount = 0, cdCount = 0, mmCount = 0;
        
        for (int i = 0; i < accountCount; i++) {
            totalBalance += accounts[i].getBalance();
            String accountType = accounts[i].accountType;
            
            switch (accountType) {
                case "Savings": savingsCount++; break;
                case "Checking": checkingCount++; break;
                case "Certificate of Deposit": cdCount++; break;
                case "Money Market": mmCount++; break;
            }
        }
        
        System.out.println("Total Bank Balance: $" + String.format("%.2f", totalBalance));
        System.out.println("Savings Accounts: " + savingsCount);
        System.out.println("Checking Accounts: " + checkingCount);
        System.out.println("Certificate of Deposits: " + cdCount);
        System.out.println("Money Market Accounts: " + mmCount);
    }
    
    public void processMonthlyOperations() {
        System.out.println("\n=== Processing Monthly Operations ===");
        
        for (int i = 0; i < accountCount; i++) {
            BankAccount account = accounts[i];
            
            // Calculate interest for eligible accounts
            if (account instanceof InterestEarning) {
                ((InterestEarning) account).compoundInterest();
            }
            
            // Charge monthly fees for checking accounts
            if (account instanceof CheckingAccount) {
                ((CheckingAccount) account).chargeMonthlyFee();
            }
            
            // Reset monthly limits
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).resetMonthlyLimits();
            }
            
            if (account instanceof MoneyMarketAccount) {
                ((MoneyMarketAccount) account).resetMonthlyLimits();
            }
            
            // Check CD maturity
            if (account instanceof CertificateOfDeposit) {
                ((CertificateOfDeposit) account).checkMaturity();
            }
        }
        
        System.out.println("Monthly operations completed");
    }
}

public class Q12_BankAccountSystem {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Bank Account System ===");
        
        // Create bank management system
        BankManagementSystem bank = new BankManagementSystem();
        
        // 1. Create different types of accounts
        System.out.println("\n1. Creating Different Account Types:");
        
        SavingsAccount savings1 = new SavingsAccount("SAV001", "Alice Johnson", 1500.0);
        SavingsAccount savings2 = new SavingsAccount("SAV002", "Bob Smith", 3000.0);
        
        CheckingAccount checking1 = new CheckingAccount("CHK001", "Carol Davis", 800.0);
        CheckingAccount checking2 = new CheckingAccount("CHK002", "David Wilson", 250.0);
        
        CertificateOfDeposit cd1 = new CertificateOfDeposit("CD001", "Eva Brown", 5000.0, 12);
        CertificateOfDeposit cd2 = new CertificateOfDeposit("CD002", "Frank Miller", 10000.0, 24);
        
        MoneyMarketAccount mm1 = new MoneyMarketAccount("MM001", "Grace Lee", 7500.0);
        MoneyMarketAccount mm2 = new MoneyMarketAccount("MM002", "Henry Clark", 4000.0);
        
        // Add accounts to bank system
        bank.addAccount(savings1);
        bank.addAccount(savings2);
        bank.addAccount(checking1);
        bank.addAccount(checking2);
        bank.addAccount(cd1);
        bank.addAccount(cd2);
        bank.addAccount(mm1);
        bank.addAccount(mm2);
        
        // 2. Display all account details
        System.out.println("\n2. Account Details:");
        bank.displayAllAccounts();
        
        // 3. Demonstrate polymorphism
        System.out.println("\n3. Polymorphic Operations:");
        BankAccount[] accounts = {savings1, checking1, cd1, mm1};
        
        for (BankAccount account : accounts) {
            System.out.println("\nAccount: " + account.getAccountNumber());
            account.deposit(100.0);
            account.calculateInterest();
            System.out.println("Balance after operations: $" + 
                             String.format("%.2f", account.getBalance()));
        }
        
        // 4. Interface-specific operations
        System.out.println("\n4. Interface-Specific Operations:");
        
        // InterestEarning operations
        System.out.println("\nInterest Earning Accounts:");
        InterestEarning[] interestAccounts = {savings1, cd1, mm1};
        for (InterestEarning account : interestAccounts) {
            account.displayInterestInfo();
            account.compoundInterest();
        }
        
        // OverdraftProtection operations
        System.out.println("\nOverdraft Protection Accounts:");
        checking1.displayOverdraftInfo();
        mm1.displayOverdraftInfo();
        
        // PremiumFeatures operations
        System.out.println("\nPremium Features Demo:");
        checking1.enablePremiumFeatures();
        checking1.displayAccountDetails();
        
        // 5. Transaction operations
        System.out.println("\n5. Transaction Operations:");
        
        // Savings account transactions
        savings1.withdraw(200.0);
        savings1.withdraw(300.0);
        savings1.deposit(150.0);
        savings1.displayTransactionHistory();
        
        // Checking account transactions
        checking1.withdraw(900.0); // This will trigger overdraft
        checking1.deposit(500.0);
        checking1.displayTransactionHistory();
        
        // Money market transactions
        mm1.withdraw(1000.0);
        mm1.withdraw(500.0);
        mm1.displayTransactionHistory();
        
        // 6. Advanced account features
        System.out.println("\n6. Advanced Account Features:");
        
        // CD maturity check
        cd1.checkMaturity();
        cd1.withdraw(1000.0); // Early withdrawal with penalty
        
        // Set overdraft limits
        checking2.setOverdraftLimit(750.0);
        mm2.setOverdraftLimit(1500.0);
        
        // Interest rate operations
        savings2.setInterestRate(3.0);
        mm2.setInterestRate(3.5);
        
        // 7. Monthly operations
        System.out.println("\n7. Monthly Operations:");
        bank.processMonthlyOperations();
        
        // 8. Generate statements
        System.out.println("\n8. Account Statements:");
        savings1.generateMonthlyStatement();
        checking1.generateMonthlyStatement();
        mm1.generateMonthlyStatement();
        
        // 9. Interface type checking
        System.out.println("\n9. Dynamic Interface Detection:");
        BankAccount[] allAccounts = {savings1, checking1, cd1, mm1};
        
        for (BankAccount account : allAccounts) {
            System.out.println("\nAccount " + account.getAccountNumber() + 
                             " (" + account.accountType + "):");
            
            if (account instanceof InterestEarning) {
                System.out.println("  ✓ Earns interest");
            }
            if (account instanceof OverdraftProtection) {
                System.out.println("  ✓ Has overdraft protection");
            }
            if (account instanceof PremiumFeatures) {
                System.out.println("  ✓ Supports premium features");
            }
            if (account instanceof TransactionTracking) {
                System.out.println("  ✓ Tracks transactions");
            }
        }
        
        // 10. Bank management report
        System.out.println("\n10. Bank Management Report:");
        bank.generateBankReport();
        
        // 11. Account closure demonstration
        System.out.println("\n11. Account Management:");
        System.out.println("Closing account: " + cd2.getAccountNumber());
        cd2.closeAccount();
        
        // Final system summary
        System.out.println("\n=== Bank Account System Summary ===");
        System.out.println("✓ Abstract classes provide common account structure");
        System.out.println("✓ Multiple interfaces enable specialized capabilities");
        System.out.println("✓ Polymorphism enables uniform account management");
        System.out.println("✓ Inheritance supports account type specialization");
        System.out.println("✓ Encapsulation protects account data");
        System.out.println("✓ Final methods ensure transaction security");
        System.out.println("✓ Static methods provide utility functions");
        System.out.println("✓ Interface default methods reduce code duplication");
        System.out.println("✓ System demonstrates comprehensive banking operations");
    }
}