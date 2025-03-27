
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;


public class SignupOne extends JFrame implements ActionListener
{
    Font txtfont= new Font("arial",Font.BOLD, 14);
    JTextField txtname,txtfname,txtpin,txtadr,txtcity,txtstate,txtmail;
    JRadioButton rdmale,rdfemale,mrd,unmrd,other;
    JButton next;
    JDateChooser date;
    long random;
    
    SignupOne()
    {
        setSize(750,800);
        setTitle("Registration");
        setLayout(null);
        //getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        Random ran= new Random();
        random=Math.abs((ran.nextLong()% 9000L) + 1000L);//makes number positive
        
        
        
        JLabel formno = new JLabel("APPLICATION FORM NO. "+random);
        formno.setBounds(180,30,600,40);
        formno.setFont(new Font("Raleway",Font.BOLD,28));
        add(formno);
        
        JLabel perdet = new JLabel("Personal Details");
        perdet.setBounds(310,70,400,30);
        perdet.setFont(new Font("arial",Font.BOLD,18));
        add(perdet);
        
        JLabel name = new JLabel("Name:");
        name.setBounds(100,130,100,30);
        name.setFont(new Font("arial",Font.BOLD,18));
        add(name);
        
        txtname= new JTextField();
        txtname.setFont(txtfont);
        txtname.setBounds(250, 130, 400, 30);
        add(txtname);
        
        JLabel fname = new JLabel("Father's Name:");
        fname.setBounds(100,180,150,30);
        fname.setFont(new Font("arial",Font.BOLD,18));
        add(fname);
        
        txtfname= new JTextField();
        txtfname.setFont(txtfont);
        txtfname.setBounds(250, 180, 400, 30);
        add(txtfname);
        
        JLabel dob = new JLabel("Date of birth:");
        dob.setBounds(100,230,150,30);
        dob.setFont(new Font("arial",Font.BOLD,18));
        add(dob);
        
        date= new JDateChooser();
        date.setBounds(250, 240, 400, 30);
        date.setForeground(Color.black);
        add(date);
        
        JLabel gender = new JLabel("Gender:");
        gender.setBounds(100,280,150,30);
        gender.setFont(new Font("arial",Font.BOLD,18));
        add(gender);
        
        rdmale= new JRadioButton("Male");
        rdmale.setBounds(250, 280, 60, 40);
        rdmale.setBackground(Color.white);
        add(rdmale);
        
        rdfemale= new JRadioButton("Female");
        rdfemale.setBounds(410, 280, 80, 40);
        rdfemale.setBackground(Color.white);
        add(rdfemale);
        
        ButtonGroup gendergrp = new ButtonGroup();
        gendergrp.add(rdmale);
        gendergrp.add(rdfemale);
        
        JLabel mail = new JLabel("Email:");
        mail.setBounds(100,330,150,30);
        mail.setFont(new Font("arial",Font.BOLD,18));
        add(mail);
        
        txtmail= new JTextField();
        txtmail.setFont(txtfont);
        txtmail.setBounds(250, 330, 400, 30);
        add(txtmail);
        
        JLabel marr = new JLabel("Marital Status:");
        marr.setBounds(100,380,150,30);
        marr.setFont(new Font("arial",Font.BOLD,18));
        add(marr);
        
        
        mrd= new JRadioButton("Married");
        mrd.setBounds(250, 380, 100, 40);
        mrd.setBackground(Color.white);
        add(mrd);
        
        unmrd= new JRadioButton("Unmarried");
        unmrd.setBounds(410, 380, 100, 40);
        unmrd.setBackground(Color.white);
        add(unmrd);
        
        other= new JRadioButton("Other");
        other.setBounds(590, 380, 100, 40);
        other.setBackground(Color.white);
        add(other);
        
        
        ButtonGroup marrgrp = new ButtonGroup();
        marrgrp.add(mrd);
        marrgrp.add(unmrd);
        marrgrp.add(other);
        
        JLabel adr  = new JLabel("Address:");
        adr.setBounds(100,430,150,30);
        adr.setFont(new Font("arial",Font.BOLD,18));
        add(adr);
        
        txtadr= new JTextField();
        txtadr.setFont(txtfont);
        txtadr.setBounds(250, 430, 400, 30);
        add(txtadr);
        
        JLabel city  = new JLabel("City:");
        city.setBounds(100,480,150,30);
        city.setFont(new Font("arial",Font.BOLD,18));
        add(city);
        
        txtcity= new JTextField();
        txtcity.setFont(txtfont);
        txtcity.setBounds(250, 480, 400, 30);
        add(txtcity);
        
        
        JLabel state  = new JLabel("State:");
        state.setBounds(100,530,150,30);
        state.setFont(new Font("arial",Font.BOLD,18));
        add(state);
        
        txtstate= new JTextField();
        txtstate.setFont(txtfont);
        txtstate.setBounds(250, 530, 400, 30);
        add(txtstate);
        
        JLabel pincode = new JLabel("Pincode:");
        pincode.setBounds(100,580,150,30);
        pincode.setFont(new Font("arial",Font.BOLD,18));
        add(pincode);
        
        txtpin= new JTextField();
        txtpin.setFont(txtfont);
        txtpin.setBounds(250, 580, 400, 30);
        add(txtpin);
        
        
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
        String formno= ""+random;
        String name= txtname.getText();
        String fname= txtfname.getText();
        String birth = ((JTextField) date.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(rdmale.isSelected())
        {
            gender= "Male";
        }
        else if(rdfemale.isSelected())
        {
            gender= "Female";
        }
        
        String email= txtmail.getText();
        String marital= null;
        if(mrd.isSelected())
        {
            marital= "Married";
        }
        else if(unmrd.isSelected())
        {
            marital="Unmarried";
        }
        else if(other.isSelected())
        {
            marital="Other";
        }
        String address= txtadr.getText();
        String city=txtcity.getText();
        String state= txtstate.getText();
        String pin = txtpin.getText();
        
        
        try
        {
            if(name.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Name required");
            }
            else
            {
                Conn cnc= new Conn();
                String query="INSERT INTO signup(formno, name, father_name, dob, gender, email, marital_status, address, city, pincode, state) VALUES ('" + formno + "', '" + name + "', '" + fname + "', '" + birth + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city + "', '" + pin + "', '" + state + "');";
//                if(cnc!=null)
//                {
//                    System.out.println("Connected");
//                }
                cnc.s.executeUpdate(query);
                setVisible(false);
                new SignupTwo(formno);
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQL Error");
            System.out.println(e);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
            
        
    }
    public static void main(String[] args)
    {
        new SignupOne();
    }
}
