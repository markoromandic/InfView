package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Structure.Entity;
import appCore.Core;
import view.SortFrame;

public class SortSql implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int index = Core.getInstance().getMainViewOu().getTableView().getSelectedIndex();
		Entity ent = Core.getInstance().getMainViewOu().getListaEntitetaUTabelama().get(index);
		
		new SortFrame(ent);
	}
}
