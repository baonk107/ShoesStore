<%-- 
    Document   : index
    Created on : Mar 3, 2022, 1:29:46 PM
    Author     : MT Bac Ninh
--%>

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
        <!-- Navigation-->
        <%@include file="components/navBarComponent.jsp" %>
        <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <%
                        Product pro = (Product) request.getAttribute("product");
                    %>
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="<%=pro.getImageURL()%>" alt="..." /></div>
                    <div class="col-md-6">
                        <div class="small mb-1" style="font-size: 20px;">Số Lượng Sản Phẩm Trong Kho: <%=pro.getUnitsInStock()%></div>
                        <h1 class="display-5 fw-bolder" style="color: blue;"><%=pro.getPName()%></h1>
                        <div class="fs-5 mb-5">
                            <span class="text-decoration-line-through" style="color: red;">$20.00</span>
                            <span>$<%=pro.getUnitPrice()%></span>
                        </div>
                        <p class="lead" style="color: black;"><%=pro.getDesPro()%></p>
                        <div class="d-flex">
                            <a id="show" <c:if test="<%=pro.getUnitsInStock() == 0%>">
                               onclick="show(this.id)"                   
                                </c:if> class="btn btn-outline-dark flex-shrink-0" href="AddToCartController?pID=<%=pro.getProductID()%>&ustock=<%=pro.getUnitsInStock()%>" >
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </a>
                            <a class="btn btn-outline-success flex-shrink-0 ms-2" href="HomeController?do=showCart">
                                <i class="bi-cart-fill me-1"></i>
                                Buy Now
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
                                
        <!-- Related items section-->
        <section class="py-5 bg-light ">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related products</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-4 d-flex">
                    <%
                        List<Product> listPro = (List<Product>) request.getAttribute("listProByCate");
                        int temp = 0;
                        for (Product topPro : listPro) {
                    %>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-danger text-red position-absolute" style="top: 0.5rem; right: 0.5rem;">NEW</div>
                            <!-- Product image-->
                            <a href="DetailController?do=detail&pID=<%=topPro.getProductID()%>&cateID=<%=topPro.getCategoryID()%>">
                                <img class="card-img-top" src="<%=topPro.getImageURL()%>" alt="..." />
                            </a>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <p style="overflow: hidden; height: 24px">
                                        <a href="DetailController?do=detail&pID=<%=topPro.getProductID()%>&cateID=<%=topPro.getCategoryID()%>" class="fw-bolder text-decoration-none" ><%=topPro.getPName()%></a>
                                    </p>
                                    <!-- Product reviews-->
                                    <p style="overflow: hidden; height: 45px;">
                                        <span class="text-warning"><%=topPro.getDesPro()%></span>
                                    </p>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">$<%=pro.getUnitPrice()%></span>
                                    $<%=Math.round((topPro.getUnitPrice() - (topPro.getUnitPrice() * topPro.getDiscount())) * 100) / 100%>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <!--<form>-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a id="show" <c:if test="<%=topPro.getUnitsInStock() == temp%>">
                                                            onclick="show(this.id)"                   
                                        </c:if> class="btn btn-outline-dark mt-auto" href="AddToCartController?pID=<%=topPro.getProductID()%>&ustock=<%=topPro.getUnitsInStock()%>">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </section>
        <!--Footer And Script-->
        <%@include file="components/footerAdmin.jsp" %>
        <script>
            function show() {
                alert("Hết hàng");
            }
            ;
        </script>
    </body>
</html>

