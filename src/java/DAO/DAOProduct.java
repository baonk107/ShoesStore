package DAO;

import Context.DBContext;
import Entity.Customer;
import Entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MT Bac Ninh
 */
//private int ProductID;//auto number
//    private String pName;
//    private int SupplierID, CategoryID;
//    private String QuantityPerUnit;
//    private double UnitPrice;
//    private int UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued;
//    private String imageURL;
public class DAOProduct {

    //Khởi tạo và chạy ResultSet
    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Connection conn = new DBContext().getConnection();
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);//Dùng cho select
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    //Update Product
    public int insertProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued]\n"
                + "           ,[image]\n"
                + "           ,[ProDescription]\n"
                + "           ,[Discount])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getPName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.getDiscontinued());
            pre.setString(10, pro.getImageURL());
            pre.setString(11, pro.getDesPro());
            pre.setDouble(12, pro.getDiscount());

            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Update Product
    public int updateProducts(Product pro) {
        int n = 0;
        String sql = "UPDATE [Products] SET [ProductName] = ?,[SupplierID] = ? ,[CategoryID] = ?,"
                + "[QuantityPerUnit] = ?,[UnitPrice] = ? ,[UnitsInStock] = ? ,"
                + "[UnitsOnOrder] = ?,[ReorderLevel] = ?,[Discontinued] = ?,[image] = ?,"
                + "[ProDescription] = ?,[Discount] = ?\n"
                + " WHERE ProductID = ?";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getPName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.getDiscontinued());
            pre.setString(10, pro.getImageURL());
            pre.setString(11, pro.getDesPro());
            pre.setDouble(12, pro.getDiscount());
            pre.setInt(13, pro.getProductID());

            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Get All Product
    public List<Product> getAllProduct() {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Products";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get Product By Cate Id
    public List<Product> getProductByCateId(int cateID) {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Products where CategoryID = ?";

        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateID);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get Product By Cate Id
    public List<Product> getProductByArrCate(String[] arr) {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Products ";
        if (arr != null) {
            for (String o : arr) {
                if (o.equals(arr[0])) {
                    sql += "where ";
                }
                sql += " CategoryID = " + o;
                if (!o.equals(arr[arr.length - 1])) {
                    sql += " or ";
                }
            }
        }
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
//            pre.setInt(1, cateID);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get List Phân Trang
    public List<Product> getAllProductsWithPaging(int page, int PAGE_SIZE) {
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM\n"
                + "( SELECT     *,\n"
                + "ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq\n"
                + "FROM         Products )t\n"
                + "WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, page);
            pre.setInt(4, PAGE_SIZE);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get List Phân Trang
    public List<Product> getAllProductsPagingByName(String pname, int page, int PAGE_SIZE) {
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM\n"
                + "( SELECT     *, ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq FROM Products \n"
                + "where ProductName like ?\n"
                + ")t WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + pname + "%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get List Phân Trang
    public List<Product> getAllProductsWithPagingByCateID(String[] cate, int page, int PAGE_SIZE) {
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM\n"
                + "(SELECT *, ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq FROM Products ";
        if (cate == null || cate.length == 0) {
            sql += " ) t WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        } else {
            for (String o : cate) {
                if (o.equals(cate[0])) {
                    sql += " where ";
                }
                sql += " CategoryID = " + o;
                if (!o.equals(cate[cate.length - 1])) {
                    sql += " or ";
                }
                if (o.equals(cate[cate.length - 1])) {
                    sql += " ) t WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
                }
            }
        }

        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, page);
            pre.setInt(4, PAGE_SIZE);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get Totals Phân Trang
    public int getTotalProduct() {
        String sql = "select COUNT(*) from Products";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);

            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //Get Totals Phân Trang Search By UserName
    public int getTotalProductByPName(String pname) {
        String sql = "select COUNT(*) from Products where ProductName like ?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + pname + "%");
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //Get Totals Phân Trang By Array Cate
    public int getTotalProductByCate(String[] cate) {
        String sql = "select COUNT(*) from Products ";
        if (cate != null) {
            for (String o : cate) {
                if (o.equals(cate[0])) {
                    sql += " where ";
                }
                sql += " CategoryID = " + o;
                if (!o.equals(cate[cate.length - 1])) {
                    sql += " or ";
                }
            }
        }
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);

            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //Serch By Name
    public List<Product> searchByName(String keySearch) {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Products where ProductName like ?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //List Search By ProductID
    public List<Product> searchProByID(int keySearch) {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Products where ProductID = ?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, keySearch);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get Pro By ID
    public Product getProductByID(String pId) {
        String sql = "select * from Products where ProductID = ?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pId);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                return pro;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Get top 4 Product By Category ID
    public List<Product> getTop4ProductByCateID(int CateID) {
        List<Product> listPro = new ArrayList<>();
        String sql = "select top(4) * from Products where CategoryID = ?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, CateID);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .pName(rs.getString(2))
                        .SupplierID(rs.getInt(3))
                        .CategoryID(rs.getInt(4))
                        .QuantityPerUnit(rs.getString(5))
                        .UnitPrice(rs.getDouble(6))
                        .UnitsInStock(rs.getInt(7))
                        .UnitsOnOrder(rs.getInt(8))
                        .ReorderLevel(rs.getInt(9))
                        .Discontinued(rs.getInt(10))
                        .imageURL(rs.getString(11))
                        .desPro(rs.getString(12))
                        .discount(rs.getDouble(13))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Product Not In Order Detail
    public List<Product> getAllPIDNotInOrderDetail() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products where ProductID not in \n"
                + "(select ProductID from [Order Details])";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .build();
                list.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //Product In Order Detail
    public List<Product> getAllPIDInOrderDetail() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products where ProductID in \n"
                + "(select ProductID from [Order Details])";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .ProductID(rs.getInt(1))
                        .build();
                list.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //Delete Product
    public int deleteProduct(String idRemove) {
        int n = 0;
        String sql = "delete from Products where ProductID = ?";
        //Check foreign key constrain
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, idRemove);
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
//        String[] arr = {"1", "2"};
        List<Product> list = new DAOProduct().getAllPIDNotInOrderDetail();
//        int n = new DAOProduct().getTotalProductByCate(arr);
        for (Product product : list) {
            System.out.println(product);
        }
//        System.out.println(n);

    }
}
