<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='../../assets/materialize/css/materialize.min.css" media="screen,projection'/>">
<!-- Bootstrap Styles-->
<link
	href="<c:url value='../../assets/css/bootstrap.css" rel="stylesheet'/>">
<!-- FontAwesome Styles-->
<link
	href="<c:url value='../..//assets/css/font-awesome.css" rel="stylesheet'/>">
<!-- Morris Chart Styles-->
<link
	href="<c:url value='../../assets/js/morris/morris-0.4.3.min.css" rel="stylesheet'/>">
<!-- Custom Styles-->
<link
	href="<c:url value='../../assets/css/custom-styles.css" rel="stylesheet'/>">
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet"
	href="<c:url value='../../assets/js/Lightweight-Chart/cssCharts.css'/>">
</head>
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>

<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle waves-effect waves-dark"
					data-toggle="collapse" data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand waves-effect waves-dark" href="list"><i
					class="large material-icons"></i> <strong>TRACK</strong></a>

				<div id="sideNav" href="">
					<i class="material-icons dp48">toc</i>
				</div>
			</div>

			<ul class="nav navbar-top-links navbar-right">
				<!-- <li><a class="dropdown-button waves-effect waves-dark"
					href="#!" data-activates="dropdown4"><i
						class="fa fa-envelope fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
				<li><a class="dropdown-button waves-effect waves-dark"
					href="#!" data-activates="dropdown3"><i
						class="fa fa-tasks fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
				<li><a class="dropdown-button waves-effect waves-dark"
					href="#!" data-activates="dropdown2"><i
						class="fa fa-bell fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
				<li><a class="dropdown-button waves-effect waves-dark"
					href="#!" data-activates="dropdown1"><i
						class="fa fa-user fa-fw"></i> <b>John Doe</b> <i
			
						class="material-icons right">arrow_drop_down</i></a></li> -->
						
				
			<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
			</li>
		
			</ul> 
			
		</nav>
		<style>
		
		.control-lable{
		font-family: bold;
		font-size: 14px;
		color: black;
		}
		
		</style>
		</div>
		<!-- Dropdown Structure -->
		

		<!--/. NAV TOP  -->
		
		
			
           
                <!-- /. ROW  --> 
		
		
				
			 
				
			
				
                <!-- /. ROW  -->

	   
				
				
				
                
                <!-- /. ROW  -->
			  
			