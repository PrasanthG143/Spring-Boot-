<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="authheader.jsp"%>
<%@include file="sidebar.jsp"%>
<div class="row well lead">
	<div class="well lead">User Registration Form</div>
	<div class="success">${result}</div>
	<form:form method="POST" modelAttribute="user" class="form-horizontal">

		<form:input type="hidden" name="id" path="id" id="id" />

		<label class="col-md-3 control-lable" for="firstName">First
			Name</label>
		<div class="col-md-7">
			<form:input type="text" path="firstName" id="firstName"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="firstName" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable" for="lastName">Last Name</label>
		<div class="col-md-7">
			<form:input type="text" path="lastName" id="lastName"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="lastName" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
		<div class="col-md-7">
			<c:choose>
				<c:when test="${edit}">
					<form:input type="text" path="ssoId" id="ssoId"
						class="form-control input-sm" disabled="true" />
				</c:when>
				<c:otherwise>
					<form:input type="text" path="ssoId" id="ssoId"
						class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="ssoId" class="help-inline" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<label class="col-md-3 control-lable" for="password">Password</label>
		<div class="col-md-7">
			<form:input type="password" path="password" id="password"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="password" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable" for="email">Email</label>
		<div class="col-md-7">
			<form:input type="text" path="email" id="email"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="email" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
		<div class="col-md-7">
			<form:select path="userProfiles" items="${roles}" multiple="true"
				itemValue="id" itemLabel="type" class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="userProfiles" class="help-inline" />
			</div>
		</div>
		<br><br>
		<label class="col-md-3 control-lable" for="groupId">Groups</label>
		<div class="col-md-7">
			<form:select path="groupId" class="form-control input-sm">

				<c:forEach items="${groups}" var="group">
					<form:option value="${group.id}">${group.groupName}</form:option>
				</c:forEach>
			</form:select>

			<div class="has-error">
				<form:errors path="groupId" class="help-inline" />
			</div>
		</div>
		<label class="col-md-3 control-lable" for="referenceId">Users</label>
		<div class="col-md-7">
			<form:select path="referenceId" class="form-control input-sm">

				<c:forEach items="${users}" var="user">
					<form:option value="${user.id}">${user.firstName}</form:option>
				</c:forEach>
			</form:select>
			<div class="has-error">
				<form:errors path="referenceId" class="help-inline" />
			</div>
		</div>

		<div class="col-md-7">
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='/list' />">Cancel</a>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Register"
						class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='/list' />">Cancel</a>
				</c:otherwise>
			</c:choose>
		</div>

	</form:form>
</div>
<%@include file="footer.jsp"%>
