import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class BankBalanceGUI extends JFrame implements ActionListener {
private BankAccount account;
private JPanel panel;
private JLabel balanceLabel;
private JButton depositButton;
private JButton withdrawButton;
private JButton exitButton;
public BankBalanceGUI() {
    String input = JOptionPane.showInputDialog(
            null,
            "Enter your starting bank balance:",
            "Starting Balance",
            JOptionPane.QUESTION_MESSAGE
    );

    double startingBalance = 0;

    try {
        startingBalance = Double.parseDouble(input);
        if (startingBalance < 0) {
            startingBalance = 0;
        }
    } catch (Exception e) {
        startingBalance = 0;
    }

    account = new BankAccount(startingBalance);

    setTitle("Bank Balance Application");
    setSize(400, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    panel = new JPanel();
    panel.setLayout(new GridLayout(4, 1, 10, 10));

    balanceLabel = new JLabel("Current Balance: $" + String.format("%.2f", account.getBalance()), SwingConstants.CENTER);

    depositButton = new JButton("Deposit");
    withdrawButton = new JButton("Withdraw");
    exitButton = new JButton("Exit");

    depositButton.addActionListener(this);
    withdrawButton.addActionListener(this);
    exitButton.addActionListener(this);

    panel.add(balanceLabel);
    panel.add(depositButton);
    panel.add(withdrawButton);
    panel.add(exitButton);

    add(panel);
    setVisible(true);
}

private void updateBalanceLabel() {
    balanceLabel.setText("Current Balance: $" + String.format("%.2f", account.getBalance()));
}

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == depositButton) {
        String input = JOptionPane.showInputDialog(
                this,
                "Enter amount to deposit:"
        );

        try {
            double amount = Double.parseDouble(input);
            if (amount > 0) {
                account.deposit(amount);
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Deposit successful.");
            } else {
                JOptionPane.showMessageDialog(this, "Enter a valid deposit amount.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    if (e.getSource() == withdrawButton) {
        String input = JOptionPane.showInputDialog(
                this,
                "Enter amount to withdraw:"
        );

        try {
            double amount = Double.parseDouble(input);
            if (account.withdraw(amount)) {
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Withdrawal successful.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid amount or insufficient funds.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    if (e.getSource() == exitButton) {
        JOptionPane.showMessageDialog(
                this,
                "Final Balance: $" + String.format("%.2f", account.getBalance())
        );
        System.exit(0);
    }
}

public static void main(String[] args) {
    new BankBalanceGUI();
}
}
