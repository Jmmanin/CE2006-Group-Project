/*
Jeremy Manin, Boon Kiat, Yeong Jin Zhi, Andrew Koh Jin Jie, Feng Wei 
CE2006- Team Secret
Term Project- Result
Stores result data
*/

import java.util.ArrayList;

public class Result
{
   private int[] rawChoices;
   private int[] importances;
   private String[] processedAnswers;
   
   public Result(int[] rA, int[] i)
   {
      rawChoices= rA;
      importances= i;
   }
   
   public int getQuestionNum()
   {
      return(rawChoices.length);
   }
   
   public int getImportance(int index)
   {
      return(importances[index]);
   }
   
   public void setProcessedAnswers(String[] pA)
   {
      processedAnswers= pA;
      assert(processedAnswers.length==(rawChoices.length-1));
   }
}