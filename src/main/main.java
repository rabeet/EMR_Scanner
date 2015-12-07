package main;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.rosuda.REngine.*;
import org.rosuda.JRI.*;
import org.rosuda.JRI.REXP;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import scan.Scan;

public class main {
	
	private static String getRowCount(int rows) {
		if (rows > 10)
			return "10+";
		else
			return ""+rows;
	}
	
	public static void main(String args[]) throws URISyntaxException, SQLException {
		System.out.println("*///////////////////////*");
		System.out.println("*EMR REMOTE DB CONNECTOR*");
		System.out.println("*///////////////////////*");
		Connection conn = connection.DBConnection.getConnection();
		
		Scan scan = new Scan();
		System.out.println();
		System.out.println("Scanning all tables...");
		ArrayList<String> tables = scan.ScanTables(conn);
		for (String tablename : tables) {
			System.out.println("\t"+tablename);
			scan.ScanColumns(conn, tablename);
			scan.ScanDoubles(conn, tablename);
		}
		
		
		
//		System.out.println("Init R::::");
//		System.out.println(System.getProperty("java.library.path"));
//		Rengine rengine = new Rengine();
//		rengine.eval(String.format("greeting%s'", "Hello R World"));
//	      REXP result = rengine.eval("greeting     System.out.println(result.asString()");
//		
//		Statement statement = conn.createStatement();
//		ResultSet rs = statement.executeQuery("SELECT * FROM patientcorepopulatedtable limit(10)");
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int columns = rsmd.getColumnCount();
//		int rows = 0;
//		
//		while (rs.next()) {
//			rows++;
//			if (rows == 11)
//				break;
//		}
//		
//		System.out.println();
//		System.out.println("DB Snapshot:-");
//		System.out.println("Number of columns: " + columns);
//		for (int i =1; i< columns+1 ; i++) System.out.print(rsmd.getColumnLabel(i)+ "(" + rsmd.getColumnTypeName(i) + ")" + "\t");
//		System.out.println();
//		System.out.println("Number of rows: " + getRowCount(rows));
//		System.out.println();
//		rs.close();
//		
//		System.out.println("Top 10 rows:-");
//		rs = statement.executeQuery("SELECT * FROM patientcorepopulatedtable limit(10)");
//		DescriptiveStatistics stats = new DescriptiveStatistics();
////		for (int i =1; i< columns+1 ; i++) System.out.print(rsmd.getColumnLabel(i) + "\t\t");System.out.println();
//		while (rs.next()) {
//			for (int i =1; i< columns+1; i++) {
//				if (rsmd.getColumnTypeName(i).equals("int4")) {
//					System.out.print(rs.getInt(i)+"\t");
////					stats.addValue(rs.getInt(i));
//				}
//				else{
//					System.out.print(rs.getString(i)+"\t");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
//		double mean = stats.getMean();
//		double std = stats.getStandardDeviation();
//		double median = stats.getPercentile(50);
//		System.out.println("Summary Stats of admissionid: ");
//		System.out.println("Mean: " + mean);
//		System.out.println("Std.Dev: " + std);
//		System.out.println("Median: " + median);
		conn.close();
	}

}
