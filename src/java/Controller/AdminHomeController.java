package Controller;

import DAO.DAOCustomer;
import DAO.DAOOrder;
import DAO.DAOProduct;
import Entity.Account;
import Entity.Customer;
import Entity.Order;
import Entity.OrderList;
import Entity.Product;
import Entity.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "AdminHomeController", urlPatterns = {"/adminHome"})
public class AdminHomeController extends HttpServlet {

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
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("accountAdmin");
            if (acc.getRoll() == 1) {
                String service = request.getParameter("do");
                DAOProduct daoPro = new DAOProduct();
                DAOCustomer daoCus = new DAOCustomer();
                DAOOrder daoOrder = new DAOOrder();
                if (service == null) {
                    service = "billManager";
                }
                if (service.equals("productManager")) {
                    List<Product> listNotIn = daoPro.getAllPIDNotInOrderDetail();
                    List<Product> listIn = daoPro.getAllPIDInOrderDetail();

                    List<Product> listPro = daoPro.getAllProduct();
                    //Set Data for request
                    request.setAttribute("listNotIn", listNotIn);
                    request.setAttribute("listIn", listIn);
                    request.setAttribute("listPro", listPro);
                    //Select JSP
                    request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
                }
                if (service.equals("customerManager")) {
                    List<Customer> listCheck = daoCus.getAllCusIDNotInOrder();
                    List<Customer> listCheckIn = daoCus.getAllCusIDInOrder();

                    List<Customer> listCus = daoCus.listAllCustommer();
                    //Set Data for request
                    request.setAttribute("listCus", listCus);
                    request.setAttribute("listCheck", listCheck);
                    request.setAttribute("listCheckIn", listCheckIn);
                    //Select JSP
                    request.getRequestDispatcher("ManagerCustomer.jsp").forward(request, response);
                }
                if (service.equals("billManager")) {
                    //Get data for Order List
                    List<OrderList> listOrder = daoOrder.getAllOrderList("select distinct o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(ord.Quantity*ord.UnitPrice) as Total\n"
                            + "from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                            + "      join Customers cus on o.CustomerID = cus.CustomerID\n"
                            + "	  join [Order Details] ord on ord.OrderID = od.OrderID\n"
                            + "group by o.OrderID,ContactName,o.OrderDate,o.ShipAddress,o.status");
                    List<OrderList> listOrderCanDele = daoOrder.getAllOrderList("select distinct o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(ord.Quantity*ord.UnitPrice) as Total\n"
                            + "from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                            + "join Customers cus on o.CustomerID = cus.CustomerID\n"
                            + "join [Order Details] ord on ord.OrderID = od.OrderID\n"
                            + "where o.status = 1\n"
                            + "group by o.OrderID,ContactName,o.OrderDate,o.ShipAddress,o.status");
                    //Get Status
                    List<Status> listStatus = daoOrder.getStatus("select * from Status");
                    //Set data
                    request.setAttribute("listOrder", listOrder);
                    request.setAttribute("listOrderCanDele", listOrderCanDele);
                    request.setAttribute("listStatus", listStatus);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
                if (service.equals("addCategory")) {
                    request.getRequestDispatcher("CategoryInsert.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("HomeController");
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
