package chaosSimulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import chaosSimulator.DefaultSetups.DEFAULTSETUPS;
import chaosSimulator.DisplayPanel.STATES;


public class MouseListener extends MouseAdapter{
	private DisplayPanel displayPanel;
	
	int clicks = 0;
	boolean canCreate;

		
	public MouseListener(DisplayPanel dp) {
		this.displayPanel = dp;
		
	}
	
	public void mousePressed(MouseEvent e) {

		 
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(displayPanel.state == STATES.menu) {
			if(mouseX >= Main.screensize.width/5 && mouseY >= Main.screensize.height/14 && mouseX <= Main.screensize.width*4/5 && mouseY <= Main.screensize.height*3/14) {
				displayPanel.state = STATES.game;
			}
			if(mouseX >= Main.screensize.width/5 && mouseY >= Main.screensize.height*4/14 && mouseX <= Main.screensize.width*4/5 && mouseY <= Main.screensize.height*6/14) {
				displayPanel.state = STATES.shapes;
			}
			if(mouseX >= Main.screensize.width/5 && mouseY >= Main.screensize.height*7/14 && mouseX <= Main.screensize.width*4/5 && mouseY <= Main.screensize.height*9/14) {
				displayPanel.state = STATES.simulation;
			}
		}
		if(displayPanel.state == STATES.shapes) {
			for(int i=0; i<DefaultSetups.getNumOfSetups(); i++) {
				DefaultSetups.setup = DEFAULTSETUPS.values()[i];
			}
		}
		
		if(displayPanel.state == STATES.game) {
			ArrayList<Magnet> magnets = this.displayPanel.getWorld().getMagnets();
			if(clicks == 0) {
				canCreate = false;
			}else {
				canCreate = true;
			}
			
			clicks ++;
			
			for(int i = 0; i < Magnet.totalMagnets; i++) {
				int magX = magnets.get(i).getXPos();
				int magY = magnets.get(i).getYPos();
				
				if((mouseX <= magX + 10 && mouseX >= magX -10 && mouseY <= magY +10 && mouseY >= magY -10)) {
					boolean onMagnet = true;
					canCreate = false;
					
					if(!canCreate && onMagnet) {
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

	}
	
	public void mouseReleased(MouseEvent e) {

	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("dragging");
		ArrayList<Magnet> magnets = this.displayPanel.getWorld().getMagnets();
		for(int i = 0; i< Magnet.totalMagnets; i++) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			if(!canCreate ) {
				
			
			
			}
			
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
}
