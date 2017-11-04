package actions;

import java.awt.event.ActionEvent;

public class AddRecordTab extends AbstractEditorAction {
	
	public AddRecordTab() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "Add Record");
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Record added");
	}
}
