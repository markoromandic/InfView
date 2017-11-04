package actions;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import appCore.Core;

public class GetLastPositionEditorMouse implements CaretListener
{
	@Override
	public void caretUpdate(CaretEvent e)
	{
		if(Core.getInstance().getMainViewMa() != null)
		{
			int position = Core.getInstance().getMainViewMa().getTextAreaEditor().getCaretPosition();
			Core.getInstance().getMainViewMa().setLastPosition(position);
		}
	}
}
