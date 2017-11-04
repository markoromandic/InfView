package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Structure.Attribute;
import Structure.Entity;
import Structure.Storage;
import appCore.Core;
import model.DatabaseConnection;
import net.miginfocom.swing.MigLayout;

public class SearchFrame extends JFrame {
	Entity entity;
	ArrayList<JTextField> inputs;
	public SearchFrame(Entity entity){
//		System.out.println("WAAAAAT!");
		this.entity = entity;
		this.setLayout(new MigLayout());
		inputs = new ArrayList<>();
		System.out.println(entity.getName());
		for(Attribute curAtr : entity.getChildrenAttributes()){
//			System.out.println("HEROROROO");
//			System.out.println(curAtr.getValue());
			if(curAtr.getValue().equals("VARCHAR") || curAtr.getValue().equals("INT") || curAtr.getValue().equals("int") || curAtr.getValue().equals("NUMERIC")){
				JLabel label = new JLabel(curAtr.getName()+" type: "+ curAtr.getValue());
				JTextField curF = new JTextField();
				inputs.add(curF);
				this.add(label, "split2, sg lb");
				this.add(curF, "sg bt, pushx, growx, wrap");
			}
		}
		this.setLocationRelativeTo(null);
		this.setSize(500, 1000);
		
		JButton button = new JButton("SEARCH");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String url = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUrl();
				String username = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUsername();
				String password = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getPassword();
				DatabaseConnection reader = Core.getInstance().getModel().getDatabaseConnection();
				reader.initialize(url, username, password);
				System.out.println("VELICINA:"+inputs.size());
				Vector<String> v = new Vector<>();
				for(JTextField curText : inputs){
					
					v.add(curText.getText());
//						System.out.println("USAO!");
					
				}
				reader.searchEntity(entity, v); 
			}
		});
		
		this.add(button);
		this.setVisible(true);
	}
	
	
}
