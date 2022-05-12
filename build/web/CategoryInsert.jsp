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
            .col-md-12 .form-label{
                margin-top: 30px;
            }
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
                        <h3 style="text-align: center;">Insert Category</h3>

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
                    <form class="row g-3" action="CategoryController" method="POST">
                        <div class="col-md-12">
                            <label for="CompanyName" class="form-label">CategoryName</label>
                            <input  required name="name"  type="text" class="form-control" id="CompanyName" placeholder="Enter CategoryName">
                        </div>
                        <div class="col-md-12">
                            <label for="ContactName" class="form-label">Description</label>
                            <input required   name="des" type="text" class="form-control" id="ContactName" placeholder="Enter Description">
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

