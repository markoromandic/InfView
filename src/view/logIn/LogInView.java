package view.logIn;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import appCore.Core;
import constants.Constants;
import constants.UserType;
import net.miginfocom.swing.MigLayout;
import test.TestUserEnter;
import view.menuBar.IVMenuBar;

public class LogInView extends JFrame
{
	private IVMenuBar ivMenuBar = null;
	
	private JTextField textFieldUserName = null;
	private JPasswordField passwordFieldPassword = null;
	
	private JLabel labelUserName = null;
	private JLabel labelPassword = null;
	private JLabel lbForgot = null;
	
	private JButton buttonLogIn = null;
	
	public LogInView()
	{
		initialize();
	}
	
	public void setTextFieldUserName(String textFieldUserName)
	{
		this.textFieldUserName.setText(textFieldUserName); 
	}

	public void setPasswordFieldPassword(String passwordFieldPassword)
	{
		this.passwordFieldPassword.setText(passwordFieldPassword);
	}
	
	private void initialize()
	{
		MigLayout migLayout = new MigLayout();
		
		setTitle(Constants.LOG_IN_TITLE);
		setSize(335, 215);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().setLayout(migLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ivMenuBar = new IVMenuBar();
		setJMenuBar(ivMenuBar);
		
		ImageIcon iconNotResized = new ImageIcon(Constants.LOG_IN_ICON);

		Image iconLogIn = iconNotResized.getImage();
		iconLogIn = iconLogIn.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		setIconImage(iconLogIn);
		
		textFieldUserName = new JTextField();
		textFieldUserName.addKeyListener(Core.getInstance().getActionManager().getLogIn());
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.addKeyListener(Core.getInstance().getActionManager().getLogIn());
		
		labelUserName = new JLabel(Constants.LOG_IN_USERNAME);
		
		labelPassword = new JLabel(Constants.LOG_IN_PASSWORD);
		
		lbForgot = new JLabel(Constants.LOG_IN_FORGOT);
		Font font = lbForgot.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lbForgot.setFont(font.deriveFont(attributes));
		Color blueLink = new Color(0, 102, 204);
		lbForgot.setForeground(blueLink);
		lbForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbForgot.addMouseListener(Core.getInstance().getActionManager().getForgot());
		
		buttonLogIn = new JButton(Constants.LOG_IN_BUTTON);
		buttonLogIn.addActionListener(Core.getInstance().getActionManager().getLogIn());
		buttonLogIn.addKeyListener(Core.getInstance().getActionManager().getLogIn());
		
		add(labelUserName, "left, sg 1, split 2");
		add(textFieldUserName, "pushx, growx, wrap");
		add(labelPassword, "left, sg 1, split 2");
		add(passwordFieldPassword, "pushx, growx, wrap");
		add(lbForgot, "split2");
		add(buttonLogIn, "tag ok, wrap");
		
		TestUserEnter testUserEnter = new TestUserEnter();
		add(testUserEnter, "pushx, growx");
		
		setMinimumSize(new Dimension(335, 150));
		
		pack();
	}

	public String getUserName()
	{	
		return textFieldUserName.getText();
	}
	
	public String getPassword()
	{
		return passwordFieldPassword.getText();
	}

	public void checkUser(String username, String type)
	{
		if(username.equals(UserType.NONE))
			return;
		
		textFieldUserName.setText("");
		passwordFieldPassword.setText("");
		
		if(type.equals(UserType.SYSTEM_ADMIN))
		{
			Core.getInstance().initializeMainViewA(username);
			
			dispose();
		}
		else if(type.equals(UserType.META_SCHEME_ADMIN))
		{
			Core.getInstance().initializeMainViewMA(username);
			
			dispose();
		}
		else if(type.equals(UserType.OPERATIVE_USER))
		{
			Core.getInstance().initializeMainViewOU(username);
		
			dispose();
		}
		else if(type.equals(UserType.STORAGE_ADMIN))
		{
			Core.getInstance().initilaizeMainViewSTA(username);
			
			dispose();
		}
		else if(type.equals(UserType.USER_ADMIN))
		{
			Core.getInstance().initializeUserAdmin(username);
			
			dispose();
		}
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
	
	public void changeClick()
	{
		Font font = lbForgot.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lbForgot.setFont(font.deriveFont(attributes));
		Color blueLink = Color.BLUE;
		lbForgot.setForeground(blueLink);
	}
	
	public void changeLinkBack()
	{
		Font font = lbForgot.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lbForgot.setFont(font.deriveFont(attributes));
		Color blueLink = new Color(0, 102, 204);
		lbForgot.setForeground(blueLink);
	}
}
