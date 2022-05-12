package Controller;

import DAO.DAOCategory;
import DAO.DAOProduct;
import Entity.CartProduct;
import Entity.Category;
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
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

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
            //Declare
            HttpSession session = request.getSession();
            DAOProduct daoPro = new DAOProduct();
            //Get Para
            int unitStock = Integer.parseInt(request.getParameter("ustock"));
            String key = request.getParameter("pID");
            //get pro
            Product product = daoPro.getProductByID(key);
            CartProduct proCart = (CartProduct) session.getAttribute(key);
            //------------------Get size CartProduct
            if (unitStock > 0) {
                String sizeStr;
                try {
                    sizeStr = session.getAttribute("size").toString();
                } catch (Exception e) {
                    sizeStr = null;
                }
                int size;
                if (sizeStr == null) {
                    size = 0;
                } else {
                    size = Integer.parseInt(sizeStr);
                }
                //-----------------Get Data CartProduct
                if (proCart == null) {//TH chưa có sản phẩm
                    proCart = CartProduct.builder()
                            .proId(product.getProductID())
                            .proName(product.getPName())
                            .quantity(1)
                            .price(Math.round(product.getUnitPrice() - (product.getUnitPrice() * product.getDiscount())))
                            .urlImages(product.getImageURL())
                            .build();
                    size++;
                    session.setAttribute(key, proCart);//Lưu vào session
                    session.setAttribute("size", size);
                } else {//TH đã có sản phẩm
                    int count = proCart.getQuantity() + 1;
//                    size++;
//                    session.setAttribute("size", size);
                    proCart.setQuantity(count);
                    session.setAttribute(key, proCart);//Lưu vào session
                }
            }
            //----------------------
            //12345678
            String pageStr = request.getParameter("page");//Get Page

            String[] arrCate = request.getParameterValues("cateID");
            if (arrCate == null) {
                String backToUrl = (String) session.getAttribute("backToUrl");
                if(pageStr != null && backToUrl.contains("do=search")){//Lấy page khi add to cart search
                   
                    response.sendRedirect(backToUrl);
                    return;
                }
                if (pageStr != null) {//Lấy page khi add trang khác 
                    backToUrl = "HomeController?page=" + pageStr;
                    request.getRequestDispatcher(backToUrl).forward(request, response);
                    return;
                }
                response.sendRedirect(backToUrl);
                return;
            }
            //Phân trang

            String fill = "";
            for (String aCate : arrCate) {
                fill += "&cateID=" + aCate;
            }
            //AddToCartController&page=1&cateID=1&cateID=2
            List<Category> listCate = new DAOCategory().getAllCategory();
            request.setAttribute("arrCate", arrCate);
            //------
            String backToUrl = (String) session.getAttribute("backToUrl");
            if (!backToUrl.contains("DetailController")) {
                request.setAttribute("page", pageStr);
            }
            //------
            request.setAttribute("fill", fill);

            request.setAttribute("listCategory", listCate);
            //--------------------------
//            String backToUrl = (String) session.getAttribute("backToUrl");
            if (backToUrl == null) {
                backToUrl = "HomeController?do=fillCategory&page=" + pageStr + fill;
            }
            request.getRequestDispatcher(backToUrl).forward(request, response);
//            response.sendRedirect(backToUrl);
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
