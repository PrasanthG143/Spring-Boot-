
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@page import="java.io.*"%>

<%@include file="authheader.jsp"%>

<%@include file="sidebar.jsp"%>
<div class="row">
	<div class="col-xs-12 col-sm-6 col-md-3">

		<div class="card horizontal cardIcon waves-effect waves-dark">
			<div class="card-image red">
				<i class="fa fa-eye fa-5x"></i>
			</div>
			<div class="card-stacked">
				<div class="card-content">
					<h3>16,150</h3>
				</div>
				<div class="card-action">
					<strong> Daily Visits</strong>
				</div>
			</div>
		</div>

	</div>
	<div class="col-xs-12 col-sm-6 col-md-3">

		<div class="card horizontal cardIcon waves-effect waves-dark">
			<div class="card-image orange">
				<i class="fa fa-shopping-cart fa-5x"></i>
			</div>
			<div class="card-stacked">
				<div class="card-content">
					<h3>25,550</h3>
				</div>
				<div class="card-action">
					<strong> Sales</strong>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 col-md-3">

		<div class="card horizontal cardIcon waves-effect waves-dark">
			<div class="card-image blue">
				<i class="fa fa-comments fa-5x"></i>
			</div>
			<div class="card-stacked">
				<div class="card-content">
					<h3>11,225</h3>
				</div>
				<div class="card-action">
					<strong> Comments</strong>
				</div>
			</div>
		</div>

	</div>
	<div class="col-xs-12 col-sm-5 col-md-3">

		<div class="card horizontal cardIcon waves-effect waves-dark">
			<div class="card-image">
				<i class="fa fa-users fa-5x"></i>
			</div>
			<div class="card-stacked">
				<div class="card-content">
					<h3>72,525</h3>
				</div>
				<div class="card-action">
					<strong> No. of Visits</strong>
				</div>
			</div>
		</div>

	</div>
</div>

<div class="row">
	<div class="col-md-6">
		<div id="pieChartContent">
			<canvas id="pie" style="height: 250px; width: 100%;"></canvas>
		</div>
	</div>
	<div class="col-md-6">

		<div id="pieChartContent1">
			<canvas id="myChart" style="height: 300px; width: 100%;"></canvas>
		</div>
	</div>
</div>

<%
	String sum = "";
	String category = "";
	Map<String, String> hmap = new HashMap<String, String>();
	List<Object[]> listResult = (List<Object[]>) request.getAttribute("category");
	for (Object[] aRow : listResult) {
		sum = (String) aRow[0];
		category = (String) aRow[1];
		// out.println(category + " - " + sum);
		hmap.put(category, sum);

	}
	List<String> keyList = new ArrayList<String>(hmap.keySet());
	List<String> valueList = new ArrayList<String>(hmap.values());
	//out.print(hmap);
%>






<!-- //lined-icons -->

<script>
		$(function() {
			var data1 = [];
			var label1 = [];
			var data = [];
			var label = [];
	<%for (int i = 0; i < hmap.size(); i++) {%>
		//  
		//alert('<%=valueList.get(i)%>');
			 data.push('<%=keyList.get(i)%>'); 
			label.push('<%=valueList.get(i)%>');
	<%}%>
		//data.push({y: parseInt(sdata[1]), label: sdata[0]});
	var color = [ 'Red', 'Yellow', 'Blue', '#99ff99',
					'#00cc00', '#6666ff', '#3366de', '#ff9900', '#ff33cc',
					'#99ff99' ];
			var breakdown = document.getElementById("pie").getContext('2d');
			window.pie = new Chart(
					breakdown,
					{
						type : 'pie',
						data : {
							// labels: ["Permit", "Pollution", "Insurance", "Maintenance", "Tyre", "Toll", "Service"],
							labels : label,
							datasets : [ {
								labelString : 'Overall Expenses',
								data : data,
								backgroundColor : color,
								borderColor : [ 'rgba(255,99,132,1)',
										'rgba(54, 162, 235, 1)',
										'rgba(255, 206, 86, 1)',
										'rgba(75, 192, 192, 1)',
										'rgba(153, 102, 255, 1)',
										'rgba(255, 159, 64, 1)' ],
								borderWidth : 1
							} ]
						},
						options : {
							title : {
								display : true,
								//text : 'Employee Spent CategoryWise',
								fontColor : '#c0392b',
								fontSize : 20
							},
							legend : {
								position : 'right',
							}

						}

					});
			window.pie.render();
	

		
<%for (int i = 0; i < hmap.size(); i++) {%>
	//  
	//alert('<%=valueList.get(i)%>');
		 data1.push('<%=keyList.get(i)%>'); 
		label1.push('<%=valueList.get(i)%>');
<%}%>
	//da
	var ctx = document.getElementById("myChart").getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			// labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
			labels : label1,
			datasets : [ {
				label : 'Money Spent in(Rs.)',
				//data: [12, 19, 3, 5, 2, 3],
				data : data1,
				backgroundColor : [ '#3366de', '#ff9900', '#ff33cc', '#99ff99',
						'#00cc00', '#6666ff', '#3366de', '#ff9900', '#ff33cc',
						'#99ff99', '#00cc00', '#6666ff', '#3366de', '#ff9900',
						'#ff33cc', '#99ff99', '#00cc00', '#6666ff', '#3366de',
						'#ff9900', '#ff33cc', '#99ff99', '#00cc00', '#6666ff',
						'#3366de', '#ff9900', '#ff33cc', '#99ff99', '#00cc00',
						'#6666ff' ],
				borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
				borderWidth : 1
			} ]
		},
		options : {
			title : {
				display : true,
				//text : 'Overall Money Spent By Employees For Trips',
				fontColor : '#c0392b',
				fontSize : 20
			},
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});
		});
</script>

<%@include file="footer.jsp"%>

