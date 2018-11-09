package chaosSimulator;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class World {

	private double startArmX = 400;
	private double startArmY = 400;
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

	private boolean stopped = false;
	private double maxStopDist = 15;
	private int posArraySize = 1000;
	private double[] posArrayX = new double[posArraySize];
	private double[] posArrayY = new double[posArraySize];
	
	private ArrayList<Magnet> magnets = new ArrayList<Magnet>();
	
	public World() {
		//class constructor
		for (int i = 0; i < posArraySize; i++) {
			posArrayX[i] = i * 10;
			posArrayY[i] = i * 10;
		}
		
		
		
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
		stopped = false;
		magnets.clear();
		Magnet.totalMagnets = 0;
		for (int i = 0; i < posArraySize; i++) {
			
			posArrayX[i] = i * 10;
			posArrayY[i] = i * 10;
		}
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
		
		//check if stopped
		double maxX = posArrayX[0];
		double minX = posArrayX[0];
		double maxY = posArrayY[0];
		double minY = posArrayY[0];
		for (int i = 0; i < posArraySize - 1; i++) { //update velarray
			posArrayX[posArraySize-1-i] = posArrayX[posArraySize-2-i];
			posArrayY[posArraySize-1-i] = posArrayY[posArraySize-2-i];
			if (posArrayX[posArraySize-1-i] > maxX) {
				maxX = posArrayX[posArraySize-1-i];
			} else if (posArrayX[posArraySize-1-i] < minX) {
				minX = posArrayX[posArraySize-1-i];
			}
			if (posArrayY[posArraySize-1-i] > maxY) {
				maxY = posArrayY[posArraySize-1-i];
			} else if (posArrayY[posArraySize-1-i] < minY) {
				minY = posArrayY[posArraySize-1-i];
			}
		}

		posArrayX[0] = armX;
		posArrayY[0] = armY;
		
		double dist = Math.sqrt(Math.pow(maxX - minX, 2) + Math.pow(maxY - minY, 2));
		
		stopped = false;
		if (dist < maxStopDist) {
			stopped = true;
		}
		
		if (stopped == true) {
			System.out.println("stopped ");
		}
	}
	
	
	
	
	
	//setters

	public void setArmX(double armX) {this.armX = armX;};
	public void setArmY(double armY) {this.armY = armY;};
	public void setVelX(double velX) {this.velX = velX;};
	public void setVelY(double velY) {this.velY = velY;};
	public void setStartArmX(double startArmX) {
		this.startArmX = startArmX;
	}
	public void setStartArmY(double startArmY) {
		this.startArmY = startArmY;
	}

	
	//getters

	public double getArmX() {return armX;};
	public double getArmY() {return armY;};
	public double getStartArmX() {
		return startArmX;
	}
	public double getStartArmY() {
		return startArmY;
	}
	public double getVelX() {return velX;};
	public double getVelY() {return velY;};
	public double getHomeX() {return homeX;};
	public double getHomeY() {return homeY;};
	public double getDefaultCoef() {return defaultCoef;};
	public ArrayList<Magnet> getMagnets() {return magnets;};

	
}


