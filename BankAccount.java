public class BankAccount {
private double balance;
public BankAccount(double balance) {
    if (balance < 0) {
        this.balance = 0;
    } else {
        this.balance = balance;
    }
}

public double getBalance() {
    return balance;
}

public void deposit(double amount) {
    if (amount > 0) {
        balance += amount;
    }
}

public boolean withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
        balance -= amount;
        return true;
    }
    return false;
}
}