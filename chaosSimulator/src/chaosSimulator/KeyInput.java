package chaosSimulator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//handles key in[ut
public class KeyInput implements KeyListener{
	private int[] keys;
	
	private boolean isPaused = false;
	private boolean unPressed = true;
	
	public KeyInput() {
		keys = new int[256];
	}
	
	public void tick() {
		if (unPressed && keys[32] == 1) {
			isPaused = !isPaused;
		}
		
		unPressed = keys[32] == 0;
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = 1;
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = 0;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public int[] getKeys() {return keys;}
	public boolean getIsPaused() {return isPaused;}
	
}
