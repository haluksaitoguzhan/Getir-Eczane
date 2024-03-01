package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Helper.*;

public class Prescription {
	private int id;
	private String prescriptionCode;
	private String customerName;
	private String customerId;
	private Medicine medicine;

	public Prescription() {
	}

	public Prescription(int id, String prescriptionCode, String customerName, String customerId, Medicine medicine) {
		this.id = id;
		this.prescriptionCode = prescriptionCode;
		this.customerName = customerName;
		this.customerId = customerId;
		this.medicine = medicine;
	}

	public static boolean confirmPrescription(String prescriptionCode) {
		String query = "SELECT * FROM getireczane.prescription WHERE pCode='"+ prescriptionCode +"'";
		ResultSet rs = DBConnection.connectDBAndQuery(query);
		try {
			if (rs.next()) {
				if(rs.getString("pCode").isEmpty()) {
					Helper.showMsg("Kayıtlı böyle bir reçete bulunamadı");
					return false;
				}else {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("yer Prescription");
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static boolean displayPrescription(JTable table,String prescriptionCode, JTextField customerName,JTextField customerId) {
		
		if(confirmPrescription(prescriptionCode)) {
			DefaultTableModel drugModel = new DefaultTableModel();
			Object[] colNameDrug = {"İLAC ADI", "FİYAT", "ADET" };
			Object[] rowDrug = new Object[3];
			String query = "SELECT * FROM getireczane.prescription WHERE pCode='"+ prescriptionCode +"'";
			ResultSet rs = DBConnection.connectDBAndQuery(query);
			drugModel.setColumnCount(0);
			drugModel.setRowCount(0);
			drugModel.setColumnIdentifiers(colNameDrug);
			try {
				while (rs.next()) {
					rowDrug[0] = rs.getString("pDrugName");
					rowDrug[1] = rs.getString("pDrugCost");
					rowDrug[2] = rs.getString("pDrugStock");
					drugModel.addRow(rowDrug);
					customerName.setText(rs.getString("pCustomerName"));
					customerId.setText(rs.getString("pCustomerId"));
				}
				
				table.setModel(drugModel);
				return true;
			} catch (SQLException e1) {
				System.out.println("yer Prescription 3");
				e1.printStackTrace();
				return false;
			}
		}else {
			Helper.showMsg("Lütfen geçerli bir KOD giriniz!");
			System.out.println("yer Prescription 2");
			return false;
		}
		
	}
	
	//textTotalPrescription
	
	public static float calculateTotalPrescription(String prescriptionCode) {
		String query = "SELECT * FROM getireczane.prescription WHERE pCode='"+ prescriptionCode +"'";
		ResultSet rs = DBConnection.connectDBAndQuery(query);
		 float subTotal = 0;
		 try {
			while(rs.next()) {
				 subTotal+=rs.getFloat("pDrugCost");
			 }
		} catch (SQLException e) {
			System.out.println("yer Prescription calculate");
			Helper.showMsg("eror");
			e.printStackTrace();
		}
		return subTotal;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrescriptionCode() {
		return prescriptionCode;
	}

	public void setPrescriptionCode(String prescriptionCode) {
		this.prescriptionCode = prescriptionCode;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
}
