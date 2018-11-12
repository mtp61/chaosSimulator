package chaosSimulator;

public class Magnet {
	private int xPos;
	private int yPos;
	private double coef; //magnet coefficient 
	private static int maxMagnets = 1000;
	
	public Magnet(int xPos, int yPos, double coef) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.coef = coef;
	}

	public void destroyMagnet() {
	}
	
	public int getXPos() { return this.xPos; }
	public int getYPos() { return this.yPos; }
	public double getCoef() { return this.coef; }

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public static int getMaxMagnets() {
		return maxMagnets;
	}
	
}
