/*
Yeong Jin Zhi
CE2006- Team Secret
Term Project- Data processing
Algorithm to process data taken from the API
*/
package reader; //comment away if not used

import java.io.*;
//import java.util.Arrays;

public class reader2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
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
}

