<%-- 
    Document   : admin1
    Created on : Mar 8, 2022, 11:21:11 PM
    Author     : MT Bac Ninh
--%>

<%@page import="java.sql.ResultSet"%>
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
    </head>
    <body>
        <%
            ResultSet rsOdDetail = (ResultSet) request.getAttribute("listOrderDetail");
            List<OrderList> listOrder = (List<OrderList>) request.getAttribute("listOrder");
            List<Status> listStatus = (List<Status>) request.getAttribute("listStatus");
        %>
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
                <!--Info Order-->
                <div class="col-md-12">
                    <table class="border table table-striped table-hover table-bordered border-primary" style="margin-top: 10px">
                        <%for (OrderList odList : listOrder) {%>
                        <tr>
                            <td>Order Detail</td>
                            <td>Information Order</td>
                        </tr>
                        <tr>
                            <td>CustomerName</td>
                            <td><%=odList.getCustomerName()%></td>
                        </tr>
                        <tr>
                            <td>OrderID</td>
                            <td><%=odList.getOrderID()%></td>
                        </tr>
                        <tr>
                            <td>Address</td>
                            <td><%=odList.getAddress()%></td>
                        </tr>
                        <tr>
                            <td>Order Date</td>
                            <td><%=odList.getOrderDate()%></td>
                        </tr>
                        <tr>
                            <td>Status</td>
                            <td>
                                <form action="BillManager?do=updateStatusDetail" method="POST">
                                    <input type="hidden" name="odId" value="<%=odList.getOrderID()%>">
                                    <select name="status" onchange="this.form.submit()">
                                        <%for (Status sta : listStatus) {%>
                                        <option value="<%=sta.getId()%>" <%=(sta.getId() == odList.getStatus() ? " selected" : "")%>><%=sta.getStatusName()%></option>
                                        <%}%>
                                    </select>
                                </form>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                </div>

                <h2 style="color: red; text-align: center; font-weight: bold;">List Order Detail</h2>
                <!--List Bill Detail-->
                <div class="col-md-12">
                    <table class="border table table-striped table-hover table-bordered border-primary" style="margin-top: 10px">
                        <thead class="bg-info" style="position: -webkit-sticky; position: sticky; top: 0;">
                            <tr>
                                <th>ProductID</th>
                                <th>ProductName</th>
                                <th>Quantity</th>
                                <th>UnitPrice</th>
                                <th>Total</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <% while (rsOdDetail.next()) {%>
                            <tr>
                                <td><%=rsOdDetail.getInt(1)%></td>
                                <td><%=rsOdDetail.getString(2)%></td>
                                <td><%=rsOdDetail.getInt(3)%></td>
                                <td><%=rsOdDetail.getDouble(4)%></td>
                                <td><%=rsOdDetail.getDouble(5)%></td>
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
