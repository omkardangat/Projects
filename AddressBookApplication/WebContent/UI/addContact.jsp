<%@page import="main.addressbook.model.Contact"%>
<%@page import="main.addressbook.controller.ContactDriver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact</title>
</head>
<body>
	<%
		ContactDriver driver = new ContactDriver();
	
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String primaryPhone = request.getParameter("primaryPhone");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String webAddress = request.getParameter("webAddress");
		String secondaryPhone = request.getParameter("secondaryPhone");
		String streetAddress = request.getParameter("streetAddress");
		Contact contact = new Contact(firstName, lastName, email, Long.parseLong(primaryPhone), city, state, Long.parseLong(zip));
		if(webAddress != null && !webAddress.isEmpty()) {
			contact.setWebAddress(webAddress);
		}
		if(secondaryPhone != null && !secondaryPhone.isEmpty()) {
			contact.setSecondaryPhone(Long.parseLong(secondaryPhone));
		}
		if(streetAddress != null && !streetAddress.isEmpty()) {
			contact.setStreetAddress(streetAddress);
		}
		String message = driver.insertContact(contact);		
	%>
	<h2><%= message %>.</h2>
	
	<br>
	<br>
	<a href="/AddressBookApplication/index.jsp">home </a>
</body>
</html>