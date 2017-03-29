/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- apiMgr
Manages program elements that use APIs
*/

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ApiMgr
{
   public ApiMgr()
   {
   
   }
   
   public void processAnswers()
   {
   
   }
   
   public void createQuestion()
   {
   
   }
   
   public void getQuestion()
   {
   
   }
   
   public void getGradEmploymentSurvey() throws IOException {	
   
      final String FILENAME = "grademploymentsurvey.txt"; /* save to txt */
   
      Runtime.getRuntime()
         	.exec("python GradEmploymentSurvey.py"); /* runs api */
   
      BufferedReader br = null;
      FileReader fr = null;
   
      try {
      
         fr = new FileReader(FILENAME);
         br = new BufferedReader(fr);
      
         String sCurrentLine;
      
         br = new BufferedReader(new FileReader(FILENAME));
      
         while ((sCurrentLine = br.readLine()) != null) {
            System.out.println(sCurrentLine);
         }
      } 
      catch (IOException e) {
      
         e.printStackTrace();
      
      } 
      finally {
      
         try {
         
            if (br != null)
               br.close();
         
            if (fr != null)
               fr.close();
         
         } 
         catch (IOException ex) {
         
            ex.printStackTrace();
         
         }
      
      }
   
   }
	

   public void getGradTypeOfCourse() throws IOException {	
   
      final String FILENAME = "gradtypeofcourse.txt"; /* save to txt */
   
      Runtime.getRuntime()
         	.exec("python GradeTypeOfCourse.py"); /* runs api */
   
      BufferedReader br = null;
      FileReader fr = null;
   
      try {
      
         fr = new FileReader(FILENAME);
         br = new BufferedReader(fr);
      
         String sCurrentLine;
      
         br = new BufferedReader(new FileReader(FILENAME));
      
         while ((sCurrentLine = br.readLine()) != null) {
            System.out.println(sCurrentLine);
         }
      } 
      catch (IOException e) {
      
         e.printStackTrace();
      
      } 
      finally {
      
         try {
         
            if (br != null)
               br.close();
         
            if (fr != null)
               fr.close();
         
         } 
         catch (IOException ex) {
         
            ex.printStackTrace();
         
         }
      
      }
   
   }
	  
   public static void main(String[] args) throws IOException
   {
      ApiMgr exampleMgr= new ApiMgr();
      exampleMgr.getGradEmploymentSurvey();
   }
}