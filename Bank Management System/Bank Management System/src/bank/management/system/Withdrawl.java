
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener
{
    
    JButton back,withdrawl;
    JTextField amt;
    String pin;
    Withdrawl(String pin_no)
    {
        setSize(900,900);
        setLayout(null);
        setLocationRelativeTo(null);
        pin=pin_no; 
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        
        
        JLabel lbl= new JLabel(i3);
        lbl.setBounds(0,0,900,900);
        add(lbl);
        
        JLabel txtamt= new JLabel("Enter the amount you want to withdraw:");
        txtamt.setForeground(Color.white);
        txtamt.setBounds(170, 300, 400, 20);
        txtamt.setFont(new Font("System",Font.BOLD,16));
        lbl.add(txtamt); 
        
        amt= new JTextField();
        amt.setFont(new Font("Raleway",Font.BOLD,16));
        amt.setBounds(170,350,320,20);
        lbl.add(amt);
        
        withdrawl= new JButton("Withdraw");
        withdrawl.setBounds(355, 485,150 , 27);
        withdrawl.addActionListener(this);
        lbl.add(withdrawl);
        
        back= new JButton("Back");
        back.setBounds(355, 520,150 , 27);
        back.addActionListener(this);
        lbl.add(back);
        
        
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==withdrawl)
        {
            String cash=amt.getText();
            Date tarik= new Date();
            if(cash.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter the amount you want to withdraw");
         
            }
            else
            {
                try
                {
                    Conn cnc= new Conn();
                    ResultSet rs= cnc.s.executeQuery("select * from bank where pin= '"+pin+"'");
                    int balance=0;
                    int lastTransactionAmount=Integer.parseInt(cash);;
                    while(rs.next())
                    {
                        if(rs.getString("type").equals("deposit"))
                        {
                            balance+=Integer.parseInt(rs.getString("amount"));
                        } 
                        else{
                            balance -=Integer.parseInt(rs.getString("amount"));
                        }

                    }
                    if(ae.getSource()!=back && balance <lastTransactionAmount)
                    {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    String query="insert into bank values('"+pin+"' ,'"+tarik+"','"+"withdrawl"+"','"+cash+"')";
                    cnc.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs"+cash+" Withdraw Successfully");
                    
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                
                
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
            new Transactions(pin).setVisible(true);
        }

    }
    
    
    public static void main(String[] args)
    {
        new Withdrawl("");
    }
}

