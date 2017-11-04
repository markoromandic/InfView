package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.DatabaseConnection;
import net.miginfocom.swing.MigLayout;

public class UpdateTab extends JFrame
{
	private Entity entity;
	
	private ArrayList<JTextField> fieldsSearch;
	private ArrayList<JTextField> fieldsUpdate;

	public UpdateTab(Entity entity)
	{
		this.entity = entity;
		initialize();
	}
	
	private void initialize()
	{
		MigLayout migLayout = new MigLayout();
		setLayout(migLayout);
		setLocationRelativeTo(null);
		fieldsUpdate = new ArrayList<>();
		fieldsSearch = new ArrayList<>();
		
		for(Attribute atr : entity.getChildrenAttributes())
		{
			JLabel label = new JLabel(atr.getName());
			JTextField txt = new JTextField();
			if(!atr.isPrimary())
				fieldsUpdate.add(txt);
			else
				fieldsSearch.add(txt);
				
			this.add(label, "split2, sg lb");
			this.add(txt, "sg bt, pushx, growx, wrap");
		}
		this.setSize(500, 1000);
		
		JButton button = new JButton("Update");
		
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
				Vector<String> search = new Vector<>();
				for(JTextField curTxt : fieldsUpdate)
					v.add(curTxt.getText());
				for(JTextField curTxt : fieldsSearch)
					search.add(curTxt.getText());
				
				reader.updateEntity(entity, v, search);
			}
		});
		
		add(button);
		
		setVisible(true);
	}
}
