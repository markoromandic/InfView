package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appCore.Core;
import model.User;

public class AddUser implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		User newUser = Core.getInstance().getUsersAdmin().getNewUserDialog().addUser();
		
		String username = newUser.getUsername();
		String email = newUser.getEmail();
		
		boolean check = Core.getInstance().getModel().getUserModel().checkNewUser(username, email);
		
		if(!check)
			System.err.println("EXIST");
		else
		{
			Core.getInstance().getModel().getUserModel().addNewUser(newUser);
			Core.getInstance().getUsersAdmin().getListOfUsers().getUserModel().refresh();
			Core.getInstance().getUsersAdmin().getNewUserDialog().close();
		}
	}
}
