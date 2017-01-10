package simon;

import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Clickable;

public interface ButtonInterfaceSam extends Clickable {

	void setColor(Color color);

	void setX(int i);

	void setY(int i);

	void setAction(Action action);

	void lightup();

	void dimlight();
	
	void setName(String name);
	

}
