/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- SurveyMgr
Generates and handles survey questions
*/

import java.io.*;
import java.util.Scanner;

public class SurveyMgr
{
   private String filename;
   private SurveyQuestion[] questions;
   
   public SurveyMgr(String fn)
   {
      filename= fn;
      questions= null;
   }
   
   public SurveyQuestion getQuestion(int index)
   {
      return(questions[index]);
   }
   
   public void generateQuestions()
   {
      File questionData;
      Scanner dataIn;
      int count= 0;
      String tempText;
      String[] tempChoices;
      
      try
      {
         questionData= new File(filename);
         dataIn= new Scanner(questionData);
         
         questions= new SurveyQuestion[Integer.parseInt(dataIn.nextLine())];
                  
         for(int i= 0;i<questions.length;i++)
         {
            tempText= dataIn.nextLine();
            tempChoices= new String[Integer.parseInt(dataIn.nextLine())];
            
            for(int j= 0;j<tempChoices.length;j++)
               tempChoices[j]= dataIn.nextLine();
               
            questions[i]= new SurveyQuestion(tempText, tempChoices);   
         }       
      }
      catch(FileNotFoundException e)
      {
         e.printStackTrace();
         System.exit(1);
      }     
   }
   
   public static void main(String[] args) throws IOException
   {
      SurveyMgr example= new SurveyMgr("survey_questions.txt");
      example.generateQuestions();
   }
}