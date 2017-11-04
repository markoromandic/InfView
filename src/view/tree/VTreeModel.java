package view.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import Structure.AbstractEntity;
import Structure.Attribute;
import Structure.Entity;
import Structure.Relation;
import Structure.Storage;
import appCore.Core;

public class VTreeModel extends DefaultTreeModel
{
	private DefaultMutableTreeNode storage = null;
	
	public VTreeModel(TreeNode root)
	{
		super(null);
	}
	
	public void initalizeOU()
	{
		storage = new DefaultMutableTreeNode(Core.getInstance().getModel().getTreeModel().getRoot());
		
		setRoot(storage);
		
		if(storage.getUserObject() instanceof Storage)
			goTS((Storage)storage.getUserObject());
	}
	
	public void initalizeMA()
	{
		storage = new DefaultMutableTreeNode(Core.getInstance().getModel().getTreeModel().getRoot());
		
		setRoot(storage);
		
		if(storage.getUserObject() instanceof Storage)
			goTS((Storage)storage.getUserObject());
	}
	
	private void goTS(Storage storage)
	{
		DefaultMutableTreeNode childNode;
		for(AbstractEntity child : storage.getChildren())
		{
			childNode = new DefaultMutableTreeNode(child);
			this.storage.add(childNode);
			if(child instanceof Structure.Package) 
				goTP((Structure.Package) child, childNode);
			else if(child instanceof Entity) 
				goTE((Entity) child, childNode);
		}
	}
	private void goTP(Structure.Package pk, DefaultMutableTreeNode nPK)
	{
		DefaultMutableTreeNode childNode;
		for(AbstractEntity child : pk.getChildren())
		{
			childNode = new DefaultMutableTreeNode(child);
			nPK.add(childNode);
			if(child instanceof Structure.Package) 
				goTP((Structure.Package) child, childNode);
			else if(child instanceof Entity) 
				goTE((Entity) child, childNode);
		}
	}
	
	private void goTE(Entity en, DefaultMutableTreeNode nEN)
	{
		if(!((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).isSQL()){
			DefaultMutableTreeNode childNode;
			for(Attribute child : en.getChildrenAttributes())
			{
				childNode = new DefaultMutableTreeNode(child);
				nEN.add(childNode);
			}
		} else{
			DefaultMutableTreeNode childNode;
			for(Relation child : en.getChildrenRelations()){
				childNode = new DefaultMutableTreeNode(child.getChildEntity());
				nEN.add(childNode);
			}
		}
	}
}
