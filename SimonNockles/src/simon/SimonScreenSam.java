package simon;

import java.awt.Color;
import java.util.ArrayList;

import guiPractice.components.Action;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import guiPractice.sampleGames.ClickableScreen;

public class SimonScreenSam extends ClickableScreen implements Runnable {

	private ArrayList<MoveInterfaceSam> moves;
	private TextLabel label;
	private ButtonInterfaceSam[] button;
	private ProgressInterfaceSam progress;
	
	private int roundNumber;
	private boolean incomingInput;
	private int sequenceIndex;
	private int lastSelectedButton;
	

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

	public void nextRound() {
		incomingInput = false;
		roundNumber++;
		moves.add(randomMove());
		progress.setRound(roundNumber);
		progress.setSequenceSize(moves.size());
		changeText("Follow the color sequence!");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		incomingInput = true;
		sequenceIndex =0;
		
	}

	private void playSequence() {
		ButtonInterfaceSam b =null;
		for(MoveInterfaceSam sequence: moves){
			if(b!=null)//{
				b.dimlight();
				b=sequence.getButton();
				b.lightup();
				int sleepTime = (int)(long)(1500*(2.0/(roundNumber+2)));
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		b.dimlight();
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(600);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Simon Game");
		moves = new ArrayList<MoveInterfaceSam>();
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	private MoveInterfaceSam randomMove() {
		int select = (int) (Math.random()*button.length);
		while(select == lastSelectedButton){
			select = (int) (Math.random()*button.length);
		}
		lastSelectedButton = select;
		return new MoveVictor(button[select]);
	}

	private MoveInterfaceSam getMove(ButtonInterfaceSam b) {
		return null;
	}

	private ProgressInterfaceSam getProgress() {
		return new ProgressVictor();
	}

	public void addButtons(ArrayList<Visible> viewObjects) {
		int numberOfButtons = 6;
		Color[] colorArray = {Color.red, Color.blue, new Color(240,160,70), new Color(20,255,140), Color.yellow, new Color(180,90,210)};
		button = new ButtonInterfaceSam[numberOfButtons];
		for(int i =0; i <numberOfButtons;i++){
			button[i] = getAButton();
			button[i].setColor(colorArray[i]);
			button[i].setX(160 + (int)(50+(50*i)));
			button[i].setY(200);
			final ButtonInterfaceSam b = button[i];
			b.dimlight();
			b.setAction(new Action(){
				public void act(){
					if(incomingInput){
						Thread blink = new Thread(new Runnable(){
							
							public void run(){
								b.lightup();
								try{
									Thread.sleep(400);
								} catch (InterruptedException e){
									e.printStackTrace();
								}
								b.dimlight();
							}
							
						});
						
						blink.start();
						
						if(incomingInput && moves.get(sequenceIndex).getButton() == b){
							sequenceIndex++;
						}
						else if(incomingInput){
							progress.GameOver();
							return;
						}
						if(sequenceIndex == moves.size()){
							Thread nextRound = new Thread(SimonScreenSam.this);
							nextRound.start();
						}
					}
				}
			});
			viewObjects.add(b);
		}
	}

	private ButtonInterfaceSam getAButton() {
		return new ButtonVictor();
	}
		

}