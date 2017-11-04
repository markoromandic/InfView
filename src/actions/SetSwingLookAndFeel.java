package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import appCore.Core;
import constants.MenuConstants;
/**
 * Akcija za promenu teme aplikacije
 * @author Romandic
 */
public class SetSwingLookAndFeel extends AbstractEditorAction
{
	public SetSwingLookAndFeel()
	{
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.SHIFT_MASK));
		putValue(NAME, MenuConstants.THEME_TITLE);
		putValue(SMALL_ICON, loadIcon(MenuConstants.LOOK_AND_FEEL_ICON)); 
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(Core.getInstance().getMainViewA() != null)
			Core.getInstance().getMainViewA().crossPlatformLookAndFeel();
		else if(Core.getInstance().getMainViewMa() != null)
			Core.getInstance().getMainViewMa().crossPlatformLookAndFeel();
		else if(Core.getInstance().getMainViewOu() != null)
			Core.getInstance().getMainViewOu().crossPlatformLookAndFeel();
		else if(Core.getInstance().getStorageAdminView() != null)
			Core.getInstance().getStorageAdminView().crossPlatformLookAndFeel();
		else if(Core.getInstance().getLogInView() != null)
			Core.getInstance().getLogInView().crossPlatformLookAndFeel();
	}

}
