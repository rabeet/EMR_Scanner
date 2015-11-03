package connection;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class DBConnection {
	
	// JDBC DB URI
	static final String DATABASE_URL = "postgres://uietljximqcodo:AyyGzaUkex4JQWud2EjHVnYIpH@ec2-107-21-219-142.compute-1.amazonaws.com:5432/d94t2ckoqdr0k1";
	
	public static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbURI = new URI(DATABASE_URL);
		
		String username = dbURI.getUserInfo().split(":")[0];
		String password = dbURI.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbURI.getHost() + ":" + dbURI.getPort() + dbURI.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		System.out.println("User: " + username);
		System.out.println("Pass: " + password);
		System.out.println("URL: " + dbUrl);
		
		return DriverManager.getConnection(dbUrl, username, password);
	}

}
