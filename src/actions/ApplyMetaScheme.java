package actions;

import java.awt.event.ActionEvent;

import appCore.Core;

public class ApplyMetaScheme extends AbstractEditorAction
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getStorageAdminView().getMetaSchemeDialog().setNewParametarsOfMetaScheme();
		Core.getInstance().getModel().getUserModel().saveMetaSchemesInfo();
	}
}
