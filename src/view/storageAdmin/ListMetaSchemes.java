package view.storageAdmin;

import javax.swing.JList;

import appCore.Core;
import model.MetaScheme;

public class ListMetaSchemes extends JList<MetaScheme>
{
	private IVListMetaSchemeModel listModel = null;
	
	public ListMetaSchemes()
	{
		initialize();
	}
	
	private void initialize()
	{
		listModel = new IVListMetaSchemeModel();
		setCellRenderer(new ListCellRenderer());
		setModel(listModel);
		
		if(Core.getInstance().isSt())
		{
			addMouseListener(Core.getInstance().getActionManager().getShowInfoOfMetaScheme());
			addKeyListener(Core.getInstance().getActionManager().getShowInfoOfMetaScheme());
		}
		else if(Core.getInstance().isOp())
		{
			if(Core.getInstance().getMainViewOu().isOpened())
			{
				addMouseListener(Core.getInstance().getActionManager().getChangeMetaScheme());
				addKeyListener(Core.getInstance().getActionManager().getChangeMetaScheme());
			}
			else
			{
				addMouseListener(Core.getInstance().getActionManager().getSelectMetaToInterpret());
				addKeyListener(Core.getInstance().getActionManager().getSelectMetaToInterpret());
			}
		}
		else if(Core.getInstance().isMa())
		{
			if(Core.getInstance().getMainViewMa().isActive())
			{
				addMouseListener(Core.getInstance().getActionManager().getChangeMetaScheme());
				addKeyListener(Core.getInstance().getActionManager().getChangeMetaScheme());
			}
			else
			{
				addMouseListener(Core.getInstance().getActionManager().getSelectMetaToInterpret());
				addKeyListener(Core.getInstance().getActionManager().getSelectMetaToInterpret());
			}
		}
	}

	public IVListMetaSchemeModel getListModel()
	{
		return listModel;
	}
}
