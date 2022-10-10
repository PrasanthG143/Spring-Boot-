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

<script>
	function initMap() {
		var map = new google.maps.Map(document.getElementById('map'), {});
		var map1 = new google.maps.Map(document.getElementById('map1'), {});
		var input =document.getElementById('source');
		var input1 = document.getElementById('destination');
		var autocomplete = new google.maps.places.Autocomplete(input);
		autocomplete.bindTo('bounds', map);
		var autocomplete1 = new google.maps.places.Autocomplete(input1);
		autocomplete1.bindTo('bounds', map1);
	}
</script>

<script
	src="https://maps.googleapis.com/maps/api/"
	async defer></script>
<style>
.help-inline {
	color: red;
	font-size: 13px;
}
</style>
</head>




<%@include file="authheader.jsp"%>
<%@include file="sidebar.jsp"%>
<div id="map"></div>
<div id="map1"></div>
<div class="row well lead">
	<c:if test="${success!= null}">
		<div class="alert alert-success">${success}</div>
	</c:if>
	<h4 class=""><b>CREATE TRIP</b></h4>
	<form:form method="POST"
		action="newtrip?${_csrf.parameterName}=${_csrf.token}"
		modelAttribute="newtrip" id="create" class="form-horizontal"
		enctype="multipart/form-data">
		<%
			Date date = new Date();
				long epoch = date.getTime();
		%>

		<form:input type="hidden" path="id" id="id" />
		<form:input type="hidden" path="tripunique"
			value="${loggedinuser}-<%=epoch%>" name="tripunique" id="tripunique" />
		<form:input type="hidden" path="status" name="status" id="status"
			value="open" />
		<form:input type="hidden" path="tripstatus" name="tripstatus"
			id="tripstatus" value="open" />
		<form:input type="hidden" path="paymentstatus" name="paymentstatus"
			id="paymentstatus" value="no" />
		<form:input type="hidden" path="tripclosetime" name="tripclosetime"
			id="tripclosetime" value="0" />
		<form:input type="hidden" path="settleamount" name="settleamount"
			id="settleamount" value="0" />
		<form:input type="hidden" path="advanceamount" name="advanceamount"
			id="advanceamount" value="0" />

		<label class="col-md-3 control-lable" for="userid">User ID</label>
		<div class="col-md-7">
			<form:input type="text" path="userid" id="userid"
				value="${loggedinuser}" class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="userid" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable" for="tripname">Trip Name</label>
		<div class="col-md-7">
			<form:input type="text" path="tripname" id="tripname"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="tripname" class="help-inline" />
			</div>
		</div>




		<label class="col-md-3 control-lable" for="designation">Trip Description</label>
		<div class="col-md-7">
			<form:input type="text" path="designation" id="designation"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="designation" class="help-inline" />
			</div>
		</div>


		<label class="col-md-3 control-lable" for="source">Source</label>
		<div class="col-md-7">
			<form:input type="text" path="source" id="source"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="source" class="help-inline" />
			</div>
		</div>


		<label class="col-md-3 control-lable" for="destination">Destination</label>
		<div class="col-md-7">
			<form:input type="text" path="destination" id="destination"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="destination" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable" for="estimatedamount">Estimated Amount</label>
		<div class="col-md-7">
			<form:input type="number" min="0" path="estimatedamount"
				id="estimatedamount" class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="estimatedamount" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable" for="dateofjourney">Date of Journey</label>
		<div class="col-md-7">
			<form:input type="text" path="dateofjourney" id="dateofjourney"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="dateofjourney" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable" for="endofjourney">End of Journey</label>
		<div class="col-md-7">
			<form:input type="text" path="endofjourney" id="endofjourney"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="endofjourney" class="help-inline" />
			</div>
		</div>
		<label class="col-md-3 control-lable" for="path1">TripImage</label>
		<div class="col-md-7">
			<form:input type="file" path="path1" id="path1"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="path1" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable hidden" for="assigngroup">Assigngroup</label>
		<div class="col-md-7">
			<form:input type="hidden" path="assigngroup" id="assigngroup" value="online"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="assigngroup" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable hidden" for="accountname">Account Name</label>
		<div class="col-md-7">
			<form:input type="hidden" path="accountname" id="accountname" value="prasanth"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="accountname" class="help-inline" />
			</div>
		</div>

		<label class="col-md-3 control-lable hidden" for="noofnights">Days</label>
		<div class="col-md-7">
			<form:input type="hidden" path="noofnights" id="noofnights" value="0"
				class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="noofnights" class="help-inline" />
			</div>
		</div>
		

		<%--  <div class="row">
                  <div class="form-group col-md-12">
                      <label class="col-md-3 control-lable" for="userProfiles">Roles</label>
                      <div class="col-md-7">
                          <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
                          <div class="has-error">
                              <form:errors path="userProfiles" class="help-inline"/>
                          </div>
                      </div>
                  </div>
              </div>--%>

		<div class="col-md-7">
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='/trips' />">Cancel</a>
				</c:when>
				<c:otherwise>
					<input type="submit" value="CREATE TRIP"
						class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='/trips' />">Cancel</a>
				</c:otherwise>
			</c:choose>
		</div>

	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#dateofjourney').bootstrapMaterialDatePicker({
			time : false,
			clearButton : true,
			format : 'MM-DD-YYYY'
		});
		$('#endofjourney').bootstrapMaterialDatePicker({
			time : false,
			clearButton : true,
			format : 'MM-DD-YYYY'
		});
	});
</script>
<%@include file="footer.jsp"%>