package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appCore.Core;
import model.MetaScheme;

public class AddNewMetaScheme implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		MetaScheme m = Core.getInstance().getStorageAdminView().getNewMetaSchemeDialog().addMeta();
		
		boolean check = Core.getInstance().getModel().getUserModel().addNewMeta(m);
	
		if(!check)
			System.err.println("EXIST");
		else
		{
			Core.getInstance().getStorageAdminView().getListOfMetaSchemes().getListModel().refresh();
			Core.getInstance().getStorageAdminView().getNewMetaSchemeDialog().close();
		}
	}
}
