package view.tree;

import javax.swing.JTree;

import actions.OpenTableFromTree;
import actions.SetCursosOnSelected;
import appCore.Core;
import constants.UserType;

public class IVTree extends JTree
{
	VTreeModel treeModel;
	
	public IVTree()
	{
		initialize();
	}
	
	private void initialize()
	{
		treeModel = new VTreeModel(null);
		setRootVisible(true);
		setCellRenderer(new VTreeRender());
		this.setModel(treeModel);
		
		if(Core.getInstance().getModel().getUserModel().getLogInUserType().equals(UserType.OPERATIVE_USER))
			addMouseListener(new OpenTableFromTree());
		else if(Core.getInstance().getModel().getUserModel().getLogInUserType().equals(UserType.META_SCHEME_ADMIN) || Core.getInstance().getModel().getUserModel().getLogInUserType().equals(UserType.SYSTEM_ADMIN))
			addMouseListener(new SetCursosOnSelected());
	}
	
	synchronized public void loadNewTreeModel()
	{
		treeModel = new VTreeModel(null);
		treeModel.initalizeMA();
		this.setModel(treeModel);
		
		expandAllNodes(this, 0, this.getRowCount());
	}
	
	public VTreeModel getTreeModel()
	{
		return treeModel;
	}

	private void expandAllNodes(JTree tree, int startingIndex, int rowCount)
	{
	    for(int i=startingIndex;i<rowCount;++i)
	    {
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount() != rowCount)
	    {
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}
}
