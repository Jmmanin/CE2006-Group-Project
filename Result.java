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
   private String[] processedChoices;
   private int[] processedImportances;
   private String[] recommended;
   
   public Result(int[] rA, int[] i)
   {
      rawChoices= rA;
      importances= i; 
      processedChoices= null;
      processedImportances= null;
      recommended= null;
   }
   
   public int getRawChoice(int index)
   {
      return(rawChoices[index]);
   }
   
   public int getQuestionNum()
   {
      return(rawChoices.length);
   }
   
   public int getImportance(int index)
   {
      return(importances[index]);
   }
      
   public String getProcessedChoice(int index)
   {
	   return processedChoices[index];
   }
   
   public int getProcessedImportance(int index)
   {
	   return processedImportances[index];
   }
   
   public String getRecommendedData(int index)
   {
      return(recommended[index]);
   }
   
   public void setProcessedChoices(String[] pC)
   {
      processedChoices= pC;
      assert(processedChoices.length==(rawChoices.length-1));
   
      int[] output= new int[importances.length-1];
	   for(int i= 0;i<output.length;i++)
		   output[i]= importances[i+1];
	   
	   processedImportances= output;
   }
   
   public void setRecommended(String rec)
   {
	   recommended = rec.split(",");
   }
}