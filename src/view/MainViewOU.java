package view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.mozilla.javascript.tools.shell.JSConsole;

import com.google.common.collect.Table;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import Structure.Entity;
import constants.AppConstants;
import constants.Constants;
import javafx.scene.control.ButtonBar;
import net.miginfocom.swing.MigLayout;
import sun.security.provider.VerificationProvider;
import view.buttonBar.IVButtonBar;
import view.buttonBar.SQLToolBar;
import view.buttonBar.TableToolbar;
import view.dialogs.ChangeMeta;
import view.menuBar.IVMenuBar;
import view.table.IVTable;
import view.tree.IVTree;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class MainViewOU extends JFrame
{
	

	private MigLayout migLayout = null;
	
	private IVMenuBar menuBar = null;
	private IVTree tree = null;
	private TableToolbar buttonBar = null;
	
	private JSplitPane treeSplitTable = null;
	private JSplitPane tableSplitTable = null;
	private JScrollPane treeScroll = null;
	private JTabbedPane tableView = null;
	private ArrayList<JTabbedPane> tableBottomView = null;
	private JScrollPane tableScroll = null;
	private IVTable table = null;
	private JPanel pnSer = null;
	private JPanel pnSek = null;
	private JPanel firstPanel = null;
	
	private ArrayList<IVTable> listaTabla;
	private ArrayList<Entity> listaEntitetaUTabelama;
	
	private boolean opened = false;
	
	private ChangeMeta chooseMeta = null;
	
	private String username = null;
	
//	private int gotToNumber = -1;

	public MainViewOU(String username)
	{
		this.username = username;
		initialize();
	}

	private void initialize()
	{
		migLayout = new MigLayout();
		setLayout(migLayout);
		
		listaTabla = new ArrayList<>();
		tableBottomView = new ArrayList<>();
		listaEntitetaUTabelama = new ArrayList<>();

		menuBar = new IVMenuBar();
		setJMenuBar(menuBar);
		
		initializeTree();
		makeFirstRightPanel();
		
		treeSplitTable = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, firstPanel);
		treeSplitTable.setDividerLocation(200);
		treeSplitTable.setOneTouchExpandable(true);
		treeSplitTable.setBorder(BorderFactory.createEtchedBorder());
		
		add(treeSplitTable, "push, grow");

		setMinimumSize(new Dimension(1250, 750));

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle(AppConstants.APP_TITLE + " - " + username);

		ImageIcon iconNotResized = new ImageIcon(AppConstants.APP_LOGO);

		Image iconInfView = iconNotResized.getImage();
		iconInfView = iconInfView.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		setIconImage(iconInfView);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		
		pack();
	}
	
	private void makeFirstRightPanel()
	{
		MigLayout migImage = new MigLayout();
		
		firstPanel = new JPanel();
		
		firstPanel.setLayout(migImage);
		
		ImageIcon icon = new ImageIcon(AppConstants.APP_LOGO);

		Image iconInfView = icon.getImage();
		iconInfView = iconInfView.getScaledInstance(411, 290, Image.SCALE_SMOOTH);
		icon = new ImageIcon(iconInfView);
		
		JLabel lbLogo = new JLabel(icon);
		
		firstPanel.add(lbLogo, "push, grow");
	}
	
	private void initializeTree()
	{
		tree = new IVTree();
		treeScroll = new JScrollPane(tree);
		treeScroll.setBorder(BorderFactory.createEmptyBorder());
	}

	public IVTree getTree()
	{
		return tree;
	}
	
	public void fillSerTableUsingEntity(Entity entity, String url)
	{
		MigLayout tableMigLayout = new MigLayout();
		
		JPanel pnSerTabela = new JPanel();
		
		buttonBar = new TableToolbar();
		
		pnSerTabela.setLayout(tableMigLayout);
		
		IVTable tbSer = new IVTable(entity, url, true);
		
		pnSerTabela.setLayout(tableMigLayout);
		JScrollPane spSer = new JScrollPane(tbSer);
		pnSerTabela.add(buttonBar, "pushx, growx, wrap");
		pnSerTabela.add(spSer, "push, grow");
		
		tableView.addTab(entity.getName(), pnSerTabela);
	}
	
	public void fillSekTableUsingEntity(Entity entity, String url)
	{
		MigLayout mgTop = new MigLayout();
		
		JPanel topTable = new JPanel();
		
		topTable.setLayout(mgTop);
		
		JPanel bottomTable = new JPanel();
		
		MigLayout mgBottom = new MigLayout();
		
		bottomTable = new JPanel(mgBottom);
		
		IVTable tb = new IVTable(entity, url, true);
		JScrollPane scTb= new JScrollPane(tb);
		
		TableToolbar tableToolbar = new TableToolbar();
		
		topTable.add(tableToolbar, "pushx, growx, wrap");
		topTable.add(scTb, "push, grow");
		
		tableSplitTable = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topTable, bottomTable);
		
		tableView.add(entity.getName(), tableSplitTable);
	}
	
	public void fillSqlTableUsingEntity(Entity entity, String url)
	{
		listaEntitetaUTabelama.add(entity);
		
		
		MigLayout mgTop = new MigLayout();
		
		JPanel topTable = new JPanel();
		
		topTable.setLayout(mgTop);
		MigLayout mgBottom = new MigLayout();
		JTabbedPane curTabPaneBot = new JTabbedPane();
		
//		curTabPaneBot.setLayout(mgBottom);
		tableBottomView.add(curTabPaneBot);
		
		IVTable tbSql = new IVTable(entity, url, true);
		
		listaTabla.add(tbSql);
		
		JScrollPane spSql = new JScrollPane(tbSql);
		
		SQLToolBar tableToolbar = new SQLToolBar();
		
		topTable.add(tableToolbar, "pushx, growx, wrap");
		topTable.add(spSql, "push, grow");
		
		
		
		
		
		tableSplitTable = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topTable, curTabPaneBot);
		tableSplitTable.setDividerLocation(450);
		
		tableView.add(entity.getName(), tableSplitTable);
		
	}
	
	public void fillSqlTableUsingEntity(Entity entity, String url, ArrayList<Vector<String>> aV)
	{
		listaEntitetaUTabelama.add(entity);
		
		
		MigLayout mgTop = new MigLayout();
		
		JPanel topTable = new JPanel();
		
		topTable.setLayout(mgTop);
		MigLayout mgBottom = new MigLayout();
		JTabbedPane curTabPaneBot = new JTabbedPane();
		
//		curTabPaneBot.setLayout(mgBottom);
		tableBottomView.add(curTabPaneBot);
		
		IVTable tbSql = new IVTable(entity, url, true, aV);
		
		listaTabla.add(tbSql);
		
		JScrollPane spSql = new JScrollPane(tbSql);
		
		SQLToolBar tableToolbar = new SQLToolBar();
		
		topTable.add(tableToolbar, "pushx, growx, wrap");
		topTable.add(spSql, "push, grow");
		
		
		
		
		
		tableSplitTable = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topTable, curTabPaneBot);
		tableSplitTable.setDividerLocation(450);
		
		tableView.add(entity.getName(), tableSplitTable);
		
	}
	
	public void openDialogToChangeMetaScheme()
	{
		opened = true;
		chooseMeta = new ChangeMeta(this);
	}
	
	public ChangeMeta getChangeMeta()
	{
		return chooseMeta;
	}

	public boolean isOpened()
	{
		return opened;
	}
	
	public void setTablePane()
	{
		tableView = new JTabbedPane();
		treeSplitTable.setRightComponent(tableView);
		treeSplitTable.setDividerLocation(200);
	}
	
	public void systemLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
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
	
	
	public ArrayList<IVTable> getListaTabla() {
		return listaTabla;
	}

	public JTabbedPane getTableView() {
		return tableView;
	}

	public ArrayList<Entity> getListaEntitetaUTabelama() {
		return listaEntitetaUTabelama;
	}

	public ArrayList<JTabbedPane> getTableBottomView() {
		return tableBottomView;
	}
	
	
	
	
}
