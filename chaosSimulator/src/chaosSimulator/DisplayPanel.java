package chaosSimulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import input.MouseListener;
import states.Menu;
import states.Shapes;
import input.KeyInput;

public class DisplayPanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	//double buffering variables
	private Image dbImage;
	private Graphics dbg;
	//thread variables
	private Thread thread1;
	private volatile boolean running = false;
	//game states
	public enum STATES {
		menu,
		game,
		shapes,
		simulation
	}
	private STATES state = STATES.menu;
	public STATES getState() {return state;}
	public void setState(STATES state) {this.state = state;}
	//Adds mouse and Key Listeners
	private MouseListener listener = new MouseListener(this);
	private KeyInput keyInput = new KeyInput(this);
	//Timing Variables
	private static final int DELAYS_BEFORE_YIELD = 10;
	//Creates the world
	private World world = new World();
	//Create Menu
	Menu menu = new Menu(this);
	Shapes shapes = new Shapes(this);
	
	
	
	//Class Constructor makes the DisplayPanel
	public DisplayPanel() {
		this.setPreferredSize(Main.screensize);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.requestFocus();
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.addKeyListener(keyInput);
	}
	
	//main function
	@SuppressWarnings("static-access")
	public void run() {
		int fps = 60, speed = 600, delays = 0;
		long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0,timePerTick = 1000000000/speed;
		
		while(running) {
			beforeTime = System.nanoTime();
			
			gameUpdate((double)fps);
			gameRender();
			paintScreen();
			
			afterTime = System.nanoTime();
			diff = afterTime - beforeTime;
			sleepTime = (timePerTick - diff) - overSleepTime;
			
			if(sleepTime < timePerTick && sleepTime > 0) {
			//the thread can try to sleep 
				try {
					thread1.sleep(sleepTime / 1000000L);
					overSleepTime = 0;
				} catch (InterruptedException ex){
					System.err.println(ex);
				}
			//the Difference was greater than the time per tick
			} else if(diff > timePerTick){
				overSleepTime = diff - timePerTick;
		
			//Accumulates delays and tries to yield
			} else if(++delays >= DELAYS_BEFORE_YIELD){
				thread1.yield();
				delays = 0;
				overSleepTime = 0;

			} else if(delays <= DELAYS_BEFORE_YIELD) {
				//nothing
			} else {
				// the loop was shorter than expected
				overSleepTime = 0;
			}
	
		}
	}
	
	//update state of simulation
	private void gameUpdate(double framerate) {
		if(running && thread1 != null) {
			if(state == STATES.game) {
				//input
				keyInput.tick();
				if (keyInput.getKeys()[82] == 1) {
					world.resetWorld();
				}
				
				//update world
				if (!keyInput.getIsPaused()) {
					world.tick(framerate);
				}
			}
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
		dbg.setColor(Color.LIGHT_GRAY);
		dbg.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		//draws the game elements
		draw(dbg);
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
			
	private void draw(Graphics g) {
		//everything drawn in this function
		if(state == STATES.menu) {
			menu.draw(g);
		}
		if(state == STATES.shapes) {
			shapes.draw(g);	
		}
		if(state == STATES.game) {
			g.setColor(Color.GRAY);
			g.fillOval((int)world.getArmX()-10,(int)world.getArmY()-10,20,20);
			
			//draw magnets
			for(int i = 0; i < this.getWorld().getMagnets().size(); i++) {
				Magnet m = world.getMagnets().get(i);
				if(m.getCoef() < 0) {
					g.setColor(Color.BLUE);
				} else {
					g.setColor(Color.RED);
				}
				g.fillOval(m.getXPos()-10,m.getYPos()-10,20,20);
			}
			
			//draw home (where the arm returns to)
			g.setColor(Color.BLACK);
			g.fillOval((int)world.getHomeX()-5,(int)world.getHomeY()-5,10,10);
			
			
			//draw pause text in middle of screen
			if (keyInput.getIsPaused()) {
				g.setColor(Color.BLACK);
				g.setFont(new Font(g.getFont().toString(), Font.PLAIN, 50));
				g.drawString("PAUSED", Main.WIDTH/2-100, 100);
			}
		}
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public Menu getMenu() {
		return this.menu;
	}
	
	public Shapes getShapes() {
		return this.shapes;
	}
}