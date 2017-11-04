package view.storageAdmin;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import model.MetaScheme;
import model.User;

public class ListCellRenderer extends DefaultListCellRenderer
{
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (renderer instanceof JLabel && value instanceof MetaScheme) 
        {
            ((JLabel) renderer).setText(((MetaScheme) value).getName());
        }
        else if (renderer instanceof JLabel && value instanceof User) 
        {
            ((JLabel) renderer).setText(((User) value).getUsername());
        }
        return renderer;
    }
}
