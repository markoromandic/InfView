package actions.test;

import java.awt.event.ActionEvent;

import actions.AbstractEditorAction;
import appCore.Core;

public class OpenStorageAdmin extends AbstractEditorAction
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getLogInView().setTextFieldUserName("storage");
		Core.getInstance().getLogInView().setPasswordFieldPassword("storage");
	}
}
