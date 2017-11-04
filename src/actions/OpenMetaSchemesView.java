package actions;

import java.awt.event.ActionEvent;

import appCore.Core;

public class OpenMetaSchemesView extends AbstractEditorAction
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String username = Core.getInstance().getModel().getUserModel().getUsername();
		
		if(Core.getInstance().isSa())
		{
			Core.getInstance().getMainViewA().dispose();
			Core.getInstance().setMainViewA();
		}
		
		Core.getInstance().initilaizeMainViewSTA(username);
	}

}
