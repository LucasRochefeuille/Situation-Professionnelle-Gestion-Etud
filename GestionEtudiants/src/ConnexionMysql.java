import javax.swing.*;
import java.sql.*;

public class ConnexionMysql {

		Connection conn = null;
		
		public static Connection ConnexionDB(){
			try{
				
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionetudiant", "root","");
				return conn;
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
				return null;
			}


		}
}
