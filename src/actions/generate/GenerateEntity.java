package actions.generate;

import java.awt.event.ActionEvent;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import actions.AbstractEditorAction;
import appCore.Core;
import constants.EditorMetaSchemeConstants;

public class GenerateEntity extends AbstractEditorAction
{
	public GenerateEntity()
	{
		putValue(NAME, EditorMetaSchemeConstants.GENERATE_ENTITY);
		putValue(SMALL_ICON, loadIcon(EditorMetaSchemeConstants.GENERATE_ENTITY_ICON)); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int offset = Core.getInstance().getMainViewMa().getTextAreaEditor().getCaretPosition();
		Document document = Core.getInstance().getMainViewMa().getTextAreaEditor().getDocument();
		
		try
		{
			document.insertString(offset, Core.getInstance().getModel().getModelGenerator().getEntityGenerator(), null);
		}
		catch (BadLocationException e1)
		{
			e1.printStackTrace();
		}
	}

}
