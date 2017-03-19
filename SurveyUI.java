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
         /*JOptionPane.showMessageDialog(null ,"You clicked on the \"New\" button.", "Hello", JOptionPane.WARNING_MESSAGE);          
         splashIcon= new ImageIcon(getImageFile("TestImage_Inv.png")); 
         splashLabel.setIcon(splashIcon);*/
         
         QuestionFrame testQ= new QuestionFrame();
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
      private JPanel questionPanel;
      private JPanel optionPanel;
      private JTextArea questionArea;
      private ButtonGroup optionGroup;
      private JRadioButton[] options;
      private JButton chooseButton;
      
      public QuestionFrame()
      {
         questionFrame= new JFrame("Question Test");
         questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         questionFrame.setLayout(new BoxLayout(questionFrame.getContentPane(), BoxLayout.X_AXIS));
         questionFrame.setResizable(false);
         
         questionPanel= new JPanel();
         questionPanel.setLayout(new FlowLayout());
         
         questionArea= new JTextArea("This is a sample question's text");
         questionArea.setPreferredSize(new Dimension(550, 450));
         questionArea.setEditable(false);
         questionPanel.add(questionArea);
         
         questionFrame.add(questionPanel);
         questionFrame.add(new JSeparator(SwingConstants.VERTICAL));
         
         optionPanel= new JPanel();
         optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
         optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
         
         JRadioButton[] options= new JRadioButton[3];
         options[0]= new JRadioButton("Option 1");
         options[0].setSelected(true);
         options[1]= new JRadioButton("Option 2");
         options[2]= new JRadioButton("Option 3");

         optionGroup= new ButtonGroup();
         for(int i=0; i<options.length; i++)
         {
            options[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            optionGroup.add(options[i]);
            optionPanel.add(options[i]);
         }
         
         chooseButton= new JButton("Choose");
         chooseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         optionPanel.add(Box.createRigidArea(new Dimension(0,15)));
         optionPanel.add(chooseButton);
         
         optionPanel.add(Box.createVerticalGlue());
         optionPanel.add(cpLabel);
         optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
         
         questionFrame.add(optionPanel);
         questionFrame.add(Box.createRigidArea(new Dimension(8,0)));
         questionFrame.pack();
         questionFrame.setVisible(true);
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