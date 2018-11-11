package chaosSimulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

import chaosSimulator.DefaultSetups.DEFAULTSETUPS;

public class DisplayPanel extends JPanel implements Runnable{
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
	//List of the possible setups
	static ArrayList<String> setups = new ArrayList<String>(DefaultSetups.getNumOfSetups());
	//Adds mouse and Key Listeners
	private MouseListener listener = new MouseListener(this);
	private KeyInput keyInput = new KeyInput(this);
	//Timing Variables
	private static final int DELAYS_BEFORE_YIELD = 10;
	//Creates the world
	private World world = new World();
	
	
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
		int fps = 600, speed = 10, delays = 0;
		long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0,timePerTick = 1000000000/fps;
		
		while(running) {
			beforeTime = System.nanoTime();
			
			gameUpdate((double)fps/speed);
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
				System.out.println(delays);
			// the loop was shorter than expected
			} else {
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
		dbg.setColor(Color.WHITE);
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
			g.setColor(Color.GREEN);
			g.fillRect(Main.screensize.width/5, Main.screensize.height/14, Main.screensize.width*3/5, Main.screensize.height/7);
			g.setFont(new Font(g.getFont().toString(), Font.PLAIN, 50));
			FontMetrics metrics = g.getFontMetrics(g.getFont());
			g.setColor(Color.BLACK);
			g.drawString("SIMULATION",Main.screensize.width/5 + ((Main.screensize.width*3/5 - metrics.stringWidth("SIMULATION"))/2), Main.screensize.height/14 + (Main.screensize.height/7 + metrics.getHeight())/2);
			g.setColor(Color.YELLOW);
			g.fillRect(Main.screensize.width/5, Main.screensize.height*4/14, Main.screensize.width*3/5, Main.screensize.height/7);
			g.setColor(Color.BLACK);
			g.drawString("DEFAULT SHAPES",Main.screensize.width/5 + ((Main.screensize.width*3/5 - metrics.stringWidth("DEFAULT SHAPES"))/2), Main.screensize.height*4/14 + (Main.screensize.height/7 + metrics.getHeight())/2);
			g.setColor(Color.RED);
			g.fillRect(Main.screensize.width/5, Main.screensize.height*7/14, Main.screensize.width*3/5, Main.screensize.height/7);
			g.setColor(Color.BLACK);
			g.drawString("PRETTY COLORS",Main.screensize.width/5 + ((Main.screensize.width*3/5 - metrics.stringWidth("PRETTY COLORS"))/2), Main.screensize.height*7/14 + (Main.screensize.height/7 + metrics.getHeight())/2);
		}
		
		if(state == STATES.shapes) {
			if(DefaultSetups.setup == null) {
				int x = 20;
				int y = 100;
				int width = 150;
				int height = 50;
				ArrayList<String> setups = new ArrayList<String>(DefaultSetups.getNumOfSetups());
				for(DefaultSetups.SETUPS ds : DefaultSetups.SETUPS.values()) {
					setups.add(ds.name());
				}

				for(int i = 0; i < DefaultSetups.getNumOfSetups(); i++) {
					g.setColor(Color.GREEN);
					g.fillRect(x, y, width, height);
					g.setColor(Color.BLACK);
					
					//string format
					g.setFont(new Font(g.getFont().toString(), Font.PLAIN, 20));
					FontMetrics metrics = g.getFontMetrics(g.getFont());

 
					g.drawString(setups.get(i), x+((width-metrics.stringWidth(setups.get(i)))/2), y+((height+metrics.getHeight())/2));
					
					if(x < Main.screensize.width - 200) {
						x += 200;
					}else {
						y += 80;
						x = 20;
					}
				}				
			}		
		}
		
		if(state == STATES.game) {
			g.setColor(Color.GREEN);
			g.fillOval((int)world.getArmX()-10,(int)world.getArmY()-10,20,20);
			
			//draw magnets
			for(int i = 0; i < Magnet.totalMagnets; i++) {
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
	
	public static ArrayList<String> getSetups() {return setups;};
}


