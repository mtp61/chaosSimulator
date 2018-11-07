package chaosSimulator;

import java.awt.Color;
 
import javax.swing.JPanel;

public class Menu extends JPanel{
	
	MouseListener listener = new MouseListener(this);
	
	public Menu() {
		this.setPreferredSize(Main.screensize);
		this.setBackground(Color.BLUE);
		this.setFocusable(true);
		this.addMouseListener(listener);
	}
}
