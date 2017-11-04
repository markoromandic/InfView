
package view.tree;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import Structure.Attribute;
import Structure.Entity;
import Structure.Package;
import Structure.Storage;
import constants.NodeRenderConstants;

public class VTreeRender extends DefaultTreeCellRenderer
{
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		int height = 15;
		int width = 15;
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		
		Component renderer = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		
		if(node.getUserObject() instanceof Storage)
			generateIcon(NodeRenderConstants.STORAGE_ICON, NodeRenderConstants.STORAGE_ICON, value.toString(), expanded, selected, width, height);
		
		else if(node.getUserObject() instanceof Package)
			generateIcon(NodeRenderConstants.OPEN_PACKAGE_ICON, NodeRenderConstants.CLOSED_PACKAGE_ICON, value.toString(), expanded, selected, width, height);
		
		else if(node.getUserObject() instanceof Entity)
			generateIcon(NodeRenderConstants.ENTITY_ICON, NodeRenderConstants.ENTITY_ICON, value.toString(), expanded, selected, width, height);
		
		else if(node.getUserObject() instanceof Attribute)
			generateIcon(NodeRenderConstants.ATTRIBUTE_ICON, NodeRenderConstants.ATTRIBUTE_ICON, value.toString(), expanded, selected, width, height);
		
		return renderer;
//		return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	}

	private void generateIcon(String iconPathOpen, String iconPathClosed, String name, boolean expanded, boolean selected, int width, int height)
	{
		String iconPath = "";
		
		if(expanded)
			iconPath = iconPathOpen;
		else
			iconPath = iconPathClosed;
		
		ImageIcon iconNotResized = new ImageIcon(iconPath);
		Image iconInfView = iconNotResized.getImage();
		iconInfView = iconInfView.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		ImageIcon icon = new ImageIcon(iconInfView);
		
		setText(name);
		setIcon(icon);
	}
}
