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
	
	
	

	public DisplayPanel() {
		this.setPreferredSize(Main.screensize);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void run() {
		while(running) {
			
			gameUpdate();
			gameRender();
			paintScreen();

		}
	}
	
	private void draw(Graphics g) {
		//everything drawn in this function
		g.setColor(Color.BLACK);
		g.drawString("ur a giant nerd matt", 200, 200);
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

	

	private void gameUpdate() {
		if(running && thread1 != null) {
			//update state of simulation
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
	
}


