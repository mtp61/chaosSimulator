package chaosSimulator;

import java.awt.Graphics;
import java.util.LinkedList;

public class World {
	//class contains vars about the simulation

	private double armX = 50;


	private double armY = 50;
	private double velX = 0;
	private double velY = 0;
	private double homeX = Main.screensize.width/2;
	private double homeY = Main.screensize.height/2;
	private double defaultCoef = 1;
	private double homeCoef = 5;
	
	private double friction = .999;

	private LinkedList<Magnet> magnets = new LinkedList<Magnet>();
	
	public World() {
		//class constructor
		
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < Magnet.totalMagnets; i++) {
			//g.setColor(Color.BLACK);
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
	
	
	
	
	
	
	//tick for the world
	public void tick(int framerate) {
		/*if(armX < homeX) {
			velX += (double)120/framerate;
		}else {
			velX -= (double)120/framerate;
		}
		if(armY < homeY) {
			velY += (double)120/framerate;
		}else {
			velY -= (double)120/framerate;
		}*/
		double[] a = Logic.acceleration(homeCoef, armX, homeX, armY, homeY, magnets);
		
		velX += a[0]/framerate;
		velY += a[1]/framerate;
		
		//simple updating
		armX += velX/framerate;
		armY += velY/framerate;
		
		
		//simple friction
		//armX *= friction/framerate;
		//armY *= friction/f;

	}
	
	
	
	
	
	//setters
	public void setArmX(double armX) {this.armX = armX;};
	public void setArmY(double armY) {this.armY = armY;};
	public void setVelX(double velX) {this.velX = velX;};
	public void setVelY(double velY) {this.velY = velY;};
	
	//getters
	public double getArmX() {return armX;};
	public double getArmY() {return armY;};
	public double getVelX() {return velX;};
	public double getVelY() {return velY;};
	public double getHomeX() {return homeX;};
	public double getHomeY() {return homeY;};
	public double getDefaultCoef() {return defaultCoef;};
	public LinkedList<Magnet> getMagnets() {return magnets;};
	
}


