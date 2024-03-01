package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Admin;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class OrderDetailsScreen extends JFrame {
	static String orderId;
	private JPanel contentPane;
	private JTable tableOrdersDetails;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailsScreen frame = new OrderDetailsScreen(orderId);
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
	public OrderDetailsScreen(String orderId) {
		setTitle("Sipariş Detaylarım");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(330, 130, 678, 487);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 642, 347);
		contentPane.add(scrollPane);

		tableOrdersDetails = new JTable();
		tableOrdersDetails.setEnabled(false);
		tableOrdersDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableOrdersDetails);
		Admin.connectDBAndDisplayCustomerOrderDetails(orderId, tableOrdersDetails);

		JLabel lbmingetlog = new JLabel(new ImageIcon(getClass().getResource("mingetlogo.jpg")));
		lbmingetlog.setBounds(460, 404, 202, 44);
		contentPane.add(lbmingetlog);

		JLabel lblSemekIstediinizIlacn = new JLabel("Siparis Detaylarım");
		lblSemekIstediinizIlacn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemekIstediinizIlacn.setForeground(new Color(255, 209, 14));
		lblSemekIstediinizIlacn.setFont(new Font("Harrington", Font.BOLD, 28));
		lblSemekIstediinizIlacn.setBounds(154, 0, 327, 55);
		contentPane.add(lblSemekIstediinizIlacn);
	}
}
