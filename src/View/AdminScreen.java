package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Model.Admin;
import Model.BestSellers;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Helper.*;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;

public class AdminScreen extends JFrame {
	private JPanel contentPane;
	private JTextField txtAdminName;
	private static Admin admin = new Admin();
	private JTextField tdrugcost;
	private JTextField tdrugid;
	private JTextField tdrugstock;
	private JTable table;
	private JTextField tdrugname;
	private JTable tableAdmin;
	private JTextField textAdminName;
	private JTextField textAdminPass;
	private JTextField textAdminMail;
	private JTextField textFilter;
	private JTable tableBestSellers;
	// private JPopupMenu drugMenuTableSelection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					AdminScreen frame = new AdminScreen(admin);
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
	public AdminScreen(Admin admin) throws ParseException {
		setTitle("Getir Eczane Yönetim");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(340, 90, 656, 601);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbmingetlog = new JLabel(new ImageIcon(getClass().getResource("mingetlogo.jpg")));
		lbmingetlog.setBounds(403, 507, 227, 44);
		contentPane.add(lbmingetlog);

		txtAdminName = new JTextField();
		txtAdminName.setEditable(false);
		txtAdminName.setBounds(10, 33, 485, 44);
		txtAdminName.setBackground(new Color(93, 62, 188));
		txtAdminName.setForeground(new Color(255, 209, 14));
		txtAdminName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		txtAdminName.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdminName.setText("Yönetici: " + admin.getName());
		contentPane.add(txtAdminName);
		txtAdminName.setColumns(10);

		JLabel lbAdminlogo = new JLabel(new ImageIcon(getClass().getResource("adminlogo.png")));
		lbAdminlogo.setBounds(519, 11, 91, 105);
		contentPane.add(lbAdminlogo);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 119, 600, 387);
		contentPane.add(tabbedPane);

		JPanel pAddDrug = new JPanel();
		pAddDrug.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("İlaç ekle", null, pAddDrug, null);
		pAddDrug.setLayout(null);

		JLabel lblNewLabel = new JLabel("İlaç Adı:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(18, 16, 89, 27);
		pAddDrug.add(lblNewLabel);

		JButton btnAdd = new JButton("Ekle");

		btnAdd.setForeground(new Color(93, 62, 188));
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAdd.setBackground(new Color(255, 209, 14));
		btnAdd.setBounds(46, 208, 89, 27);
		pAddDrug.add(btnAdd);

		tdrugcost = new JTextField();
		tdrugcost.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		tdrugcost.setColumns(10);
		tdrugcost.setBounds(17, 106, 164, 27);
		pAddDrug.add(tdrugcost);

		JLabel lblIsim = new JLabel("Ücreti:");
		lblIsim.setForeground(Color.WHITE);
		lblIsim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblIsim.setBounds(17, 77, 72, 38);
		pAddDrug.add(lblIsim);

		JLabel lblKullancId = new JLabel("İlaç ID:");
		lblKullancId.setForeground(Color.WHITE);
		lblKullancId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblKullancId.setBounds(17, 259, 133, 27);
		pAddDrug.add(lblKullancId);

		tdrugid = new JTextField();
		tdrugid.setHorizontalAlignment(SwingConstants.CENTER);
		tdrugid.setEditable(false);
		tdrugid.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		tdrugid.setColumns(10);
		tdrugid.setBounds(17, 286, 164, 27);
		pAddDrug.add(tdrugid);

		JButton btnDel = new JButton("Sil");

		btnDel.setForeground(new Color(93, 62, 188));
		btnDel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDel.setBackground(new Color(255, 209, 14));
		btnDel.setBounds(46, 321, 89, 27);
		pAddDrug.add(btnDel);

		JLabel lblNewLabel_1_1 = new JLabel("Stok Adedi:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1_1.setBounds(20, 144, 115, 24);
		pAddDrug.add(lblNewLabel_1_1);

		tdrugstock = new JTextField();
		tdrugstock.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		tdrugstock.setColumns(10);
		tdrugstock.setBounds(17, 170, 164, 27);
		pAddDrug.add(tdrugstock);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(197, 22, 377, 324);
		pAddDrug.add(scrollPane);

		/*
		 * drugMenuTableSelection = new JPopupMenu(); JMenuItem updateMenu = new
		 * JMenuItem("Güncelle"); JMenuItem deleteMenu = new JMenuItem("Sil");
		 * drugMenuTableSelection.add(updateMenu);
		 * drugMenuTableSelection.add(deleteMenu);
		 */

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// table.setComponentPopupMenu(drugMenuTableSelection);
		scrollPane.setViewportView(table);

		tdrugname = new JTextField();
		tdrugname.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		tdrugname.setColumns(10);
		tdrugname.setBounds(18, 41, 164, 27);
		pAddDrug.add(tdrugname);

		Admin.connectDBAndDisplayDrugs("admin", table);

		JSeparator separator = new JSeparator();
		separator.setBounds(14, 249, 163, 2);
		pAddDrug.add(separator);

		JPanel pAddDrug_1 = new JPanel();
		pAddDrug_1.setLayout(null);
		pAddDrug_1.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("Tüm yöneticileri gör", null, pAddDrug_1, null);

		JLabel lblAdminTC = new JLabel("TC No:");
		lblAdminTC.setForeground(Color.WHITE);
		lblAdminTC.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblAdminTC.setBounds(17, 234, 104, 27);
		pAddDrug_1.add(lblAdminTC);

		JButton btnDelAdmin = new JButton("Sil");
		btnDelAdmin.setVisible(false);
		btnDelAdmin.setForeground(new Color(93, 62, 188));
		btnDelAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelAdmin.setBackground(new Color(255, 209, 14));
		btnDelAdmin.setBounds(457, 321, 128, 27);
		pAddDrug_1.add(btnDelAdmin);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(17, 22, 568, 201);
		pAddDrug_1.add(scrollPane_1);

		tableAdmin = new JTable();
		tableAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableAdmin);

		Admin.connectDBAndDisplayAdmins(tableAdmin);
		
		MaskFormatter mftc = new MaskFormatter("###########");
		JFormattedTextField formattedAdminTc = new JFormattedTextField(mftc);
		formattedAdminTc.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		formattedAdminTc.setBounds(17, 261, 210, 27);
		pAddDrug_1.add(formattedAdminTc);

		JLabel lblAdminName = new JLabel("Ad Soyad:");
		lblAdminName.setForeground(Color.WHITE);
		lblAdminName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblAdminName.setBounds(240, 234, 105, 27);
		pAddDrug_1.add(lblAdminName);

		textAdminName = new JTextField();
		textAdminName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textAdminName.setColumns(10);
		textAdminName.setBounds(237, 261, 210, 27);
		pAddDrug_1.add(textAdminName);

		JLabel lblAdminPass = new JLabel("Şifre:");
		lblAdminPass.setForeground(Color.WHITE);
		lblAdminPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblAdminPass.setBounds(240, 295, 114, 24);
		pAddDrug_1.add(lblAdminPass);

		textAdminPass = new JTextField();
		textAdminPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textAdminPass.setColumns(10);
		textAdminPass.setBounds(237, 321, 210, 27);
		pAddDrug_1.add(textAdminPass);

		JButton btnAddAdmin = new JButton("Ekle");
		btnAddAdmin.setForeground(new Color(93, 62, 188));
		btnAddAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAddAdmin.setBackground(new Color(255, 209, 14));
		btnAddAdmin.setBounds(457, 288, 128, 27);
		pAddDrug_1.add(btnAddAdmin);
		
		JLabel lblAdminMail = new JLabel("Email:");
		lblAdminMail.setForeground(Color.WHITE);
		lblAdminMail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblAdminMail.setBounds(17, 295, 105, 24);
		pAddDrug_1.add(lblAdminMail);
		
		textAdminMail = new JTextField();
		textAdminMail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textAdminMail.setColumns(10);
		textAdminMail.setBounds(17, 321, 210, 27);
		pAddDrug_1.add(textAdminMail);
		
		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.setVisible(false);
		btnTemizle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				formattedAdminTc.setText(null);
				textAdminName.setText(null);
				textAdminMail.setText(null);
				textAdminPass.setText(null);
				btnTemizle.setVisible(false);
			}
		});
		btnTemizle.setForeground(new Color(93, 62, 188));
		btnTemizle.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnTemizle.setBackground(new Color(255, 209, 14));
		btnTemizle.setBounds(457, 255, 128, 27);
		pAddDrug_1.add(btnTemizle);
		
		JPanel pBestSelers = new JPanel();
		pBestSelers.setLayout(null);
		pBestSelers.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("Çok satanlar", null, pBestSelers, null);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(10, 44, 575, 271);
		pBestSelers.add(scrollPane_1_1);
		
		textFilter = new JTextField();
		textFilter.setText("0");
		textFilter.setHorizontalAlignment(SwingConstants.CENTER);
		textFilter.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textFilter.setColumns(10);
		textFilter.setBounds(85, 326, 103, 27);
		pBestSelers.add(textFilter);
		
		tableBestSellers = new JTable();
		tableBestSellers.setEnabled(false);
		scrollPane_1_1.setViewportView(tableBestSellers);
		
		BestSellers.displayBestSellers(tableBestSellers, Integer.parseInt(textFilter.getText()));
		
		JButton btnFilter = new JButton("Filtrele");
		btnFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BestSellers.displayBestSellers(tableBestSellers, Integer.parseInt(textFilter.getText()));
			}
		});
		btnFilter.setForeground(new Color(93, 62, 188));
		btnFilter.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnFilter.setBackground(new Color(255, 209, 14));
		btnFilter.setBounds(200, 326, 103, 27);
		pBestSelers.add(btnFilter);
		
		JLabel lblokSatanlar_1 = new JLabel("Çok  Satanlar");
		lblokSatanlar_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblokSatanlar_1.setForeground(Color.BLACK);
		lblokSatanlar_1.setFont(new Font("Harrington", Font.BOLD, 24));
		lblokSatanlar_1.setBounds(130, 0, 327, 43);
		pBestSelers.add(lblokSatanlar_1);
		
		JLabel lblFiltrele = new JLabel("Filtrele:");
		lblFiltrele.setForeground(Color.WHITE);
		lblFiltrele.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblFiltrele.setBounds(10, 326, 75, 27);
		pBestSelers.add(lblFiltrele);

		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tdrugid.getText().length() == 0) {
					Helper.showMsg("Lütfen silmek istediğiniz ilacı seçiniz!");
				} else {
					if (Helper.confirm("sure")) {
						int id = Integer.parseInt(tdrugid.getText());
						String query = "DELETE FROM drugs WHERE idDrugs='" + id + "'";
						String query1 = "DELETE FROM cart WHERE idbasket='" + id + "'";
						if (DBConnection.updateDBAndQuery(query) && DBConnection.updateDBAndQuery(query1)) {
							Helper.showMsg("Silme işlemi başarıyla gerçekleşti");
							tdrugid.setText(null);
							Admin.connectDBAndDisplayDrugs("admin", table);
						}
					}
				}

				/*
				 * String query = "DELETE FROM drugs WHERE idDrugs='" + id + "'";
				 * DBConnection.connectDBAndQuery("update", query);
				 */
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tdrugname.getText().length() == 0 || tdrugcost.getText().length() == 0
						|| tdrugstock.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					String name = tdrugname.getText();
					float cost = Float.parseFloat(tdrugcost.getText());
					int stock = Integer.parseInt(tdrugstock.getText());
					String query = "INSERT INTO drugs (dName, dCost, dStock,dSales)" + " VALUES ('" + name + "', '" + cost
							+ "', '" + stock + "', '" + 0 + "')";
					if (DBConnection.updateDBAndQuery(query)) {
						Helper.showMsg("Ekleme işlemi başarılı");
						tdrugname.setText(null);
						tdrugcost.setText(null);
						tdrugstock.setText(null);
						Admin.connectDBAndDisplayDrugs("admin", table);
					}

				}

				/*
				 * String query = "INSERT INTO drugs (dName, dCost, dStock)" + " VALUES ('" +
				 * name + "', '" + cost + "', '" + stock + "')";
				 * DBConnection.connectDBAndQuery("update", query);
				 */
			}
		});

		JButton btnkYap = new JButton("Çıkış Yap");
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
		btnkYap.setBounds(21, 517, 111, 29);
		contentPane.add(btnkYap);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					tdrugid.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}
			}
		});

		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				try {
					if (e.getType() == TableModelEvent.UPDATE) {
						if (Helper.confirm("changing")) {
							int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
							String name = table.getValueAt(table.getSelectedRow(), 1).toString();
							float cost = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString());
							int stock = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
							String query = "UPDATE drugs SET dName='" + name + "', dCost='" + cost + "', dStock = '"
									+ stock + "' WHERE idDrugs='" + id + "'";
							if (DBConnection.updateDBAndQuery(query) == false) {
								Helper.showMsg("Böyle bir ID zaten var");
								Admin.connectDBAndDisplayDrugs("admin", table);
							}
						} else {
							Admin.connectDBAndDisplayDrugs("admin", table);
						}
					}
				} catch (Exception e2) {
					Helper.showMsg("eror");
				}
			}
		});

		tableAdmin.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					btnTemizle.setVisible(true);
					btnDelAdmin.setVisible(true);
					formattedAdminTc.setText(tableAdmin.getValueAt(tableAdmin.getSelectedRow(), 0).toString());
					textAdminName.setText(tableAdmin.getValueAt(tableAdmin.getSelectedRow(), 1).toString());
					textAdminMail.setText(tableAdmin.getValueAt(tableAdmin.getSelectedRow(), 2).toString());
					textAdminPass.setText(tableAdmin.getValueAt(tableAdmin.getSelectedRow(), 3).toString());
				} catch (Exception e2) {
				}
			}
		});

		btnAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (formattedAdminTc.getText().length() == 0 || textAdminName.getText().length() == 0
						|| textAdminPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					String id = formattedAdminTc.getText();
					String name = textAdminName.getText();
					String email = textAdminMail.getText();
					String pass = textAdminPass.getText();
					String query = "INSERT INTO getireczane.administrators (adminID, adminName,adminEmail, adminPassword)"
							+ " VALUES ('" + id + "', '" + name + "', '" + email + "', '" + pass + "')";
					if (DBConnection.updateDBAndQuery(query)) {
						Helper.showMsg("Yönetici ekleme işlemi başarılı");
						formattedAdminTc.setText(null);
						textAdminName.setText(null);
						textAdminMail.setText(null);
						textAdminPass.setText(null);
						Admin.connectDBAndDisplayAdmins(tableAdmin);
					}else {
						Helper.showMsg("Böyle bir yönetici zaten var");
					}
				}
			}
		});

		btnDelAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//12345678910
				if (formattedAdminTc.getText().length() == 0) {
					Helper.showMsg("Lütfen silmek istediğiniz yönetici seçiniz!");
				} else {
					if(!formattedAdminTc.getText().equals("12345678910" ) && !formattedAdminTc.getText().equals("98765432101")) {
						if (Helper.confirm("sure")) {
							String id = formattedAdminTc.getText().toString();
							String query = "DELETE FROM getireczane.administrators WHERE adminID='" + id + "'";
							
							if (DBConnection.updateDBAndQuery(query)) {
								Helper.showMsg("Silme işlemi başarıyla gerçekleşti");
								btnTemizle.setVisible(false);
								btnDelAdmin.setVisible(false);
								formattedAdminTc.setText(null);
								textAdminName.setText(null);
								textAdminMail.setText(null);
								textAdminPass.setText(null);
								Admin.connectDBAndDisplayAdmins(tableAdmin);
							}
						}
					}else {
						Helper.showMsg("Bu yönetici silinemez");
						btnTemizle.setVisible(false);
						btnDelAdmin.setVisible(false);
					}
				}
			}
		});
		
		formattedAdminTc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnTemizle.setVisible(true);
			}
		});
		
		textAdminName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnTemizle.setVisible(true);
			}
		});
		
		textAdminMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnTemizle.setVisible(true);
			}
		});
		
		textAdminPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnTemizle.setVisible(true);
			}
		});
		
		
	}
}
