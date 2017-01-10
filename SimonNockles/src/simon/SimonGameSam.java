package simon;

import guiPractice.GUIApplication;


public class SimonGameSam extends GUIApplication {

	public SimonGameSam(int i, int j) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initScreen() {
		SimonScreenSam work = new SimonScreenSam(getWidth(), getHeight());
		setScreen(work);

	}

	public static void main(String[] args) {
		SimonGameSam game = new SimonGameSam(800,800);
		Thread app = new Thread(game);
		app.start();
	}

}
