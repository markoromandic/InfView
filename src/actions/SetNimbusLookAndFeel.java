package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import appCore.Core;
import constants.MenuConstants;

public class SetNimbusLookAndFeel extends AbstractEditorAction
{
	public SetNimbusLookAndFeel()
	{
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.SHIFT_MASK));
		putValue(NAME, MenuConstants.THEME_TITLE);
		putValue(SMALL_ICON, loadIcon(MenuConstants.LOOK_AND_FEEL_ICON)); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(Core.getInstance().getMainViewA() != null)
			Core.getInstance().getMainViewA().nimbusPlatformLookAndFeel();
		else if(Core.getInstance().getMainViewMa() != null)
			Core.getInstance().getMainViewMa().nimbusPlatformLookAndFeel();
		else if(Core.getInstance().getMainViewOu() != null)
			Core.getInstance().getMainViewOu().nimbusPlatformLookAndFeel();
		else if(Core.getInstance().getStorageAdminView() != null)
			Core.getInstance().getStorageAdminView().nimbusPlatformLookAndFeel();
		else if(Core.getInstance().getLogInView() != null)
			Core.getInstance().getLogInView().nimbusPlatformLookAndFeel();
	}
}
