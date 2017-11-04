package test;

import javax.swing.JButton;
import javax.swing.JPanel;

import appCore.Core;
import net.miginfocom.swing.MigLayout;

public class TestUserEnter extends JPanel
{
	private JButton buttonOU = null;
	private JButton buttonSA = null;
	private JButton buttonMA = null;
	private JButton buttonSTA = null;
	private JButton buttonUA = null;

	public TestUserEnter()
	{
		initialize();
	}
	
	public void initialize()
	{
		MigLayout migLayout = new MigLayout();
		setLayout(migLayout);
		
		buttonOU = new JButton("Operative user");
		buttonOU.addActionListener(Core.getInstance().getActionManager().getOpenOperativeUser());
		buttonSA = new JButton("System admin");
		buttonSA.addActionListener(Core.getInstance().getActionManager().getOpenSystemAdmin());
		buttonMA = new JButton("Meta Scheme admin");
		buttonMA.addActionListener(Core.getInstance().getActionManager().getOpenMetaSchemeAdmin());
		buttonSTA = new JButton("Storage admin");
		buttonSTA.addActionListener(Core.getInstance().getActionManager().getOpenStorageAdmin());
		buttonUA = new JButton("User admin");
		buttonUA.addActionListener(Core.getInstance().getActionManager().getOpenUserAdmin());
		
		add(buttonSA, "span, pushx, growx, wrap");
		add(buttonOU, "sg a, split2, pushx, growx");
		add(buttonMA, "sg a, pushx, growx, wrap");
		add(buttonUA, "sg a, split2, pushx, growx");
		add(buttonSTA, "sg a, pushx, growx, wrap");
	}
}
