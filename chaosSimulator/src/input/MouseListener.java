package input;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import chaosSimulator.DefaultSetups.SETUPS;
import chaosSimulator.DisplayPanel;
import chaosSimulator.DisplayPanel.STATES;
import chaosSimulator.Magnet;
import chaosSimulator.Main;


public class MouseListener extends MouseAdapter{
	private DisplayPanel displayPanel;
	
	int clicks = 0;
	boolean canCreate;

		
	public MouseListener(DisplayPanel dp) {
		this.displayPanel = dp;
		
	}
	
	public void mousePressed(MouseEvent e) {

		 
		double mouseX = e.getX();
		double mouseY = e.getY();
		
		if(displayPanel.getState() == STATES.menu) {
			if(mouseX >= Main.screensize.width/5 && mouseY >= Main.screensize.height/14 && mouseX <= Main.screensize.width*4/5 && mouseY <= Main.screensize.height*3/14) {
				displayPanel.setState(STATES.game);
			}
			if(mouseX >= Main.screensize.width/5 && mouseY >= Main.screensize.height*4/14 && mouseX <= Main.screensize.width*4/5 && mouseY <= Main.screensize.height*6/14) {
				displayPanel.setState(STATES.shapes);
			}
			if(mouseX >= Main.screensize.width/5 && mouseY >= Main.screensize.height*7/14 && mouseX <= Main.screensize.width*4/5 && mouseY <= Main.screensize.height*9/14) {
				displayPanel.setState(STATES.simulation);
			}
		}
		if(displayPanel.getState() == STATES.shapes) {
			//(DefaultSetups.setup == DEFAULTSETUPS.values()[i])
				
		}

		
		if(displayPanel.getState() == STATES.game) {
			ArrayList<Magnet> magnets = this.displayPanel.getWorld().getMagnets();
			boolean onMagnet;
			if(clicks == 0) {
				canCreate = false;
			}else {
				canCreate = true;
			}
			
			clicks ++;
			
			for(int i = 0; i < Magnet.getTotalMagnets(); i++) {
				System.out.print(magnets);
				int magX = magnets.get(i).getXPos();
				int magY = magnets.get(i).getYPos();
				
				if((mouseX <= magX + 10 && mouseX >= magX -10 && mouseY <= magY +10 && mouseY >= magY -10)) {
					canCreate = false;
				
		
					
				if(!canCreate) {
					Magnet.totalMagnets--;
					magnets.remove(i);
				}
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
}
