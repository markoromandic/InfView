package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appCore.Core;

public class OpenMetaSchemeEditor implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String username = Core.getInstance().getModel().getUserModel().getUsername();
		Core.getInstance().initializeMainViewMA(username);
		
		if(Core.getInstance().isSa())
		{
			Core.getInstance().getMainViewA().dispose();
			Core.getInstance().setMainViewA();
		}
	}
}