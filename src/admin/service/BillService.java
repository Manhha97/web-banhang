package admin.service;

import java.util.List;

import admin.dao.BillDAO;
import admin.dto.BillDetailResponse;
import admin.dto.BillResponse;
import admin.dto.StatisticResponse;
import admin.model.*;

public class BillService {
	private BillDAO billDAO= new BillDAO();
	public BillService() {
		
	}
	public BillResponse search(BillResponse response){
        BillResponse objectResponse = new BillResponse();
        objectResponse.setList(billDAO.search(response));
        objectResponse.setTotal(billDAO.getTotal(response));
        return objectResponse;
    }
	public Bill getByCode(Bill bill){
        Bill u = billDAO.getByCode(bill);
        return  u;
    }
    public OrderDetail getAllOrderDetail(OrderDetail custormDetailOrder){
        OrderDetail c= billDAO.getAllOrderDetail(custormDetailOrder);
        return c;
    }
    public Customer getCustomerinf( Customer custorm){
    	Customer c= billDAO.getCustomerinf(custorm);
        return c;
    }

    public BillDetailResponse getProductDetail(BillDetailResponse response){
    	BillDetailResponse objectResponse = new BillDetailResponse();
        objectResponse.setList(billDAO.getProductDetail(response));
        return objectResponse;
    }
    public StatisticResponse getStatisticMonth(StatisticResponse response){
    	StatisticResponse objectResponse = new StatisticResponse();
        objectResponse.setList(billDAO.getStatisticMonth(response));
        return objectResponse;
    }
    public StatisticResponse getStatisticDate(StatisticResponse response){
    	StatisticResponse objectResponse = new StatisticResponse();
        objectResponse.setList(billDAO.getStatisticDate(response));
        return objectResponse;
    }
    public StatisticResponse getStatisticProductMonth(StatisticResponse response){
    	StatisticResponse objectResponse = new StatisticResponse();
        objectResponse.setList(billDAO.getStatisticProductMonth(response));
        return objectResponse;
    }
}
