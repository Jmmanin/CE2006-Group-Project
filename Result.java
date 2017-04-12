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
   
   public int getQuestionNum()
   {
      return(rawChoices.length);
   }
   
   public int getImportance(int index)
   {
      return(importances[index]);
   }
   
   public void setProcessedChoices(String[] pC)
   {
      processedChoices= pC;
      assert(processedChoices.length==(rawChoices.length-1));
   
      int[] output= int[raw.length-1];
	   for(int i;i<output.length;i++)
		   output[i]= raw[i+1];
	   
	   processedImportances= output;
   }
   
   public String getProcessedChoices(int index)
   {
	   return processedChoices[index];
   }
   
   public String getProcessedImportances(int index)
   {
	   return processedImportances[index];
   }
   
   public void setRecommended(String[] rec)
   {
	   recommended = rec;
   }
}