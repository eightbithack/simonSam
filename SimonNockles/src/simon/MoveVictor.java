package simon;

public class MoveVictor implements MoveInterfaceSam {

	private ButtonInterfaceSam b;
	
	public MoveVictor(ButtonInterfaceSam b) {
		this.b = b;
	}

	@Override
	public ButtonInterfaceSam getButton() {
		// TODO Auto-generated method stub
		return b;
	}

}
