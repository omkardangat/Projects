<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test JSP</title>

<script type="text/javascript">
function validateEmail(email) {	  
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function showConfirm() {
	var email = document.forms["deleteContact"]["email"].value;
	if (!validateEmail(email)) {
		alert("Invalid email address");
		return false;
	} else {
		var r=confirm("Delete conatact with email " + email + " ? ");
    	return r;
	}
}

function validateNumber(phone) {
	return (phone - 0) == phone;
}

function validateModifyContactForm() {
	var email = document.forms["modifyContact"]["email"].value;
	if (!validateEmail(email)) {
		alert("Invalid email address");
		return false;
	}
}

function validateAddContactForm()
{
	var firstName = document.forms["addContact"]["firstName"].value;
	var lastName = document.forms["addContact"]["lastName"].value;
	var email = document.forms["addContact"]["email"].value;
	var primaryPhone = document.forms["addContact"]["primaryPhone"].value;
	var city = document.forms["addContact"]["city"].value;
	var state = document.forms["addContact"]["state"].value;
	var zip = document.forms["addContact"]["zip"].value;
	var secondaryPhone = document.forms["addContact"]["secondaryPhone"].value;
	
	if (firstName == null || firstName == "") {
  		alert("First name must be filled out");
  		return false;
  	} else if(lastName == null || lastName == "") {
  		alert("Last name must be filled out");
  		return false;
  	} else if(email == null || email == "") {
  		alert("Email Address must be filled out");
  		return false;
  	} else if(primaryPhone == null || primaryPhone == "") {
  		alert("Primary phone must be filled out");
  		return false;
  	} else if(city == null || city == "") {
  		alert("City must be filled out");
  		return false;
  	} else if(state == null || state == "") {
  		alert("State must be filled out");
  		return false;
  	} else if(zip == null || zip == "") {
  		alert("Zip must be filled out");
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

<h2>Add Contact </h2>
<form name = "addContact" action="UI/addContact.jsp" method="post" onsubmit="return validateAddContactForm()" >
  <p>First name*:   <input name="firstName"> Last name*: <input name="lastName"></p>
  <p>Email Address*: <input name="email"> Primary Phone*: <input name="primaryPhone"></p>
  <p>City*: <input name="city"> State*: <input name="state"> Zip*: <input name="zip"></p>
  <p>Web Address: <input name="webAddress">
  Secondary Phone: <input name="secondaryPhone">
  Street Address: <input name="streetAddress"></p>
  <p><input type="submit" value="Submit"></p>
</form>
<br>

<h2>Search Contact </h2>
	<form action="UI/search.jsp" method="post">
  	<p>
  	<p>First Name and/or Last Name : <input name="name" value="">
  	   <b>OR</b> Email Address: <input name="email" value="">
  		<input type="submit" value="Search">
  	</p>
	</form>
</body>
<br>

<h2>Delete Contact </h2>
	<form name ="deleteContact" action="UI/delete.jsp" method="post" onsubmit="return showConfirm()">
  	<p>
  	<p> Email Address: <input name="email" value="">
  		<input type="submit" value="Delete" >
  	</p>
	</form>
</body>
<br>

<h2> Modify Contact </h2>
<form name = "modifyContact" action="UI/modifyContact.jsp" method="post" onsubmit="return validateModifyContactForm()" >
  <p>Email Address: <input name="email"> 
  <input type="submit" value="Update"></p>
</form>

</html>

