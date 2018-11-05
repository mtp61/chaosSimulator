package chaosSimulator;

import java.util.LinkedList;

public class Logic {
	public Logic() {
		
	}
	
	public double[] acceleration(double homeCoef, double armX, double homeX, double armY, double homeY, LinkedList<Magnet> magnets) {
		//calculate force in the x direction
		double[] sum = {0,0};
		//add for home
		double deltaX = Math.abs(armX-homeX);
		double deltaY = Math.abs(armY-homeY);
		double angle = Math.atan(deltaY/deltaX);
		double radius = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY,2));
		double force = Math.pow(radius/homeCoef, 2);
		double ax = force * Math.cos(angle);
		double ay = force * Math.sin(angle);
		if(armX < homeX) {
			sum[0] -= ax;
		} else {
			sum[0] += ax;
		}
		if(armY < homeY) {
			sum[0] -= ay;
		} else {
			sum[0] += ay;
		}		
		
		
		
		//add for magnets
		for(int i = 0; i < magnets.size(); i++) {
			sum[0] += 0;
		}
		
		return sum;
	}
	
	
}
