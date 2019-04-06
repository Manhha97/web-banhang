package admin.model;

import java.sql.Date;

public class Product {
    private Integer id;
    private String code;
    private String name;
    private String image;
    private Integer quantity;
    private Double price;
    private Integer sale;
    private Integer typeId;
    private Integer nxbId;
    private Integer authorId;
    private String priceSort;
    private String nameSort;
    private Date createAt;
    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPriceSort() {
        return priceSort;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }

    public String getNameSort() {
        return nameSort;
    }

    public void setNameSort(String nameSort) {
        this.nameSort = nameSort;
    }

    public Product(String code) {
        this.code = code;
    }

    public Product(String code, String name, String image, Integer quantity, Double price, Integer sale, Integer typeId, Integer nxbId, Integer authorId) {
        this.code = code;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
        this.typeId = typeId;
        this.nxbId = nxbId;
        this.authorId = authorId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getNxbId() {
        return nxbId;
    }

    public void setNxbId(Integer nxbId) {
        this.nxbId = nxbId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", sale=" + sale +
                ", typeId=" + typeId +
                ", nxbId=" + nxbId +
                ", authorId=" + authorId +
                '}';
    }
}
