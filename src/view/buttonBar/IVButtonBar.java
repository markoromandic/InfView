package view.buttonBar;

import javax.swing.BorderFactory;
import javax.swing.JToolBar;

import actions.MakeSekTab;
import appCore.Core;

public class IVButtonBar extends JToolBar
{
	public IVButtonBar()
	{
		initialize();
	}
	
	private void initialize()
	{
		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(false);
		
		if(Core.getInstance().isOp())
		{
			add(Core.getInstance().getActionManager().getCloseTab());
			add(Core.getInstance().getActionManager().getFetchTab());
			add(Core.getInstance().getActionManager().getAddRecordTab());
			add(Core.getInstance().getActionManager().getDeleteRecordTab());
			add(Core.getInstance().getActionManager().getFindRecordTab());
			add(Core.getInstance().getActionManager().getSortMdiTab());
			add(Core.getInstance().getActionManager().getSortMmTab());
			add(Core.getInstance().getActionManager().getMakeSekTab());
			
			
			
		}
		else if(Core.getInstance().isMa())
		{
			add(Core.getInstance().getActionManager().getGenerateRepository());
			add(Core.getInstance().getActionManager().getGeneratePackage());
			add(Core.getInstance().getActionManager().getGenerateEntity());
			add(Core.getInstance().getActionManager().getGenerateAttribute());
		}
	}

	private void add(MakeSekTab makeSekTab) {
		// TODO Auto-generated method stub
		
	}
}
