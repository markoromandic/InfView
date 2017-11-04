package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import appCore.Core;
import constants.Constants;
import constants.UserType;

public class LogIn  extends KeyAdapter implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		initialize();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);
		
		int keyPressed = e.getKeyCode();
		String keyChar= KeyEvent.getKeyText(keyPressed);
		
		if (keyChar.equals(Constants.ENTER))
		{
			initialize();
		}
	}
	
	private void initialize()
	{
		String username = Core.getInstance().getLogInView().getUserName();
		String password = Core.getInstance().getLogInView().getPassword();
		
		String user = Core.getInstance().getModel().getUserModel().CheckUser(username, password);
		
		String a[] = user.split(";");
		
		if(a[0].equals(UserType.NONE))
			Core.getInstance().getLogInView().checkUser(a[0], null);
		else
		{
			Core.getInstance().getLogInView().checkUser(a[1], a[0]);
			Core.getInstance().setLogInView();
		}
			
		
		
	}
}
