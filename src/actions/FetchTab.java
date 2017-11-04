package actions;

import java.awt.event.ActionEvent;

public class FetchTab extends AbstractEditorAction {
	public FetchTab() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "Fetch next block");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Block fetched");
	}
}