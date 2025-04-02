package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
    
public class Mini_Statement extends JFrame implements ActionListener {
    JButton back;
    private static String token = null;
    String user; // user id

    public Mini_Statement(String token, String uid) {
        Mini_Statement.token = token;
        if (!UserDAO.verifyToken(token)) {
            JOptionPane.showMessageDialog(null, "Access Denied: Invalid Token");
            return;
        }
        user = uid;
        
        setSize(450,700);
        setTitle("Mini Statement");
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        
     // Bank title label
        JLabel bank = new JLabel("Apna Bank");
        bank.setFont(new Font("Raleway", Font.BOLD, 17));
        bank.setBounds(20, 20, 360, 30);  // Top of the frame, full width
        add(bank);

        // Label to display mini statement details using HTML formatting
        JLabel statementLabel = new JLabel();
        statementLabel.setBounds(20, 60, 360, 400);  // Below the bank label with ample space
        add(statementLabel);

        // Label to display current balance
        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(20, 470, 360, 30);  // Near the bottom, above the back button if any
        add(balanceLabel);

        try {
            Conn cnc = new Conn();
            // Retrieve mini statement ordered by date descending
            String query = "SELECT * FROM User_Statement WHERE user_id = ? ORDER BY date DESC";
            PreparedStatement stmt = cnc.c.prepareStatement(query);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            
            String miniStatementText = "<html>";
            int updatedBalance = 0;
            // Iterate over transactions and build HTML string
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("transaction_type");
                int amount = rs.getInt("amount");
                updatedBalance = rs.getInt("updated_balance");
                miniStatementText += date + " &nbsp;&nbsp;" + type 
                        + " &nbsp;&nbsp; Rs " + amount 
                        + " &nbsp;&nbsp; Bal: Rs " + updatedBalance + "<br><br>";

            }
            miniStatementText += "</html>";
            statementLabel.setText(miniStatementText);
            balanceLabel.setText("Your Current Balance is Rs " + updatedBalance);
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        back = new JButton("Back");
        back.setBounds(150, 550, 100, 30);
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new UserDashboard(token, user).setVisible(true);
    }
    
    public static void main(String[] args) {
        new Mini_Statement("", "");
    }
}
