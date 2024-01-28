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
			<td>StudentMobile:</td>
			<td>${student.studentMobile}</td>
		</tr>
		<tr>
			<td>StudentDOB:</td>
			<td>${student.studentDOB}</td>
		</tr>
		<tr>
			<td>StudentSKills:</td>
			<td>${student.studentSkills}</td>
		</tr>
	</table>
</body>
</html>