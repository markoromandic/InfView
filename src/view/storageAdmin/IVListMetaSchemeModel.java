package view.storageAdmin;

import javax.swing.DefaultListModel;

import appCore.Core;
import model.MetaScheme;

public class IVListMetaSchemeModel extends DefaultListModel<MetaScheme>
{
	public IVListMetaSchemeModel()
	{
		initialize();
	}
	
	private void initialize()
	{
		for (MetaScheme mScheme : Core.getInstance().getModel().getUserModel().getMetaSchemes())
		{
			addElement(mScheme);
		}
	}
	
	public void refresh()
	{
		clear();
		for (MetaScheme mScheme : Core.getInstance().getModel().getUserModel().getMetaSchemes())
		{
			addElement(mScheme);
		}
	}
}
