package chaosSimulator;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Main extends JFrame{
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	public static final Dimension screensize = new Dimension(WIDTH,HEIGHT);
	
	World world = new World();
	
	public Main() {
		//creates a the DisplayPanel
		DisplayPanel dp = new DisplayPanel();
		
		//creates the JFrame with these parameters
		this.setTitle("This shit is magnets");
		this.setSize(screensize);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//adds the JPanel(Display Panel)
		this.add(dp);
		this.setVisible(true);
		
		
	}

	public static void main(String[] args) {
//<<<<<<< HEAD
		new Main();
		
//=======
//>>>>>>> stash
	}
	
	private void init() {
		//called on start of program
		
	}
	
	private void tick(double time) {//time is the fraction of a second to calculate for
		//get input
		
		
		//create/delete magnets
		
		
		//update positions
		System.out.println(world.getArmX());
	}
	
	private void render() {
		//render magnets, arm
	}

}
