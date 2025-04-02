package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

public class Registration extends JFrame implements ActionListener
{
    Font txtfont= new Font("arial",Font.BOLD, 14);
    JTextField txtuser;
    JPasswordField txtpass, txtcpass;
    JRadioButton rdmale,rdfemale,mrd,unmrd,other;
    JButton next;
    JDateChooser date;
    long random;
    
    Registration()
    {
        setSize(750,800);
        setTitle("Registration");
        setLayout(null);
        //getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        Random ran= new Random();
        random=Math.abs((ran.nextLong()% 9000L) + 1000L);//makes number positive
        
        
        
        JLabel formno = new JLabel("CONFIRM REGISTRATION");
        formno.setBounds(180,30,600,40);
        formno.setFont(new Font("Raleway",Font.BOLD,28));
        add(formno);
        
        JLabel perdet = new JLabel("Enter your User ID & Password");
        perdet.setBounds(310,70,400,30);
        perdet.setFont(new Font("arial",Font.BOLD,18));
        add(perdet);
        
        JLabel user = new JLabel("User ID:");
        user.setBounds(100,130,100,30);
        user.setFont(new Font("arial",Font.BOLD,18));
        add(user);
        
        txtuser= new JTextField();
        txtuser.setFont(txtfont);
        txtuser.setBounds(250, 130, 400, 30);
        add(txtuser);
        
        JLabel pass = new JLabel("Password:");
        pass.setBounds(100,180,150,30);
        pass.setFont(new Font("arial",Font.BOLD,18));
        add(pass);
        
        txtpass= new JPasswordField();
        txtpass.setFont(txtfont);
        txtpass.setBounds(250, 180, 400, 30);
        add(txtpass);

        JLabel cpass = new JLabel("Confirm Password:");
        cpass.setBounds(100,230,150,30);
        cpass.setFont(new Font("arial",Font.BOLD,18));
        add(cpass);
        
        txtcpass= new JPasswordField();
        txtcpass.setFont(txtfont);
        txtcpass.setBounds(250, 230, 400, 30);
        add(txtcpass);
        
        
        next= new JButton("NEXT");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setFont(new Font("raleway",Font.BOLD,14));
        next.setBounds(570, 660, 80, 30);
        add(next);
        next.addActionListener(this);
        System.out.println("till here1");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
        
    }
    public void actionPerformed(ActionEvent ae){
        System.out.println("till here 2");
        String user= txtuser.getText();
        String pass= String.valueOf(txtpass.getPassword());        
        try
        {
            if(user.equals(""))
            {
                JOptionPane.showMessageDialog(null,"User ID required");
            }
            else if(pass.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter strong password");
            }
            else
            {
            	UserDAO.registerUser(user, pass);
                Conn conn = new Conn();

                PreparedStatement balancestmt = conn.c.prepareStatement("INSERT INTO User_Balance (balance, user_id) VALUES (?, ?);");
                balancestmt.setInt(1, 0);
                balancestmt.setString(2, user);
                balancestmt.executeUpdate();
            	System.out.println("New User Registered!");
            	setVisible(false);
            	new Login();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    public static void main(String[] args)
    {
        new Registration();
    }
}
