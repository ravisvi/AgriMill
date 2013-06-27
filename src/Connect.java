
import java.sql.*;
public class Connect
{
	public Connection conn = null;
	public void connect()
	{ 
		 
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		
			System.out.println("Connecting to database..."); 
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mill","ordell","password"); 
			
		}
		catch(SQLException se)
		{
			//Handle errors for JDBC 
			se.printStackTrace(); 
		}
		catch(Exception e)
		{ //Handle errors for Class.forName 
			e.printStackTrace();
		}
		
        }
}
