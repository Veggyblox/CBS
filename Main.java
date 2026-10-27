import java.util.Scanner;

// Custom Exception for invalid menu choices
class InvalidMenuException extends Exception {
    public InvalidMenuException(String message) {
        super(message);
    }
}

// Custom Exception for insufficient opening balance or withdrawals
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 0.0;
        
        System.out.println("=== Welcome to Codingal Banking Services (CBS) ===");
        
        // Step 1: Taking opening balance with custom exception validation
        try {
            System.out.print("Enter opening balance (Minimum 1000 required): ");
            double openingBalance = scanner.nextDouble();
            
            if (openingBalance < 1000) {
                throw new InsufficientBalanceException("Opening balance cannot be less than 1000!");
            }
            balance = openingBalance;
            System.out.println("Account created successfully with balance: " + balance);
            
        } catch (InsufficientBalanceException e) {
            System.out.println("Exception Caught: " + e.getMessage());
            System.out.println("Defaulting opening balance to minimum required: 1000.");
            balance = 1000.0;
        } catch (Exception e) {
            System.out.println("Invalid input type detected.");
        }

        // Step 2: Banking Menu Loop
        boolean running = true;
        while (running) {
            System.out.println("\n--- CBS Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            try {
                int choice = scanner.nextInt();
                
                // Using throw to catch invalid menu options
                if (choice < 1 || choice > 4) {
                    throw new InvalidMenuException("Selected menu option does not exist! Please choose between 1 and 4.");
                }

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: " + balance);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount <= 0) {
                            throw new InsufficientBalanceException("Deposit amount must be greater than zero.");
                        }
                        balance += depositAmount;
                        System.out.println("Successfully deposited. New Balance: " + balance);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAmount > balance) {
                            throw new InsufficientBalanceException("Withdrawal failed! Insufficient funds.");
                        }
                        balance -= withdrawAmount;
                        System.out.println("Successfully withdrawn. Remaining Balance: " + balance);
                        break;
                    case 4:
                        running = false;
                        System.out.println("Thank you for using Codingal Banking Services!");
                        break;
                }

            } catch (InvalidMenuException e) {
                System.out.println("Menu Error: " + e.getMessage());
            } catch (InsufficientBalanceException e) {
                System.out.println("Transaction Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: Please enter a valid numerical value.");
                scanner.next(); // Clear invalid input buffer
            }
        }
        
        scanner.close();
    }
}
