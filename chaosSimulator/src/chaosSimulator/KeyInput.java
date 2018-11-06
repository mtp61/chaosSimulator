package chaosSimulator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
	private int[] keys;
	
	public KeyInput() {
		keys = new int[256];
	}
	
	public void tick() {
		
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
	
}
