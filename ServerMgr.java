/*
Jeremy Manin
CE2006- Team Secret
Term Project- ServerMgr
Manages communications between program and server
 */

import java.io.*;
import java.sql.*; //module for mysql
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServerMgr
{
   public ServerMgr()
   {
   
   }

   public void saveLocal(String username, Result theResult)
   {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
      Date date = new Date();
      StringBuilder filename= new StringBuilder("results/");
      filename.append(username);
      filename.append(dateFormat.format(date));
      filename.append(".txt");
      File data= new File(filename.toString());
      File list= new File("results/_resultsList.txt");      
      FileOutputStream dataOut= null, listOut= null;
      ObjectOutputStream dataWriter= null;
      PrintWriter listWriter= null;
      
      try
      {
         if(!data.exists())
            data.createNewFile();
         if(!list.exists())
            list.createNewFile();   
         dataOut= new FileOutputStream(data, false);
         listOut= new FileOutputStream(list, true);
         dataWriter= new ObjectOutputStream(dataOut);
         listWriter= new PrintWriter(listOut);
         
         listWriter.println(filename.toString());
         dataWriter.writeObject(theResult);            
      
         dataWriter.close();
         listWriter.close();
         dataOut.close();
         listOut.close();
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }      
   }

   public String[] loadLocalList()
   {
      ArrayList<String> filenames= new ArrayList<String>();
      File list= new File("results/_resultsList.txt");
      Scanner listIn;
            
      try
      {
         listIn= new Scanner(list);         
         
         while(listIn.hasNextLine())
         {
            filenames.add(listIn.nextLine());
         }
      }
      catch(IOException e)
      {
         return(new String[0]);
      }
      
      String[] output= new String[filenames.size()];      
      return(filenames.toArray(output));  
   }

   public Result loadLocal(String filename)
   {
      File data= new File(filename);
      FileInputStream dataIn;
      ObjectInputStream dataReader;
      Result output= null;
      
      try
      {
         dataIn= new FileInputStream(data);
         dataReader= new ObjectInputStream(dataIn);
         
         output= (Result)dataReader.readObject();
         
         dataReader.close();
         dataIn.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      
      return(output);
   }

   public int saveRowtoServer(String tableName, String values) {
      int status = 0;
      try {
         String columnsPara = "";
         if (tableName.equals("emp")){
            columnsPara = "(id, name, age)";
         }
         else if (tableName.equals("serialobjects")){
            columnsPara = "(title, serial)";
         }
         else{
            columnsPara = "(iduserinfo, PW)";
         }
         Connection con = createConnection(); /*
      	 * connect to server using
      	 * jdbc
      	 */
         status = insertQuery(tableName, columnsPara, values,
            	con); /* submit query to save a row to table */
         if (status == 1){
            System.out.println("Row saved");
         }
         close(con); /* close connection to server */
      } 
      catch (Exception e) {
         System.out.println(e);
      }
      return status;
   }

   public List<Map<String, Object>> loadTablefromServer(String tableName) {
      List<Map<String, Object>> results = null;
      try {
      
         Connection con = createConnection(); /*
      	 * connect to server using
      	 * jdbc
      	 */
         ResultSet rs = selectQuery(tableName,
            	con); /* submit query to show all rows in table 'emp' */
         results = map(rs);
         close(con); /* close connection to server */
      	
      } 
      catch (Exception e) {
         System.out.println(e);
      }
      return results;
   }

   public void loadRowfromServer(String tableName, String loc) {
      try {
         Connection con = createConnection(); /*
      	 * connect to server using
      	 * jdbc
      	 */
         ResultSet rs = selectQuery(tableName, loc,
            	con); /* submit query to show a row from table 'emp' */
         List<Map<String, Object>> results = map(rs);
         printTable(results);
         close(con); /* close connection to server */
      } 
      catch (Exception e) {
         System.out.println(e);
      }
   }

   public void deleteRowfromServer(String tableName, String loc) {
      try {
         Connection con = createConnection(); /*
      	 * connect to server using
      	 * jdbc
      	 */
         deleteRow(tableName, loc,
            	con); /* submit query to delete row from table */
         System.out.println("Row deleted");
         close(con); /* close connection to server */
      } 
      catch (Exception e) {
         System.out.println(e);
      }
   }

   public Connection createConnection() throws ClassNotFoundException, SQLException {
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://mysql4.gear.host/teamsecretdb"; /*
   	 * hosted on
   	 * gearhost.
   	 * com <10MB
   	 */
      String username = "teamsecretdb"; /* default username */
      String password = "TEAMSECRET2017!"; /* password */
      Class.forName(driver);
      if ((username == null) || (password == null) || (username.trim().length() == 0)
      		|| (password.trim().length() == 0)) {
      
         return DriverManager.getConnection(url);
      } 
      else {
         return DriverManager.getConnection(url, username, password);
      }
   }

   public void close(Connection connection) {
      try {
         if (connection != null) {
            connection.close();
         }
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void close(Statement st) {
      try {
         if (st != null) {
            st.close();
         }
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void close(ResultSet rs) {
      try {
         if (rs != null) {
            rs.close();
         }
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   
   }

   public List<Map<String, Object>> map(ResultSet rs) throws SQLException {
      List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
   
      try {
         if (rs != null) {
            ResultSetMetaData meta = rs.getMetaData();
            int numColumns = meta.getColumnCount();
            while (rs.next()) {
               Map<String, Object> row = new HashMap<String, Object>();
               for (int i = 1; i <= numColumns; ++i) {
                  String name = meta.getColumnName(i);
                  Object value = rs.getObject(i);
                  row.put(name, value);
               }
               results.add(row);
            }
         }
      } 
      finally {
         close(rs);
      }
      return results;
   }

   public ResultSet selectQuery(String tableName, Connection con)
   		throws SQLException /* load all rows from table */
   
   {
      String s = "SELECT * from " + tableName;
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(s);
      return rs;
   
   }

   public ResultSet selectQuery(String tableName, String loc, Connection con)
   		throws SQLException /* load a single row from table */
   
   {
      String s = "SELECT * from " + tableName + " WHERE "
         	+ loc; /* SELECT * from tableName WHERE loc */
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(s);
      return rs;
   
   }

   public int insertQuery(String tableName, String columnsPara, String values, Connection con) throws SQLException {
      int status = 0;
      try{
         String s = "INSERT INTO " + tableName + columnsPara + " VALUES(" + values + ")";
         Statement stmt = con.createStatement();
         stmt.executeUpdate(s);
         status = 1;
      }
      catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
         System.out.println("Duplicate Key");
         status = 2;
      }
      return status;
   }

   public void deleteRow(String tableName, String loc, Connection con) throws SQLException {
      String s = "DELETE FROM " + tableName + " WHERE " + loc;
      Statement stmt = con.createStatement();
      stmt.executeUpdate(s);
   }
	
   public void printTable(List<Map<String, Object>> results) {
      Map<String, Object> temp = new HashMap<String, Object>();
      for (int i = 1; i <= results.size(); i++) {
         temp = results.get(i - 1);
         for (Map.Entry<String, Object> entry : temp.entrySet()) {
            System.out.format("| %5s = %10s |", entry.getKey(), entry.getValue());
         
         }
      
         System.out.println("\n==================================================================");
      }
   
   }

   public int verifyUser(String user, String pw) throws SQLException, ClassNotFoundException{
   	
      String loc = "iduserinfo = '" + user+ "'";
      String tableName = "userinfo";
      String dbusername = "";
      String dbpassword = "";
      Connection con = createConnection(); /*
   	 * connect to server using
   	 * jdbc
   	 */
      ResultSet rs = selectQuery(tableName, loc, con);
      List<Map<String, Object>> results = map(rs);
      Map<String, Object> temp = new HashMap<String, Object>();
      try{
         temp = results.get(0);
      }
      catch (IndexOutOfBoundsException e){
         return 2;
      }
      int i = 0;
      for (Map.Entry<String, Object> entry : temp.entrySet()) {
         if (i==0){
            dbusername = (String) entry.getValue();
            i++;
         }
         else{
            dbpassword = (String) entry.getValue();
         
         }
      }
      if ((dbpassword.equals(pw)) && (dbusername.equals(user))){
         close(con);
         return 1;
      }
      else{
         close(con);
         return 0;	
      }
   }
	
   public int createUser(String user, String pw) throws SQLException, ClassNotFoundException{
   	
      int status = 0;
      try{
         String tableName = "userinfo";
         String values = "'" + user + "', '" + pw +"'";
         status = saveRowtoServer(tableName, values);
      }
      finally{
         ;
      }
      return status;
   }
	
   public int saveSerial(String username, Object serial) throws SQLException, ClassNotFoundException, IOException
   {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
      Date date = new Date();
      StringBuilder temp= new StringBuilder(username);
      temp.append(dateFormat.format(date));
      String title= temp.toString();
      
      int status = 0;
      try{
         Connection con = createConnection();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(baos);
         oos.writeObject(serial);
         byte[] serialAsBytes = baos.toByteArray();
         ByteArrayInputStream bais = new ByteArrayInputStream(serialAsBytes);
         String sql = "INSERT INTO serialobjects (title, serial) VALUES (?, ?)";
         PreparedStatement stmt = con.prepareStatement(sql);
         stmt.setString(1, title);
         stmt.setBinaryStream(2, bais, serialAsBytes.length);
         stmt.executeUpdate();
         stmt.close();
         con.close();
         status = 1;
      }
      catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
         System.out.println("Duplicate Key");
         status = 2;
      }
      finally{
         ;
      }
      return status;
   } 

   public Result loadSerialRow(String title) throws SQLException, ClassNotFoundException, IOException{
   	
      Result result = null;
      String loc = "title = '" + title + "'";
      String tableName = "serialobjects";
      Connection con = createConnection(); /*
   	 * connect to server using
   	 * jdbc
   	 */
      ResultSet rs = selectQuery(tableName, loc, con);
      List<Map<String, Object>> results = map(rs);
      while (rs.next()) {
         byte[] st = (byte[]) rs.getObject(1);
         ByteArrayInputStream baip = new ByteArrayInputStream(st);
         ObjectInputStream ois = new ObjectInputStream(baip);
         result = (Result) ois.readObject();
      }
      con.close();
      rs.close();
      return result;
   }
	
   public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException { 
   //       ServerMgr example= new ServerMgr();
   //       
   //       String tableName = "emp";
   //       String user = "TEAMSECRETLOSERS";
   //       String pw = "ILOVELOSERS";
   //       String values = "4, 'BK', 23";
   //       String loc = "id = 4";
   //       String title = "testtitle";
   //       String serial = "testserial";
   //    	//example.saveSerial(title); //These method calls need to be updated
   //    	//example.loadSerial("tokyoowl");
   
   }
}