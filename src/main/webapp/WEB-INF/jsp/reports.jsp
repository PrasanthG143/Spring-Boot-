<%@page import="org.apache.taglibs.standard.tag.common.xml.ForEachTag"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%@include file="authheader.jsp"%>

<script>
	$(function() {

		$("#dateofjourney").datepicker();
		$("#endofjourney").datepicker();
	});

	function loadTrips() {

		name = $("#userid").val();
		$.ajax({
			type : 'get',
			url : 'getTrips',
			data : 'userid=' + name,
			success : function(html) {

				var result = "";
				var opt = "";
				$.each(html, function(k, v) {
					result = $(this).attr('id');
					//alert($(this).attr('tripunique'));
					opt = opt + "<option value=" + $(this).attr('tripunique')
							+ "> " + $(this).attr('tripunique') + "</option>";
				});
				//alert(opt);
				$("#tripunique").html(opt);
			}

		});

	}
</script>

<script>
	function newDoc() {
		window.location.href = "instantBills";
	}
</script>
</head>





<%@include file="sidebar.jsp"%>
<div class="row well read">

<input type="button" class="btn btn-success" value="Instant Bills"
		style="float: right" onclick="newDoc()">
</div>
<div class="row well read">
	<c:if test="${success!= null}">
		<div class="alert alert-success">${success}</div>
	</c:if>
	
	<h3>Test Form</h3>
	<form:form method="GET" action="getReports">
		<%
			Date date = new Date();
				long epoch = date.getTime();
		%>

		<!-- <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="groups">Groups</label>
                    <div class="col-md-7">
                        <select name="assigngroups" style="width:60%;" id="assigngroups" class="form-control input-sm">
                            <option>Select Groups</option>
                        </select>
                        <div class="has-error">
                        </div>
                    </div>
                </div>
            </div> -->
		<%
			ArrayList<String> al = (ArrayList<String>) request.getAttribute("loggedinuser");
		%>
<%-- 		<c:out value="<%=al%>"></c:out>
 --%>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="userid">Users</label>
				<div class="col-md-7">
					<select name="userid" id="userid" onchange="loadTrips()"
						style="width: 60%;" class="form-control input-sm">
						<option value="">Select Users</option>
						<%
							for (int x = 0; x < al.size(); x++) {%> 
						<option value="<%=al.get(x)%>"><b><%=al.get(x)%></b></option>
						<%}%>
					</select>
					<div class="has-error"></div>
				</div>
			</div>
		</div>
		



		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="tripunique">Trips</label>
				<div class="col-md-7">
					<select name="tripunique" style="width: 60%;" id="tripunique"
						class="form-control input-sm">
						<option value="select">Select Trips</option>
						<%--  <c:forEach var="entry" items="${result}"> 
                                  </c:forEach> --%>
					</select>

					<div class="has-error"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="source">ReportType</label>

				<div class="col-md-7">
					<select name="reporttype" id="reporttype" style="width: 60%;"
						class="form-control input-sm">
						<option value="html">Html</option>
						<option value="excel">Excel</option>
					</select>

					<div class="has-error"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="startdate">StartDate</label>
				<div class="col-md-7">
					<input type="date" name="startdate" style="width: 60%;"
						id="startdate" class="form-control input-sm">
					<div class="has-error"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="enddate">EndDate</label>
				<div class="col-md-7">
					<input type="date" name="enddate" style="width: 60%;" id="enddate"
						value="" class="form-control input-sm">
					<div class="has-error"></div>
				</div>
			</div>
		</div>


		<center>
			<div class="row">
				<div class="form-actions">
					<input type="submit" value="Reports" class="btn btn-primary btn-sm" />
				</div>
			</div>
			<center>
	</form:form>
</div>
<%@include file="footer.jsp"%>
