package actions.test;

import java.awt.event.ActionEvent;

import actions.AbstractEditorAction;
import appCore.Core;

public class OpenSystemAdmin extends AbstractEditorAction
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getLogInView().setTextFieldUserName("admin");
		Core.getInstance().getLogInView().setPasswordFieldPassword("admin");
	}
}
