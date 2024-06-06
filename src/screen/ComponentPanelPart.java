package screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import event.KeyboardInput;
import event.MouseInput;
import models.PartImage;
import utils.CharInfo;

public class ComponentPanelPart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graphics g2;
	private BufferedImage image;
	private int width = 200;
	private int height = 300;
	private Thread thread;
	private boolean start = true;
	private int x = 0;
	private int y = 0;
	private final int FPS = 60;
	private final int TARGET_TIME = 1000000000 / FPS;

	private PartImage head;
	private PartImage body;
	private PartImage leg;
	private MouseInput mouseInput;
	private int tcf = 1;
	private PartImage selectPart;
	int ddx = width / 2;
	int ddy = height - 50;
	private Image bongImage;

	int cx = width / 2;
	int cy = height - 40;
//	public ScreenComponent(Image head, Image body, Image leg) {
//		this.head = head;
//		this.body = body;
//		this.leg = leg;
//		// start();
//	}

	{
		bongImage = new ImageIcon(getClass().getResource("/data/myTexture2dbong.png")).getImage();

	}

	public ComponentPanelPart(PartImage head, PartImage body, PartImage leg, int tcf) {
		this.head = head;
		this.body = body;
		this.leg = leg;
		this.tcf = tcf;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				width = getWidth();
				height = getHeight();
				if (width > 0 && height > 0 && image == null) {
					start();
					setFocusable(true);
					requestFocusInWindow();
//					requestFocus();

				}
			}
		});
		setBackground(Color.RED);
		setSize(new Dimension(width, height));
		mouseInput = new MouseInput(this);
		addKeyListener(new KeyboardInput(this, width, height));
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);

	}

	public void start() {

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2 = image.createGraphics();
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (start) {
					long startTime = System.nanoTime();
					drawBackground();
					drawGame();
					render();

					long time = System.nanoTime() - startTime;
					if (time < TARGET_TIME) {
						long sleep = (TARGET_TIME - time) / 1000000;
						sleep(sleep);
					}
				}
			}
		});

		thread.start();
	}

	private void drawBackground() {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);

	}

	public void draw() {

	}

//	private void drawGame() {
////		System.out.println("run");
//		drawShadow();
//		drawText();
//
//		try {
//			if (head != null || body != null || leg != null) {
//				drawImage(head, (cx) + Math.round(head.getDx()) + CharInfo.CharInfo[tcf][0][1],
//						cy - CharInfo.CharInfo[tcf][0][2] + Math.round(head.getDy()));
//				drawImage(leg, (cx) + leg.getDx() + CharInfo.CharInfo[tcf][1][1],
//						cy - CharInfo.CharInfo[tcf][1][2] + leg.getDy());
//				drawImage(body, (cx) + body.getDx() + CharInfo.CharInfo[tcf][2][1],
//						cy - CharInfo.CharInfo[tcf][2][2] + body.getDy());
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//	}

	private void drawGame() {
//		System.out.println("run");
		drawShadow();
		drawText();

		try {
			if (head != null || body != null || leg != null) {

				drawImage(head, (cx) + head.getDx() + CharInfo.CharInfo[tcf][0][1],
						cy - CharInfo.CharInfo[tcf][0][2] + Math.round(head.getDy()));
				drawImage(leg, (cx) + leg.getDx() + CharInfo.CharInfo[tcf][1][1],
						cy - CharInfo.CharInfo[tcf][1][2] + leg.getDy());
				drawImage(body, (cx) + body.getDx() + CharInfo.CharInfo[tcf][2][1],
						cy - CharInfo.CharInfo[tcf][2][2] + body.getDy());

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}

	private void render() {
//		Graphics g = this.getGraphics();
//		g.drawImage(this.image, 0, 0, null);
//		g.dispose();
		repaint();

	}

	private void drawShadow() {
		int cx = getWidth() / 2 - bongImage.getWidth(null) / 2;
		int cy = getHeight() - 40;
		g2.drawImage(bongImage, cx, cy, null);
	}

	private void drawText() {
		Font font = new Font("Arial", Font.BOLD, 16);
		double divDx = 4;
		double divDy = 4;
		g2.setFont(font);
		g2.setColor(Color.RED);
		if (head != null && this.body != null && this.leg != null) {
			g2.drawString("head ( x : " + (int) (head.getDx() / divDx) + " - y : " + (int) (head.getDy() / divDy) + ")",
					5, 20);
			g2.drawString("body ( x : " + (int) (body.getDx() / divDx) + " - y : " + (int) (body.getDy() / divDy) + ")",
					5, 40);
			g2.drawString("leg  ( x : " + (int) (leg.getDx() / divDx) + " - y : " + (int) (leg.getDy() / divDy) + ")",
					5, 60);
		}

		/*
		 * leg : x/3, y/2
		 * 
		 * 18026 2 -14 | 18029 -7 -14 | 18045 -7 -6 -- 18026 -2 -20 | 18029 -7 -14 |
		 * 18045 -7 -6
		 * 
		 * 20094 -13 -21 | 20098 -7 -13 | 20113 -6 -6
		 * 
		 */
		// [[21260, -1, -20],[21261, -6, -17],[3000,0,0]]
		// [[21262, 5, -9],[21263, -2, -14],[21264, -10, -11],[21265, 0, -11],[21266, 0,
		// -11],[21267, 3, -11],[21268, 0, -12],[21269, -4, -13],[21270, 0, -15],[21271,
		// 0, -11],[21272, 2, -9],[21273, -2, -8],[21274, -3, -10],[21275, 0,
		// -10],[21276, 0, -9],[21277, 0, -9],[3001,0,0]]
		// [[21278, 3, -4],[21279, 0, -8],[21280, 0, -4],[21281, 0, -4],[21282, 0,
		// -4],[21283, 0, -4],[21284, 0, -4],[21285, 5, -2],[21286, 4, -8],[21287, 0,
		// -4],[21288, 0, -5],[21289, 0, -3],[21290, 0, 0],[3002,0,0]]
	}

	public PartImage getSelectedPart(int x, int y) {
		if (isPartClicked(head, x, y)) {
			System.out.println("head");
			return head;
		} else if (isPartClicked(body, x, y)) {
			System.out.println("body");
			return body;
		} else if (isPartClicked(leg, x, y)) {
			System.out.println("leg");
			return leg;
		}
		return null;
	}

	public void drawBorder(PartImage part, double partX, double partY, boolean isBorder) {
		if (isBorder) {
			g2.setColor(Color.RED);
			g2.drawRect((int) partX, (int) partY, part.getImageIcon().getWidth(null),
					part.getImageIcon().getHeight(null));

		}
		repaint();
	}

	private boolean isPartClicked(PartImage part, double x, double y) {
		double partX = 0;
		double partY = 0;
		if (part.getType() == 0) {
			partX = (cx) + part.getDx() + CharInfo.CharInfo[tcf][0][1];
			partY = (cy) + part.getDy() - CharInfo.CharInfo[tcf][0][2];
		} else if (part.getType() == 1) {
			partX = (cx) + part.getDx() + CharInfo.CharInfo[tcf][2][1];
			partY = (cy) + part.getDy() - CharInfo.CharInfo[tcf][2][2];
		} else if (part.getType() == 2) {
			partX = (cx) + part.getDx() + CharInfo.CharInfo[tcf][1][1];
			partY = (cy) + part.getDy() - CharInfo.CharInfo[tcf][1][2];
		}
		System.out
				.println("part info head : x :" + CharInfo.CharInfo[2][0][1] + " - y : " + CharInfo.CharInfo[2][0][2]);
		System.out
				.println("part info body :  x :" + CharInfo.CharInfo[2][2][1] + " - y : " + CharInfo.CharInfo[2][2][2]);
		System.out
				.println("part info leg :  x :" + CharInfo.CharInfo[2][1][1] + " - y : " + CharInfo.CharInfo[2][1][2]);
		int imgWidth = part.getImageIcon().getWidth(null);
		int imgHeight = part.getImageIcon().getHeight(null);

		return x >= partX && x <= partX + imgWidth && y >= partY && y <= partY + imgHeight;
	}

	public void drawImage(PartImage part, double x, double y) {

		if (selectPart == part) {
			drawBorder(part, x, y, true);
		} else {
			drawBorder(part, x, y, false);
		}

		x *= 1;
		y *= 1;
		int w = part.getImageIcon().getWidth(null);
		int h = part.getImageIcon().getHeight(null);

		g2.drawImage(part.getImageIcon(), (int) x, (int) y, w, h, null);

	}

	private void sleep(long speed) {
		try {
			Thread.sleep(speed);
		} catch (InterruptedException ex) {
			System.err.println(ex);
		}
	}

	public PartImage getSelectPart() {
		return selectPart;
	}

	public void setSelectPart(PartImage selectPart) {
		this.selectPart = selectPart;
	}
}