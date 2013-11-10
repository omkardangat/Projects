package test;

import java.sql.SQLException;
import java.util.List;

import main.addressbook.controller.ContactDriver;
import main.addressbook.model.Contact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAddessBook {

	ContactDriver driver;
	
	@Before
	public void SetupDatabaseConnection() {
		try {
			driver = new ContactDriver();
		} catch (SQLException e) {
			System.out.println("Invalid username or password - " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestAddContact() { 
		Contact contact = new Contact("Omkar", "Dangat", "omkar.dangat@gmail.com", 1234567890, "SF", "CA", 94132);
		String message = driver.insertContact(contact);
		if(message.equals("contact has been added") || message.equals("Duplicate record")) {
			Assert.assertNotNull(message);
		} else {
			Assert.fail();
		}
	}
	
	@Test
	public void TestUpdateContact() {
		Contact contact = new Contact("Omkar", "Dangat", "om_dangat@yahoo.co.in", 1234567890, "MountainView", "CA", 94040);
		int success = driver.updateContact("omkar.dangat@gmail.com", contact);
	}
	
	@Test
	public void TestAddContact2() { 
		Contact contact = new Contact("Omkar", "Kwik", "omkar.dangat@gmail.com", 1234567892, "SF", "CA", 94040);
		String message = driver.insertContact(contact);
		if(message.equals("contact has been added") || message.equals("Duplicate record")) {
			Assert.assertNotNull(message);
		} else {
			Assert.fail();
		}
	}
	
	@Test
	public void TestSearchContact() {
		List<Contact> contacts = driver.searchContactByEmail("om_dangat@yahoo.co.in");
		for (Contact element : contacts) {
			if(element != null) {
				element.printContactDetails();
			}
		}
		
		List<Contact> list = driver.searchContactByName(new String[] {"omkar", "kwik"});
		for (Contact element : list) {
			if(element != null) {
				element.printContactDetails();
			}
		}
	}
	
	@Test 
	public void TestDeleteContact() {
		// need to test through mysql
		int success = driver.deleteContact("om_dangat@yahoo.co.in");
		success = driver.deleteContact("omkar.dangat@gmail.com");
	}
}
