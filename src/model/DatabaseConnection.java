package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import Structure.Attribute;
import Structure.Entity;
import Structure.Storage;
import appCore.Core;
import view.table.IVTable;

public class DatabaseConnection
{
	private Connection connection;
	
	private boolean connected = false;
	private String url;
	private String username;
	private String password;
//	private String database;
	
	public DatabaseConnection()
	{
		
//		initialize();
	}
	
	public void initialize(String url, String username, String password)
	{
		if(!connected){
			this.url = url;
			this.username = username;
			this.password = password;
	//		this.database = database;
			if(connect())
			{
//				System.out.println("CONNECTED?!");
				connected = true;
				try
				{
					Statement statement = connection.createStatement();
					
					ResultSet rs = statement.executeQuery("SELECT * FROM DRZAVA");
					
					while(rs.next())
					{
//						System.out.println(rs.getString("DR_NAZIV"));
					}
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
	}
	
	private boolean connect()
	{
		try
		{
//			System.out.println(url);
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:jtds:sqlserver://" + url, username, password);
			
			return true;
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("NEMA KLASE");
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			System.err.println("NE MOGU DA SE KONEKTUJEM");
		}
		
		return false;
	}
	
	
	public ArrayList<Vector<String>> getAllEntity(Entity entity) throws SQLException{
		ArrayList<Vector<String>> result = new ArrayList<>();
		
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("SELECT * FROM "+entity.getCode());
//		System.out.println("came this far...");
//		System.out.println("SELECT * FROM "+entity.getCode());
		while(rs.next())
		{
			Vector<String> v = new Vector<>();
			for(Attribute atr : entity.getChildrenAttributes()){
				v.add(rs.getString(atr.getCode()));
			}
			result.add(v);
		}
		
		
		return result;
	}
	
	public void deleteEntity(Entity entity, Vector<String> v) throws SQLException{
		String whereClause = "";
		int i = 0;
		for (Attribute atr : entity.getChildrenAttributes()){
			if(atr.isPrimary()){
				if(whereClause.equals(""))
					whereClause += atr.getCode()+"='"+v.get(i)+"'";
				else
					whereClause += " AND "+atr.getCode()+"='"+v.get(i)+"'";
			}
			
			i++;
		}
		try
		{
			Statement statement = connection.createStatement();
			statement.execute("DELETE "+entity.getCode()+" WHERE "+whereClause);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(Core.getInstance().getMainViewOu(), e.toString(),"SQL Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void sortEntity(Entity entity, Vector<String> v)
	{
		try
		{
			String selectClause = "";
			int i = 0;
			
			for (Attribute atr : entity.getChildrenAttributes())
			{
				if(selectClause.equals(""))
					selectClause += atr.getCode() + " " + v.get(i);
				else
					selectClause += ", " + atr.getCode() + " " + v.get(i);
				
				i++;
			}
			
			String sql  = "SELECT * FROM " + entity.getCode() + " ORDER BY " + selectClause;
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Vector<String>> aV = new ArrayList<>();
			while(rs.next()){
				Vector<String> vector = new Vector<String>();
				
				for(Attribute curAtr : entity.getChildrenAttributes()){
					vector.add(rs.getString(curAtr.getCode()));
				}
				
				aV.add(vector);
			}
			
			Core.getInstance().getMainViewOu().fillSqlTableUsingEntity(entity, ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUrl(), aV);
//			System.out.println("Dodjoh");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(Core.getInstance().getMainViewOu(), e.toString(),"SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateEntity(Entity entity, Vector<String> v, Vector<String> search)
	{
		System.out.println("DUZINA:"+search.size());
		try
		{
			String atr = "";
			String sql = "";
			String pk = "";
			
			int i = 1, k = 1;
			System.out.println("DUZINA:"+search.size());
			for(Attribute currAtr : entity.getChildrenAttributes())
			{
				if(currAtr.isPrimary())
				{
					if(pk.equals(""))
						pk += currAtr.getCode() + "=? ";
					else
						pk += " AND " + currAtr.getCode() + "=?";
					i++;
				}
				else 
				{
					if(atr.equals(""))
						atr += currAtr.getCode() + "=?";
					else
						atr += "," + currAtr.getCode() + "=?";
					k++;
				}
			}
			System.out.println("DUZINA:"+search.size());
			sql = "UPDATE " + entity.getCode() + " SET " + atr + " WHERE " + pk;
			
			System.out.println(sql);
			System.out.println("DUZINA:"+search.size());
			int j;
			
			PreparedStatement statement = connection.prepareStatement(sql);
			for(j=1; j<i;j++)
			{
				statement.setString(j, v.get(j - 1));
				System.out.println("HEJHEJ");
			}
			System.out.println("DUZINA:"+search.size());
			System.out.println(k);
			
			for(int m = 1; m < k; m++)
			{
				System.out.println(search.size());
				System.out.println("M-1:"+ (m+j));
				String pom = search.get(m - 1);
				statement.setString(m - 1 + j, pom);
			}
			
			statement.execute();
			
			Core.getInstance().getMainViewOu().fillSqlTableUsingEntity(entity, ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUrl());
//			System.out.println("Dodjoh");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(Core.getInstance().getMainViewOu(), e.toString(),"SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addEntity(Entity entity, Vector<String> v) throws SQLException{
		String atr = "";
		String question = "";
		int i = 1;
		for(Attribute curAtr : entity.getChildrenAttributes()){
			if(atr.equals("")){
				atr += curAtr.getCode();
				question += "?";
			}
			else{
				atr +=","+curAtr.getCode();
				question += ",?";
			}
			i++;
		}
		String sql = "INSERT INTO "+entity.getCode()+" (" + atr +") VALUES ("+question+")";
		PreparedStatement statement = connection.prepareStatement(sql);
		for(int j=1; j<i;j++){
			statement.setString(j, v.get(j-1));
		}
		statement.execute();
	}
	
	public void searchEntity(Entity entity, Vector<String> v){
		try{
		String atr = "";
		String question = "";
		int i = 0;
		String sql = "SELECT * FROM "+entity.getCode();
		int j = 0;
//		ArrayList<Integer> varchars = new ArrayList<>();
		int[] var = new int[10];
		int brojacVar = -1;
		for(Attribute curAtr : entity.getChildrenAttributes()){
			
			if(curAtr.getValue().equals("VARCHAR") || curAtr.getValue().equals("INT") || curAtr.getValue().equals("int") || curAtr.getValue().equals("NUMERIC")){
				brojacVar++;
				if(!v.get(j).equals("")){
					if(i>0) sql+= "AND ";
					else sql+=" WHERE ";
					
					if(curAtr.getValue().equals("VARCHAR")){
						sql += curAtr.getCode()+" LIKE ? ";
						var[brojacVar]=1;
//						varchars.add(i);
					}
					else {
						String operand = v.get(i).substring(0,1);
						sql += curAtr.getCode()+" "+operand+" ? ";
					
					
					}
							
							
							
					i++;
				}
				j++;
			}
		}
		
		
		System.out.println("Brojac VAR:" + brojacVar);
		System.out.println(sql);
		PreparedStatement statement = connection.prepareStatement(sql);
//		for(int z = 1; z<=i; z++){
//			System.out.println("THIS:"+v.get(z-1));
////			boolean isIn = false;
////			for(int o=0;o<i;o++){
////				if(varchars.get(o)==z-1) isIn=true;
////			}
////			if(isIn)
////				statement.setString(z, v.get(z-1));
////			else{ statement.setString(z, v.get(z-1).substring(1));
////			 System.out.println("HUE:"+v.get(z-1).substring(1));
////			}
////			System.out.println("HUE:"+v.get(z-1).substring(1));
////			statement.setString(z, v.get(z-1).substring(1));
//			System.out.println("'%"+v.get(z-1)+"%'");
//			statement.setString(z, "'%"+v.get(z-1)+"%'");
//		}
		int brojac = 1;
		int brojacVar2 = -1;
		System.out.println("NOVA VELICINA MSM ONO:"+ v.size());
		for(String str : v){
			brojacVar2++;
			if(!str.equals("")){
				if(var[brojacVar2]==1){
					System.out.println("THIS:"+str);
					System.out.println("'%"+str+"%'");
					statement.setString(brojac, "'%"+str+"%'");
				} else {
					statement.setString(brojac, str.substring(1));
				}
				brojac++;
			}
		}
		
//		System.out.println("OVO JE STATEMENT NA KRAJU:"+statement);
		ResultSet rs = statement.executeQuery();
		ArrayList<Vector<String>> aV = new ArrayList<>();
		while(rs.next()){
			Vector<String> vector = new Vector<String>();
			
			for(Attribute curAtr : entity.getChildrenAttributes()){
				vector.add(rs.getString(curAtr.getCode()));
			}
			
			aV.add(vector);
		}
		
		Core.getInstance().getMainViewOu().fillSqlTableUsingEntity(entity, ((Storage)Core.getInstance().getModel().getTreeModel().getRoot()).getUrl(), aV);
		System.out.println("Dodjoh");
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Core.getInstance().getMainViewOu(), e.toString(),"SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
