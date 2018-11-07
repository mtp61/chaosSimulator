package chaosSimulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//double buffering
	private Image dbImage;
	private Graphics dbg;
	//gamevars
	private Thread thread1;
	private volatile boolean running = false;
	

	MouseListener listener = new MouseListener(this);

	private World world = new World();
	

	public DisplayPanel() {
		this.setPreferredSize(Main.screensize);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.requestFocus();
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		
	}
	
	
	//main function
	public void run() {
		
		init();
		
		//this is shitty code and needs to be cleaned up
		int fps = 600; //this not shit
		int speed = 5; //this not shit
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				gameUpdate((double)fps/speed);
				gameRender();
				paintScreen();
				delta--;
				ticks++;
			}
			
			if(timer >= 1000000000) {
				System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
			

		}
	}
	
	
	
	private void paintScreen() {
		Graphics g;
		try {
			g = this.getGraphics();
			if(dbImage != null || g != null){
				g.drawImage(dbImage, 0, 0, this);
			}
			g.dispose();
		}catch(Exception e) {
			System.err.println(e);
		}
		
	}

	private void gameRender() {
		if(dbImage == null) {
			dbImage = createImage(Main.WIDTH, Main.HEIGHT);
			if(dbImage == null) {
				System.err.println("dbImage is still null");
				return;
			}else {
				dbg = dbImage.getGraphics();
			}
		}
		//clears the screen before we draw to it
		dbg.setColor(Color.WHITE);
		dbg.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		//draws the game elements
		draw(dbg);
	}


	@Override
	public void addNotify() {
		super.addNotify();
		startSimulation();
	}
	
	
	private void startSimulation() {
		if(thread1 == null || !running) {
			thread1 = new Thread(this);
			thread1.start();
			running = true;
		}
	}
	
	public void stopSimulation() {
		if(running) {
			running = false;
		}
	}
	
	
	
	
	//setup simulation 
	private void init() {
		
		
	}
	
	
	
	
	//update state of simulation
	private void gameUpdate(double framerate) {
		if(running && thread1 != null) {
			world.tick(framerate);
			
		}
		
	}
	
	private void draw(Graphics g) {
		//everything drawn in this function

		//draw arm
		g.setColor(Color.GREEN);
		g.fillOval((int)world.getArmX()-10,(int)world.getArmY()-10,20,20);
		
		
		//draw magnets
		g.setColor(Color.RED);
		for(int i = 0; i < Magnet.totalMagnets; i++) {
			Magnet m = world.getMagnets().get(i);
			g.fillOval(m.getXPos()-10,m.getYPos()-10,20,20);
		}
		
		
		//draw home (where the arm returns to)
		g.setColor(Color.BLACK);
		g.fillOval((int)world.getHomeX()-5,(int)world.getHomeY()-5,10,10);
			
	}
	
	public World getWorld() {
		return this.world;
	}
	
}


