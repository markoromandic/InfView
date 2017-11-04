package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appCore.Core;

public class NewUser implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getUsersAdmin().openNewUserDialog();
	}

}
