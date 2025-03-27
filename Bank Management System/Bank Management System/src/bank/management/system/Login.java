
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class Login extends JFrame implements ActionListener
{
    JButton log;
    JButton clear;
    JButton signup;
    JTextField txt1;
    JPasswordField pwd;
    Login()
    {
        setTitle("ATM Login");
        setSize(800,500);
        setLayout(null);
        setLocationRelativeTo(null);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);  
        JLabel lbl1= new JLabel(i3);
        lbl1.setBounds(70,10,100,100);
        add(lbl1);
        
        JLabel lbl2= new JLabel("Welcome to ATM");
        lbl2.setFont((new Font("arial",Font.BOLD,40)));
        lbl2.setBounds(250, 40,400 , 40);
        add(lbl2);
        
        JLabel card = new JLabel("Card No: ");
        card.setFont(new Font("Raleway",Font.BOLD, 28));
        card.setBounds(120,150,1500,40);
        add(card);
        
        JLabel pin= new JLabel("Pin: ");
        pin.setFont(new Font("Raleway",Font.BOLD, 28));
        pin.setBounds(120,220,230,30);
        add(pin);
        
        txt1= new JTextField();
        txt1.setBounds(300, 150, 230, 30);
        txt1.setFont(new Font("arail",Font.BOLD,15));
        add(txt1);
        
        pwd= new JPasswordField();
        pwd.setBounds(300, 220, 230, 30);
        pwd.setFont(new Font("arail",Font.BOLD,15));
        add(pwd);
        
        log= new JButton("Sign in");
        log.setBounds(300, 300, 100, 30);
        log.setBackground(Color.black);
        log.setForeground(Color.white);
        log.addActionListener(this);
        add(log);
        
        clear= new JButton("Clear");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);
        
        signup= new JButton("Sign Up");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);
        
        
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==clear)
        {
            txt1.setText("");
            pwd.setText("");
        }
        else if(ae.getSource()==signup)
        {
            this.dispose();
            new SignupOne();
        }
        else if(ae.getSource()==log)
        {
            Conn cnc= new Conn();
            String card=txt1.getText();
            String pass=String.valueOf(pwd.getPassword());
            String query="select * from login where Card_Number ='"+card+"' and PIN_Number ='"+pass+"'";
           
            try
            {
               ResultSet res= cnc.s.executeQuery(query);
               if(res.next())
               {
                   this.dispose();
                   new Transactions(pass);
               }
               else
               {
                   JOptionPane.showMessageDialog(null, "Incorrect card number or pin");
                   
               }
               
                
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        
    }
    public static void main(String[] args)
    {
            new Login();
    }
}
