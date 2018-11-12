package input;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import chaosSimulator.DisplayPanel;
import chaosSimulator.DisplayPanel.STATES;
import chaosSimulator.Magnet;


public class MouseListener extends MouseAdapter{
	private DisplayPanel displayPanel;
	private static int clicks = 0;
	boolean canCreate;
	public MouseListener(DisplayPanel dp) {
		this.displayPanel = dp;
	}
	
	@SuppressWarnings("unused")
	public void mousePressed(MouseEvent e) {
		double mouseX = e.getX();
		double mouseY = e.getY();
		
		if(displayPanel.getState() == STATES.menu) {
			displayPanel.getMenu().listen(mouseX, mouseY);
		}
		
		if(displayPanel.getState() == STATES.shapes) {
			displayPanel.getShapes().listen(mouseX, mouseY);
		}

		if(displayPanel.getState() == STATES.game) {
			ArrayList<Magnet> magnets = this.displayPanel.getWorld().getMagnets();
			if(clicks == 0) {
				canCreate = false;
			}else {
				canCreate = true;
			}
			
			clicks ++;
			
			for(int i = 0; i < magnets.size(); i++) {
				double magX = magnets.get(i).getXPos();
				double magY = magnets.get(i).getYPos();
				
				if((mouseX <= magX + 15 && mouseX >= magX -15 && mouseY <= magY +15 && mouseY >= magY -15)) {
					canCreate = false;
				}
				if(!canCreate) {
					//Magnet.totalMagnets--;
					magnets.remove(i);
					break;
				}			
			}
			if(canCreate) {
				int modifiers = e.getModifiers();
				int xCoord = e.getX();
				int yCoord = e.getY();
				double coef = displayPanel.getWorld().getDefaultCoef();
				if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			        coef *= 1;
				}
			       
			    if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
			    	coef *= -1;
		        }
			Magnet a = new Magnet(xCoord, yCoord, coef);
			this.displayPanel.getWorld().addMagnet(a);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
	
	public void mouseDragged(MouseEvent e) {

	}
	
	public void mouseMoved(MouseEvent e) { 

	}
	
	public static void setClicks(int c) {
		clicks = c;
	}
}

