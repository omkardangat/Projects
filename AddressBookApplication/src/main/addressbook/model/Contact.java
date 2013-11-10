package main.addressbook.model;

public class Contact {

	private String firstName;
	private String lastName;
	private String email;
	private long primaryPhone;
	private String city;
	private String state;
	private long zip;
	private String webAddress;
	private long secondaryPhone;
	private String streetAddress;
	
	/**
	 * 
	 * @param firstName String
	 * @param lastName String
	 * @param email String
	 * @param primaryPhone Long
	 * @param city String
	 * @param state String
	 * @param zip Long
	 */
	public Contact(String firstName, String lastName, String email, long primaryPhone, String city, String state, long zip) {
		this.setCity(city);
		this.setEmail(email);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPrimaryPhone(primaryPhone);
		this.setState(state);
		this.setZip(zip);
	}

	/**
	 * Get first name
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set first name
	 * @param firstName String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get last name
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * set last name
	 * @param lastName String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * get email
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set email
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * get primary phone
	 * @return long
	 */
	public long getPrimaryPhone() {
		return primaryPhone;
	}

	/**
	 * set primary phone
	 * @param primaryPhone long
	 */
	public void setPrimaryPhone(long primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	/**
	 * get city
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * set city
	 * @param city String
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * get State
	 * @return String
	 */
	public String getState() {
		return state;
	}

	/**
	 * set state
	 * @param state String
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * get zip	
	 * @return long
	 */
	public long getZip() {
		return zip;
	}

	/**
	 * set zip
	 * @param zip long
	 */
	public void setZip(long zip) {
		this.zip = zip;
	}

	/**
	 * get web address
	 * @return String
	 */
	public String getWebAddress() {
		return webAddress;
	}

	/**
	 * ser web address
	 * @param webAddress String
	 */
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	/**
	 * get secondary phone
	 * @return long
	 */
	public long getSecondaryPhone() {
		return secondaryPhone;
	}

	/**
	 * set secondary phone
	 * @param secondaryPhone long
	 */
	public void setSecondaryPhone(long secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	/**
	 * get Street address
	 * @return String
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * set street address
	 * @param streetAddress String
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	/**
	 * Print contact details - For testing purpose
	 */
	public void printContactDetails() {
		System.out.println(this.firstName + " " + this.lastName + " " + this.city + " " + this.email + " " + this.primaryPhone + " " + this.state);
	}
	
}
