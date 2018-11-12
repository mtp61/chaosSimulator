package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import chaosSimulator.DisplayPanel;
import chaosSimulator.DisplayPanel.STATES;
import chaosSimulator.Magnet;
import chaosSimulator.Main;
import chaosSimulator.World;

public class Shapes {
	private static int homeX = Main.screensize.width/2;
	private static int homeY = Main.screensize.width/2;
	private ArrayList<String> shapes = new ArrayList<>(
			Arrays.asList("SQUARE", "TRIANGLE", "HEXAGON", "GRID", "CIRCLE", "CROSS", "THREETHREE")
			);
	int bStartX = 20;
	int bStartY = 100;
	int bWidth = 150;
	int bHeight = 50;
	int xGap = 50;
	int yGap = 30;
	DisplayPanel displayPanel;
	
	public Shapes(DisplayPanel dp) {
		this.displayPanel = dp;
	}
	
	public void draw(Graphics g) {
		int x;
		int y;

		for(int i = 0; i < shapes.size(); i++) {
			x = bStartX + (i-(4*(i/4)))*(bWidth + xGap);
			y = bStartY + (i/4)*(bHeight + yGap);
			g.setColor(Color.GREEN);
			g.fillRect(x, y, bWidth, bHeight);
			g.setColor(Color.BLACK);
			//string format
			g.setFont(new Font(g.getFont().toString(), Font.PLAIN, 20));
			FontMetrics metrics = g.getFontMetrics(g.getFont());
			g.drawString(shapes.get(i), x+((bWidth-metrics.stringWidth(shapes.get(i)))/2), y+((bHeight+metrics.getHeight())/2));
		}			
	}
	
	public void listen(double mouseX, double mouseY) {
		int x = bStartX;
		int y = bStartY;
		for(int i = 0; i < shapes.size(); i++) {
			x = bStartX + (i-(4*(i/4)))*(bWidth + xGap);
			y = bStartY + (i/4)*(bHeight + yGap);
			
			if(mouseX > x && mouseX < x + bWidth && mouseY > y && mouseY < y + bHeight) {
				System.out.println(i);
				callShapeFunction(i);
				break;
			}
		}
	}
	
	private void callShapeFunction(int function) {
		switch(function) {
		case 0:
			square(this.displayPanel.getWorld(), 50);
			System.out.println(this.displayPanel.getWorld().getMagnets());
			break;
		case 1:
			triangle(this.displayPanel.getWorld(), 50);
			break;
		case 2:
			hexagon(this.displayPanel.getWorld(), 50);
			break;
		case 3:
			grid(this.displayPanel.getWorld(), 50);
			break;
		case 4:
			circle(this.displayPanel.getWorld(), 50);
			break;
		case 5:
			cross(this.displayPanel.getWorld(), 50);
			break;
		case 6:
			threeThree(this.displayPanel.getWorld(), 50);
			break;
		default:
			System.out.println("error in switch statement");
		}
		this.displayPanel.setState(STATES.game);
	}

	public ArrayList<String> getShapes(){
		return shapes;
	}
	
	/**
	 * 
	 * ALL OF THE SHAPE FUNCTIONS COME BELOW
	 * NAME THEM THE SAME AS THE STRING IN THE ARRAY
	 * 
	 */
	
	public void square(World world, int distance) {
		world.addMagnet(new Magnet(homeX,homeY+distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX+distance,homeY+distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX+distance,homeY,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX+distance,homeY-distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX,homeY-distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX-distance,homeY-distance,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX-distance,homeY,world.getDefaultCoef()));
		world.addMagnet(new Magnet(homeX-distance,homeY+distance,world.getDefaultCoef()));
	}
	
	private void triangle(World world, int i) {
		// TODO Auto-generated method stub
		
	}
	
	private void hexagon(World world, int i) {
		// TODO Auto-generated method stub
		
	}
	
	private void grid(World world, int i) {
		// TODO Auto-generated method stub
		
	}
	
	private void circle(World world, int i) {
		// TODO Auto-generated method stub
		
	}
	
	private void cross(World world, int i) {
		// TODO Auto-generated method stub
		
	}
	
	private void threeThree(World world, int i) {
		// TODO Auto-generated method stub
		
	}

	
}
