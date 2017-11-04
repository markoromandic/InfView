package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import Structure.Entity;
import Structure.Storage;
import appCore.Core;
import model.DatabaseConnection;
import view.table.IVTable;

public class DeleteSqlRecord implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("DELETE");
		
		int index = Core.getInstance().getMainViewOu().getTableView().getSelectedIndex();
		Entity ent = Core.getInstance().getMainViewOu().getListaEntitetaUTabelama().get(index);
		IVTable currentIV = Core.getInstance().getMainViewOu().getListaTabla().get(index);
		index = currentIV.getSelectedRow();
		Vector<String> v = new Vector<>();
		
		System.out.println(ent.getName());
		for(int i = 0 ; i<currentIV.getColumnCount(); i++){
			v.add((String)currentIV.getValueAt(index, i));
		}
		String url = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUrl();
		String username = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUsername();
		String password = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getPassword();
		DatabaseConnection reader = Core.getInstance().getModel().getDatabaseConnection();
		reader.initialize(url, username, password);
		try {
			reader.deleteEntity(ent, v);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
