/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- SurveyQuestion
Holds data for survey questions
*/

public class SurveyQuestion
{
   private String question;
   private String[] options;
   
   public SurveyQuestion(String q, String[] o)
   {
      question= q;
      options= o;
   }
}