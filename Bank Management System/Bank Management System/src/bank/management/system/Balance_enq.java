package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Balance_enq extends JFrame implements ActionListener {
    JButton back;
    private static String token = null;
    String user; // user ID

    public Balance_enq(String token, String uid) {
        Balance_enq.token = token;
        if (!UserDAO.verifyToken(token)) {
            JOptionPane.showMessageDialog(null, "Access Denied: Invalid Token");
            return;
        }
        user = uid;

        setSize(900, 900);
        setTitle("Current Account Balance");
        setLayout(null);
        setLocationRelativeTo(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        int balance = 0;
        try {
            Conn cnc = new Conn();
            String query = "SELECT balance FROM User_Balance WHERE user_id = ?";
            PreparedStatement stmt = cnc.c.prepareStatement(query);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                balance = rs.getInt("balance");
            } else {
                JOptionPane.showMessageDialog(null, "User balance record not found");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        JLabel l1 = new JLabel("Your Current Account Balance is Rs " + balance);
        l1.setForeground(Color.white);
        l1.setBounds(170, 300, 400, 30);
        image.add(l1);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 27);
        back.addActionListener(this);
        image.add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new UserDashboard(token, user);
    }

    public static void main(String[] args) {
        new Balance_enq("", "");
    }
}