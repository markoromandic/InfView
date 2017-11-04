package view.systemAdmin;

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
import constants.SystemAdminConstants;
import model.User;
import net.miginfocom.swing.MigLayout;
import view.dialogs.InfoUserDialog;
import view.dialogs.NewUserDialog;
import view.menuBar.IVMenuBar;

public class UsersView extends JFrame
{
	private MigLayout migLayout = null;
	
	private ListOfUsers listOfUsers = null;
	private JButton btInfo = null;
	private JButton btAdd = null;
	private JButton btRemove = null;
	private String user = null;
	private IVMenuBar menuBar = null;
	
	private InfoUserDialog infoUserDialog = null;
	private NewUserDialog newUserDialog = null;
	
	private boolean active = false;
	
	public UsersView(String user)
	{
		this.user = user;
		initialize();
	}
	
	private void initialize()
	{
		active = true;
		
		migLayout = new MigLayout();
		setLayout(migLayout);
		
		menuBar = new IVMenuBar();
		setJMenuBar(menuBar);
		
		listOfUsers = new ListOfUsers();
		listOfUsers.setBorder(BorderFactory.createTitledBorder(SystemAdminConstants.USERS));
		JScrollPane scListOfUsers = new JScrollPane(listOfUsers);
		scListOfUsers.setBorder(BorderFactory.createEmptyBorder());
		
		btInfo = new JButton(SystemAdminConstants.INFO);
		btInfo.addActionListener(Core.getInstance().getActionManager().getInfoUser());
		
		btAdd = new JButton(SystemAdminConstants.ADD);
		btAdd.addActionListener(Core.getInstance().getActionManager().getNewUser());
		
		btRemove = new JButton(SystemAdminConstants.REMOVE);
		btRemove.addActionListener(Core.getInstance().getActionManager().getRemoveUser());
		
		add(scListOfUsers, "push, grow, wrap");
		add(btInfo, "tag help2, split3, sg 1");
		add(btAdd, "tag right, sg 1");
		add(btRemove, "tag right, sg 1");
		
		setVisible(true);
		setMinimumSize(new Dimension(450, 325));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(SystemAdminConstants.USERS_TITLE + user);
		
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

	public ListOfUsers getListOfUsers()
	{
		return listOfUsers;
	}
	
	public User getSelectedUser()
	{
		User sU = listOfUsers.getSelectedValue();
		return listOfUsers.getSelectedValue();
	}
	
	public InfoUserDialog getInfoUserDialog()
	{
		return infoUserDialog;
	}

	public void openDialog(User selectedUser)
	{
		infoUserDialog = new InfoUserDialog(selectedUser, this);
	}
	
	public void deleteDialog()
	{
		infoUserDialog = null;
	}
	
	public void openNewUserDialog()
	{
		newUserDialog = new NewUserDialog(this);
	}
	
	public void deleteNewUserDialog()
	{
		newUserDialog = null;
	}

	public NewUserDialog getNewUserDialog()
	{
		return newUserDialog;
	}
}
