<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Form</title>
<script
	src="<c:url value='http://code.jquery.com/ui/1.10.4/jquery-ui.js'/>"
	rel="stylesheet" />
</script>
<script
	src="<c:url value='https://maps.googleapis.com/maps/api/js?sensor=true'/>"
	rel="stylesheet" />
</script>
<link
	href="<c:url value='http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css'/>"
	rel="stylesheet" />
<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {

		$("#dateofjourney").datepicker();
		$("#endofjourney").datepicker();
	});
</script>
</head>




<%@include file="authheader.jsp"%>
<%@include file="sidebar.jsp"%>
<div class="row well lead">
	<c:if test="${success!= null}">
		<div class="alert alert-success">${success}</div>
	</c:if>
	<h3 class="well lead">Test Form</h3>
	<form:form method="POST" modelAttribute="usermanagement"
		class="form-horizontal">

		

		<label class="col-md-3 control-lable" for="groupName">GroupName</label>
		<div class="col-md-7">
			<form:input type="text" path="groupName" id="groupName"
				name="groupName" class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="groupName" class="help-inline" />
			</div>
		</div>

		<div class="col-md-7">
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='/usermanagement' />">Cancel</a>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Create Group"
						class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='/home' />">Cancel</a>
				</c:otherwise>
			</c:choose>
		</div>

	</form:form>

	</br>
	</br>
	</br>
	</br>

	<table class="table">
		<tr>
			<th>Sno</th>
			<th>GroupName</th>
		</tr>
		<%--    <c:out value="${groups}"></c:out> --%>
		<c:forEach items="${groups}" var="user">
			</tbody>
			<tr>
				<td>${user.id}</td>
				<td>${user.groupName}</td>
			</tr>

			<tbody>
		</c:forEach>

	</table>


</div>
<%@include file="footer.jsp"%>