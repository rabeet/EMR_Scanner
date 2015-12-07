package scan;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Scan {
	
	public ArrayList<String> ScanTables(Connection conn) throws SQLException {
		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", new String[] {"TABLE"});
		ArrayList<String> list = new ArrayList<String>();
		while (rs.next()) {
		  list.add(rs.getString(3));
		}
		return list;
	}
	/*
	 * Scan the columns in a given table
	 */
	public void ScanColumns(Connection conn, String tablename) throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM "+tablename+" limit(10)");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		int numDoubles=0;
		int numIntegers=0;
		int numStrings=0;
		int numText=0;
		System.out.println("\t\tNumber of columns: " + columns);
		for (int i =1; i< columns+1; i++) {
			if (i==1)
				System.out.print("\t\t");
			System.out.print(rsmd.getColumnLabel(i)+ "(" + rsmd.getColumnTypeName(i) + ")" + ",");
			if (rsmd.getColumnTypeName(i).equalsIgnoreCase("numeric"))
				numDoubles++;
			else if (rsmd.getColumnTypeName(i).equalsIgnoreCase("int4"))
				numIntegers++;
			else if (rsmd.getColumnTypeName(i).equalsIgnoreCase("bpchar"))
				numStrings++;
			else if (rsmd.getColumnTypeName(i).equalsIgnoreCase("text"))
				numText++;
		}
		System.out.println();
		System.out.println("\t\tNumber of numeric(float) attributes: " + numDoubles);
		System.out.println("\t\tNumber of integer(whole number) attributes: " + numIntegers);
		System.out.println("\t\tNumber of String(bpchar) attributes: " + numStrings);
		System.out.println("\t\tNumber of textual attributes: " + numText);
	}
	
	/*
	 * Scan the doubles
	 */
	
	public void ScanDoubles(Connection conn, String tablename) throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM "+tablename+" limit(10)");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=1; i<columns+1; i++) {
			if (rsmd.getColumnTypeName(i).equalsIgnoreCase("numeric"))
				list.add(i);
		}
		Object[] test = list.toArray();
		for (int i=0; i<test.length; i++) {
			System.out.println(test[i]);
		}
	}
}
