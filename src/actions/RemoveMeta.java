package actions;

import java.awt.event.ActionEvent;

import appCore.Core;
import model.MetaScheme;

public class RemoveMeta extends AbstractEditorAction
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		MetaScheme rm = Core.getInstance().getStorageAdminView().getSelectedMetaScheme();
		
		Core.getInstance().getModel().getUserModel().removeMetaScheme(rm);
		Core.getInstance().getStorageAdminView().getListOfMetaSchemes().getListModel().refresh();
	}
}
