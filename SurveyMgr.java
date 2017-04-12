/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- SurveyMgr
Generates and handles survey questions
*/

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.lang.Integer;   

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
   
   public int getQuestionNum()
   {
      return(questions.length);
   }
   
   public void answerQuestion(int index, int choice, int importance)
   {
      questions[index].setAnswer(choice, importance);
      
      if(questions[index].getHasSubQuestions())
         questions[index+1]= questions[index].getSubQuestion(choice);
   }
   
   public void generateQuestions()
   {
      File questionData;
      Scanner dataIn;
      String tempText, tempText2;
      String[] tempChoices, tempChoices2;
      boolean hasSub;
      SurveyQuestion[] tempSubQuestions= null;
      
      try
      {
         questionData= new File(filename);
         dataIn= new Scanner(questionData);
         
         questions= new SurveyQuestion[Integer.parseInt(dataIn.nextLine())];
                  
         for(int i= 0;i<questions.length;i++)
         {
            tempText= dataIn.nextLine();
            tempChoices= new String[Integer.parseInt(dataIn.nextLine())];
            hasSub= Boolean.parseBoolean(dataIn.nextLine());
            
            if(!hasSub)
            {
               for(int j= 0;j<tempChoices.length;j++)
                  tempChoices[j]= dataIn.nextLine();
            }
            else
            {
               tempSubQuestions= new SurveyQuestion[tempChoices.length];
               
               for(int j=0;j<tempChoices.length;j++)
               {
                  tempChoices[j]= dataIn.nextLine();
                  tempText2= tempChoices[j];
                  tempChoices2= new String[Integer.parseInt(dataIn.nextLine())];
                  
                  for(int k=0;k<tempChoices2.length;k++)
                     tempChoices2[k]= dataIn.nextLine();
                  
                  tempSubQuestions[j]= new SurveyQuestion(tempText2, tempChoices2);   
               } 
            }      
               
            questions[i]= new SurveyQuestion(tempText, tempChoices);
            
            if(hasSub)
            {
               questions[i].setSubQuestions(tempSubQuestions); 
               i++;
            }     
         }
         
         dataIn.close();      
      }
      catch(FileNotFoundException e)
      {
         e.printStackTrace();
         System.exit(1);
      }     
   }
   
   public Result createResult()
   {
      int[] choices= new int[questions.length];
      int[] importances= new int[questions.length];
      
      for(int i=0;i<questions.length;i++)
      {
         choices[i]= questions[i].getChoice();
         importances[i]= questions[i].getImportance();
      }
      
      Result output= new Result(choices,importances);
      
      return(output);
   }   
   
   public static void main(String[] args) throws IOException
   {
      SurveyMgr example= new SurveyMgr("survey_questions.txt");
      example.generateQuestions();
      
   }
   
}
