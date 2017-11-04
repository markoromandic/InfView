package view.dialogs;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import appCore.Core;
import constants.DialogConstants;
import model.User;
import net.miginfocom.swing.MigLayout;
import view.systemAdmin.UsersView;

public class InfoUserDialog extends JFrame
{
	private MigLayout migLayout = null;
	
	private JLabel lbID = null;
	private JLabel lbUsername = null;
	private JLabel lbPassword = null;
	private JLabel lbName = null;
	private JLabel lbSurname = null;
	private JLabel lbEmail = null;

	private JTextField tfUsername = null;
	private JPasswordField tfPassword = null;
	private JTextField tfName = null;
	private JTextField tfSurname = null;
	private JTextField tfEmail = null;
	
	private JButton btFinish = null;
	private JButton btApply = null;
	
	private User user = null;
	
	private UsersView usersView = null;
	
	private String checkYes = "";
	
	public InfoUserDialog(User user, UsersView usersView)
	{
		this.usersView = usersView;
		this.user = user;
		initialize();
	}
	
	private void initialize()
	{
		migLayout = new MigLayout();
		
		setLayout(migLayout);
		
		lbID = new JLabel(DialogConstants.ID_LABEL + String.valueOf(user.getId()));
		lbUsername = new JLabel(DialogConstants.USERNAME_LABEL);
		lbPassword = new JLabel(DialogConstants.PASSWORD_LABEL);
		lbName = new JLabel(DialogConstants.NAME_LABEL);
		lbSurname = new JLabel(DialogConstants.SURNAME_LABEL);
		lbEmail = new JLabel(DialogConstants.EMAIL_LABEL);
		
		tfUsername = new JTextField(user.getUsername());
		tfUsername.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListener());
		
		tfPassword = new JPasswordField(user.getPassword());
		tfPassword.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListener());
		
		tfName = new JTextField(user.getName());
		tfName.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListener());
		
		tfSurname = new JTextField(user.getSurname());
		tfSurname.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListener());
		
		tfEmail = new JTextField(user.getEmail());
		tfEmail.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListener());
		
		btApply = new JButton(DialogConstants.APPLY);
		btApply.addActionListener(Core.getInstance().getActionManager().getApplyUser());
		btApply.setEnabled(false);
		
		btFinish = new JButton(DialogConstants.FINISH);
		btFinish.addActionListener(Core.getInstance().getActionManager().getFinishUser());
		
		add(lbID, "sg lb, wrap");
		add(lbUsername, "sg lb, split2");
		add(tfUsername, "sg tf, pushx, growx, wrap");
		add(lbPassword, "sg lb, split2");
		add(tfPassword, "sg tf, pushx, growx, wrap");
		add(lbName, "sg lb, split2");
		add(tfName, "sg tf, pushx, growx, wrap");
		add(lbSurname, "sg lb, split2");
		add(tfSurname, "sg tf, pushx, growx, wrap");
		add(lbEmail, "sg lb, split2");
		add(tfEmail, "sg tf, pushx, growx, wrap");
		add(btFinish, "tag finish, sg bt, split2");
		add(btApply, "tag apply, sg bt");
		
		setLocationRelativeTo(usersView);
		
		setMinimumSize(new Dimension(325, 220));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		ImageIcon iconNotResized = new ImageIcon(DialogConstants.USER_ICON);

		Image iconInfView = iconNotResized.getImage();
		iconInfView = iconInfView.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		setIconImage(iconInfView);
		
		setTitle(user.getUsername());
		
		pack();
		
		addWindowListener(Core.getInstance().getActionManager().getDialogUserListner());
		
		setVisible(true);
	}
	
	public void askForSave()
	{
		if(btApply.isEnabled())
		{
			Object[] options = {DialogConstants.YES, DialogConstants.NO, DialogConstants.CANCEL};
			JOptionPane optionPane = new JOptionPane(DialogConstants.SAVE + tfUsername.getText() + DialogConstants.QUESTION, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION, null, options, options[0]);
			JDialog dialog = optionPane.createDialog(this, "");
			dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
			
			String click = (String)optionPane.getValue();

			if (click.equals(DialogConstants.YES))
				checkYes = DialogConstants.Y;
			else if(click.equals(DialogConstants.NO))
				checkYes = DialogConstants.N;
			else if(click.equals(DialogConstants.CANCEL))
				checkYes = DialogConstants.C;
			
			dialog.dispose();
		}
	}

	public String getCheckYes()
	{
		return checkYes;
	}
	
	public void setNewParametarsOfUser()
	{
		user.setUsername(tfUsername.getText());
		user.setPassword(tfPassword.getText());
		user.setName(tfName.getText());
		user.setSurname(tfSurname.getText());
		user.setEmail(tfEmail.getText());
	
		btApply.setEnabled(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void deleteThis()
	{
		dispose();
		usersView.deleteDialog();
		usersView.setEnabled(true);
		usersView.toFront();
	}
	
	public void setEnabledApply()
	{
		btApply.setEnabled(true);
	}
	
	public String getUsername()
	{
		return tfUsername.getText();
	}
	
	public String getEmail()
	{
		return tfEmail.getText();
	}
	
	public String getUsernameEx()
	{
		return user.getUsername();
	}
	
	public void setNewTitle()
	{
		setTitle(user.getUsername());
	}
}
