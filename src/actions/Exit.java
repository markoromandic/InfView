package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import constants.MenuConstants;

public class Exit extends AbstractEditorAction
{
	public Exit()
	{
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		putValue(NAME, MenuConstants.EXIT_TITLE);
		putValue(SMALL_ICON, loadIcon(MenuConstants.EXIT_ICON)); 
//		putValue(SHORT_DESCRIPTION, COPY_TOOLTIP);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}

}
