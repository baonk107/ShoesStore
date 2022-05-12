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
        <style>
            body{
                /*background-image: url("image/bg.jpg");*/
                background-color: #bdbebf;
            }

            #myVideo {
                position: fixed;
                right: 0;
                bottom: 0;
                left: 0;
                top: 0;
                min-width: 100%;
                min-height: 100%;
                z-index: -999;
            }
        </style>
    </head>
    <body>
        <video id="myVideo" autoplay muted loop width="320" height="240" controls>
            <source src="image/bg.mp4" type="video/mp4">
        </video>
        <!-- Navigation-->
        <%@include file="components/navBarComponent.jsp" %>
        <!-- Header-->
        <div id="carouselExampleCaptions" class="carousel slide mh-50" data-bs-ride="carousel" <c:if test="${sessionScope.account != null}">
             style="margin-top: 100px;
            </c:if>">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="image/Shop shoes.jpg" class="d-block w-100 mh-50" alt=""/>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>First slide label</h5>
                        <p>Some representative placeholder content for the first slide.</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="image/Shop shoes1.jpg" class="d-block w-100 mh-50" alt=""/>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Second slide label</h5>
                        <p>Some representative placeholder content for the second slide.</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="image/Shop shoes2.jpg" class="d-block w-100 mh-50" alt=""/>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Third slide label</h5>
                        <p>Some representative placeholder content for the third slide.</p>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5">
                <h3 class="text-center" style="color: red;font-weight: bold; margin-bottom: 20px">List Categories</h3>
                <!--Category-->
                <div style="position: -webkit-sticky; position: sticky; 
                     <c:if test="${sessionScope.account != null}">
                         top: 109px; 
                     </c:if>
                     <c:if test="${sessionScope.account == null}">
                         top: 69px; 
                     </c:if>
                     z-index: 9; background-color: #f8f9fa; padding: 5px 0;" class="col-md-12 mb-sm-12 d-flex justify-content-center flex-wrap">

                    <!--Form ListCategory-->
                    <form action="HomeController?do=fillCategory" method="post" class="d-flex justify-content-center flex-wrap">
                        <c:forEach items="${sessionScope.listCategory}" var="c">
                            <ul class="list-group">
                                <li class="list-group-item btn btn-outline-primary ms-lg-1">
                                    <input type="checkbox" 
                                           <c:forEach items="${arrCate}" var="a">
                                               ${c.cateID == a?" checked":""}
                                           </c:forEach>
                                           onclick="this.form.submit()" name="cateID" value="${c.cateID}" style="padding: 3px;"/> ${c.cateName}
                                </li>
                            </ul>
                        </c:forEach>
                    </form>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <!--ListProduct-->
                        <h3 class="text-center" style="color: red;font-weight: bold; margin: 20px 0;">List Products</h3>

                        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-4 justify-content-center">
                            <%
                                List<Product> listPro = (List<Product>) request.getAttribute("listProduct");
                                int temp = 0;
                                for (Product pro : listPro) {
                            %>
                            <div class="col mb-5">
                                <div class="card h-100">
                                    <!-- Sale badge-->
                                    <div class="badge bg-danger text-red position-absolute" style="top: 0.5rem; right: 0.5rem;">NEW</div>
                                    <!-- Product image-->
                                    <a href="DetailController?do=detail&pID=<%=pro.getProductID()%>&cateID=<%=pro.getCategoryID()%>&page=${page}">
                                        <img class="card-img-top" src="<%=pro.getImageURL()%>" alt="..." />
                                    </a>
                                    <!-- Product details-->
                                    <div class="card-body p-4">
                                        <div class="text-center">
                                            <!-- Product name-->
                                            <p style="overflow: hidden; height: 24px">
                                                <a href="DetailController?do=detail&pID=<%=pro.getProductID()%>&cateID=<%=pro.getCategoryID()%>&page=${page}" class="fw-bolder text-decoration-none" ><%=pro.getPName()%></a>
                                            </p>
                                            <!-- Product reviews-->
                                            <p style="overflow: hidden; height: 45px;">
                                                <span class="text-warning"><%=pro.getDesPro()%></span>
                                            </p>
                                            <!-- Product price-->
                                            <span class="text-muted text-decoration-line-through">$<%=pro.getUnitPrice()%></span>
                                            $<%=Math.round((pro.getUnitPrice() - (pro.getUnitPrice() * pro.getDiscount())) * 100) / 100%>
                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <!--<form>-->

                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div class="text-center"><a id="show" <c:if test="<%=pro.getUnitsInStock() == temp%>">
                                                                    onclick="show(this.id)"                   
                                                </c:if> class="btn btn-outline-dark mt-auto" href="AddToCartController?pID=<%=pro.getProductID()%>&ustock=<%=pro.getUnitsInStock()%>&page=${page}${fill}">Add to cart</a></div>
                                    </div>

                                </div>
                            </div>
                            <%}%>
                        </div>
                        <!--Paging Product-->
                        <c:if test="${sessionScope.backToUrl eq \"HomeController\"}">
                            <c:choose>
                                <c:when test="${listProduct == null || listProduct.size() == 0}">
                                    Not FOUND
                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination d-flex justify-content-center">
                                            <c:if test="${page > 1}">
                                                <li class="page-item" ><a class="page-link" href="HomeController?page=${page - 1}" >Previous</a></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item ${i == page?" active":""}"><a class="page-link" href="HomeController?page=${i}">${i}</a></li>
                                                </c:forEach>
                                                <c:if test="${page < totalPage}">
                                                <li class="page-item"><a class="page-link" href="HomeController?page=${page + 1}">Next</a></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <!--Paging Search-->
                        <c:if test="${sessionScope.backToUrl eq searchPage}">
                            <c:choose>
                                <c:when test="${listProduct == null || listProduct.size() == 0}">
                                    Not FOUND
                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination d-flex justify-content-center">
                                            <c:if test="${page > 1}">
                                                <li class="page-item" ><a class="page-link" href="HomeController?do=search&page=${page - 1}&searchKey=${keySearch}" >Previous</a></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item ${i == page?" active":""}"><a class="page-link" href="HomeController?do=search&page=${i}&searchKey=${keySearch}">${i}</a></li>
                                                </c:forEach>
                                                <c:if test="${page < totalPage}">
                                                <li class="page-item"><a class="page-link" href="HomeController?do=search&page=${page + 1}&searchKey=${keySearch}">Next</a></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <!--Paging Category-->
                        <c:if test="${sessionScope.backToUrl eq \"HomeController?do=fillCategory\"}">
                            <c:choose>
                                <c:when test="${listProduct == null || listProduct.size() == 0}">
                                    Not FOUND
                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination d-flex justify-content-center">
                                            <c:if test="${page > 1}">
                                                <li class="page-item" ><a class="page-link" href="HomeController?do=fillCategory&page=${page - 1}${fill}" >Previous</a></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item ${i == page?" active":""}"><a class="page-link" href="HomeController?do=fillCategory&page=${i}${fill}">${i}</a></li>
                                                <input type="hidden" name="cateID" value="">
                                            </c:forEach>
                                            <c:if test="${page < totalPage}">
                                                <li class="page-item"><a class="page-link" href="HomeController?do=fillCategory&page=${page + 1}${fill}">Next</a></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
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

