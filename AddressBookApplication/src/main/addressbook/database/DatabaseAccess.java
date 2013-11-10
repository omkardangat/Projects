package main.addressbook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import main.addressbook.model.Contact;

public class DatabaseAccess {

	private Connection connection;
	private String databaseConnectionProperties = "{\"database_name\" : \"address_book\", \"user_name\" : \"root\", \"password\" : \"password\", \"url\" : \"jdbc:mysql://localhost/\"}";
	
	private String insertQuery = "insert into contacts (first_name, last_name, email, primary_phone, city, state, zip, web_address, secondary_phone, street_address) " 
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";  

	/**
	 * Initialize DatabaseAccess instance and connect to mysql 
	 * @throws SQLException
	 */
	public DatabaseAccess() throws SQLException {
		String userName;
		String password;
		String url;
		MySqlDriver.load();
		try {
			JSONObject object = new JSONObject(databaseConnectionProperties);
			userName = object.optString("user_name");
			password = object.optString("password");
			url = object.optString("url") + object.optString("database_name");
		} catch (Exception e) {
			e.printStackTrace();
			S
			userName = "root";
			password = "";
			url = "jdbc:mysql://localhost/address_book";
		} 
		
		connection = DriverManager.getConnection(url, userName, password);
	}
	
	/**
	 * Add contact to database
	 * @param contact Contact
	 * @return String
	 * @throws SQLException
	 */
	public String insertContactToDatabase(Contact contact) throws SQLException {
		ResultSet result = checkRecordPresentInDatabase(contact.getEmail());
		if(result != null && result.first()) {
			return "Duplicate record for " + contact.getEmail();
		}
		
		PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
		int index = 1;
		insertStmt.setString(index++, contact.getFirstName());
		insertStmt.setString(index++, contact.getLastName());
		insertStmt.setString(index++, contact.getEmail());
		insertStmt.setLong(index++, contact.getPrimaryPhone());
		insertStmt.setString(index++, contact.getCity());
		insertStmt.setString(index++, contact.getState());
		insertStmt.setLong(index++, contact.getZip());
		insertStmt.setString(index++, contact.getWebAddress());
		insertStmt.setLong(index++, contact.getSecondaryPhone());
		insertStmt.setString(index++, contact.getStreetAddress());
		int success = insertStmt.executeUpdate();
		if(success == 1) {
			return "Contact has been added";
		}
		return "Error in adding contact";
	}
	
	/**
	 * Update contact by email address
	 * @param email String
	 * @param contact Contact
	 * @return
	 * @throws SQLException
	 */
	public int updateContactToDatabase(String email, Contact contact) throws SQLException {
		String updateQuery = "update contacts set first_name = '" + contact.getFirstName() + "'" 
				+ ", last_name = '" + contact.getLastName() + "'"
				+ ", email = '" + contact.getEmail() + "'"
				+ ", primary_phone = " + contact.getPrimaryPhone() 
				+ ", city = '" + contact.getCity() + "'"
				+ ", state = '" + contact.getState() + "'"
				+ ", zip = " + contact.getZip();
				if(contact.getWebAddress() != null) {
					updateQuery += ", web_address = '" + contact.getWebAddress() + "'";
				}
				updateQuery += ", secondary_phone = " + contact.getSecondaryPhone();
				if(contact.getWebAddress() != null) {		
					updateQuery += ", street_address = '" + contact.getStreetAddress() + "'";
				}
				updateQuery += " where email = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		preparedStatement.setString(1, email);
		return preparedStatement.executeUpdate();
	}
	
	/**
	 * Delere contact from database
	 * @param email String
	 * @return int
	 * @throws SQLException
	 */
	public int deleteContactFromDatabase(String email) throws SQLException {
		String deleteQuery = "delete from contacts where email = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
		preparedStatement.setString(1, email);
		return preparedStatement.executeUpdate();
	}
	
	/**
	 * Search contact by email
	 * @param email String
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet searchContactByEmail(String email) throws SQLException {
		String query = "select * from contacts where email = ?";
		return executeQueryForOneParameter(query, email);
	}
	
	/**
	 * Search contact by first name
	 * @param firstName String
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet searchContactByFirstName(String firstName) throws SQLException {
		String query = "select * from contacts where first_name = ?";
		return executeQueryForOneParameter(query, firstName);
	}
	
	/**
	 * Search contact by first and last name
	 * @param names String[] - first and last name
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet searchContactByFirstAndLastName(String[] names) throws SQLException {
		String query = "select * from contacts where first_name = ? and last_name = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, names[0]);
		statement.setString(2, names[1]);
		return statement.executeQuery();
	}
	
	/**
	 * Search contact by name
	 * @param name String - first name or last name
	 * @return String
	 * @throws SQLException
	 */
	public ResultSet searchContactByName(String name) throws SQLException {
		String query = "select * from contacts where first_name = ? or last_name = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, name);
		return statement.executeQuery();
	}
	
	/**
	 * check record is present in database or not
	 * @param email String
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet checkRecordPresentInDatabase (String email) throws SQLException {
		String query = "select email from contacts where email = ?";
		return executeQueryForOneParameter(query, email);
	}
	
	/**
	 * Execute given query with one paramaeter
	 * @param query
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	private ResultSet executeQueryForOneParameter(String query, String parameter) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, parameter);
		return preparedStatement.executeQuery();
	}
}
