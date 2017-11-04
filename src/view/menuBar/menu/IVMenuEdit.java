package view.menuBar.menu;

import javax.swing.JMenu;

import com.sun.glass.events.KeyEvent;

import constants.MenuConstants;

public class IVMenuEdit extends JMenu
{
	public IVMenuEdit()
	{
		initialize();
	}
	
	private void initialize()
	{
		setText(MenuConstants.EDIT_TITLE);
		setMnemonic(KeyEvent.VK_E);
	}
}
