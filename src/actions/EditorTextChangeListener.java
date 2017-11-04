package actions;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import appCore.Core;

public class EditorTextChangeListener implements DocumentListener
{
	@Override
	public void insertUpdate(DocumentEvent e)
	{
		if(Core.getInstance().getMainViewMa().isTimerStatus())
			Core.getInstance().getMainViewMa().setChanged(true);
		Core.getInstance().getMainViewMa().setButtonSaveStatus(false);
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		if(Core.getInstance().getMainViewMa().isTimerStatus())
			Core.getInstance().getMainViewMa().setChanged(true);
		Core.getInstance().getMainViewMa().setButtonSaveStatus(false);
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		if(Core.getInstance().getMainViewMa().isTimerStatus())
			Core.getInstance().getMainViewMa().setChanged(true);
		Core.getInstance().getMainViewMa().setButtonSaveStatus(false);
	}
}
