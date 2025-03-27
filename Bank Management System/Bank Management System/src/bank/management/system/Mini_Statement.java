/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Mini_Statement extends JFrame implements ActionListener
{
    JButton back;
    
    Mini_Statement(String pin)
    {
        setSize(400,600);
        setTitle("Mini Statment");
        setLayout(null);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        
       getContentPane().setBackground(Color.white);
        
       
       
       JLabel bank= new JLabel("State Bank of India");
       bank.setFont(new Font("Raleway",Font.BOLD,17));
       bank.setBounds(120,30,160,20);
       add(bank);
       
       JLabel balance= new JLabel();
       balance.setBounds(20,400,300,20);
       add(balance);
   
       JLabel card= new JLabel();
       card.setBounds(20,80,300,20);
       add(card);
       
       JLabel lbl1= new JLabel();
       lbl1.setBounds(20,140,400,200);
       add(lbl1);
       try
       {
           Conn cnc= new Conn();
           ResultSet rs=cnc.s.executeQuery("select * from login where PIN_Number ='"+ pin+"'");
           while(rs.next())
           {
               card.setText("Card Number: "+rs.getString("Card_Number").substring(0,4)+"XXXXXXXX"+rs.getString("Card_Number").substring(12));
           }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
       
       try
       {
           Conn cnc= new Conn();
           int bal=0;
          ResultSet res= cnc.s.executeQuery("select * from bank where PIN ='"+pin+"'");
          while(res.next())
          {
            lbl1.setText(lbl1.getText()+ "<html>" + res.getString("data")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+res.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+res.getString("amount")+"<br><br><html>" );
            if(res.getString("type").equals("deposit"))
                    {
                        bal+=Integer.parseInt(res.getString("amount"));
                    } 
                    else{
                        bal -=Integer.parseInt(res.getString("amount"));
                    }
          }
          
          balance.setText("Your Current Balance is Rs "+bal);
       }
       catch(Exception ae)
               {
                   System.out.println(ae);
               }
     
       
       
        
        
    
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }
    
    public static void main(String[] args)
    {
        new Mini_Statement("");
    }
}
