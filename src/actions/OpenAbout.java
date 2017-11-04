package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import constants.MenuConstants;
import view.aboutUs.ViewAboutUs;

public class OpenAbout extends AbstractEditorAction
{
	public OpenAbout()
	{
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		putValue(NAME, MenuConstants.ABOUT_US_TITLE);
		putValue(SMALL_ICON, loadIcon(MenuConstants.ABOUT_US_ICON)); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		ViewAboutUs aboutUs = new ViewAboutUs();
	}
}
