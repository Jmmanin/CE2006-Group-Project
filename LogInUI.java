/*
Jeremy Manin
CE2006- Team Secret
Term Project- LogInUI
User Interface for the user logIn/user creation section of the program
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
   
      newUserButton= new JButton("New User");
      newUserButton.addActionListener(new NewUserListener());
      buttonPanel.add(newUserButton);
      buttonPanel.add(Box.createHorizontalGlue());
   
      logInButton= new JButton("Log In");
      logInButton.addActionListener(new LogInListener());
      buttonPanel.add(logInButton);
      buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
      
      logInFrame.add(buttonPanel);
      logInFrame.pack();
      logInFrame.setVisible(true);
   }
   
   private class NewUserListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new NewUserDialog();
      }
   }
   
   private class LogInListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
      
      }
   }
   
   private class NewUserDialog extends JDialog
   {
      private JDialog NewUserBox;
      private JPanel titlePanel;
      private JPanel fieldPanel;
      private JPanel buttonPanel;
      private JLabel titleLabel;
      private JLabel pwLabel2;
      private JPasswordField pwField2;
      private JButton createButton;
      private JButton backButton;
      
      public NewUserDialog()
      {
         NewUserBox= new JDialog(logInFrame, "New User", true);
         NewUserBox.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         NewUserBox.setResizable(false);
         NewUserBox.setLayout(new BoxLayout(NewUserBox.getContentPane(), BoxLayout.Y_AXIS));
      
         titlePanel= new JPanel();
         titlePanel.setLayout(new FlowLayout());
      
         titleLabel= new JLabel("New User Creation");
         titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
         titlePanel.add(titleLabel);
         NewUserBox.add(titlePanel);
      
         fieldPanel= new JPanel();
         fieldPanel.setLayout(new GridLayout(3,2));
      
         fieldPanel.add(uNLabel);
         fieldPanel.add(uNField);
         fieldPanel.add(pwLabel);
         fieldPanel.add(pwField);
         
         pwLabel2= new JLabel(" Confirm Password: ");
         fieldPanel.add(pwLabel2);
            
         pwField2= new JPasswordField();
         fieldPanel.add(pwField2);
         NewUserBox.add(fieldPanel);
      
         buttonPanel= new JPanel();
         buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
         buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
      
         createButton= new JButton("Create");
         createButton.addActionListener(new CreateListener());
         buttonPanel.add(createButton);
         buttonPanel.add(Box.createHorizontalGlue());
      
         backButton= new JButton("Back");
         backButton.addActionListener(new BackListener());
         buttonPanel.add(backButton);
         buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
      
         NewUserBox.add(buttonPanel);
         NewUserBox.pack();
         NewUserBox.setVisible(true);
      }
      
      private class CreateListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
         
         }
      }
   
      private class BackListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            NewUserBox.setVisible(false);
            NewUserBox.dispose();
         }
      }
   }
   
   public static void main(String args[])
   {
      new LogInUI();
   }
}