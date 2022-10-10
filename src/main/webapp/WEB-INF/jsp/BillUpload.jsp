
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

<script>
	function myFunction() {
		var members = parseInt(document.getElementById("members").value);
		// alert(members);
		if (members == "1") {
			document.getElementById("a").value = members;
		} else {

			var persons = prompt("Enter Names Seperated with :", +",");
			//document.forms[0].elements[0].value = members;
			document.getElementById("a").value = persons;
			//alert("You Entered"+persons);
		}
	}
</script>
<script type="text/javascript">
	function loadXMLDoc() {
		var xmlhttp;
		var keys = document.getElementById("selecttrip").value;
		//alert(keys);

		// var urls = "db_fetch-"+ keys;

		$.ajax({
			type : "GET",
			url : "db_fetch",
			data : {
				Trip : keys
			},
			dataType : "json",
			success : function(data) {
				//alert(url);
				// alert(data[0]['userid']);
				$("#result").text(data);
				console.log("SUCCESS : ", data);
				$("#btnSubmit").prop("disabled", false);

			},
			error : function(e) {
				alert(e);
				$("#result").text(e.responseText);
				console.log("ERROR : ", e);
				$("#btnSubmit").prop("disabled", false);

			}
		});
	}

	function Uploadbill() {

		//var files = $("#file")[0].files;
		var file_data = $("#file").prop("files")[0];
		var form = new FormData();

		var latitude = $('#lat').val();
		var longitude = $('#lng').val();
		var members = $('#a').val();
		var status = $('#status').val();
		var userid = $('#userid').val();
		var slct = $('#slct').val();
		var tripunique = $('#selecttrip').val();
		var uploadatonside = $('#uploadatonside').val();
		var billpaid = $('#billpaid').val();
		var billamount = $('#billamount').val();
		var repair = $('#repair').val();
		var description = $('#description').val();
		// image: files

		form.append('latitude', latitude);
		form.append('longitude', longitude);
		form.append('members', members);
		form.append('status', status);
		form.append('userid', userid);
		form.append('slct', slct);
		form.append('tripunique', tripunique);
		form.append('uploadatonside', uploadatonside);
		form.append('billpaid', billpaid);
		form.append('billamount', billamount);
		form.append('repair', repair);
		form.append('description', description);
		form.append('file', file_data);

		alert(file_data);

		$.ajax({
			type : "POST",
			headers : {
				'Content-Type' : undefined
			},
			url : "savebillupload?${_csrf.parameterName}=${_csrf.token}",
			data : form,

			//   contentType: "application/json; charset=utf-8",
			processData : false, //prevent jQuery from automatically transforming the data into a query string
			dataType : 'text',
			contentType : false,
			success : function(data) {
				//alert(url);
				alert(data);
				if (data == "Successfully uploaded!") {
					location.reload();
				}
				$("#result").text(data);
				console.log("SUCCESS : ", data);

				$("#btnSubmit").prop("disabled", false);

			},
			error : function(e) {
				if (e == "Successfully uploaded!") {
					location.reload();
				}
				$("#result").text(e.responseText);
				console.log("ERROR : ", e);
				$("#btnSubmit").prop("disabled", false);

			}
		});

	}
	function uploadFormData() {
		$('#result').html('');
		var file_data = $("#file2").prop("files")[0];
		var oMyForm = new FormData();
		oMyForm.append("image", file_data);
		oMyForm.append("name", "Prasamnth");

		$.ajax({
			url : "upload?${_csrf.parameterName}=${_csrf.token}",
			data : oMyForm,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
				$('#result').html(data);
			}
		});
	}
</script>
<script>
	function change() {
		var select = document.getElementById("slct");
		var divv = document.getElementById("container");
		var value = select.value;
		//alert();
		document.getElementById("value").value = value;
		if (value == 'food') {
			toAppend = "<input type='number' min='0' name='numberofperson' id='members' placeholder='Number of Persons' style='width: 20%;' onblur='myFunction()' required>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'fuel') {
			toAppend = "<input type='number' min='0' placeholder='distance' style='width: 50%;' id='distance'  onkeyup='myFunction3()'>&nbsp;\n\
                <br><input type='number'  min='0' id='odometer' placeholder='odometer' style='width: 50%;' onkeyup='myFunction3()'>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'lodge') {
			toAppend = "<input type='number' min='0' name='numberofperson' id='members' placeholder='Number of Persons' style='width: 20%;' onblur='myFunction()' required>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'airfare') {
			toAppend = "<input type='number' min='0' name='numberofperson' id='members' placeholder='Number of Persons' style='width: 20%;' onblur='myFunction()' required>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'cabfare') {
			toAppend = "<input type='number' name='numberofperson' id='members' placeholder='Number of Persons' style='width: 20%;' onblur='myFunction()' required>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'busfare') {
			toAppend = "<input type='number' min='0' name='numberofperson' id='members' placeholder='Number of Persons' style='width: 50%;' onblur='myFunction()' required>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'trainfare') {
			toAppend = "<input type='number' min='0' name='numberofperson' id='members' placeholder='Number of Persons' style='width: 50%;' onblur='myFunction()' required>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'otherfare') {
			toAppend = "<input type='number' min='0' name='numberofperson' id='members' placeholder='Number of Persons' style='width: 50%;' onblur='myFunction()' required>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'autofare') {
			toAppend = "<input type='number' min='0' name='numberofperson' id='members' placeholder='Number of Persons' style='width: 50%;' onblur='myFunction()' required>";
			divv.innerHTML = toAppend;
			return;
		} else if (value == 'nothing') {
			toAppend = "";
			divv.innerHTML = toAppend;
			return;
		}
	}
</script>
<script>
	function myFunction3() {
		var x = document.getElementById("distance").value;
		var y = document.getElementById("odometer").value;
		document.getElementById("distance1").value = x;
		document.getElementById("odometer1").value = y;
	}
</script>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<%@include file="authheader.jsp"%>
<%@include file="sidebar.jsp"%>

<div class="row well lead">

	<c:if test="${success!= null}">
		<div class="alert alert-success">${success}</div>
	</c:if>
	<h4 class="h">Bill Upload Form</h4>

	<div id="result"></div>
	<form:form name="frm1" id="fileUploadForm"
		enctype="multipart/form-data">

		<input type="hidden" path="latitude" id="lat" name="latitude">
		<input type="hidden" path="longitude" id="lng" name="longitude">
		<input type="hidden" path="acc" id="acc" name="acc">
		<input type="hidden" path="members" name="members" id="a">
		<input type="hidden" path="value" name="value" id="value">
		<input type="hidden" path="distance" name="distance" id="distance1">
		<input type="hidden" path="odometer" name="odometer" id="odometer1">
		<input type="hidden" path="id" name="id" id="id">
		<input type="hidden" path="status" name="status" id="status"
			value="open" />

		<!-- <label class="control-lable" for="userid">User ID</label> -->
		<div class="form-group">
			<input type="hidden" path="userid" name="userid" id="userid"
				value="${loggedinuser}" class="form-control input-sm" />
		</div>

		<label class="control-lable" for="selecttrip">Trip ID</label>
		<div class="form-control1">
			<select path="selecttrip" id="selecttrip" onchange="loadXMLDoc()"
				name="tripunique" class="form-control input-sm">
				<option value="">Select Trip</option>
				<c:forEach var="newtrip" items="${newtrip}">
					<option value="${newtrip.tripunique}">
						${newtrip.userid}-${newtrip.tripname}</option>
				</c:forEach>
			</select>
		</div>

			<label class="control-lable" for="image">FILE</label>
			<div class="form-group">
				<input type="file" name="file" path="file" id="file"
					class="form-control input-sm" />

			</div>

			<td><label class="control-lable" for="slct">Bill TYPE</label>
				<div class="form-group">
					<select id="slct" class="form-control input-sm" onchange="change();" name="slct"  required="required">
						<option value="nothing">Select Bill Type</option>
						<option value="lodge">Lodge</option>
						<option value="food">Food</option>
						<option value="fuel">Fuel</option>
						<option value="busfare">Bus Fare</option>
						<option value="trainfare">Train Fare</option>
						<option value="cabfare">Cab Fare</option>
						<option value="airfare">Air Fare</option>
						<option value="autofare">Auto Fare</option>
						<option value="otherfare">Others</option>
					</select>
				</div>

			<div id="container"></div>
				</td> 
			<label class="control-lable" for="billpaid">Bill Paid</label>
			<div class="form-group">
				<select  id="billpaid" name="billpaid"
					required="required" class="form-control ">
					<option>Company Paid</option>
					<option>Self Paid</option>
				</select>
			</div>

			<label class="control-lable" for="billamount">BillAmount</label>
			<div class="form-group">
				<input type="text" path="billamount" name="billamount"
					id="billamount" class="form-control input-sm" />

			</div>

			<label class="control-lable" for="description">Description</label>
			<div class="form-group">
				<input type="text" path="description" id="description"
					class="form-control input-sm" />
			</div>


			<!-- <label class="control-lable" for="repair">Vehicle
				Repair</label> -->
			<div class="form-group">
				<input type="hidden" path="repair" id="repair" name="repair"
					class="form-control input-sm" />

			</div>

<!-- 			<label class="control-lable" for="uploadatonside">uploadatonside</label>
 -->			<div class="form-group hidden">
				<select  style='display:none' class="form-control input-sm"  name="uploadatonside" id="uploadatonside">
					<option>YES</option>
					<option>NO</option>
				</select>
			</div>



			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
	</form:form>
	<div class="row1">
		<div class="form-actions floatRight">
			<button class="btn btn-success" value="Submit" onclick="Uploadbill()">Upload</button>
			<i></i>

		</div>
	</div>
</div>
<script>
	if (!navigator.geolocation) {
		alert('Your Browser does not support HTML5 Geo Location. Please Use Newer Version Browsers');
	}

	navigator.geolocation.getCurrentPosition(successf, errorf);
	function successf(position) {
		var latitude = parseFloat(position.coords.latitude);
		var longitude = parseFloat(position.coords.longitude);
		var accuracy = position.coords.accuracy;
		document.getElementById("lat").value = latitude;
		document.getElementById("lng").value = longitude;
		document.getElementById("acc").value = accuracy;
		//alert(latitude+""+longitude); 
	}
	function errorf(error) {
		document.getElementById("lat").value = "0.0";
		document.getElementById("lng").value = "0.0";
		switch (error.code) {
		case error.PERMISSION_DENIED:
			alert("User denied the request for Geolocation.");
			break;
		case error.POSITION_UNAVAILABLE:
			alert("Location information is unavailable.");
			break;
		case error.TIMEOUT:
			alert("The request to get user location timed out.");
			break;
		case error.UNKNOWN_ERROR:
			alert("An unknown error occurred.");
			break;
		}
	}
</script>
<%@include file="footer.jsp"%>

</html>