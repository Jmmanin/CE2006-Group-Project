/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- LogInUI
User Interface for the user logIn/user creation section of the program
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LogInUI
{
   private JFrame logInFrame;
   private JPanel titlePanel;
   private JPanel fieldPanel;
   private JPanel buttonPanel;
   private JLabel titleLabel;
   private JLabel uNLabel;
   private JTextField uNField;
   private JLabel pwLabel;
   private JPasswordField pwField;
   private JButton newUserButton;
   private JButton logInButton;
   
   private ServerMgr serverMgr;
   
   public LogInUI()
   {
      logInFrame= new JFrame("Log In");
      logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      logInFrame.setResizable(false);
      logInFrame.setLayout(new BoxLayout(logInFrame.getContentPane(), BoxLayout.Y_AXIS));
      
      titlePanel= new JPanel();
      titlePanel.setLayout(new FlowLayout());
      
      titleLabel= new JLabel("Welcome to Dream Course Finder");
      titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
      titlePanel.add(titleLabel);
      logInFrame.add(titlePanel);
      
      fieldPanel= new JPanel();
      fieldPanel.setLayout(new GridLayout(2,2));
      
      uNLabel= new JLabel(" Username: ");
      fieldPanel.add(uNLabel);
      
      uNField= new JTextField();
      fieldPanel.add(uNField);
   
      pwLabel= new JLabel(" Password: ");
      fieldPanel.add(pwLabel);
            
      pwField= new JPasswordField();
      fieldPanel.add(pwField);
      logInFrame.add(fieldPanel);
      
      buttonPanel= new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
      buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
   
      LogInListener theListener= new LogInListener();
   
      newUserButton= new JButton("New User");
      newUserButton.setActionCommand("new");
      newUserButton.addActionListener(theListener);
      buttonPanel.add(newUserButton);
      buttonPanel.add(Box.createHorizontalGlue());
   
      logInButton= new JButton("Log In");
      logInButton.setActionCommand("log");
      logInButton.addActionListener(theListener);
      buttonPanel.add(logInButton);
      buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
      
      logInFrame.add(buttonPanel);
      logInFrame.pack();
      logInFrame.setVisible(true);
      
      serverMgr= new ServerMgr();
   }
   
   private class LogInListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         int status= 2;
         
         if(e.getActionCommand().equals("new"))
         {
            new NewUserDialog();
         }
         else
         {
            try
            {
               status= serverMgr.verifyUser(uNField.getText(),new String(pwField.getPassword()));
            }
            catch(Exception e2)
            {
               JOptionPane.showMessageDialog(logInFrame ,"Unable to connect to database.\nCheck connection and try again.", "SQL Error", JOptionPane.ERROR_MESSAGE);          
               System.exit(1);
            }
            
            if(status==2)
               JOptionPane.showMessageDialog(logInFrame ,"Could not log-in.\nInvalid username entered.", "Invalid Login", JOptionPane.ERROR_MESSAGE);          
            else if(status==0)
               JOptionPane.showMessageDialog(logInFrame ,"Could not log-in.\nInvalid password entered.", "Invalid Login", JOptionPane.ERROR_MESSAGE);          
            else
            {
               JOptionPane.showMessageDialog(logInFrame ,"Login Successful.\nWelcome back " + uNField.getText() + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);          
               logInFrame.setVisible(false);
               logInFrame.dispose();
               new SurveyUI(uNField.getText(),serverMgr);
            }
         }
      }
   }
   
   private class NewUserDialog extends JDialog implements ActionListener
   {
      private JPanel titlePanel;
      private JPanel fieldPanel;
      private JPanel buttonPanel;
      private JLabel titleLabel;
      private JTextField uNFieldCreate;
      private JPasswordField pwFieldCreate;
      private JLabel pwLabelCreate2;
      private JPasswordField pwFieldCreate2;
      private JButton createButton;
      private JButton backButton;
      
      public NewUserDialog()
      {
         super(logInFrame, "New User", true);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setResizable(false);
         setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
      
         titlePanel= new JPanel();
         titlePanel.setLayout(new FlowLayout());
      
         titleLabel= new JLabel("New User Creation");
         titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
         titlePanel.add(titleLabel);
         add(titlePanel);
      
         fieldPanel= new JPanel();
         fieldPanel.setLayout(new GridLayout(3,2));
      
         fieldPanel.add(uNLabel);
         uNFieldCreate= new JTextField();
         fieldPanel.add(uNFieldCreate);
         fieldPanel.add(pwLabel);
         pwFieldCreate= new JPasswordField();
         fieldPanel.add(pwFieldCreate);
         
         pwLabelCreate2= new JLabel(" Confirm Password: ");
         fieldPanel.add(pwLabelCreate2);
            
         pwFieldCreate2= new JPasswordField();
         fieldPanel.add(pwFieldCreate2);
         add(fieldPanel);
      
         buttonPanel= new JPanel();
         buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
         buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
            
         createButton= new JButton("Create");
         createButton.setActionCommand("create");
         createButton.addActionListener(this);
         buttonPanel.add(createButton);
         buttonPanel.add(Box.createHorizontalGlue());
      
         backButton= new JButton("Back");
         backButton.setActionCommand("back");
         backButton.addActionListener(this);
         buttonPanel.add(backButton);
         buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
      
         add(buttonPanel);
         pack();
         setVisible(true);
      }
      
      public void actionPerformed(ActionEvent e)
      {
         if(e.getActionCommand().equals("create"))
         {
            String username, password, password2;
            int status= 0;
            
            username= uNFieldCreate.getText();
            password= new String(pwFieldCreate.getPassword());
            password2= new String(pwFieldCreate2.getPassword());
            
            if(username.length()==0 || username.length()>20)
               JOptionPane.showMessageDialog(this ,"Invalid username length.\nUsername must be less than 20 characters long\nand at least 1 character long.", "Invalid Username", JOptionPane.ERROR_MESSAGE);          
            else if(password.length()==0 || password.length()>20)
               JOptionPane.showMessageDialog(this ,"Invalid password length.\nPassword must be less than 20 characters long\nand at least 1 character long.", "Invalid Password", JOptionPane.ERROR_MESSAGE);          
            else if(!password.equals(password2))
               JOptionPane.showMessageDialog(this ,"Passwords entered do not match.", "Password Mismatch", JOptionPane.ERROR_MESSAGE);          
            else
            {
               try
               {
                  status= serverMgr.createUser(username, password);
               }
               catch(Exception e2)
               {
                  JOptionPane.showMessageDialog(logInFrame ,"Unable to connect to database.\nCheck connection and try again.", "SQL Error", JOptionPane.ERROR_MESSAGE);          
                  System.exit(1);
               }
                              
               if(status==2)
                  JOptionPane.showMessageDialog(this ,"Username already exists.\nPlease try another.", "Invalid Username", JOptionPane.ERROR_MESSAGE);
               else if(status==0)
                  JOptionPane.showMessageDialog(this ,"Unable to create account.", "User Create Error", JOptionPane.ERROR_MESSAGE);
               else
               {
                  JOptionPane.showMessageDialog(this ,"User creation successful.\nWelcome " + uNFieldCreate.getText() + "!", "User Create Success", JOptionPane.INFORMATION_MESSAGE);          
                  logInFrame.setVisible(false);
                  logInFrame.dispose();
                  new SurveyUI(username, serverMgr);
               }
            }
         }   
         else
         {
            setVisible(false);
            dispose();
         }
      }
   }
   
   public static void main(String args[])
   {
      new LogInUI();
   }
}