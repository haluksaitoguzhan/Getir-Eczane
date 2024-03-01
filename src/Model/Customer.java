package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Helper.DBConnection;
import Helper.Helper;

public class Customer extends User {
	private String address;
	private String birthDate;
	private String phoneNumber;
	
	public Customer() {}
	
	public Customer(String userId, String userName, String email, String password, String address, String birthDate, String phoneNumber) {
		super(userId,userName,email,password);
		this.address = address;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}
	
	public static void myOrders(String customerId) {
		
	}
	
	public static void addDrugsCartToOrders(String customerId) {
		ResultSet rs = DBConnection.connectDBAndQuery("SELECT * FROM getireczane.cart where basketID = '" + customerId + "'");
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String pAddedDate = df.format(now);
		try {
			
			while (rs.next()) {
				String query = "INSERT INTO orderdetail (idorderdetail, detailID, odDrugName, odUnitCost, odTotalCost, odAmount, odAddedDate)"
						+ " VALUES ('" + Customer.findOrderID(customerId) + "','" + customerId + "', '" + rs.getString("cName") + "', '" + rs.getString("cUnitCost")
						+ "', '" + rs.getString("cTotalCost") + "', '" + rs.getString("cAmount") + "', '" + pAddedDate + "')";
				DBConnection.updateDBAndQuery(query);
			}
			
		} catch (SQLException e1) {
			System.out.println("ilaçları geçiş işleminde bir hata oldu");
			e1.printStackTrace();
		}
	}
	
	public static int findOrderID(String customerId) {
		String query = "SELECT idorder FROM getireczane.order";//
		ResultSet rs = DBConnection.connectDBAndQuery(query);
		int orderIndex = 0;
		try {
			while (rs.next()) {
				orderIndex = rs.getInt("idorder");
			}
		} catch (SQLException e1) {
			System.out.println("order index: "+orderIndex);
			Helper.showMsg("son index bulunamadı yer dbConnection!");
			e1.printStackTrace();
		}
		return orderIndex+1;
	}
	
	public static boolean register() {
		return true;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
