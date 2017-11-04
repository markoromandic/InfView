package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JList;

import appCore.Core;
import constants.Constants;
import constants.DialogConstants;
import model.MetaScheme;

public class SelectMetaToInterpret extends MouseAdapter implements KeyListener, ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(Core.getInstance().isOp())
		{
			MetaScheme m = Core.getInstance().getChooseMeta().getSelectedMeta();
			
			String path = m.getLocation() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION;
			
			Core.getInstance().getModel().startInterpreter();
			
			Core.getInstance().getModel().loadFromJSONToRoot(path);
			
			Core.getInstance().getMainViewOu().getTree().getTreeModel().initalizeOU();
			
			Core.getInstance().getChooseMeta().enableOU();
		}
		else if(Core.getInstance().isMa())
		{
			MetaScheme m = Core.getInstance().getChooseMeta().getSelectedMeta();
			
			String path = m.getLocation() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION;
			
			Core.getInstance().getModel().startInterpreter();
			
			Core.getInstance().getModel().loadFromJSONToRoot(path);
			
			Object metashema = Core.getInstance().getModel().getInterpreter().loadJSONForView(path);
			Core.getInstance().getMainViewMa().loadJSONToTextPane(metashema, Core.getInstance().getModel().getInterpreter().getMapper());
			
			Core.getInstance().getMainViewMa().getTreeMetaScheme().getTreeModel().initalizeMA();
			
			Core.getInstance().getChooseMeta().enableMA();
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyPressedNum = e.getKeyCode();
		String keyChar = KeyEvent.getKeyText(keyPressedNum);
		
		if(keyChar.equals(Constants.ENTER))
		{
			if(Core.getInstance().isOp())
			{
				MetaScheme m = Core.getInstance().getChooseMeta().getSelectedMeta();
				
				String path = m.getLocation() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION;
				
				Core.getInstance().getModel().startInterpreter();
				
				Core.getInstance().getModel().loadFromJSONToRoot(path);
				
				Core.getInstance().getMainViewOu().getTree().getTreeModel().initalizeOU();
				
				Core.getInstance().getChooseMeta().enableOU();
			}
			else if(Core.getInstance().isMa())
			{
				MetaScheme m = Core.getInstance().getChooseMeta().getSelectedMeta();
				
				String path = m.getLocation() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION;
				
				Core.getInstance().getModel().startInterpreter();
				
				Core.getInstance().getModel().loadFromJSONToRoot(path);
				
				Object metashema = Core.getInstance().getModel().getInterpreter().loadJSONForView(path);
				Core.getInstance().getMainViewMa().loadJSONToTextPane(metashema, Core.getInstance().getModel().getInterpreter().getMapper());
				
				Core.getInstance().getMainViewMa().getTreeMetaScheme().getTreeModel().initalizeMA();
				
				Core.getInstance().getChooseMeta().enableMA();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		super.mouseClicked(e);
		
		if(e.getClickCount() == 2)
		{
			JList theList = (JList) e.getSource();
			
			int index = theList.locationToIndex(e.getPoint());
			
			MetaScheme m = (MetaScheme)theList.getModel().getElementAt(index);
			
			String path = m.getLocation() + File.separator + m.getName() + DialogConstants.JSON_EXTENSION;
		
			Core.getInstance().getModel().startInterpreter();
			
			if(Core.getInstance().isOp())
			{
				Core.getInstance().getModel().loadFromJSONToRoot(path);
				
				Core.getInstance().getMainViewOu().getTree().getTreeModel().initalizeOU();

				Core.getInstance().getChooseMeta().enableOU();
			}
			else if(Core.getInstance().isMa())
			{
				Core.getInstance().getModel().loadFromJSONToRoot(path);
				
				Object metashema = Core.getInstance().getModel().getInterpreter().loadJSONForView(path);
				Core.getInstance().getMainViewMa().loadJSONToTextPane(metashema, Core.getInstance().getModel().getInterpreter().getMapper());
				
				Core.getInstance().getMainViewMa().getTreeMetaScheme().getTreeModel().initalizeMA();

				Core.getInstance().getChooseMeta().enableMA();
			}
		}
	}
}
