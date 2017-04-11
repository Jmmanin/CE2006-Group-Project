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
   
   public int[][] getAnswers()
   {
      int[][] answers= new int[questions.length][2];
      
      for(int i=0;i<questions.length;i++)
      {
         answers[i][0]= questions[i].getChoice();
         answers[i][1]= questions[i].getImportance();
      }
      
      return(answers);
   }
   
   
   public static List<String> getImportance(int[][] answers){
	 List<String> result = new ArrayList<String>();
         String course, salary, location, employRate, workHours, workEnv, courseImpt, salaryImpt, employRateImpt, locationImpt, workHoursImpt, workEnvImpt;
         course = salary = location = employRate = workHours = workEnv = courseImpt = salaryImpt = employRateImpt = locationImpt = workHoursImpt = workEnvImpt = "";
         
       /* this if branch checks for major*/
       switch (answers[0][0]) {
           case 1:
           // engineering
           switch (answers[1][0]) {
               case 1:
                   // electronics or mechanics
                   course = "Electronic/Mechanical Engineering";
                   break;
               case 2:
                   // Science
                   course  = "Engineering Science";
                   break;
               case 3:
                   // Information
                   course = "Information Engineering";
                   break;
               default:
                   // Anything
                   course = "Engineering";
                   break;
           }
               break;
           case 2:
           // computing
           switch (answers[1][0]) {
               case 1:
                   // hardware
                   course = "Computer Engineering";
                   break;
               case 2:
                   // software
                   course = "Computer Science";
                   break;
               default:
                   //both
                   course = "Computing";
                   break;
           }
               break;
           case 3:
           // accountancy and business
           switch (answers[1][0]) {
               case 1:
                   // mathematics
                   course = "Business Mathematics";
                   break;
               case 2:
                   // Operations/planning
                   course = "Operations";
                   break;
               default:
                   // both
                   course = "Accountancy and Business";
                   break;
           }
               break;
           case 4:
               // art design media
               switch (answers[1][0]) {
                   case 1:
                       // animation
                       course = "Animation";
                       break;
                   case 2:
                       // photography
                       course = "Photography";
                       break;
                   case 3:
                       // design/innovation
                       course = "Design";
                       break;
                   default:
                       // anything
                       course = "Art";
                       break;
               }
               break;
           case 5:
               // comms studies
               course = "Communications Studies";
               break;
           case 6:
               // sports
               course = "Sports";
               break;
           case 7:
           // health
           switch (answers[1][0]) {
               case 1:
                   // medicine
                   course = "Medicine";
                   break;
               case 2:
                   // pharmacy
                   course = "Pharmacy";
                   break;
               case 3:
                   // dentistry
                   course = "Dentistry";
                   break;
               case 4:
                   // nursing
                   course = "Nursing";
                   break;
               default:
                   // anything
                   course = "Health";
                   break;
           }
               break;
           case 8:
           // humanities
           switch (answers[1][0]) {
               case 1:
                   // language studies
                   course = "Language Studies";
                   break;
               case 2:
                   // society
                   course = "Society";
                   break;
               case 3:
                   // history
                   course = "History";
                   break;
               default:
                   // anything
                   course = "Humanities";
                   break;
           }
               break;
           case 9:
               // language
               course = "Language";
               break;
           case 10:
           // science
           switch (answers[1][0]) {
               case 1:
                   // biology
                   course = "Biology";
                   break;
               case 2:
                   // chemistry
                   course = "Chemistry";
                   break;
               case 3:
                   // physics
                   course = "Physics";
                   break;
               case 4:
                   // food
                   course = "Food Science";
                   break;
               case 5:
                   // data
                   course = "Data Science";
                   break;
               default:
                   // anything
                   course = "Science";
                   break;
           }
               break;
           case 11:
               // law
               course = "Law";
               break;
           case 12:
           // mathematics
           switch (answers[1][0]) {
               case 1:
                   // statistics
                   course = "Statistics";
                   break;
               case 2:
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
       switch (answers[2][0]) {
           case 1:
               // >= 3k
               salary = "3000";
               break;
           case 2:
               // >= 3.5k
               salary = "3500";
               break;
           case 3:
               // >= 4k
               salary = "4000";
               break;
           case 4:
               // >= 4.5k
               salary = "4500";
               break;
           case 5:
               // >4.5k
               salary = "5000";
               break;
           default:
               // anything
               salary = "0";
               break;
       }
	   
       /* this branch checks for rate of employment */
       switch (answers[3][0]) {
           case 1:
               // >= 70%
               employRate = "70";
               break;
           case 2:
               // >= 75%
               employRate = "75";
               break;
           case 3:
               // >= 80%
               employRate = "80";
               break;
           case 4:
               // >= 85%
               employRate = "85";
               break;
           case 5:
               // >85%
               employRate = "90";
               break;
           default:
               // anything
               employRate = "0";
               break;
       }
	   
       /* this branch checks for location */
       switch (answers[4][0]) {
           case 1:
               // south west
               location = "South West";
               break;
           case 2:
               // central
               location = "central";
               break;
           default:
               // anywhere
               location = "";
               break;
       }
	   
	   /* this branch checks for working hours */
       switch (answers[5][0]){ 
           // flexible
           case 1:
              workHours = "Flexible";
              break;
           default: 
               // fixed
               workHours = "Fixed";
               break;
	   }
	   
       /* this branch checks for working environment */
       switch (answers[6][0]) {
           case 1:
               // office job
               workEnv = "Office";
               break;
           case 2:
               // hands-on
               workEnv = "Hands on";
               break;
           case 3:
               // backend
               workEnv = "Backend";
               break;
           case 4:
               // frontend
               workEnv = "Frontend";
               break;
           case 5:
               // fast paced
               workEnv = "Fast Paced";
               break;
           case 6:
               // laboratory
               workEnv = "Laboratory";
               break;
           default:
               // outfield
               workEnv = "Outfield";
               break;
       }
         /* get importance */
         courseImpt = Integer.toString(answers[0][1]);
         salaryImpt = Integer.toString(answers[1][1]);
         employRateImpt = Integer.toString(answers[2][1]);
         locationImpt = Integer.toString(answers[3][1]);
         workHoursImpt = Integer.toString(answers[4][1]);
         workEnvImpt = Integer.toString(answers[5][1]);
	 
         result.add(course);
         result.add(courseImpt);
         result.add(salary);
         result.add(salaryImpt);
         result.add(employRate);
         result.add(employRateImpt);
         result.add(location);
         result.add(locationImpt);
         result.add(workHours);
         result.add(workHoursImpt);
         result.add(workEnv);
         result.add(workEnvImpt);
         
	 return result;
   }
   public static String weighImportance(List<String> result){
       String highest = "";
       String[] priority = new String[result.size()];
       //String highest2 = ""; commented out portion for getting second weightage
       int max = 0;
       int temp;
       //int max2 = 0;
       for(int i = 0; i<result.size();i++){
          if(i%2 == 1){
            temp = Integer.parseInt(result.get(i));
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
   public static void courseFinder(String highest, List<String> result) throws IOException {
       BufferedReader br = new BufferedReader(new FileReader("Records.txt"));
       
   }
   
   public static void main(String[] args) throws IOException
   {
      SurveyMgr example= new SurveyMgr("survey_questions.txt");
      example.generateQuestions();
      
   }
   
}
