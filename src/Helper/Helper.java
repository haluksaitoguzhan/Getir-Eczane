package Helper;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;

public class Helper {

	public static void OptionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.okButtonText", "Tamam");
	}

	public static void showMsg(String str) {
		String msg;
		OptionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanları doldurunuz!";
			break;
		case "warning":
			msg = "Eksik ya da hatalı giriş yapınız!";
			break;
		case "eror":
			msg = "Beklenmedik bir hata oluştu";
			break;
		default:
			msg = str;
		}
		JOptionPane.showMessageDialog(null, msg, "Dikkat", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		String msg;
		OptionPaneChangeButtonText();
		switch (str) {
		case "sure":
			msg = "Silme işlemini gerçekleştirmek istiyormusunuz?";
			break;
		case "exit":
			msg = "Çıkış yapmak istiyormusunuz?";
			break;
		case "changing":
			msg = "Bu değişikliği yapmak istiyor musunuz?";
			break;
		default:
			msg = str;
			break;
		}

		int result = JOptionPane.showConfirmDialog(null, msg, "Dikkat!", JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == 0)
			return true;
		else
			return false;
	}

	public static void createToastMessage(String s, int x, int y) {
		JWindow w;
		w = new JWindow();
		// make the background transparent
		w.setBackground(new Color(0, 0, 0, 0));

		// create a panel
		JPanel p = new JPanel() {
			public void paintComponent(Graphics g) {
				int wid = g.getFontMetrics().stringWidth(s);
				int hei = g.getFontMetrics().getHeight();

				// draw the boundary of the toast and fill it
				g.setColor(Color.black);
				g.fillRect(10, 10, wid + 30, hei + 10);
				g.setColor(Color.black);
				g.drawRect(10, 10, wid + 30, hei + 10);

				// set the color of text
				g.setColor(new Color(250, 250, 250));
				g.drawString(s, 25, 27);
				int t = 250;

				// draw the shadow of the toast
				for (int i = 0; i < 4; i++) {
					t -= 60;
					g.setColor(new Color(0, 0, 0, t));
					g.drawRect(10 - i, 10 - i, wid + 30 + i * 2, hei + 10 + i * 2);
				}
			}
		};

		w.add(p);
		w.setLocation(x, y);
		w.setSize(300, 100);

		try {
			w.setOpacity(1);
			w.setVisible(true);

			// wait for some time
			// Thread.sleep(2000);

			// make the message disappear slowly
			for (double d = 1.0; d > 0.2; d -= 0.1) {
				Thread.sleep(200);
				w.setOpacity((float) d);
			}

			// set the visibility to false
			w.setVisible(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
