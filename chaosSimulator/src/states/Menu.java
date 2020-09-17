package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import chaosSimulator.DisplayPanel;
import chaosSimulator.Main;
import chaosSimulator.DisplayPanel.STATES;

public class Menu{
	private DisplayPanel displayPanel;
	private int bWidth = Main.screensize.width*3/5;
	private int bHeight = Main.screensize.height/7;
	private int bGap = Main.screensize.height/14;
	private int bX = Main.screensize.width/5;
	private int bStartY = bHeight;
	
	public Menu(DisplayPanel dp){
		this.displayPanel = dp;
	}

	public void draw(Graphics g) {
		g.setFont(new Font(g.getFont().toString(), Font.PLAIN, 50));
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		g.setColor(Color.BLACK);
		g.drawString("Welcome To 'This Is Magnets'",Main.screensize.width-metrics.stringWidth("Welcome To 'This Is Magnets'")-6, 75);
		g.setColor(Color.RED);
		g.fillRect(bX, bStartY, bWidth, bHeight);
		g.setColor(Color.BLACK);
		g.drawString("SIMULATION", bX + ((bWidth - metrics.stringWidth("SIMULATION"))/2), bStartY + (bHeight + metrics.getHeight())/2);
		g.setColor(Color.RED);
		g.fillRect(bX, bStartY + bHeight + bGap, bWidth, bHeight);
		g.setColor(Color.BLACK);
		g.drawString("DEFAULT SHAPES", bX + ((bWidth - metrics.stringWidth("DEFAULT SHAPES"))/2), bStartY + bHeight + bGap + (bHeight + metrics.getHeight())/2);
		g.setColor(Color.RED);
		g.fillRect(bX, bStartY + 2*bHeight + 2*bGap, bWidth, bHeight);
		g.setColor(Color.BLACK);
		g.drawString("PRETTY COLORS", bX + ((bWidth - metrics.stringWidth("PRETTY COLORS"))/2), bStartY + 2 * bHeight + 2 * bGap + (bHeight + metrics.getHeight())/2);
	}	
	
	public void listen(double mouseX, double mouseY) {
		if(mouseX >= bX && mouseY >= bStartY && mouseX <= bX + bWidth && mouseY <= bStartY + bHeight) {
			displayPanel.setState(STATES.game);
		}
		if(mouseX >= bX && mouseY >= bStartY + bHeight + bGap && mouseX <= bX + bWidth && mouseY <= bStartY + 2*bHeight + bGap) {
			displayPanel.setState(STATES.shapes);
		}
		if(mouseX >= bX && mouseY >= bStartY + 2*bHeight + 2*bGap && mouseX <= bX + bWidth && mouseY <= bStartY + 3*bHeight + 2*bGap) {
			displayPanel.setState(STATES.simulation);
		}
	}
}
