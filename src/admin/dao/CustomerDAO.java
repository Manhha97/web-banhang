package admin.dao;

import admin.model.Customer;
import util.ConnectionUtil;
import util.ConvertUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public Customer checkLogin(Customer customer){
        Customer customer1 = null;
        String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getPassword());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                customer1 = new Customer();
                customer1.setId(rs.getInt("id"));
                customer1.setName(rs.getString("name"));
                customer1.setEmail(rs.getString("email"));
                customer1.setPhone(rs.getString("phone"));
                customer1.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer1;
    }
    public Boolean edit(Customer customer){
        String sql = "update customer set `name`=?, password=? where email = ?";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getEmail());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Customer getCustomer(Customer customer){
        Customer customer1 = null;
        String sql = "SELECT * FROM customer WHERE email = ?";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getEmail());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                customer1 = new Customer();
                customer1.setId(rs.getInt("id"));
                customer1.setName(rs.getString("name"));
                customer1.setEmail(rs.getString("email"));
                customer1.setPhone(rs.getString("phone"));
                customer1.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer1;
    }
    public Boolean register(Customer customer){
        String sql="insert into customer(`name`,email,phone,password) values (?,?,?,?)";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getEmail());
            statement.setString(3,customer.getPhone());
            statement.setString(4,customer.getPassword());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
