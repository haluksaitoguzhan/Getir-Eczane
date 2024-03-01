package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;
import Model.CreditCard;
import Model.Customer;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;

public class PaymentScreen extends JFrame {

	private static Customer customer;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentScreen frame = new PaymentScreen(customer);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaymentScreen(Customer custoemr) {
		setCustomer(custoemr);
		setTitle("Getir Eczane Ödeme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 170, 411, 475);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JEditorPane editorPaneAddress = new JEditorPane();
		editorPaneAddress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		editorPaneAddress.setBounds(125, 71, 213, 96);
		editorPaneAddress.setText(customer.getAddress());
		contentPane.add(editorPaneAddress);
		
		JLabel lbCardlogo = new JLabel(new ImageIcon(getClass().getResource("cardlogo3.png")));//cardlogo2
		lbCardlogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(!editorPaneAddress.getText().isEmpty()) {
						customer.setAddress(editorPaneAddress.getText());
						CreditCardScreen ccs = new CreditCardScreen(custoemr);
						ccs.setVisible(true);
						dispose();
					}else {
						Helper.showMsg("Lütfen adres bilgilerinizi eksiksiz giriniz!");
					}
				} catch (ParseException e1) {
					Helper.showMsg("Kredi kartı bölümüne gidilemiyor!");
					e1.printStackTrace();
				}
				
			}
		});
		lbCardlogo.setBounds(57, 261, 121, 90);
		contentPane.add(lbCardlogo);
		
		JLabel lbCashlogo = new JLabel(new ImageIcon(getClass().getResource("cashlogo2.png")));//cashlogo1
		lbCashlogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!editorPaneAddress.getText().isEmpty()) {
					customer.setAddress(editorPaneAddress.getText());
					CashScreen cs = new CashScreen(custoemr);
					cs.setVisible(true);
					dispose();
				}else {
					Helper.showMsg("Lütfen adres bilgilerinizi eksiksiz giriniz!");
				}
			}
		});
		lbCashlogo.setBounds(210, 261, 110, 90);
		contentPane.add(lbCashlogo);
		
		JLabel lblNewLabel = new JLabel("Kredi Kartı");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(57, 350, 121, 33);
		lblNewLabel.setForeground(new Color(255, 209, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblCash = new JLabel("Nakit");
		lblCash.setHorizontalAlignment(SwingConstants.CENTER);
		lblCash.setForeground(new Color(255, 209, 14));
		lblCash.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblCash.setBounds(232, 350, 74, 33);
		contentPane.add(lblCash);
		
		JLabel lbmingetlog = new JLabel(new ImageIcon(getClass().getResource("mingetlogo.jpg")));
		lbmingetlog.setBounds(188, 392, 207, 44);
		contentPane.add(lbmingetlog);
		
		JLabel lbldemeTipi = new JLabel("Ödeme Tipi");
		lbldemeTipi.setHorizontalAlignment(SwingConstants.CENTER);
		lbldemeTipi.setForeground(new Color(255, 209, 14));
		lbldemeTipi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lbldemeTipi.setBounds(109, 222, 165, 33);
		contentPane.add(lbldemeTipi);
		
		JLabel lbAdminlogo = new JLabel(new ImageIcon(getClass().getResource("backlogo1.png")));
		lbAdminlogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerScreen cs = new CustomerScreen(customer);
				cs.setVisible(true);
				dispose();
			}
		});
		lbAdminlogo.setBounds(0, 0, 60, 60);
		contentPane.add(lbAdminlogo);
		
		JLabel lblAdres = new JLabel("Adres:");
		lblAdres.setForeground(Color.WHITE);
		lblAdres.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblAdres.setBounds(54, 70, 71, 38);
		contentPane.add(lblAdres);
		
		JLabel lblAdresBilgisi = new JLabel("Adres Bilgisi");
		lblAdresBilgisi.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresBilgisi.setForeground(new Color(255, 209, 14));
		lblAdresBilgisi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblAdresBilgisi.setBounds(109, 26, 165, 33);
		contentPane.add(lblAdresBilgisi);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 199, 324, -14);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(54, 199, 294, 2);
		contentPane.add(separator_1);
	}
	
	public static Customer getCustomer() {
		return customer;
	}

	public static void setCustomer(Customer customer) {
		PaymentScreen.customer = customer;
	}
}
