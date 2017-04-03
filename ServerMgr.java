/*
Jeremy Manin
CE2006- Team Secret
Term Project- ServerMgr
Manages communications between program and server
 */

import java.io.*;
import java.sql.*; //module for mysql
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerMgr {
	public ServerMgr() {

	}

	public void saveLocal() {

	}

	public void loadLocal() {

	}

	public static int saveRowtoServer(String tableName, String values) {
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
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static List<Map<String, Object>> loadTablefromServer(String tableName) {
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
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return results;
	}

	public static void loadRowfromServer(String tableName, String loc) {
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
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteRowfromServer(String tableName, String loc) {
		try {
			Connection con = createConnection(); /*
			 * connect to server using
			 * jdbc
			 */
			deleteRow(tableName, loc,
					con); /* submit query to delete row from table */
			System.out.println("Row deleted");
			close(con); /* close connection to server */
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Connection createConnection() throws ClassNotFoundException, SQLException {
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
		} else {
			return DriverManager.getConnection(url, username, password);
		}
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Map<String, Object>> map(ResultSet rs) throws SQLException {
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
		} finally {
			close(rs);
		}
		return results;
	}

	public static ResultSet selectQuery(String tableName, Connection con)
			throws SQLException /* load all rows from table */

	{
		String s = "SELECT * from " + tableName;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		return rs;

	}

	public static ResultSet selectQuery(String tableName, String loc, Connection con)
			throws SQLException /* load a single row from table */

	{
		String s = "SELECT * from " + tableName + " WHERE "
				+ loc; /* SELECT * from tableName WHERE loc */
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		return rs;

	}

	public static int insertQuery(String tableName, String columnsPara, String values, Connection con) throws SQLException {
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

	public static void deleteRow(String tableName, String loc, Connection con) throws SQLException {
		String s = "DELETE FROM " + tableName + " WHERE " + loc;
		Statement stmt = con.createStatement();
		stmt.executeUpdate(s);
	}

	
	public static void printTable(List<Map<String, Object>> results) {
		Map<String, Object> temp = new HashMap<String, Object>();
		for (int i = 1; i <= results.size(); i++) {
			temp = results.get(i - 1);
			for (Map.Entry<String, Object> entry : temp.entrySet()) {
				System.out.format("| %5s = %10s |", entry.getKey(), entry.getValue());

			}

			System.out.println("\n==================================================================");
		}

	}

	public static int verifyUser(String user, String pw) throws SQLException, ClassNotFoundException{
		
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
	
	public static int createUser(String user, String pw) throws SQLException, ClassNotFoundException{
		
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
	
	public static int saveSerial(String title, Object serial) throws SQLException, ClassNotFoundException, IOException{
		
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
		    stmt.setString(1, "title");
		    stmt.setBinaryStream(2, bais, serialAsBytes.length);
		    stmt.executeUpdate();
		    stmt.close();
		    con.close();
		    status = 1;
		}
		finally{
			;
		}
		return status;
	}
	

	public static Result loadSerialRow(String title) throws SQLException, ClassNotFoundException{
		
		Result result = NULL;
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
		String tableName = "emp";
		String user = "TEAMSECRETLOSERS";
		String pw = "ILOVELOSERS";
		String values = "4, 'BK', 23";
		String loc = "id = 4";
		String title = "testtitle";
		String serial = "testserial";
		saveSerial(title);
		loadSerial("tokyoowl");

	}
}