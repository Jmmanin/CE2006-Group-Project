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
   
   public void processApiData() throws IOException
   {
		BufferedReader br = new BufferedReader(new FileReader("grademploymentsurvey.txt"));
		br.skip(754);//skip useless stuff
		String data = br.readLine();// read from file
		data = data.replaceAll("\\{", "");//replace unwanted stuff
		data = data.replaceAll("}", "");
		String[] dataArray = data.split(",");//split data
		String[] records = new String[dataArray.length-12];//12 unnecessary array slots
		String[] finalrecord = new String[dataArray.length-12];//same
		String temp = "";
		String temp1 = "";
		int recordNum = 0;
		for(int i=0;i<dataArray.length-3;i++){// because last 3 records are not relevant
			temp = dataArray[i];
			temp = temp.replaceAll("\"", "");//more replacing of unwanted stuff
			temp = temp.replaceAll("]", "");
			temp = temp.trim();
			if(temp.equals("school: College of Humanities")|| temp.equals("degree: Art")){
				temp1 = temp;
				continue;
			}
			else if(temp.equals("Arts & Social Sciences")||temp.equals("Design & Media")){
				temp = temp1 + ","+ temp;
			}
			records[recordNum] = temp;
			recordNum++;
		}
		for(int i=0; i<records.length;i++){
			temp = records[i];
			dataArray = temp.split(":");
			finalrecord[i] = dataArray[1].trim();
			System.out.println(finalrecord[i]);
		}
		br.close();
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