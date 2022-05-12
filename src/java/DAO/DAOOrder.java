package DAO;

import Context.DBContext;
import Entity.Employee;
import Entity.Order;
import Entity.OrderList;
import Entity.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MT Bac Ninh
 */
public class DAOOrder {

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

    //Return Order ID New
    public int returnOrderID(Order order) {
        String sql = "INSERT INTO [Orders]\n"
                + "           ([CustomerID]\n"
                + "           ,[ShipAddress]\n"
                + "           ,[status]\n"
                + "           ,[TotalPrice])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            //Mở connect
            Connection conn = new DBContext().getConnection();
            //Đưa Prepare
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//Return key được thêm
            pre.setString(1, order.getCustomerID());
            pre.setString(2, order.getShipAddress());
            pre.setInt(3, order.getStatus());
            pre.setDouble(4, order.getTotalPrice());
            pre.executeUpdate();
            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //get All Order List
    public List<OrderList> getAllOrderList(String sql) {
        List<OrderList> listOrder = new ArrayList<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String customerName = rs.getString(2);
                String OrderDate = rs.getString(3);
                String Address = rs.getString(4);
                int status = rs.getInt(5);
                double total = rs.getDouble(6);
                OrderList list = OrderList.builder()
                        .OrderID(ordId)
                        .customerName(customerName)
                        .OrderDate(OrderDate)
                        .address(Address)
                        .status(status)
                        .TotalPrice(total)
                        .build();

                listOrder.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }

    //get All Order List By User
    public List<OrderList> getAllOrderListByUser(String user) {
        String sql = "select o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(od.Quantity*od.UnitPrice) as Total\n"
                + "                from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                + "                join Customers cus on o.CustomerID = cus.CustomerID\n"
                + "                where cus.UserName = ?\n"
                + "                group by o.OrderID,cus.ContactName,o.OrderDate,o.ShipAddress,o.status";
        List<OrderList> listOrder = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String customerName = rs.getString(2);
                String OrderDate = rs.getString(3);
                String Address = rs.getString(4);
                int status = rs.getInt(5);
                double total = rs.getDouble(6);
                OrderList list = OrderList.builder()
                        .OrderID(ordId)
                        .customerName(customerName)
                        .OrderDate(OrderDate)
                        .address(Address)
                        .status(status)
                        .TotalPrice(total)
                        .build();

                listOrder.add(list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }

    //get All Order List By Status ID
    public List<OrderList> getAllOrderListByStatusID(int statusID) {
        List<OrderList> listOrder = new ArrayList<>();
        String sql = "select o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(od.Quantity*od.UnitPrice) as Total\n"
                + "                from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                + "                join Customers cus on o.CustomerID = cus.CustomerID\n"
                + "                group by o.OrderID,cus.ContactName,o.OrderDate,o.ShipAddress,o.status\n"
                + "                having o.status = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, statusID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String customerName = rs.getString(2);
                String OrderDate = rs.getString(3);
                String Address = rs.getString(4);
                int status = rs.getInt(5);
                double total = rs.getDouble(6);
                OrderList list = OrderList.builder()
                        .OrderID(ordId)
                        .customerName(customerName)
                        .OrderDate(OrderDate)
                        .address(Address)
                        .status(status)
                        .TotalPrice(total)
                        .build();

                listOrder.add(list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }

    //get All Order List By Status ID And User Name
    public List<OrderList> getAllOrderListByStatusIDAndUser(String user, int statusID) {
        List<OrderList> listOrder = new ArrayList<>();
        String sql = "select o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(od.Quantity*od.UnitPrice) as Total\n"
                + "                from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                + "                join Customers cus on o.CustomerID = cus.CustomerID\n"
                + "                where o.status = ? and cus.UserName = ?\n"
                + "                group by o.OrderID,cus.ContactName,o.OrderDate,o.ShipAddress,o.status";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, statusID);
            pre.setString(2, user);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String customerName = rs.getString(2);
                String OrderDate = rs.getString(3);
                String Address = rs.getString(4);
                int status = rs.getInt(5);
                double total = rs.getDouble(6);
                OrderList list = OrderList.builder()
                        .OrderID(ordId)
                        .customerName(customerName)
                        .OrderDate(OrderDate)
                        .address(Address)
                        .status(status)
                        .TotalPrice(total)
                        .build();

                listOrder.add(list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }

    //get All Order List by OrderID
    public List<OrderList> getAllOrderListByID(int orderID) {
        List<OrderList> listOrder = new ArrayList<>();
        String sql = "select o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(od.Quantity*od.UnitPrice) as Total\n"
                + "                from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                + "                join Customers cus on o.CustomerID = cus.CustomerID\n"
                + "                where od.OrderID = ?\n"
                + "                group by o.OrderID,cus.ContactName,o.OrderDate,o.ShipAddress,o.status";
        try {
            //Mở connect
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String customerName = rs.getString(2);
                String OrderDate = rs.getString(3);
                String Address = rs.getString(4);
                int status = rs.getInt(5);
                double total = rs.getDouble(6);
                OrderList list = OrderList.builder()
                        .OrderID(ordId)
                        .customerName(customerName)
                        .OrderDate(OrderDate)
                        .address(Address)
                        .status(status)
                        .TotalPrice(total)
                        .build();

                listOrder.add(list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }

    //Search list Order By OrderID or CustomerName
    public List<OrderList> searchByOrderID(int odId) {
        List<OrderList> listOrder = new ArrayList<>();
        try {
            String sql = "select distinct o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(ord.Quantity*ord.UnitPrice) as Total\n"
                    + "from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                    + "      join Customers cus on o.CustomerID = cus.CustomerID\n"
                    + "       join [Order Details] ord on ord.OrderID = od.OrderID\n"
                    + "       group by o.OrderID,ContactName,o.OrderDate,o.ShipAddress,o.status\n"
                    + "       having o.OrderID = ?";

            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, odId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String customerName = rs.getString(2);
                String OrderDate = rs.getString(3);
                String Address = rs.getString(4);
                int status = rs.getInt(5);
                double total = rs.getDouble(6);
                OrderList list = OrderList.builder()
                        .OrderID(ordId)
                        .customerName(customerName)
                        .OrderDate(OrderDate)
                        .address(Address)
                        .status(status)
                        .TotalPrice(total)
                        .build();

                listOrder.add(list);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }

    //Search list Order By OrderID or CustomerName
    public List<OrderList> searchByCusName(String cusName) {
        List<OrderList> listOrder = new ArrayList<>();
        try {
            String sql = "select distinct o.OrderID, cus.ContactName,o.OrderDate,o.ShipAddress,o.status,SUM(ord.Quantity*ord.UnitPrice) as Total\n"
                    + "from [Order Details] od join Orders o on o.OrderID = od.OrderID\n"
                    + "      join Customers cus on o.CustomerID = cus.CustomerID\n"
                    + "       join [Order Details] ord on ord.OrderID = od.OrderID\n"
                    + "       group by o.OrderID,ContactName,o.OrderDate,o.ShipAddress,o.status\n"
                    + "       having cus.ContactName like ?";

            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + cusName + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String customerName = rs.getString(2);
                String OrderDate = rs.getString(3);
                String Address = rs.getString(4);
                int status = rs.getInt(5);
                double total = rs.getDouble(6);
                OrderList list = OrderList.builder()
                        .OrderID(ordId)
                        .customerName(customerName)
                        .OrderDate(OrderDate)
                        .address(Address)
                        .status(status)
                        .TotalPrice(total)
                        .build();

                listOrder.add(list);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }

    public static void main(String[] args) {
        List<OrderList> list = new DAOOrder().getAllOrderListByUser("user1");
        for (OrderList orderList : list) {
            System.out.println(orderList);
        }
    }

    //Get Status
    public List<Status> getStatus(String sql) {
        List<Status> listStatus = new ArrayList<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int statusId = rs.getInt(1);
                String statusName = rs.getString(2);
                Status status = Status.builder()
                        .id(statusId)
                        .statusName(statusName)
                        .build();
                listStatus.add(status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listStatus;
    }

    //Update Status
    //Update
    public int updateStatus(int status, int odId) {
        int n = 0;
        String sql = "update Orders set status = ? where OrderID =?";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, odId);

            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public void deleteOrderByOrderID(String orderID) {
        String sql = "delete from [Order Details] where OrderID = ?";
        //Check foreign key constrain
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, orderID);
            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteOrderDetailByOrderID(String orderID) {
        String sql = "delete from Orders where OrderID = ?";
        //Check foreign key constrain
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, orderID);
            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
