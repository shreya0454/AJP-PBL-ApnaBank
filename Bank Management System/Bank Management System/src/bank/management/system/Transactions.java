
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener
{
    JButton deposit,withdrawl,balenq,exit,pinchange,mini,fastcash;
    String pin_no;
    Transactions(String pin)
    {
       
        setSize(900,900);
        setTitle("ATM Simulator");
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        pin_no=pin;
               
       // getContentPane().setBackground(Color.white);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        JLabel txt=new JLabel("Please Select Your Transaction");
        txt.setBounds(225,300,700,35);
        txt.setForeground(Color.white);
        txt.setFont(new Font("Raleway",Font.BOLD,16));
        image.add(txt);
        
        deposit= new JButton("Deposit");
        deposit.setFont(new Font("arial",Font.BOLD,13));
        deposit.setBounds(170,415,150,30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        withdrawl= new JButton("Withdrawl");
        withdrawl.setFont(new Font("arial",Font.BOLD,13));
        withdrawl.setBounds(350,415,150,30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);
        
        fastcash= new JButton("Fast Cash");
        fastcash.setFont(new Font("arial",Font.BOLD,13));
        fastcash.setBounds(170,450,150,30);
        fastcash.addActionListener(this);
        image.add(fastcash);
        
        mini= new JButton("Mini Statement");
        mini.setFont(new Font("arial",Font.BOLD,13));
        mini.setBounds(350,450,150,30);
        mini.addActionListener(this);
        image.add(mini);
        
        pinchange= new JButton("Change PIN");
        pinchange.setFont(new Font("arial",Font.BOLD,13));
        pinchange.setBounds(170,485,150,30);
        pinchange.addActionListener(this);
        image.add(pinchange);
        
        balenq= new JButton("Balance Enquiry");
        balenq.setFont(new Font("arial",Font.BOLD,13));
        balenq.setBounds(350,485,150,30);
        balenq.addActionListener(this);
        image.add(balenq);
        
        exit= new JButton("Exit");
        exit.setFont(new Font("arial",Font.BOLD,13));
        exit.setBounds(350,520,150,30);
        exit.addActionListener(this);
        image.add(exit);
        
        
        
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==exit)
        {
            System.exit(0);
        }
        if(ae.getSource()==deposit)
        {
            setVisible(false);
            new Deposit(pin_no);
        }
        else if(ae.getSource()==withdrawl)
        {
            setVisible(false);
            new Withdrawl(pin_no);
        }
        else if(ae.getSource()==fastcash)
        {
            setVisible(false);
            new Fastcash(pin_no);
        }
         else if(ae.getSource()==pinchange)
        {
            setVisible(false);
            new PIN_change(pin_no);
        }else if(ae.getSource()==balenq)
        {
            setVisible(false);
            new Balance_enq(pin_no);
        }
        else if(ae.getSource()==mini)
        {
            new Mini_Statement(pin_no);
        }
        
    }
    
    public static void main(String[] args)
    {
        new Transactions("");
;    }
}
