package DAO;

import Context.DBContext;
import Entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MT Bac Ninh
 */
public class DAOCategory {

    public List<Category> getAllCategory() {
        List<Category> listCate = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            //Mở kết nối
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            //ResultSet get Data
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Category cate = Category.builder()
                        .cateID(rs.getInt(1))
                        .cateName(rs.getString(2))
                        .des(rs.getString(3))
                        .build();
                listCate.add(cate);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listCate;
    }

    public int addCategories(Category cate) {
        int n = 0;
        String sql = "insert into Categories(CategoryName, Description)"
                + " values(?,?)";
        try {
            //Mở kết nối
            Connection conn = new DBContext().getConnection();
            //Create Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCateName());
            pre.setString(2, cate.getDes());
            //run
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

}
