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
        <style>
            body{
                background-image: url("image/bgcheckout.jpg");
                background-size: auto;
                background-position: right;
            }
        </style>
    </head>
    <body>
        <%
            List<CartProduct> list = (List<CartProduct>) request.getAttribute("listProductCarts");
//            String customerID = (String)request.getAttribute("customerID");
        %>
        <!-- Product section-->
        <section class="py-5">
            <div class="container" ">
                <div class="row">
                    <h3 class="text-center" style="margin: 20px 0;">CHECK OUT</h3>
                    <div class="col-md-7" style="border: 1px solid #ccc; border-radius: 5px">
                        <h3 class="text-center">List Products purchased</h3>
                        <table class="table table-bordered table-striped text-center">
                            <thead class="thead-dark bg-dark text-white">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Image</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% double total = 0; %>
                                <% for (CartProduct pro : list) { //Lấy ra các bản ghi%>
                                <tr>
                                    <td><%=pro.getProId()%></td>
                                    <td><img src="<%=pro.getUrlImages()%>" width="100"></td>
                                    <td><%=pro.getProName()%></td>
                                    <td><%=pro.getQuantity()%></td>
                                    <td><%=pro.getPrice()%></td>
                                    <td><%=pro.getQuantity() * pro.getPrice()%></td>
                                    <% total += (pro.getQuantity() * pro.getPrice()); %>
                                </tr>
                                <%}%>
                                <tr>
                                    <td colspan="5"></td>
                                    <td colspan="1">Total Price: <%=total%></td>
                                </tr> 
                            </tbody>
                        </table>
                    </div>
                    <c:if test="${sessionScope.size == null}">
                        <div class="col-md-5" style="border: 1px solid #ccc; border-radius: 5px">
                            <div style="">
                                <h3 class="text-center" style="color: red">No products have been added</h3>
                                <a class="btn btn-block btn-outline-primary w-100" href="HomeController" style="text-decoration: none;">Back to buy</a>
                            </div>
                        </div>
                    </c:if>     
                    <c:if test="${sessionScope.size != null}">
                        <div class="col-md-5" style="border: 1px solid #ccc; border-radius: 5px">
                            <h3 class="text-center " style="margin: 20px">Information of Customer</h3>
                            <a class="btn btn-block btn-outline-primary w-100" href="HomeController" style="text-decoration: none;">Back to buy</a>
                            <form action="CheckOutController?do=checkout-info" method="POST">
                                <div class="mb-3">
                                    <label for="CustomerID" class="form-label">CustomerID</label>
                                    <input readonly value="${sessionScope.account.customerID}" type="text" class="form-control" id="CustomerID" name="customerID" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="ShipAddress" class="form-label">Address</label>
                                    <input type="text" class="form-control" id="ShipAddress" name="address" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="total" class="form-label">TotalPrice</label>
                                    <input value="<%=total%>" readonly type="email" class="form-control" id="total" name="total" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="status" class="form-label">Status</label>
                                    <input readonly value="1" type="text" class="form-control" id="status" name="status" aria-describedby="emailHelp">
                                </div>
                                <button id="show" type="submit" class="btn btn-primary w-100">Submit</button>
                            </form>
                        </div>
                    </c:if>

                </div>
            </div>
        </section>

        <!--Footer And Script-->
        <div style="margin-top: 300px;">
            <%@include file="components/footerAdmin.jsp" %>
        </div>
    </body>
</html>

