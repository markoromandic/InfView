package actions.test;

import java.awt.event.ActionEvent;

import actions.AbstractEditorAction;
import appCore.Core;

public class OpenOperativeUser extends AbstractEditorAction
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getLogInView().setTextFieldUserName("marko");
		Core.getInstance().getLogInView().setPasswordFieldPassword("marko");
	}

}
