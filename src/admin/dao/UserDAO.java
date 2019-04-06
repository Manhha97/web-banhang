package admin.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import admin.dto.ObjectResponse;
import admin.dto.UserResponse;
import admin.model.Role;
import admin.model.User;
import util.ConnectionUtil;

public class UserDAO {

    public ArrayList<User> search(UserResponse response, int roleId) {
        ArrayList<User> list = new ArrayList<User>();
        User user = response.getUser();
        Integer currentPage = response.getCurrentPage();
        Integer pageSize = response.getPageSize();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM user WHERE 1 = 1";
            HashMap<Integer, Object> query = new HashMap<Integer, Object>();
            Integer i = 0;
            if(user.getCode() != null && !user.getCode().equalsIgnoreCase("")){
                sql += " AND code LIKE ?";
                i++;
                query.put(i, user.getCode().trim());
            }
            if(user.getName() != null && !user.getName().equalsIgnoreCase("")){
                sql += " AND `name` LIKE ?";
                i++;
                query.put(i, user.getName().trim());
            }
            if(user.getBirthday() != null && !user.getBirthday().equalsIgnoreCase("")){
                sql += " AND birthday LIKE ?";
                i++;
                query.put(i, user.getBirthday().trim());
            }
            if(user.getAddress() != null && !user.getAddress().equalsIgnoreCase("")){
                sql += " AND address LIKE ?";
                i++;
                query.put(i, user.getBirthday().trim());
            }
            if(user.getEmail() != null && !user.getEmail().equalsIgnoreCase("")){
                sql += " AND email LIKE ?";
                i++;
                query.put(i, user.getEmail().trim());
            }

            if(user.getRole_id() != 0){
                sql += " AND role_id = ?";
                i++;
                query.put(i, user.getRole_id());
            }
            if(roleId!=3){
                sql += " and role_id != 'BOSS'";
            }
            if(user.getName() != null && !user.getName().equalsIgnoreCase("")){
                sql += " ORDER BY `name` " ;
            }
            else {
                sql += " ORDER BY id desc";
            }
            sql += " LIMIT " + (currentPage - 1)*pageSize + ", " + pageSize;
            PreparedStatement statement = connection.prepareStatement(sql);
            if(i != 0){
                for (Integer key: query.keySet()) {
                    Object value = query.get(key);
                    if(value instanceof Integer){
                        statement.setInt(key, (Integer) value);
                    }else if(value instanceof String){
                        statement.setString(key, "%"+value.toString()+"%");
                    }
                }
            }

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                User p = new User();
                p.setCode(rs.getString("code"));
                p.setName(rs.getString("name"));
                p.setBirthday(rs.getString("birthday"));
                p.setAddress(rs.getString("address"));
                p.setEmail(rs.getString("email"));
                p.setRole_id(rs.getInt("role_id"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Integer getTotal(UserResponse response, int id) {
        User user = response.getUser();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT COUNT(1) FROM user WHERE 1 = 1";
            HashMap<Integer, Object> query = new HashMap<Integer, Object>();
            Integer i = 0;
            if(user.getCode() != null && !user.getCode().equalsIgnoreCase("")){
                sql += " AND code LIKE ?";
                i++;
                query.put(i, user.getCode().trim());
            }
            if(user.getName() != null && !user.getName().equalsIgnoreCase("")){
                sql += " AND `name` LIKE ?";
                i++;
                query.put(i, user.getName().trim());
            }
            if(user.getBirthday() != null && !user.getBirthday().equalsIgnoreCase("")){
                sql += " AND `birthday` LIKE ?";
                i++;
                query.put(i, user.getBirthday().trim());
            }
            if(user.getEmail() != null && !user.getEmail().equalsIgnoreCase("")){
                sql += " AND email LIKE ?";
                i++;
                query.put(i, user.getEmail().trim());
            }
            if(user.getAddress() != null && !user.getAddress().equalsIgnoreCase("")){
                sql += " AND address LIKE ?";
                i++;
                query.put(i, user.getAddress().trim());
            }
            if(user.getRole_id() != 0){
                sql += " AND role_id = ?";
                i++;
                query.put(i, user.getRole_id());
            }
            if(id!=3){
                sql += " and role_id != 'BOSS'";
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            if(i != 0){
                for (Integer key: query.keySet()) {
                    Object value = query.get(key);
                    if(value instanceof Integer){
                        statement.setInt(key, (Integer) value);
                    }else if(value instanceof String){
                        statement.setString(key, "%"+value.toString()+"%");
                    }
                }
            }
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                Integer count = rs.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //	delete 1 user
    public Boolean delete(User user) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "DELETE FROM user WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getCode());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //	Insert a user
    public Boolean insert(User user) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "INSERT INTO user( code,`name`, birthday, address, profile, role_id,email,password) " +
                    "VALUES(?, ?, ?, ?, ?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getCode());
            statement.setString(2, user.getName());
            statement.setString(3, user.getBirthday());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getProfile());
            statement.setInt(6, user.getRole_id());
            statement.setString(7, user.getEmail());
            statement.setString(8, md5(user.getPassword()));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //	Update a user
    public Boolean update(User user) {
        System.out.println(user.toString());
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "UPDATE user SET `name` = ?, birthday = ?, address = ?, profile = ?, role_id = ? WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getBirthday());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getProfile());
            statement.setInt(5, user.getRole_id());
            statement.setString(6, user.getCode());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //
    public User getByCode(User user) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM user WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getCode());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setCode(rs.getString("code"));
                u.setName(rs.getString("name"));
                u.setBirthday(rs.getString("birthday"));
                u.setAddress(rs.getString("address"));
                u.setProfile(rs.getString("profile"));
                u.setRole_id(rs.getInt("role_id"));
                u.setEmail(rs.getString("email"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Role> getAllRole(){
        ArrayList<Role> list = new ArrayList<Role>();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM role";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Role role = new Role(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"));
                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User login(User user) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM user WHERE email = ? AND password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, md5(user.getPassword()));
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setCode(rs.getString("code"));
                u.setName(rs.getString("name"));
                u.setBirthday(rs.getString("birthday"));
                u.setAddress(rs.getString("address"));
                u.setProfile(rs.getString("profile"));
                u.setRole_id(rs.getInt("role_id"));
                u.setEmail(rs.getString("email"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
