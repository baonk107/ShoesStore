package Controller;

import DAO.DAOCategory;
import DAO.DAOProduct;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DetailController", urlPatterns = {"/DetailController"})
public class DetailController extends HttpServlet {

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
            String service = request.getParameter("do");
            DAOProduct daoPro = new DAOProduct();
            HttpSession session = request.getSession();
            if (service == null) {
                response.sendRedirect("DetailController");
            }
            if (service.equals("detail")) {
                String pId = request.getParameter("pID");
                int CateID = Integer.parseInt(request.getParameter("cateID"));
                Product product = daoPro.getProductByID(pId);//Get Product By ID
                List<Product> listProByCate = daoPro.getTop4ProductByCateID(CateID);//Get Product By Cate ID

                System.out.println(listProByCate);
                request.setAttribute("product", product);
                request.setAttribute("listProByCate", listProByCate);
                //--------
                //12345678
//                String[] arrCate = request.getParameterValues("cateID");
//                if (arrCate == null) {
//                    response.sendRedirect("HomeController");
//                    return;
//                }
                //Phân trang
//                String pageStr = request.getParameter("page");//Get Page

                //List Cate
//                String fill = "";
//                for (String aCate : arrCate) {
//                    fill += "&cateID=" + aCate;
//                }
//                request.setAttribute("page", pageStr);
//                request.setAttribute("fill", fill);
                //-------------------
                session.setAttribute("backToUrl", "DetailController?do=detail&pID=" + product.getProductID() + "&cateID=" + CateID);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
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
