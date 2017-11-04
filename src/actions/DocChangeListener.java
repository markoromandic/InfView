package actions;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import appCore.Core;

public class DocChangeListener implements DocumentListener
{

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		if(Core.getInstance().isSt())
		{
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().setEnabledApply();
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
		else if(Core.getInstance().isSu())
		{
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setEnabledApply();
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		if(Core.getInstance().isSt())
		{
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().setEnabledApply();
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
		else if(Core.getInstance().isSu())
		{
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setEnabledApply();
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		if(Core.getInstance().isSt())
		{
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().setEnabledApply();
			Core.getInstance().getStorageAdminView().getMetaSchemeDialog().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
		else if(Core.getInstance().isSu())
		{
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setEnabledApply();
			Core.getInstance().getUsersAdmin().getInfoUserDialog().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}

}
