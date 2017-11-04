package view.storageAdmin;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import appCore.Core;
import constants.AppConstants;
import constants.Constants;
import constants.StorageAdminConstants;
import model.MetaScheme;
import net.miginfocom.swing.MigLayout;
import view.dialogs.MetaSchemeDialog;
import view.dialogs.NewMetaSchemeDialog;
import view.menuBar.IVMenuBar;

public class StorageAdminView extends JFrame
{
	private String user = null;
	private IVMenuBar menuBar = null;
	private ListMetaSchemes listOfMetaSchemes = null;
	private JScrollPane scrollPane = null;
	private MetaSchemeDialog metaSchemeDialog = null;
	private JButton info = null;
	private JButton newMeta = null;
	private JButton removeMeta = null;
	private NewMetaSchemeDialog newMetaSchemeDialog = null;

	public StorageAdminView(String user)
	{
		this.user = user;
		initialize();
	}

	private void initialize()
	{
		MigLayout migLayout = new MigLayout();
		setLayout(migLayout);
		
		menuBar = new IVMenuBar();
		setJMenuBar(menuBar);

		listOfMetaSchemes = new ListMetaSchemes();
		listOfMetaSchemes.setBorder(BorderFactory.createTitledBorder("Meta schemes"));
		
		scrollPane = new JScrollPane(listOfMetaSchemes);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		add(scrollPane, "wrap, push, grow");
		
		info = new JButton(StorageAdminConstants.SHOW_MORE);
		info.addActionListener(Core.getInstance().getActionManager().getShowInfoOfMetaScheme());
		newMeta = new JButton(StorageAdminConstants.ADD_NEW_META);
		newMeta.addActionListener(Core.getInstance().getActionManager().getNewMeta());
		removeMeta = new JButton(StorageAdminConstants.REMOVE);
		removeMeta.addActionListener(Core.getInstance().getActionManager().getRemoveMeta());
		
		add(info, "tag help2, split3, sg 1");
		add(newMeta, "tag right, sg 1");
		add(removeMeta, "tag right, sg 1");
		
		setVisible(true);
		setMinimumSize(new Dimension(450, 325));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(StorageAdminConstants.META_SCHEMES_TITLE + user);
		
		ImageIcon iconNotResized = new ImageIcon(AppConstants.APP_LOGO);

		Image iconInfView = iconNotResized.getImage();
		iconInfView = iconInfView.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		setIconImage(iconInfView);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		pack();
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
	
	public MetaScheme getSelectedMetaScheme()
	{
		return listOfMetaSchemes.getSelectedValue();
	}
	
	public void openDialog(MetaScheme mSelected)
	{
		metaSchemeDialog = new MetaSchemeDialog(mSelected, this);
	}
	
	public MetaSchemeDialog getMetaSchemeDialog()
	{
		return metaSchemeDialog;
	}
	
	public void deleteDialog()
	{
		metaSchemeDialog = null;
	}
	
	public void openNewMetaDialog()
	{
		 newMetaSchemeDialog = new NewMetaSchemeDialog(this);
	}
	
	public ListMetaSchemes getListOfMetaSchemes()
	{
		return listOfMetaSchemes;
	}

	public NewMetaSchemeDialog getNewMetaSchemeDialog()
	{
		return newMetaSchemeDialog;
	}
	
	public void deleteAddDialog()
	{
		newMetaSchemeDialog = null;
	}
}
