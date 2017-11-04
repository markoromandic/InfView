package actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;

import appCore.Core;

public class SetCursosOnSelected extends MouseAdapter
{
	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getClickCount() == 2) 
		{
		    DefaultMutableTreeNode clicked = (DefaultMutableTreeNode) Core.getInstance().getMainViewMa().getTreeMetaScheme().getClosestPathForLocation(e.getX(), e.getY()).getLastPathComponent();
		    
		    String name = clicked.toString();
		    
		    Core.getInstance().getMainViewMa().findName(name);
		}
	}
}
