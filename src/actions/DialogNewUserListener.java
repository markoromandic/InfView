package actions;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import appCore.Core;

public class DialogNewUserListener implements WindowListener
{

	@Override
	public void windowOpened(WindowEvent e)
	{
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		Core.getInstance().getUsersAdmin().getNewUserDialog().close();
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
