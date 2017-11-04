package actions;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import appCore.Core;

public class DocChangeListenerNewMeta implements DocumentListener
{
	@Override
	public void insertUpdate(DocumentEvent e)
	{
		Core.getInstance().getStorageAdminView().getNewMetaSchemeDialog().setAddEnabled();
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		Core.getInstance().getStorageAdminView().getNewMetaSchemeDialog().setAddEnabled();
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		Core.getInstance().getStorageAdminView().getNewMetaSchemeDialog().setAddEnabled();
	}
}
