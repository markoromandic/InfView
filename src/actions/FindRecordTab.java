package actions;

import java.awt.event.ActionEvent;

public class FindRecordTab extends AbstractEditorAction{
	
	public FindRecordTab() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "Find Record");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Record found");
	}

}
