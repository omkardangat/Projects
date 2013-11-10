<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "main.addressbook.controller.*" %>
<%@page import = "main.addressbook.model.*" %>
<%@page import = "java.util.ArrayList" %>
<%@page import = "java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Results</title>
</head>
<body>
	<%	ContactDriver driver = new ContactDriver();
		List<Contact> list = null;
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		if(name != null && !name.isEmpty()) {
			String[] names = name.split(" ");
			list = driver.searchContactByName(names);
		} else if(email != null && !email.isEmpty()) {
			list = driver.searchContactByEmail(email);
		}
		
		if(list == null || list.isEmpty()) { %>
			<h1> No Record found</h1>
		<% }  else { %>
	<table border="1">
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email Address</th>
			<th>Primary Phone Number</th>
			<th>City</th>
			<th>State</th>
			<th>Zip</th>
			<th>Web Address</th>
			<th>Secondary Phone Number</th>
			<th>Street Address</th>
		</tr>
	<% 	for (Contact element : list) { %>
		<tr>
			<td><%= element.getFirstName() %> </td>
			<td><%= element.getLastName() %> </td>
			<td><%= element.getEmail() %> </td>
			<td><%= element.getPrimaryPhone() %> </td>
			<td><%= element.getCity() %> </td>
			<td><%= element.getState() %> </td>
			<td><%= element.getZip() %> </td>
			<td><% String webAddress = element.getWebAddress(); 
				if(webAddress != null) {
					out.print(webAddress); 
				} %> 
			</td>
			<td><% long secondaryPhone = element.getSecondaryPhone(); 
				if(secondaryPhone != 0) {
					out.print(webAddress);
				} %> 
			</td>			
			<td><% String streetAddress = element.getStreetAddress();
				if(streetAddress != null) {
					out.print(streetAddress);
				} %> </td>
		</tr>
		<% } 
		} // else closed %>
	</table>
	
	<br>
	<br>
	<a href="/AddressBookApplication/index.jsp">home </a>	
</body>
</html>