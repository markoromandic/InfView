package appCore;

import javax.swing.SwingUtilities;

import actions.ActionManager;
import model.MainModel;
import view.MainViewOU;
import view.dialogs.ChooseMeta;
import view.logIn.LogInView;
import view.metaSchemeEditor.MainViewMA;
import view.storageAdmin.StorageAdminView;
import view.systemAdmin.MainViewA;
import view.systemAdmin.UsersView;

public class Core
{
	public static Core instance = null;

	private ActionManager actionManager = null;
	private MainViewOU mainViewOu = null;
	private MainViewA mainViewA = null;
	private MainViewMA mainViewMa = null;
	private MainModel model = null;
	private StorageAdminView storageAdminView = null;
	private UsersView usersAdmin = null;
	private LogInView logInView = null;
	private ChooseMeta chooseMeta = null;
	
	private boolean op = false;
	private boolean ma = false;
	private boolean sa = false;
	private boolean st = false;
	private boolean su = false;
	
	public static Core getInstance()
	{
		if(instance == null)
		{
			instance = new Core();
			instance.initialize();
		}
		
		return instance;
	}

	private void initialize()
	{
		actionManager = new ActionManager();

		if(SwingUtilities.isEventDispatchThread())
		{
			logInView = new LogInView();
		}
		else
		{
		    SwingUtilities.invokeLater(new Runnable()
		    {
		        @Override
		        public void run()
		        {
		        	logInView = new LogInView();
		        }
		    });
		}
		
		model = new MainModel();
	}
	
	public LogInView getLogInView()
	{
		return logInView;
	}

	public MainModel getModel()
	{
		return model;
	}

	public ActionManager getActionManager()
	{
		return actionManager;
	}
	
	public void initializeMainViewOU(String username)
	{
		op = true;
		model.getUserModel().loadMetaSchemes();
		mainViewOu = new MainViewOU(username);
		chooseMeta = new ChooseMeta(mainViewOu);
	}

	public void initializeMainViewA(String username)
	{
		sa = true;
		mainViewA = new MainViewA(username);
	}
	
	public void initializeMainViewMA(String username)
	{
		ma = true;
		model.getUserModel().loadMetaSchemes();
		mainViewMa = new MainViewMA(username);
		chooseMeta = new ChooseMeta(mainViewMa);
	}
	
	public void initializeLogIn()
	{
		logInView = new LogInView();
	}
	
	public void initilaizeMainViewSTA(String username)
	{
		st = true;
		model.getUserModel().loadMetaSchemes();
		storageAdminView = new StorageAdminView(username);
	}

	public StorageAdminView getStorageAdminView()
	{
		return storageAdminView;
	}

	public ChooseMeta getChooseMeta()
	{
		return chooseMeta;
	}
	
	public MainViewOU getMainViewOu()
	{
		return mainViewOu;
	}

	public MainViewA getMainViewA()
	{
		return mainViewA;
	}

	public MainViewMA getMainViewMa()
	{
		return mainViewMa;
	}

//	ZA PROVERU
	public boolean isOp()
	{
		return op;
	}

	public boolean isMa()
	{
		return ma;
	}

	public boolean isSa()
	{
		return sa;
	}

	public boolean isSt()
	{
		return st;
	}
	
	
//	BRISANJE
	public void setMainViewOu() 
	{
		op = false;
		this.mainViewOu = null;
	}

	public void setMainViewA() 
	{
		sa = false;
		this.mainViewA = null;
	}

	public void setMainViewMa() 
	{
		ma = false;
		this.mainViewMa = null;
	}

	public void setLogInView() 
	{
		this.logInView = null;
	}
	
	public void setStorageAdminView()
	{
		st = false;
		this.storageAdminView = null;
	}
	
	public void setChooseMeta(ChooseMeta chooseMeta)
	{
		this.chooseMeta = chooseMeta;
	}
	
	public void initializeUserAdmin(String user)
	{
		su = true;
		usersAdmin = new UsersView(user);
	}
	
	public UsersView getUsersAdmin()
	{
		return usersAdmin;
	}

	public void setUsersAdmin()
	{
		su = false;
		this.usersAdmin = null;
	}

	public static void main(String[] args)
	{
		Core.getInstance();
	}

	public boolean isSu()
	{
		return su;
	}
	
	
}
