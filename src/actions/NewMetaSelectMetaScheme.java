package actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import appCore.Core;

public class NewMetaSelectMetaScheme extends MouseAdapter
{
	@Override
	public void mouseClicked(MouseEvent e)
	{
		super.mouseClicked(e);
		
		Core.getInstance().getStorageAdminView().getNewMetaSchemeDialog().selectMetaScheme();
	}
}
