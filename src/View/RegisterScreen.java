package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Helper.DBConnection;
import Helper.Helper;
import Model.Customer;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField passCustomer;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
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
	public RegisterScreen() throws ParseException {
		setTitle("Getir Eczane Kaydol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 70, 450, 630);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbGetLog = new JLabel(new ImageIcon(getClass().getResource("getlogo.jpg")));
		lbGetLog.setBounds(65, 20, 289, 60);
		contentPane.add(lbGetLog);

		JLabel lblNewLabel = new JLabel("T.C No:");
		lblNewLabel.setBounds(94, 91, 89, 38);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(lblNewLabel);

		JLabel lblTelNo = new JLabel("Tel No:");
		lblTelNo.setBounds(97, 186, 89, 38);
		lblTelNo.setForeground(Color.WHITE);
		lblTelNo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(lblTelNo);

		JLabel lblAdres = new JLabel("Adres:");
		lblAdres.setBounds(112, 378, 71, 38);
		lblAdres.setForeground(Color.WHITE);
		lblAdres.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(lblAdres);

		MaskFormatter mftc = new MaskFormatter("###########");
		JFormattedTextField txtTc = new JFormattedTextField(mftc);
		txtTc.setBounds(184, 91, 183, 35);
		txtTc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(txtTc);

		MaskFormatter mftelno = new MaskFormatter("05## ### ## ##");
		//String s="haluk";
		//MaskFormatter mftelno = new MaskFormatter(s+"@gmail.com");
		JFormattedTextField txtTelno = new JFormattedTextField(mftelno);
		txtTelno.setBounds(184, 186, 183, 35);
		txtTelno.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(txtTelno);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(182, 378, 215, 98);
		contentPane.add(scrollPane);

		JEditorPane editorPaneAddress = new JEditorPane();
		editorPaneAddress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		scrollPane.setViewportView(editorPaneAddress);

		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setBounds(103, 235, 80, 38);
		lblEmail_1.setForeground(Color.WHITE);
		lblEmail_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(lblEmail_1);

		txtEmail = new JTextField();
		txtEmail.setBounds(184, 232, 183, 38);
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);

		passCustomer = new JPasswordField();
		passCustomer.setBounds(184, 281, 183, 35);
		passCustomer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		contentPane.add(passCustomer);

		JLabel lblNewLabel_1 = new JLabel("Şifre:");
		lblNewLabel_1.setBounds(113, 278, 70, 38);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(lblNewLabel_1);

		txtName = new JTextField();
		txtName.setBounds(184, 137, 183, 38);
		txtName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtName.setColumns(10);
		contentPane.add(txtName);

		JLabel lblIsim = new JLabel("İsim:");
		lblIsim.setBounds(122, 139, 58, 38);
		lblIsim.setForeground(Color.WHITE);
		lblIsim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(lblIsim);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(184, 327, 183, 35);
		contentPane.add(dateChooser);
		
		
		
		/*
		MaskFormatter mfdt = new MaskFormatter("##/##/####");
		JFormattedTextField txtBirthDate = new JFormattedTextField(mfdt);
		txtBirthDate.setBounds(25, 487, 183, 35);
		txtBirthDate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(txtBirthDate);
		*/

		JLabel lblDogumTarihi = new JLabel("Doğum Tarihi:");
		lblDogumTarihi.setBounds(25, 329, 183, 38);
		lblDogumTarihi.setForeground(Color.WHITE);
		lblDogumTarihi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(lblDogumTarihi);

		JButton btnKaydol = new JButton("Kaydol");
		btnKaydol.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			String birthDate;
			public void actionPerformed(ActionEvent e) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					birthDate = sdf.format(dateChooser.getDate());
				} catch (Exception e2) {}
				
				if (txtTc.getText().length() == 0 || txtName.getText().length() == 0 || txtTelno.getText().length() == 0
						|| txtEmail.getText().length() == 0 || passCustomer.getText().length() == 0
						|| birthDate.length() == 0 || editorPaneAddress.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					String customerID = txtTc.getText();
					String name = txtName.getText();
					String email = txtEmail.getText();
					String pass = passCustomer.getText();
					String address = editorPaneAddress.getText();
					String tell = txtTelno.getText();
					Customer c = new Customer(customerID, name, email, pass, address, birthDate, tell);

					String query = "INSERT INTO customer (customerID, cName, cEmail, cPass, cAddress, cBirthDate, cTell)"
							+ " VALUES ('" + customerID + "', '" + name + "', '" + email + "', '" + pass + "', '"
							+ address + "', '" + birthDate + "', '" + tell + "')";
					//DBConnection.updateDBAndQuery(query);
					if(DBConnection.updateDBAndQuery(query)) {
						txtTc.setText(null);
						txtName.setText(null);
						txtEmail.setText(null);
						passCustomer.setText(null);
						editorPaneAddress.setText(null);
						dateChooser.setDate(null);
						txtTelno.setText(null);
						CustomerScreen cs = new CustomerScreen(c);
						cs.setVisible(true);
						dispose();
					}else {
						Helper.showMsg("Böyle bir kullanıcı zaten var!");
						/*
						try {
							LoginScreen lg = new LoginScreen();
							lg.setVisible(true);
							dispose();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						*/
						System.out.println("durum degerlendiriliyor");
					}
					
				}
			}
		});
		btnKaydol.setBounds(155, 499, 108, 34);
		btnKaydol.setForeground(new Color(93, 62, 188));
		btnKaydol.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnKaydol.setBackground(new Color(255, 209, 14));
		contentPane.add(btnKaydol);

		JLabel lblGiriYapmakIin = new JLabel("Giris yapmak için tıklayınızı!");
		lblGiriYapmakIin.setBounds(94, 544, 272, 23);
		lblGiriYapmakIin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					LoginScreen lgscn = new LoginScreen();
					lgscn.setVisible(true);
					dispose();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		lblGiriYapmakIin.setForeground(new Color(255, 209, 14));
		lblGiriYapmakIin.setFont(new Font("Harrington", Font.BOLD, 20));
		contentPane.add(lblGiriYapmakIin);
		
	}
}
