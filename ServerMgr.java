
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

	public boolean verifyAccount() {
		return (true);
	}

	public void createAccount() {

	}

	public void saveLocal() {

	}

	public void loadLocal() {

	}

	public static void saveRowtoServer(String tableName, String values) {
		try {
			Connection con = createConnection(); /*
			 * connect to server using
			 * jdbc
			 */
			insertQuery(tableName, values,
					con); /* submit query to save a row to table */
			System.out.println("Row saved");
			close(con); /* close connection to server */
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void loadTablefromServer(String tableName) {
		try {

			Connection con = createConnection(); /*
			 * connect to server using
			 * jdbc
			 */
			ResultSet rs = selectQuery(tableName,
					con); /* submit query to show all rows in table 'emp' */
			List<Map<String, Object>> results = map(rs);
			printTable(results);
			close(con); /* close connection to server */
		} catch (Exception e) {
			System.out.println(e);
		}
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

	public static void insertQuery(String tableName, String values, Connection con) throws SQLException {
		String s = "INSERT INTO " + tableName + " VALUES(" + values + ")";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(s);

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

	public static void main(String args[]) {
		String tableName = "emp";
		String values = "4, 'BK', 22";
		String loc = "id = 4";
		loadTablefromServer(tableName);
		deleteRowfromServer(tableName, loc);
		loadTablefromServer(tableName);
		saveRowtoServer(tableName, values);
		loadTablefromServer(tableName);
		loadRowfromServer(tableName, loc);
	}
}