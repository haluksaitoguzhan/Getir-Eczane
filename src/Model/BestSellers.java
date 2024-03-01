package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Helper.DBConnection;

public class BestSellers {
	
	public static void displayBestSellers(JTable table, int avarage) {
		DefaultTableModel drugModel = new DefaultTableModel();
		Object[] colNameDrug = { "ID", "İLAC ADI", "FİYAT", "SATIŞ ADETİ" };
		Object[] rowDrug = new Object[4];

		String query = "SELECT * FROM getireczane.drugs WHERE drugs.dSales >= '" + avarage
				+ "' and drugs.dStock != '0' ORDER BY drugs.dSales DESC, dName";
		ResultSet rs = DBConnection.connectDBAndQuery(query);

		drugModel.setColumnCount(0);
		drugModel.setRowCount(0);
		drugModel.setColumnIdentifiers(colNameDrug);
		try {
			while (rs.next()) {
				rowDrug[0] = rs.getString("idDrugs");
				rowDrug[1] = rs.getString("dName");
				rowDrug[2] = rs.getString("dCost");
				rowDrug[3] = rs.getString("dSales");
				drugModel.addRow(rowDrug);
			}
			table.setModel(drugModel);
		} catch (SQLException e1) {
			System.out.println("tam burası");
			e1.printStackTrace();
		}
	}

}
