package chaosSimulator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class World {
	//class contains vars about the simulation
	private double armX = 0;
	private double armY = 0;
	private double velX = 0;
	private double velY = 0;
	private double homeX = 0;
	private double homeY = 0;

	private LinkedList<Magnet> magnets = new LinkedList<Magnet>();
	
	public World() {
		//class constructor
		
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < Magnet.totalMagnets; i++) {
			g.setColor(Color.BLACK);
			g.fillOval(//x position of the magnet in the array, //y position of the magnet in the array,//width 15, //height 15);
		}
	}
	
	public void addMagnet(Magnet magnet) {
		if(Magnet.totalMagnets < Magnet.maxMagnets){
			//add magnet
			magnets.add(magnet);
			
		}else {
			//too many magnets
			System.out.println("too many magnets");
		}
	}
	
	public void removeMagnet(Magnet magnet) {
		magnet.destroyMagnet();
		magnets.remove(magnet);
	}
	
	//setters
	public void setArmX(double armX) {this.armX = armX;};
	public void setArmY(double armY) {this.armY = armY;};
	public void setVelX(double velX) {this.velX = velX;};
	public void setVelY(double velY) {this.velY = velY;};
	
	//getters
	public double getArmX() {return this.armX;};
	public double getArmY() {return this.armY;};
	public double getVelX() {return this.velX;};
	public double getVelY() {return this.velY;};
	public LinkedList<Magnet> getMagnets() {return this.magnets;};
	
}


