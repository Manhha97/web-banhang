package admin.model;

public class Statistic {
	private String month;
	private float sum;
	private float price;
	private String product_code;
	public Statistic() {
		super();
	}
	public Statistic(String month, float sum) {
		super();
		this.month = month;
		this.sum = sum;
	}
	public Statistic(String month, float sum, float price, String product_code) {
		super();
		this.month = month;
		this.sum = sum;
		this.price = price;
		this.product_code = product_code;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "Statistic [month=" + month + ", sum=" + sum + ", price=" + price + ", product_code=" + product_code
				+ "]";
	}
	

}
