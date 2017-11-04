package view.menuBar.menu;

import javax.swing.JMenu;

import com.sun.glass.events.KeyEvent;

import appCore.Core;
import constants.MenuConstants;

public class IVMenuFile extends JMenu
{
	public IVMenuFile()
	{
		initialize();
	}
	
	private void initialize()
	{
		setText(MenuConstants.FILE_TITLE);
		setMnemonic(KeyEvent.VK_F);
		
		Core.getInstance();
		if(Core.getInstance().isMa() || Core.getInstance().isOp() || Core.getInstance().isSa() || Core.getInstance().isSt() || Core.getInstance().isSu())
		{
			add(Core.getInstance().getActionManager().getSignOut());
			addSeparator();
		}
		
		add(Core.getInstance().getActionManager().getExit());
	}
}
