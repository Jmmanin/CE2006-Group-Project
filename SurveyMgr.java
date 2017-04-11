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
   
   
   public static String weighImportance(int[][] answers){
	   String result = "";
	   /* this if branch checks for major*/
	   if(answers[0][0] == 1){ // engineering
		   if(answers[1][0] == 1){ // electronics or mechanics
			   result = "Electronic/Mechanical";
		   }
		   else if(answers[1][0] == 2){ // Science
			   
		   }
		   else if(answers[1][0] == 3){ // Information
			   
		   }
		   else{ // Anything
			   
		   }
	   }
	   else if(answers[0][0] == 2){ // computing
		   if(answers[1][0] == 1){ // hardware
			   
		   }
		   else if(answers[1][0] == 2){ // software
			   
		   }
		   else{ //both
			   
		   }
	   }
	   else if(answers[0][0] == 3){ // accountancy and business
		   if(answers[1][0] == 1){ // mathematics
			   
		   }
		   else if(answers[1][0] == 2){ // Operations/planning
			   
		   }
		   else{ // both
			   
		   }
	   }
	   else if(answers[0][0] == 4){ // art design media
		   if(answers[1][0] == 1){ // animation
			   
		   }
		   else if(answers[1][0] == 2){ // photography
			   
		   }
		   else if(answers[1][0] == 3){ // design/innovation
			   
		   }
		   else{ // anything
			   
		   }
	   }
	   else if(answers[0][0] == 5){ // comms studies
		  
	   }
	   else if(answers[0][0] == 6){ // sports
		   
	   }
	   else if(answers[0][0] == 7){ // health
		   if(answers[1][0] == 1){ // medicine
			   
		   }
		   else if(answers[1][0] == 2){ // pharmacy
			   
		   }
		   else if(answers[1][0] == 3){ // dentistry
			   
		   }
		   else if(answers[1][0] == 4){ // nursing
			   
		   }
		   else{ // anything
			   
		   }		   
	   }
	   else if(answers[0][0] == 8){ // humanities
		   if(answers[1][0] == 1){ // language studies
			   
		   }
		   else if(answers[1][0] == 2){ // society
			   
		   }
		   else if(answers[1][0] == 3){ // history
			   
		   }
		   else{ // anything
			   
		   }
	   }
	   else if(answers[0][0] == 9){ // language
		   
	   }
	   else if(answers[0][0] == 10){ // science
		   if(answers[1][0] == 1){ // biology
			   
		   }
		   else if(answers[1][0] == 2){ // chemistry
			   
		   }
		   else if(answers[1][0] == 3){ // physics
			   
		   }
		   else if(answers[1][0] == 4){ // food
			   
		   }
		   else if(answers[1][0] == 5){ // data
			   
		   }
		   else{ // anything
			   
		   }
	   }
	   else if(answers[0][0] == 11){ // law
		   
	   }
	   else if(answers[0][0] == 12){ // mathematics
		   if(answers[1][0] == 1){ // statistics
			   
		   }
		   else if(answers[1][0] == 2){ // finance
			   
		   }
		   else{ // anything
			   
		   }
	   }
	   else{ // music
		   
	   }
	   /* this if branch checks for salary*/
	   if(answers[2][0] == 1){ // >= 3k
		   
	   }
	   else if(answers[2][0] == 2){ // >= 3.5k
		   
	   }
	   else if(answers[2][0] == 3){ // >= 4k
		   
	   }
	   else if(answers[2][0] == 4){ // >= 4.5k
		   
	   }
	   else if(answers[2][0] == 5){ // >4.5k
		   
	   }
	   else{ // anything
		   
	   }
	   
	   /* this branch checks for rate of employment */
	   if(answers[3][0] == 1){ // >= 70%
		   
	   }
	   else if(answers[3][0] == 2){ // >= 75%
		   
	   }
	   else if(answers[3][0] == 3){ // >= 80%
		   
	   }
	   else if(answers[3][0] == 4){ // >= 85%
		   
	   }
	   else if(answers[3][0] == 5){ // >85%
		   
	   }
	   else{ // anything
		   
	   }
	   
	   /* this branch checks for location */
	   if(answers[4][0] == 1){ // south west
		   
	   }
	   else if(answers[4][0] == 2){ // central
		   
	   }
	   else{ // anywhere
		   
	   }
	   
	   /* this branch checks for working hours */
	   if(answers[5][0] == 1){ // flexible
		   
	   }
	   else{ // fixed
		   
	   }
	   
	   /* this branch checks for working environment */
	   if(answers[6][0] == 1){ // office job
		   
	   }
	   else if(answers[6][0] == 2){ // hands-on
		   
	   }
	   else if(answers[6][0] == 3){ // backend
		   
	   }
	   else if(answers[6][0] == 4){ // frontend
		   
	   }
	   else if(answers[6][0] == 5){ // fast paced
		   
	   }
	   else if(answers[6][0] == 6){ // laboratory
		   
	   }
	   else{ // outfield
		   
	   }
	   
	   return result;
   }
   
   public static void main(String[] args) throws IOException
   {
      SurveyMgr example= new SurveyMgr("survey_questions.txt");
      example.generateQuestions();
      weighImportance();
   }
   
}
