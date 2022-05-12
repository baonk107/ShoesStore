<%-- 
    Document   : index
    Created on : Mar 3, 2022, 1:29:46 PM
    Author     : MT Bac Ninh
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="Entity.Status"%>
<%@page import="Entity.OrderList"%>
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
            ResultSet rsOdDetail = (ResultSet) request.getAttribute("listOrderDetail");
            List<OrderList> listOrder = (List<OrderList>) request.getAttribute("listOrder");
            List<Status> listStatus = (List<Status>) request.getAttribute("listStatus");
        %>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light
             <c:if test="${sessionScope.account != null}">
                 fixed-top pt-5"
             </c:if>>
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="HomeController">Đăng Anh Shop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link " aria-current="page" href="/ProjectShopping/HomeController">Home</a></li>
                        <li class="nav-item"><a class="nav-link " href="HomeController?do=showCart"><i class="bi bi-cart-check-fill"></i>ShowCart</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- Search -->
                    <form class="d-flex mx-auto" action="HomeController?do=search" method="POST">
                        <input name="searchKey" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-success" type="submit">Search</button>
                    </form>
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
            <div class="container" style="min-height: 350px; margin-top: 70px;">
                <h3>List of purchased products</h3>
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
                                <%for (Status sta : listStatus) {%>
                                <%=(sta.getId() == odList.getStatus() ? sta.getStatusName() : "")%>
                                <%}%>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                </div>

                <h2 style="color: red; text-align: center; font-weight: bold;">List Order Detail</h2>
                <!--List Bill Detail-->
                <div class="col-md-12">
                    <table class="border table table-striped table-hover table-bordered border-primary" style="margin-top: 10px">
                        <thead class="bg-info" style="position: -webkit-sticky; top: 0;">
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
        </section>
        <!--Footer And Script-->
        <div style="margin-top: 70px;">
            <%@include file="components/footerAdmin.jsp" %>
        </div>
    </body>
</html>

