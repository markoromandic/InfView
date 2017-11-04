package actions;

import java.awt.event.ActionEvent;

public class DeleteRecordTab extends AbstractEditorAction {
	
	public DeleteRecordTab() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "Delete Record");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Record deleted");
	}
	
}
