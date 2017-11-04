package view.dialogs;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import appCore.Core;
import constants.AdminViewConstants;
import constants.DialogConstants;
import model.MetaScheme;
import net.miginfocom.swing.MigLayout;
import view.storageAdmin.StorageAdminView;

public class MetaSchemeDialog extends JFrame
{
	private MigLayout migLayout = null;
	
	private MetaScheme m = null;

	private JFrame frame = null;
	
	private JLabel lbID = null;
	private JLabel lbName = null;
	private JLabel lbLocation = null;
	
	private JTextField tfName = null;
	private JTextField tfPath = null;
	
	private JButton btApply = null;
	private JButton btFinish = null;
	
	private JTextArea taDescription = null;

	private String checkYes = "";
	
	public MetaSchemeDialog(MetaScheme m, JFrame frame)
	{
		frame.setEnabled(false);
		this.frame = frame;
		this.m = m;
		initilaizeGUI();
		initialize();
		addWindowListener(Core.getInstance().getActionManager().getDialogMetaSchemeListener());
	}

	private void initialize()
	{
		setTitle(DialogConstants.META_SCHEME_DIALOG_TITLE + m.getName());
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
		
		lbID = new JLabel(DialogConstants.ID_LABEL + m.getId());
		
		lbName = new JLabel(DialogConstants.NAME_LABEL);
		tfName = new JTextField(m.getName());
		tfName.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListener());
		
		lbLocation = new JLabel(DialogConstants.LOCATION_LABEL);
		tfPath = new JTextField(m.getLocation());
		tfPath.addMouseListener(Core.getInstance().getActionManager().getSelectPath());
		
		btApply = new JButton(DialogConstants.APPLY);
		btApply.setEnabled(false);
		btApply.addActionListener(Core.getInstance().getActionManager().getApplyMetaScheme());
		
		btFinish = new JButton(DialogConstants.FINISH);
		btFinish.addActionListener(Core.getInstance().getActionManager().getFinishMetaSchemeST());
		
		taDescription = new JTextArea(m.getDescription());
		taDescription.setBorder(BorderFactory.createTitledBorder(DialogConstants.DESCRIPTION));
		taDescription.setLineWrap(true);
		taDescription.getDocument().addDocumentListener(Core.getInstance().getActionManager().getDocChangeListener());
		JScrollPane deScrollPane = new JScrollPane(taDescription);
		
		deScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		add(lbID, "wrap, sg 1");
		add(lbName, "split2, sg 1");
		add(tfName, "sg 2, pushx, growx, wrap");
		add(lbLocation, "split2, sg 1");
		add(tfPath, "sg 2, pushx, growx, wrap");
		add(deScrollPane, "span, push, grow, wrap");
		add(btApply, "split2, tag apply");
		add(btFinish, "tag finish");
	}
	
	public void selectPath()
	{
		JFileChooser fc = new JFileChooser(".");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle(DialogConstants.SELECT_TARGET_TITLE);
		fc.setCurrentDirectory(new File(tfPath.getText()));
		
		int returnVal = fc.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			String newPath = fc.getSelectedFile().getAbsolutePath();
			
			if(!newPath.equals(tfPath.getText()))
				tfPath.setText(newPath);
			
			btApply.setEnabled(true);
		}
	}

	public void setNewParametarsOfMetaScheme()
	{
		if(!m.getLocation().equals(tfPath.getText()) && m.getName().equals(tfName.getText()))
		{
			File from = new File(m.getLocation() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION);
			
			File to = new File(tfPath.getText() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION);
			
			if(to.exists())
			{
				System.err.println("FAJL POSTOJI(NAPRAVICE SE DA PITA DA LI ZELIM DA PREKOPIRAM I DALJE, TRENUTNO SAMO OBRISE STARI I PREKOPIRA)");
				to.delete();
			}
				
			try
			{
				Files.copy(from.toPath(), to.toPath());
				from.delete();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			m.setLocation(tfPath.getText());
		}
		else if(!m.getLocation().equals(tfPath.getText()) && !m.getName().equals(tfName.getText()))
		{
			File from = new File(m.getLocation() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION);
			
			File to = new File(tfPath.getText() + File.separator + tfName.getText() + DialogConstants.JSON_EXTENSION);
			
			if(to.exists())
			{
				System.err.println("FAJL POSTOJI(NAPRAVICE SE DA PITA DA LI ZELIM DA PREKOPIRAM I DALJE, TRENUTNO SAMO OBRISE STARI I PREKOPIRA)");
				to.delete();
			}
				
			try
			{
				Files.copy(from.toPath(), to.toPath());
				from.delete();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			m.setName(tfName.getText());
			m.setLocation(tfPath.getText());
			
			setTitle(DialogConstants.META_SCHEME_DIALOG_TITLE + m.getName());
		}
		else if(m.getLocation().equals(tfPath.getText()) && !m.getName().equals(tfName.getText())) 
		{
			File rename = new File(m.getLocation() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION);
			
			File check = new File(m.getLocation() + File.separator + tfName.getText() + DialogConstants.JSON_EXTENSION);
			
			if(check.exists())
			{
				System.err.println("FAJL POSTOJI(NAPRAVICE SE DA PITA DA LI ZELIM DA PREKOPIRAM I DALJE, TRENUTNO SAMO OBRISE STARI I PREKOPIRA)");
				check.delete();
			}
			
			rename.renameTo(check);
			
			m.setName(tfName.getText());
			
			setTitle(DialogConstants.META_SCHEME_DIALOG_TITLE + m.getName());
		}
		if(!m.getDescription().equals(taDescription.getText()))
		{
			m.setDescription(taDescription.getText());
		}
	
		btApply.setEnabled(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void setEnabledApply()
	{
		btApply.setEnabled(true);
	}
	
	public void askForSave()
	{
		if(btApply.isEnabled())
		{
			Object[] options = {DialogConstants.YES, DialogConstants.NO, DialogConstants.CANCEL};
			JOptionPane optionPane = new JOptionPane(DialogConstants.SAVE + tfName.getText() + DialogConstants.QUESTION, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION, null, options, options[0]);
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
	
	public void deleteThis()
	{
		StorageAdminView s = (StorageAdminView)frame;
		dispose();
		s.deleteDialog();
		s.setEnabled(true);
		s.toFront();
	}
	
	public String isCheckYes()
	{
		return checkYes;
	}
}
