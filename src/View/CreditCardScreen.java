package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Helper.DBConnection;
import Helper.Helper;
import Model.Admin;
import Model.Cart;
import Model.CreditCard;
import Model.Customer;
import Model.SendMail;


import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class CreditCardScreen extends JFrame {
	private static Customer customer;
	private JPanel contentPane;
	private JTextField textSubTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditCardScreen frame = new CreditCardScreen(customer);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public CreditCardScreen(Customer customer) throws ParseException {
		setCustomer(customer);
		setTitle("Getir Eczane Kredi Kartı ile Ödeme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 170, 385, 479);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbmingetlog = new JLabel(new ImageIcon(getClass().getResource("mingetlogo.jpg")));
		lbmingetlog.setBounds(162, 396, 207, 44);
		contentPane.add(lbmingetlog);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(20, 66, 330, 324);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTelNo = new JLabel("Kart No:");
		lblTelNo.setForeground(Color.WHITE);
		lblTelNo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblTelNo.setBounds(10, 22, 89, 38);
		panel.add(lblTelNo);

		MaskFormatter mfn = new MaskFormatter("#### #### #### ####");
		JFormattedTextField fNumber = new JFormattedTextField(mfn);
		fNumber.setFont(new Font("Times New Roman", Font.BOLD, 18));
		fNumber.setBounds(109, 22, 183, 35);
		panel.add(fNumber);

		JLabel lblNewLabel_1 = new JLabel("Şifre:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(45, 164, 56, 38);
		panel.add(lblNewLabel_1);
		
		MaskFormatter mfp = new MaskFormatter("####");
		JFormattedTextField fPassword = new JFormattedTextField(mfp);
		fPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		fPassword.setBounds(109, 167, 183, 35);
		panel.add(fPassword);

		JLabel lblTelNo_1 = new JLabel("CVV:");
		lblTelNo_1.setForeground(Color.WHITE);
		lblTelNo_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblTelNo_1.setBounds(45, 67, 54, 38);
		panel.add(lblTelNo_1);

		MaskFormatter mfcvv = new MaskFormatter("###");
		JFormattedTextField fCVV = new JFormattedTextField(mfcvv);
		fCVV.setFont(new Font("Times New Roman", Font.BOLD, 18));
		fCVV.setBounds(109, 71, 183, 35);
		panel.add(fCVV);

		JLabel lblTelNo_1_1 = new JLabel("S.K.T:");
		lblTelNo_1_1.setForeground(Color.WHITE);
		lblTelNo_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblTelNo_1_1.setBounds(23, 116, 76, 38);
		panel.add(lblTelNo_1_1);

		MaskFormatter mfvt = new MaskFormatter("##/##");
		JFormattedTextField fValidTrough = new JFormattedTextField(mfvt);
		fValidTrough.setFont(new Font("Times New Roman", Font.BOLD, 18));
		fValidTrough.setBounds(109, 120, 183, 35);
		panel.add(fValidTrough);

		JLabel lblSemekIstediinizIlacn = new JLabel("Kredi kartı bilgileri");
		lblSemekIstediinizIlacn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemekIstediinizIlacn.setForeground(new Color(255, 209, 14));
		lblSemekIstediinizIlacn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblSemekIstediinizIlacn.setBounds(74, 22, 217, 33);
		contentPane.add(lblSemekIstediinizIlacn);

		JLabel lbBackLogo = new JLabel(new ImageIcon(getClass().getResource("backlogo1.png")));
		lbBackLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PaymentScreen ps = new PaymentScreen(customer);
				ps.setVisible(true);
				dispose();
			}
		});
		lbBackLogo.setBounds(1, 3, 50, 44);
		contentPane.add(lbBackLogo);

		JButton btnConfirm = new JButton("Siparişi Onayla");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (fNumber.getText().length() == 0
						|| fCVV.getText().length() == 0 || fValidTrough.getText().length() == 0
						|| fPassword.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

						Date now = new Date();
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						String pAddedDate = df.format(now);
						
						CreditCard cc = new CreditCard();
						cc.setpId(customer.getId());
						cc.setPCustomerName(customer.getName());
						cc.setpDate(pAddedDate);
						cc.setpType("Kredi Kartı");
						cc.setSubTotal(Cart.calculateSubTotalCart(customer.getId()));
						cc.setpAddress(customer.getAddress());
						cc.setNumber(fNumber.getText());
						cc.setCVV(fCVV.getText());
						cc.setValidThrough(fValidTrough.getText());
						cc.setPassword(fPassword.getText());

						if (cc.pay(cc)) {
							fNumber.setText(null);
							fCVV.setText(null);
							fValidTrough.setText(null);
							fPassword.setText(null);
							Customer.addDrugsCartToOrders(customer.getId());
							
							Helper.showMsg("  İşleminiz başarıyla gerçekleşti.\nSiparişiniz bu gün içinde yola çıkacaktır.");
							
							SendMail.sendEmail(customer);
							
							CustomerScreen cs = new CustomerScreen(customer);
							cs.setVisible(true);
							dispose();				
						} else {
							Helper.showMsg("Sipariş işleminiz gerçekleştirilemiyor!");
							CustomerScreen cs = new CustomerScreen(customer);
							cs.setVisible(true);
							dispose();
						}
					
					/*
					 if (Admin.updateCatalog(customer.getId())) {
						//String cUser = txtUserName.getText();
						String cNumber = fNumber.getText();
						String cCVV = fCVV.getText();
						String cVT = fValidTrough.getText();
						String cPass = passwordCard.getText();

						Date now = new Date();
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						String pAddedDate = df.format(now);

						String query = "INSERT INTO payment (idpayment,pID, pDate, pType, pSubTotal, pNumber, pCVV, pVT, pPass, pAddress)"
								+ " VALUES ('" + Customer.findOderID(customer.getId()) + "','" + customer.getId()
								+ "', '" + pAddedDate + "', 'Kredi Kartı', '"
								+ Cart.calculateSubTotalCart(customer.getId()) + "', '" + cNumber + "', '" + cCVV
								+ "', '" + cVT + "', '" + cPass + "', '" + customer.getAddress() + "')";

						// System.out.println(query);

						if (DBConnection.updateDBAndQuery(query)) {
							fNumber.setText(null);
							fCVV.setText(null);
							fValidTrough.setText(null);
							passwordCard.setText(null);
							Customer.addDrugsCartToOrders(customer.getId());

							//System.out.println(Customer.findOderID(customer.getId()));

							String query2 = "INSERT INTO getireczane.order (orderID, oCustomerName, oDateCreated, oSubTotal)"
									+ " VALUES ('" + customer.getId() + "', '" + customer.getName() + "', '"
									+ pAddedDate + "', '" + Cart.calculateSubTotalCart(customer.getId()) + "')";

							System.out.println(query + "\n" + query2);

							if (DBConnection.updateDBAndQuery(query2)) {
								System.out.println("Sipariş bilgisi oluşturuldu");
								Helper.showMsg(
										"\tİşleminiz başarıyla gerçekleşti.\nSiparişiniz bu gün içinde yola çıkacaktır.");
								
								SendMail.sendEmail(customer);

								String query3 = "DELETE FROM getireczane.cart WHERE basketID='" + customer.getId()
										+ "'";
								if (!DBConnection.updateDBAndQuery(query3)) {
									Helper.showMsg("Sepetinizde güncelleme hatası!");
								}
								CustomerScreen cs = new CustomerScreen(customer);
								cs.setVisible(true);
								dispose();
							} else {
								System.out.println("Sipariş bilgisi oluşturulamadı hata alındı");
							}
						} else {
							Helper.showMsg("Sipariş işleminiz gerçekleştirilemiyor!");
							CustomerScreen cs = new CustomerScreen(customer);
							cs.setVisible(true);
							dispose();
						}
					}else {
						Helper.showMsg("eror");
						CustomerScreen cs = new CustomerScreen(customer);
						cs.setVisible(true);
						dispose();
					}
					 */
				}
			}
		});

		btnConfirm.setForeground(new Color(93, 62, 188));
		btnConfirm.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnConfirm.setBackground(new Color(255, 209, 14));
		btnConfirm.setBounds(109, 269, 150, 34);
		panel.add(btnConfirm);
		
		textSubTotal = new JTextField();
		textSubTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textSubTotal.setEditable(false);
		textSubTotal.setText(String.valueOf(Cart.calculateSubTotalCart(customer.getId())));
		textSubTotal.setBounds(176, 213, 116, 35);
		panel.add(textSubTotal);
		textSubTotal.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Toplam Tutar:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_1.setBounds(23, 210, 150, 38);
		panel.add(lblNewLabel_1_1);

	}

	public static Customer getCustomer() {
		return customer;
	}

	public static void setCustomer(Customer customer) {
		CreditCardScreen.customer = customer;
	}
}
