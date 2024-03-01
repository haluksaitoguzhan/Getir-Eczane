package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Helper.*;

public class Admin extends User {
	public Admin() {
	}

	public Admin(String id, String name, String email, String password) {
		super(id, name, email, password);
	}

	public static boolean updateCatalog(String customerId) {
		String query = "SELECT cart.idbasket, cart.cAmount, drugs.dStock, drugs.dName, drugs.dSales FROM getireczane.cart, getireczane.drugs WHERE cart.idbasket = drugs.idDrugs and cart.basketID = '"
				+ customerId + "'";

		try {
			ResultSet rs = DBConnection.connectDBAndQuery(query);

			while (rs.next()) {
				int sales = rs.getInt("dSales") + rs.getInt("cAmount");
				int newStock = rs.getInt("dStock") - rs.getInt("cAmount");
					DBConnection.updateDBAndQuery("UPDATE drugs SET dSales='" + sales + "',dStock='" + newStock
							+ "' WHERE idDrugs='" + rs.getString("idbasket") + "'");
			}
			return true;
		} catch (Exception e) {
			System.out.println("yer Admin updateCatalog");
			e.printStackTrace();
			return false;
		}
	}

	public static boolean controlAllStock(String customerId) {
		String query = "SELECT cart.idbasket, cart.cAmount, drugs.dStock, drugs.dName, drugs.dSales FROM getireczane.cart, getireczane.drugs WHERE cart.idbasket = drugs.idDrugs and cart.basketID = '"
				+ customerId + "'";

		try {
			ResultSet rs = DBConnection.connectDBAndQuery(query);
			while (rs.next()) {
				if (rs.getInt("dStock") < rs.getInt("cAmount")) {
					Helper.showMsg(rs.getString("dName") + " ilacından "+ rs.getString("dStock")+" adet stok kalmıştır!");
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			System.out.println("yer Admin controlAllStockAndUpdate");
			e.printStackTrace();
			return false;
		}
	}

	public static int controlStock(int drugId) {
		int stock = 0;
		ResultSet rs = DBConnection
				.connectDBAndQuery("SELECT dStock FROM getireczane.drugs WHERE idDrugs='" + drugId + "'");

		try {
			rs.next();
			stock = rs.getInt("dStock");
			// System.out.println("gelen stock: "+ stock);
		} catch (SQLException e) {
			System.out.println("yer admin updateCatalog");
			e.printStackTrace();
		}
		return stock;
	}

	public static void connectDBAndDisplayDrugs(String type, JTable table) {
		if (type.matches("admin")) {
			DefaultTableModel drugModel = new DefaultTableModel();
			Object[] colNameDrug = { "ID", "İLAC ADI", "FİYAT", "STOK" };
			Object[] rowDrug = new Object[4];

			ResultSet rs = DBConnection.connectDBAndQuery("SELECT * FROM drugs");
			drugModel.setColumnCount(0);
			drugModel.setRowCount(0);
			drugModel.setColumnIdentifiers(colNameDrug);
			try {
				while (rs.next()) {
					rowDrug[0] = rs.getString("idDrugs");
					rowDrug[1] = rs.getString("dName");
					rowDrug[2] = rs.getString("dCost");
					rowDrug[3] = rs.getString("dStock");
					drugModel.addRow(rowDrug);
				}
				table.setModel(drugModel);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			DefaultTableModel drugModel = new DefaultTableModel();
			Object[] colNameDrug = { "ID", "İLAC ADI", "FİYAT" };
			Object[] rowDrug = new Object[3];

			ResultSet rs = DBConnection.connectDBAndQuery("SELECT * FROM drugs");
			drugModel.setColumnCount(0);
			drugModel.setRowCount(0);
			drugModel.setColumnIdentifiers(colNameDrug);
			try {
				while (rs.next()) {
					rowDrug[0] = rs.getString("idDrugs");
					rowDrug[1] = rs.getString("dName");
					rowDrug[2] = rs.getString("dCost");
					// rowDrug[2] = rs.getString("dStock");
					drugModel.addRow(rowDrug);
				}
				table.setModel(drugModel);
			} catch (SQLException e1) {
				System.out.println("tam burası");
				e1.printStackTrace();
			}
		}
	}

	public static void connectDBAndDisplayCustomerCart(String customerId, JTable table) {
		DefaultTableModel drugModel = new DefaultTableModel();
		Object[] colNameDrug = { "ID", "İLAC ADI", "BİRİM FİYAT", "ADET", "ARA FİYAT" };
		Object[] rowDrug = new Object[5];
		ResultSet rs = DBConnection
				.connectDBAndQuery("SELECT * FROM getireczane.cart where basketID = '" + customerId + "'");
		drugModel.setColumnCount(0);
		drugModel.setRowCount(0);
		drugModel.setColumnIdentifiers(colNameDrug);
		try {
			while (rs.next()) {
				rowDrug[0] = rs.getString("idbasket");
				rowDrug[1] = rs.getString("cName");
				rowDrug[2] = rs.getString("cUnitCost");
				rowDrug[3] = rs.getString("cAmount");
				rowDrug[4] = rs.getString("cTotalCost");
				drugModel.addRow(rowDrug);
			}
			table.setModel(drugModel);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static void connectDBAndDisplayCustomerOrders(String customerId, JTable table) {
		DefaultTableModel drugModel = new DefaultTableModel();
		Object[] colNameDrug = { "SİPARİŞ ID", "KULLANICI ADI", "SİPARİŞ TARİHİ", "FİYAT", "ÖDEME TİPİ" };
		Object[] rowDrug = new Object[5];
		String query = "SELECT getireczane.order.*,getireczane.payment.pType FROM getireczane.order,getireczane.payment WHERE idpayment = idorder and orderID = '"
				+ customerId + "'";
		ResultSet rs = DBConnection.connectDBAndQuery(query);

		// "SELECT * FROM getireczane.order where orderID = '" + customerId + "'"
		drugModel.setColumnCount(0);
		drugModel.setRowCount(0);
		drugModel.setColumnIdentifiers(colNameDrug);
		try {
			while (rs.next()) {
				rowDrug[0] = rs.getString("idorder");
				rowDrug[1] = rs.getString("oCustomerName");
				rowDrug[2] = rs.getString("oDateCreated");
				rowDrug[3] = rs.getString("oSubTotal");
				rowDrug[4] = rs.getString("pType");
				//
				drugModel.addRow(rowDrug);
			}
			table.setModel(drugModel);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static void connectDBAndDisplayCustomerOrderDetails(String orderID, JTable table) {
		DefaultTableModel drugModel = new DefaultTableModel();
		Object[] colNameDrug = { "İLAC ADI", "BİRİM FİYAT", "ADET", "ARA FİYAT", "TARİH", "TOPLAM FİYAT" };
		Object[] rowDrug = new Object[6];
		ResultSet rs = DBConnection
				.connectDBAndQuery("SELECT * FROM getireczane.orderdetail where idorderdetail = '" + orderID + "'");
		drugModel.setColumnCount(0);
		drugModel.setRowCount(0);
		drugModel.setColumnIdentifiers(colNameDrug);
		try {
			while (rs.next()) {
				rowDrug[0] = rs.getString("odDrugName");
				rowDrug[1] = rs.getString("odUnitCost");
				rowDrug[2] = rs.getString("odAmount");
				rowDrug[3] = rs.getString("odTotalCost");
				rowDrug[4] = rs.getString("odAddedDate");
				rowDrug[5] = OrderDetails.calculateSubTotalOrderDetail(orderID);
				drugModel.addRow(rowDrug);
			}
			table.setModel(drugModel);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static void connectDBAndDisplayAdmins(JTable table) {
		DefaultTableModel drugModel = new DefaultTableModel();
		Object[] colNameDrug = { "TC", "ADMİN ADI", "EMAİL", "ŞİFRE" };
		Object[] rowDrug = new Object[4];
		ResultSet rs = DBConnection.connectDBAndQuery("SELECT * FROM getireczane.administrators");
		drugModel.setColumnCount(0);
		drugModel.setRowCount(0);
		drugModel.setColumnIdentifiers(colNameDrug);
		try {
			while (rs.next()) {
				rowDrug[0] = rs.getString("adminID");
				rowDrug[1] = rs.getString("adminName");
				rowDrug[2] = rs.getString("adminEmail");
				rowDrug[3] = rs.getString("adminPassword");
				drugModel.addRow(rowDrug);
			}
			table.setModel(drugModel);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// SELECT * FROM getireczane.cart where basketID = 'null'

}
