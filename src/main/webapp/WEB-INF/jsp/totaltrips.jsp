<%-- 
    Document   : totaltrips
    Created on : 30 Nov, 2017, 10:54:11 AM
    Author     : glodeveloper
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<%@include file="authheader.jsp"%>
<%@include file="sidebar.jsp"%>
<style type="text/css">
body, html {
	height: 100%;
	background-repeat: no-repeat;
	/*               background:url('../css.png');*/
	font-family: 'Oxygen', sans-serif;
	background-size: cover;
}

.height {
	min-height: 160px;
}

.icon {
	font-size: 47px;
	color: white;
}

.iconbig {
	font-size: 77px;
	color: #5CB85C;
}

.table>tbody>tr>.emptyrow {
	border-top: none;
	background: url(../img/ta.png);
	background-size: cover;
	background-repeat: no-repeat;
}

.table>thead>tr>.emptyrow {
	border-bottom: none;
}

.table>tbody>tr>.highrow {
	border-top: 3px solid;
}

.panel-default>.panel-heading {
	background-color: #24e686;
	color: white;
	width: 270px;
	padding-right: 30px;
}

th, td {
	border: none;
	font-size: 16px;
	color: black;
	background: #fff;
}

table.table table-striped {
	table-layout: fixed;
	background: url(../css.png);
}

.display-font {
	font-weight: bolder;
	font-size: 14px;
	color: blue;
	font-family: "Georgia";
	content: normal;
}

strong {
	font-family: Georgia, Times, "Times New Roman", serif;
	font-size: 12px;
}

.panel-body {
	background-color: #e0f8f2;
	padding: 5px;
	width: 330px;
	padding-right: 30px;
	content-align: center;
}
</style>
<div class="row read well">
	<div class="col-xs-12">
		<div class="text-center"></div>

		<c:forEach items="${trips}" var="trips">
			<div class="col-xs-12 col-md-3 col-lg-3 pull-left ">
				<div class="panel panel-default height">
					<div class="panel-heading">
						<b> <a
							href="Details?userid=prash&tripunique=${trips.tripunique}"> <img
								src='../../TripImages/${trips.path}' width="250" height="400"
								alt="" /></a>
						</b>
					</div>

					<div class="panel-body">
						<table style="width: 100%;" background="../css.png">



							<tr>
								<!--	<td><strong>Urer ID:</strong></td>  -->
								<td><span class="display-font">${trips.userid}</span></td>

							</tr>
							<tr>
								<!-- 	<td><strong>TripName:</strong></td> -->
								<td><span class="display-font">${trips.tripname}</span></td>

							</tr>
							<tr>
								<!-- <td><strong>Source:</strong></td> -->
								<td><span class="display-font">${trips.source}</span></td>

							</tr>
							<tr>
								<!-- <td><strong>Destination:</strong></td> -->
								<td><span class="display-font">${trips.destination}</span></td>

							</tr>


							</div>
							</div>
							</div>
						</table>
					</div>

				</div>
			</div>
		</c:forEach>




	</div>
</div>




<!-- Default panel contents -->
<%-- <div class="panel-heading"><span class="lead">List of Trips </span></div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>UserID</th>
                            <th>Trip Name</th>
                            <th>Source</th>
                            <th>Destination</th>
                             <th>Estimated Amount</th>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')" >
                                <th width="100"></th>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN')">
                                <th width="100"></th>
                                </sec:authorize>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${trips}" var="trips">
                            <tr>
                                <td>${trips.userid}</td>
                                <td>${trips.tripname}</td>
                                <td>${trips.source}</td>
                                <td>${trips.destination}</td>
                                <td>${trips.estimatedamount}</td>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <td><a href="<c:url value='/edit-trips-${trips.id}' />" class="btn btn-success custom-width">edit</a></td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <td><a href="<c:url value='/delete-trips-${trips.id}' />" class="btn btn-danger custom-width">delete</a></td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
          
            <sec:authorize access="hasRole('ADMIN')">
                <div class="well">
                    <a href="<c:url value='/newtrip' />">Add New Trip</a>
                </div>
            </sec:authorize> --%>

<%@include file='footer.jsp'%>