package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import admin.model.*;
import com.sun.org.apache.regexp.internal.recompile;

import admin.dto.BillDetailResponse;
import admin.dto.BillResponse;
import admin.dto.ObjectResponse;
import admin.dto.StatisticResponse;
import util.ConnectionUtil;

public class BillDAO {
	public ArrayList<Bill> search(BillResponse response) {
        ArrayList<Bill> list = new ArrayList<Bill>();
        Bill bill = response.getBill();
        Integer currentPage = response.getCurrentPage();
        Integer pageSize = response.getPageSize();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM bill WHERE 1 = 1";
            HashMap<Integer, Object> query = new HashMap<Integer, Object>();
            Integer i = 0;
            if(bill.getCode() != null && !bill.getCode().equalsIgnoreCase("")){
                sql += " AND code LIKE ?";
                i++;
                query.put(i, bill.getCode().trim());
            }
            if(bill.getCreateAt()!= null){
                sql += " AND create_at LIKE ?";
                i++;
                query.put(i, bill.getCreateAt());
            }
            if(bill.getOrderDetailId() != 0){
                sql += " AND order_detail_id LIKE ?";
                i++;
                query.put(i, bill.getOrderDetailId());
            }
            if(bill.getCreateAt()!= null){
                sql += " ORDER BY `create_at` " ;
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
                Bill p = new Bill();
                p.setCode(rs.getString("code"));
                p.setCreateAt(rs.getTimestamp("create_at"));
                p.setOrderDetailId(rs.getInt("order_detail_id"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public Integer getTotal(BillResponse response) {
        Bill bill = response.getBill();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT COUNT(1) FROM bill WHERE 1 = 1";
            HashMap<Integer, Object> query = new HashMap<Integer, Object>();
            Integer i = 0;
            if(bill.getCode() != null && !bill.getCode().equalsIgnoreCase("")){
                sql += " AND code LIKE ?";
                i++;
                query.put(i, bill.getCode().trim());
            }
            if(bill.getCreateAt()!= null){
                sql += " AND create_at LIKE ?";
                i++;
                query.put(i, bill.getCreateAt());
            }
            if(bill.getOrderDetailId() != 0){
                sql += " AND order_detail_id LIKE ?";
                i++;
                query.put(i, bill.getOrderDetailId());
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

    public Bill getByCode(Bill bill) {
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM bill WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bill.getCode());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Bill p = new Bill();
                p.setCode(rs.getString("code"));
                p.setCreateAt(rs.getTimestamp("create_at"));
                p.setOrderDetailId(rs.getInt("order_detail_id"));
                
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public OrderDetail getAllOrderDetail(OrderDetail order_id){
        
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM order_detail WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order_id.getId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                OrderDetail p = new OrderDetail();
                p.setId(resultSet.getInt("id"));
                p.setCustomerId(resultSet.getInt("customer_id"));
                p.setName(resultSet.getString("name"));
                p.setAddress(resultSet.getString("address"));
                p.setPhone(resultSet.getString("phone"));
                p.setDeliveryId(resultSet.getInt("delivery_id"));

                return p;
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Customer getCustomerinf(Customer customer) {
    	
    	try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM customer WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Customer p = new Customer();
                p.setId(resultSet.getInt("id"));
                p.setName(resultSet.getString("name"));
                p.setEmail(resultSet.getString("email"));
                p.setPhone(resultSet.getString("phone"));

                
                return p;
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	public ArrayList<BillDetail> getProductDetail(BillDetailResponse response) {
		ArrayList<BillDetail> list = new ArrayList<BillDetail>();
        BillDetail bill = response.getBilldetail();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT * FROM bill_detail WHERE 1 = 1 AND bill_code = '"+bill.getBillCode()+"'";
            
            
            PreparedStatement statement = connection.prepareStatement(sql);


            ResultSet rs = statement.executeQuery();
            
            while (rs.next()){
                BillDetail p = new BillDetail();
                p.setBillCode(rs.getString("bill_code"));
                p.setProductCode(rs.getString("product_code"));
                p.setQuantity(rs.getInt("quantity"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
		}
	public ArrayList<Statistic> getStatisticMonth(StatisticResponse response) {
		ArrayList<Statistic> list = new ArrayList<Statistic>();
		Statistic bill = response.getStatistic();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT SUM(total_price) AS sum, SUBSTR(bill.create_at FROM 1 FOR 7) AS month "
            		+ " FROM bill GROUP BY SUBSTR(bill.create_at FROM 1 FOR 7)";
            
            PreparedStatement statement = connection.prepareStatement(sql);


            ResultSet rs = statement.executeQuery();
            
            while (rs.next()){
                Statistic p = new Statistic();
                p.setMonth(rs.getString("month"));
                p.setSum(rs.getFloat("sum"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
		}
	
	public ArrayList<Statistic> getStatisticDate(StatisticResponse response) {
		ArrayList<Statistic> list = new ArrayList<Statistic>();
		Statistic bill = response.getStatistic();
        try {
            Connection connection = ConnectionUtil.open();
            String sql = "SELECT SUM(total_price) AS sum, SUBSTR(bill.create_at FROM 1 FOR 10) AS month "
            		+ " FROM bill GROUP BY SUBSTR(bill.create_at FROM 1 FOR 10)";
            
            PreparedStatement statement = connection.prepareStatement(sql);


            ResultSet rs = statement.executeQuery();
            
            while (rs.next()){
                Statistic p = new Statistic();
                p.setMonth(rs.getString("month"));
                p.setSum(rs.getFloat("sum"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
		}
	
	public ArrayList<Statistic> getStatisticProductMonth(StatisticResponse response) {
		ArrayList<Statistic> list = new ArrayList<Statistic>();
		Statistic bill = response.getStatistic();
        try {
            Connection connection = ConnectionUtil.open();
           
            String sql = "SELECT month, product_code,Max(Z.sum) AS sum, Z.price as price "
            		+ "  FROM (SELECT SUBSTR(bill.create_at FROM 1 FOR 7) AS month, product_code, SUM(bill_detail.quantity) AS sum, price*bill_detail.quantity AS price "
            					+"  FROM bill, bill_detail, products WHERE bill.code= bill_detail.bill_code AND product_code=products.code GROUP BY SUBSTR(bill.create_at FROM 1 FOR 7), product_code) AS Z" + 
            		"  GROUP BY Z.month";
            
            PreparedStatement statement = connection.prepareStatement(sql);


            ResultSet rs = statement.executeQuery();
            
            while (rs.next()){
                Statistic p = new Statistic();
                p.setMonth(rs.getString("month"));
                p.setPrice(rs.getFloat("price"));
                p.setProduct_code(rs.getString("product_code"));
                p.setSum(rs.getFloat("sum"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
		}
}
