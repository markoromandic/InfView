package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appCore.Core;
import model.User;

public class RemoveUser implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		User u = Core.getInstance().getUsersAdmin().getSelectedUser();
		
		Core.getInstance().getModel().getUserModel().removeUser(u);
		Core.getInstance().getUsersAdmin().getListOfUsers().getUserModel().refresh();
	}
}
