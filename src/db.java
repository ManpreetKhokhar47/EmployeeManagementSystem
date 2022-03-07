
import java.sql.*;

import javax.swing.*;

public class db extends JFrame {
	
	Connection con;
	
	
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String dbuser = "system";
	String dbpwd = "system";
	
	db()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,dbuser,dbpwd);
		
		}
		catch(Exception e)
		{
			System.out.print("Error :" + e );
		}
		
		
	}

}
