package chaosSimulator;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class World {

	private double startArmX = 0;
	private double startArmY = 0;

	private double armX = startArmX;
	private double armY = startArmY;
	private double startVelX = 0;
	private double startVelY = 0;
	private double velX = startVelX;
	private double velY = startVelY;

	private double homeX = Main.screensize.width/2;
	private double homeY = Main.screensize.height/2;


	private double defaultCoef = 10;


	private double homeCoef = 10;
	
	private double friction = .95;

	private ArrayList<Magnet> magnets = new ArrayList<Magnet>();
	
	public World() {
		//class constructor
		DefaultSetups.setup1(this);
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
	
	
	
	//reset simulation
	public void resetWorld() {
		armX = startArmX;
		armY = startArmY;
		velX = startVelX;
		velY = startVelY;
		magnets.clear();
		Magnet.totalMagnets = 0;
	}

	
	
	
	//tick for the world 
	public void tick(double framerate) {
		//calculate acceleration
		double[] a = Logic.acceleration(homeCoef, armX, homeX, armY, homeY, magnets);
		
		velX += a[0]/framerate;
		velY += a[1]/framerate;
		
		//simple updating
		armX += velX/framerate;
		armY += velY/framerate;
		
		
		//simple friction
		velX *= 1-((1-friction)/framerate);
		velY *= 1-((1-friction)/framerate);

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
	public ArrayList<Magnet> getMagnets() {return magnets;};
	
}


