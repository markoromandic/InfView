package actions;

import java.awt.event.ActionEvent;

public class SortMdiTab extends AbstractEditorAction{
	
	public SortMdiTab() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "Sort MDI");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("MDI sorted");
	}

}
