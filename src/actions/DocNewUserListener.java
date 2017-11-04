package actions;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import appCore.Core;

public class DocNewUserListener implements DocumentListener
{
	
	@Override
	public void insertUpdate(DocumentEvent e)
	{
		Core.getInstance().getUsersAdmin().getNewUserDialog().setAdd(Core.getInstance().getUsersAdmin().getNewUserDialog().checkIsItFilled());
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		Core.getInstance().getUsersAdmin().getNewUserDialog().setAdd(Core.getInstance().getUsersAdmin().getNewUserDialog().checkIsItFilled());
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		Core.getInstance().getUsersAdmin().getNewUserDialog().setAdd(Core.getInstance().getUsersAdmin().getNewUserDialog().checkIsItFilled());
	}

}
