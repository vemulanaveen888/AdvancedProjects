<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>${headerMessage }</h1>
	<h1>${msg}</h1>

	<table>
		<tr>
			<td>StudentName:</td>
			<td>${s1.studentName}</td>
		</tr>
		<tr>
			<td>StudentHobby:</td>
			<td>${s1.studentHobby}</td>
		</tr>
	</table>
</body>
</html>