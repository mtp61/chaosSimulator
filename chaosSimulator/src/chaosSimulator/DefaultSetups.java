package chaosSimulator;

import java.util.ArrayList;

public class DefaultSetups {
	
	private static int homeX = Main.screensize.width/2;
	private static int homeY = Main.screensize.width/2;
	
	public enum DEFAULTSETUPS {
		SQUARE, TRIANGLE, HEXAGON, GRID, CIRCLE, CROSS
	}
	private static int numOfSetups = 6; //THIS MUST BE CHANGED TO MATCH THE NUMBER OF ITEMS IN THE ENUM
	
	public static DEFAULTSETUPS setup = null;
	
	public static int getNumOfSetups() {
		return numOfSetups;
	}

	public DefaultSetups() {
		
	}
	
	public static void square(World world, int distance) {
		world.addMagnet(new Magnet(homeX,homeY+distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX+distance,homeY+distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX+distance,homeY,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX+distance,homeY-distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX,homeY-distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX-distance,homeY-distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX-distance,homeY,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX-distance,homeY+distance,world.getDefaultCoef()));
		
	}
	public static void setup2(World world) {
		world.addMagnet(new Magnet(350,350,world.getDefaultCoef()));
		world.addMagnet(new Magnet(450,350,world.getDefaultCoef()));
		world.addMagnet(new Magnet(450,450,world.getDefaultCoef()));
		world.addMagnet(new Magnet(350,450,world.getDefaultCoef()));
		
		world.addMagnet(new Magnet(350,400,-world.getDefaultCoef()));
		world.addMagnet(new Magnet(450,400,-world.getDefaultCoef()));
		world.addMagnet(new Magnet(400,350,-world.getDefaultCoef()));
		world.addMagnet(new Magnet(400,450,-world.getDefaultCoef()));
	}


}
