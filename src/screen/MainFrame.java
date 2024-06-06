package screen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.PartImage;

public class MainFrame extends JFrame {
	public static final int WIDTH = 1360;
	public static final int HEIGHT = 768;
	private int WIDTHCHILD = 200;
	private int HEIGHTCHILD = 300;
	private JPanel pRun;
	private JPanel pStand;
	private JPanel pFall;
	private JPanel pFly;
	private JPanel pSkillStand;
	private JPanel pInjure;
	private JTabbedPane tabby;

	private List<PartImage> lHead;
	private List<PartImage> lBody;
	private List<PartImage> lLeg;

	{
		lHead = Arrays.asList(new PartImage[2]);

		lBody = Arrays.asList(new PartImage[16]);
		lLeg = Arrays.asList(new PartImage[13]);
	}

	public MainFrame() {
		Init();
	}

	private void Init() {

		setTitle("Tool Part Java");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		setLocationRelativeTo(null);

		tabby = new JTabbedPane();
		updateTab();
		add(tabby);
		add(JpanelAction(), BorderLayout.EAST);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		javax.swing.SwingUtilities.invokeLater(() -> {
			setVisible(true);
		});

	}

	public void updateTab() {
		tabby.removeAll();
		addTabStand(tabby);
		addTabRun(tabby);
		addTabFall(tabby);
		addTabFly(tabby);
		addTabInju(tabby);
		addTabSkillStand(tabby);
	}

	public JPanel JpanelAction() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 250));
		panel.setMaximumSize(new Dimension(250, 250));
		panel.setBorder(BorderFactory.createTitledBorder("Import Hình ảnh"));
		panel.setLayout(new GridBagLayout());

		JButton importHead = new JButton("Import Head");
		importHead.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "png");
				chooser.setFileFilter(filter);

				chooser.setMultiSelectionEnabled(true);

				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] selectedFiles = chooser.getSelectedFiles();
					System.out.println(selectedFiles.length);
					int index = 0;
					for (File file : selectedFiles) {
						String fileName = file.getName();
						int id = Integer.parseInt(fileName.split("\\.")[0]);
						try {
							Image img = ImageIO.read(file);
							PartImage partTest = new PartImage(id, img, 0, 0, 0);
							lHead.set(index, partTest);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						index++;
					}
					updateTab();
				}

			}
		});
		JButton importBody = new JButton("Import Body");
		importBody.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "png");
				chooser.setFileFilter(filter);

				chooser.setMultiSelectionEnabled(true);

				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] selectedFiles = chooser.getSelectedFiles();
					System.out.println(selectedFiles.length);
					int index = 0;
					for (File file : selectedFiles) {
						String fileName = file.getName();
						int id = Integer.parseInt(fileName.split("\\.")[0]);
						try {
							Image img = ImageIO.read(file);
							PartImage partTest = new PartImage(id, img, 0, 0, 0);
							lBody.set(index, partTest);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						index++;
					}
					updateTab();
				}

			}
		});
		JButton importLeg = new JButton("Import Leg ");
		importLeg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "png");
				chooser.setFileFilter(filter);

				chooser.setMultiSelectionEnabled(true);

				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] selectedFiles = chooser.getSelectedFiles();
					System.out.println(selectedFiles.length);
					int index = 0;
					for (File file : selectedFiles) {
						String fileName = file.getName();
						int id = Integer.parseInt(fileName.split("\\.")[0]);
						try {
							Image img = ImageIO.read(file);
							PartImage partTest = new PartImage(id, img, 0, 0, 0);
							lLeg.set(index, partTest);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						index++;
					}
					updateTab();
				}

			}
		});
		JButton exportPart = new JButton("Export Part");

		exportPart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String dataHead = "";
				String dataBody = "";
				String dataLeg = "";
				for (PartImage partTest : lHead) {
					dataHead += partTest.exportData() + ",";
				}
				for (PartImage partTest : lBody) {
					dataBody += partTest.exportData() + ",";
				}
				for (PartImage partTest : lLeg) {
					dataLeg += partTest.exportData() + ",";
				}
				System.out.println("[" + dataHead.subSequence(0, dataHead.length() - 1) + ",[3000,0,0]]");
				System.out.println("[" + dataBody.subSequence(0, dataBody.length() - 1) + ",[3001,0,0]]");
				System.out.println("[" + dataLeg.subSequence(0, dataLeg.length() - 1) + ",[3002,0,0]]");
			}
		});

		JButton helpButton = new JButton("Help Part");
		helpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame();

				frame.setTitle("help");
				frame.setSize(new Dimension(700, 500));
				frame.setResizable(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setLayout(null);
				frame.add(new HelpPanel());

				javax.swing.SwingUtilities.invokeLater(() -> {
					frame.setVisible(true);
				});

			}
		});
		JPanel buttonsPanels = new JPanel();
		buttonsPanels.setLayout(new BoxLayout(buttonsPanels, BoxLayout.Y_AXIS));

		buttonsPanels.add(importHead);
		buttonsPanels.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonsPanels.add(importBody);
		buttonsPanels.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonsPanels.add(importLeg);
		buttonsPanels.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonsPanels.add(exportPart);
		buttonsPanels.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonsPanels.add(helpButton);
		panel.add(buttonsPanels);
		return panel;

	}

	public void addTabStand(JTabbedPane tab) {
		pStand = new JPanel();
		pStand.setLayout(null);

		ComponentPanelPart screen = new ComponentPanelPart(lHead.get(0), lBody.get(1), lLeg.get(1), 1);
		pStand.add(screen);
		tab.addTab("Stand", pStand);

	}

	public void addTabRun(JTabbedPane tab) {
		pRun = new JPanel();
		pRun.setLayout(null);

		ComponentPanelPart screen = new ComponentPanelPart(lHead.get(1), lBody.get(2), lLeg.get(2), 2);
		ComponentPanelPart screen2 = new ComponentPanelPart(lHead.get(1), lBody.get(3), lLeg.get(3), 3);
		ComponentPanelPart screen3 = new ComponentPanelPart(lHead.get(1), lBody.get(4), lLeg.get(4), 4);
		ComponentPanelPart screen4 = new ComponentPanelPart(lHead.get(1), lBody.get(5), lLeg.get(5), 5);
		ComponentPanelPart screen5 = new ComponentPanelPart(lHead.get(1), lBody.get(6), lLeg.get(6), 6);
		pRun.add(screen);
		pRun.add(screen2);
		pRun.add(screen3);
		pRun.add(screen4);
		pRun.add(screen5);

		screen2.setBounds(210, 0, WIDTHCHILD, HEIGHTCHILD);
		screen3.setBounds(420, 0, WIDTHCHILD, HEIGHTCHILD);
		screen4.setBounds(630, 0, WIDTHCHILD, HEIGHTCHILD);
		screen5.setBounds(840, 0, WIDTHCHILD, HEIGHTCHILD);

		tab.addTab("Run", pRun);
	}

	public void addTabFall(JTabbedPane tab) {

		pFall = new JPanel();
		pFall.setLayout(null);

		ComponentPanelPart screen = new ComponentPanelPart(lHead.get(0), lBody.get(8), lLeg.get(8), 12);

		pFall.add(screen);

		tab.addTab("Fall", pFall);

	}

	public void addTabFly(JTabbedPane tab) {

		pFly = new JPanel();
		pFly.setLayout(null);

		ComponentPanelPart screen = new ComponentPanelPart(lHead.get(0), lBody.get(7), lLeg.get(0), 8);

		pFly.add(screen);

		tab.addTab("Fly", pFly);

	}

	public void addTabInju(JTabbedPane tab) {

		pInjure = new JPanel();
		pInjure.setLayout(null);

		ComponentPanelPart screen = new ComponentPanelPart(lHead.get(0), lBody.get(0), lLeg.get(7), 23);

		pInjure.add(screen);

		tab.addTab("Inju", pInjure);

	}

	public void addTabSkillStand(JTabbedPane tab) {
		pSkillStand = new JPanel();
		pSkillStand.setLayout(null);

		ComponentPanelPart screen = new ComponentPanelPart(lHead.get(1), lBody.get(2), lLeg.get(9), 15);
		ComponentPanelPart screen2 = new ComponentPanelPart(lHead.get(1), lBody.get(9), lLeg.get(11), 11);
		ComponentPanelPart screen3 = new ComponentPanelPart(lHead.get(1), lBody.get(10), lLeg.get(9), 13);
		ComponentPanelPart screen4 = new ComponentPanelPart(lHead.get(1), lBody.get(11), lLeg.get(9), 14);
		ComponentPanelPart screen5 = new ComponentPanelPart(lHead.get(1), lBody.get(12), lLeg.get(10), 9);
		ComponentPanelPart screen6 = new ComponentPanelPart(lHead.get(1), lBody.get(12), lLeg.get(12), 10);
		ComponentPanelPart screen7 = new ComponentPanelPart(lHead.get(1), lBody.get(13), lLeg.get(9), 16);
		ComponentPanelPart screen8 = new ComponentPanelPart(lHead.get(1), lBody.get(14), lLeg.get(9), 19);
		ComponentPanelPart screen9 = new ComponentPanelPart(lHead.get(1), lBody.get(15), lLeg.get(9), 20);
		ComponentPanelPart screen10 = new ComponentPanelPart(lHead.get(0), lBody.get(7), lLeg.get(9), 17);
		pSkillStand.add(screen);
		pSkillStand.add(screen2);
		pSkillStand.add(screen3);
		pSkillStand.add(screen4);
		pSkillStand.add(screen5);
		pSkillStand.add(screen6);
		pSkillStand.add(screen7);
		pSkillStand.add(screen8);
		pSkillStand.add(screen9);
		pSkillStand.add(screen10);

		screen2.setBounds(210, 0, WIDTHCHILD, HEIGHTCHILD);
		screen3.setBounds(420, 0, WIDTHCHILD, HEIGHTCHILD);
		screen4.setBounds(630, 0, WIDTHCHILD, HEIGHTCHILD);
		screen5.setBounds(840, 0, WIDTHCHILD, HEIGHTCHILD);
		screen6.setBounds(0, 310, WIDTHCHILD, HEIGHTCHILD);

		screen7.setBounds(210, 310, WIDTHCHILD, HEIGHTCHILD);
		screen8.setBounds(420, 310, WIDTHCHILD, HEIGHTCHILD);
		screen9.setBounds(630, 310, WIDTHCHILD, HEIGHTCHILD);
		screen10.setBounds(840, 310, WIDTHCHILD, HEIGHTCHILD);
		tab.addTab("Skill Stand", pSkillStand);
	}

}
