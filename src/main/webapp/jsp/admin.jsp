<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1,  shrink-to-fit=no charset=utf-8" />
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/elements.css">
<title>Admin page</title>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container-fluid bg">
		<div class="form-inline">
			<form action="controller" method="POST">
				<input type="text" name="name" value="" class="form-control"
					placeholder=<fmt:message key="label.name"/>> <input
					type="hidden" name="command" value="find_by_name">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.find" />
				</button>
			</form>
		</div>
			<p>${message}</p>
		<br />
		<div>
			<form action="controller" method="POST">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.find_all" />
				</button>
				<input type="hidden" name="command" value="find_all_users">
			</form>
		</div>
		<br />
		<div class="form-inline">
			<form action="controller" method="POST">
				<input type="text" class="form-control" name="email_to" placeholder="email" />
				<button type="submit" class="btn btn-primary">Send email</button>
				<input type="hidden" name="command" value="send_email">
			</form>
		</div>
	</div>
	<table class="table table-striped">	
		<c:if test="${list ne null}">
			<tr>
				<th><fmt:message key="label.number" /></th>
				<th><fmt:message key="label.id" /></th>
				<th><fmt:message key="label.name" /></th>
				<th><fmt:message key="label.surname" /></th>
				<th><fmt:message key="label.email" /></th>
				<th><fmt:message key="label.phone_column" /></th>
				<th><fmt:message key="label.login" /></th>
				<th><fmt:message key="label.blocked" /></th>
			</tr>
		</c:if>

		<c:forEach var="elem" items="${list}" varStatus="status">
			<tr>
				<td><c:out value="${status.count }" /></td>
				<td><c:out value="${elem.userId }" /></td>
				<td><c:out value="${elem.name }" /></td>
				<td><c:out value="${elem.surname }" /></td>
				<td><c:out value="${elem.email }" /></td>
				<td><c:out value="${elem.phone }" /></td>
				<td><c:out value="${elem.login }" /></td>
				<td><c:out value="${elem.blocked }" /></td>
			</tr>
		</c:forEach>

	</table>

	<c:import url="footer.jsp" />
</body>
</html>
