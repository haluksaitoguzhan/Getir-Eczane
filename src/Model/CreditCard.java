package Model;

import Helper.DBConnection;
import Helper.Helper;
import View.CustomerScreen;

public class CreditCard  extends Payment {
	private String number;
	private String CVV;
	private String validThrough;
	private String password;
	
	public CreditCard() {}
	
	public CreditCard(String pId, String pCustomerName, String pDate, float pAmount, String pType, float subTotal,String address, String number, String cVV, String validThrough, String password) {
		super(pId, pCustomerName, pDate, pAmount, pType, subTotal,address);
		this.number = number;
		CVV = cVV;
		this.validThrough = validThrough;
		this.password = password;
	}
	
	@Override
	public boolean pay(CreditCard cc) {
		String query = "INSERT INTO payment (idpayment,pID, pDate, pType, pSubTotal, pNumber, pCVV, pVT, pPass, pAddress)"
				+ " VALUES ('" + Customer.findOrderID(cc.getpId()) + "','" + cc.getpId()
				+ "', '" + cc.getpDate() + "', '"+cc.getpType() +"', '"
				+ cc.getSubTotal() + "', '" + cc.getNumber() + "', '" + cc.getCVV()
				+ "', '" + cc.getValidThrough() + "', '" + cc.getPassword() + "', '" + cc.getpAddress() + "')";
		
		String query1 = "INSERT INTO getireczane.order (orderID, oCustomerName, oDateCreated, oSubTotal)"
				+ " VALUES ('" + cc.getpId() + "', '" + cc.getPCustomerName() + "', '"
				+ cc.getpDate() + "', '" + cc.getSubTotal() + "')";
		
		String query2 = "DELETE FROM getireczane.cart WHERE basketID='" + cc.getpId() + "'";
		
		if(Admin.updateCatalog(cc.getpId())) {
			
			if(DBConnection.updateDBAndQuery(query)) {
				Customer.addDrugsCartToOrders(cc.getpId());
				
				if (DBConnection.updateDBAndQuery(query1) && DBConnection.updateDBAndQuery(query2) ) {
					return true;				
				} else {
					System.out.println("yer CreditCard payOrder 1");
					Helper.showMsg("Odeme isleminde hata!");
					return false;
				}
				
			}else {
				System.out.println("yer CreditCard payOrder 2");
				return false;
			}
			
		}else {
			System.out.println("yer CreditCard payOrder 3");
			return false;
		}
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String cVV) {
		CVV = cVV;
	}

	public String getValidThrough() {
		return validThrough;
	}

	public void setValidThrough(String validThrough) {
		this.validThrough = validThrough;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean pay(Cash cash) {
		// TODO Auto-generated method stub
		return false;
	}
}
