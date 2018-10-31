package chaosSimulator;

public class Magnet {
	private int xPos;
	private int yPos;
	private double coef; //magnet coefficient 
	
	static int totalMagnets;
	static int maxMagnets = 10;
	
	public Magnet(int xPos, int yPos, double coef) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.coef = coef;
		
		this.totalMagnets++;
	}
	
	public void destroyMagnet() {
		//called when destroying magnet
		this.totalMagnets--;
	}
	
	
}
