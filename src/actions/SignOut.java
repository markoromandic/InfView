package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import appCore.Core;
import constants.MenuConstants;

public class SignOut extends AbstractEditorAction
{ 
	public SignOut()
	{
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.SHIFT_MASK));
		putValue(NAME, MenuConstants.SIGN_OUT_TITLE);
		putValue(SMALL_ICON, loadIcon(MenuConstants.SIGN_OUT_ICON)); 
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Core.getInstance().initializeLogIn();
		
		if(Core.getInstance().isOp())
		{
			Core.getInstance().getMainViewOu().dispose();
			Core.getInstance().setMainViewOu();
		}
		else if(Core.getInstance().isSa())
		{
			Core.getInstance().getMainViewA().dispose();
			Core.getInstance().setMainViewA();
		}
		else if(Core.getInstance().isMa())
		{
			Core.getInstance().getMainViewMa().dispose();
			Core.getInstance().setMainViewMa();
		}
		else if(Core.getInstance().isSt())
		{
			Core.getInstance().getStorageAdminView().dispose();
			Core.getInstance().setStorageAdminView();
		}
		else if(Core.getInstance().isSu())
		{
			Core.getInstance().getUsersAdmin().dispose();
			Core.getInstance().setUsersAdmin();
		}
	}
}
