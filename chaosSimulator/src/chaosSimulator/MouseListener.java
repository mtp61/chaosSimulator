package chaosSimulator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter{
	private DisplayPanel displayPanel;
	
	public MouseListener(DisplayPanel displayPanel) {
		this.displayPanel = displayPanel;
	}
	
	public void mousePressed(MouseEvent e) {
		int xCoord = e.getX();
		int yCoord = e.getY();
		Magnet a = new Magnet(xCoord, yCoord, displayPanel.getWorld().getDefaultCoef());
		this.displayPanel.getWorld().addMagnet(a);
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
}
