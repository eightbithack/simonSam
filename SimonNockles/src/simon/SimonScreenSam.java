package simon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import guiPractice.sampleGames.ClickableScreen;

public class SimonScreenSam extends ClickableScreen implements Runnable {
	
	private TextLabel label;
	private ButtonInterfaceSam[] button;
	private ProgressInterfaceSam progress;
	private ArrayList<MoveInterfaceSam> moveset;
	
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton = -1;

	public SimonScreenSam(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
		
	}

	@Override
	public void run() {
		label.setText("");
	    nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber++;
		randomMove();
		progress.setRound(roundNumber);
		progress.setSequenceSize(moveset.size());
		changeText("Simon's Turn");
		label.setText("");
		playSequence();
		changeText("Your Turn");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private void playSequence() {
		ButtonInterfaceSam b = null;
		for (int i = 0; i < moveset.size(); i++){
			if(b == null){
				b.dimlight();
				b = moveset.get(i).getButton();
				b.lightup();
				int sleepTime = 1000/roundNumber;
				try{
					Thread.sleep(sleepTime);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		b.dimlight();
		
	}

	private void changeText(String s) {
		try{
			label.setText(s);
			Thread.sleep(1000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
		private MoveInterfaceSam randomMove() {
			ButtonInterfaceSam b;
			int randMove = (int)(Math.random() * button.length);
			while (randMove == lastSelectedButton){
				randMove = (int)(Math.random() * button.length);
			}
			lastSelectedButton = randMove;
			b = button[randMove];
			return getMove(b);
		}

		

	private MoveInterfaceSam getMove(ButtonInterfaceSam b) {
			// TODO Auto-generated method stub
			return null;
		}

		//Placeholder until partner finishes implementation of ProgressInterface
	private ProgressInterfaceSam getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	private void addButtons(List<Visible> viewObjects) {
		int numberOfButtons = 5;
		Color[] colors = new Color[5];
		colors[0] = Color.gray;
		colors[1] = Color.blue;
		colors[2] = Color.black;
		colors[3] = Color.red;
		colors[4] = Color.green;
		for (int i = 0; i < numberOfButtons; i++){
			final ButtonInterfaceSam b = getAButton();
			b.setColor(colors[i]);
			b.setX(i * 30);
			b.setY(50);
			b.setAction(new Action(){

				public void act(){
					if (acceptingInput){
						Thread blink = new Thread(new Runnable(){
						public void run(){
							b.lightup();
							try{
								Thread.sleep(800);
							}
							catch(InterruptedException e){
								e.printStackTrace();
							}
							b.dimlight();
						}

					
						});
						blink.start();
						if (b == moveset.get(sequenceIndex).getButton()){
							sequenceIndex++;
						}
						else{
							progress.GameOver();
						}
						if(sequenceIndex == moveset.size()){
							Thread nextRound = new Thread(SimonScreenSam.this);
							nextRound.start(); 
						}
						viewObjects.add(b);
					}
				}
				
				

			});
		}
	}

	private ButtonInterfaceSam getAButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		moveset = new ArrayList<MoveInterfaceSam>();
		//add 2 moves to start
		lastSelectedButton = -1;
		moveset.add(randomMove());
		moveset.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
		
	}

}
