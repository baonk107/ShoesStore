<%-- 
    Document   : ProductInsert
    Created on : Mar 9, 2022, 11:42:54 PM
    Author     : MT Bac Ninh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
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
            .col-md-6 input{
                margin-bottom:  25px;
            }
        </style>
    </head>
    <body>
        <%
            ResultSet rsSup = (ResultSet) request.getAttribute("rsSupplier");
            ResultSet rsCate = (ResultSet) request.getAttribute("rsCategories");
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
                        <a href="adminHome?do=productManager" class="text-center text-muted btn btn-outline-info">Product Manager</a>

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
                    <form class="row g-3" action="productController?do=insert" method="POST">
                        <div class="col-md-6">
                            <label for="ProductName" class="form-label">ProductName</label>
                            <input required name="pName"  type="text" class="form-control" id="ProductName" placeholder="Enter ProductName">
                        </div>
                        <div class="col-md-6" >
                            <label for="SupplierID" class="form-label">SupplierID</label>
                            <select  name="suppId" style="width: 100%;height: 38px;" name="supID" class="form-select" aria-label="Default select example" class="form-control" id="SupplierID">
                                <% while (rsSup.next()) {%>
                                <option value="<%=rsSup.getInt(1)%>">  <%=rsSup.getString(2)%> </option>
                                <% }%>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="CategoryID" class="form-label">CategoryID</label>
                            <select  name="cateID" style="width: 100%;height: 38px;" class="form-select" aria-label="Default select example" class="form-control" id="CategoryID">
                                <%while (rsCate.next()) {%>
                                <option value="<%=rsCate.getInt(1)%>"> <%=rsCate.getString(2)%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="QuantityPerUnit" class="form-label">QuantityPerUnit</label>
                            <input  name="qPerUnit" type="text" class="form-control" id="QuantityPerUnit" placeholder="Enter QuantityPerUnit">
                        </div>
                        <div class="col-md-6">
                            <label for="UnitPrice" class="form-label">UnitPrice</label>
                            <input required min="0" value="0" name="price" type="number" class="form-control" id="UnitPrice" placeholder="Enter UnitPrice">
                        </div>
                        <div class="col-md-6">
                            <label for="UnitsInStock" class="form-label">UnitsInStock</label>
                            <input min="0" value="0" name="uInStock" type="number" class="form-control" id="UnitsInStock" placeholder="Enter UnitsInStock">
                        </div>
                        <div class="col-md-6">
                            <label for="UnitsOnOrder" class="form-label">UnitsOnOrder</label>
                            <input min="0" value="0" name="uOnOrder" type="number" class="form-control" id="UnitsOnOrder" placeholder="Enter UnitsOnOrder">
                        </div>
                        <div class="col-md-6">
                            <label for="ReorderLevel" class="form-label">ReorderLevel</label>
                            <input min="0" value="0" name="reOrder" type="number" class="form-control" id="ReorderLevel" placeholder="Enter ReorderLevel">
                        </div>
                        <div class="col-md-6" style="padding-top:37px; font-size: 17px;">
                            <label for="Discontinued" class="form-label">Discontinued:  </label>
                            <input type="radio" value="0" name="discontinute" checked>Continute
                            <input type="radio" value="1" name="discontinute">Discontinute
                        </div>
                        <div class="col-md-6">
                            <label for="ProDescription" class="form-label">Description</label>
                            <input  name="des" type="text" class="form-control" id="ProDescription" placeholder="Enter ProDescription">
                        </div>
                        <div class="col-md-6">
                            <label for="Discount" class="form-label">Discount</label>
                            <input min="0" value="0" name="discount" type="text" class="form-control" id="Discount" placeholder="Enter Discount">
                        </div>
                        <div class="col-md-6">
                            <label for="image" class="form-label">Image</label>
                            <input  name="image" type="text" class="form-control" id="image" placeholder="Enter URLImage">
                        </div>

                        <div class="col-12">
                            <button type="submit" name="submit" class="btn btn-primary">Insert</button>
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
