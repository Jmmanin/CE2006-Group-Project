/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- ComparisonUI
User Interface for the results comparison section of the program
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Map;
import java.util.List;

public class ComparisonUI
{
   private JFrame theFrame;
   private JPanel leftPanel;
   private JPanel centerPanel;
   private JPanel rightPanel;
   private JLabel resultLabel1;
   private JTextArea resultArea1;
   private JLabel resultLabel2;
   private JTextArea resultArea2;
   private JButton saveButton;
   private JButton compareButton;
   private JButton newButton;
   private JButton logOutButton;
   private JButton closeButton;
   private JLabel cpLabel;
   
   private String username;
   private ServerMgr serverMgr;
   private Result result1;
   private Result result2;
   
   public ComparisonUI(String uN, ServerMgr sM, Result r1, Result r2)
   {
      username= uN;
      serverMgr= sM;
      result1= r1;
      result2= r2;
         
      theFrame= new JFrame("Compare Your Results");
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      theFrame.setLayout(new BoxLayout(theFrame.getContentPane(), BoxLayout.X_AXIS));
      theFrame.setResizable(false);
      theFrame.add(Box.createRigidArea(new Dimension(5,0)));
   
      leftPanel= new JPanel();
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      
      resultLabel1= new JLabel("Original");
      resultLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
      leftPanel.add(resultLabel1);
         
      resultArea1= new JTextArea("This is a sample result");
      resultArea1.append(result1.getRecommendedData(1));
      resultArea1.setPreferredSize(new Dimension(400, 450));
      resultArea1.setEditable(false);
      resultArea1.setLineWrap(true);
      resultArea1.setAlignmentX(Component.CENTER_ALIGNMENT);
      leftPanel.add(resultArea1);
      theFrame.add(leftPanel);
      theFrame.add(Box.createRigidArea(new Dimension(10,0)));
      
      centerPanel= new JPanel();
      centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
      resultLabel2= new JLabel("Comparison");
      resultLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
      centerPanel.add(resultLabel2);
         
      resultArea2= new JTextArea("This is a sample comparison result");
      resultArea2.append(result2.getRecommendedData(2));
      resultArea2.setPreferredSize(new Dimension(400, 450));
      resultArea2.setEditable(false);
      resultArea2.setLineWrap(true);
      resultArea2.setAlignmentX(Component.CENTER_ALIGNMENT);
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
            new SaveDialog();         
         }
         else if(e.getActionCommand().equals("compare"))
         {
            theFrame.setVisible(false);
            theFrame.dispose();
            new LoadDialog();
         }
         else if(e.getActionCommand().equals("new"))
         {
            theFrame.setVisible(false);
            theFrame.dispose();
            new SurveyUI(username, serverMgr);   
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
            try
            {
               serverMgr.saveSerial(username, result1);
            }
            catch(Exception e2)
            {
               e2.printStackTrace();
            }
            JOptionPane.showMessageDialog(null ,"Saved to server.", "Saved", JOptionPane.INFORMATION_MESSAGE);          
         }
         else if(e.getActionCommand().equals("local"))
         {
            serverMgr.saveLocal(username, result1);
            JOptionPane.showMessageDialog(null ,"Saved to local folder.", "Saved", JOptionPane.INFORMATION_MESSAGE);          
         } 
         else if(e.getActionCommand().equals("both"))
         {
            try
            {
               serverMgr.saveSerial(username, result1);
            }
            catch(Exception e2)
            {
               e2.printStackTrace();
            }   
            serverMgr.saveLocal(username, result1);
            JOptionPane.showMessageDialog(null ,"Saved to server and local folder", "Saved", JOptionPane.INFORMATION_MESSAGE);          
         }
      
         setVisible(false);
         dispose();
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
         super(theFrame, "Load Previous Results", true);
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
               theFrame.dispose();
               setVisible(false);
               dispose();
               try
               {
                  new ComparisonUI(username, serverMgr, result1, serverMgr.loadSerialRow(serverModel.get(serverList.getSelectedIndex())));
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
               theFrame.dispose();
               setVisible(false);
               dispose();
               new ComparisonUI(username, serverMgr, result1, serverMgr.loadLocal(localModel.get(localList.getSelectedIndex())));
            }
            else
               JOptionPane.showMessageDialog(this ,"Please select an option.", "Load Error", JOptionPane.ERROR_MESSAGE);          
         }
         else
         {
            setVisible(false);
            dispose();
            theFrame.setVisible(true);
         }   
      }
   }
   
   public static void main(String args[])
   {
      new ComparisonUI("test", new ServerMgr(), null, null);
   }
}