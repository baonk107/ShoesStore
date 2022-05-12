<%-- 
    Document   : ManagerCustomer
    Created on : Mar 9, 2022, 12:08:37 AM
    Author     : MT Bac Ninh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Entity.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Manager Customer</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/stylesidebar.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
            body{
                background-image: url("image/bgcheckout.jpg");
            }
        </style>
    </head>
    <body>
        <%
            List<Customer> listCustomer = (List<Customer>) request.getAttribute("listCus");
            List<Customer> listCheck = (List<Customer>) request.getAttribute("listCheck");
            List<Customer> listCheckIn = (List<Customer>) request.getAttribute("listCheckIn");
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
                        <a href="adminHome?do=customerManager" class="text-center text-muted btn btn-outline-info">Customer Manager</a>

                        <!-- Search -->
                        <div>
                            <form class="d-flex mx-auto" action="CustomerManager?do=search" method="POST">
                                <input name="searchKey" class="form-control me-2" type="search" placeholder="Search By Contact Name" aria-label="Search">
                                <button class="btn btn-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
                <c:if test="${mess != null}" >
                    <div class="alert alert-success" role="alert">
                        ${mess}
                    </div>
                </c:if>
                <!--Add Product-->
                <p class="d-flex justify-content-end" style="cursor: pointer; padding: 5px;">
                    <a href="CustomerManager?do=insert" class="btn btn-success text-dark"><i class="material-icons" style="">add_circle</i>Add New Customer</a>
                </p>
                <!--List Bill-->
                <div class="col-md-12" style="padding: 0;">
                    <table class="border table table-striped table-hover table-bordered text-center" style="margin-top: 10px; font-size: 13px;">
                        <thead class="bg-info" style="position: -webkit-sticky; position: sticky; top: 0;">
                            <tr>
                                <th>CID</th>
                                <th>Company Name</th>
                                <th>Ct Name</th>
                                <th>Contact</th>
                                <th>Address</th>
                                <th>City</th>
                                <th>Region</th>
                                <th>Code</th>
                                <th>Country</th>
                                <th>Phone</th>
                                <th>Fax</th>
                                <th colspan="2">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Customer cus : listCustomer) {%>
                            <tr>
                                <td><%=cus.getCustomerID()%></td>
                                <td><%=cus.getCompanyName()%></td>
                                <td><%=cus.getContactName()%></td>
                                <td><%=cus.getContactTitle()%></td>
                                <td><%=cus.getAddress()%></td>
                                <td><%=cus.getCity()%></td>
                                <td><%=cus.getRegion()%></td>
                                <td><%=cus.getPostalCode()%></td>
                                <td><%=cus.getCountry()%></td>
                                <td><%=cus.getPhone()%></td>
                                <td><%=cus.getFax()%></td>
                                <td><a href="CustomerManager?do=update&cusID=<%=cus.getCustomerID()%>"><i style="color: #FFC107;" class="material-icons">&#xE254;</i></a></td>
                                <td>
                                    <%for (Customer cusID : listCheck) {
                                            //Check Được xóa
                                            if (cusID.getCustomerID().equals(cus.getCustomerID())) {
                                    %>
                                    <a class="" href="CustomerManager?do=delete&cusID=<%=cus.getCustomerID()%>"><i style="color: #F44336;" class="material-icons">&#xE872;</i></a>
                                    <%} %>
                                    <%}%>

                                    <%for (Customer cusID : listCheckIn) {
                                            //Check Được xóa
                                            if (cusID.getCustomerID().equals(cus.getCustomerID())) {
                                    %>
                                    <a class="btn disabled" href="CustomerManager?do=delete&cusID=<%=cus.getCustomerID()%>"><i style="color: #F44336;" class="material-icons">&#xE872;</i></a>
                                    <%} %>
                                    <%}%>
                                </td>
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
    </body>
</html>
