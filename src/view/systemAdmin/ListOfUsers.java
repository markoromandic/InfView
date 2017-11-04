package view.systemAdmin;

import javax.swing.JList;

import appCore.Core;
import model.User;
import view.storageAdmin.ListCellRenderer;

public class ListOfUsers extends JList<User>
{
	private IVListUserModel userModel = null;
	
	public ListOfUsers()
	{
		initialize();
	}
	
	private void initialize()
	{
		userModel = new IVListUserModel();
		ListCellRenderer listCellRenderer = new ListCellRenderer();
		setCellRenderer(listCellRenderer);
		
		setModel(userModel);
		
		addKeyListener(Core.getInstance().getActionManager().getInfoUser());
		addMouseListener(Core.getInstance().getActionManager().getInfoUser());
	}

	public IVListUserModel getUserModel()
	{
		return userModel;
	}
}
