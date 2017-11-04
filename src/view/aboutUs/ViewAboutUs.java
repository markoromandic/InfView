package view.aboutUs;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import appCore.Core;
import constants.MenuConstants;
import net.miginfocom.swing.MigLayout;

public class ViewAboutUs extends JFrame
{
	private ImageIcon iconAbout;
	private JPanel panelAbout;
	
	private JLabel about;
	
	public ViewAboutUs() 
	{
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		if(Core.getInstance().getLogInView() != null)
		{
			setLocationRelativeTo(Core.getInstance().getLogInView());
		}
		else if(Core.getInstance().isMa())
		{
			setLocationRelativeTo(Core.getInstance().getMainViewMa());
		}
		else if(Core.getInstance().isOp())
		{
			setLocationRelativeTo(Core.getInstance().getMainViewOu());
		}
		else if(Core.getInstance().isSa())
		{
			setLocationRelativeTo(Core.getInstance().getMainViewA());
		}
		else if(Core.getInstance().isSt())
		{
			setLocationRelativeTo(Core.getInstance().getStorageAdminView());
		}
		else if(Core.getInstance().isSu())
		{
			setLocationRelativeTo(Core.getInstance().getUsersAdmin());
		}
					
		setSize(370, 400);
		
		setTitle(MenuConstants.ABOUT_US_TITLE);
		ImageIcon backgroundIcon = new ImageIcon(MenuConstants.ABOUT_US_ICON);
		
		Image iconBackground =	backgroundIcon.getImage();
		iconBackground = iconBackground.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		
		setIconImage(iconBackground);
		setResizable(false);
		
		iconAbout = new ImageIcon("images/newIconSuperheroAbout.png");
		
		Image imageResizing = iconAbout.getImage();
		imageResizing = imageResizing.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		iconAbout = new ImageIcon(imageResizing);
		
		about = new JLabel(iconAbout);
		
		MigLayout migLayout = new MigLayout();
		setLayout(migLayout);
		getContentPane().add(about);
		
		setVisible(true);
	}
}
