package admin.dto;

import java.util.List;

import admin.model.BillDetail;

public class BillDetailResponse {
	private BillDetail billdetail;
	private List list;
	public BillDetailResponse() {
		super();
	}
	public BillDetail getBilldetail() {
		return billdetail;
	}
	public void setBilldetail(BillDetail billdetail) {
		this.billdetail = billdetail;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "BillDetailResponse [billdetail=" + billdetail + ", list=" + list + "]";
	}
	

}
