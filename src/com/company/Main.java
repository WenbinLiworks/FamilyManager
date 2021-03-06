package com.company;

import Controllers.LoginController;
import Controllers.RegisterFamilyController;
import Controllers.StartController;
import Views.LoginView;
import Views.RegisterFamilyView;
import Views.StartView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
     /*   try {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery("select * from employees");
            while(rs.next()){
                System.out.println(rs.getString("last_name"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    */
        /* Create and display the dialog */
/***st = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareProject", "root", "root").prepareStatement(query);
*/
/*EventQueue.invokeLater(new Runnable() {
            public void run() {//create a thread
                StartView sview=new StartView();
                StartController scontroller=new StartController(sview);
            }
        });

        EventQueue.invokeLater(new Runnable() {
            public void run() {//create a thread
                RegisterFamilyController rfc=new RegisterFamilyController(new RegisterFamilyView());
            }
        });

*/        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginController lc=new LoginController(new LoginView());
            }
        });


    }
}
