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
   private int choice;
   private int importance;
   
   public SurveyQuestion(String q, String[] o)
   {
      question= q;
      options= o;
      choice= -1;
      importance= -1;
   }
   
   public String getQuestionText()
   {
      return(question);
   }
   
   public String getOption(int index)
   {
      return(options[index]);
   }
   
   public int getOptionsNum()
   {
      return(options.length);
   }
   
   public int getChoice()
   {
      return(choice);
   }
   
   public int getImportance()
   {
      return(importance);
   }
   
   public void setAnswer(int c, int i)
   {
      choice= c;
      importance= i;
   }   
}