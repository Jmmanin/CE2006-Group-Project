/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- ApiMgr
Handles operations that involve APIs
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
	 
    
   public void processApiData() throws IOException {
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
   
   public void processRawAnswers(Result r){
      String[] processed = new String[r.getQuestionNum()-1];
      String course, salary, location, employRate, workHours, workEnv, courseImpt, salaryImpt, employRateImpt, locationImpt, workHoursImpt, workEnvImpt;
         
       /* this if branch checks for major*/
      switch (choices[0]) {
         case 0:
           // engineering
            switch (choices[1]) {
               case 0:
                   // electronics or mechanics
                  course = "Electronic/Mechanical Engineering";
                  break;
               case 1:
                   // Science
                  course  = "Engineering Science";
                  break;
               case 2:
                   // Information
                  course = "Information Engineering";
                  break;
               default:
                   // Anything
                  course = "Engineering";
                  break;
            }
            break;
         case 1:
           // computing
            switch (choices[1]) {
               case 0:
                   // hardware
                  course = "Computer Engineering";
                  break;
               case 1:
                   // software
                  course = "Computer Science";
                  break;
               default:
                   //both
                  course = "Computing";
                  break;
            }
            break;
         case 2:
           // accountancy and business
            switch (choices[1]) {
               case 0:
                   // mathematics
                  course = "Business Mathematics";
                  break;
               case 1:
                   // Operations/planning
                  course = "Operations";
                  break;
               default:
                   // both
                  course = "Accountancy and Business";
                  break;
            }
            break;
         case 3:
               // art design media
            switch (choices[1]) {
               case 0:
                       // animation
                  course = "Animation";
                  break;
               case 1:
                       // photography
                  course = "Photography";
                  break;
               case 2:
                       // design/innovation
                  course = "Design";
                  break;
               default:
                       // anything
                  course = "Art";
                  break;
            }
            break;
         case 4:
               // comms studies
            course = "Communications Studies";
            break;
         case 5:
               // sports
            course = "Sports";
            break;
         case 6:
           // health
            switch (choices[1]) {
               case 0:
                   // medicine
                  course = "Medicine";
                  break;
               case 1:
                   // pharmacy
                  course = "Pharmacy";
                  break;
               case 2:
                   // dentistry
                  course = "Dentistry";
                  break;
               case 3:
                   // nursing
                  course = "Nursing";
                  break;
               default:
                   // anything
                  course = "Health";
                  break;
            }
            break;
         case 7:
           // humanities
            switch (choices[1]) {
               case 0:
                   // language studies
                  course = "Language Studies";
                  break;
               case 1:
                   // society
                  course = "Society";
                  break;
               case 2:
                   // history
                  course = "History";
                  break;
               default:
                   // anything
                  course = "Humanities";
                  break;
            }
            break;
         case 8:
               // language
            course = "Language";
            break;
         case 9:
           // science
            switch (choices[1]) {
               case 0:
                   // biology
                  course = "Biology";
                  break;
               case 1:
                   // chemistry
                  course = "Chemistry";
                  break;
               case 2:
                   // physics
                  course = "Physics";
                  break;
               case 3:
                   // food
                  course = "Food Science";
                  break;
               case 4:
                   // data
                  course = "Data Science";
                  break;
               default:
                   // anything
                  course = "Science";
                  break;
            }
            break;
         case 10:
               // law
            course = "Law";
            break;
         case 11:
           // mathematics
            switch (choices[1]) {
               case 0:
                   // statistics
                  course = "Statistics";
                  break;
               case 1:
                   // finance
                  course = "Finance";
                  break;
               default:
                   // anything
                  course = "Mathematics";
                  break;
            }
            break;
         default:
               // music
            course = "Music";
            break;
      }
         
       /* this if branch checks for salary*/
      switch (choices[2]) {
         case 0:
               // >= 3k
            salary = "3000";
            break;
         case 1:
               // >= 3.5k
            salary = "3500";
            break;
         case 2:
               // >= 4k
            salary = "4000";
            break;
         case 3:
               // >= 4.5k
            salary = "4500";
            break;
         case 4:
               // >4.5k
            salary = "5000";
            break;
         default:
               // anything
            salary = "0";
            break;
      }
      
       /* this branch checks for rate of employment */
      switch (choices[3]) {
         case 0:
               // >= 70%
            employRate = "70";
            break;
         case 1:
               // >= 75%
            employRate = "75";
            break;
         case 2:
               // >= 80%
            employRate = "80";
            break;
         case 3:
               // >= 85%
            employRate = "85";
            break;
         default:
               // anything
            employRate = "0";
            break;
      }
      
       /* this branch checks for location */
      switch (choices[4]) {
         case 0:
               // south west
            location = "South West";
            break;
         case 1:
               // central
            location = "central";
            break;
         default:
               // anywhere
            location = "";
            break;
      }
      
      /* this branch checks for working hours */
      switch (choices[5]){ 
           // flexible
         case 0:
            workHours = "Flexible";
            break;
         default: 
               // fixed
            workHours = "Fixed";
            break;
      }
      
       /* this branch checks for working environment */
      switch (choices[6]) {
         case 0:
               // office job
            workEnv = "Office";
            break;
         case 1:
               // hands-on
            workEnv = "Hands on";
            break;
         case 2:
               // backend
            workEnv = "Backend";
            break;
         case 3:
               // frontend
            workEnv = "Frontend";
            break;
         case 4:
               // fast paced
            workEnv = "Fast Paced";
            break;
         case 5:
               // laboratory
            workEnv = "Laboratory";
            break;
         default:
               // outfield
            workEnv = "Outfield";
            break;
      }
    
      processed[0]= course;
      processed[1]= salary;
      processed[2]= employRate;
      processed[3]= location;
      processed[4]= workHours;
      processed[5]= workEnv;
         
      r.setProcessedAnswers(processed);
   }
   
   public void weighAnswers(Result r){
      String highest = "";
      String[] priority = new String[r.getQuestionNum()];
       //String highest2 = ""; commented out portion for getting second weightage
      int max = 0;
      int temp;
       //int max2 = 0;
      for(int i = 0; i<priority.length;i++){
         if(i%2 == 1){
            temp = Integer.parseInt(r.getImportance(i));
            if(temp > max){
               max = temp;
               highest = Integer.toString(i);
                //max2 = max;
                //highest2 = highest;
            }
            else if(temp == max){
               highest = highest + "," + Integer.toString(i);
            }
         //            else if(temp < max){
         //                if(temp > max2){
         //                    max2 = temp;
         //                }
         //                else if(temp == max2){
         //                    max2 = max2 + "," + Integer.toString(i);
         //                }
         //            }
         }
      }
      priority = highest.split(",");
      highest = "";
      for (String priority1 : priority) {
         temp = Integer.parseInt(priority1);
         switch (temp) {
            case 1:
               highest = highest + "Course" + ", ";
               break;
            case 3:
               highest = highest + "Salary" + ", ";
               break;
            case 5:
               highest = highest + "Employment Rate" + ", ";
               break;
            case 7:
               highest = highest + "Location" + ", ";
               break;
            case 9:
               highest = highest + "Working Hours" + ", ";
               break;
            default:
               highest = highest + "Working Environment";
               break;
         }
           
      }
      return highest;
   }
   
   public void courseFinder(String highest, List<String> result) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader("Records.txt"));
      String temp[] = new String[13];
      List<String> searchResults = new ArrayList<String>();
      String course, salary, employRate, location, workHours, workEnv;
      course = result.get(0);
      salary = result.get(2);
      employRate = result.get(4);
      location = result.get(6);
      workHours = result.get(8);
      workEnv = result.get(10);
   
      while(br.readLine() != null){
         temp = br.readLine().split(",");
         if(course.equals(temp[2].trim())){
            System.out.println(temp[2]);
            searchResults.add(Arrays.toString(temp));
         }
      }
      System.out.println(searchResults);
       
   }

   
   public static void main(String[] args) throws IOException  {
      ApiMgr example= new ApiMgr();
      example.getGradEmploymentSurvey();
   }
}