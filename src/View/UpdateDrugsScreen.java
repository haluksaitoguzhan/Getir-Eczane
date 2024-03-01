package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Window.Type;

public class UpdateDrugsScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDrugsScreen frame = new UpdateDrugsScreen();
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
	public UpdateDrugsScreen() {
		setTitle("İlaç Güncelleme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 150, 285, 205);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, 11));
		contentPane.setBackground(new Color(93, 62, 188));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeilenHcre = new JLabel("Seçilen Hücre:");
		lblSeilenHcre.setForeground(Color.WHITE);
		lblSeilenHcre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblSeilenHcre.setBounds(45, 25, 136, 27);
		contentPane.add(lblSeilenHcre);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(47, 51, 176, 35);
		contentPane.add(textField);
		
		JButton btnDzenle = new JButton("Düzenle");
		btnDzenle.setForeground(new Color(93, 62, 188));
		btnDzenle.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDzenle.setBackground(new Color(255, 209, 14));
		btnDzenle.setBounds(83, 95, 98, 35);
		contentPane.add(btnDzenle);
	}

}
