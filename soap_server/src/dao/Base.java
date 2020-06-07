package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Base {
	private static Connection connection;
	// quand il une classe est charge en memeoire ; le premiere bloc qui va s'executer est le bloc static 
		static {
			//charger le pilot jdbc
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ECOMMERCE","root","");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection() {
			return connection;
		}
}
