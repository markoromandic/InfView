package actions;

import appCore.Core;

public class EditorTimer
{
	public void check()
	{
		String editedJSON = Core.getInstance().getMainViewMa().getTextAreaEditor().getText();
		boolean validated = Core.getInstance().getModel().getInterpreter().validate(editedJSON);
		if(validated)
		{
			Core.getInstance().getModel().loadFromJSONEditorToTree();
			Core.getInstance().getMainViewMa().edit();
		}
	}
}
