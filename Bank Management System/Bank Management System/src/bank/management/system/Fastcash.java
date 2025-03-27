
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Fastcash extends JFrame implements ActionListener
{
    JButton hundred,hundred5,thousand,thousand2,thousand5,thousand10,back;
    String pin_no;
    Fastcash(String pin)
    {
       
        setSize(900,900);
        setTitle("Fast Cash");
        setLayout(null);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        pin_no=pin;
               
       // getContentPane().setBackground(Color.white);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        JLabel txt=new JLabel("SELECT WITHDRAWL AMOUNT");
        txt.setBounds(225,300,700,35);
        txt.setForeground(Color.white);
        txt.setFont(new Font("Raleway",Font.BOLD,16));
        image.add(txt);
        
        hundred= new JButton("RS 100");
        hundred.setFont(new Font("arial",Font.BOLD,13));
        hundred.setBounds(170,415,150,30);
        hundred.addActionListener(this);
        image.add(hundred);
        
        hundred5= new JButton("RS 500");
        hundred5.setFont(new Font("arial",Font.BOLD,13));
        hundred5.setBounds(350,415,150,30);
        hundred5.addActionListener(this);
        image.add(hundred5);
        
        thousand= new JButton("RS 1000");
        thousand.setFont(new Font("arial",Font.BOLD,13));
        thousand.setBounds(170,450,150,30);
        thousand.addActionListener(this);
        image.add(thousand);
        
        thousand2= new JButton("RS 2000");
        thousand2.setFont(new Font("arial",Font.BOLD,13));
        thousand2.setBounds(350,450,150,30);
        thousand2.addActionListener(this);
        image.add(thousand2);
        
        thousand5= new JButton("RS 5000");
        thousand5.setFont(new Font("arial",Font.BOLD,13));
        thousand5.setBounds(170,485,150,30);
        thousand5.addActionListener(this);
        image.add(thousand5);
        
        thousand10= new JButton("RS 10000");
        thousand10.setFont(new Font("arial",Font.BOLD,13));
        thousand10.setBounds(350,485,150,30);
        thousand10.addActionListener(this);
        image.add(thousand10);
        
        back= new JButton("Back");
        back.setFont(new Font("arial",Font.BOLD,13));
        back.setBounds(350,520,150,30);
        back.addActionListener(this);
        image.add(back);
        
        
        
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            setVisible(false);
           new Transactions(pin_no).setVisible(true);
        }
        else
        {   int lastTransactionAmount=0;
            String amount= ((JButton)ae.getSource()).getText().substring(3);
            try
            {
                Conn cnc= new Conn();
                ResultSet rs= cnc.s.executeQuery("select * from bank where pin= '"+pin_no+"'");
                int balance=0;
                while(rs.next())
                {
                    if(rs.getString("type").equals("deposit"))
                    {
                        balance+=Integer.parseInt(rs.getString("amount"));
                    } 
                    else{
                        balance -=Integer.parseInt(rs.getString("amount"));
                    }
                    lastTransactionAmount = Integer.parseInt(amount);
                }
                if(ae.getSource()!=back && balance <lastTransactionAmount)
                {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date tarik=new Date();
                String query="insert into bank values('"+pin_no+"' ,'"+tarik+"','"+"withdrawl"+"','"+amount+"')";
                cnc.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs "+amount+" Debited Successfully");
                
                setVisible(false);
                new Transactions(pin_no);
            
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

    }
    
    public static void main(String[] args)
    {
        new Fastcash("");
;    }
}
