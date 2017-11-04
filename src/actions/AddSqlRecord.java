package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Structure.Entity;
import Structure.Storage;
import appCore.Core;
import model.DatabaseConnection;
import view.AddFrame;

public class AddSqlRecord implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int index = Core.getInstance().getMainViewOu().getTableView().getSelectedIndex();
		Entity ent = Core.getInstance().getMainViewOu().getListaEntitetaUTabelama().get(index);
		
		new AddFrame(ent);
		
//		String url = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUrl();
//		String username = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUsername();
//		String password = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getPassword();
//		DatabaseConnection reader = Core.getInstance().getModel().getDatabaseConnection();
//		reader.initialize(url, username, password);
	}

}
