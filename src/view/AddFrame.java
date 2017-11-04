package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Structure.Attribute;
import Structure.Entity;
import Structure.Storage;
import appCore.Core;
import model.DatabaseConnection;
import net.miginfocom.swing.MigLayout;

public class AddFrame extends JFrame{
	
	Entity entity;
	ArrayList<JTextField> inputs;
	public AddFrame(Entity entity){
		this.entity = entity;
		this.setLayout(new MigLayout());
		inputs = new ArrayList<>();
		for(Attribute curAtr : entity.getChildrenAttributes()){
			System.out.println("JESAM!");
			JLabel label = new JLabel(curAtr.getName());
			JTextField curF = new JTextField();
			inputs.add(curF);
			this.add(label, "split2, sg lb");
			this.add(curF, "sg bt, pushx, growx, wrap");
		}
		this.setLocationRelativeTo(null);
		this.setSize(500, 1000);
		
		JButton button = new JButton("ADD");
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
				try {
					Vector<String> v = new Vector<>();
					for(JTextField curText : inputs){
						
						v.add(curText.getText());
						System.out.println("USAO!");
						
					}
					reader.addEntity(entity, v);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(Core.getInstance().getMainViewOu(), e1.toString(),"SQL Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.add(button);
		this.setVisible(true);
	}
	
	

}
