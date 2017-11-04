package actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import appCore.Core;

public class Forgot extends MouseAdapter
{
	@Override
	public void mousePressed(MouseEvent e)
	{
		super.mousePressed(e);
		
		Core.getInstance().getLogInView().changeClick();
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		super.mouseClicked(e);

		System.err.println("IN DEVELOPMENT");
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		super.mouseReleased(e);
		
		Core.getInstance().getLogInView().changeLinkBack();
	}
}
