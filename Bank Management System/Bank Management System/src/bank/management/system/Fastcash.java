package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fastcash extends JFrame implements ActionListener {
    JButton hundred, hundred5, thousand, thousand2, thousand5, thousand10, back;
    private static String token = null;
    String user; // user id

    // Constructor accepting token and user id
    public Fastcash(String token, String uid) {
        Fastcash.token = token;
        if (!UserDAO.verifyToken(token)) {
            JOptionPane.showMessageDialog(null, "Access Denied: Invalid Token");
            return;
        }
        user = uid;
        
        setSize(900, 900);
        setTitle("Fast Cash");
        setLayout(null);
        setLocationRelativeTo(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel txt = new JLabel("SELECT WITHDRAWAL AMOUNT");
        txt.setBounds(225, 300, 700, 35);
        txt.setForeground(Color.white);
        txt.setFont(new Font("Raleway", Font.BOLD, 16));
        image.add(txt);
        
        hundred = new JButton("RS 100");
        hundred.setFont(new Font("Arial", Font.BOLD, 13));
        hundred.setBounds(170, 415, 150, 30);
        hundred.addActionListener(this);
        image.add(hundred);
        
        hundred5 = new JButton("RS 500");
        hundred5.setFont(new Font("Arial", Font.BOLD, 13));
        hundred5.setBounds(350, 415, 150, 30);
        hundred5.addActionListener(this);
        image.add(hundred5);
        
        thousand = new JButton("RS 1000");
        thousand.setFont(new Font("Arial", Font.BOLD, 13));
        thousand.setBounds(170, 450, 150, 30);
        thousand.addActionListener(this);
        image.add(thousand);
        
        thousand2 = new JButton("RS 2000");
        thousand2.setFont(new Font("Arial", Font.BOLD, 13));
        thousand2.setBounds(350, 450, 150, 30);
        thousand2.addActionListener(this);
        image.add(thousand2);
        
        thousand5 = new JButton("RS 5000");
        thousand5.setFont(new Font("Arial", Font.BOLD, 13));
        thousand5.setBounds(170, 485, 150, 30);
        thousand5.addActionListener(this);
        image.add(thousand5);
        
        thousand10 = new JButton("RS 10000");
        thousand10.setFont(new Font("Arial", Font.BOLD, 13));
        thousand10.setBounds(350, 485, 150, 30);
        thousand10.addActionListener(this);
        image.add(thousand10);
        
        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 13));
        back.setBounds(350, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new UserDashboard(token, user).setVisible(true);
        } else {
            // Extract withdrawal amount from button text, removing "RS " prefix
            String buttonText = ((JButton) ae.getSource()).getText();
            String amountStr = buttonText.substring(3).trim();
            int withdrawAmount = Integer.parseInt(amountStr);

            // Get today's date formatted as "yyyy-MM-dd"
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tarik = today.format(formatter);
            
            try {
                Conn cnc = new Conn();
                
                // Retrieve current balance from User_Balance using user id
                String balance_query = "SELECT balance FROM User_Balance WHERE user_id = ?";
                PreparedStatement balancestmt = cnc.c.prepareStatement(balance_query);
                balancestmt.setString(1, user);
                ResultSet rs = balancestmt.executeQuery();
                
                if (rs.next()) {
                    int current_balance = rs.getInt("balance");
                    if (current_balance < withdrawAmount) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    int new_balance = current_balance - withdrawAmount;
                    
                    // Insert a record into User_Statement for this withdrawal transaction
                    String stmtQuery = "INSERT INTO User_Statement (user_id, updated_balance, date, transaction_type, amount) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement stmt = cnc.c.prepareStatement(stmtQuery);
                    stmt.setString(1, user);
                    stmt.setInt(2, new_balance);
                    stmt.setString(3, tarik);
                    stmt.setString(4, "Withdrawal");
                    stmt.setInt(5, withdrawAmount);
                    stmt.executeUpdate();
                    
                    // Update the User_Balance table with the new balance
                    String update_query = "UPDATE User_Balance SET balance = ? WHERE user_id = ?";
                    PreparedStatement updateStmt = cnc.c.prepareStatement(update_query);
                    updateStmt.setInt(1, new_balance);
                    updateStmt.setString(2, user);
                    updateStmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Rs " + withdrawAmount + " Debited Successfully");
                    
                    setVisible(false);
                    new UserDashboard(token, user).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "User balance record not found");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public static void main(String[] args) {
        new Fastcash("", "");
    }
}
