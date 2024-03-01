package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Admin;
import Model.Customer;

public class LoginScreen extends JFrame {

	
	private JPanel contentPane;
	private JPasswordField passCustomer;
	private JPasswordField passAdmin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() throws ParseException {
		setTitle("Getir Eczane Giriş");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 150, 454, 366);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbGetLog = new JLabel(new ImageIcon(getClass().getResource("getlogo.jpg")));
		lbGetLog.setBounds(65, 18, 289, 60);
		contentPane.add(lbGetLog);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(0, 0, 0));
		tabbedPane.setBounds(17, 82, 407, 217);
		contentPane.add(tabbedPane);

		JPanel pUser = new JPanel();
		pUser.setForeground(new Color(102, 0, 153));
		pUser.setBorder(UIManager.getBorder("CheckBoxMenuItem.border"));
		pUser.setBackground(new Color(93, 62, 188));
		tabbedPane.addTab("Kullanıcı", null, pUser, null);
		tabbedPane.setForegroundAt(0, new Color(0, 0, 0));
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		pUser.setLayout(null);

		JLabel lblNewLabel = new JLabel("T.C No:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(50, 11, 89, 38);
		pUser.add(lblNewLabel);

		MaskFormatter mftcuser = new MaskFormatter("###########");
		JFormattedTextField txtCustomerTc = new JFormattedTextField(mftcuser);
		txtCustomerTc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtCustomerTc.setBounds(143, 15, 183, 35);
		pUser.add(txtCustomerTc);

		JLabel lblNewLabel_1 = new JLabel("Şifre:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(71, 64, 70, 38);
		pUser.add(lblNewLabel_1);

		JButton btnUser = new JButton("Giriş Yap");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCustomerTc.getText().length() == 0 || passCustomer.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					ResultSet rs = DBConnection.connectDBAndQuery("SELECT * FROM getireczane.customer");
					boolean control = false;
					try {
						while (rs.next()) {
							if (txtCustomerTc.getText().equals(rs.getString("customerID"))
									&& passCustomer.getText().equals(rs.getString("cPass"))) {
								Customer c = new Customer();
								c.setName(rs.getString("cName"));
								c.setEmail(rs.getString("cEmail"));
								c.setId(rs.getString("customerID"));
								c.setPassword(rs.getString("cPass"));
								c.setAddress(rs.getString("cAddress"));
								c.setBirthDate("cBirtDate");
								c.setPhoneNumber("cTell");
								control = true;

								CustomerScreen cs = new CustomerScreen(c);
								cs.setVisible(true);
								dispose();

							}
						}

						if (control == false) {
							txtCustomerTc.setText(null);
							passCustomer.setText(null);
							Helper.showMsg("warning");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnUser.setForeground(new Color(93, 62, 188));
		btnUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnUser.setBackground(new Color(255, 209, 14));
		btnUser.setBounds(154, 112, 108, 34);
		pUser.add(btnUser);

		passCustomer = new JPasswordField();
		passCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		passCustomer.setBounds(144, 65, 183, 35);
		pUser.add(passCustomer);

		JLabel registerText = new JLabel("Kaydolmak için tıklayınız!");
		registerText.setBounds(71, 157, 244, 23);
		pUser.add(registerText);
		registerText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					RegisterScreen sgnup = new RegisterScreen();
					sgnup.setVisible(true);
					dispose();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		registerText.setForeground(new Color(255, 209, 14));
		registerText.setFont(new Font("Harrington", Font.BOLD, 20));

		JPanel pAdmin = new JPanel();
		pAdmin.setLayout(null);
		pAdmin.setForeground(new Color(255, 255, 0));
		pAdmin.setBorder(UIManager.getBorder("CheckBoxMenuItem.border"));
		pAdmin.setBackground(new Color(93, 62, 188));
		tabbedPane.addTab("Yönetici", null, pAdmin, null);
		tabbedPane.setBackgroundAt(1, new Color(255, 255, 255));
		tabbedPane.setForegroundAt(1, new Color(0, 0, 0));

		JLabel lblNewLabel_3 = new JLabel("T.C No:");
		lblNewLabel_3.setForeground(new Color(255, 209, 14));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_3.setBounds(50, 11, 89, 38);
		pAdmin.add(lblNewLabel_3);

		MaskFormatter mftcadmin = new MaskFormatter("###########");
		JFormattedTextField txtAdminTc = new JFormattedTextField(mftcadmin);
		txtAdminTc.setBackground(SystemColor.scrollbar);
		txtAdminTc.setForeground(SystemColor.desktop);
		txtAdminTc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtAdminTc.setBounds(143, 15, 183, 35);
		pAdmin.add(txtAdminTc);

		JLabel lblNewLabel_1_2 = new JLabel("Şifre:");
		lblNewLabel_1_2.setForeground(new Color(255, 209, 14));
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_2.setBounds(71, 64, 70, 38);
		pAdmin.add(lblNewLabel_1_2);

		JButton btnAdmin = new JButton("Yönetici olarak giriş yap");
		btnAdmin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (txtAdminTc.getText().length() == 0 || passAdmin.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					ResultSet rs = DBConnection.connectDBAndQuery("SELECT * FROM administrators");
					boolean control = false;
					try {
						while (rs.next()) {
							if (txtAdminTc.getText().equals(rs.getString("adminID"))
									&& passAdmin.getText().equals(rs.getString("adminPassword"))) {
								Admin admin = new Admin();
								admin.setName(rs.getString("adminName"));
								admin.setEmail(rs.getString("adminEmail"));
								admin.setId(rs.getString("adminID"));
								admin.setPassword(rs.getString("adminPassword"));
								control = true;

								try {
									AdminScreen as = new AdminScreen(admin);
									as.setVisible(true);
									dispose();
									rs.close();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}

							}
						}

						if (control == false) {
							txtAdminTc.setText(null);
							passAdmin.setText(null);
							Helper.showMsg("warning");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnAdmin.setForeground(new Color(93, 62, 188));
		btnAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAdmin.setBackground(new Color(255, 209, 14));
		btnAdmin.setBounds(100, 112, 206, 34);
		pAdmin.add(btnAdmin);

		passAdmin = new JPasswordField();
		passAdmin.setBackground(SystemColor.scrollbar);
		passAdmin.setForeground(SystemColor.desktop);
		passAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		passAdmin.setBounds(144, 65, 183, 35);
		pAdmin.add(passAdmin);
	}
}
