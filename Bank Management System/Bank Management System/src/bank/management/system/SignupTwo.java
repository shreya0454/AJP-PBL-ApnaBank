
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;


public class SignupTwo extends JFrame implements ActionListener
{
    Font txtfont= new Font("arial",Font.BOLD, 14);
    JTextField txtpan,txtadhar;
    JRadioButton rdyes,rdno,eyes,eno;
    JComboBox  relgn,category,income,edu,occupation;
    JButton next;
    String form;
    
    SignupTwo(String formno)
    {
        setSize(750,800);
        setTitle("Registration Page-2");
        setLayout(null);
        //getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        form=formno;
        
        JLabel lblform = new JLabel("APPLICATION FORM NO. "+formno);
        lblform.setBounds(180,30,600,40);
        lblform.setFont(new Font("Raleway",Font.BOLD,28));
        add(lblform);
        
        JLabel adddet = new JLabel("Additional Details");
        adddet.setBounds(310,70,400,30);
        adddet.setFont(new Font("arial",Font.BOLD,18));
        add(adddet);
        
        JLabel religion = new JLabel("Religion:");
        religion.setBounds(100,130,100,30);
        religion.setFont(new Font("arial",Font.BOLD,18));
        add(religion);
        
        String multirel[] = {"Hindi","Muslim","Sikh","Christian","Others"};
        relgn = new JComboBox(multirel);
        relgn.setBounds(270, 130, 400, 30);
        relgn.setBackground(Color.white);
        add(relgn);
       
        
        JLabel cat = new JLabel("Category:");
        cat.setBounds(100,180,150,30);
        cat.setFont(new Font("arial",Font.BOLD,18));
        add(cat);
        
        String valcategory[] = {"General","OBC","SC","ST","Other"};
        category= new JComboBox(valcategory);
        category.setBounds(270, 180, 400, 30);
        category.setBackground(Color.white);
        add(category);
        
        
        JLabel incme = new JLabel("Income:");
        incme.setBounds(100,230,150,30);
        incme.setFont(new Font("arial",Font.BOLD,18));
        add(incme);
        
        String valIncome[] = {"NULL","< 1,50,000","< 2,50,000","< 5,00,000","Upto 10,00,000"};
        income= new JComboBox(valIncome);
        income.setBounds(270, 230, 400, 30);
        income.setBackground(Color.white);
        add(income);
        
        
        JLabel educ = new JLabel("Educational");
        educ.setBounds(100,280,150,30);
        educ.setFont(new Font("arial",Font.BOLD,18));
        add(educ);
        
        
        JLabel qul = new JLabel("Qualification:");
        qul.setBounds(100,300,150,30);
        qul.setFont(new Font("arial",Font.BOLD,18));
        add(qul);
        
        String valedu[] = {"Non-Graduate","Graduate","Post-Graduate","PhD","Others"};
        edu= new JComboBox(valedu);
        edu.setBounds(270, 290, 400, 30);
        edu.setBackground(Color.white);
        add(edu);
        
        
        JLabel opp = new JLabel("Occupation:");
        opp.setBounds(100,350,150,30);
        opp.setFont(new Font("arial",Font.BOLD,18));
        add(opp);
        
        String valoccupation[] = {"Salaried","Self-Employed","Bussiness","Student","Retired","Other"};
        occupation= new JComboBox(valoccupation);
        occupation.setBounds(270, 350, 400, 30);
        occupation.setBackground(Color.white);
        add(occupation);
      
        
        JLabel panno  = new JLabel("PAN Number:");
        panno.setBounds(100,430,150,30);
        panno.setFont(new Font("arial",Font.BOLD,18));
        add(panno);
        
        txtpan= new JTextField();
        txtpan.setFont(txtfont);
        txtpan.setBounds(270, 430, 400, 30);
        add(txtpan);
        
        JLabel adharno  = new JLabel("Aadhar Number:");
        adharno.setBounds(100,480,150,30);
        adharno.setFont(new Font("arial",Font.BOLD,18));
        add(adharno);
        
        txtadhar= new JTextField();
        txtadhar.setFont(txtfont);
        txtadhar.setBounds(270, 480, 400, 30);
        add(txtadhar);
        
        
        JLabel citi  = new JLabel("Senior Citizen:");
        citi.setBounds(100,530,150,30);
        citi.setFont(new Font("arial",Font.BOLD,18));
        add(citi);
        
        rdyes= new JRadioButton("Yes");
        rdyes.setBounds(270, 530, 60, 40);
        rdyes.setBackground(Color.white);
        add(rdyes);
        
        rdno= new JRadioButton("No");
        rdno.setBounds(410, 530, 80, 40);
        rdno.setBackground(Color.white);
        add(rdno);
        
        ButtonGroup gendergrp = new ButtonGroup();
        gendergrp.add(rdyes);
        gendergrp.add(rdno);
       
        
        JLabel pincode = new JLabel("Existing Account:");
        pincode.setBounds(100,580,200,30);
        pincode.setFont(new Font("arial",Font.BOLD,18));
        add(pincode);
        
        eyes= new JRadioButton("Yes");
        eyes.setBounds(270, 580, 60, 40);
        eyes.setBackground(Color.white);
        add(eyes);
        
         eno= new JRadioButton("No");
        eno.setBounds(410, 580, 80, 40);
        eno.setBackground(Color.white);
        add(eno);
        
        ButtonGroup gendergrp2 = new ButtonGroup();
        gendergrp2.add(eyes);
        gendergrp2.add(eno);
        
       
        
        
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
                
        String srel= (String)relgn.getSelectedItem();
        String scategory= (String)category.getSelectedItem();
        String sincome=(String)income.getSelectedItem();
        String sedu= (String)edu.getSelectedItem();
        String socc= (String)occupation.getSelectedItem();
        String span=(String)txtpan.getText();
        String sadhar=(String )txtadhar.getText();
        
        String citi = null;
        if(rdyes.isSelected())
        {
            citi= "YES";
        }
        else if(rdno.isSelected())
        {
            citi= "NO";
        }
       
        String eacc= null;
        if(eyes.isSelected())
        {
            eacc= "Yes";
        }
        else if(eno.isSelected())
        {
            eacc="No";
        }
        try
        {
           Conn cnc= new Conn();
           String query="INSERT INTO signup2(formno, Religion, Category, Income, Education, Occupation, PAN_Number, Aadhar_Number, Senior_Citizen, Existing_Account) VALUES ('" +form + "','" + srel + "', '" + scategory + "', '" + sincome + "', '" + sedu + "', '" + socc + "', '" + span + "', '" + sadhar + "', '" + citi + "', '" + eacc + "');";
//                if(cnc!=null)
//                {
//                    System.out.println("Connected");
//                }
                cnc.s.executeUpdate(query);
                this.dispose();;
                new SignupThree(form);
           
        }
        catch(SQLException e)
        {
            System.out.println("SQL ERROR");
            System.out.println(e);
       }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
            
        
    }
    public static void main(String[] args)
    {
        new SignupTwo("");
    }
}

