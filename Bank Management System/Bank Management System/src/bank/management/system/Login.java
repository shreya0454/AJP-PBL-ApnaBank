
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class Login extends JFrame implements ActionListener
{
    private static String token = null;

    JButton log;
    JButton clear;
    JButton signup;
    JTextField txt1;
    JPasswordField pwd;
    Login()
    {
        setTitle("Login");
        setSize(800,500);
        setLayout(null);
        setLocationRelativeTo(null);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);  
        JLabel lbl1= new JLabel(i3);
        lbl1.setBounds(70,10,100,100);
        add(lbl1);
        
        JLabel lbl2= new JLabel("Welcome to ApnaBank");
        lbl2.setFont((new Font("arial",Font.BOLD,36)));
        lbl2.setBounds(250, 40,400 , 40);
        add(lbl2);
        
        JLabel uid = new JLabel("User ID: ");
        uid.setFont(new Font("Raleway",Font.BOLD, 28));
        uid.setBounds(120,150,1500,40);
        add(uid);
        
        JLabel pass= new JLabel("Password: ");
        pass.setFont(new Font("Raleway",Font.BOLD, 28));
        pass.setBounds(120,220,230,30);
        add(pass);
        
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
        
        signup= new JButton("Open New Account");
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
            String uid=txt1.getText();
            String pass=String.valueOf(pwd.getPassword());
            token = UserDAO.loginUser(uid, pass);	
            System.out.println("Successfully signed in!");
            setVisible(false);
            new UserDashboard(token, uid);
        }
        
    }
    public static void main(String[] args)
    {
            new Login();
    }
}
