package view.menuBar.menu;

import javax.swing.JMenu;
import com.sun.glass.events.KeyEvent;

import appCore.Core;
import constants.MenuConstants;
import view.menuBar.menu.settings.MenuLookAndFeel;

public class IVMenuSettings extends JMenu
{
	private MenuLookAndFeel menuLookAndFeel = null;
	
	public IVMenuSettings()
	{
		initialize();
	}
	
	private void initialize()
	{
		setText(MenuConstants.SETTINGS_TITLE);
		setMnemonic(KeyEvent.VK_S);
		
		menuLookAndFeel = new MenuLookAndFeel();
		
		if(Core.getInstance().getLogInView() != null)
		{
			add(Core.getInstance().getActionManager().getOpenChooseMetaDialog());
		}
		
		add(menuLookAndFeel);
	}

	public MenuLookAndFeel getMenuLookAndFeel()
	{
		return menuLookAndFeel;
	}
}
