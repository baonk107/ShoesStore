package Controller;

import DAO.DAOCategory;
import DAO.DAOCustomer;
import DAO.DAOProduct;
import Entity.Account;
import Entity.CartProduct;
import Entity.Category;
import Entity.Customer;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MT Bac Ninh
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("do");
            HttpSession session = request.getSession();//Session
            DAOCategory daoCate = new DAOCategory();
            DAOProduct daoPro = new DAOProduct();
            DAOCustomer daoCus = new DAOCustomer();
            if (service == null) {
                service = "home";
            }
            //Home
            if (service.equals("home")) {//Phân trang for Product
                String pageStr = request.getParameter("page");//Get Page

                //-------
                List<Category> listCategory = daoCate.getAllCategory();

                //Phân trang
                int page = 1;
                final int PAGE_SIZE = 12;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                List<Product> listProduct = daoPro.getAllProductsWithPaging(page, PAGE_SIZE);
                int totalProduct = daoPro.getTotalProduct();
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                //---------------
                String[] arrCate = request.getParameterValues("cateID");

                //------------
                //Set Data for Session
                session.setAttribute("listCategory", listCategory);
                //Set Data for JSP
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);//Phân Trang
                session.setAttribute("backToUrl", "HomeController");
                //Run
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            //Search
            if (service.equals("search")) {
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");

                String keySearch = request.getParameter("searchKey");
                if (keySearch.isEmpty()) {
                    response.sendRedirect("HomeController");
                    return;
                }
                //Phân trang
                String pageStr = request.getParameter("page");//Get Page
                int page = 1;

                final int PAGE_SIZE = 12;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                List<Product> listProduct = daoPro.getAllProductsPagingByName(keySearch, page, PAGE_SIZE);
                int totalProduct = daoPro.getTotalProductByPName(keySearch);
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                //---------------
                List<Category> listCate = new DAOCategory().getAllCategory();

                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);

                //Set for Paging Search
                String searchPage = "HomeController?do=search" + "&searchKey=" + keySearch + "&page=" + page;
                request.setAttribute("searchPage", searchPage);

                //HomeController?do=search&page=2&searchKey=D
                request.setAttribute("keySearch", keySearch);
                session.setAttribute("backToUrl", "HomeController?do=search&searchKey=" + keySearch + "&page=" + page);

                request.setAttribute("listCategory", listCate);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (service.equals("fillCategory")) {
                //12345678
                String[] arrCate = request.getParameterValues("cateID");
                if (arrCate == null) {
                    response.sendRedirect("HomeController");
                    return;
                }
                //Phân trang
                String pageStr = request.getParameter("page");//Get Page
                //Phân trang
                int page = 1;
                final int PAGE_SIZE = 12;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                List<Product> listProduct = daoPro.getAllProductsWithPagingByCateID(arrCate, page, PAGE_SIZE);
                int totalProduct = daoPro.getTotalProductByCate(arrCate);//Get total page
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                //Get Lisy
                List<Category> listCate = new DAOCategory().getAllCategory();
                //List Cate
                String fill = "";
                for (String aCate : arrCate) {
                    fill += "&cateID=" + aCate;
                }

                //Set request paging
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);

                request.setAttribute("fill", fill);
                request.setAttribute("arrCate", arrCate);

                session.setAttribute("backToUrl", "HomeController?do=fillCategory");//Chuyển hướng trang

                request.setAttribute("listProduct", listProduct);//Name phải giống bên HomeCtroller
                request.setAttribute("listCategory", listCate);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (service.equals("showCart")) {
                List<CartProduct> listProductCarts = new ArrayList<>();
                Enumeration em = session.getAttributeNames();
                double total = 0;
//                int sum = 0;
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (!key.equals("backToUrl") && !key.equals("size") && !key.equals("listCategory") && !key.equals("account")) {
                        CartProduct pro = (CartProduct) session.getAttribute(key);
                        if (pro == null) {
                            listProductCarts = new ArrayList<>();
                        }
//                        sum += pro.getQuantity();
                        total += (pro.getPrice() * pro.getQuantity());
                        listProductCarts.add(pro);
                        session.setAttribute(key, pro);
                    }
                }
//                session.setAttribute("size", sum);
                request.setAttribute("total", total);
                request.setAttribute("listProductCarts", listProductCarts);
                request.getRequestDispatcher("showCart.jsp").forward(request, response);
            }
            //RemoveCart
            if (service.equals("removeCart")) {
                String id = request.getParameter("id");
                if (id != null) {
                    String size = session.getAttribute("size").toString();
                    int sizes = Integer.parseInt(size) - 1;
                    String sizeLast = "" + sizes;
                    session.setAttribute("size", sizeLast);
                    session.removeAttribute(id);
                } else {
                    Enumeration em = session.getAttributeNames();
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (!key.equals("backToUrl") && !key.equals("listCategory") && !key.equals("account")) {
                            session.removeAttribute(key);
                        }
                    }
                }
                response.sendRedirect("HomeController?do=showCart");
            }
            //updateQuantity
            if (service.equals("updateQuantity")) {
                String strQuantity = request.getParameter("quantity");
                String id = request.getParameter("productID");
                if (strQuantity.isEmpty()) {//When User delete input quantity
                    CartProduct pro = (CartProduct) session.getAttribute(id);
                    pro.setQuantity(0);
                    response.sendRedirect("HomeController?do=showCart");
                    return;
                }

                int quantity = Integer.parseInt(strQuantity);
//                session.setAttribute("size", quantity);
                System.out.println(quantity);
                Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals(id)) {
                        CartProduct pro = (CartProduct) session.getAttribute(key);
                        pro.setQuantity(quantity);
                        break;
                    }
                }
                response.sendRedirect("HomeController?do=showCart");
            }
            //Login
            if (service.equals("login")) {
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    //Get request
                    String userName = request.getParameter("user");
                    String passWord = request.getParameter("pass");
                    List<Account> listAllAcc = daoCus.getAllAccount();
                    boolean checkLogin = false;
                    Account account = null;
                    for (Account acc : listAllAcc) {
                        if (acc.getUserName() != null || acc.getPassWord() != null) {
                            if (acc.getUserName().equals(userName) && acc.getPassWord().equals(passWord)) {
                                checkLogin = true;
                                account = acc;
                                break;
                            }
                        }
                    }
                    //Check Acount
                    if (checkLogin == false) {//Login Fail
                        String mess = "USERNAME or PASSWORD invalid!";
                        request.setAttribute("mess", mess);
                        request.setAttribute("user", userName);
                        request.setAttribute("pass", passWord);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        if (account.getRoll() == 1) {
                            session.setAttribute("accountAdmin", account);//Session
                            session.setMaxInactiveInterval(86400);
                            response.sendRedirect("adminHome");
                        } else {
                            Customer cus = daoCus.getCusIDByUserName(userName);
                            Account accountAll = Account.builder()
                                    .customerID(cus.getCustomerID())
                                    .userName(userName)
                                    .passWord(passWord)
                                    .build();
                            session.setAttribute("account", accountAll);//Session
                            session.setMaxInactiveInterval(86400);
                            response.sendRedirect("HomeController?do=home");
                        }
                    }
                }
            }
            //Register
            if (service.equals("register")) {
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    //Get para
                    String userName = request.getParameter("user");
                    String passWord = request.getParameter("pass");
                    String rePassWord = request.getParameter("repass");

                    String mess;
                    //Check Pass
                    if (passWord.equals(rePassWord)) {
                        boolean checkUserName = daoCus.checkRegister(userName, passWord);
                        if (checkUserName == false) {
                            mess = "User Name exits!!!";
                        } else {
                            mess = "Register Succesfull";
                            request.setAttribute("mess", mess);
                            request.setAttribute("user", userName);
                            request.setAttribute("pass", passWord);

                            Customer cus = daoCus.getCusIDByUserName(userName);
                            Account accountAll = Account.builder()
                                    .customerID(cus.getCustomerID())
                                    .userName(userName)
                                    .passWord(passWord)
                                    .build();
                            session.setAttribute("account", accountAll);//Session
                            session.setMaxInactiveInterval(86400);
                            response.sendRedirect("HomeController?do=home");
                            return;
                        }
                    } else {
                        mess = "Password must same Repasswoed";
                    }
                    request.setAttribute("mess", mess);
                    request.setAttribute("user", userName);
                    request.setAttribute("pass", passWord);
                    request.setAttribute("repass", rePassWord);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
            if (service.equals("changePassWord")) {//Change Pass word
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("changePassWord.jsp").forward(request, response);
                } else {
                    //Get para
                    String userName = request.getParameter("user");
                    String passWord = request.getParameter("pass");
                    String newPass = request.getParameter("newpass");

                    String mess;
                    //Check userName and PassWord
                    Account checkPass = daoCus.checkLogin(userName, passWord);
                    if (checkPass == null) {
                        mess = "UserName or PassWord invalid!";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("changePassWord.jsp").forward(request, response);
                    } else {
                        mess = "Change PassWord Successful!";
                        daoCus.updatePassWordByUser(newPass, userName);
                        request.setAttribute("mess", mess);
                        request.setAttribute("user", userName);
                        request.setAttribute("pass", newPass);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            }
            //Logout
            if (service.equals("logout")) {
                //--------------
                Enumeration em = session.getAttributeNames();
//                while (em.hasMoreElements()) {
//                    String key = em.nextElement().toString();
//                    if (key.equals("account")) {
//                        session.removeAttribute(key);
//                    }
//                    if (key.equals("accountAdmin")) {
//                        session.removeAttribute(key);
//                    }
//                }
                session.invalidate();
                //--------------
                response.sendRedirect("HomeController?do=home");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
