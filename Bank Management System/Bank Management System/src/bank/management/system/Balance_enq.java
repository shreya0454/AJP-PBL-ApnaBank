
package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;


public class Balance_enq extends JFrame implements ActionListener
{
    JButton back;
    String pin_no;
    Balance_enq(String pin)
    {
        setSize(900,900);
        setTitle("Current Account Balance");
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
        int balance=0;
        try{
                 Conn cnc= new Conn();
                ResultSet rs= cnc.s.executeQuery("select * from bank where pin= '"+pin_no+"'");
               
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
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        JLabel l1= new JLabel("Your Current Account Balance is Rs "+ balance);
        l1.setForeground(Color.white);
        l1.setBounds(170,300,400,30);
        image.add(l1);
        
        back= new JButton("Back");
        back.setBounds(355, 520,150 , 27);
        back.addActionListener(this);
        image.add(back);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
        new Transactions(pin_no);
    }
    
    public static void main(String[] args)
    {
        new Balance_enq("");
    }
}
