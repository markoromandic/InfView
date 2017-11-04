package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import appCore.Core;
import constants.MenuConstants;

public class OpenChooseMetaDialog extends AbstractEditorAction
{
	public OpenChooseMetaDialog()
	{
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		putValue(NAME, MenuConstants.SELECT_META_SCHEME);
		putValue(SMALL_ICON, loadIcon(MenuConstants.META_SCHEME_SELECT_ICON)); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(Core.getInstance().isOp())
			Core.getInstance().getMainViewOu().openDialogToChangeMetaScheme();
		else if(Core.getInstance().isMa())
			Core.getInstance().getMainViewMa().openDialogToChangeMetaScheme();
	}
	
}
