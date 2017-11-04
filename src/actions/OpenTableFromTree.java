package actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

import Structure.Entity;
import Structure.Package;
import Structure.Storage;
import appCore.Core;
import constants.UserType;

public class OpenTableFromTree extends MouseAdapter
{

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getClickCount() == 2  && Core.getInstance().getModel().getUserModel().getLogInUserType().equals(UserType.OPERATIVE_USER)) 
		{
//		    System.err.println(Core.getInstance().getMainViewOu().getTree().getClosestPathForLocation(e.getX(), e.getY()));
		
		    DefaultMutableTreeNode clicked = (DefaultMutableTreeNode) Core.getInstance().getMainViewOu().getTree().getClosestPathForLocation(e.getX(), e.getY()).getLastPathComponent();
		    
		    
		    if (clicked.getUserObject() instanceof Entity){
		    	Entity entity = (Entity)clicked.getUserObject();
		    	String url = "";
		    	DefaultMutableTreeNode currentForURL = (DefaultMutableTreeNode) clicked.getParent();
		    	while (currentForURL != null){
		    		if(currentForURL.getUserObject() instanceof Entity){
		    			url= ((Entity) currentForURL.getUserObject()).getUrl() + url;
		    		}
		    		else if(currentForURL.getUserObject() instanceof Package){
//		    			System.out.println("PACKAGE NAME: "+ ((Package) currentForURL.getUserObject()).getName());
		    			url= ((Package) currentForURL.getUserObject()).getUrl() + url;
		    		}
		    		else if(currentForURL.getUserObject() instanceof Storage){
		    			url= ((Storage) currentForURL.getUserObject()).getUrl() + File.separator + url;
		    		}
		    		
		    		currentForURL = (DefaultMutableTreeNode) currentForURL.getParent();
		    	}
		    	if(entity.getDataBaseType().equals("ser")){
		    		Core.getInstance().getMainViewOu().fillSerTableUsingEntity(entity, url);
		    	}
		    	else if(entity.getDataBaseType().equals("sek")){
		    		Core.getInstance().getMainViewOu().fillSekTableUsingEntity(entity, url);
		    	}
		    	else if(entity.getDataBaseType().equals("sql")){
		    		System.out.println("HAHA " + url);
		    		Core.getInstance().getMainViewOu().fillSqlTableUsingEntity(entity, url);
		    	}
//		    	System.out.println(((Structure.Entity) clicked.getUserObject()).getName());
		    }
//		    System.err.println(Core.getInstance().getMainViewOu().getTree().getLastSelectedPathComponent());
		}
	}
}
