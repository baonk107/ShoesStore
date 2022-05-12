package Controller;

import DAO.DAOOrder;
import Entity.Account;
import Entity.OrderList;
import Entity.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
@WebServlet(name = "BillManagerController", urlPatterns = {"/BillManager"})
public class BillManagerController extends HttpServlet {

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
                DAOOrder daoOrder = new DAOOrder();
                if (service == null) {
                    response.sendRedirect("adminHome?do=billManager");
                }
                if (service.equals("search")) {
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    //abc  trong number
                    String keySearch = request.getParameter("searchKey");
                    int id;
                    try {
                        id = Integer.parseInt(keySearch);
                        //search by id
                        List<OrderList> listOrder = daoOrder.searchByOrderID(id);
                        request.setAttribute("listOrder", listOrder);

                    } catch (Exception e) {
                        //Search by name
                        List<OrderList> listOrder = daoOrder.searchByCusName(keySearch);
                        request.setAttribute("listOrder", listOrder);
                    }

                    List<Status> listStatus = daoOrder.getStatus("select * from Status");
                    request.setAttribute("listStatus", listStatus);
                    //Select JSP
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
                if (service.equals("orderDetail")) {
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

                    request.getRequestDispatcher("adminDetailOrderList.jsp").forward(request, response);
                }
                if (service.equals("updateStatusDetail")) {
                    String orderID = request.getParameter("odId");
                    int odId = Integer.parseInt(orderID);
                    int status = Integer.parseInt(request.getParameter("status"));
                    System.out.println(odId);
                    System.out.println(status);
                    int n = daoOrder.updateStatus(status, odId);
                    response.sendRedirect("BillManager?do=orderDetail&orderID=" + orderID);
                }
                if (service.equals("updateStatusOrder")) {
                    String orderID = request.getParameter("odId");
                    int odId = Integer.parseInt(orderID);
                    int status = Integer.parseInt(request.getParameter("status"));
                    System.out.println(odId);
                    System.out.println(status);
                    int n = daoOrder.updateStatus(status, odId);
                    response.sendRedirect("adminHome?do=billManager");
                }
                if (service.equals("listAllByStatus")) {
                    int statusID = Integer.parseInt(request.getParameter("status"));
                    //Get data for Order List
                    List<OrderList> listOrder;
                    if (statusID == -1) {
                        listOrder = daoOrder.getAllOrderList("select o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(od.Quantity*od.UnitPrice) as Total\n"
                                + "                from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                                + "                join Customers cus on o.CustomerID = cus.CustomerID\n"
                                + "                group by o.OrderID,cus.ContactName,o.OrderDate,o.ShipAddress,o.status");
                    } else {
                        listOrder = daoOrder.getAllOrderListByStatusID(statusID);
                    }
                    List<OrderList> listOrderCanDele = daoOrder.getAllOrderList("select distinct o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(ord.Quantity*ord.UnitPrice) as Total\n"
                            + "from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                            + "join Customers cus on o.CustomerID = cus.CustomerID\n"
                            + "join [Order Details] ord on ord.OrderID = od.OrderID\n"
                            + "where o.status = 1\n"
                            + "group by o.OrderID,ContactName,o.OrderDate,o.ShipAddress,o.status");
                    //Get Status
                    List<Status> listStatus = daoOrder.getStatus("select * from Status");
                    //Set data
                    request.setAttribute("statusID", statusID);
                    request.setAttribute("listOrder", listOrder);
                    request.setAttribute("listStatus", listStatus);
                    request.setAttribute("listOrderCanDele", listOrderCanDele);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
                if (service.equals("delete")) {
                    String orderID = request.getParameter("orderID");
                    DAOOrder dao = new DAOOrder();

                    dao.deleteOrderDetailByOrderID(orderID);
                    dao.deleteOrderByOrderID(orderID);
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
                    String mess = "Delete succesfull";
                    //Get Status
                    List<Status> listStatus = daoOrder.getStatus("select * from Status");
                    //Set data
                    request.setAttribute("mess", mess);
                    request.setAttribute("listOrder", listOrder);
                    request.setAttribute("listOrderCanDele", listOrderCanDele);
                    request.setAttribute("listStatus", listStatus);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
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
