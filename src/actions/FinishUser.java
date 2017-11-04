package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appCore.Core;
import constants.DialogConstants;

public class FinishUser implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getUsersAdmin().getInfoUserDialog().askForSave();
		if(Core.getInstance().getUsersAdmin().getInfoUserDialog().getCheckYes().equals(DialogConstants.Y))
		{
			String username = Core.getInstance().getUsersAdmin().getInfoUserDialog().getUsername();
			String email = Core.getInstance().getUsersAdmin().getInfoUserDialog().getUsername();
			String usernameEx = Core.getInstance().getUsersAdmin().getInfoUserDialog().getUsernameEx();
			
			if(Core.getInstance().getModel().getUserModel().checkUser(username, email, usernameEx))
			{
				Core.getInstance().getUsersAdmin().getInfoUserDialog().setNewParametarsOfUser();
				Core.getInstance().getModel().getUserModel().saveUserInfo();
				Core.getInstance().getUsersAdmin().getInfoUserDialog().deleteThis();
			}
			else
			{
				System.err.println("POSTOJI");
			}
				
		}
		else if(Core.getInstance().getUsersAdmin().getInfoUserDialog().getCheckYes().equals(DialogConstants.N))
		{
			Core.getInstance().getUsersAdmin().getInfoUserDialog().deleteThis();
		}
		else if(Core.getInstance().getUsersAdmin().getInfoUserDialog().getCheckYes().equals(DialogConstants.C))
		{}
		else 
		{
			Core.getInstance().getUsersAdmin().getInfoUserDialog().deleteThis();
		}
	}

}
