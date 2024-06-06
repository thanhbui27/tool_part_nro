package event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import models.PartImage;
import screen.ComponentPanelPart;

public class MouseInput implements MouseListener, MouseMotionListener {
	private int prevX, prevY;
	private boolean dragging;
	private PartImage selectedPart;
	private ComponentPanelPart screenComponent;

	public MouseInput(ComponentPanelPart screenComponent) {
		this.screenComponent = screenComponent;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
//		if (dragging && selectedPart != null) {
//			int dx = e.getX() - prevX;
//			int dy = e.getY() - prevY;
//			selectedPart.setDx(selectedPart.getDx() + dx);
//			selectedPart.setDy(selectedPart.getDy() + dy);
//			prevX = e.getX();
//			prevY = e.getY();
//			screenComponent.repaint();
//		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		selectedPart = screenComponent.getSelectedPart(x, y);
		if (selectedPart != null) {
			screenComponent.setSelectPart(selectedPart);
			screenComponent.repaint();

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		prevX = e.getX();
		prevY = e.getY();
		selectedPart = screenComponent.getSelectedPart(e.getX(), e.getY());
		System.out.println("x : " + e.getX() + " | y : " + e.getY());
		if (selectedPart != null) {
			dragging = true;
			System.out.println("dx : " + selectedPart.getDx() + " dy : " + selectedPart.getDy() + " - width : "
					+ selectedPart.getImageIcon().getWidth(null));

			screenComponent.requestFocusInWindow();
		} else {
			System.out.println("press");
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		dragging = false;
		selectedPart = null;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
