package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import Structure.Entity;
import appCore.Core;
import view.UpdateTab;
import view.table.IVTable;

public class UpdateSqlRecord implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int index = Core.getInstance().getMainViewOu().getTableView().getSelectedIndex();
		Entity ent = Core.getInstance().getMainViewOu().getListaEntitetaUTabelama().get(index);
		IVTable currentIV = Core.getInstance().getMainViewOu().getListaTabla().get(index);
		index = currentIV.getSelectedRow();
		
		new UpdateTab(ent);
	}

}
