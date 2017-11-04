package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Structure.Entity;
import appCore.Core;
import view.SearchFrame;

public class FindSqlRecord implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("FIND");
		int index = Core.getInstance().getMainViewOu().getTableView().getSelectedIndex();
		Entity ent = Core.getInstance().getMainViewOu().getListaEntitetaUTabelama().get(index);
		new SearchFrame(ent);
	}
}
