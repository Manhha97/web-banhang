package admin.dto;

import java.util.List;

import admin.model.Bill;
import admin.model.BillDetail;
import admin.model.User;

public class BillResponse {
	private Integer currentPage;
    private Bill bill;
    private Integer pageSize;
    private List list;
    private Integer total;
	
	public BillResponse() {
		super();
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "BillResponse [currentPage=" + currentPage + ", bill=" + bill + ", pageSize=" + pageSize + ", list="
				+ list + ", total=" + total + "]";
	}
    
}
