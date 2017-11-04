package actions;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import appCore.Core;

public class CheckerAutoValidation implements ItemListener
{
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		boolean status = Core.getInstance().getMainViewMa().checkAutoValidationStatus();
		
		Core.getInstance().getMainViewMa().setTimerStatus(status);
	}
}
