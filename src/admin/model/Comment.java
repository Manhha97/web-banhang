package admin.model;

import java.sql.Timestamp;

public class Comment {
    private Customer customer;
    private Product product;
    private String comment;
    private Timestamp createAt;
    private Integer starVote;

    public Comment() {
    }

    public Integer getStarVote() {
        return starVote;
    }

    public void setStarVote(Integer starVote) {
        this.starVote = starVote;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
