package simon;

import guiPractice.GUIApplication;


public class SimonGameSam extends GUIApplication {

	public SimonGameSam() {
		
	}

	@Override
	public void initScreen() {
		SimonScreenSam s = new SimonScreenSam(getWidth(), getHeight());
		setScreen(s);
	}

	public static void main(String[] args) {
		SimonGameSam game = new SimonGameSam();
		Thread app = new Thread(game);
		app.start();
	}
	
}
