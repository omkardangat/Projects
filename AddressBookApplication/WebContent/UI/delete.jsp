<%@page import="main.addressbook.controller.ContactDriver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Contatc</title>
</head>
<body>
	<%	ContactDriver driver = new ContactDriver();
		String email = request.getParameter("email");
		int success = driver.deleteContact(email);
		if(success > 0) {
	%>
		<b> Contact has been deleted </b>
	<% } else { %>
		<b> Contact could not deleted. Either contact is not present or some error is deleting contact.</b>
	<% } %>
	
	<br>
	<br>
	<a href="/AddressBookApplication/index.jsp">home </a>
</body>
</html>