package Model;

import java.sql.Date;

public abstract class Payment{
	private String pId;
	private String pCustomerName;
	private String pDate;
	private float pAmount;
	private String pType;
	private float subTotal;
	private String pAddress;
	
	
	public Payment() {}
	
	public Payment(String pId, String customerName, String pDate, float pAmount, String pType, float subTotal,String pAddress) {
		super();
		this.pId = pId;
		this.pCustomerName = customerName;
		this.pDate = pDate;
		this.pAmount = pAmount;
		this.pType = pType;
		this.subTotal = subTotal;
		this.pAddress = pAddress;
	}
	
	public abstract boolean pay(CreditCard creditCard);
	public abstract boolean pay(Cash cash);
	
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getPCustomerName() {
		return pCustomerName;
	}
	public void setPCustomerName(String customerName) {
		this.pCustomerName = customerName;
	}
	public String getpDate() {
		return pDate;
	}
	public void setpDate(String pDate) {
		this.pDate = pDate;
	}
	public float getpAmount() {
		return pAmount;
	}
	public void setpAmount(float pAmount) {
		this.pAmount = pAmount;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public String getpAddress() {
		return pAddress;
	}

	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}
	
}
