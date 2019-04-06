package admin.dao;

import admin.model.*;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public Boolean insert(OrderDetail detailOrder){
        String sql = "INSERT INTO order_detail (customer_id, `name`, phone, address, delivery_id, distance, apiId) VALUES (?,?,?,?,?,?,?)";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, detailOrder.getCustomerId());
            statement.setString(2, detailOrder.getName());
            statement.setString(3, detailOrder.getPhone());
            statement.setString(4, detailOrder.getAddress());
            statement.setInt(5, detailOrder.getDeliveryId());
            statement.setDouble(6, detailOrder.getDistance());
            statement.setString(7, detailOrder.getApiId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean edit(OrderDetail orderDetail){
        String sql="update order_detail set `name`=?, phone=?, address=?,delivery_id=?,distance=? where customer_id = ? and api_id = ?";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, orderDetail.getName());
            statement.setString(2, orderDetail.getPhone());
            statement.setString(3, orderDetail.getAddress());
            statement.setInt(4, orderDetail.getDeliveryId());
            statement.setDouble(5, orderDetail.getDistance());
            statement.setInt(6,orderDetail.getCustomerId());
            statement.setString(6,orderDetail.getApiId());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public OrderDetail getAddressOrder(Customer customer){
        OrderDetail orderDetail = null;
        String sql = "SELECT od.*,dt.amout FROM order_detail od, delivery_type dt  WHERE dt.id = od.delivery_id AND ";
        if(customer.getId() == null && customer.getApiId() != null) {
        	sql += " od.api_id = ?";
        } else {
        	sql += " od.customer_id = ?";
        }
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            if(customer.getId() == null && customer.getApiId() != null) {
            	statement.setString(1, customer.getApiId());
            } else {
            	statement.setInt(1, customer.getId());
            }
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setAmount(rs.getInt("amout"));
                orderDetail.setAddress(rs.getString("address"));
                orderDetail.setName(rs.getString("name"));
                orderDetail.setPhone(rs.getString("phone"));
                orderDetail.setDistance(rs.getDouble("distance"));
                orderDetail.setDeliveryId(rs.getInt("delivery_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }
    public Bill getBill(Integer orderId, String billCode){
        Bill bill = null;
        String sql = "SELECT * FROM bill WHERE bill.order_detail_id = ? AND bill.`code` = ?";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.setString(2, billCode);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                bill = new Bill();
                bill.setCode(rs.getString("code"));
                bill.setCreateAt(rs.getTimestamp("create_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
    public List<Bill> getListBill(Integer orderId){
        List<Bill> list = new ArrayList<Bill>();
        String sql = "SELECT * FROM bill WHERE bill.order_detail_id = ?";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Bill bill = new Bill();
                bill.setCode(rs.getString("code"));
                bill.setCreateAt(rs.getTimestamp("create_at"));
                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<BillDetail> getBillDetail(String billCode){
        List<BillDetail> list = new ArrayList<BillDetail>();
        String sql = "SELECT products.`name`,products.price,bill_detail.quantity FROM bill_detail,products WHERE bill_code = ? AND bill_detail.product_code = products.code";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, billCode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                BillDetail bill = new BillDetail();
                bill.setQuantity(rs.getInt("quantity"));
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                bill.setProduct(product);
                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Boolean insertBill(Bill bill){
        String sql = "INSERT INTO bill (code, create_at, order_detail_id) VALUES (?,?,?)";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bill.getCode());
            statement.setTimestamp(2, bill.getCreateAt());
            statement.setInt(3, bill.getOrderDetailId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean insertBillDetail(List<BillDetail> list){
        String sql = "INSERT INTO bill_detail (bill_code, product_code, quantity) VALUES (?,?,?)";
        try {
            Connection connection = ConnectionUtil.open();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            for (BillDetail billDetail: list) {
                statement.setString(1, billDetail.getBillCode());
                statement.setString(2, billDetail.getProductCode());
                statement.setInt(3, billDetail.getQuantity());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
