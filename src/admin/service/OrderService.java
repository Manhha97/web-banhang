package admin.service;

import admin.dao.OrderDAO;
import admin.model.Bill;
import admin.model.BillDetail;
import admin.model.Customer;
import admin.model.OrderDetail;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    public OrderService() {
    }
    public Boolean insertBill(Bill bill){
        return orderDAO.insertBill(bill);
    }
    public Boolean insertBillDetail(List<BillDetail> list){
        return orderDAO.insertBillDetail(list);
    }
    public OrderDetail getAddressOrder(Customer customer){
        return orderDAO.getAddressOrder(customer);
    }
    public Boolean insert(OrderDetail orderDetail){
        return orderDAO.insert(orderDetail);
    }
    public Boolean edit(OrderDetail orderDetail){
        return orderDAO.edit(orderDetail);
    }
    public Bill getBill(Integer id, String code){
        return orderDAO.getBill(id, code);
    }
    public List<Bill> getListBill(Integer id){
        return orderDAO.getListBill(id);
    }
    public List<BillDetail> getBillDetail(String code){
        return orderDAO.getBillDetail(code);
    }
}
