import java.util.*;

abstract class Account {
    protected String accountNumber;
    protected String holderName;
    protected int pin;
    protected double balance;
    protected ArrayList<String> history;

    public Account(String accountNumber, String holderName, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;
        history = new ArrayList<>();
        history.add("Account opened with balance: " + balance);
    }

    public boolean login(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void deposit(double amount) {
        balance += amount;
        history.add("Deposited: " + amount + " | Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            history.add("Withdrawn: " + amount + " | Balance: " + balance);
        } else {
            history.add("Failed withdrawal due to insufficient balance");
        }
    }

    public void printHistory() {
        for (String s : history) {
            System.out.println(s);
        }
    }

    public double getBalance() {
        return balance;
    }

    public abstract void applyInterest();
}

class SavingsAccount extends Account {
    private double interestRate = 0.05;

    public SavingsAccount(String accNo, String name, int pin, double balance) {
        super(accNo, name, pin, balance);
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        history.add("Interest added: " + interest + " | Balance: " + balance);
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(String accNo, String name, int pin, double balance) {
        super(accNo, name, pin, balance);
    }

    public void applyInterest() {
        history.add("No interest for Current Account");
    }
}

public class BankSystem {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        accounts.put("S1001", new SavingsAccount("S1001", "Akash", 1234, 5000));
        accounts.put("C2001", new CurrentAccount("C2001", "Rahul", 1111, 7000));

        while (true) {
            System.out.println("\n1.Login  2.Exit");
            int choice = sc.nextInt();
            if (choice == 1) loginProcess();
            else break;
        }
    }

    static void loginProcess() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.next();

        if (!accounts.containsKey(accNo)) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        Account acc = accounts.get(accNo);

        if (!acc.login(pin)) {
            System.out.println("Incorrect PIN");
            return;
        }

        System.out.println("Welcome " + acc.holderName);
        userMenu(acc);
    }

    static void userMenu(Account acc) {
        while (true) {
            System.out.println("\n1.Deposit  2.Withdraw  3.Balance  4.History  5.Apply Interest  6.Logout");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Amount: ");
                acc.deposit(sc.nextDouble());
            } else if (choice == 2) {
                System.out.print("Amount: ");
                acc.withdraw(sc.nextDouble());
            } else if (choice == 3) {
                System.out.println("Balance: " + acc.getBalance());
            } else if (choice == 4) {
                acc.printHistory();
            } else if (choice == 5) {
                acc.applyInterest();
            } else if (choice == 6) {
                System.out.println("Logged Out");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
