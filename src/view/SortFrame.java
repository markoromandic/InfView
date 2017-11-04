package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Structure.Attribute;
import Structure.Entity;
import Structure.Storage;
import appCore.Core;
import javafx.scene.control.ComboBox;
import model.DatabaseConnection;
import net.miginfocom.swing.MigLayout;

public class SortFrame extends JFrame
{
	private Entity entity;
	ArrayList<JComboBox<String>> sorting;
	
	public SortFrame(Entity entity)
	{
		this.entity = entity;
		initialize();
	}
	
	private void initialize()
	{
		MigLayout migLayout = new MigLayout();
		setLayout(migLayout);
		setLocationRelativeTo(null);
		sorting = new ArrayList<>();
		
		for(Attribute atr : entity.getChildrenAttributes())
		{
			JLabel label = new JLabel(atr.getName());
			JComboBox<String> cb = new JComboBox<>();
			cb.addItem("ASC");
			cb.addItem("DESC");
			sorting.add(cb);
			this.add(label, "split2, sg lb");
			this.add(cb, "sg bt, pushx, growx, wrap");
		}
		this.setSize(500, 1000);
		
		JButton button = new JButton("Sort");
		button.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String url = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUrl();
				String username = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUsername();
				String password = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getPassword();
				DatabaseConnection reader = Core.getInstance().getModel().getDatabaseConnection();
				reader.initialize(url, username, password);
				
				Vector<String> v = new Vector<>();
				for(JComboBox<String> curCombo : sorting)
				{
					v.add(String.valueOf(curCombo.getSelectedItem()));
				}
				reader.sortEntity(entity, v);

			}
		});
		
		add(button);
		setVisible(true);
	}
}
