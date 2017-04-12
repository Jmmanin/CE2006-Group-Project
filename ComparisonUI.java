/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- ComparisonUI
User Interface for the results comparison section of the program
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ComparisonUI
{
   private JFrame theFrame;
   private JPanel leftPanel;
   private JPanel centerPanel;
   private JPanel rightPanel;
   private JTextArea resultArea1;
   private JTextArea resultArea2;
   private JButton saveButton;
   private JButton compareButton;
   private JButton newButton;
   private JButton logOutButton;
   private JButton closeButton;
   private JLabel cpLabel;
   
   public ComparisonUI()
   {
      theFrame= new JFrame("ResultUI Test");
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      theFrame.setLayout(new BoxLayout(theFrame.getContentPane(), BoxLayout.X_AXIS));
      theFrame.setResizable(false);
         
      leftPanel= new JPanel();
      leftPanel.setLayout(new FlowLayout());
         
      resultArea1= new JTextArea("This is a sample result");
      resultArea1.setPreferredSize(new Dimension(350, 450));
      resultArea1.setEditable(false);
      resultArea1.setLineWrap(true);
      leftPanel.add(resultArea1);
      theFrame.add(leftPanel);
      
      centerPanel= new JPanel();
      centerPanel.setLayout(new FlowLayout());
         
      resultArea2= new JTextArea("This is a sample comparison result");
      resultArea2.setPreferredSize(new Dimension(350, 450));
      resultArea2.setEditable(false);
      resultArea2.setLineWrap(true);
      centerPanel.add(resultArea2);
      theFrame.add(centerPanel);
      theFrame.add(new JSeparator(SwingConstants.VERTICAL));
      
      rightPanel= new JPanel();
      rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
      
      ResultListener theListener= new ResultListener();
      
      saveButton= new JButton("Save");
      saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      saveButton.setActionCommand("save");
      saveButton.addActionListener(theListener);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      rightPanel.add(saveButton);
   
      compareButton= new JButton("Compare");
      compareButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      compareButton.setActionCommand("compare");
      compareButton.addActionListener(theListener);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      rightPanel.add(compareButton);
      
      newButton= new JButton("New");
      newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      newButton.setActionCommand("new");
      newButton.addActionListener(theListener);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      rightPanel.add(newButton);
      
      logOutButton= new JButton("Log Out");
      logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      logOutButton.setActionCommand("logout");
      logOutButton.addActionListener(theListener);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      rightPanel.add(logOutButton);
   
      closeButton= new JButton("Close");
      closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      closeButton.setActionCommand("close");
      closeButton.addActionListener(theListener);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      rightPanel.add(closeButton);
      rightPanel.add(Box.createVerticalGlue());
      
      cpLabel= new JLabel("<html><center>CE2006 Term Project<br>Team Secret<br>S2 2017</center></html>");
      cpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      rightPanel.add(cpLabel);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
   
      theFrame.add(rightPanel);
      theFrame.add(Box.createRigidArea(new Dimension(8,0)));
      theFrame.pack();
      theFrame.setVisible(true);
   }

   private class ResultListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getActionCommand().equals("save"))
         {
            resultArea1.append(" *save clicked*");
            new SaveDialog();         
         }
         else if(e.getActionCommand().equals("compare"))
         {
            resultArea1.append(" *compare clicked*");         
         }
         else if(e.getActionCommand().equals("new"))
         {
            resultArea1.append(" *new clicked*");         
         }
         else if(e.getActionCommand().equals("logout"))
         {
            theFrame.setVisible(false);
            theFrame.dispose();
            new LogInUI();
         }
         else
         {
            System.exit(0);
         }
      }
   }
   
   private class SaveDialog extends JDialog implements ActionListener
   {
      private JPanel titlePanel;
      private JPanel buttonPanel;
      private JLabel titleLabel;
      private JButton serverButton;
      private JButton localButton;
      private JButton bothButton;
      private JButton cancelButton;
      
      public SaveDialog()
      {
         super(theFrame, "Save?", true);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setResizable(false);
         setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
         
         titlePanel= new JPanel();
         titlePanel.setLayout(new FlowLayout());
      
         titleLabel= new JLabel("Save Results?");
         titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
         titlePanel.add(titleLabel);
         add(titlePanel);
         
         buttonPanel= new JPanel();
         buttonPanel.setLayout(new GridLayout(2,2));
         
         serverButton= new JButton("Save To Server");
         serverButton.setActionCommand("server");
         serverButton.addActionListener(this);
         buttonPanel.add(serverButton);
      
         localButton= new JButton("Save To Local");
         localButton.setActionCommand("local");
         localButton.addActionListener(this);
         buttonPanel.add(localButton);
      
         bothButton= new JButton("Save To Both");
         bothButton.setActionCommand("both");
         bothButton.addActionListener(this);
         buttonPanel.add(bothButton);
      
         cancelButton= new JButton("Cancel");
         cancelButton.setActionCommand("cancel");
         cancelButton.addActionListener(this);
         buttonPanel.add(cancelButton);
         
         add(buttonPanel);
         pack();
         setVisible(true);
      }
      
      public void actionPerformed(ActionEvent e)
      {
         if(e.getActionCommand().equals("server"))
         {
            JOptionPane.showMessageDialog(null ,"You clicked on the \"Server\" button.", "Hello", JOptionPane.WARNING_MESSAGE);          
         }
         else if(e.getActionCommand().equals("local"))
         {
            JOptionPane.showMessageDialog(null ,"You clicked on the \"Local\" button.", "Hello", JOptionPane.WARNING_MESSAGE);          
         } 
         else if(e.getActionCommand().equals("both"))
         {
            JOptionPane.showMessageDialog(null ,"You clicked on the \"Both\" button.", "Hello", JOptionPane.WARNING_MESSAGE);          
         }
      
         setVisible(false);
         dispose();
      }
   }

   
   public static void main(String args[])
   {
      new ComparisonUI();
   }
}