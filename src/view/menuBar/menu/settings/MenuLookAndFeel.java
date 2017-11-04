package view.menuBar.menu.settings;

import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import appCore.Core;
import constants.MenuConstants;

public class MenuLookAndFeel extends JMenu
{

	private JRadioButtonMenuItem menuRadioSystemLookAndFeel = null;
	private JRadioButtonMenuItem menuRadioCrossPlatformLookAndFeel = null;
	private JRadioButtonMenuItem menuRadioNimbusLookAndFeel = null;
	
	private ButtonGroup buttonGroupLookAndFeel;
	
	public MenuLookAndFeel()
	{
		initialize();
	}
	
	private void initialize()
	{
		setText(MenuConstants.THEME_TITLE);
		
		Icon icon = null;
		   
		ImageIcon iconNotResized = new ImageIcon(MenuConstants.LOOK_AND_FEEL_ICON);
			
		Image imgResizing =	iconNotResized.getImage();
		imgResizing = imgResizing.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(imgResizing);
		
		setIcon(icon);
		
		menuRadioSystemLookAndFeel = new JRadioButtonMenuItem(Core.getInstance().getActionManager().getSystemLookAndFeel());
		menuRadioCrossPlatformLookAndFeel = new JRadioButtonMenuItem(Core.getInstance().getActionManager().getSwingLookAndFeel());
		menuRadioNimbusLookAndFeel = new JRadioButtonMenuItem(Core.getInstance().getActionManager().getNimbusLookAndFeel());
		
		buttonGroupLookAndFeel = new ButtonGroup();
		buttonGroupLookAndFeel.add(menuRadioSystemLookAndFeel);
		buttonGroupLookAndFeel.add(menuRadioCrossPlatformLookAndFeel);
		buttonGroupLookAndFeel.add(menuRadioNimbusLookAndFeel);
		
		add(menuRadioCrossPlatformLookAndFeel);
		add(menuRadioSystemLookAndFeel);
		add(menuRadioNimbusLookAndFeel);
		
		menuRadioCrossPlatformLookAndFeel.setSelected(true);
	}
	
}

