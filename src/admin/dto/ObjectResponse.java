package admin.dto;

import admin.model.Product;

import java.util.List;

public class ObjectResponse {
    private Integer currentPage;
    private Product product;
    private Integer pageSize;
    private List list;
    private Integer total;


    public ObjectResponse() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        return "ObjectResponse{" +
                "currentPage=" + currentPage +
                ", product=" + product +
                ", pageSize=" + pageSize +
                ", list=" + list +
                ", total=" + total +
                '}';
    }
}
