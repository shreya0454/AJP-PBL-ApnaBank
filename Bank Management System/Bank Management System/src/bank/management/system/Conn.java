/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;
import java.sql.*;
 

public class Conn
{
    Connection c;
        Statement s;
        ResultSet res;
    
    public Conn()
    {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmangementsystem","root","sa123");
            if(c!=null)
            {
                System.out.println("connected");
            }
            
            s=c.createStatement();
            
            
    }
    catch(Exception e)
    {
        System.out.println(e);
        System.out.println("Not connected");
    }
    }
}
