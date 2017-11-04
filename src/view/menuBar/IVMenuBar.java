package view.menuBar;

import javax.swing.JMenuBar;

import view.menuBar.menu.IVMenuEdit;
import view.menuBar.menu.IVMenuFile;
import view.menuBar.menu.IVMenuHelp;
import view.menuBar.menu.IVMenuSettings;

public class IVMenuBar extends JMenuBar
{
	private IVMenuFile menuFile;
	private IVMenuEdit menuEdit;
	private IVMenuHelp menuHelp;
	private IVMenuSettings menuSettings;
	
	public IVMenuBar()
	{
		initializeMenus();
		add(menuFile);
		add(menuEdit);
		add(menuSettings);
		add(menuHelp);
	}
	
	private void initializeMenus()
	{
		menuFile = new IVMenuFile();
		menuEdit = new IVMenuEdit();
		menuHelp = new IVMenuHelp();
		menuSettings = new IVMenuSettings();
	}

	public IVMenuFile getMenuFile()
	{
		return menuFile;
	}

	public IVMenuEdit getMenuEdit()
	{
		return menuEdit;
	}

	public IVMenuHelp getMenuHelp()
	{
		return menuHelp;
	}

	public IVMenuSettings getMenuSettings()
	{
		return menuSettings;
	}
}
