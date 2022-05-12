package Controller;

import DAO.DAOProduct;
import Entity.Account;
import Entity.OrderList;
import Entity.Product;
import Entity.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
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
@WebServlet(name = "productManagerController", urlPatterns = {"/productController"})
public class ProductManagerController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("accountAdmin");
            if (acc.getRoll() == 1) {
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                String service = request.getParameter("do");
                DAOProduct daoPro = new DAOProduct();
                if (service == null) {
                    response.sendRedirect("adminHome?do=productManager");
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
                        List<Product> listPro = daoPro.searchProByID(id);
                        request.setAttribute("listPro", listPro);

                    } catch (Exception e) {
                        //Search by name
                        List<Product> listPro = daoPro.searchByName(keySearch);
                        request.setAttribute("listPro", listPro);
                    }

                    //Select JSP
                    request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
                }
                if (service.equals("update")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        //List
                        int pID = Integer.parseInt(request.getParameter("pId"));
                        ResultSet rs = daoPro.getData("select * from Products where ProductID=" + pID);
                        ResultSet rsSup = daoPro.getData("select * from Suppliers");
                        ResultSet rsCate = daoPro.getData("select * from Categories");
                        request.setAttribute("rsProduct", rs);
                        request.setAttribute("rsSupplier", rsSup);
                        request.setAttribute("rsCategories", rsCate);

                        request.getRequestDispatcher("ProductUpdate.jsp").forward(request, response);
                    } else {
                        //Update Get Data and convert
                        int pID = Integer.parseInt(request.getParameter("pId"));
                        String pName = request.getParameter("pName");
                        int supID = Integer.parseInt(request.getParameter("suppId"));
                        int cateID = Integer.parseInt(request.getParameter("cateID"));
                        String qPerUnit = request.getParameter("qPerUnit");
                        double price = Double.parseDouble(request.getParameter("price"));
                        int uInStock = Integer.parseInt(request.getParameter("uInStock"));
                        int uOnOrder = Integer.parseInt(request.getParameter("uOnOrder"));
                        int reOrder = Integer.parseInt(request.getParameter("reOrder"));
                        int discontinute = Integer.parseInt(request.getParameter("discontinute"));
                        String des = request.getParameter("des");
                        double discount = Double.parseDouble(request.getParameter("discount"));
                        String image = request.getParameter("image");

                        //Display 
                        Product pro = Product.builder()
                                .ProductID(pID).pName(pName).SupplierID(supID).CategoryID(cateID).QuantityPerUnit(qPerUnit).UnitPrice(price)
                                .UnitsInStock(uInStock).UnitsOnOrder(uOnOrder).ReorderLevel(reOrder).Discontinued(discontinute).discount(discount).imageURL(image).desPro(des)
                                .build();
                        int update = daoPro.updateProducts(pro);
                        response.sendRedirect("adminHome?do=productManager");
                    }
                }
                if (service.equals("insert")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        ResultSet rsSup = daoPro.getData("select * from Suppliers");
                        ResultSet rsCate = daoPro.getData("select * from Categories");
                        request.setAttribute("rsSupplier", rsSup);
                        request.setAttribute("rsCategories", rsCate);

                        request.getRequestDispatcher("ProductInsert.jsp").forward(request, response);
                    } else {
                        //Get Data Insert
                        String ProductName = request.getParameter("pName");
                        String SupplierID = request.getParameter("suppId");
                        String CategoryID = request.getParameter("cateID");
                        String QuantityPerUnit = request.getParameter("qPerUnit");
                        String UnitPrice = request.getParameter("price");
                        String UnitsInStock = request.getParameter("uInStock");
                        String UnitsOnOrder = request.getParameter("uOnOrder");
                        String ReorderLevel = request.getParameter("reOrder");
                        String Discontinued = request.getParameter("discontinute");
                        String Des = request.getParameter("des");
                        String image = request.getParameter("image");
                        String discountStr = request.getParameter("discount");
                        System.out.println("OK");
                        //Check/ Validate data
                        String mess = "Add Successfull New Product";
                        if (ProductName.length() > 40) {
                            mess = "ProductName must less than 40 character";
                        }
                        if (QuantityPerUnit.length() > 20) {
                            mess = "QuantityPerUnit less than 20 character";
                        }
                        //Convert
                        int suppId = Integer.parseInt(SupplierID);
                        int cateId = Integer.parseInt(CategoryID);
                        double uPrice = Double.parseDouble(UnitPrice);
                        int uInStock = Integer.parseInt(UnitsInStock);
                        int uOnOrder = Integer.parseInt(UnitsOnOrder);
                        int reOrLevel = Integer.parseInt(ReorderLevel);
                        int discon = Integer.parseInt(Discontinued);
                        double discount = Double.parseDouble(discountStr);

                        Product pro = Product.builder()
                                .pName(ProductName).SupplierID(suppId).CategoryID(cateId).QuantityPerUnit(QuantityPerUnit).UnitPrice(uPrice)
                                .UnitsInStock(uInStock).UnitsOnOrder(uOnOrder).ReorderLevel(reOrLevel).Discontinued(discon).discount(discount).imageURL(image).desPro(Des)
                                .build();
                        int insert = daoPro.insertProduct(pro);
                        if (insert > 0) {
                            //Add succesfull
                            response.sendRedirect("adminHome?do=productManager");
                        } else {
                            request.setAttribute("mess", mess);
                            ResultSet rsSup = daoPro.getData("select * from Suppliers");
                            ResultSet rsCate = daoPro.getData("select * from Categories");
                            request.setAttribute("rsSupplier", rsSup);
                            request.setAttribute("rsCategories", rsCate);
                            request.getRequestDispatcher("ProductInsert.jsp").forward(request, response);
                        }
                    }

                }
                if (service.equals("deleteProduct")) {
                    String pID = request.getParameter("pId");
                    int remove = daoPro.deleteProduct(pID);
                    //Select JSP
                    String mess;
                    if (remove > 0) {//Remove Successs
                        mess = "Delete Successfull!";
                        List<Product> listNotIn = daoPro.getAllPIDNotInOrderDetail();
                        List<Product> listIn = daoPro.getAllPIDInOrderDetail();

                        List<Product> listPro = daoPro.getAllProduct();
                        //Set Data for request
                        request.setAttribute("messRemove", mess);
                        request.setAttribute("listNotIn", listNotIn);
                        request.setAttribute("listIn", listIn);
                        request.setAttribute("listPro", listPro);
                        //Select JSP
                        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
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
