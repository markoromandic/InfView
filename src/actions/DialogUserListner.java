package actions;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import appCore.Core;
import constants.DialogConstants;

public class DialogUserListner implements WindowListener
{

	@Override
	public void windowOpened(WindowEvent e)
	{
	}

	@Override
	public void windowClosing(WindowEvent e)
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
				System.err.println("POSTOJI");
			
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

	@Override
	public void windowClosed(WindowEvent e)
	{
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}

}
