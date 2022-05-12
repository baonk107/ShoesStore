package DAO;

import Context.DBContext;
import Entity.Account;
import Entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author MT Bac Ninh
 */
public class DAOEmployee {

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
    //Add Emp
    public int addEmployees(Employee emp) {
        int n = 0;
        String sql = "insert into Employees(LastName, FirstName, Title, TitleOfCourtesy"
                + ", BirthDate, HireDate, Address, City, Region, "
                + "PostalCode, Country,HomePhone, Extension, Notes, "
                + "ReportsTo, PhotoPath)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            //Create Prepare Statement
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setString(5, emp.getBirthDate());
            pre.setString(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setString(14, emp.getNotes());
            pre.setInt(15, emp.getReportsTo());
            pre.setString(16, emp.getPhotoPath());
            //run
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Update
    public int updateEmployees(Employee emp) {
        int n = 0;
        String sql = "update Employees set LastName=?, FirstName=?, Title=?, TitleOfCourtesy=?"
                + ", BirthDate=?, HireDate=?, Address=?, City=?, Region=?, "
                + "PostalCode=?, Country=?,HomePhone=?, Extension=?, Notes=?, "
                + "ReportsTo=?, PhotoPath=? where EmployeeID=?";

        try {
            Connection conn = new DBContext().getConnection();
            //Create Prepare Statement
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setString(5, emp.getBirthDate());
            pre.setString(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setString(14, emp.getNotes());
            pre.setInt(15, emp.getReportsTo());
            pre.setString(16, emp.getPhotoPath());
            pre.setInt(17, emp.getEmployeeID());
            //run
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Delete
    public int deleEmployee(int id) {
        int n = 0;
        String sql = "delete from Employees where EmployeeID=" + id;

        try {
            Connection conn = new DBContext().getConnection();
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Select list all
    public List<Employee> listAllEmployee() {
        String sql = "select * from Employees";
        List<Employee> listEmp = new ArrayList<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int empID = rs.getInt("EmployeeID");
                String lName = rs.getString(2);
                String fName = rs.getString(3);
                String title = rs.getString(4);
                String TOC = rs.getString(5);
                String birthDate = rs.getString(6);
                String hireDate = rs.getString(7);
                String address = rs.getString(8);
                String city = rs.getString(9);
                String region = rs.getString(10);
                String postalCode = rs.getString(11);
                String country = rs.getString(12);
                String homePhone = rs.getString(13);
                String extension = rs.getString(14);
                String photo = rs.getString(15);
                String notes = rs.getString(16);
                int reportsTo = rs.getInt(17);
                String photoPath = rs.getString(18);
                String userName = rs.getString(19);
                String passWord = rs.getString(20);
                int roll = rs.getInt(21);

                Employee emp = Employee.builder()
                        .EmployeeID(empID).LastName(lName).FirstName(fName).Title(title)
                        .TitleOfCourtesy(TOC).BirthDate(birthDate).HireDate(hireDate).Address(address)
                        .City(city).Region(region).PostalCode(postalCode).Country(country)
                        .HomePhone(homePhone).Extension(extension).Photo(photo)
                        .Notes(notes).ReportsTo(reportsTo).PhotoPath(photoPath).userName(userName).passWord(passWord)
                        .roll(roll)
                        .build();
                listEmp.add(emp);
                //System.out.println(emp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listEmp;
    }
}
