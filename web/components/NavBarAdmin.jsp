<%-- 
    Document   : NavBarAdmin
    Created on : Mar 9, 2022, 12:03:27 AM
    Author     : MT Bac Ninh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" type="text/css">
    <style>
        html{
            /* Hành vi khi cuộn trang */
            scroll-behavior: smooth;
        }
        #iconBack{
            position: fixed;
            bottom: 40px;
            right:  10px;
            font-size: 30px;
            z-index: 9;
            color: #F44336;
        }
    </style>
</head>
<a href="#"><i id="iconBack" class="fa-solid fa-circle-arrow-up"></i></a>
<nav id="sidebar">
    <div  class="p-4 pt-5">
        <a href="#" class="img logo rounded-circle mb-5" style="background-image: url(image/profile.jpg);"></a>
        <h2 style="color: white; text-align: center">Hello: ${sessionScope.accountAdmin.userName}</h2>
        <ul class="list-unstyled components mb-5">
            <li class="active">
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">List Manager</a>
                <ul class="collapse list-unstyled" id="homeSubmenu">
                    <li>
                        <a href="adminHome?do=customerManager">Customer Manager</a>
                    </li>
                    <li>
                        <a href="adminHome?do=productManager">Product manager</a>
                    </li>
                    <li>
                        <a href="adminHome?do=billManager">Bill Manager</a>
                    </li>
                    <li>
                        <a href="adminHome?do=addCategory">Add Category</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="HomeController?do=logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
