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
import java.util.List;
import java.util.ArrayList;

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
      BufferedWriter bw = new BufferedWriter(new FileWriter("Courses.txt"));
      BufferedWriter bw2 = new BufferedWriter(new FileWriter("Records.txt"));
      br.skip(754);//skip useless stuff
      String data = br.readLine();// read from file
      data = data.replaceAll("\\{", "");//replace unwanted stuff
      data = data.replaceAll("}", "");
      String[] dataArray = data.split(",");//split data
      String[] records = new String[dataArray.length-12];//12 unnecessary array slots
      String[] finalrecord = new String[dataArray.length-12];//same
      List<String> NUS = new ArrayList<String>();
      List<String> NTU = new ArrayList<String>();
      List<String> SMU = new ArrayList<String>();
      List<String> SUTD = new ArrayList<String>();
      String temp = "";
      String temp1 = "";
      int recordNum = 0;
      int count = 0;
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
         if(i>2)
            count++;
         if(count == 1){
            if(finalrecord[i].equals("National University of Singapore")){
               NUS.add(finalrecord[i-1]);
            }
            else if(finalrecord[i].equals("Nanyang Technological University")){
               NTU.add(finalrecord[i-1]);
            }
            else if(finalrecord[i].equals("Singapore Management University")){
               SMU.add(finalrecord[i-1]);
            }
            else{
               SUTD.add(finalrecord[i-1]);
            }
         	
         }
         if(i == 2 || count == 13){
            count = 0;
         }
      }
   	
      bw.write("NUS");
      bw.newLine();
      for(String str : NUS){
         bw.write(str);
         bw.newLine();
      }
   
      bw.write("NTU");
      bw.newLine();
      for(String str : NTU){
         bw.write(str);
         bw.newLine();
      }
   	
      bw.write("SMU");
      bw.newLine();
      for(String str : SMU){
         bw.write(str);
         bw.newLine();
      }
      bw.write("SUTD");
      bw.newLine();
      for(String str : SUTD){
         bw.write(str);
         bw.newLine();
      }
   	/* the code below strips away the description ie University : NUS. "University :" is stripped away */
      for(int i=0; i<records.length;i++){
         temp = records[i];
         System.out.println(temp);
         dataArray = temp.split(":");
         finalrecord[i] = dataArray[1].trim();
      }
   	
   	/* the code below creates Records.txt file to store all information about a single course*/
      for(int i = 0; i < finalrecord.length; i++){ // records are stored in the format grs mthly 25th percentile, school, degree, uni, grs mthy median, perm employment rate, basic monthly median, grs mthy 75 percentile, grs mthly mean, basic mthly mean, the year this record was from, id of record
         if(i == 0){
            count = 0;
            bw2.write(finalrecord[i]);
            bw2.write(", ");
         }
         else if(count<12){
            count++;
            bw2.write(finalrecord[i]);
            bw2.write(", ");
         }
         else if(count == 12){
            count = 0;
            bw2.newLine();
            bw2.write(finalrecord[i]);
            bw2.write(", ");
         }
      }
      bw2.close();
      bw.close();
      br.close();
   }       
   
   public void processRawChoices(Result r){
      String[] processed = new String[r.getQuestionNum()-1];
      String course, salary, location, employRate, workHours, workEnv, courseImpt, salaryImpt, employRateImpt, locationImpt, workHoursImpt, workEnvImpt;
         
       /* this if branch checks for major*/
      switch (r.getRawChoice(0)) {
         case 0:
           // engineering
            switch (r.getRawChoice(1)) {
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
            switch (r.getRawChoice(1)) {
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
            switch (r.getRawChoice(1)) {
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
            switch (r.getRawChoice(1)) {
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
            switch (r.getRawChoice(1)) {
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
            switch (r.getRawChoice(1)) {
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
            switch (r.getRawChoice(1)) {
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
            switch (r.getRawChoice(1)) {
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
      switch (r.getRawChoice(2)) {
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
      switch (r.getRawChoice(3)) {
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
      switch (r.getRawChoice(4)) {
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
      switch (r.getRawChoice(5)){ 
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
      switch (r.getRawChoice(6)) {
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
         
      r.setProcessedChoices(processed);
   }

   
   public void courseFinder(Result r) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader("Records.txt"));
      String temp[] = new String[13];
      List<String[]> searchResults = new ArrayList<String[]>();
      String course, temp2;
      course = r.getProcessedChoice(0); // get the search parameter
      int salaryImpt = r.getProcessedImportance(1);
      int employRateImpt = r.getProcessedImportance(2);
      int count = 0;
      int maxSalary = 0;
      double maxEmployRate = 0.0;
      int[] highestSalary = new int[2];
      String[] highestEmployRate = new String[2];
      br.readLine(); // skip line
      while((temp2= br.readLine()) != null){
         temp2 = temp2.replaceAll(", $", "");
         temp = temp2.split(",");
         if(temp[2].trim().equals("Arts & Social Sciences")){
            if(temp[3].trim().contains(course)){
               searchResults.add(temp);
               if(Integer.parseInt(temp[9]) > maxSalary){
                  maxSalary = Integer.parseInt(temp[9]);
                  highestSalary[0] = Integer.parseInt(temp[9]);
                  highestSalary[1] = count;
               }
               if(Double.parseDouble(temp[12]) > maxEmployRate){
                  maxEmployRate = Double.parseDouble(temp[12]);
                  highestEmployRate[0] = temp[12];
                  highestEmployRate[1] = Integer.toString(count);
               }
               count++;
            }
         }
         if(temp[2].trim().contains(course)){
            searchResults.add(temp);
            if(!temp[9].trim().equals("na")){
               if(Integer.parseInt(temp[9].trim()) > maxSalary){
                  maxSalary = Integer.parseInt(temp[9].trim());
                  highestSalary[0] = Integer.parseInt(temp[9].trim());
                  highestSalary[1] = count;
               }
            }
            if(!temp[12].trim().equals("na")){
               if(Double.parseDouble(temp[12]) > maxEmployRate){
                  maxEmployRate = Double.parseDouble(temp[12]);
                  highestEmployRate[0] = temp[12];
                  highestEmployRate[1] = Integer.toString(count);
               }
            }
            count++;
         }
      }
      /* comparison of importance */
      if(highestSalary[1] == Integer.parseInt(highestEmployRate[1])){ //check if it is the same record in both arrays
         r.setRecommended(searchResults.get(highestSalary[1]));
      }
      else{ // else we compare the weights
         if(salaryImpt>employRateImpt){
            r.setRecommended(searchResults.get(highestSalary[1]));
         }
         else{
            r.setRecommended(searchResults.get(Integer.parseInt(highestEmployRate[1])));
         }
           
      }
   }

   
   public static void main(String[] args) throws IOException  {
      ApiMgr example= new ApiMgr();
      int[] test= {1,0,0,0,0,0,0};
      int[] test2= {0,0,0,0,0,0,0};
      Result test3= new Result(test,test2);
      example.processRawChoices(test3);
      example.courseFinder(test3);      
   }
}