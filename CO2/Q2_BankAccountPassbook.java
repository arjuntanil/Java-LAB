package c02;

import java.util.Scanner;

// Bank Account Passbook - Demonstrating Passing/Returning Objects, static keyword
class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private double balance;
    private static int accountCount = 0; // Static variable to track total accounts
    
    // Constructor
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        accountCount++; // Increment count for each new account
        this.accountNumber = 1000 + accountCount; // Unique account number
    }
    
    // Getter methods
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public static int getAccountCount() {
        return accountCount;
    }
    
    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    // Method to transfer money (returns Transaction object)
    public Transaction transferTo(BankAccount target, double amount) {
        Transaction transaction;
        
        if (this.withdraw(amount)) {
            target.deposit(amount);
            transaction = new Transaction(this.accountNumber, target.accountNumber, amount, "Success");
        } else {
            transaction = new Transaction(this.accountNumber, target.accountNumber, amount, "Failed");
        }
        
        return transaction;
    }
    
    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + String.format("%.2f", balance));
    }
}

class Transaction {
    private int fromAccount;
    private int toAccount;
    private double amount;
    private String status;
    
    // Constructor
    public Transaction(int fromAccount, int toAccount, double amount, String status) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.status = status;
    }
    
    // Method to display transaction details
    public void displayTransaction() {
        System.out.println("\n--- Transaction Details ---");
        System.out.println("From Account: " + fromAccount);
        System.out.println("To Account: " + toAccount);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Status: " + status);
    }
}

public class Q2_BankAccountPassbook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Bank Account Passbook System ===");
        
        // Create two bank accounts
        System.out.print("Enter first account holder name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter initial balance for " + name1 + ": $");
        double balance1 = scanner.nextDouble();
        BankAccount account1 = new BankAccount(name1, balance1);
        
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter second account holder name: ");
        String name2 = scanner.nextLine();
        System.out.print("Enter initial balance for " + name2 + ": $");
        double balance2 = scanner.nextDouble();
        BankAccount account2 = new BankAccount(name2, balance2);
        
        // Display initial account details
        System.out.println("\n=== INITIAL ACCOUNT DETAILS ===");
        account1.displayAccountDetails();
        account2.displayAccountDetails();
        System.out.println("\nTotal Accounts Created: " + BankAccount.getAccountCount());
        
        // Perform transactions
        System.out.println("\n=== PERFORMING TRANSACTIONS ===");
        
        // Successful transfer
        System.out.println("\nAttempting transfer: $500 from Account " + account1.getAccountNumber() + " to Account " + account2.getAccountNumber());
        Transaction transaction1 = account1.transferTo(account2, 500);
        transaction1.displayTransaction();
        
        // Failed transfer (insufficient funds)
        System.out.println("\nAttempting transfer: $10000 from Account " + account2.getAccountNumber() + " to Account " + account1.getAccountNumber());
        Transaction transaction2 = account2.transferTo(account1, 10000);
        transaction2.displayTransaction();
        
        // Display updated account details
        System.out.println("\n=== UPDATED ACCOUNT DETAILS ===");
        account1.displayAccountDetails();
        account2.displayAccountDetails();
        
        // Additional operations
        System.out.println("\n=== ADDITIONAL OPERATIONS ===");
        account1.deposit(200);
        System.out.println("Deposited $200 to Account " + account1.getAccountNumber());
        
        boolean withdrawResult = account2.withdraw(100);
        System.out.println("Withdrawal of $100 from Account " + account2.getAccountNumber() + ": " + (withdrawResult ? "Success" : "Failed"));
        
        // Final account details
        System.out.println("\n=== FINAL ACCOUNT DETAILS ===");
        account1.displayAccountDetails();
        account2.displayAccountDetails();
        
        scanner.close();
    }
}