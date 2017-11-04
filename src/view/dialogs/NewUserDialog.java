package view.dialogs;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import appCore.Core;
import constants.DialogConstants;
import constants.UserType;
import model.User;
import net.miginfocom.swing.MigLayout;
import view.systemAdmin.UsersView;

public class NewUserDialog extends JFrame
{
	private MigLayout migLayout = null;
	
	private JLabel lbID = null;
	private JLabel lbUsername = null;
	private JLabel lbPassword = null;
	private JLabel lbName = null;
	private JLabel lbSurname = null;
	private JLabel lbEmail = null;
	private JLabel lbType = null;
	
	private JTextField tfUsername = null;
	private JPasswordField tfPassword = null;
	private JTextField tfName = null;
	private JTextField tfSurname = null;
	private JTextField tfEmail = null;
	
	private JComboBox<String> cbType = null;
	
	private JButton btAdd = null;
	
	private UsersView usersView = null;
	
	private long newID = -1;
	
	public NewUserDialog(UsersView usersView)
	{
		this.usersView = usersView;
		initialize();
	}
	
	private void initialize()
	{
		migLayout = new MigLayout();
		
		addWindowListener(Core.getInstance().getActionManager().getDialogNewUserListener());
		
		setLayout(migLayout);
		setLocationRelativeTo(usersView);
		
		newID = Core.getInstance().getModel().getUserModel().getUsers().get(Core.getInstance().getModel().getUserModel().getUsers().size() - 1).getId() + 1; 
		
		lbID = new JLabel(DialogConstants.ID_LABEL + String.valueOf(newID));
		lbUsername = new JLabel(DialogConstants.USERNAME_LABEL);
		lbPassword = new JLabel(DialogConstants.PASSWORD_LABEL);
		lbName = new JLabel(DialogConstants.NAME_LABEL);
		lbSurname = new JLabel(DialogConstants.SURNAME_LABEL);
		lbEmail = new JLabel(DialogConstants.EMAIL_LABEL);
		
		tfUsername = new JTextField();
		tfUsername.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocNewUserListener());
		
		tfPassword = new JPasswordField();
		tfPassword.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocNewUserListener());
		
		lbType = new JLabel(DialogConstants.TYPE_LABEL);
		
//		EDITOVATI
		cbType = new JComboBox<>();
		cbType.addItem(UserType.OPERATIVE_USER);
		cbType.addItem(UserType.SYSTEM_ADMIN);
		cbType.addItem(UserType.META_SCHEME_ADMIN);
		cbType.addItem(UserType.USER_ADMIN);
		cbType.addItem(UserType.STORAGE_ADMIN);
		
		tfName = new JTextField();
		tfName.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocNewUserListener());
		
		tfSurname = new JTextField();
		tfSurname.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocNewUserListener());
		
		tfEmail = new JTextField();
		tfEmail.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocNewUserListener());
		
		btAdd = new JButton(DialogConstants.ADD);
		btAdd.addActionListener(Core.getInstance().getActionManager().getAddUser());
		
		add(lbID, "sg lb, wrap");
		add(lbUsername, "sg lb, split2");
		add(tfUsername, "sg tf, pushx, growx, wrap");
		add(lbPassword, "sg lb, split2");
		add(tfPassword, "sg tf, pushx, growx, wrap");
		add(lbType, "sg lb, split2");
		add(cbType, "sg tf, pushx, growx, wrap");
		add(lbName, "sg lb, split2");
		add(tfName, "sg tf, pushx, growx, wrap");
		add(lbSurname, "sg lb, split2");
		add(tfSurname, "sg tf, pushx, growx, wrap");
		add(lbEmail, "sg lb, split2");
		add(tfEmail, "sg tf, pushx, growx, wrap");
		add(btAdd, "tag apply");
		
		ImageIcon iconNotResized = new ImageIcon(DialogConstants.USER_ICON);

		Image iconMetaSchemeEditor = iconNotResized.getImage();
		iconMetaSchemeEditor = iconMetaSchemeEditor.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		setIconImage(iconMetaSchemeEditor);
		
		pack();
		
		setMinimumSize(new Dimension(250, 300));
		
		setVisible(true);
	}
	
	public boolean checkIsItFilled()
	{
		if(!tfUsername.getText().equals("") && !tfPassword.getText().equals("") && !tfName.getText().equals("") && !tfSurname.getText().equals("") && !tfEmail.getText().equals(""))
			return true;
		else
			return false;
	}
	
	public void setAdd(boolean status)
	{
		btAdd.setEnabled(status);
	}
	
	public void close()
	{
		usersView.setEnabled(true);
		usersView.toFront();
		
		dispose();
	}
	
	public User addUser()
	{
		long id = newID;
		String type = String.valueOf(cbType.getSelectedItem());
		String username = tfUsername.getText();
		String password = tfPassword.getText();
		String name = tfName.getText();
		String surname= tfSurname.getText();
		String email = tfEmail.getText();
		
		User newUser = new User(newID, type, username, password, name, surname, email);
		
		return newUser;
	}
}
