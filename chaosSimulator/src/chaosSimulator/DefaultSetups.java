package chaosSimulator;


public class DefaultSetups {
	public DefaultSetups() {
		
	}
	
	public void setup1(World world) {
		world.addMagnet(new Magnet(450,450,world.getDefaultCoef()));
	}
}
