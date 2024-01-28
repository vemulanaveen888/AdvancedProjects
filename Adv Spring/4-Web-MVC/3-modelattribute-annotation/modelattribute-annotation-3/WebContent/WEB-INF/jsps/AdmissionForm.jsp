<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Student Admission Form</h1>
	<form
		action="/modelattribute-annotation-3/app/submitAdmissionForm.html"
		method="POST">
		<table>
			<tr>
				<td>Student's Name:</td>
				<td><input type="text" name="studentName" /></td>
			</tr>
			<tr>
				<td>Student's Hobby :</td>
				<td><input type="text" name="studentHobby" /></td>
			</tr>
			<tr>
				<td>Student's Mobile:</td>
				<td><input type="text" name="studentMobile" /></td>
			</tr>
			<tr>
				<td>Student's DOB:</td>
				<td><input type="text" name="studentDOB" /></td>
			</tr>
			<tr>
				<td>Student's Skills set:</td>
				<td><select name="studentSkills" multiple>
						<option value="java">Java Core</option>
						<option value="Spring">Spring</option>
						<option value="Hibernate">Hibernate</option>
				</select></td>
			</tr>
		</table>
		
		<table>
			<tr>
				<td>Students Address</td>
			</tr>
			<tr>
				<td>Country:<input type="text" name="studentAddress.country" /></td>
				<td>City:<input type="text" name="studentAddress.city" /></td>
				<td>Street:<input type="text" name="studentAddress.street" /></td>
				<td>Pincode:<input type="text" name="studentAddress.pincode" /></td>
			</tr>
		</table>
		<input type="submit" value="Submit this form by clicking here" />
	</form>
</body>
</html>