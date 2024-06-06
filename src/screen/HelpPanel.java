package screen;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {

	private Image img;

	public HelpPanel() {
		img = new ImageIcon(getClass().getResource("/data/help.png")).getImage();
		setSize(700, 500);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
