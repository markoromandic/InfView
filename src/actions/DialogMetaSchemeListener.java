package actions;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import appCore.Core;
import constants.DialogConstants;

public class DialogMetaSchemeListener implements WindowListener
{

	@Override
	public void windowOpened(WindowEvent e)
	{
	}

	@Override
	public void windowClosing(WindowEvent e)
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

	@Override
	public void windowClosed(WindowEvent e)
	{
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}

}
