package actions.generate;

import java.awt.event.ActionEvent;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import actions.AbstractEditorAction;
import appCore.Core;
import constants.EditorMetaSchemeConstants;

public class GenerateAttribute extends AbstractEditorAction
{
	public GenerateAttribute()
	{
		putValue(NAME, EditorMetaSchemeConstants.GENERATE_ATTRIBUTE);
		putValue(SMALL_ICON, loadIcon(EditorMetaSchemeConstants.GENERATE_ATTRIBUTE_ICON));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int offset = Core.getInstance().getMainViewMa().getTextAreaEditor().getCaretPosition();
		Document document = Core.getInstance().getMainViewMa().getTextAreaEditor().getDocument();
		
		try
		{
			document.insertString(offset, Core.getInstance().getModel().getModelGenerator().getAttributeGenerator(), null);
		}
		catch (BadLocationException e1)
		{
			e1.printStackTrace();
		}
	}

}
