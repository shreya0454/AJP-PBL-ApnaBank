/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;


/**
 *
 * @author ASUS
 */
public class SignupThree extends JFrame implements ActionListener
{
    JRadioButton saving,fd,current,rd;
    JCheckBox atmcard,cheque,estate,interbank,mobbanking,alerts,declare;
    JButton submit,cancel;
    String formno;
    
    SignupThree(String form)
    {
        
        setSize(750,800);
        setTitle("Registration Page-3");
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        formno=form;
        
        JLabel l1= new JLabel("Accounts Details");
        l1.setFont(new Font("Raleway",Font.BOLD,28));
        l1.setBounds(270,50,400,40);
        add(l1);
        
        JLabel lbltype= new JLabel("Account Type");
        lbltype.setFont(new Font("Raleway",Font.BOLD,17));
        lbltype.setBounds(100,130,200,30);
        add(lbltype);
        
        saving= new JRadioButton(" Saving Account");
        saving.setFont(new Font("Raleway",Font.BOLD,15));
        saving.setBounds(100, 180, 180, 20);
        saving.setBackground(Color.white);
        add(saving);
        
        fd= new JRadioButton(" Fixed Deposit Account");
        fd.setFont(new Font("Raleway",Font.BOLD,15));
        fd.setBounds(350, 180, 250, 20);
        fd.setBackground(Color.white);
        add(fd);
        
        current= new JRadioButton(" Current Account");
        current.setFont(new Font("Raleway",Font.BOLD,15));
        current.setBounds(100, 220, 180, 20);
        current.setBackground(Color.white);
        add(current);
        
        rd= new JRadioButton(" Recurring Deposit Account");
        rd.setFont(new Font("Raleway",Font.BOLD,15));
        rd.setBounds(350, 220, 250, 20);
        rd.setBackground(Color.white);
        add(rd);
        
        ButtonGroup grp1= new ButtonGroup();
        grp1.add(saving);
        grp1.add(fd);
        grp1.add(current);
        grp1.add(rd);
        
        JLabel lblcard= new JLabel("Card Number:");
        lblcard.setFont(new Font("Raleway",Font.BOLD,17));
        lblcard.setBounds(100,270,150,30);
        add(lblcard);
        
        JLabel number= new JLabel("XXXX-XXXX-XXXX-4186");
        number.setFont(new Font("Raleway",Font.BOLD,17));
        number.setBounds(250,270,300,30);
        add(number);
        
        JLabel msg= new JLabel("This is your 16 digit card number.");
        msg.setFont(new Font("Raleway",Font.PLAIN,11));
        msg.setBounds(250,290,300,30);
        add(msg);
        
        JLabel lblpin= new JLabel("PIN:");
        lblpin.setFont(new Font("Raleway",Font.BOLD,17));
        lblpin.setBounds(100,330,150,30);
        add(lblpin);
        
        
        JLabel lblnumber= new JLabel("XXXX");
        lblnumber.setFont(new Font("Raleway",Font.BOLD,17));
        lblnumber.setBounds(250,330,300,30);
        add(lblnumber);
        
        JLabel pindet= new JLabel("Your 4 Digit PIN");
        pindet.setFont(new Font("Raleway",Font.PLAIN,11));
        pindet.setBounds(250,350,150,30);
        add(pindet);
        
        JLabel lblservice= new JLabel("Services Required");
        lblservice.setFont(new Font("Raleway",Font.BOLD,17));
        lblservice.setBounds(100,390,150,30);
        add(lblservice);
        
        
         atmcard= new JCheckBox("ATM Card");
        atmcard.setBackground(Color.white);
        atmcard.setBounds(100, 430, 200, 30);
        atmcard.setFont(new Font("Raleway",Font.BOLD,15));
        add(atmcard);
        
        interbank= new JCheckBox("Internet Banking");
        interbank.setBackground(Color.white);
        interbank.setBounds(350, 430, 200, 30);
        interbank.setFont(new Font("Raleway",Font.BOLD,15));
        add(interbank);
        
         mobbanking= new JCheckBox("Mobile Banking");
        mobbanking.setBackground(Color.white);
        mobbanking.setBounds(100, 470, 200, 30);
        mobbanking.setFont(new Font("Raleway",Font.BOLD,15));
        add(mobbanking);
        
        alerts= new JCheckBox("Email & SMS Alerts");
        alerts.setBackground(Color.white);
        alerts.setBounds(350, 470, 200, 30);
        alerts.setFont(new Font("Raleway",Font.BOLD,15));
        add(alerts);
        
        cheque= new JCheckBox("Cheque Book");
        cheque.setBackground(Color.white);
        cheque.setBounds(100, 510, 200, 30);
        cheque.setFont(new Font("Raleway",Font.BOLD,15));
        add(cheque);
        
        estate= new JCheckBox("E-Statment");
        estate.setBackground(Color.white);
        estate.setBounds(350, 510, 200, 30);
        estate.setFont(new Font("Raleway",Font.BOLD,15));
        add(estate);
        
        declare= new JCheckBox("I hereby declares above entered information are correct");
        declare.setBackground(Color.white);
        declare.setBounds(100, 570, 350, 30);
        declare.setFont(new Font("Raleway",Font.PLAIN,10));
        add(declare);
        
        
        submit= new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.setFont(new Font("raleway",Font.BOLD,14));
        submit.setBounds(240, 630, 100, 30);
        add(submit);
        submit.addActionListener(this);
        
        cancel= new JButton("CANCEL");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("raleway",Font.BOLD,14));
        cancel.setBounds(400, 630, 100, 30);
        add(cancel);
        cancel.addActionListener(this);
        setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        
        String account="";
        if(saving.isSelected())
        {
            account="Saving Account";
        }
        else if(current.isSelected())
        {
            account="Current Account";
        }
        else if(fd.isSelected())
        {
            account="Fixed Deposit Account";
        }
        else if(rd.isSelected())
        {
            account="Reoccuring Deposit Account";
        }
        
        Random ran= new Random();
        String cardno="" +Math.abs((ran.nextLong()%9000000L) + 5040936000000000L);
        
        String pinno="" +Math.abs((ran.nextLong()%9000L) + 1000L);
         
        String service="";
        if(atmcard.isSelected())
        {
            service=service +"ATM Card";
        }
        else if(interbank.isSelected())
        {
            service=service +"Internet Banking";
        }
        else if(mobbanking.isSelected())
        {
            service=service +"Mobile Banking";
        }
        else if(cheque.isSelected())
        {
            service=service +"Cheque Book";
        }
        else if(alerts.isSelected())
        {
            service=service +"Email & SMS Alerts";
        }
        else if(estate.isSelected())
        {
            service=service +"E-Statement";
        }
        
        try
        {
            if(ae.getSource()==cancel)
            {
                this.dispose();
                new Login().setVisible(true);
                
            }
            if(ae.getSource()==submit)
            {
                
                if(account.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Choose an account type", "Instruction", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    if(!declare.isSelected())
                    {
                        JOptionPane.showMessageDialog(null,"Check the final declaration","Caution", JOptionPane.PLAIN_MESSAGE);
                    }
                    else
                    {
                        
                        Conn cnc= new Conn();
                        String query="INSERT INTO signup3(formno, Account_Type, Card_Number, PIN_Number, Services) VALUES ('" +formno + "','" + account + "', '" + cardno + "', '" + pinno + "', '" + service + "');";
                        String query1="INSERT INTO login(formno, Card_Number, PIN_Number) VALUES ('" +formno + "','" + cardno + "', '" + pinno + "');";
                        cnc.s.executeUpdate(query);
                        cnc.s.executeUpdate(query1);
                        JOptionPane.showMessageDialog(null, "Card Number: "+ cardno +"\nPIN: "+pinno, "Card Details", JOptionPane.INFORMATION_MESSAGE);
                        
                        
                    }
                    setVisible(false);
                    new Deposit(pinno).setVisible(true);
                    
                }
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
    
    public static void main(String[] args)
    {
       new SignupThree("");
    }
}
