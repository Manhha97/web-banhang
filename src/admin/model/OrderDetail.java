package admin.model;

public class OrderDetail {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Integer deliveryId;
    private Integer customerId;
    private Double distance;
    private Integer amount;

    public OrderDetail() {
    }

    public OrderDetail(String name, String phone, String address, Integer deliveryId, Integer customerId, Double distance) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.deliveryId = deliveryId;
        this.customerId = customerId;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
