package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import View.LoginScreen;

public class DBConnection {
	
	public static ResultSet connectDBAndQuery(String query) {
		ResultSet myRs = null;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/getireczane", "root", "4757");
			Statement myStat = myConn.createStatement();
			myRs = myStat.executeQuery(query);
		} catch (Exception e) {
			Helper.showMsg("eror");
			e.printStackTrace();
		}
		return myRs;
	}
	
	public static boolean updateDBAndQuery(String query) {
		boolean control = false;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/getireczane", "root", "4757");
			Statement myStat = myConn.createStatement();
			myStat.executeUpdate(query);
			control = true;
		} catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				//Helper.showMsg("Böyle bir kullanıcı zaten var");
				System.out.println("yer dbConnection");
				control = false;
			}else {
				System.out.println("yer dbConnection 2");
				Helper.showMsg("eror");
				e.printStackTrace();
				control = false;
			}
		}
		return control;
	}
	
	public static boolean controlUniqeCart(String customerId,int drugId) {
		boolean control = true;
		String query = "SELECT * FROM getireczane.cart WHERE basketID = '" + customerId + "'";
		ResultSet rs = connectDBAndQuery(query);
		try {
			while(rs.next()) {
				if(rs.getInt("idbasket") == drugId) {
					control = false;
				}
			}
		} catch (SQLException e) {
			control = false;
			Helper.showMsg("eror");
			System.out.println("yer dbConnection 3");
			e.printStackTrace();
		}
		return control;
	}


	public static void displaySeraching(String query, JTable table) {
		DefaultTableModel drugModel = new DefaultTableModel();
		Object[] colNameDrug = { "ID", "İLAC ADI", "FİYAT" };
		Object[] rowDrug = new Object[3];

		ResultSet rs = DBConnection.connectDBAndQuery(query);
		drugModel.setColumnCount(0);
		drugModel.setRowCount(0);
		drugModel.setColumnIdentifiers(colNameDrug);
		try {
			while (rs.next()) {
				// rowDrug[3] = rs.getString("idDrugs");
				rowDrug[0] = rs.getString("idDrugs");
				rowDrug[1] = rs.getString("dName");
				rowDrug[2] = rs.getString("dCost");
				// rowDrug[2] = rs.getString("dStock");
				drugModel.addRow(rowDrug);
			}
			table.setModel(drugModel);
		} catch (SQLException e1) {
			Helper.showMsg("Şu anda arama yapılamıyor!");
			e1.printStackTrace();
		}

	}

}
