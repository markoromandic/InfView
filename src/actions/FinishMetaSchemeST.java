package actions;

import java.awt.event.ActionEvent;

import appCore.Core;
import constants.DialogConstants;

public class FinishMetaSchemeST extends AbstractEditorAction
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().getStorageAdminView().getMetaSchemeDialog().askForSave();
		if(Core.getInstance().getStorageAdminView().getMetaSchemeDialog().isCheckYes().equals(DialogConstants.Y))
		{
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().setNewParametarsOfMetaScheme();
			Core.getInstance().getModel().getUserModel().saveMetaSchemesInfo();
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().deleteThis();
		}
		else if(Core.getInstance().getStorageAdminView().getMetaSchemeDialog().isCheckYes().equals(DialogConstants.N))
		{
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().deleteThis();
		}
		else if(Core.getInstance().getStorageAdminView().getMetaSchemeDialog().isCheckYes().equals(DialogConstants.C))
		{}
		else 
		{
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().deleteThis();
		}
	}
}
