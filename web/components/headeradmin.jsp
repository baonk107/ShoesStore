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
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top d-flex justify-content-between">
    <div class="container-fluid px-4 px-lg-5 " >
        <a class="navbar-brand" href="adminHome">Đăng Anh Shop Manager</a>
        <c:if test="${sessionScope.accountAdmin != null}">
            <!--RollNumber--> 
            <!--User--> 
            <button class="navbar-brand btn btn-outline-secondary ms-lg-1" href="#!"><i class="bi bi-person-circle"></i>Welcome: ${sessionScope.accountAdmin.userName}</button>
        </c:if>
        <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarSupportedContent">
            <c:if test="${sessionScope.accountAdmin != null}">
                <!-- Logout -->
                <a href="HomeController?do=logout" class="btn btn-primary ms-lg-1">Logout<i class="bi bi-box-arrow-in-left"></i></i></a>
            </c:if>
            <c:if test="${sessionScope.accountAdmin == null}">
                <!-- Login -->
                <a href="HomeController?do=login" class="btn btn-primary ms-lg-1">Login<i class="bi bi-box-arrow-in-right"></i></a>
                <!-- Regiter -->
                <a href="HomeController?do=register" class="btn btn-warning ms-lg-1">HomeController?do=register <i class="bi bi-heart-fill"></i></a>
                </c:if>
        </div>
    </div>
</nav>