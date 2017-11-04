package view.menuBar.menu;

import javax.swing.JMenu;

import com.sun.glass.events.KeyEvent;

import appCore.Core;
import constants.MenuConstants;

public class IVMenuHelp extends JMenu
{
	public IVMenuHelp()
	{
		initialize();
	}
	
	private void initialize()
	{
		setText(MenuConstants.HELP_TITLE);
		setMnemonic(KeyEvent.VK_H);
		
		add(Core.getInstance().getActionManager().getOpenAbout());
	}
}
