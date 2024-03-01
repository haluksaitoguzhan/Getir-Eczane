package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import Helper.DBConnection;
import Helper.Helper;

public class OrderDetails {
	private String orderID;
	private String drugID;
	private String drugName;
	private float unitCost;
	private float subTotal;
	
	public OrderDetails() {}
	
	public OrderDetails(String orderID, String drugID, String drugName, float unitCost, float subTotal) {
		this.orderID = orderID;
		this.drugID = drugID;
		this.drugName = drugName;
		this.unitCost = unitCost;
		this.subTotal = subTotal;
	}
	
	public static float calculateSubTotalOrderDetail(String orderId) {
		 ResultSet rs = DBConnection.connectDBAndQuery("SELECT odTotalCost FROM getireczane.orderdetail where idorderdetail = '" + orderId + "'");
		 float subTotal = 0;
		 try {
			while(rs.next()) {
				 subTotal+=rs.getFloat("odTotalCost");
			 }
		} catch (SQLException e) {
			Helper.showMsg("eror");
			e.printStackTrace();
		}
		return subTotal;
	}
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getDrugID() {
		return drugID;
	}
	public void setDrugID(String drugID) {
		this.drugID = drugID;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public float getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
}
