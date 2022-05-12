<%-- 
    Document   : ManagerProduct
    Created on : Mar 9, 2022, 12:08:21 AM
    Author     : MT Bac Ninh
--%>

<%@page import="Entity.Product"%>
<%@page import="java.util.List"%>
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
            body{
                background-image: url("image/bgcheckout.jpg");
            }
        </style>
    </head>
    <body>
        <%
            List<Product> listProduct = (List<Product>) request.getAttribute("listPro");
            List<Product> listNotIn = (List<Product>) request.getAttribute("listNotIn");
            List<Product> listIn = (List<Product>) request.getAttribute("listIn");
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

                        <!-- Search -->
                        <div>
                            <form class="d-flex mx-auto" action="productController?do=search" method="POST">
                                <input style="min-width:  350px;" name="searchKey" class="form-control me-2" type="search" placeholder="Search By ProductID or Product Name" aria-label="Search">
                                <button class="btn btn-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
                <c:if test="${mess != null}" >
                    <div class="${mess!=null?"alert alert-danger":""}" role="alert">
                        ${mess}
                    </div>
                </c:if>
                <c:if test="${messRemove != null}" >
                    <div class="${messRemove!=null?"alert alert-success":""}" role="alert">
                        ${messRemove}
                    </div>
                </c:if>
                <!--Add Product-->
                <p class="d-flex justify-content-end" style="cursor: pointer; padding: 5px;">
                    <a href="productController?do=insert" class="btn btn-success text-dark"><i class="material-icons" style="">add_circle</i>Add New Product</a>
                </p>
                <!--List Bill-->
                <div class="col-md-12" style="padding: 0;">
                    <table class="border table table-striped table-hover table-bordered text-center" style="margin-top: 10px;">
                        <thead class="bg-info" style="position: -webkit-sticky; position: sticky; top: 0;">
                            <tr>
                                <th>PID</th>
                                <th>Product Name</th>
                                <th>Supplier ID</th>
                                <th>Category ID</th>
                                <th>Qquantity PerUnit</th>
                                <th>Unit Price</th>
                                <th>Units InStock</th>
                                <th>Units OnOrder</th>
                                <th>Reorder Level</th>
                                <th>Discontinue</th>
                                <th>Image</th>
                                <th>Description</th>
                                <th>Discount</th>
                                <th colspan="2" class="text-center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Product pro : listProduct) {%>
                            <tr>
                                <td><%=pro.getProductID()%></td>
                                <td><%=pro.getPName()%></td>
                                <td><%=pro.getSupplierID()%></td>
                                <td><%=pro.getCategoryID()%></td>
                                <td><%=pro.getQuantityPerUnit()%></td>
                                <td><%=pro.getUnitPrice()%></td>
                                <td><%=pro.getUnitsInStock()%></td>
                                <td><%=pro.getUnitsOnOrder()%></td>
                                <td><%=pro.getReorderLevel()%></td>
                                <td><%=pro.getDiscontinued()%></td>
                                <td><img src="<%=pro.getImageURL()%>" style="width: 100px"></td>
                                <td><%=pro.getDesPro()%></td>
                                <td><%=pro.getDiscount()%></td>
                                <td><a href="productController?do=update&pId=<%=pro.getProductID()%>"><i style="color: #FFC107;" class="material-icons">&#xE254;</i></a></td>
                                <td>
                                    <%for (Product proCheck : listNotIn) {
                                            //Check Được xóa
                                            if (proCheck.getProductID() == (pro.getProductID())) {
                                    %>
                                    <a href="productController?do=deleteProduct&pId=<%=pro.getProductID()%>"><i style="color: #F44336;" class="material-icons">&#xE872;</i></a>
                                    <%} %>
                                    <%}%>
                                    <%for (Product proCheck : listIn) {
                                            //Check Khong duoc Fxóa
                                            if (proCheck.getProductID() == (pro.getProductID())) {
                                    %>
                                    <a class="btn disabled" href="productController?do=deleteProduct&pId=<%=pro.getProductID()%>"><i style="color: #F44336;" class="material-icons">&#xE872;</i></a>
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
