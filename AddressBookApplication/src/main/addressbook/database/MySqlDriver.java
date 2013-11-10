package main.addressbook.database;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDriver {

	/**
	 * Get Mysql Driver Name
	 * @return String
	 */
	public static String getDriverName() {
		return "com.mysql.jdbc.Driver";
	}
	
	/**
	 * Register mysql driver
	 * @throws SQLException
	 */
	static public void load() throws SQLException {
		String className = getDriverName();

		Driver driver = null;
		// Then try to instantiate the driver
		try {
			driver = (Driver) Class.forName(className).newInstance();
		} catch (Exception e) {
		}
		// Finally, register the driver
		DriverManager.registerDriver(driver);
	}
}
