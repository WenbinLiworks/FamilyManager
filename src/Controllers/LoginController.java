package Controllers;

import Views.LoginView;
import Views.RegisterView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class LoginController {
    private LoginView lview;

    public LoginController(LoginView lview) {
        this.lview = lview;
        addMinimizeAction(new RegisterController.MinimizeListeners(lview,true),lview.minimize);
        addExitAction(new RegisterController.ExitListeners(lview,true),lview.exit);
        lview.addUsernameFocus(new FocusUsernameListener());
        lview.addPasswordFocus(new FocusPasswordListener());
        lview.addRegisterContextAction(new RegisterContextAction());
        lview.addLoginListener(new LoginExecutable());
        lview.addLoginMouse(new LoginMouseListener());
    }
    class FocusUsernameListener extends FocusAdapter {
        //clear the text field-username if it is "username"
        @Override
        public void focusGained(FocusEvent e) {
            if (lview.username.getText().trim().toLowerCase().equals("username")) {
                lview.username.setText("");
                lview.username.setForeground(Color.black);
                //set a border to text field-username
                lview.username.setBorder(lview.frameTextfield);
            }
            //set a yellow border to the username icon
            Border frameYellow = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow);
            lview.userIcon.setBorder(frameYellow);
        }

        //if the text is equal to username or to an empty string
        @Override
        public void focusLost(FocusEvent e) {
            if (lview.username.getText().trim().toLowerCase().equals("username") ||
                    lview.username.getText().trim().equals("")) {
                lview.username.setText("username");
                lview.username.setForeground(new Color(153, 153, 153));
                //remove the border from the text field
                lview.username.setBorder(null);
            }
            //remove the border from the icon label
            lview.userIcon.setBorder(null);
        }
    }

    class FocusPasswordListener extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            String pass = String.valueOf(lview.createPassword.getPassword());
            if (pass.trim().toLowerCase().equals("password")) {
                lview.createPassword.setText("");
                lview.createPassword.setForeground(Color.black);
                //set a border to text password field
                lview.createPassword.setBorder(lview.frameTextfield);
            }
            //set a yellow border to the username icon
            Border frameYellow = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow);
            lview.passIcon1.setBorder(frameYellow);
        }

        //if the text is equal to username or to an empty string
        @Override
        public void focusLost(FocusEvent e) {
            String pass = String.valueOf(lview.createPassword.getPassword());
            if (pass.trim().toLowerCase().equals("password") ||
                    pass.trim().equals("")) {
                lview.createPassword.setText("password");
                lview.createPassword.setForeground(new Color(153, 153, 153));
                //remove the border from the text field
                lview.createPassword.setBorder(null);
            }
            //remove the border from the icon label
            lview.passIcon1.setBorder(null);
        }
    }

    class RegisterContextAction extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e) {
            Border fr=BorderFactory.createMatteBorder(0,0,1,0,Color.blue);
            lview.registerContext.setBorder(fr);
        }
        @Override
        public void mouseExited(MouseEvent e){
            lview.registerContext.setBorder(null);
        }
        @Override
        public void mouseClicked(MouseEvent e){
            RegisterView rv=new RegisterView();
            RegisterController rc=new RegisterController(rv);
            try {
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class LoginExecutable implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PreparedStatement st;
            ResultSet rs;
            //get the username and password
            String uname = lview.username.getText();
            String pword = String.valueOf(lview.createPassword.getPassword());
            //create a select query to check if the username and the password exist in the DB
            String query = "SELECT * FROM users WHERE username= ? AND password = ?";
            //show a message if the username or the password fields are empty
            if(uname.trim().equals("username")){
                JOptionPane.showMessageDialog(null,"Enter your username","Empty username",2);
            }
            else if(pword.trim().equals("password"))
            {
                JOptionPane.showMessageDialog(null,"Enter your password","Empty password",2);
            }
            else {
                try {
                    st = DriverManager.getConnection("jdbc:mysql://localhost:3306/family", "root", "root").prepareStatement(query);
                    st.setString(1, uname);
                    st.setString(2, pword);
                    rs = st.executeQuery();
                    if (rs.next())//username and password are correct
                    {
                        System.out.println("dkkkkkkkkk");
                    } else {
                        //error message
                        JOptionPane.showMessageDialog(null, "Invalid username/password", "Login Error", 2);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class LoginMouseListener extends MouseAdapter{
        @Override
        public void mouseExited(MouseEvent e) {
            lview.login.setBackground(new Color(0,84,104));
        }
        @Override
        public void mouseEntered(MouseEvent e){
            lview.login.setBackground(new Color(0,101,183));
        }
    }
}

