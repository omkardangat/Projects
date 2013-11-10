<%@page import="java.util.List"%>
<%@page import="main.addressbook.model.Contact"%>
<%@page import="main.addressbook.controller.ContactDriver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Contact</title>

<script type="text/javascript">

function validateNumber(phone) {
	return (phone - 0) == phone;
}

function validateEmail(email) {	  
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validateUpdateContactForm() {
	var firstName = document.forms["updateContact"]["firstName"].value;
	var lastName = document.forms["updateContact"]["lastName"].value;
	var email = document.forms["updateContact"]["email"].value;
	var primaryPhone = document.forms["updateContact"]["primaryPhone"].value;
	var city = document.forms["updateContact"]["city"].value;
	var state = document.forms["updateContact"]["state"].value;
	var zip = document.forms["updateContact"]["zip"].value;
	var secondaryPhone = document.forms["updateContact"]["secondaryPhone"].value;
	
	if (firstName == null || firstName == "") {
  		alert("First name must not be empty");
  		return false;
  	} else if(lastName == null || lastName == "") {
  		alert("Last name must not be empty");
  		return false;
  	} else if(email == null || email == "") {
  		alert("Email Address must not be empty");
  		return false;
  	} else if(primaryPhone == null || primaryPhone == "") {
  		alert("Primary phone must not be empty");
  		return false;
  	} else if(city == null || city == "") {
  		alert("City must not be empty");
  		return false;
  	} else if(state == null || state == "") {
  		alert("State must not be empty");
  		return false;
  	} else if(zip == null || zip == "") {
  		alert("Zip must not be empty");
  		return false;
  	} else if (!validateEmail(email)) {
		alert("Invalid email address");
		return false;
	} else if(!validateNumber(primaryPhone)) {
		alert("Primary phone should be numeric. Example 4155703088");
		return false;
	} else if(!validateNumber(zip)) {
		alert("Zip should be numeric. Example 94132");
		return false;
	} else if(!validateNumber(secondaryPhone)) {
		alert("Secondary phone should be numeric. Example 4155703088");
		return false;
	}	
}
</script>

</head>
<body>
	<% 	ContactDriver driver = new ContactDriver();
		String email = request.getParameter("email");
		session.setAttribute("inputEmail", email);
		List<Contact> contacts = driver.searchContactByEmail(email);
		if(contacts == null || contacts.isEmpty()) { 
	%>
			<h3> Contact not found in database </h3>
	<% } else {
			Contact contact = contacts.get(0);
	%>
	<h2>Update Contact </h2>
	<form name = "updateContact" action="updateContact.jsp" method="post" onsubmit="return validateUpdateContactForm()" >
  		<p>First name*:   <input name="firstName" value= <%= contact.getFirstName() %>> Last name*: <input name="lastName" value= <%= contact.getLastName() %>></p>
  		<p>Email Address*: <input name="email" value= <%= contact.getEmail() %>> Primary Phone*: <input name="primaryPhone" value= <%= contact.getPrimaryPhone() %>></p>
	 	<p>City*: <input name="city" value= <%= contact.getCity() %>> State*: <input name="state" value= <%= contact.getState() %>> Zip*: <input name="zip" value= <%= contact.getZip() %>></p>
	  	<p>Web Address: <input name="webAddress" value= <% String webAddress = contact.getWebAddress(); 
	  														if(webAddress != null) {
	  															out.print(webAddress);
	  														}%>>
	  	Secondary Phone: <input name="secondaryPhone" value= <% long secondaryPhone = contact.getSecondaryPhone(); 
	  														if(secondaryPhone != 0) {
	  															out.print(secondaryPhone);
	  														}%>>
	  	Street Address: <input name="streetAddress" value= <% String streetAddress = contact.getStreetAddress(); 
	  														if(streetAddress != null) {
	  															out.print(streetAddress);
	  														}%>></p>
	  	<p><input type="submit" value="Submit"></p>
	</form>
	<br>
<br>
	
	<% } %>
	
	<br>
	<br>
	<a href="/AddressBookApplication/index.jsp">home </a>
</body>
</html>