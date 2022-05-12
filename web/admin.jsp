<%-- 
    Document   : admin1
    Created on : Mar 8, 2022, 11:21:11 PM
    Author     : MT Bac Ninh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Entity.Status"%>
<%@page import="Entity.OrderList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <title>Manager</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/stylesidebar.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link href="../css/search.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap/style.css" rel="stylesheet" type="text/css"/>
        <style>
            body{
                background-image: url("image/bgcheckout.jpg");
            }
        </style>
    </head>
    <body>
        <%
            List<OrderList> listOrder = (List<OrderList>) request.getAttribute("listOrder");
            List<OrderList> listOrderCanDele = (List<OrderList>) request.getAttribute("listOrderCanDele");
            List<Status> listStatus = (List<Status>) request.getAttribute("listStatus");

        %>
        <!--<img src="image/bgcheckout.jpg" alt=""/>-->
        <!--Nav Bar-->
        <div class="wrapper d-flex align-items-stretch">
            <%@include file="components/NavBarAdmin.jsp" %>
            <!-- Page Content  -->
            <div id="content" class="p-4 p-md-5">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <!--Button NavBar-->
                        <button type="button" id="sidebarCollapse" class="btn btn-primary">
                            <i class="fa fa-bars"></i>
                            <span class="sr-only">Toggle Menu</span>
                        </button>
                        <!--Name Option-->
                        <a href="adminHome?do=billManager" class="text-center text-muted btn btn-outline-info">Bill Manager</a>

                        <!-- Search -->
                        <div>
                            <form class="d-flex mx-auto" action="BillManager?do=search"  method="POST">
                                <input style="min-width:  350px;" name="searchKey" class="form-control me-2" type="search" placeholder="Search By OrderID or CustomerName" aria-label="Search">
                                <button class="btn btn-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
                <!--Update Status-->
                <div class="d-flex justify-content-end" >
                    <form action="BillManager?do=listAllByStatus" method="post" style="margin-right: 11%;padding: 10px 20px;">
                        <select class="border-warning btn-outline-success" name="status" onchange="this.form.submit()" style="margin-right: 9%;padding: 10px 10px; cursor: pointer;">
                            <option value="-1">All Status</option>
                            <c:forEach items="${listStatus}" var="s">
                                <option value="${s.id}" ${s.id == statusID?" selected":""}>${s.statusName}</option>
                            </c:forEach>
                        </select>
                    </form>
                </div>
                <c:if test="${mess != null}" >
                    <div class="alert alert-success" role="alert">
                        ${mess}
                    </div>
                </c:if>
                <!--List Bill-->
                <div class="col-md-12">
                    <table class="border table table-striped table-hover table-bordered border-primary" style="margin-top: 10px">
                        <thead class="bg-info" style="position: -webkit-sticky; position: sticky; top: 0;">
                            <tr>
                                <th>OrderID</th>
                                <th>CustomerName</th>
                                <th>OrderDate</th>
                                <th>Address</th>
                                <th>Total</th>
                                <th>Status</th>
                                <th>View</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (OrderList od : listOrder) {%>
                            <tr>
                                <td> <%=od.getOrderID()%> </td>
                                <td> <%=od.getCustomerName()%></td>
                                <td> <%=od.getOrderDate()%></td>
                                <td> <%=od.getAddress()%></td>
                                <td> <%=od.getTotalPrice()%></td>
                                <td>
                                    <form action="BillManager?do=updateStatusOrder" method="POST">
                                        <input type="hidden" name="odId" value="<%=od.getOrderID()%>">
                                        <select name="status" onchange="this.form.submit()" style="cursor: pointer;">
                                            <%for (Status sta : listStatus) {%>
                                            <option value="<%=sta.getId()%>" <%=(sta.getId() == od.getStatus() ? " selected" : "")%>><%=sta.getStatusName()%></option>
                                            <%}%>
                                        </select>
                                    </form>
                                </td>
                                <td><a href="BillManager?do=orderDetail&orderID=<%=od.getOrderID()%>">Details</a></td>
                                <%for (OrderList o : listOrderCanDele) {
                                        //Check Được xóa
                                        if (o.getOrderID() == od.getOrderID()) {
                                %>
                                <td><a href="BillManager?do=delete&orderID=<%=od.getOrderID()%>">Delete</a></td>
                                <%}%>
                                <%}%>

                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="components/footerAdmin.jsp" %>

        <script src="js/jquerysidebar.min.js"></script>
        <script src="js/poppersidebar.js"></script>
        <script src="js/bootstrapsidebar.min.js"></script>
        <script src="js/mainsidebar.js"></script>
        <script src="js/search.js" type="text/javascript"></script>
    </body>
</html>
