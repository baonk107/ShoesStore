<%-- 
    Document   : CustomerManager
    Created on : Mar 10, 2022, 3:43:12 PM
    Author     : MT Bac Ninh
--%>

<%@page import="java.sql.ResultSet"%>
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
            .col-md-6 input{
                margin-bottom:  20px;
            }
/*            .not-active {
                pointer-events: none;
                cursor: default;
                text-decoration: none;
                color: black;
                cursor: not-allowed;
            }*/
        </style>
    </head>
    <body>
        <%
            ResultSet rsCus = (ResultSet) request.getAttribute("rsCustomer");
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
                                <input name="searchKey" class="form-control me-2" type="search" placeholder="Search By Ct Name" aria-label="Search">
                                <button class="btn btn-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
                <!--Add Product-->
                <p class="d-flex justify-content-end" style="cursor: pointer; padding: 5px;">
                    <a class="btn btn-success text-dark"><i class="material-icons" style="">add_circle</i>Add New Customer</a>
                </p>
                <!--List Bill-->
                <div class="container">
                    <%if (rsCus.next()) {%>
                    <form class="row g-3" action="CustomerManager?do=update" method="POST">
                        <div class="col-md-6">
                            <label for="CustomerID" class="form-label">CustomerID</label>
                            <input value="<%=rsCus.getString(1)%>" readonly  name="cusID" type="text" class="form-control" id="CustomerID" placeholder="Enter CustomerID">
                        </div>
                        <div class="col-md-6">
                            <label for="CompanyName" class="form-label">CompanyName</label>
                            <input value="<%=rsCus.getString(2)%>" required name="cpName"  type="text" class="form-control" id="CompanyName" placeholder="Enter CompanyName">
                        </div>
                        <div class="col-md-6">
                            <label for="ContactName" class="form-label">ContactName</label>
                            <input required value="<%=rsCus.getString(3)%>"  name="ctName" type="text" class="form-control" id="ContactName" placeholder="Enter ContactName">
                        </div>
                        <div class="col-md-6">
                            <label for="ContactTitle" class="form-label">ContactTitle</label>
                            <input value="<%=rsCus.getString(4)%>"  name="contactTitle" type="text" class="form-control" id="ContactTitle" placeholder="Enter ContactTitle">
                        </div>
                        <div class="col-md-6">
                            <label for="Address" class="form-label">Address</label>
                            <input value="<%=rsCus.getString(5)%>"  name="address" type="text" class="form-control" id="Address" placeholder="Enter Address">
                        </div>
                        <div class="col-md-6">
                            <label for="City" class="form-label">City</label>
                            <input value="<%=rsCus.getString(6)%>"  name="city" type="text" class="form-control" id="City" placeholder="Enter City">
                        </div>
                        <div class="col-md-6">
                            <label for="Region" class="form-label">Region</label>
                            <input value="<%=rsCus.getString(7)%>"  name="region" type="text" class="form-control" id="Region" placeholder="Enter Region">
                        </div>
                        <div class="col-md-6">
                            <label for="PostalCode" class="form-label">PostalCode</label>
                            <input value="<%=rsCus.getString(8)%>"  name="pscode" type="text" class="form-control" id="PostalCode" placeholder="Enter PostalCode">
                        </div>
                        <div class="col-md-6">
                            <label for="Country" class="form-label">Country</label>
                            <input value="<%=rsCus.getString(9)%>"  name="country" type="text" class="form-control" id="Country" placeholder="Enter Country">
                        </div>
                        <div class="col-md-6">
                            <label for="Phone" class="form-label">Phone</label>
                            <input value="<%=rsCus.getString(10)%>"  name="phone" type="text" class="form-control" id="Phone" placeholder="Enter Phone">
                        </div>
                        <div class="col-md-6">
                            <label for="Fax" class="form-label">Fax</label>
                            <input value="<%=rsCus.getString(11)%>"  name="fax" type="text" class="form-control" id="Fax" placeholder="Enter Fax">
                        </div>
                        <div class="col-md-6">
                            <label for="UserName" class="form-label">UserName</label>
                            <input readonly value="<%=rsCus.getString(12)%>"  name="user" type="text" class="form-control" id="UserName" placeholder="Enter UserName">
                        </div>
                        <div class="col-md-6">
                            <label for="PassWord" class="form-label">PassWord</label>
                            <input value="<%=rsCus.getString(13)%>"  name="pass" type="text" class="form-control" id="PassWord" placeholder="Enter PassWord">
                        </div>

                        <div class="col-12">
                            <button type="submit" name="submit" class="btn btn-primary">Update</button>
                        </div>
                    </form>
                    <%}%>
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

