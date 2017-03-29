/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- ResultUI
User Interface for presenting results to the user
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ResultUI
{
   private JFrame theFrame;
   private JPanel leftPanel;
   private JPanel rightPanel;
   private JTextArea resultArea;
   private JButton saveButton;
   private JButton compareButton;
   private JButton newButton;
   private JButton closeButton;
   private JLabel cpLabel;

   public ResultUI()
   {
      theFrame= new JFrame("ResultUI Test");
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      theFrame.setLayout(new BoxLayout(theFrame.getContentPane(), BoxLayout.X_AXIS));
      theFrame.setResizable(false);
         
      leftPanel= new JPanel();
      leftPanel.setLayout(new FlowLayout());
         
      resultArea= new JTextArea("This is a sample result");
      resultArea.setPreferredSize(new Dimension(550, 450));
      resultArea.setEditable(false);
      resultArea.setLineWrap(true);
      leftPanel.add(resultArea);
            
      theFrame.add(leftPanel);
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
            resultArea.append(" *save clicked*");
            new SaveDialog();         
         }
         else if(e.getActionCommand().equals("compare"))
         {
            resultArea.append(" *compare clicked*");         
         }
         else if(e.getActionCommand().equals("new"))
         {
            resultArea.append(" *new clicked*");         
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
      new ResultUI();
   }
}