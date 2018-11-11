package chaosSimulator;

import java.util.ArrayList;

public class DefaultSetups {
	
	private static int homeX = Main.screensize.width/2;
	private static int homeY = Main.screensize.width/2;
	
	public static enum SETUPS {
		SQUARE, TRIANGLE, HEXAGON, GRID, CIRCLE, CROSS, THREETHREE
	}
	private static int numOfSetups = 7; //THIS MUST BE CHANGED TO MATCH THE NUMBER OF ITEMS IN THE ENUM
	public static SETUPS setup = null;
	public static int getNumOfSetups() {return numOfSetups;}

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

}
