package view.buttonBar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import appCore.Core;
import net.miginfocom.swing.MigLayout;

public class SQLToolBar extends JPanel
{
	private JButton btAdd, btDelete, btUpdate, btFind, btSort;
	
	public SQLToolBar()
	{
		initialize();
	}
	
	private void initialize()
	{
		MigLayout migLayout = new MigLayout();
		setLayout(migLayout);
		
		btAdd = new JButton("Add");
		btAdd.addActionListener(Core.getInstance().getActionManager().getAddSqlRecord());
		btDelete = new JButton("Delete");
		btDelete.addActionListener(Core.getInstance().getActionManager().getDeleteSqlRecord());
		btUpdate = new JButton("Update");
		btUpdate.addActionListener(Core.getInstance().getActionManager().getUpdateSqlRecord());
		btFind = new JButton("Find");
		btFind.addActionListener(Core.getInstance().getActionManager().getFindSqlRecord());
		btSort = new JButton("Sort");
		btSort.addActionListener(Core.getInstance().getActionManager().getSortSql());
		
		add(btAdd, "split4, sg bt");
		add(btUpdate, "sg bt");
		add(btDelete, "sg bt");
		add(btFind, "sg bt");
		add(btSort, "sg bt");
	}
}
