package event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import screen.ComponentPanelPart;

public class KeyboardInput implements KeyListener {
	int x = 0;
	int y = 0;
	double dx = 0;
	double dy = 0;
	int width;
	int height;
	int count = 0;
	private ComponentPanelPart screenComponent;

	public KeyboardInput(ComponentPanelPart screenComponent, int width, int height) {
		this.screenComponent = screenComponent;
		this.width = width;
		this.height = height;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (screenComponent.getSelectPart() != null) {
			double STEPX = 4;
			double STEPY = 4;
			// TODO Auto-generated method stub
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				y -= STEPY;
				screenComponent.getSelectPart().setDy(screenComponent.getSelectPart().getDy() - STEPY);
				break;
			case KeyEvent.VK_DOWN:
				y += STEPY;

				screenComponent.getSelectPart().setDy(screenComponent.getSelectPart().getDy() + STEPY);
				break;
			case KeyEvent.VK_LEFT:

				x -= STEPX;
				screenComponent.getSelectPart().setDx(screenComponent.getSelectPart().getDx() - STEPX);

				break;
			case KeyEvent.VK_RIGHT:
				x += STEPX;
				screenComponent.getSelectPart().setDx(screenComponent.getSelectPart().getDx() + STEPX);

				break;
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
