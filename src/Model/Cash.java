package Model;

import Helper.DBConnection;
import Helper.Helper;

public class Cash extends Payment {
	
	@Override
	public boolean pay(Cash cash) {
		String query = "INSERT INTO payment (idpayment,pID, pDate, pType, pSubTotal, pAddress)" + " VALUES ('"
				+ Customer.findOrderID(cash.getpId()) + "', '" + cash.getpId() + "', '" + cash.getpDate() + "', '"
				+ cash.getpType() + "', '" + cash.getSubTotal() + "', '" + cash.getpAddress() + "')";

		String query1 = "INSERT INTO getireczane.order (orderID, oCustomerName, oDateCreated, oSubTotal)" + " VALUES ('"
				+ cash.getpId() + "', '" + cash.getPCustomerName() + "', '" + cash.getpDate() + "', '" + cash.getSubTotal()
				+ "')";

		String query2 = "DELETE FROM getireczane.cart WHERE basketID='" + cash.getpId() + "'";

		if (Admin.updateCatalog(cash.getpId())) {
			if(DBConnection.updateDBAndQuery(query)) {
				Customer.addDrugsCartToOrders(cash.getpId());
				
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
		} else {
			System.out.println("yer CreditCard payOrder 3");
			return false;
		}
	}
	
	@Override
	public boolean pay(CreditCard creditCard) {
		return false;
	}

}
