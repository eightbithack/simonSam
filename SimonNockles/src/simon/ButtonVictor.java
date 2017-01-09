package simon;

import java.awt.Color;
import java.awt.Graphics2D;
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
		
		
	}

}
