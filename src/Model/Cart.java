package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import Helper.*;

import Helper.DBConnection;

public class Cart extends Medicine {
	private Date dateAdded;
	private float totalCost;
	
	public Cart() {}
	
	public static float calculateSubTotalCart(String customerId) {
		 ResultSet rs = DBConnection.connectDBAndQuery("SELECT cTotalCost FROM getireczane.cart where basketID = '" + customerId + "'");
		 float subTotal = 0;
		 try {
			while(rs.next()) {
				 subTotal+=rs.getFloat("cTotalCost");
			 }
		} catch (SQLException e) {
			Helper.showMsg("eror");
			e.printStackTrace();
		}
		return subTotal;
	}
	
	public Cart(int id, String name, float cost, int stock, int cartID, Date dateAdded, float totalCost) {
		super(id, name, cost, stock);
		this.dateAdded = dateAdded;
		this.totalCost = totalCost;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
}
