package actions.test;

import java.awt.event.ActionEvent;

import actions.AbstractEditorAction;
import appCore.Core;

public class OpenMetaSchemeAdmin extends AbstractEditorAction
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getLogInView().setTextFieldUserName("meta");
		Core.getInstance().getLogInView().setPasswordFieldPassword("meta");
	}

}
