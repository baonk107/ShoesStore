package DAO;

import Context.DBContext;
import Entity.CartProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author MT Bac Ninh
 */
public class DAOOrderDetail {

    public int insertOrderDetail(int orderID, List<CartProduct> listProductCarts) {
        int n = 0;
        String sql = "INSERT INTO [Order Details]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Quantity]\n"
                + "           ,[Image])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            //Mở connect
            Connection conn = new DBContext().getConnection();
            //Đưa Prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderID);
            for (CartProduct cart : listProductCarts) {
                pre.setInt(2, cart.getProId());
                pre.setDouble(3, cart.getPrice());
                pre.setInt(4, cart.getQuantity());
                pre.setString(5, cart.getUrlImages());
                n = pre.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

}
