

<style>
/* Style the buttons */
.waves-effect waves-dark {
	border: none;
	outline: none;
	padding: 10px 16px;
	background-color: #f1f1f1;
	cursor: pointer;
	font-size: 18px;
}

/* Style the active class, and buttons on mouse-over */
.active, .waves-effect waves-dark:hover {
	background-color: #666;
	color: white;
}
</style>

<script>
	// Add active class to the current button (highlight it)
	var header = document.getElementById("main-menu");
	var btns = header.getElementsByClassName("waves-effect waves-dark");
	for (var i = 0; i < btns.length; i++) {
		btns[i].addEventListener("click", function() {
			var current = document.getElementsByClassName("active");
			if (current.length > 0) {
				current[0].className = current[0].className.replace(" active",
						"");
			}
			this.className += " active";
		});
	}
</script>


<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">
			<li><a class="waves-effect waves-dark" href="home"><i
					class="fa fa-dashboard"></i> DASHBOARD</a></li>

			<li><a href="newtrip" class="waves-effect waves-dark"><i
					class="fa fa-plus"></i>CREATE NEWTRIP</a></li>

			<li><a href="billupload" class="waves-effect waves-dark"><i
					class="fa fa-share"></i>UPLOAD BILL</a></li>

			<li><a href="trips" class="waves-effect waves-dark"><i
					class="fa fa-building-o"></i>TOTAL TRIPS</a></li>

			<li><a href="reports" class="waves-effect waves-dark"><i
					class="fa fa-list-ul"></i> REPORTS </a></li>

			<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
				<li><a href="management" class="waves-effect waves-dark"> <i
						class="fa fa-list-ul"></i>USER MANAGEMENT</a></li>

				<li><a class="waves-effect waves-dark" href="list"><i
						class="fa fa-dashboard"></i>LIST OF USERS</a></li>
				<li><a href="newuser" class="waves-effect waves-dark"><i
						class="fa fa-users"></i> CREATE USER</a></li>


			</sec:authorize>


			<!-- <li><a href="#" class="waves-effect waves-dark"><i
					class="fa fa-sitemap"></i> Multi-Level Dropdown<span
					class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="#">Second Level Link</a></li>
					<li><a href="#">Second Level Link</a></li>
					<li><a href="#">Second Level Link<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li><a href="#">Third Level Link</a></li>
							<li><a href="#">Third Level Link</a></li>
							<li><a href="#">Third Level Link</a></li>

						</ul></li>
				</ul></li> 
			<li><a href="empty.html" class="waves-effect waves-dark"><i
					class="fa fa-fw fa-file"></i> Empty Page</a></li> -->
		</ul>

	</div>

</nav>
<!-- /. NAV SIDE  -->
<%-- <li><a href="<%=request.getContextPath()%>/list" class="active">Users list</a></li>
            <li><a href="<%=request.getContextPath()%>/newtrip">New Trip</a></li>
            <li><a href="<%=request.getContextPath()%>/newuser">ADD NewUser</a></li>
            <li><a href="<%=request.getContextPath()%>/trips">Trip lists</a></li>
            <li><a href="<%=request.getContextPath()%>/billupload">Upload</a></li>
            <li><a href="<%=request.getContextPath()%>/reports">Reports</a></li>--%>
<div id="page-wrapper">
	<!-- <div class="header"> 
                        <h1 class="page-header">
                            Dashboard
                        </h1>
						<ol class="breadcrumb">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">Dashboard</a></li>
					  <li class="active">Data</li>
					</ol> 
									
		</div> -->
	<div id="page-inner">