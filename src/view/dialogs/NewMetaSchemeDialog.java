package view.dialogs;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import appCore.Core;
import constants.AdminViewConstants;
import constants.Constants;
import constants.DialogConstants;
import model.MetaScheme;
import net.miginfocom.swing.MigLayout;
import view.storageAdmin.StorageAdminView;

public class NewMetaSchemeDialog extends JFrame
{
	private MigLayout migLayout = null;
	
	private JFrame frame = null;
	
	private JLabel lbID = null;
	private JLabel lbName = null;
	private JLabel lbLocation = null;
	
	private JTextField tfName = null;
	private JTextField tfPath = null;
	
	private JButton btAdd = null;
	private JButton btSelect = null;
	
	private JTextArea taDescription = null;

	private String checkYes = "";
	
	long newID = -1;
	
	public NewMetaSchemeDialog(JFrame frame)
	{
		frame.setEnabled(false);
		this.frame = frame;
		initilaizeGUI();
		initialize();
		addWindowListener(Core.getInstance().getActionManager().getDialogNewMetaSchemeListener());
	}

	private void initialize()
	{
		setLocationRelativeTo(frame);
		setMinimumSize(new Dimension(300, 200));
		
		ImageIcon iconNotResized = new ImageIcon(AdminViewConstants.META_SCHEME_EDITOR_ICON);

		Image iconMetaSchemeEditor = iconNotResized.getImage();
		iconMetaSchemeEditor = iconMetaSchemeEditor.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		setIconImage(iconMetaSchemeEditor);
		
		pack();
		setVisible(true);
	}

	private void initilaizeGUI()
	{
		migLayout = new MigLayout();
		
		setLayout(migLayout);
		
		if(Core.getInstance().getModel().getUserModel().getMetaSchemes().size() > 0)
			newID = Core.getInstance().getModel().getUserModel().getMetaSchemes().get(Core.getInstance().getModel().getUserModel().getMetaSchemes().size() - 1).getId() + 1;
		else 
			newID = 1;
		
		lbID = new JLabel(DialogConstants.ID_LABEL + newID);
		
		lbName = new JLabel(DialogConstants.NAME_LABEL);
		
		tfName = new JTextField();
		tfName.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListenerNewMeta());
		tfName.addMouseListener(Core.getInstance().getActionManager().getNewMetaSelectMetaScheme());
		
		lbLocation = new JLabel(DialogConstants.LOCATION_LABEL);
		tfPath = new JTextField();
		tfPath.addMouseListener(Core.getInstance().getActionManager().getNewMetaSelectPath());
		
		btAdd = new JButton(DialogConstants.ADD);
		btAdd.setEnabled(false);
		btAdd.addActionListener(Core.getInstance().getActionManager().getAddNewMetaScheme());
		
		btSelect = new JButton(DialogConstants.SELECT);
		btSelect.addActionListener(Core.getInstance().getActionManager().getSelectNewMetaScheme());
		
		taDescription = new JTextArea();
		taDescription.setBorder(BorderFactory.createTitledBorder(DialogConstants.DESCRIPTION));
		taDescription.setLineWrap(true);
		taDescription.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListenerNewMeta());
		
		JScrollPane deScrollPane = new JScrollPane(taDescription);
		deScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		add(lbID, "wrap, sg 1");
		add(lbName, "split2, sg 1");
		add(tfName, "sg 2, pushx, growx, wrap");
		add(lbLocation, "split2, sg 1");
		add(tfPath, "sg 2, pushx, growx, wrap");
		add(deScrollPane, "span, push, grow, wrap");
		add(btSelect, "split, sg 3, tag left");
		add(btAdd, "sg 3, tag apply");
	}
	
	public void selectPath()
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle(DialogConstants.SELECT_TARGET_TITLE);
		if(tfPath.getText().equals(DialogConstants.NONE))
			fc.setCurrentDirectory(Core.getInstance().getModel().getUserModel().getLocator().getParentFile());
		else 
			fc.setCurrentDirectory(new File(tfName.getText()));
		
		int returnVal = fc.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			String path = fc.getSelectedFile().getParentFile().getAbsolutePath();
			
			tfPath.setText(path);
			
			btAdd.setEnabled(true);
		}
	}
	
	public void selectMetaScheme()
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle(DialogConstants.SELECT_META_TITLE);
		if(tfPath.getText().equals(DialogConstants.NONE))
			fc.setCurrentDirectory(Core.getInstance().getModel().getUserModel().getLocator().getParentFile());
		else 
			fc.setCurrentDirectory(new File(tfName.getText()));
			
		FileNameExtensionFilter filter = new FileNameExtensionFilter(DialogConstants.JSON_EXTENSION, DialogConstants.JSON);
		
		fc.setFileFilter(filter);
		
		int returnVal = fc.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			String name = fc.getSelectedFile().getName();
			
			if (name.indexOf(Constants.FULL_STOP) > 0)
			    name = name.substring(0, name.lastIndexOf(Constants.FULL_STOP));
			
			tfName.setText(name);
			
			btAdd.setEnabled(true);
		}
	}
	
	public void setAddEnabled()
	{
		btAdd.setEnabled(true);
	}
	
	public void selectMeta()
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle(DialogConstants.SELECT_META_TITLE);
		fc.setCurrentDirectory(Core.getInstance().getModel().getUserModel().getLocator().getParentFile());
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(DialogConstants.JSON_EXTENSION, DialogConstants.JSON);
		
		fc.setFileFilter(filter);
		
		int returnVal = fc.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			String url = fc.getSelectedFile().getParentFile().getAbsolutePath();
			String name = fc.getSelectedFile().getName();
			
			if (name.indexOf(Constants.FULL_STOP) > 0)
			    name = name.substring(0, name.lastIndexOf(Constants.FULL_STOP));
			
			tfName.setText(name);
			tfPath.setText(url);
			
			btAdd.setEnabled(true);
		}
	}
	
	public MetaScheme addMeta()
	{
		MetaScheme am = new MetaScheme(newID, tfName.getText(), tfPath.getText(), taDescription.getText());
		
		return am;
	}
	
	public void close()
	{
		StorageAdminView st = (StorageAdminView)frame;
		
		st.setEnabled(true);
		st.toFront();
		
		dispose();
	}
}
