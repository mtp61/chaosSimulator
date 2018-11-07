package chaosSimulator;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class MouseListener extends MouseAdapter{
	private DisplayPanel displayPanel;
	
	public MouseListener(DisplayPanel dp) {
		this.displayPanel = dp;
	}
	
	public void mousePressed(MouseEvent e) {
		ArrayList<Magnet> magnets = this.displayPanel.getWorld().getMagnets();
		boolean canCreate = true;
		for(int i = 0; i < Magnet.totalMagnets; i++) {
			int magX = magnets.get(i).getXPos();
			int magY = magnets.get(i).getYPos();
			int mouseX = e.getX();
			int mouseY = e.getY();
			 
			if((mouseX <= magX + 10 && mouseX >= magX -10 && mouseY <= magY +10 && mouseY >= magY -10)) {
				canCreate = false;
				
				if(!canCreate) {
					Magnet.totalMagnets--;
					magnets.remove(i);
					
				}
				break;
			}
			//hello nerd
			
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
	
	public void mouseReleased(MouseEvent e) {

	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("dragging");
		ArrayList<Magnet> magnets = this.displayPanel.getWorld().getMagnets();
		for(int i = 0; i< Magnet.totalMagnets; i++) {
			int magX = magnets.get(i).getXPos();
			int magY = magnets.get(i).getYPos();
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			
			if(mouseX <= magX + 10 && mouseX >= magX -10 && mouseY <= magY +10 && mouseY >= magY -10) {
				magnets.get(i).setxPos(mouseX);
				magnets.get(i).setyPos(mouseY);
				
				break;
			}
			
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
}
