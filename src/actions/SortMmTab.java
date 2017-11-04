package actions;

import java.awt.event.ActionEvent;

public class SortMmTab extends AbstractEditorAction{
	
	public SortMmTab() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "Sort MM");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("MM sorted");
	}

}
