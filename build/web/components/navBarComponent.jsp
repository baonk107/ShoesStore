<%-- 
    Document   : navBarComponent
    Created on : Mar 3, 2022, 10:27:43 PM
    Author     : MT Bac Ninh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .nav-link:hover{
        background-color: gray;
        color: white;
    }
</style>

<!--Nav 1-->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top 
     <c:if test="${sessionScope.account != null}">
         pt-5"
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
<c:if test="${sessionScope.account != null}">
    <!--Nav INFO-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top pt-1">
        <div class="container px-4 px-lg-5 d-flex justify-content-evenly">
            <!--RollNumber--> 
            <button type="button" class="btn btn-outline-secondary ms-lg-1">HE151451:Nguyễn Khắc Bảo</button>
            <!--User--> 
            <button class="navbar-brand btn btn-outline-secondary ms-lg-1" href="#!"><i class="bi bi-person-circle"></i>Welcome: ${sessionScope.account.userName}</button>
        </div>
    </nav>
</c:if>