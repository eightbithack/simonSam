package simon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class ButtonVictor extends Component implements ButtonInterfaceSam {
	
	private static final int WIDTH = 80;
	private static final int HEIGHT = 80;
	private Action action;
	private Color color;
	private Color displayColor;
	private boolean highlight;

	public ButtonVictor(int x, int y, int w, int h) {
		super(x, y, WIDTH, HEIGHT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		action.act();
		
	}

	@Override
	public boolean isHovered(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
		displayColor = color;
		update();
		
	}

	@Override
	public void setAction(Action action) {
		this.action = action;
		
	}

	@Override
	public void lightup() {
		if(color != null){
			displayColor = color;
		}
		highlight = true;
		update();
		
	}

	@Override
	public void dimlight() {
		displayColor = Color.gray;
		highlight = false;
		update();
		
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) g.setColor(displayColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawOval(0, 0, WIDTH-1, HEIGHT-1);
		if(highlight){
			g.setColor(Color.white);
			Polygon p = new Polygon();
			
			int s = (int)(5/8.0 * WIDTH);
			int t = (int)(1.0/5*HEIGHT)+4;
			p.addPoint(s-4, t-4);
			p.addPoint(s+7, t-2);
			p.addPoint(s+10, t);
			p.addPoint(s+14, t+10);
			p.addPoint(s+12, t+14);
			p.addPoint(s+8, t+3);
			g.fill(p);
		}
		
	}

}
