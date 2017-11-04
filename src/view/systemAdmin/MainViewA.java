package view.systemAdmin;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import appCore.Core;
import constants.AdminViewConstants;
import constants.AppConstants;
import constants.Constants;
import net.miginfocom.swing.MigLayout;
import view.menuBar.IVMenuBar;

public class MainViewA extends JFrame
{
	private String username = null;
	private JButton openMetaSchemeEditor = null;
	private JButton users = null;
	private JButton metaSchemes = null;
	private IVMenuBar menuBar = null;
	private MigLayout migLayout = null;
	
	private boolean su = false;
	
	public MainViewA(String username)
	{
		this.username = username;
		initialize();
	}
	
	private void initialize()
	{
		initializeGUI();
		initializeIcon();
		initializeMenuBar();
		
		users = new JButton(AdminViewConstants.USERS);
		users.addActionListener(Core.getInstance().getActionManager().getOpenUsers());
		
		openMetaSchemeEditor = new JButton(AdminViewConstants.META_SCHEME_EDITOR_TITLE);
		openMetaSchemeEditor.addActionListener(Core.getInstance().getActionManager().getOpenMetaSchemeEditor());
		
		metaSchemes = new JButton(AdminViewConstants.META_SCHEMES_TITLE);
		metaSchemes.addActionListener(Core.getInstance().getActionManager().getOpenMetaSchemesView());
		
		add(users, "wrap, sg 1, pushx, growx");
		add(metaSchemes, "wrap, sg 1, pushx, growx");
		add(openMetaSchemeEditor, "wrap, sg 1, pushx, growx");
		
		setMinimumSize(new Dimension(350, 150));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		pack();
	}
	
	private void initializeGUI()
	{
		migLayout = new MigLayout();
		setLayout(migLayout);
		
		setLocationRelativeTo(Core.getInstance().getLogInView());
		
		setTitle(AppConstants.APP_TITLE + " - " + username);
		
		setVisible(true);
	}
	
	private void initializeMenuBar()
	{
		menuBar = new IVMenuBar();
		
		setJMenuBar(menuBar);
	}
	
	private void initializeIcon()
	{
		ImageIcon iconNotResized = new ImageIcon(AppConstants.APP_LOGO);

		Image iconInfView = iconNotResized.getImage();
		iconInfView = iconInfView.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		setIconImage(iconInfView);
	}
	
	public void systemLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
			pack();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void crossPlatformLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
			pack();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void nimbusPlatformLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(Constants.NIMBUS);
			SwingUtilities.updateComponentTreeUI(this);
			pack();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean isSu()
	{
		return su;
	}
}
