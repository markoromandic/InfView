package actions;

import java.awt.event.ActionEvent;

import appCore.Core;

public class SelectNewMetaScheme extends AbstractEditorAction
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getStorageAdminView().getNewMetaSchemeDialog().selectMeta();
	}
}
