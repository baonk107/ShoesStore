package Controller;

import DAO.DAOCustomer;
import Entity.Account;
import Entity.Customer;
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
@WebServlet(name = "CustomerManagerController", urlPatterns = {"/CustomerManager"})
public class CustomerManagerController extends HttpServlet {

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
                DAOCustomer daoCus = new DAOCustomer();
                if (service == null) {
                    response.sendRedirect("adminHome?do=customerManager");
                }
                if (service.equals("search")) {
                    List<Customer> listCheck = daoCus.getAllCusIDNotInOrder();
                    List<Customer> listCheckIn = daoCus.getAllCusIDInOrder();
                    
                    //Set Data for request
                    request.setAttribute("listCheck", listCheck);
                    request.setAttribute("listCheckIn", listCheckIn);
                    //Search
                    String searchKey = request.getParameter("searchKey");
                    List<Customer> listCus = daoCus.searchCusByName(searchKey);
                    //Set Data for request
                    request.setAttribute("listCus", listCus);
                    //Select JSP
                    request.getRequestDispatcher("ManagerCustomer.jsp").forward(request, response);
                }
                if (service.equals("update")) {
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String cusID = request.getParameter("cusID");
                        ResultSet rs = daoCus.getData("select * from Customers where CustomerID = '" + cusID + "'");

                        //Set Data for request
                        request.setAttribute("rsCustomer", rs);
                        request.getRequestDispatcher("CustomerUpdate.jsp").forward(request, response);
                    } else {
                        //Get Data and Update
                        String cID = request.getParameter("cusID");
                        String cpName = request.getParameter("cpName");
                        String ctName = request.getParameter("ctName");
                        String ctTitle = request.getParameter("contactTitle");
                        String address = request.getParameter("address");
                        String city = request.getParameter("city");
                        String region = request.getParameter("region");
                        String pscode = request.getParameter("pscode");
                        String country = request.getParameter("country");
                        String phone = request.getParameter("phone");
                        String fax = request.getParameter("fax");
                        String user = request.getParameter("user");
                        String pass = request.getParameter("pass");

                        Customer cus = Customer.builder()
                                .CustomerID(cID).CompanyName(cpName).ContactName(ctName).ContactTitle(ctTitle).Address(address)
                                .City(city).Region(region).PostalCode(pscode).Country(country).Phone(phone).Fax(fax).userName(user)
                                .passWord(pass).roll(0)
                                .build();

                        int updateCus = daoCus.updateCustomers(cus);
                        response.sendRedirect("adminHome?do=customerManager");
                    }
                }
                if (service.equals("insert")) {
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        request.getRequestDispatcher("CustomerInsert.jsp").forward(request, response);
                    } else {
                        //Get Data
                        String cpName = request.getParameter("cpName");
                        String ctName = request.getParameter("ctName");
                        String ctTitle = request.getParameter("contactTitle");
                        String address = request.getParameter("address");
                        String city = request.getParameter("city");
                        String region = request.getParameter("region");
                        String pscode = request.getParameter("pscode");
                        String country = request.getParameter("country");
                        String phone = request.getParameter("phone");
                        String fax = request.getParameter("fax");
                        String user = request.getParameter("user");
                        String pass = request.getParameter("pass");
                        String mess = "Insert Successfull";
                        //Check data
                        if (cpName.length() > 40) {
                            mess = "CompanyName must less than 40 character";
                        }
                        if (ctName.length() > 30) {
                            mess = "ContactName must less than 30 character";
                        }
                        if (ctTitle.length() > 30) {
                            mess = "ContactTitle must less than 30 character";
                        }
                        if (address.length() > 60) {
                            mess = "Address must less than 60 character";
                        }
                        if (city.length() > 15) {
                            mess = "City must less than 15 character";
                        }
                        if (region.length() > 15) {
                            mess = "Region must less than 15 character";
                        }
                        if (pscode.length() > 10) {
                            mess = "PostalCode must less than 10 character";
                        }
                        if (country.length() > 15) {
                            mess = "Country must less than 15 character";
                        }
                        if (phone.length() > 24) {
                            mess = "Phone must less than 24 character";
                        }
                        if (fax.length() > 24) {
                            mess = "Fax must less than 24 character";
                        }
                        //Get CustomerID random
                        String cusID = daoCus.randCusID(5).toUpperCase();
                        boolean checkID = daoCus.checkDuplicateCustomerId(cusID);
                        if (checkID == false) {//Not duplicate
                            boolean checkUser = daoCus.checkDuplicateUsername(user);
                            if (checkUser == false) {//Not duplicate
                                Customer cus = Customer.builder()
                                        .CustomerID(cusID).CompanyName(cpName).ContactName(ctName).ContactTitle(ctTitle).Address(address)
                                        .City(city).Region(region).PostalCode(pscode).Country(country).Phone(phone).Fax(fax).userName(user)
                                        .passWord(pass).roll(0)
                                        .build();
                                System.out.println(cus);
                                int n = daoCus.addCustomers(cus);
                                System.out.println(n);
                                if (n > 0) {
                                    System.out.println("Insert Thanh Cong");
                                    response.sendRedirect("adminHome?do=customerManager");
                                }
                            } else {//Duplicate User
                                mess = "UserName has exits";
                                request.setAttribute("mess", mess);
                                request.getRequestDispatcher("CustomerInsert.jsp").forward(request, response);
                            }
                        }
                    }
                }
                if (service.equals("delete")) {
                    String cusID = request.getParameter("cusID");
                    int removeCus = daoCus.deleCustomer(cusID);
                    //Select JSP
                    String mess;
                    if (removeCus > 0) {//Remove Successs
                        mess = "Delete Successfull!";
                        List<Customer> listCheck = daoCus.getAllCusIDNotInOrder();
                        List<Customer> listCheckIn = daoCus.getAllCusIDInOrder();

                        List<Customer> listCus = daoCus.listAllCustommer();
                        //Set Data for request
                        request.setAttribute("mess", mess);
                        request.setAttribute("listCus", listCus);
                        request.setAttribute("listCheck", listCheck);
                        request.setAttribute("listCheckIn", listCheckIn);
                        //Select JSP
                        request.getRequestDispatcher("ManagerCustomer.jsp").forward(request, response);
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
