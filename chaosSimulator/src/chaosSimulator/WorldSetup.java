package chaosSimulator;

public interface WorldSetup {
	void setupWorld(World world, int num);
	
	default void defaultSetupWorld(World world, int num) {
		
	}
}
