package Controller;

import DAO.DAOCustomer;
import DAO.DAOOrder;
import DAO.DAOOrderDetail;
import Entity.Account;
import Entity.CartProduct;
import Entity.Customer;
import Entity.Order;
import Entity.OrderList;
import Entity.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

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
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("account");
            if (acc == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                if (acc.getRoll() == 0) {
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    /* TODO output your page here. You may use following sample code. */
                    String service = request.getParameter("do");
                    DAOOrder daoOrder = new DAOOrder();
                    DAOOrderDetail daoOrDetail = new DAOOrderDetail();
                    DAOCustomer daoCus = new DAOCustomer();
                    if (service == null) {
                        response.sendRedirect("checkout");
                    }
                    if (service.equals("checkout")) {//Gửi Thông tin showCart sang check out
                        List<CartProduct> listProductCarts = new ArrayList<>();
                        Enumeration em = session.getAttributeNames();

                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (!key.equals("backToUrl") && !key.equals("size") && !key.equals("listCategory") && !key.equals("account")) {
                                CartProduct pro = (CartProduct) session.getAttribute(key);
                                if (pro == null) {
                                    listProductCarts = new ArrayList<>();
                                }
                                listProductCarts.add(pro);
                            }
                        }

                        request.setAttribute("listProductCarts", listProductCarts);
                        request.getRequestDispatcher("checkout.jsp").forward(request, response);
                    }
                    if (service.equals("checkout-info")) {//Add order
                        request.setCharacterEncoding("UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        //get data from Form
                        String customerID = request.getParameter("customerID");
                        String address = request.getParameter("address");
                        String totalPrice = request.getParameter("total");
                        String status = request.getParameter("status");

                        ////Get Data Product
                        List<CartProduct> listProductCarts = new ArrayList<>();
                        Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (!key.equals("backToUrl") && !key.equals("size") && !key.equals("listCategory") && !key.equals("account")) {
                                CartProduct pro = (CartProduct) session.getAttribute(key);
                                if (pro == null) {
                                    listProductCarts = new ArrayList<>();
                                }
                                listProductCarts.add(pro);
                                session.setAttribute(key, pro);
                            }
                        }
                        System.out.println(totalPrice);
                        //Lưu Order
                        Order order = Order.builder()
                                .CustomerID(customerID)
                                .ShipAddress(address)
                                .totalPrice(Double.parseDouble(totalPrice))
                                .status(Integer.parseInt(status))
                                .build();
                        //Get 
                        System.out.println(order);
                        int orderID = daoOrder.returnOrderID(order);
                        //Lưu OrderDetails
                        int n = daoOrDetail.insertOrderDetail(orderID, listProductCarts);
                        if (n > 0) {//Check out successs
                            session.removeAttribute("size");

                            response.sendRedirect("CheckOutController?do=billPurchased");
                        }
                    }
                    if (service.equals("billPurchased")) {//Bill Purchased
                        //Remove after checkout
                        Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (!key.equals("backToUrl") && !key.equals("listCategory") && !key.equals("account")) {
                                session.removeAttribute(key);
                            }
                        }
                        //Bill Order 
                        List<OrderList> listOrder = daoOrder.getAllOrderListByUser(acc.getUserName());
                        //Get Status
                        List<Status> listStatus = daoOrder.getStatus("select * from Status");
                        //Set data
                        request.setAttribute("listOrder", listOrder);
                        request.setAttribute("listStatus", listStatus);
                        request.getRequestDispatcher("billPurchased.jsp").forward(request, response);
                    }
                    if (service.equals("listAllByStatus")) {//Get All By Status
                        int statusID = Integer.parseInt(request.getParameter("status"));
                        //Get data for Order List
                        List<OrderList> listOrder;
                        if (statusID == -1) {
                            listOrder = daoOrder.getAllOrderListByUser(acc.getUserName());
                        } else {
                            listOrder = daoOrder.getAllOrderListByStatusIDAndUser(acc.getUserName(), statusID);
                        }
                        //Get Status
                        List<Status> listStatus = daoOrder.getStatus("select * from Status");
                        //Set data
                        request.setAttribute("statusID", statusID);
                        request.setAttribute("listOrder", listOrder);
                        request.setAttribute("listStatus", listStatus);
                        request.getRequestDispatcher("billPurchased.jsp").forward(request, response);
                    }
                    if (service.equals("purchasedDetail")) { //Bill Purchased Detail
                        int orderID = Integer.parseInt(request.getParameter("orderID"));
                        //Get list Order
                        List<OrderList> listOrder = daoOrder.getAllOrderListByID(orderID);
                        List<Status> listStatus = daoOrder.getStatus("select * from Status");//Get List Status
                        ResultSet listOrderDetail = daoOrder.getData("select p.ProductID,p.ProductName,o.Quantity,o.UnitPrice,(o.Quantity * o.UnitPrice) as Total\n"
                                + "from [Order Details] o \n"
                                + "join Products p on o.ProductID = p.ProductID\n"
                                + "where o.OrderID =" + orderID);
                        //Set Data for JSP
                        request.setAttribute("listOrder", listOrder);
                        request.setAttribute("listStatus", listStatus);
                        request.setAttribute("listOrderDetail", listOrderDetail);

                        request.getRequestDispatcher("PurchasedDetail.jsp").forward(request, response);
                    }
                }
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
