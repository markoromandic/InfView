package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import appCore.Core;

public class ApplyUser implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String username = Core.getInstance().getUsersAdmin().getInfoUserDialog().getUsername();
		String email = Core.getInstance().getUsersAdmin().getInfoUserDialog().getEmail();
		String usernameEx = Core.getInstance().getUsersAdmin().getInfoUserDialog().getUsernameEx();
		
		if(Core.getInstance().getModel().getUserModel().checkUser(username, email, usernameEx))
		{
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setNewParametarsOfUser();
			Core.getInstance().getModel().getUserModel().saveUserInfo();
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setNewTitle();
		}
		else
			System.err.println("POSTOJI");
	}
}
