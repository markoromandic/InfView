package view.dialogs;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import appCore.Core;
import constants.AppConstants;
import constants.DialogConstants;
import model.MetaScheme;
import net.miginfocom.swing.MigLayout;
import view.MainViewOU;
import view.metaSchemeEditor.MainViewMA;
import view.storageAdmin.ListMetaSchemes;

public class ChangeMeta extends JFrame
{
	private MigLayout migLayout = null;
	private ListMetaSchemes listMetaSchemes = null;
	private JButton btOk = null;
	private MainViewOU mo = null;
	private MainViewMA ma = null;
	
	public ChangeMeta(MainViewOU mo)
	{
		this.mo = mo;
		initializeOP();
	}
	
	public ChangeMeta(MainViewMA ma)
	{
		this.ma = ma;
		initializeMA();
	}
	
	private void initializeOP()
	{
		mo.setEnabled(false);
		
		setTitle(DialogConstants.CHOOSE_DATABASE);
		
		migLayout = new MigLayout();
		setLayout(migLayout);
		
		listMetaSchemes = new ListMetaSchemes();
		listMetaSchemes.setBorder(BorderFactory.createTitledBorder(DialogConstants.DATABASES));
		JScrollPane scListMeta = new JScrollPane(listMetaSchemes);
		scListMeta.setBorder(BorderFactory.createEmptyBorder());
		
		btOk = new JButton("Ok");
		btOk.addActionListener(Core.getInstance().getActionManager().getChangeMetaScheme());
		
		add(listMetaSchemes, "push, grow, wrap");
		add(btOk, "tag ok");
		
		setMinimumSize(new Dimension(350, 250));
		
		ImageIcon iconNotResized = new ImageIcon(AppConstants.APP_LOGO);

		Image iconInfView = iconNotResized.getImage();
		iconInfView = iconInfView.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		setIconImage(iconInfView);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
	}
	
	private void initializeMA()
	{
		ma.setEnabled(false);
		
		setTitle(DialogConstants.CHOOSE_META_SCHEME);
		
		migLayout = new MigLayout();
		setLayout(migLayout);
		
		listMetaSchemes = new ListMetaSchemes();
		listMetaSchemes.setBorder(BorderFactory.createTitledBorder(DialogConstants.META_SCHEMES));
		JScrollPane scListMeta = new JScrollPane(listMetaSchemes);
		scListMeta.setBorder(BorderFactory.createEmptyBorder());
		
		btOk = new JButton(DialogConstants.SELECT);
		btOk.addActionListener(Core.getInstance().getActionManager().getChangeMetaScheme());
		
		add(listMetaSchemes, "push, grow, wrap");
		add(btOk, "tag ok");
		
		setMinimumSize(new Dimension(350, 250));
		
		ImageIcon iconNotResized = new ImageIcon(AppConstants.APP_LOGO);

		Image iconInfView = iconNotResized.getImage();
		iconInfView = iconInfView.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		setIconImage(iconInfView);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
	}
	
	public MetaScheme getSelectedMeta()
	{
		return listMetaSchemes.getSelectedValue();
	}
	
	public void enableOU()
	{
		mo.setEnabled(true);
		mo.toFront();
		dispose();
		Core.getInstance().setChooseMeta(null);
	}
	
	public void enableMA()
	{
		ma.setEnabled(true);
		ma.toFront();
		dispose();
		Core.getInstance().setChooseMeta(null);
	}
}