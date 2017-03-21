/*
Jeremy Manin
CE2006- Team Secret
Term Project- SurveyUI
User Interface for the survey section of the program
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class SurveyUI
{
   private JFrame startFrame;
   private JPanel leftPanel;
   private JPanel rightPanel;
   private ImageIcon splashIcon;
   private JLabel splashLabel;
   private JButton newButton;
   private JButton loadButton;
   private JLabel cpLabel;

   public SurveyUI()
   {         
      startFrame= new JFrame("SurveyUI Test");
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
      
      SurveyListener theListener= new SurveyListener();
      
      newButton= new JButton("New");
      newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      newButton.setActionCommand("new");
      newButton.addActionListener(theListener);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      rightPanel.add(newButton);
   
      loadButton= new JButton("Load");
      loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      loadButton.setActionCommand("load");
      loadButton.addActionListener(theListener);
      rightPanel.add(loadButton);
      rightPanel.add(Box.createVerticalGlue());
      
      cpLabel= new JLabel("<html><center>CE2006 Term Project<br>Team Secret<br>S2 2017</center></html>");
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
   
   private class SurveyListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getActionCommand().equals("new"))
         {
            QuestionFrame testQ= new QuestionFrame();
         }
         else
         {
            new LoadDialog();
         }
      }
   }
    
   private class LoadDialog extends JDialog implements ActionListener
   {   
      private JDialog loadDialog;
      private JPanel leftPanel;
      private JPanel rightPanel;
      private JTextArea serverArea;
      private JTextArea localArea;
      private JButton loadButton;
      private JButton backButton;
   
      public LoadDialog()
      {
         loadDialog= new JDialog(startFrame, "Load Previous Results", true);
         loadDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         loadDialog.setLayout(new BoxLayout(loadDialog.getContentPane(), BoxLayout.X_AXIS));
         loadDialog.setResizable(false);
         
         leftPanel= new JPanel();
         leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
         leftPanel.add(Box.createRigidArea(new Dimension(0,4)));
         
         serverArea= new JTextArea("Sample Server Results");
         serverArea.setPreferredSize(new Dimension(550, 250));
         serverArea.setEditable(false);
         serverArea.setLineWrap(true);
         leftPanel.add(serverArea);
         leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
         
         localArea= new JTextArea("Sample Local Results");
         localArea.setPreferredSize(new Dimension(550, 250));
         localArea.setEditable(false);
         localArea.setLineWrap(true);
         leftPanel.add(localArea);
         leftPanel.add(Box.createRigidArea(new Dimension(0,4)));
            
         loadDialog.add(Box.createRigidArea(new Dimension(8,0)));
         loadDialog.add(leftPanel);
         loadDialog.add(new JSeparator(SwingConstants.VERTICAL));
      
         rightPanel= new JPanel();
         rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
                  
         loadButton= new JButton("Load");
         loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         loadButton.setActionCommand("load");
         loadButton.addActionListener(this);
         rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
         rightPanel.add(loadButton);
      
         backButton= new JButton("Back");
         backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         backButton.setActionCommand("back");
         backButton.addActionListener(this);
         rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
         rightPanel.add(backButton);
         rightPanel.add(Box.createVerticalGlue());
      
         rightPanel.add(cpLabel);
         rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      
         loadDialog.add(rightPanel);
         loadDialog.add(Box.createRigidArea(new Dimension(8,0)));
         loadDialog.pack();
         loadDialog.setVisible(true);
      }
      
      public void actionPerformed(ActionEvent e)
      {
         if(e.getActionCommand().equals("load"))
         {
            JOptionPane.showMessageDialog(null ,"You clicked on the \"Load\" button.", "Hello", JOptionPane.WARNING_MESSAGE);          
         }
         else
         {
            loadDialog.setVisible(false);
            loadDialog.dispose();
         }
      }
   }
      
   private class QuestionFrame extends JFrame implements ActionListener
   {
      private JFrame questionFrame;
      private JPanel questionPanel;
      private JPanel optionPanel;
      private JTextArea questionArea;
      private ButtonGroup optionGroup;
      private JRadioButton[] options;
      private ButtonGroup importanceGroup;
      private JRadioButton[] importance;
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
         questionArea.setLineWrap(true);
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
      
         optionPanel.add(Box.createRigidArea(new Dimension(0,15)));
      
         JRadioButton[] importance= new JRadioButton[3];
         importance[0]= new JRadioButton("Very Important");
         importance[0].setSelected(true);
         importance[1]= new JRadioButton("Important");
         importance[2]= new JRadioButton("Not Important");
      
         importanceGroup= new ButtonGroup();
         for(int i=0; i<importance.length; i++)
         {
            importance[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            importanceGroup.add(importance[i]);
            optionPanel.add(importance[i]);
         }
         
         chooseButton= new JButton("Choose");
         chooseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         chooseButton.setActionCommand("choose");
         chooseButton.addActionListener(this);
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
      
      public void actionPerformed(ActionEvent e)
      {
         if(e.getActionCommand().equals("choose"))
         {
            JOptionPane.showMessageDialog(null ,"You clicked on the \"Choose\" button.", "Hello", JOptionPane.WARNING_MESSAGE);          
         }
         else
         {
         
         }
      }
   }
            
   public static void main(String args[])
   {
      new SurveyUI();
   }   
}