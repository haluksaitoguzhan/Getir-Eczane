package Model;

public class Orders {
	private String orderID;
	private String customerName;
	private String dateShipped;
	private OrderDetails od;

	public Orders() {}
	
	public Orders(String orderID, String dateShipped, String customerName, OrderDetails od) {
		this.orderID = orderID;
		this.dateShipped = dateShipped;
		this.customerName = customerName;
		this.od = od;
	}
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getDateShipped() {
		return dateShipped;
	}
	public void setDateShipped(String dateShipped) {
		this.dateShipped = dateShipped;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public OrderDetails getOd() {
		return od;
	}
	public void setOd(OrderDetails od) {
		this.od = od;
	}
	
}
