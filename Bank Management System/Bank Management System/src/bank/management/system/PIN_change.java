package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class PIN_change extends JFrame implements ActionListener {
    JButton change, back;
    JPasswordField txtcon, txtpin;
    String user; 

    public PIN_change(String user) {
        setSize(900, 900);
        setTitle("Change Password");
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.user = user;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel txt = new JLabel("Change your Password");
        txt.setForeground(Color.white);
        txt.setFont(new Font("System", Font.BOLD, 16));
        txt.setBounds(270, 290, 500, 35);
        image.add(txt);

        JLabel lblpin = new JLabel("Enter New Password:");
        lblpin.setForeground(Color.white);
        lblpin.setFont(new Font("System", Font.BOLD, 14));
        lblpin.setBounds(170, 340, 200, 35);
        image.add(lblpin);

        txtpin = new JPasswordField();
        txtpin.setFont(new Font("System", Font.BOLD, 14));
        txtpin.setBounds(340, 348, 150, 22);
        image.add(txtpin);

        JLabel lblcon = new JLabel("Re-Enter New Password:");
        lblcon.setForeground(Color.white);
        lblcon.setFont(new Font("System", Font.BOLD, 14));
        lblcon.setBounds(170, 390, 500, 35);
        image.add(lblcon);

        txtcon = new JPasswordField();
        txtcon.setFont(new Font("System", Font.BOLD, 14));
        txtcon.setBounds(340, 390, 150, 22);
        image.add(txtcon);

        change = new JButton("Change");
        change.setBounds(355, 485, 150, 27);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 27);
        back.addActionListener(this);
        image.add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String password = String.valueOf(txtpin.getPassword()); 
                String repassword = String.valueOf(txtcon.getPassword()); 

                if (!password.equals(repassword)) {
                    JOptionPane.showMessageDialog(null, "Entered passwords do not match");
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter password");
                } else {
                    // Hash the new password using BCrypt (as in your registerUser logic)
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                    Conn cnc = new Conn();
                    String query = "UPDATE Accounts SET password = ? WHERE user_id = ?";
                    PreparedStatement pstmt = cnc.c.prepareStatement(query);
                    pstmt.setString(1, hashedPassword);
                    pstmt.setString(2, user);
                    int updated = pstmt.executeUpdate();
                    if (updated > 0) {
                        JOptionPane.showMessageDialog(null, "Password Changed Successfully");
                        setVisible(false);
                        new Transactions(user).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Password update failed.");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(user).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PIN_change("");
    }
}
