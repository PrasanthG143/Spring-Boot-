<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ALL Reports</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>    
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript"   src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript"   src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
        <script type="text/javascript"   src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel='stylesheet' type='text/css' > 
        <link href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css" rel='stylesheet' type='text/css' >
        <link href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css" rel='stylesheet' type='text/css' >  



        <script>
        $(document).ready(function () {
                var table = $('#example').DataTable({
                   
                });
            });</script>

    </head>

    <body>
<%@include file="authheader.jsp" %>
<%@include file="sidebar.jsp" %>

        
<div class="row well read">
<div class="table-responsive">
        <table  id="example" class="table">
            <!-- here should go some titles... -->
            <tr style="align-content: center; background-color: lightblue"  >
                <th>Trip ID</th>
                <th>Group ID</th>
                <th>Trip Status</th>
                <th>Payment Status</th>
                <th>Status</th>
                <th>Advance Amount</th>
                <th>Settle Amount</th>
                <th>No Of Nights</th>
                <th>Date of Journey</th>
                <th>Company Paid Amount</th>
                <th>Self Paid Amount</th>
                <th>Details</th>
               <!--  <th>Settle Amount</th>
                <th>Trip Settle</th>
                <th>Delete</th> -->

            </tr>
            <tr>
            </tr>
            <%--
            Strcture=   Map<String, List<Map<String, List<Object>>>>
           get  key as User Id
           Iterate map Again will get String List<Object>
            --%>
            <%  String GroupName = "";%>
            <% String UserName = ""; %>
            <c:forEach var="entry" items="${result}"> 
            <%-- For Loop For FIRST USERs --%>
                <%--<c:out value="${result}"/>--%>

                <%--   <tr><td colspan="7">  Key: <c:out value="${entry.key}"/></td></tr> KEY IS USER NAME --%>

                <c:forEach  var="list"  begin="0" end ="${entry.value.size()-1}">
                    <%-- LOOPING FOR INDIVIDUAL TRIPS --%>
                    <c:set var="ss" value="${entry.value.get(list)}"/>


                    <%

                        Map<String, Object> resp = null;

                        //resp = (Map<String, Object>)pageContext.getAttribute("test");
                        resp = (Map<String, Object>) pageContext.getAttribute("ss");
                        
                        List<Object> list = (List<Object>) resp.get("info");

                        if (GroupName.equalsIgnoreCase("") || (!GroupName.equalsIgnoreCase(list.get(9).toString()))) {
                            GroupName = list.get(9).toString();
                            out.println("<tr><td colspan='15' style='font-weight: bold; color:blue; font-size: 20px;'>" + GroupName + "</td></tr>");
                        }
                        if (UserName.equalsIgnoreCase("") || (!UserName.equalsIgnoreCase(list.get(0).toString()))) {
                            UserName = list.get(0).toString();
                            out.println("<tr><td colspan='15' style='font-weight: bold; color: green;'>" + UserName + "</td></tr>");
                        }
                        int adv = ((Integer.parseInt(list.get(12).toString()) + Integer.parseInt(list.get(12).toString())) - (Integer.parseInt(list.get(2).toString()) + Integer.parseInt(list.get(8).toString())));

                        String due = "";
                        if (list.get(5).toString().equals("open") || adv == 0) {
                            due = "";
                        } else {
                            due = "<input type='button' style='width: 100%; background-color: #0283ca; color: white;' onclick=window.open('Settle?tripunique=" + list.get(7).toString() + "') name='' value='Settle'>";
                        }

                        String link = null;
                        if (list.get(5).toString().equals("settled") || list.get(5).toString().equals("open")) {
                            link = "";
                        } else {
                            link = "<input type='button' onclick=window.open('Paymentsettle?tripunique=" + list.get(7).toString() + "') style='width: 100%; background-color: #0283ca; color: white;' name='' value='Trip Settle'>";
                        }

                        out.println("<tr>"
                                + "<td>" + list.get(0).toString() + "-" + list.get(10).toString() + "-" + list.get(1).toString() + "</td>"
                                + "<td>" + list.get(9).toString() + "</td>"
                                + "<td>" + list.get(6).toString() + "</td>"
                                + "<td>" + list.get(4).toString() + "</td>"
                                + "<td>" + list.get(5).toString() + "</td>"
                                + "<td>" + list.get(2).toString() + "</td>"
                                + "<td>" + list.get(8).toString() + "</td>"
                                + "<td>" + list.get(3).toString() + "</td>"
                                + "<td>" + list.get(1).toString() + "</td>"
                                + "<td>" + list.get(11).toString() + "</td>"
                                + "<td>" + list.get(12).toString() + "</td>"
                                + "<td><input type='button' onclick=location.assign('Details?tripunique=" + list.get(7).toString() + "') style='width: 100%; background-color: #0283ca; color: white;' name='' value='get details'></td>"
                              /*   + "<td>" + due + "</td><td>" + link + "</td>"
                                + "<td><input type='button' onclick=save('" + list.get(7).toString() + "') style='width: 100%; background-color: #a84b54; color: white;' value='Delete Trip'/></td>"
                       */          //t
                                + "</tr>");
                    %>

                </c:forEach>
                <%
                    //  String resp = "abc";
                    //  Map test = (Map) pageContext.getAttribute("test");
                    //pageContext.setAttribute("resp", test.values());
%>


            </c:forEach>


        </table>

</div>
</div>
    <%@include file="footer.jsp"%>