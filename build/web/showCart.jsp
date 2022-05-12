<%-- 
    Document   : index
    Created on : Mar 3, 2022, 1:29:46 PM
    Author     : MT Bac Ninh
--%>

<%@page import="Entity.CartProduct"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <%
            List<CartProduct> list = (List<CartProduct>) request.getAttribute("listProductCarts");
        %>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light
             <c:if test="${sessionScope.account != null}">
                 fixed-top "
             </c:if>>
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="HomeController">Đăng Anh Shop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link " aria-current="page" href="/ProjectShopping/HomeController">Home</a></li>
                        <li class="nav-item"><a class="nav-link " href="CheckOutController?do=billPurchased"><i class="bi bi-cart-check-fill"></i>Purchased products</a></li>
                    </ul>
                    <!-- Search -->
<!--                    <form class="d-flex mx-auto" action="HomeController?do=search" method="POST">
                        <input name="searchKey" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-success" type="submit">Search</button>
                    </form>-->
                    <!-- Cart -->
                    <div class="d-flex my-2">
                        <a class="btn btn-outline-dark" href="HomeController?do=showCart">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.size == null?0:sessionScope.size}</span>
                        </a>
                    </div>
                    <c:if test="${sessionScope.account != null}">
                        <!-- Logout -->
                        <a href="HomeController?do=logout" class="btn btn-primary ms-lg-1">Logout<i class="bi bi-box-arrow-in-left"></i></i></a>
                    </c:if>
                    <c:if test="${sessionScope.account == null}">
                        <!-- Login -->
                        <a href="HomeController?do=login" class="btn btn-primary ms-lg-1">Login<i class="bi bi-box-arrow-in-right"></i></a>
                        <!-- Regiter -->
                        <a href="HomeController?do=register" class="btn btn-warning ms-lg-1">Regiter <i class="bi bi-heart-fill"></i></a>
                        </c:if>
                </div>
            </div>
        </nav>
        <!-- Product section-->
        <section class="py-5">
            <div class="container" style="min-height: 350px; margin-top: 50px;">
                <h3 style="text-align: center; margin: 20px;">List Products</h3>
                <table class="table table-bordered table-striped text-center">
                    <thead class="thead-dark bg-dark text-white">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Image</th>
                            <th scope="col">Name</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Total</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (CartProduct pro : list) {%>
                    <form action="HomeController?do=updateQuantity" method="post">
                        <tr>
                        <input type="hidden" name="productID" value="<%=pro.getProId()%>">
                        <td><%=pro.getProId()%></td>
                        <td><img src="<%=pro.getUrlImages()%>" width="100"></td>
                        <td><%=pro.getProName()%></td>
                        <td><input min="1" onchange="this.form.submit()" type="number" value="<%=pro.getQuantity()%>" name="quantity"></td>
                        <td><%=pro.getPrice()%></td>
                        <td><%=pro.getQuantity() * pro.getPrice()%></td>
                        <td><a class="text-decoration-none btn btn-outline-danger" href="HomeController?do=removeCart&id=<%=pro.getProId()%>">Remove</a><i class="bi bi-trash"></i></td>
                        </tr>
                    </form>
                    <%}%>
                    <tr>
                        <td colspan="5"></td>
                        <td >Total Price: ${total}</td>
                        <td><a class="text-decoration-none btn btn-outline-info" href="HomeController?do=removeCart">Remove All</a><i class="bi bi-trash"></i></td>
                    </tr> 
                    </tbody>
                </table>
                <a <c:if test="${sessionScope.account != null}">
                        href="CheckOutController?do=checkout"
                    </c:if>  
                    <c:if test="${sessionScope.account == null}">
                        href="HomeController?do=login"
                    </c:if>  
                    class="btn btn-primary text-decoration-none" style="min-width: 250px; float: right; margin-top: 50px; border-radius: 5px; font-weight: bold;">Check out</a>
            </div>
        </section>
        <!--Footer And Script-->
        <div style="margin-top: 70px;">
            <%@include file="components/footerAdmin.jsp" %>
        </div>
    </body>
</html>

