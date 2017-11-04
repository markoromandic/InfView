package actions;

import java.awt.event.ActionEvent;

public class MakeSekTab extends AbstractEditorAction{
	public MakeSekTab() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "Make .sek");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(".sek made");
	}
}
