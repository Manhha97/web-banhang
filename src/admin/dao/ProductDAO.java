package admin.dao;

import admin.dto.ObjectResponse;
import admin.dto.ProductDetail;
import admin.model.Author;
import admin.model.NXB;
import admin.model.Product;
import admin.model.Type;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductDAO {
    public ArrayList<Product> search(ObjectResponse response) {
        ArrayList<Product> list = new ArrayList<Product>();
        Product product = response.getProduct();
        Integer currentPage = response.getCurrentPage();
        Integer pageSize = response.getPageSize();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM products WHERE 1 = 1";
            HashMap<Integer, Object> query = new HashMap<Integer, Object>();
            Integer i = 0;
            if(product.getCode() != null && !product.getCode().equalsIgnoreCase("")){
                sql += " AND code LIKE ?";
                i++;
                query.put(i, product.getCode().trim());
            }
            if(product.getName() != null && !product.getName().equalsIgnoreCase("")){
                sql += " AND `name` LIKE ?";
                i++;
                query.put(i, product.getName().trim());
            }
            if(product.getSale() != null && product.getSale() != 0){
                sql += " AND sale != 0";
            }
            if(product.getTypeId() != null && product.getTypeId() != 0){
                sql += " AND type_id = ?";
                i++;
                query.put(i, product.getTypeId());
            }
            if(product.getAuthorId() != null && product.getAuthorId() != 0){
                sql += " AND author_id = ?";
                i++;
                query.put(i, product.getAuthorId());
            }
            if(product.getNxbId() != null && product.getNxbId() != 0){
                sql += " AND nxb_id = ?";
                i++;
                query.put(i, product.getNxbId());
            }
            if(product.getNameSort() != null && !product.getNameSort().equalsIgnoreCase("")){
                sql += " ORDER BY `name` " + product.getNameSort();
            }
            else if(product.getPriceSort() != null && !product.getPriceSort().equalsIgnoreCase("")){
                sql += " ORDER BY price " + product.getPriceSort();
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
                Product p = new Product();
                p.setCode(rs.getString("code"));
                p.setName(rs.getString("name"));
                p.setSale(rs.getInt("sale"));
                p.setImage(rs.getString("image"));
                p.setAuthorId(rs.getInt("author_id"));
                p.setNxbId(rs.getInt("nxb_id"));
                p.setTypeId(rs.getInt("type_id"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Integer getTotal(ObjectResponse response) {
        Product product = response.getProduct();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT COUNT(1) FROM products WHERE 1 = 1";
            HashMap<Integer, Object> query = new HashMap<Integer, Object>();
            Integer i = 0;
            if(product.getCode() != null && !product.getCode().equalsIgnoreCase("")){
                sql += " AND code LIKE ?";
                i++;
                query.put(i, product.getCode().trim());
            }
            if(product.getName() != null && !product.getName().equalsIgnoreCase("")){
                sql += " AND `name` LIKE ?";
                i++;
                query.put(i, product.getName().trim());
            }
            if(product.getTypeId() != null && product.getTypeId() != 0){
                sql += " AND type_id = ?";
                i++;
                query.put(i, product.getTypeId());
            }
            if(product.getAuthorId() != null && product.getAuthorId() != 0){
                sql += " AND author_id = ?";
                i++;
                query.put(i, product.getAuthorId());
            }
            if(product.getNxbId() != null && product.getNxbId() != 0){
                sql += " AND nxb_id = ?";
                i++;
                query.put(i, product.getNxbId());
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

    public Product getByCode(Product product) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM products WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getCode());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Product p = new Product();
                p.setCode(rs.getString("code"));
                p.setName(rs.getString("name"));
                p.setImage(rs.getString("image"));
                p.setAuthorId(rs.getInt("author_id"));
                p.setNxbId(rs.getInt("nxb_id"));
                p.setTypeId(rs.getInt("type_id"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setSale(rs.getInt("sale"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ProductDetail detail(Product product) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT p.*,n.`name` nname, a.`name` aname, t.`name` tname FROM products p, nxb n, author a, type t WHERE p.author_id = a.id AND p.nxb_id = n.id AND p.type_id = t.id AND p.`code` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getCode());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                ProductDetail p = new ProductDetail();
                p.setId(rs.getInt("id"));
                p.setCode(rs.getString("code"));
                p.setName(rs.getString("name"));
                p.setImage(rs.getString("image"));
                p.setAuthor(rs.getString("aname"));
                p.setNxb(rs.getString("nname"));
                p.setType(rs.getString("tname"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setSale(rs.getInt("sale"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean insert(Product product) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "INSERT INTO products(code, `name`, quantity, price, sale, image, type_id, nxb_id, author_id, create_at) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getCode());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getQuantity());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getSale());
            statement.setString(6, product.getImage());
            statement.setInt(7, product.getTypeId());
            statement.setInt(8, product.getNxbId());
            statement.setInt(9, product.getAuthorId());
            statement.setDate(10, product.getCreateAt());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(Product product) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "UPDATE products SET `name` = ?, quantity = ?, price = ?, sale = ?, type_id = ?, nxb_id = ?," +
                    " image = ?, author_id = ? WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getSale());
            statement.setInt(5, product.getTypeId());
            statement.setInt(6, product.getNxbId());
            statement.setString(7, product.getImage());
            statement.setInt(8, product.getAuthorId());
            statement.setString(9, product.getCode());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(Product product) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "DELETE FROM products WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getCode());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Product> getProductNew(Date date) {
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT p.id, p.code, p.name, p.image, p.author_id, p.price, p.sale FROM products p WHERE TIMESTAMPDIFF(DAY, ?, p.create_at) >= 0 and TIMESTAMPDIFF(DAY, p.create_at, NOW()) BETWEEN 0 AND 3 ORDER BY id desc";

            sql += " LIMIT 0,10";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setCode(rs.getString("code"));
                p.setName(rs.getString("name"));
                p.setSale(rs.getInt("sale"));
                p.setImage(rs.getString("image"));
                p.setAuthorId(rs.getInt("author_id"));
//                p.setNxbId(rs.getInt("nxb_id"));
//                p.setTypeId(rs.getInt("type_id"));
//                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
//    -----------------------------------------------------------------------------------------------
//    --------------------------------------- else --------------------------------------------------
    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<Author>();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM author";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Author author = new Author(resultSet.getInt("id"), resultSet.getString("name"));
                list.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<NXB> getAllNxb(){
        ArrayList<NXB> list = new ArrayList<NXB>();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM nxb";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                NXB author = new NXB(resultSet.getInt("id"), resultSet.getString("name"));
                list.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<Type> getAllType(){
        ArrayList<Type> list = new ArrayList<Type>();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM type";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Type author = new Type(resultSet.getInt("id"), resultSet.getString("name"));
                list.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
