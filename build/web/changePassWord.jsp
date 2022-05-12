<%-- 
    Document   : login
    Created on : Mar 4, 2022, 3:02:02 PM
    Author     : MT Bac Ninh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Style -->
        <link rel="stylesheet" href="css/style.css">
        <title>Welcome to Login</title>
    </head>
    <body>
        <div class="d-md-flex half">
            <div class="bg" style="background-image: url('image/ShoesLogin.jpg'); background-size: cover;"></div>
            <div class="contents" style="background-image: url('image/Banner2.jpg'); background-size: cover;">
                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-md-12">
                            <div class="form-block mx-auto">
                                <div class="text-center mb-5">
                                    <h3><strong>Register</strong></h3>
                                    <p class="mb-4">Chào mừng bạn đã đến với cửa stores ĐĂNG ANH SHOP! Chúc quý khách mua hàng vui vẻ</p> 
                                </div>
                                <form action="HomeController?do=changePassWord" method="post">
                                    <c:if test="${mess != null}">
                                        <div class="alert alert-danger d-flex align-items-center" role="alert">
                                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                            <div>
                                                <i class="bi bi-exclamation-triangle-fill"></i>${mess}
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group first">
                                        <label for="username">Username</label>
                                        <input required name="user" value="" type="text" class="form-control" placeholder="Enter UserName" id="username">
                                    </div>
                                    <div class="form-group last mb-3">
                                        <label for="password">Old Password</label>
                                        <input required name="pass" value="" type="text" class="form-control" placeholder="Your Old Password" id="password">
                                    </div>
                                    <div class="form-group first mb-3">
                                        <label for="password">New password</label>
                                        <input required name="newpass" value="" type="text" class="form-control" placeholder="Your New Password" id="repassword">
                                    </div>
                                    <input name="submit" type="submit" value="Comfirm" class="btn btn-block btn-primary">
                                    <p style="margin-top: 20px">
                                        <a href="HomeController?do=login" style="color: blue;"> <- Back to Login</a>
                                    </p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
