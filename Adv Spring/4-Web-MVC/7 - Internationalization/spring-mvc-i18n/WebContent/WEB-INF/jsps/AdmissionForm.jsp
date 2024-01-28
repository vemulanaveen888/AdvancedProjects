<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<a href="/spring-mvc-i18n/app/admissionForm.html?sitelanguage=en">English</a>
<a href="/spring-mvc-i18n/app/admissionForm.html?sitelanguage=fr">French</a>

	<h1>Student Admission Form</h1>
	<form:errors path="student.*"/>
	<form
		action="/spring-mvc-i18n/app/submitAdmissionForm.html"
		method="POST">
		<table>
			<tr>
				<td><spring:message code="label.studentName"/></td>
				<td><input type="text" name="studentName" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.studentHobby"/></td>
				<td><input type="text" name="studentHobby" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.studentMobile"/></td>
				<td><input type="text" name="studentMobile" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.studentDOB"/></td>
				<td><input type="text" name="studentDOB" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.studentSkills"/></td>
				<td><select name="studentSkills" multiple>
						<option value="java">Java Core</option>
						<option value="Spring">Spring</option>
						<option value="Hibernate">Hibernate</option>
				</select></td>
			</tr>
		</table>
		
		<table>
			<tr>
				<td><spring:message code="label.studentAddress"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.country"/><input type="text" name="studentAddress.country" /></td>
				<td><spring:message code="label.city"/><input type="text" name="studentAddress.city" /></td>
				<td><spring:message code="label.street"/><input type="text" name="studentAddress.street" /></td>
				<td><spring:message code="label.pincode"/><input type="text" name="studentAddress.pincode" /></td>
			</tr>
		</table>
		<input type="submit" value="Submit this form by clicking here" />
	</form>
</body>
</html>