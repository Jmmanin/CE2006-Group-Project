/*
Jeremy Manin
CE2006- Team Secret
Term Project- ResultMgr
Handles saving/loading results to/from server and local location 
*/

import java.io.*;
import java.sql.*;  /*module for mysql */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResultMgr
{   
   public ResultMgr()
   {

   }
   
   public void saveLocal()
   {
   
   }
   
   public void loadLocal()
   {
   
   }
   
   public static void saveServer()
   {
	   try{  
		   String driver = "com.mysql.jdbc.Driver"; 
		   String url = "jdbc:mysql://mysql4.gear.host/teamsecretdb";	/*hosted on gearhost.com <10MB*/
		   String username = "teamsecretdb";							/*default username*/
		   String password = "TEAMSECRET2017!";							/*password*/	
		   Connection con = createConnection(driver, url,username,password); /*connect to server using jdbc*/
		   System.out.println("Con success");
		   String query = "INSERT INTO emp " + "VALUES(4, 'BK', 22)";
		   insertQuery(query, con); 			/*submit query to show table 'emp'*/ 
		   System.out.println("Insert Succesful");
		   close(con);														/* close connection to server */
	   }
	   catch(Exception e)
	   { 
		   System.out.println(e);
	   }  
   }
   
   public static void loadServer()
   {
	   try{  
		   String driver = "com.mysql.jdbc.Driver"; 
		   String url = "jdbc:mysql://mysql4.gear.host/teamsecretdb";	/*hosted on gearhost.com <10MB*/
		   String username = "teamsecretdb";							/*default username*/
		   String password = "TEAMSECRET2017!";							/*password*/	
		   Connection con = createConnection(driver, url,username,password); /*connect to server using jdbc*/
		   ResultSet rs= selectQuery("select * from emp", con); 			/*submit query to show table 'emp'*/ 
		   List<Map<String, Object>> results= map(rs);
		   printTable(results);
		   close(con);														/* close connection to server */
	   }
	   catch(Exception e)
	   { 
		   System.out.println(e);
	   }  
   }
	     
   public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException
   {
	   Class.forName(driver);
	   if ((username == null) ||(password == null) || (username.trim().length() == 0) || (password.trim().length() == 0 ))
	   {
		   return DriverManager.getConnection(url);
	   }
	   else
	   {
		   return DriverManager.getConnection(url, username, password);
	   }
   }
   
   public static void close(Connection connection)
   {
	   try
	   {
		   if (connection != null)
		   {
			   connection.close();
		   }
	   }
	   catch (SQLException e)
	   {
		   e.printStackTrace();
	   }
   }
   
   public static void close(Statement st)
   {
	   try
	   {
		   if (st != null)
		   {
			   st.close();
		   }
	   }
	   catch (SQLException e)
	   {
		   e.printStackTrace();
	   }
   }
   
   public static void close(ResultSet rs)
   {
	   try
	   {
		   if (rs != null)
		   {
			   rs.close();
		   }
	   }
	   catch (SQLException e)
	   {
		   e.printStackTrace();
	   }
	
   }
  
   public static List<Map<String, Object>> map(ResultSet rs) throws SQLException
   {
	   List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
	   
	   try
	   {
		   if (rs != null)
		   {
			   ResultSetMetaData meta = rs.getMetaData();
			   int numColumns = meta.getColumnCount();
			   while (rs.next())
			   {
				   Map<String,Object> row = new HashMap<String, Object>();
				   for (int i = 1; i <= numColumns; ++i)
				   {
					   String name = meta.getColumnName(i);
					   Object value = rs.getObject(i);
					   row.put(name,value);
				   }
				   results.add(row);
			   }
		   }
	   }
	   finally
	   {
		   close(rs);
	   }
	   return results; 
   }
 
   public static ResultSet selectQuery(String s, Connection con) throws SQLException

   {
	   Statement stmt = con.createStatement();
	   ResultSet rs = stmt.executeQuery(s);
	   return rs;

   }
   
   public static void insertQuery(String s, Connection con) throws SQLException

   {
	   Statement stmt = con.createStatement();
	   stmt.executeUpdate(s);

   }
   public static void printTable(List<Map<String, Object>> results)
   {
	   Map<String, Object> temp = new HashMap<String, Object>();
	   for (int i = 1; i <= results.size(); i++)
	   {
		   temp = results.get(i-1);
		   for (Map.Entry<String,Object> entry: temp.entrySet())
		   {
			   System.out.format("| %5s = %10s |", entry.getKey(), entry.getValue());

		   }
		   
		   System.out.println("\n==================================================================");
	   }
	   	
   }
   
   public static void main(String args[]){  
	  loadServer();
	  saveServer();
	  loadServer();
   }
}