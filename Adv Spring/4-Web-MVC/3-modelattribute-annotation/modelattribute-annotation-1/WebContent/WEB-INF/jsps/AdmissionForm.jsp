<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>${headerMessage}</h1>
	
	<h1>Student Admission Form</h1>

	<form action="/modelattribute-annotation-1/app/submitAdmissionForm.html" method="POST">
		<p>
			Student's Name: <input type="text" name="studentName" />
		</p>
		<p>
			Student's Hobby : <input type="text" name="studentHobby" />
		</p>
		<input type="submit" value="Submit this form by clicking here" />
	</form>
</body>
</html>