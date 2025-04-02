package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Withdrawl extends JFrame implements ActionListener {
    private static String token = null;
    
    JButton back, withdraw;
    JTextField amt;
    String user;
    
    // Constructor now accepts token and user id similar to Deposit class
    public Withdrawl(String token, String uid) {
        Withdrawl.token = token;
        if (!UserDAO.verifyToken(token)) {
            JOptionPane.showMessageDialog(null, "Access Denied: Invalid Token");
            return;
        }
        
        setSize(900, 900);
        setLayout(null);
        setLocationRelativeTo(null);
        user = uid;
        
        // Set up background image (same as Deposit)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel lbl = new JLabel(i3);
        lbl.setBounds(0, 0, 900, 900);
        add(lbl);
        
        // Label for withdrawal amount text
        JLabel txtamt = new JLabel("Enter the amount you want to withdraw:");
        txtamt.setForeground(Color.white);
        txtamt.setBounds(170, 300, 400, 20);
        txtamt.setFont(new Font("System", Font.BOLD, 16));
        lbl.add(txtamt);
        
        // Text field for the withdrawal amount
        amt = new JTextField();
        amt.setFont(new Font("Raleway", Font.BOLD, 16));
        amt.setBounds(170, 350, 320, 20);
        lbl.add(amt);
        
        // Withdraw button
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 485, 150, 27);
        withdraw.addActionListener(this);
        lbl.add(withdraw);
        
        // Back button
        back = new JButton("Back");
        back.setBounds(355, 520, 150, 27);
        back.addActionListener(this);
        lbl.add(back);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == withdraw) {
            String cash = amt.getText();
            if(cash.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the amount you want to withdraw");
            } else {
                int amount = Integer.parseInt(cash);
                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String tarik = today.format(formatter);
                
                try {
                    Conn cnc = new Conn();
                    
                    // Retrieve current balance using the user id
                    String balance_query = "SELECT balance FROM User_Balance WHERE user_id = ?";
                    PreparedStatement balancestmt = cnc.c.prepareStatement(balance_query);
                    balancestmt.setString(1, user);
                    ResultSet rs = balancestmt.executeQuery();
                    
                    if (rs.next()) {
                        int current_balance = rs.getInt("balance");
                        if (current_balance < amount) {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                            return;
                        }
                        
                        int new_balance = current_balance - amount;
                        
                        // Insert a record into User_Statement for the withdrawal
                        String query = "INSERT INTO User_Statement (user_id, updated_balance, date, transaction_type, amount) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement stmt = cnc.c.prepareStatement(query);
                        stmt.setString(1, user);
                        stmt.setInt(2, new_balance);
                        stmt.setString(3, tarik);
                        stmt.setString(4, "Withdrawal");
                        stmt.setInt(5, amount);
                        stmt.executeUpdate();
                        
                        // Update the User_Balance table with the new balance
                        String update_query = "UPDATE User_Balance SET balance = ? WHERE user_id = ?";
                        PreparedStatement update_stmt = cnc.c.prepareStatement(update_query);
                        update_stmt.setInt(1, new_balance);
                        update_stmt.setString(2, user);
                        update_stmt.executeUpdate();
                        System.out.println("Balance updated!");
                        
                        JOptionPane.showMessageDialog(null, "Rs " + cash + " withdrawn successfully");
                        
                        setVisible(false);
                        new UserDashboard(token, user).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found");
                    }
                } catch(SQLException e) {
                    System.out.println(e);
                }
            }
        } else if(ae.getSource() == back) {
            setVisible(false);
            new UserDashboard(token, user).setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        new Withdrawl("", "");
    }
}
