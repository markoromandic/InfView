package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appCore.Core;

public class OpenUserAdmin implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getLogInView().setTextFieldUserName("useradmin");
		Core.getInstance().getLogInView().setPasswordFieldPassword("useradmin");
	}
	
}
