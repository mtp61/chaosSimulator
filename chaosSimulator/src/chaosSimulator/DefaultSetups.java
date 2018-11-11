package chaosSimulator;

import java.util.ArrayList;

public class DefaultSetups {
	
	private static int homeX = Main.screensize.width/2;
	private static int homeY = Main.screensize.width/2;
	
	public enum DEFAULTSETUPS {
		SQUARE, TRIANGLE, HEXAGON, GRID, CIRCLE, CROSS, THREETHREE
	}
	private static int numOfSetups = 7; //THIS MUST BE CHANGED TO MATCH THE NUMBER OF ITEMS IN THE ENUM
	
	public static DEFAULTSETUPS setup = DEFAULTSETUPS.SQUARE;
	
	public static ArrayList<WorldSetup> setupFunctions = new ArrayList<WorldSetup>();
	
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
	public static void createSetupFuncs() {
		setupFunctions.add((World world, int num) -> {
			world.addMagnet(new Magnet(homeX,homeY+num,world.getDefaultCoef()));
			world.addMagnet(new Magnet(homeX+num,homeY+num,world.getDefaultCoef()));
			world.addMagnet(new Magnet(homeX+num,homeY,world.getDefaultCoef()));
			world.addMagnet(new Magnet(homeX+num,homeY-num,world.getDefaultCoef()));
			world.addMagnet(new Magnet(homeX,homeY-num,world.getDefaultCoef()));
			world.addMagnet(new Magnet(homeX-num,homeY-num,world.getDefaultCoef()));
			world.addMagnet(new Magnet(homeX-num,homeY,world.getDefaultCoef()));
			world.addMagnet(new Magnet(homeX-num,homeY+num,world.getDefaultCoef()));
		});
	}


}
