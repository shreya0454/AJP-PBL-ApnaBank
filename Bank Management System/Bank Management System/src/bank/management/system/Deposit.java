
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deposit extends JFrame implements ActionListener
{
    private static String token = null;
    
    JButton back,deposit;
    JTextField amt;
    String user;
    Deposit(String token, String uid)
    {
    	Deposit.token = token;
        if (!UserDAO.verifyToken(token)) {
            JOptionPane.showMessageDialog(null, "Access Denied: Invalid Token");
            return;
        }
        
        setSize(900,900);
        setLayout(null);
        setLocationRelativeTo(null);
        user=uid; 
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        
        
        JLabel lbl= new JLabel(i3);
        lbl.setBounds(0,0,900,900);
        add(lbl);
        
        JLabel txtamt= new JLabel("Enter the amount you want to deposit:");
        txtamt.setForeground(Color.white);
        txtamt.setBounds(170, 300, 400, 20);
        txtamt.setFont(new Font("System",Font.BOLD,16));
        lbl.add(txtamt); 
        
        amt= new JTextField();
        amt.setFont(new Font("Raleway",Font.BOLD,16));
        amt.setBounds(170,350,320,20);
        lbl.add(amt);
        
        deposit= new JButton("Deposit");
        deposit.setBounds(355, 485,150 , 27);
        deposit.addActionListener(this);
        lbl.add(deposit);
        
        back= new JButton("Back");
        back.setBounds(355, 520,150 , 27);
        back.addActionListener(this);
        lbl.add(back);
        
        
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==deposit)
        {
            String cash=amt.getText();
            int amount = Integer.parseInt(cash);
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tarik = today.format(formatter);
            
            if(cash.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter the amount you want to deposit");
         
            }
            else
            {
                try
                {
                    Conn cnc= new Conn();
                    
                    String balance_query = "SELECT balance " +
                            "FROM User_Balance " +
                            "WHERE user_id = ?";                    
                    PreparedStatement balancestmt = cnc.c.prepareStatement(balance_query);
                    balancestmt.setString(1, user);
                    ResultSet rs = balancestmt.executeQuery();
                    rs.next();
                    int current_balance = rs.getInt("balance");
                    int new_balance	 = current_balance + amount;

                    
                    String query="INSERT INTO User_Statement (account_number, updated_balance, date, transaction_type, amount) VALUES (?,?,?,?,?);";
                    PreparedStatement stmt = cnc.c.prepareStatement(query);
                    stmt.setString(1, user);
                    stmt.setInt(2, new_balance);
                    stmt.setString(3, tarik);
                    stmt.setString(4, "Deposit");
                    stmt.setInt(5, amount);
                    cnc.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs"+cash+" Deposited Successfully");
                    
                    setVisible(false);
                    new UserDashboard(token, user).setVisible(true);
                
                
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                }
            }
        }
        else if(ae.getSource()==back)
        {
            setVisible(false);
            new UserDashboard(token, user).setVisible(true);
        }

    }
    
    
    public static void main(String[] args)
    {
        new Deposit("","");
    }
}
