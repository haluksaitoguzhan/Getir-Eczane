package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Helper.DBConnection;
import Helper.Helper;
import Model.Admin;
import Model.BestSellers;
import Model.Cart;
import Model.Customer;
import Model.Prescription;

import javax.swing.JTabbedPane;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ListSelectionModel;
import javax.swing.Icon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;

public class CustomerScreen extends JFrame {
	private static Customer customer = new Customer();
	private JPanel contentPane;
	private JTextField txtCustomerName;
	private JTabbedPane tabbedPane;
	private JPanel pAddDrug;
	private JButton btnAddBasket;
	private JScrollPane scrollPane;
	private JButton btnkYap;
	private JTable table;
	private JLabel lblSemekIstediinizIlacn;
	private JPanel pCart;
	private JButton btnAddCart;
	private JLabel lblCart;
	private JTextField textSubTotalCart;
	private JButton btnDelCart;
	private JScrollPane scrollPaneCart;
	private JTextField textSearch;
	private JLabel lblNewLabel;
	private JTextField textdrugname;
	private JLabel lblIsim;
	private JTextField textdrugtotalcost;
	private JTextField textSearch2;
	private JLabel lblNewLabel_1;
	private JTextField textdrugunitcost;
	private JTable tableCart;
	private JLabel lblNewLabel_2;
	private JTextField txtDelet;
	private JLabel lbDruglogo;
	private JLabel lblIlaId;
	private JTextField textdrugid;
	private JPanel pShipped;
	private JScrollPane scrollPaneOrders;
	private JLabel lblS;
	private JTable tableOrders;
	private JTextField textOrderID;
	private JLabel lblOrderId;
	private JButton btnOrderDetail;
	private JButton btnSiperilerimiGster;
	private JPanel pPrescription;
	private JTextField textTotalPrescription;
	private JButton btnOrderPrescription;
	private JLabel lblCart_1;
	private JButton btnSearchPrescription;
	private JLabel lblokSatanlar;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane_2;
	private JTable tablePrescription;
	private JLabel lblPrescriptionTC;
	private JLabel lblPrescriptionName;
	private JTextField txtPrescriptionName;
	private JTextField txtPrescriptionTC;
	private MaskFormatter mfp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerScreen frame = new CustomerScreen(customer);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CustomerScreen(Customer customer) {
		setTitle("Getir Eczane Müşteri");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(340, 90, 656, 601);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbmingetlog = new JLabel(new ImageIcon(getClass().getResource("mingetlogo.jpg")));
		lbmingetlog.setBounds(413, 518, 227, 44);
		contentPane.add(lbmingetlog);

		JLabel lbCustomerlogo = new JLabel(new ImageIcon(getClass().getResource("cuslogo1.png")));
		lbCustomerlogo.setBounds(3, 2, 134, 129);
		contentPane.add(lbCustomerlogo);

		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(135, 37, 495, 44);
		txtCustomerName.setEditable(false);
		txtCustomerName.setBackground(new Color(93, 62, 188));
		txtCustomerName.setForeground(new Color(255, 209, 14));
		txtCustomerName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		txtCustomerName.setHorizontalAlignment(SwingConstants.LEFT);
		txtCustomerName.setText("Kullanıcı: " + customer.getName());
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(13, 129, 617, 387);
		contentPane.add(tabbedPane);

		btnkYap = new JButton("Çıkış Yap");
		btnkYap.setBounds(14, 524, 111, 29);
		btnkYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("exit")) {
					try {
						Helper.createToastMessage("Çıkış Yapılıyor", 600, 500);
						dispose();
						LoginScreen lgn = new LoginScreen();
						lgn.setVisible(true);

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnkYap.setForeground(new Color(93, 62, 188));
		btnkYap.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnkYap.setBackground(new Color(255, 209, 14));
		contentPane.add(btnkYap);

		pAddDrug = new JPanel();
		pAddDrug.setLayout(null);
		pAddDrug.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("İlaçlar", null, pAddDrug, null);

		JSpinner spin = new JSpinner();
		spin.setEnabled(false);

		spin.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		spin.setBounds(542, 213, 42, 27);
		pAddDrug.add(spin);

		textdrugtotalcost = new JTextField();
		textdrugtotalcost.setEditable(false);
		textdrugtotalcost.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textdrugtotalcost.setColumns(10);
		textdrugtotalcost.setBounds(442, 277, 164, 27);
		pAddDrug.add(textdrugtotalcost);

		btnAddBasket = new JButton("Sepete Ekle");
		btnAddBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textdrugname.getText().length() == 0 || textdrugtotalcost.getText().length() == 0) {
					Helper.showMsg("Lutfen ilaç seçiniz!");
				} else {

					int drugID = Integer.parseInt(textdrugid.getText());
					int drugAmount = (int) spin.getValue();

					if (Admin.controlStock(drugID) >= drugAmount) {
						String idBasket = customer.getId();
						String drugName = textdrugname.getText().toString();
						float drugUnitCost = Float.parseFloat(textdrugunitcost.getText());
						float drugTotalCost = Float.parseFloat(textdrugtotalcost.getText());
						Date now = new Date();
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						String cAddedDate = df.format(now);
						String query = "INSERT INTO cart (idbasket, basketID, cName, cUnitCost, cTotalCost, cAmount, cAddedDate)"
								+ " VALUES ('" + drugID + "','" + idBasket + "', '" + drugName + "', '" + drugUnitCost
								+ "', '" + drugTotalCost + "', '" + drugAmount + "', '" + cAddedDate + "')";
						if (DBConnection.controlUniqeCart(idBasket, drugID)) {
							if (DBConnection.updateDBAndQuery(query)) {
								Helper.showMsg("Sepete başarıyla eklendi");
								spin.setValue(1);
								spin.setEnabled(false);
								textdrugid.setText(null);
								textdrugunitcost.setText(null);
								textdrugname.setText(null);
								textdrugtotalcost.setText(null);
								textSubTotalCart.setText(String.valueOf(Cart.calculateSubTotalCart(customer.getId())));
								// Admin.connectDBAndDisplayDrugs("basket", tableCart);
								Admin.connectDBAndDisplayCustomerCart(idBasket, tableCart);
							}
						} else {
							Helper.showMsg(
									"Aynı ilaçtan iki defa sipariş edemezsiniz!\n\t Lütfen sepetinizi güncelleyiniz.");
						}
					} else {
						Helper.showMsg("Yeterli stok bulunamadı!");
					}
				}
			}
		});
		btnAddBasket.setForeground(new Color(93, 62, 188));
		btnAddBasket.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAddBasket.setBackground(new Color(255, 209, 14));
		btnAddBasket.setBounds(462, 315, 128, 27);
		pAddDrug.add(btnAddBasket);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 421, 279);
		pAddDrug.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		Admin.connectDBAndDisplayDrugs("customer", table);

		lblSemekIstediinizIlacn = new JLabel("Seçmek istediginiz ilacın üstüne tıklayınız!");
		lblSemekIstediinizIlacn.setForeground(SystemColor.desktop);
		lblSemekIstediinizIlacn.setFont(new Font("Harrington", Font.BOLD, 16));
		lblSemekIstediinizIlacn.setBounds(52, 330, 327, 23);
		pAddDrug.add(lblSemekIstediinizIlacn);

		lblNewLabel = new JLabel("İlaç Adı:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(442, 84, 89, 27);
		pAddDrug.add(lblNewLabel);

		textdrugname = new JTextField();
		textdrugname.setEditable(false);
		textdrugname.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textdrugname.setColumns(10);
		textdrugname.setBounds(442, 109, 164, 27);
		pAddDrug.add(textdrugname);

		lblIsim = new JLabel("Toplam Fiyat:");
		lblIsim.setForeground(Color.WHITE);
		lblIsim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblIsim.setBounds(441, 251, 119, 27);
		pAddDrug.add(lblIsim);

		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = textSearch.getText();
				String query = "SELECT idDrugs,dName,dCost FROM getireczane.drugs WHERE dName like '" + search + "%'";
				DBConnection.displaySeraching(query, table);
			}
		});

		textSearch.setBounds(41, 11, 164, 27);
		pAddDrug.add(textSearch);
		textSearch.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textSearch.setColumns(10);

		JLabel lbSearchlogo = new JLabel(new ImageIcon(getClass().getResource("aramalogo2.png")));
		lbSearchlogo.setBounds(10, 11, 30, 29);
		pAddDrug.add(lbSearchlogo);

		JLabel lblIlaAdedi = new JLabel("İlaç Adedi:");
		lblIlaAdedi.setForeground(Color.WHITE);
		lblIlaAdedi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblIlaAdedi.setBounds(442, 215, 94, 27);
		pAddDrug.add(lblIlaAdedi);

		lblNewLabel_1 = new JLabel("Birim Fiyatı:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(441, 150, 119, 27);
		pAddDrug.add(lblNewLabel_1);

		textdrugunitcost = new JTextField();
		textdrugunitcost.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textdrugunitcost.setEditable(false);
		textdrugunitcost.setColumns(10);
		textdrugunitcost.setBounds(441, 175, 164, 27);
		pAddDrug.add(textdrugunitcost);

		lblIlaId = new JLabel("İlaç ID:");
		lblIlaId.setForeground(Color.WHITE);
		lblIlaId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblIlaId.setBounds(442, 21, 89, 27);
		pAddDrug.add(lblIlaId);

		textdrugid = new JTextField();
		textdrugid.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textdrugid.setEditable(false);
		textdrugid.setColumns(10);
		textdrugid.setBounds(442, 46, 164, 27);
		pAddDrug.add(textdrugid);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					spin.setEnabled(true);
					spin.setValue(1);
					textdrugid.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					textdrugname.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					textdrugunitcost.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					textdrugtotalcost.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				} catch (Exception e2) {
				}
			}
		});

		spin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int drugId = Integer.parseInt(textdrugid.getText());
				int amount = Integer.parseInt(spin.getValue().toString());
				// System.out.println("yer cs spin: "+Admin.controlCatalog(drugId));
				if (Admin.controlStock(drugId) >= amount) {
					float cost = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString())
							* Integer.parseInt(spin.getValue().toString());
					textdrugtotalcost.setText(String.valueOf(cost));
				} else {
					spin.setValue(amount - 1);
					Helper.showMsg("Daha fazla alamazsınız!\nYeterli stoğumuz kalmadı.");
				}
			}
		});

		pPrescription = new JPanel();
		pPrescription.setLayout(null);
		pPrescription.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("Reçete İle Al", null, pPrescription, null);

		textTotalPrescription = new JTextField();
		textTotalPrescription.setText("0.0");
		textTotalPrescription.setHorizontalAlignment(SwingConstants.CENTER);
		textTotalPrescription.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textTotalPrescription.setEditable(false);
		textTotalPrescription.setColumns(10);
		textTotalPrescription.setBounds(374, 326, 103, 27);
		pPrescription.add(textTotalPrescription);

		btnOrderPrescription = new JButton("Sipariş Ver");
		btnOrderPrescription.setVisible(false);
		btnOrderPrescription.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PaymentScreen ps = new PaymentScreen(customer);
				ps.setVisible(true);
				dispose();
			}
		});
		btnOrderPrescription.setForeground(new Color(93, 62, 188));
		btnOrderPrescription.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnOrderPrescription.setBackground(new Color(255, 209, 14));
		btnOrderPrescription.setBounds(487, 327, 118, 27);
		pPrescription.add(btnOrderPrescription);

		lblCart_1 = new JLabel("Toplam:");
		lblCart_1.setForeground(Color.WHITE);
		lblCart_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblCart_1.setBounds(295, 326, 75, 27);
		pPrescription.add(lblCart_1);
		
		try {
			mfp = new MaskFormatter("U##U###");
		} catch (ParseException e1) {
		}
		
		JFormattedTextField formattedPrescription = new JFormattedTextField(mfp);
		formattedPrescription.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		formattedPrescription.setBounds(436, 46, 96, 28);
		pPrescription.add(formattedPrescription);

		btnSearchPrescription = new JButton("Ara");
		btnSearchPrescription.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String code = formattedPrescription.getText().toString();
				if (Prescription.displayPrescription(tablePrescription, code, txtPrescriptionName, txtPrescriptionTC)) {
					lblPrescriptionTC.setVisible(true);
					lblPrescriptionName.setVisible(true);
					txtPrescriptionTC.setVisible(true);
					txtPrescriptionName.setVisible(true);
					textTotalPrescription.setText(String.valueOf(Prescription.calculateTotalPrescription(code)));
					btnOrderPrescription.setVisible(true);
				} else {
					System.out.println("reçete kod işleminde hata yer CustomemerScreen btnSearchPrescription");
				}

			}
		});
		btnSearchPrescription.setForeground(new Color(93, 62, 188));
		btnSearchPrescription.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSearchPrescription.setBackground(new Color(255, 209, 14));
		btnSearchPrescription.setBounds(538, 46, 64, 27);
		pPrescription.add(btnSearchPrescription);

		lblokSatanlar = new JLabel("Reçete  kodunu  gir  ilaçların  kapına  gelsinnn...");
		lblokSatanlar.setHorizontalAlignment(SwingConstants.CENTER);
		lblokSatanlar.setForeground(Color.BLACK);
		lblokSatanlar.setFont(new Font("Harrington", Font.BOLD, 24));
		lblokSatanlar.setBounds(10, 0, 595, 47);
		pPrescription.add(lblokSatanlar);

		lblNewLabel_3 = new JLabel("Kod:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(393, 45, 40, 27);
		pPrescription.add(lblNewLabel_3);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 83, 592, 232);
		pPrescription.add(scrollPane_2);

		tablePrescription = new JTable();
		tablePrescription.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(tablePrescription);

		lblPrescriptionTC = new JLabel("T.C.");
		lblPrescriptionTC.setVisible(false);
		lblPrescriptionTC.setForeground(Color.WHITE);
		lblPrescriptionTC.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblPrescriptionTC.setBounds(10, 46, 46, 27);
		pPrescription.add(lblPrescriptionTC);

		lblPrescriptionName = new JLabel("ADI:");
		lblPrescriptionName.setVisible(false);
		lblPrescriptionName.setForeground(Color.WHITE);
		lblPrescriptionName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblPrescriptionName.setBounds(175, 45, 44, 27);
		pPrescription.add(lblPrescriptionName);

		txtPrescriptionName = new JTextField();
		txtPrescriptionName.setVisible(false);
		txtPrescriptionName.setEditable(false);
		txtPrescriptionName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtPrescriptionName.setBounds(221, 44, 164, 27);
		pPrescription.add(txtPrescriptionName);
		txtPrescriptionName.setColumns(10);

		txtPrescriptionTC = new JTextField();
		txtPrescriptionTC.setVisible(false);
		txtPrescriptionTC.setEditable(false);
		txtPrescriptionTC.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtPrescriptionTC.setBounds(47, 46, 118, 27);
		pPrescription.add(txtPrescriptionTC);
		txtPrescriptionTC.setColumns(10);

		pCart = new JPanel();
		pCart.setLayout(null);
		pCart.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("Sepetim", null, pCart, null);

		scrollPaneCart = new JScrollPane();
		scrollPaneCart.setBounds(10, 44, 421, 279);
		pCart.add(scrollPaneCart);

		tableCart = new JTable();
		tableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneCart.setViewportView(tableCart);

		Admin.connectDBAndDisplayCustomerCart(customer.getId(), tableCart);

		textSubTotalCart = new JTextField();
		textSubTotalCart.setHorizontalAlignment(SwingConstants.CENTER);
		textSubTotalCart.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textSubTotalCart.setEditable(false);
		textSubTotalCart.setColumns(10);
		textSubTotalCart.setBounds(326, 326, 103, 27);
		pCart.add(textSubTotalCart);
		textSubTotalCart.setText(String.valueOf(Cart.calculateSubTotalCart(customer.getId())));

		tableCart.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					spin.setEnabled(true);
					txtDelet.setText(tableCart.getValueAt(tableCart.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}
			}
		});

		btnAddCart = new JButton("Sipariş Ver");
		btnAddCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Admin.controlAllStock(customer.getId())) {
					if (tableCart.getRowCount() != 0) {
						PaymentScreen ps = new PaymentScreen(customer);
						ps.setVisible(true);
						dispose();
					} else {
						Helper.showMsg("Sepetinizde hiç ilaç yok!");
					}
				} else {
					Helper.showMsg("Yetersiz stok nedeniyle işlem başarısız!");
				}
			}
		});

		btnAddCart.setForeground(new Color(93, 62, 188));
		btnAddCart.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAddCart.setBackground(new Color(255, 209, 14));
		btnAddCart.setBounds(468, 327, 118, 27);
		pCart.add(btnAddCart);

		lblCart = new JLabel("Toplam:");
		lblCart.setForeground(Color.WHITE);
		lblCart.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblCart.setBounds(251, 326, 75, 27);
		pCart.add(lblCart);

		btnDelCart = new JButton("Sil");
		btnDelCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDelet.getText().length() == 0) {
					Helper.showMsg("Lütfen silmek istediğiniz ilacı seçiniz!");
				} else {
					if (Helper.confirm("sure")) {
						int id = Integer.parseInt(txtDelet.getText());
						String query = "DELETE FROM cart WHERE idbasket='" + id + "'";
						if (DBConnection.updateDBAndQuery(query)) {
							txtDelet.setText(null);
							Admin.connectDBAndDisplayCustomerCart(customer.getId(), tableCart);
							textSubTotalCart.setText(String.valueOf(Cart.calculateSubTotalCart(customer.getId())));
						} else {
							Helper.showMsg("Silme işlemi başarısız!");
							txtDelet.setText(null);
						}
					}
				}
			}
		});

		btnDelCart.setForeground(new Color(93, 62, 188));
		btnDelCart.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelCart.setBackground(new Color(255, 209, 14));
		btnDelCart.setBounds(475, 104, 89, 27);
		pCart.add(btnDelCart);

		JLabel lblSepetim = new JLabel("Sepetim");
		lblSepetim.setForeground(Color.BLACK);
		lblSepetim.setFont(new Font("Harrington", Font.BOLD, 24));
		lblSepetim.setBounds(172, 5, 96, 33);
		pCart.add(lblSepetim);

		lblNewLabel_2 = new JLabel("Seçilen İlaç ID:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(445, 41, 141, 27);
		pCart.add(lblNewLabel_2);

		txtDelet = new JTextField();
		txtDelet.setHorizontalAlignment(SwingConstants.CENTER);
		txtDelet.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtDelet.setEditable(false);
		txtDelet.setColumns(10);
		txtDelet.setBounds(441, 66, 164, 27);
		pCart.add(txtDelet);

		lbDruglogo = new JLabel(new ImageIcon(getClass().getResource("ilaclogo.jpg")));
		lbDruglogo.setBounds(441, 152, 164, 154);
		pCart.add(lbDruglogo);

		pShipped = new JPanel();
		pShipped.setLayout(null);
		pShipped.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("Siparişlerim", null, pShipped, null);

		scrollPaneOrders = new JScrollPane();
		scrollPaneOrders.setBounds(10, 44, 592, 274);
		pShipped.add(scrollPaneOrders);

		tableOrders = new JTable();
		tableOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneOrders.setViewportView(tableOrders);

		textOrderID = new JTextField();
		textOrderID.setHorizontalAlignment(SwingConstants.CENTER);
		textOrderID.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textOrderID.setEditable(false);
		textOrderID.setColumns(10);
		textOrderID.setBounds(54, 322, 84, 27);
		pShipped.add(textOrderID);

		btnSiperilerimiGster = new JButton("Siperişlerimi göster");
		btnSiperilerimiGster.setForeground(new Color(93, 62, 188));
		btnSiperilerimiGster.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSiperilerimiGster.setBackground(new Color(255, 209, 14));
		btnSiperilerimiGster.setBounds(20, 322, 231, 27);
		pShipped.add(btnSiperilerimiGster);

		btnSiperilerimiGster.setVisible(false);

		Admin.connectDBAndDisplayCustomerOrders(customer.getId(), tableOrders);

		tableOrders.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					String id = tableOrders.getValueAt(tableOrders.getSelectedRow(), 0).toString();
					textOrderID.setText(id);
				} catch (Exception e2) {
				}
			}
		});

		lblS = new JLabel("Siparislerim");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setForeground(Color.BLACK);
		lblS.setFont(new Font("Harrington", Font.BOLD, 24));
		lblS.setBounds(212, 1, 158, 43);
		pShipped.add(lblS);

		JLabel lblSipariDetaynGrmek = new JLabel("Siparis detayını görmek için üstüne tıklayınız!");
		lblSipariDetaynGrmek.setHorizontalAlignment(SwingConstants.CENTER);
		lblSipariDetaynGrmek.setForeground(Color.BLACK);
		lblSipariDetaynGrmek.setFont(new Font("Harrington", Font.BOLD, 16));
		lblSipariDetaynGrmek.setBounds(248, 322, 354, 27);
		pShipped.add(lblSipariDetaynGrmek);

		lblOrderId = new JLabel("ID:");
		lblOrderId.setForeground(Color.WHITE);
		lblOrderId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblOrderId.setBounds(20, 322, 39, 27);
		pShipped.add(lblOrderId);

		btnOrderDetail = new JButton("Detaya git");
		btnOrderDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textOrderID.getText().length() == 0) {
					Helper.showMsg("Lütfen sipariş detaynını tablodan seçiniz!");
				} else {
					btnSiperilerimiGster.setVisible(true);
					Admin.connectDBAndDisplayCustomerOrderDetails(textOrderID.getText().toString(), tableOrders);
					btnOrderDetail.setVisible(false);
					textOrderID.setVisible(false);
					lblOrderId.setVisible(false);
				}
			}
		});

		btnOrderDetail.setForeground(new Color(93, 62, 188));
		btnOrderDetail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnOrderDetail.setBackground(new Color(255, 209, 14));
		btnOrderDetail.setBounds(148, 323, 103, 27);
		pShipped.add(btnOrderDetail);

		btnSiperilerimiGster.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin.connectDBAndDisplayCustomerOrders(customer.getId(), tableOrders);
				btnSiperilerimiGster.setVisible(false);
				btnOrderDetail.setVisible(true);
				textOrderID.setVisible(true);
				lblOrderId.setVisible(true);
			}
		});
	}
}
