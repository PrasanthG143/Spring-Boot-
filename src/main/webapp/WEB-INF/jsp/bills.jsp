
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@page import="java.io.*"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
.modal fade {
	position: absolute;
	left: -10000px;
	top: -10000px;
}
</style>

<%@include file="authheader.jsp"%>

<%@include file="sidebar.jsp"%>



<script type="text/javascript">
	$(document).ready(function() {
		$('#start').bootstrapMaterialDatePicker({
			time : false,
			clearButton : true,
			format : 'MM-DD-YYYY'
		});
		$('#end').bootstrapMaterialDatePicker({
			time : false,
			clearButton : true,
			format : 'MM-DD-YYYY'
		});
	});
</script>
<div class="row well read">
	<form action="bills">

		<input type="text" class="form-control" name="start"
			placeholder="MM-DD-YYYY" id="start" required="required"> <input
			type="text" class="form-control" name="end" id="end"
			placeholder="MM-DD-YYYY" required="required"> <input
			type=submit name="" value="Get">
	</form>
	<br>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: lightblue;">
				<th>ID</th>
				<th>User ID</th>
				<th>Amount</th>
				<th>Description</th>
				<th>View</th>
			</tr>
		</thead>
		<c:forEach items="${bills}" var="bill">
			<tbody>
				<tr>
					<td>${bill.id}</td>
					<td>${bill.userid}</td>
					<td>${bill.billamount}</td>
					<td>${bill.description}</td>
					<c:choose>
						<c:when test="${bill.type=='empty'}">
							<td style="color: red">NO IMAGE</td>
						</c:when>
						<c:otherwise>
							<td><input type="button"
								onclick="load('${bill.fileName}', '${bill.type}')"
								value="viewImage"></td>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>

			</tbody>
		</c:forEach>

	</table>


	<div id="modal1" class="modal modal-fixed-footer"
		style="max-height: 100%;">
		<div class="modal-header">
			<h3>View Bill</h3>

		</div>
		<div class="modal-body">
			<div id="image" class="hide-it-off-screen" style="display: none;">
				<img src="" id="imagepreview" style="width: 550px; height: 300px;">
			</div>
			<div id="pdf" class="hide-it-off-screen" style="display: none;">
				<embed src="" id="pdfpreview" frameborder="0" width="100%"
					height="600px">
			</div>
			<div id="excel" class="hide-it-off-screen" style="display: none;">
				<a src="" id="excelpreview" width="100%" height="200px"
					type="application/msword">Click to Download</a>
			</div>
			<p id="test"></p>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close btn teal lighten-1">Cancel</a>
		</div>
	</div>

</div>




<script>
	function load(a, b) {

		$('#modal1').modal();

		//alert(a);

		//var modal=document.getElementById('exampleModal').value;
		// $('#imagepreview').attr('src', $('#imageresource').attr('src'));
		//$('#exampleModal').find('.modal-body').img();
		var image = document.getElementById("image");
		var pdf = document.getElementById("pdf");
		var excel = document.getElementById("excel");
		var img = 'http://localhost/TemBillimages/' + a;

		if (b.indexOf('image') !== -1) {
			image.style.display = 'block';
			pdf.style.display = 'none';
			excel.style.display = 'none';

			$(".modal-body #imagepreview").attr("src", img);
			//alert('image');
		} else if (b.indexOf('pdf') !== -1) {
			//alert('pdf');
			image.style.display = 'none';
			pdf.style.display = 'block';
			excel.style.display = 'none';

			$(".modal-body #pdfpreview").attr("src", img);
		} else {
			//	alert('excel');
			image.style.display = 'none';
			pdf.style.display = 'none';
			excel.style.display = 'block';

			$(".modal-body #excelpreview").attr("href", img);

		}
		$('#modal1').modal('open');
		//document.getElementById('exampleModal').style.display = "block"; */ */ */ */
	}
</script>
<%@include file="footer.jsp"%>