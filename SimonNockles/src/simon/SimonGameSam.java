package simon;

import guiPractice.GUIApplication;
import whackAMole.WhackAMoleGame;
import whackAMole.WhackAMoleScreen;

public class SimonGameSam extends GUIApplication {

	public SimonGameSam() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initScreen() {
		SimonScreenSam work = new SimonScreenSam(getWidth(), getHeight());
		setScreen(work);

	}

	public static void main(String[] args) {
		SimonGameSam game = new SimonGameSam();
		Thread app = new Thread(game);
		app.start();
	}

}
