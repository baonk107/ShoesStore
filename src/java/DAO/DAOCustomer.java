package DAO;

import Context.DBContext;
import Entity.Account;
import Entity.Customer;
import Entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MT Bac Ninh
 */
public class DAOCustomer {

    //Result Set
    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Connection conn = new DBContext().getConnection();
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);//Dùng cho select
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //Check login
    public Account checkLogin(String userName, String password) {
        String sql = "select * from Customers where username=? and password=?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, password);

            //Thực thi và trả về kết quả
            ResultSet rs = pre.executeQuery();
            //Run lặp lấy dữ liệu
            while (rs.next()) {
                Account loginCus = Account.builder()
                        .userName(rs.getString(12))
                        .passWord(rs.getString(13))
                        .roll(rs.getInt(14))
                        .build();
                return loginCus;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    //Get CustomerID By UserName
    public Customer getCusIDByUserName(String userName) {
        String sql = "select CustomerID from Customers where UserName =?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, userName);

            //Thực thi và trả về kết quả
            ResultSet rs = pre.executeQuery();
            //Run lặp lấy dữ liệu
            while (rs.next()) {
                Customer cus = Customer.builder()
                        .CustomerID(rs.getString(1))
                        .build();
                return cus;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    //Get All Account
    public List<Account> getAllAccount() {
        List<Account> listAccount = new ArrayList<>();
        List<Customer> listCustomers = listAllCustommer();
        List<Employee> listEmployee = new DAOEmployee().listAllEmployee();
        for (Employee emp : listEmployee) {
            Account lisyacc = Account.builder()
                    .userName(emp.getUserName())
                    .passWord(emp.getPassWord())
                    .roll(emp.getRoll())
                    .build();
            listAccount.add(lisyacc);
        }
        for (Customer cus : listCustomers) {
            Account listacc = Account.builder()
                    .customerID(cus.getCustomerID())
                    .userName(cus.getUserName())
                    .passWord(cus.getPassWord())
                    .roll(cus.getRoll())
                    .build();
            listAccount.add(listacc);
        }
        return listAccount;
    }

    //Insert
    public int addCustomers(Customer cus) {
        int n = 0;
        String sql = "INSERT INTO [Customers]\n"
                + "           ([CustomerID]\n"
                + "           ,[CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax]\n"
                + "           ,[UserName]\n"
                + "           ,[PassWord]\n"
                + "           ,[roll])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //Create Statement
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCompanyName());
            pre.setString(3, cus.getContactName());
            pre.setString(4, cus.getContactTitle());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getRegion());
            pre.setString(8, cus.getPostalCode());
            pre.setString(9, cus.getCountry());
            pre.setString(10, cus.getPhone());
            pre.setString(11, cus.getFax());
            pre.setString(12, cus.getUserName());
            pre.setString(13, cus.getPassWord());
            pre.setInt(14, cus.getRoll());

            //run
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //true checkDuplicateCustomerId
    public boolean checkDuplicateCustomerId(String customerId) {
        List<Customer> listCustomers = listAllCustommer();
        for (Customer cus : listCustomers) {
            if (cus.getCustomerID().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    //Check equal USERNAME
    public boolean checkDuplicateUsername(String username) {
        List<Account> listAccount = getAllAccount();
        for (Account acc : listAccount) {
            if (acc.getUserName() != null) {
                if (acc.getUserName().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    //random customer Id
    public String randCusID(int length) {
        String asciiUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String asciiLowerCase = asciiUpperCase.toLowerCase();
        String asciiChars = asciiUpperCase + asciiLowerCase;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Random rand = new Random();
        while (i < length) {
            sb.append(asciiChars.charAt(rand.nextInt(asciiChars.length())));
            i++;
        }
        return sb.toString();
    }

    //Check insert True or False
    public boolean checkRegister(String user, String pass) {
        Customer cus = Customer.builder()
                .userName(user)
                .passWord(pass).build();
        while (true) {
            String random = randCusID(5).toUpperCase();
            cus.setCustomerID(random);
            if (!checkDuplicateCustomerId(random)) {
                if (!checkDuplicateUsername(cus.getUserName())) {
                    addCustomers(cus);//Gọi hàm add
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    //Update
    public int updateCustomers(Customer cus) {
        int n = 0;
        String sql = "update Customers set CompanyName=?,ContactName=?,"
                + "ContactTitle=?,Address=?,City=?,Region=?,PostalCode=?,Country=?, "
                + "Phone=?, Fax=? , UserName = ?, PassWord=?, roll=? where CustomerID=?";

        //Create Statement
        Connection conn;
        try {
            conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCompanyName());
            pre.setString(2, cus.getContactName());
            pre.setString(3, cus.getContactTitle());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getCity());
            pre.setString(6, cus.getRegion());
            pre.setString(7, cus.getPostalCode());
            pre.setString(8, cus.getCountry());
            pre.setString(9, cus.getPhone());
            pre.setString(10, cus.getFax());
            pre.setString(11, cus.getUserName());
            pre.setString(12, cus.getPassWord());
            pre.setInt(13, cus.getRoll());
            pre.setString(14, cus.getCustomerID());
            //run
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

//    public List<Product> getListProductCanDelete() {
//        List<Product> listProducts = new ArrayList<>();
//        String sql = "select * from Products where ProductID not in(select ProductID from [Order Details])";
//        try {
//            ps = connection.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while(rs.next()) {
//                Product p = Product.builder()
//                        .productId(rs.getInt(1))
//                        .productName(rs.getString(2))
//                        .supplierId(rs.getInt(3))
//                        .categoryId(rs.getInt(4))
//                        .quantityPerUnit(rs.getString(5))
//                        .unitPrice(rs.getDouble(6))
//                        .unitsInStock(rs.getInt(7))
//                        .unitsOnOrder(rs.getInt(8))
//                        .reOrderLevel(rs.getInt(9))
//                        .discontinued(rs.getInt(10))
//                        .image(rs.getString(11))
//                        .description(rs.getString(12))
//                        .title(rs.getString(13))
//                        .discount(rs.getDouble(14)).build();
//                listProducts.add(p);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return listProducts;
//    }
//    public boolean deleteProduct(String id) {
//        List<Product> listProducts = getListProductCanDelete();
//        int productId = Integer.parseInt(id);
//        boolean isRemove = false;
//        for (Product p : listProducts) {
//            if (p.getProductId() == productId) {
//                isRemove = true;
//                break;
//            }
//        }
//        if(isRemove == true) {
//            String sql = "delete from Products where ProductID = ?";
//            try {
//                ps = connection.prepareStatement(sql);
//                ps.setInt(1, productId);
//                ps.executeUpdate();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            return true;
//        }
//        else return false;
//    }
    //GetAll CustomerID not in Order Check foreign key Customer
    public List<Customer> getAllCusIDNotInOrder() {
        List<Customer> list = new ArrayList<>();
        String sql = "select CustomerID, ContactName from Customers where CustomerID "
                + "not in(select CustomerID from Orders)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Customer cus = Customer.builder()
                        .CustomerID(rs.getString(1))
                        .ContactName(rs.getString(2))
                        .build();
                list.add(cus);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //GetAll CustomerID not in Order Check foreign key Customer
    public List<Customer> getAllCusIDInOrder() {
        List<Customer> list = new ArrayList<>();
        String sql = "select CustomerID, ContactName from Customers where CustomerID "
                + "in(select CustomerID from Orders)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Customer cus = Customer.builder()
                        .CustomerID(rs.getString(1))
                        .ContactName(rs.getString(2))
                        .build();
                list.add(cus);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //Delete
    public int deleCustomer(String idRemove) {
        int n = 0;
        String sql = "delete from Customers where CustomerID = ?";
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

    //Get List Customer By ContactName
    public List<Customer> searchCusByName(String ctName) {
        List<Customer> list = new ArrayList<>();
        String sql = "select * from Customers where ContactName like ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + ctName + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String cusId = rs.getString("CustomerID");
                String companyName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String region = rs.getString(7);
                String postalCode = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String fax = rs.getString(11);
                String userName = rs.getString(12);
                String pass = rs.getString(13);
                int roll = rs.getInt(14);
                Customer cus = Customer.builder()
                        .CustomerID(cusId)
                        .CompanyName(companyName)
                        .ContactName(contactName)
                        .ContactTitle(contactTitle)
                        .Address(address)
                        .City(city)
                        .Region(region)
                        .PostalCode(postalCode)
                        .Country(country)
                        .Phone(phone)
                        .Fax(fax)
                        .userName(userName)
                        .passWord(pass)
                        .roll(roll)
                        .build();
                list.add(cus);
                //System.out.println(cus);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //Select All
    public List<Customer> listAllCustommer() {
        String sql = "select * from Customers";
        List<Customer> list = new ArrayList<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String cusId = rs.getString("CustomerID");
                String companyName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String region = rs.getString(7);
                String postalCode = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String fax = rs.getString(11);
                String userName = rs.getString(12);
                String pass = rs.getString(13);
                int roll = rs.getInt(14);
                Customer cus = Customer.builder()
                        .CustomerID(cusId)
                        .CompanyName(companyName)
                        .ContactName(contactName)
                        .ContactTitle(contactTitle)
                        .Address(address)
                        .City(city)
                        .Region(region)
                        .PostalCode(postalCode)
                        .Country(country)
                        .Phone(phone)
                        .Fax(fax)
                        .userName(userName)
                        .passWord(pass)
                        .roll(roll)
                        .build();
                list.add(cus);
                //System.out.println(cus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //Change PassWord
    public void updatePassWordByUser(String newPass, String userName) {
        String sql = "update Customers set PassWord = ? where UserName = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, newPass);
            pre.setString(2, userName);
            //run
            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        List<Customer> cus = dao.getAllCusIDNotInOrder();
        for (Customer cu : cus) {
            System.out.println(cu);
        }
    }
}
