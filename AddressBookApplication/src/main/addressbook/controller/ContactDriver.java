package main.addressbook.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.addressbook.database.DatabaseAccess;
import main.addressbook.model.Contact;

public class ContactDriver {

	private DatabaseAccess access;
	
	/**
	 * Initilize ContactDriver instance
	 * @throws SQLException
	 */
	public ContactDriver() throws SQLException {
		if(access == null)
			access = getDatabaseConnection();
	}
	
	/**
	 * get database connection
	 * @return DatabaseAccess
	 */
	public DatabaseAccess getDatabaseConnection() {
		try {
			return new DatabaseAccess();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();			
		}
		return null;
	}
	
	/**
	 * insert contact
	 * @param contact Contact
	 * @return String
	 */
	public String insertContact(Contact contact) {
		if(access != null) {
			try {
				return access.insertContactToDatabase(contact);
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return e.getMessage();
			}
		}
		return "Database connection error";
	}
	
	/**
	 * update contact
	 * @param email String
	 * @param contact Contact
	 * @return int
	 */
	public int updateContact(String email, Contact contact) {
		if(access != null) {
			try {
				return access.updateContactToDatabase(email, contact);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * delete contact
	 * @param email String
	 * @return int
	 */
	public int deleteContact(String email) {
		if(access != null) {
			try {
				return access.deleteContactFromDatabase(email);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		return 0;
	}
	
	/**
	 * serch contact by name
	 * @param names String[]
	 * @return List<Contact>
	 */
	public List<Contact> searchContactByName(String[] names) {
		List<Contact> contacts = null;
		if(access != null) {
			try {
				if(names != null) {
					ResultSet resultSet= null;
					if(names.length == 1) {
						resultSet = access.searchContactByName(names[0]);						
					} else if(names.length == 2) {
						resultSet = access.searchContactByFirstAndLastName(names);						
					}
					
					if(resultSet != null) {
						contacts = getContacts(resultSet);
					}
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		return contacts;
	}
	
	/**
	 * search contact by email
	 * @param email String
	 * @return List<Contact>
	 */
	public List<Contact> searchContactByEmail(String email) {
		List<Contact> contacts = null;
		if(access != null) {
			try {
				ResultSet resultSet = access.searchContactByEmail(email);
				contacts = getContacts(resultSet);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		return contacts;
	}
	
	/**
	 * get Contatct from result set
	 * @param resultSet ResultSet
	 * @return List<Contact>
	 * @throws SQLException
	 */
	private List<Contact> getContacts(ResultSet resultSet) throws SQLException {
		List<Contact> contacts = new ArrayList<Contact>();
		while(resultSet.next()) {
			Contact contact;
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String emailAddress = resultSet.getString("email");
			long primaryPhone = resultSet.getLong("primary_phone");
			String city = resultSet.getString("city");
			String state = resultSet.getString("state");
			long zip = resultSet.getLong("zip");
			String webAddress = resultSet.getString("web_address");
			long secondaryPhone = resultSet.getLong("secondary_phone");
			String streetAddress = resultSet.getString("street_address");
			
			contact = new Contact(firstName, lastName, emailAddress, primaryPhone, city, state, zip);
			contact.setWebAddress(webAddress);
			contact.setSecondaryPhone(secondaryPhone);
			contact.setStreetAddress(streetAddress);
			contacts.add(contact);
		}
		return contacts;
	}
}
