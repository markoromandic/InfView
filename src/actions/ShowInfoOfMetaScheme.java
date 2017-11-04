package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

import appCore.Core;
import constants.Constants;
import model.MetaScheme;

public class ShowInfoOfMetaScheme extends MouseAdapter implements ActionListener, KeyListener
{

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getClickCount() == 2)
		{
			JList theList = (JList) e.getSource();
			
			int index = theList.locationToIndex(e.getPoint());
			
			MetaScheme m = (MetaScheme)theList.getModel().getElementAt(index);
			
			Core.getInstance().getStorageAdminView().openDialog(m);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		MetaScheme m = Core.getInstance().getStorageAdminView().getSelectedMetaScheme();
	
		Core.getInstance().getStorageAdminView().openDialog(m);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyPressedNum = e.getKeyCode();
		String keyChar = KeyEvent.getKeyText(keyPressedNum);
		
		if(keyChar.equals(Constants.ENTER))
		{
			MetaScheme m = Core.getInstance().getStorageAdminView().getSelectedMetaScheme();
			
			Core.getInstance().getStorageAdminView().openDialog(m);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

}
	