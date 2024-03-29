/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- SurveyUI
User Interface for the survey section of the program
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.io.IOException;

public class SurveyUI
{
   private JFrame startFrame;
   private JPanel leftPanel;
   private JPanel rightPanel;
   private ImageIcon splashIcon;
   private JLabel splashLabel;
   private JButton newButton;
   private JButton loadButton;
   private JButton logOutButton;
   private JLabel cpLabel;
   
   private String username;
   private ServerMgr serverMgr;
   private SurveyMgr surveyMgr;

   public SurveyUI(String uN, ServerMgr sM)
   {         
      startFrame= new JFrame("Dream Course Finder");
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setLayout(new BoxLayout(startFrame.getContentPane(), BoxLayout.X_AXIS));
      startFrame.setResizable(false);
         
      leftPanel= new JPanel();
      leftPanel.setLayout(new FlowLayout());
         
      splashIcon= new ImageIcon(getImageFile("splash.png"));   
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
      
      logOutButton= new JButton("Log Out");
      logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      logOutButton.setActionCommand("logout");
      logOutButton.addActionListener(theListener);
      rightPanel.add(logOutButton);
      rightPanel.add(Box.createVerticalGlue());
   
      cpLabel= new JLabel("<html><center>CE2006 Term Project<br>Team Secret<br>S2 2017</center></html>");
      cpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      rightPanel.add(cpLabel);
      rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
   
      startFrame.add(rightPanel);
      startFrame.add(Box.createRigidArea(new Dimension(8,0)));
      startFrame.pack();
      startFrame.setVisible(true);
      
      username= uN;
      serverMgr= sM;
      surveyMgr= new SurveyMgr("survey_questions.txt");
      surveyMgr.generateQuestions();
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
            startFrame.setVisible(false);
            startFrame.dispose();
            new QuestionFrame(0);
         }
         else if(e.getActionCommand().equals("logout"))
         {
            startFrame.setVisible(false);
            startFrame.dispose();
            new LogInUI();
         }
         else
         {
            startFrame.setVisible(false);
            startFrame.dispose();
            new LoadDialog();
         }
      }
   }
    
   private class LoadDialog extends JDialog implements ActionListener
   {   
      private JPanel leftPanel;
      private JPanel rightPanel;
      private JLabel serverLabel;
      private DefaultListModel<String> serverModel;
      private JList<String> serverList;
      private JScrollPane serverScroller;
      private JLabel localLabel;
      private DefaultListModel<String> localModel;
      private JList<String> localList;
      private JScrollPane localScroller;
      private JButton loadServerButton;
      private JButton loadLocalButton;
      private JButton backButton;
      private JLabel cpLabel2;
      
      private List<Map<String, Object>> results;
   
      public LoadDialog()
      {         
         super(startFrame, "Load Previous Results", true);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
         setResizable(false);
         
         leftPanel= new JPanel();
         leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
         leftPanel.add(Box.createRigidArea(new Dimension(0,4)));
          
         serverLabel= new JLabel("Server Results"); 
         serverLabel.setFont(new Font("Arial", Font.BOLD, 12));
         serverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         leftPanel.add(serverLabel);
                  
         try
         {
            results= serverMgr.loadTablefromServer("serialobjects");
         }
         catch(Exception e)
         {
            e.printStackTrace();
            System.exit(1);
         }
         
         serverModel= new DefaultListModel<String>();
         for(int i=0;i<results.size();i++)
            serverModel.addElement((String)results.get(i).get("title"));
      
         serverList= new JList<String>(serverModel);
         serverList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         serverList.setLayoutOrientation(JList.VERTICAL);
         serverList.setVisibleRowCount(-1);
         
         serverScroller = new JScrollPane(serverList);
         serverScroller.setPreferredSize(new Dimension(550, 234));
         leftPanel.add(serverScroller);
         leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
                  
         localLabel= new JLabel("Local Results"); 
         localLabel.setFont(new Font("Arial", Font.BOLD, 12));
         localLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         leftPanel.add(localLabel);
                  
         localModel= new DefaultListModel<String>();
         String[] localResults= serverMgr.loadLocalList();
         for(int i=0;i<localResults.length;i++)
            localModel.addElement(localResults[i]);
      
         localList= new JList<String>(localModel);
         localList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         localList.setLayoutOrientation(JList.VERTICAL);
         localList.setVisibleRowCount(-1);
         
         localScroller = new JScrollPane(localList);
         localScroller.setPreferredSize(new Dimension(550, 234));
         leftPanel.add(localScroller);
         leftPanel.add(Box.createRigidArea(new Dimension(0,4)));
            
         add(Box.createRigidArea(new Dimension(8,0)));
         add(leftPanel);
         add(new JSeparator(SwingConstants.VERTICAL));
      
         rightPanel= new JPanel();
         rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
                  
         loadServerButton= new JButton("Load Server");
         loadServerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         loadServerButton.setActionCommand("loadserver");
         loadServerButton.addActionListener(this);
         rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
         rightPanel.add(loadServerButton);
      
         loadLocalButton= new JButton("Load Local");
         loadLocalButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         loadLocalButton.setActionCommand("loadlocal");
         loadLocalButton.addActionListener(this);
         rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
         rightPanel.add(loadLocalButton);
      
         backButton= new JButton("Back");
         backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         backButton.setActionCommand("back");
         backButton.addActionListener(this);
         rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
         rightPanel.add(backButton);
         rightPanel.add(Box.createVerticalGlue());
      
         cpLabel2= new JLabel("<html><center>CE2006 Term Project<br>Team Secret<br>S2 2017</center></html>");
         cpLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
         rightPanel.add(cpLabel2);
         rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
      
         add(rightPanel);
         add(Box.createRigidArea(new Dimension(8,0)));
         pack();
         setVisible(true);
      }
      
      public void actionPerformed(ActionEvent e)
      {
         if(e.getActionCommand().equals("loadserver"))
         {
            if(serverList.getSelectedIndex()>-1)
            {
               startFrame.dispose();
               setVisible(false);
               dispose();
               try
               {
                  new ResultUI(username, serverMgr, serverMgr.loadSerialRow(serverModel.get(serverList.getSelectedIndex())));
               }
               catch(Exception e2)
               {
                  e2.printStackTrace();
               }
            }
            else
               JOptionPane.showMessageDialog(this ,"Please select an option.", "Load Error", JOptionPane.ERROR_MESSAGE);          
         }
         else if(e.getActionCommand().equals("loadlocal"))
         {
            if(localList.getSelectedIndex()>-1)
            {
               startFrame.dispose();
               setVisible(false);
               dispose();
               new ResultUI(username, serverMgr, serverMgr.loadLocal(localModel.get(localList.getSelectedIndex())));
            }
            else
               JOptionPane.showMessageDialog(this ,"Please select an option.", "Load Error", JOptionPane.ERROR_MESSAGE);          
         }
         else
         {
            setVisible(false);
            dispose();
            startFrame.setVisible(true);
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
            
      private int currQuestion;      
            
      public QuestionFrame(int cQ)
      {         
         currQuestion= cQ;
      
         questionFrame= new JFrame("Question Test");
         questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         questionFrame.setLayout(new BoxLayout(questionFrame.getContentPane(), BoxLayout.X_AXIS));
         questionFrame.setResizable(false);
         
         questionPanel= new JPanel();
         questionPanel.setLayout(new FlowLayout());
         
         questionArea= new JTextArea(surveyMgr.getQuestion(currQuestion).getQuestionText());
         questionArea.setPreferredSize(new Dimension(550, 450));
         questionArea.setEditable(false);
         questionArea.setLineWrap(true);
         questionPanel.add(questionArea);
         
         questionFrame.add(questionPanel);
         questionFrame.add(new JSeparator(SwingConstants.VERTICAL));
         
         optionPanel= new JPanel();
         optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
         optionPanel.add(Box.createRigidArea(new Dimension(0,5)));
      
         options= new JRadioButton[surveyMgr.getQuestion(currQuestion).getOptionsNum()];
         for(int i= 0;i<options.length;i++)
            options[i]= new JRadioButton(surveyMgr.getQuestion(currQuestion).getOption(i));
         
         options[0].setSelected(true);            
      
         optionGroup= new ButtonGroup();
         for(int i=0; i<options.length; i++)
         {
            optionGroup.add(options[i]);
            optionPanel.add(options[i]);
         }
      
         optionPanel.add(Box.createRigidArea(new Dimension(0,15)));
      
         importance= new JRadioButton[3];
         importance[0]= new JRadioButton("Very Important");
         importance[0].setSelected(true);
         importance[1]= new JRadioButton("Important");
         importance[2]= new JRadioButton("Not Important");
      
         importanceGroup= new ButtonGroup();
         for(int i=0; i<importance.length; i++)
         {
            importanceGroup.add(importance[i]);
            optionPanel.add(importance[i]);
         }
                  
         chooseButton= new JButton("Choose");
         chooseButton.setActionCommand("choose");
         chooseButton.addActionListener(this);
         optionPanel.add(Box.createRigidArea(new Dimension(0,15)));
         optionPanel.add(chooseButton);
         optionPanel.add(Box.createVerticalGlue());
                           
         optionPanel.add(Box.createRigidArea(new Dimension(0,10)));         
         questionFrame.add(optionPanel);
         questionFrame.add(Box.createRigidArea(new Dimension(8,0)));
         questionFrame.pack();
         questionFrame.setVisible(true);         
      }
      
      public void actionPerformed(ActionEvent e)
      {
         int optionPicked= -1;
         int importancePicked= -1;
                           
         if(e.getActionCommand().equals("choose"))
         {
            for(int i= 0;i<options.length;i++)
            {
               if(options[i].isSelected())
               {
                  optionPicked= i;
                  break;
               }
            } 
            
            for(int i= 0;i<importance.length;i++)
            {
               if(importance[i].isSelected())
               {
                  importancePicked= i;
                  break;
               }
            }
            
            questionFrame.setVisible(false);
            questionFrame.dispose();
                       
            surveyMgr.answerQuestion(currQuestion,optionPicked,importancePicked);
            currQuestion++;
            
            if(currQuestion<surveyMgr.getQuestionNum())
            {
               new QuestionFrame(currQuestion);
            }
            else
            {
               JOptionPane.showMessageDialog(null ,"Survey completed!\nPress ok to proceed\nto the results screen.", "All Done", JOptionPane.INFORMATION_MESSAGE);          
               Result theResult= surveyMgr.createResult();
               ApiMgr apiMgr= new ApiMgr();
               apiMgr.processRawChoices(theResult);
               try
               {
                  apiMgr.courseFinder(theResult);
               }
               catch(IOException e2)
               {
                  e2.printStackTrace();
               }
               new ResultUI(username, serverMgr, theResult);
            }   
         }
         else
         {
         
         }
      }
   }
            
   public static void main(String args[])
   {
      new SurveyUI("test", new ServerMgr());
   }   
}