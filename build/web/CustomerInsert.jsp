<%-- 
    Document   : CustomerInsert
    Created on : Mar 10, 2022, 3:43:40 PM
    Author     : MT Bac Ninh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Manager Product</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/stylesidebar.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
            .col-md-6 .form-label{
                margin-top: 20px;
            }
            /*            .col-md-6 div{
                            display: none;
                        }*/
        </style>
    </head>
    <body>
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

                    </div>
                </nav>
                <!--Add Product-->
                <c:if test="${mess != null}" >
                    <div class="alert alert-danger" role="alert">
                        ${mess}
                    </div>
                </c:if>
                <!--Form Update Product-->
                <div class="container">
                    <form class="row g-3" action="CustomerManager?do=insert" method="POST">
                        <div class="col-md-6">
                            <label for="CompanyName" class="form-label">CompanyName</label>
                            <input  required name="cpName"  type="text" class="form-control" id="CompanyName" placeholder="Enter CompanyName">
                            <!--<div style="color: red;" id="iscpName">CompanyName must less than 40 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="ContactName" class="form-label">ContactName</label>
                            <input required   name="ctName" type="text" class="form-control" id="ContactName" placeholder="Enter ContactName">
                            <!--<div style="color: red;" id="isctName">ContactName must less than 30 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="ContactTitle" class="form-label">ContactTitle</label>
                            <input   name="contactTitle" type="text" class="form-control" id="ContactTitle" placeholder="Enter ContactTitle">
                            <!--<div style="color: red;" id="iscontactTitle">ContactTitle must less than 30 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="Address" class="form-label">Address</label>
                            <input   name="address" type="text" class="form-control" id="Address" placeholder="Enter Address">
                            <!--<div style="color: red;" id="isaddress">Address must less than 60 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="City" class="form-label">City</label>
                            <input   name="city" type="text" class="form-control" id="City" placeholder="Enter City">
                            <!--<div style="color: red;" id="iscity">City must less than 15 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="Region" class="form-label">Region</label>
                            <input   name="region" type="text" class="form-control" id="Region" placeholder="Enter Region">
                            <!--<div style="color: red;" id="isregion">Region must less than 15 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="PostalCode" class="form-label">PostalCode</label>
                            <input   name="pscode" type="text" class="form-control" id="PostalCode" placeholder="Enter PostalCode">
                            <!--<div style="color: red;" id="ispscode">PostalCode must less than 10 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="Country" class="form-label">Country</label>
                            <input   name="country" type="text" class="form-control" id="Country" placeholder="Enter Country">
                            <!--<div style="color: red;" id="iscountry">Country must less than 15 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="Phone" class="form-label">Phone</label>
                            <input   name="phone" type="text" class="form-control" id="Phone" placeholder="Enter Phone">
                            <!--<div style="color: red;" id="isphone">Phone must less than 24 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="Fax" class="form-label">Fax</label>
                            <input   name="fax" type="text" class="form-control" id="Fax" placeholder="Enter Fax">
                            <!--<div style="color: red;" id="isfax">Fax must less than 24 character</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="UserName" class="form-label">UserName</label>
                            <input required  name="user" type="text" class="form-control" id="UserName" placeholder="Enter UserName">
                            <!--<div style="color: red;" id="isuser">Not Empty UserName</div>-->
                        </div>
                        <div class="col-md-6">
                            <label for="PassWord" class="form-label">PassWord</label>
                            <input required  name="pass" type="text" class="form-control" id="PassWord" placeholder="Enter PassWord">
                            <!--<div style="color: red;" id="ispass">Not Empty PassWord</div>-->
                        </div>

                        <div class="col-12" style="margin-top: 30px;">
                            <button style="padding: 10px 30px; margin-left: 45%;" id="button" type="submit" name="submit" class="btn btn-primary">Insert</button>
                        </div>
                    </form>
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

