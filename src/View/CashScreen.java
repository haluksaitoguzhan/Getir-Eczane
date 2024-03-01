package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Helper.Helper;
import Model.Cart;
import Model.Cash;
import Model.Customer;
import Model.SendMail;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashScreen extends JFrame {
	private static Customer customer;
	private JPanel contentPane;
	private JTextField textSubTotal;
	private JTextField textName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CashScreen frame = new CashScreen(customer);
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
	public CashScreen(Customer customer) {
		setCustomer(customer);
		setTitle("Getir Eczane Kapıda Ödeme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 170, 385, 344);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(19, 63, 330, 186);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("İsim:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(23, 18, 56, 38);
		panel.add(lblNewLabel_1);
		
		JButton btnConfirm = new JButton("Siparişi Onayla");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

						Date now = new Date();
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						String pAddedDate = df.format(now);
						
						Cash cash = new Cash();
						cash.setpId(customer.getId());
						cash.setPCustomerName(textName.getText());
						cash.setpDate(pAddedDate);
						cash.setpType("Kapıda Ödeme");
						cash.setSubTotal(Cart.calculateSubTotalCart(customer.getId()));
						cash.setpAddress(customer.getAddress());

						if (cash.pay(cash)) {
							textName.setText(null);
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

				}
			}
		});
		
		btnConfirm.setForeground(new Color(93, 62, 188));
		btnConfirm.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnConfirm.setBackground(new Color(255, 209, 14));
		btnConfirm.setBounds(109, 126, 150, 34);
		panel.add(btnConfirm);
		
		textSubTotal = new JTextField();
		textSubTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textSubTotal.setText(String.valueOf(Cart.calculateSubTotalCart(customer.getId())));
		textSubTotal.setEditable(false);
		textSubTotal.setColumns(10);
		textSubTotal.setBounds(176, 70, 116, 35);
		panel.add(textSubTotal);
		
		JLabel lblNewLabel_1_1 = new JLabel("Toplam Tutar:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_1.setBounds(23, 67, 150, 38);
		panel.add(lblNewLabel_1_1);
		
		textName = new JTextField();
		textName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textName.setColumns(10);
		textName.setBounds(89, 21, 204, 35);
		panel.add(textName);
		
		JLabel lbBackLogo = new JLabel(new ImageIcon(getClass().getResource("backlogo1.png")));
		lbBackLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PaymentScreen ps = new PaymentScreen(customer);
				ps.setVisible(true);
				dispose();
			}
		});
		lbBackLogo.setBounds(0, 0, 50, 52);
		contentPane.add(lbBackLogo);
		
		JLabel lbmingetlog = new JLabel(new ImageIcon(getClass().getResource("mingetlogo.jpg")));
		lbmingetlog.setBounds(162, 260, 207, 44);
		contentPane.add(lbmingetlog);
		
		JLabel lblSemekIstediinizIlacn = new JLabel("Kapıda ödeme bilgileri");
		lblSemekIstediinizIlacn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemekIstediinizIlacn.setForeground(new Color(255, 209, 14));
		lblSemekIstediinizIlacn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblSemekIstediinizIlacn.setBounds(60, 19, 255, 33);
		contentPane.add(lblSemekIstediinizIlacn);
		
		
		
	}

	public static Customer getCustomer() {
		return customer;
	}

	public static void setCustomer(Customer customer) {
		CashScreen.customer = customer;
	}
}
