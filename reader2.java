/*
Yeong Jin Zhi
CE2006- Team Secret
Term Project- Data processing
Algorithm to process data taken from the API
*/
//package reader; //comment away if not used

import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Arrays;

public class reader2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("grademploymentsurvey.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("Courses.txt"));
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
		
//		for(int i=0; i<records.length;i++){
//			temp = records[i];
//			dataArray = temp.split(":");
//			finalrecord[i] = dataArray[1].trim();
//			System.out.println(finalrecord[i]);
//		}
		bw.close();
		br.close();
	}
}

