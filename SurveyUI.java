/*
Jeremy Manin
CE2006- Team Secret
Term Project- SurveyUI
INSERT DESCRIPTION
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class SurveyUI
{
   JFrame startFrame;
   JPanel leftPanel;
   JPanel rightPanel;
   ImageIcon splashIcon;
   JLabel splashLabel;
   JButton newButton;
   JButton loadButton;
   JLabel cpLabel;

   public SurveyUI()
   {         
      startFrame= new JFrame("GUI Test");
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setLayout(new BoxLayout(startFrame.getContentPane(), BoxLayout.X_AXIS));
      startFrame.setResizable(false);
         
      leftPanel= new JPanel();
      leftPanel.setLayout(new FlowLayout());
         
      splashIcon= new ImageIcon(getImageFile("TestImage.png"));   
      splashLabel= new JLabel(splashIcon);
      leftPanel.add(splashLabel);
            
      startFrame.add(leftPanel);
      startFrame.add(new JSeparator(SwingConstants.VERTICAL));
      
      rightPanel= new JPanel();
      rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
      
      newButton= new JButton("New");
      newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      newButton.addActionListener(new NewListener());
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      rightPanel.add(newButton);
   
      loadButton= new JButton("Load");
      loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      loadButton.addActionListener(new LoadListener());
      rightPanel.add(loadButton);
      rightPanel.add(Box.createVerticalGlue());
      
      cpLabel= new JLabel("(C) Copyright Info");
      cpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      rightPanel.add(cpLabel);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
   
      startFrame.add(rightPanel);
      startFrame.add(Box.createRigidArea(new Dimension(8,0)));
      startFrame.pack();
      startFrame.setVisible(true);
   }
      
   private BufferedImage getImageFile(String filename)
   {
      try
      {
         return((BufferedImage)ImageIO.read(SurveyUI.class.getResourceAsStream(filename)));
      }
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null ,"Error loading image resource.", "Load Error", JOptionPane.ERROR_MESSAGE);          
         System.exit(1);
      }
      
      return(null);
   }
   
   class NewListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JOptionPane.showMessageDialog(null ,"You clicked on the \"New\" button.", "Hello", JOptionPane.WARNING_MESSAGE);          
         splashIcon= new ImageIcon(getImageFile("TestImage_Inv.png")); 
         splashLabel.setIcon(splashIcon);
      }
   }
   
   class LoadListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JOptionPane.showMessageDialog(null ,"You clicked on the \"Load\" button.", "Hello", JOptionPane.WARNING_MESSAGE);          
      }
   }
   
   private class QuestionFrame extends JFrame
   {
      private JFrame questionFrame;
      private JTextField questionField;
      private JPanel optionPanel;
      private ButtonGroup optionGroup;
      private JRadioButton[] options;
      private JButton chooseButton;
      
      public QuestionFrame()
      {
        
      }
      
      private class OptionListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
         
         }
      }
      
      private class ChooseListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
         
         }
      }
   }
            
   public static void main(String args[])
   {
      new SurveyUI();
   }   
}