package view.systemAdmin;

import javax.swing.DefaultListModel;

import appCore.Core;
import model.User;

public class IVListUserModel extends DefaultListModel<User>
{
	public IVListUserModel()
	{
		initialize();
	}
	
	private void initialize()
	{
		for (User u : Core.getInstance().getModel().getUserModel().getUsers())
		{
			addElement(u);
		}
	}
	
	public void refresh()
	{
		clear();
		for (User u : Core.getInstance().getModel().getUserModel().getUsers())
		{
			addElement(u);
		}
	}
}
