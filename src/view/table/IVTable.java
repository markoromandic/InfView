package view.table;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Structure.Attribute;
import Structure.Entity;
import Structure.Relation;
import Structure.Storage;
import appCore.Core;
import model.DatabaseConnection;
import model.file.IVAbstractFile;
import model.file.SerDatoteka;
import view.buttonBar.SQLToolBar;

public class IVTable extends JTable
{
	private Entity entity;
	private String url;
	boolean first;
	
	public IVTable(Entity entity, String url, boolean first)
	{
		this.first = first;
		this.entity = entity;
		if(!((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).isSQL())
			this.url = url + File.separator + entity.getUrl();
		else this.url = url.replace("\\", "");
		if(((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).isSQL())
			initializeOUSQL();
		else initializeOU();
	}
	
	public IVTable(Entity entity, String url, boolean first, ArrayList<Vector<String>> v)
	{
		this.first = first;
		this.entity = entity;
		this.url = url.replace("\\", "");
		initializeOUSQLSEARCH(v);
	}
	
	private void initializeOU()
	{
		DefaultTableModel tableModel = (DefaultTableModel)this.getModel();
		for(Attribute att : entity.getChildrenAttributes())
			tableModel.addColumn(att.getName());
		setModel(tableModel);
		SerDatoteka reader = new SerDatoteka(entity);
		ArrayList<Vector<String>> v = reader.fetchNextBlock(1000, url);
		
		for(Vector<String> curVector : v)
		tableModel.addRow(curVector);
	}
	
	private void initializeOUSQL()
	{
		DefaultTableModel tableModel = (DefaultTableModel)this.getModel();
		for(Attribute att : entity.getChildrenAttributes())
			tableModel.addColumn(att.getName());
		setModel(tableModel);
		String username = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUsername();
		String password = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getPassword();
		DatabaseConnection reader = Core.getInstance().getModel().getDatabaseConnection();
		reader.initialize(url, username, password);
		
		
		ArrayList<Vector<String>> v;
		try {
			v = reader.getAllEntity(entity);
			for(Vector<String> curVector : v)
				tableModel.addRow(curVector);
			
			if(first){
//				int indexSelected = Core.getInstance().getMainViewOu().getTableView().getSelectedIndex();
//				if(indexSelected == -1) indexSelected = 0;
				JTabbedPane tabsBottom = Core.getInstance().getMainViewOu().getTableBottomView().get(Core.getInstance().getMainViewOu().getTableBottomView().size()-1);
				
				for(Relation curRel : entity.getChildrenRelations()){
					Entity curEnt = curRel.getChildEntity();
					IVTable tbSql = new IVTable(curEnt, url, false);
					
					JScrollPane spSql = new JScrollPane(tbSql);
					String curTitle = curEnt.getName();
					tabsBottom.add(curTitle, spSql);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		System.out.println("Done!");
	}
	private void initializeOUSQLSEARCH(ArrayList<Vector<String>> v)
	{
		
		DefaultTableModel tableModel = (DefaultTableModel)this.getModel();
		for(Attribute att : entity.getChildrenAttributes())
			tableModel.addColumn(att.getName());
		setModel(tableModel);
		String username = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUsername();
		String password = ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getPassword();
		DatabaseConnection reader = Core.getInstance().getModel().getDatabaseConnection();
		reader.initialize(url, username, password);
		
		
//		ArrayList<Vector<String>> v;
		try {
//			System.out.println("KJFHAHFBFKAFSJKAJFSHJAFSKJAFH");
//			v = reader.getAllEntity(entity);
			for(Vector<String> curVector : v)
				tableModel.addRow(curVector);
			
//			System.out.println("KJFHAHFBFKAFSJKAJFSHJAFSKJAFH");
			if(first){
//				int indexSelected = Core.getInstance().getMainViewOu().getTableView().getSelectedIndex();
//				if(indexSelected == -1) indexSelected = 0;
				JTabbedPane tabsBottom = Core.getInstance().getMainViewOu().getTableBottomView().get(Core.getInstance().getMainViewOu().getTableBottomView().size()-1);
				
//				System.out.println("AAAAA");
				for(Relation curRel : entity.getChildrenRelations()){
//					System.out.println("FFFF");
					Entity curEnt = curRel.getChildEntity();
					IVTable tbSql = new IVTable(curEnt, url, false);
					
					JScrollPane spSql = new JScrollPane(tbSql);
					String curTitle = curEnt.getName();
//					System.out.println("CAK I OVDE!");
					tabsBottom.add(curTitle, spSql);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		System.out.println("Done!");
	}
}
