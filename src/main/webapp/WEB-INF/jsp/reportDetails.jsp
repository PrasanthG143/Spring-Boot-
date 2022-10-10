<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.example.model.Billupload"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

  <%@include file="authheader.jsp" %>	
  
    <%@include file="sidebar.jsp" %>	
        <div class="row well read" >
   <div class="row">
<div class="col-md-6">
	<div id="pieChartContent">
		<canvas id="pie" style="height: 250px; width: 100%;"></canvas>
	</div>
	</div>
	<div class="col-md-6"></div>
</div>    <%Map<String,Integer> hmap=new HashMap<>();
             List<Billupload> al=(List<Billupload>)request.getAttribute("details");
             for (int i = 0; i < al.size(); i++) {

 				// System.out.println("Trips=====" + newtrips.toArray()[i]);
 				Billupload s = (Billupload) al.get(i);
                hmap.put(s.getSlct(), Integer.parseInt(s.getBillamount()));
            //	out.println(al);
             }
             List<String> keyList = new ArrayList<String>(hmap.keySet());
         	List<Integer> valueList = new ArrayList<Integer>(hmap.values());
         
         %>
         <script>
      
		$(function() {
			var data = [];
			var label = [];
	<%for (int i = 0; i < hmap.size(); i++) {%>
		//  
		// alert('<%=valueList.get(i)%>');
			 data.push('<%=valueList.get(i)%>'); 
			label.push('<%=keyList.get(i)%>');
	<%}%> 
		//data.push({y: parseInt(sdata[1]), label: sdata[0]});
			var color = [ '#3366de', '#ff9900', '#ff33cc', '#99ff99',
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
								text : 'Employee Spent CategoryWise',
								fontColor : '#c0392b',
								fontSize : 20
							},
							legend : {
								position : 'right',
							}

						}

					});
			window.pie.render();
		});
	</script>
       

                <!-- Modal -->
                <div class="table-responsive">
                    <!-- Default panel contents -->
                    <div class="panel-heading"><span class="lead">Total Bills </span></div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>TripID</th>
                                <th>billamount</th>
                                <th>billpaid</th>
                                <th>Bill Type</th>
                                 <th>Bill Time</th>
                                <th>Description</th>


                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${details}" var="details">
                                <tr>
                                    <td>${details.userid}-${details.tripunique}</td>
                                    <td>${details.billamount}</td>
                                    <td>${details.billpaid}</td>
                                    <td>${details.slct}</td>
                                    <td>${details.description}</td>
                                    <td>${details.description}</td>
                                    <c:choose>
                                        <c:when  test="${details.type=='empty'}">
                                            <td style="color: red">NO IMAGE</td>          
                                        </c:when>    
                                        <c:otherwise>
                                            <td><input type="button" onclick="load('${details.fileName}', '${details.type}')" value="viewImage"></td>
                                            </c:otherwise>
                                        </c:choose>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
               

            
           <!--  <div class="modal fade"  id="exampleModal" tabindex="-1"  role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">VIEW BILL</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div id="image"   class="hide-it-off-screen"  style="position:absolute; left: -10000px; top: -10000px;">
                                <img src="" id="imagepreview" style="width: 550px; height: 300px;" >
                            </div>
                            <div id="pdf"  class="hide-it-off-screen"  style="position:absolute; left: -10000px; top: -10000px;">
                                 <embed src="" id ="pdfpreview" frameborder="0"  width="100%" height="400px">
                            </div>
                            <div id="excel"  class="hide-it-off-screen"  style="position:absolute; left: -10000px; top: -10000px;">
                            <a src="" id ="excelpreview" type="application/msword">Click to Download</a>
                            </div>
                            <p id="test"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
             -->
            <!-- Modal Trigger -->

  <!-- Modal Structure -->
  <div id="modal1" class="modal modal-fixed-footer" style="max-height: 100%;">
         <div class="modal-header">
 <h3>View Bill</h3>
   
    </div>
         <div class="modal-body">
                            <div id="image"   class="hide-it-off-screen"  style="display: none;">
                                <img src="" id="imagepreview" style="width: 550px; height: 300px;" >
                            </div>
                            <div id="pdf"  class="hide-it-off-screen"  style="display: none;">
                                 <embed src="" id ="pdfpreview" frameborder="0"  width="100%" height="600px">
                            </div>
                            <div id="excel"  class="hide-it-off-screen"  style="display: none;">
                            <a src="" id ="excelpreview" width="100%" height="200px" type="application/msword">Click to Download</a>
                            </div>
                            <p id="test"></p>
                        </div>
    <div class="modal-footer">
      <a href="#!" data-dismiss="modal" class="modal-action modal-close btn teal lighten-1" >Cancel</a>
    </div>
  </div>
            
            
            
            
            
      </div>
        <script>
            function load(a, b) {
            	    $('#modal1').modal();
                var image = document.getElementById("image");
                var pdf = document.getElementById("pdf");
                var excel = document.getElementById("excel");
                var img = '../../TripImages/BillImages/' + a;

                if (b.indexOf('image') !== -1) {
                    image.style.display = 'block';
                    pdf.style.display = 'none';
                    excel.style.display = 'none';
                    
                    $(".modal-body #imagepreview").attr("src", img);
               //     alert('image');
                } else if(b.indexOf('pdf') !== -1) {
                 //   alert('pdf');
                    image.style.display = 'none';
                    pdf.style.display = 'block';
                    excel.style.display = 'none';
                    
                    $(".modal-body #pdfpreview").attr("src", img);
                }else{
                	//alert('excel');
                    image.style.display = 'none';
                    pdf.style.display = 'none';
                    excel.style.display = 'block';
                    
                    $(".modal-body #excelpreview").attr("href", img);

                    }
                $('#modal1').modal('open'); 
                //document.getElementById('exampleModal').style.display = "block"; */ */ */ */
            }
        </script>
    <%@include file="footer.jsp" %>	
