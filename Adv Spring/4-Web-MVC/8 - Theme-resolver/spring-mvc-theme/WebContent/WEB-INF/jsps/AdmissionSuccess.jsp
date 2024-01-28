<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>${msg}</h1>

	<h3>congratualtion!</h3>

	<table>
		<tr>
			<td>StudentName:</td>
			<td>${student.studentName}</td>
		</tr>
		<tr>
			<td>StudentHobby:</td>
			<td>${student.studentHobby}</td>
		</tr>
		<tr>
			<td>StudentHobby:</td>
			<td>${student.studentMobile}</td>
		</tr>
		<tr>
			<td>StudentHobby:</td>
			<td>${student.studentDOB}</td>
		</tr>
		<tr>
			<td>StudentHobby:</td>
			<td>${student.studentSkills}</td>
		</tr>
		<tr>
			<td>Student Address</td>
			<td>Country:${student.studentAddress.country}
				City:${student.studentAddress.city}
				Street:${student.studentAddress.street}
				Pincode:${student.studentAddress.pincode}</td>
		</tr>
	</table>
</body>
</html>