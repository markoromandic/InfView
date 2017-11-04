package actions.generate;

import java.awt.event.ActionEvent;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import actions.AbstractEditorAction;
import appCore.Core;
import constants.EditorMetaSchemeConstants;

public class GeneratePackage extends AbstractEditorAction
{
	public GeneratePackage()
	{
		putValue(NAME, EditorMetaSchemeConstants.GENERATE_PACKAGE);
		putValue(SMALL_ICON, loadIcon(EditorMetaSchemeConstants.GENERATE_PACKAGE_ICON));
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int offset = Core.getInstance().getMainViewMa().getTextAreaEditor().getCaretPosition();
		Document document = Core.getInstance().getMainViewMa().getTextAreaEditor().getDocument();
		
		try
		{
			document.insertString(offset, Core.getInstance().getModel().getModelGenerator().getPackageGenerator(), null);
		}
		catch (BadLocationException e1)
		{
			e1.printStackTrace();
		}
	}

}
