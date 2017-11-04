package model;

public class MainModel
{
	private LogInModel userModel = null;
	private TreeModel treeModel;
	private MetaSchemeInterpreter interpreter;
	private ModelGenerator modelGenerator = null;
	private DatabaseConnection databaseConnection;

	public MainModel()
	{
		initialize();
	}

	private void initialize()
	{
		userModel = new LogInModel();
	}
	
	public void startInterpreter()
	{
		treeModel = new TreeModel();
		interpreter = new MetaSchemeInterpreter();
		
		
//		NAPRAVITI IF RELACIONA BAZA, USPOSTAVITI KONEKCIJU
		
//		final String url = "jdbc:jtds:sqlserver://147.91.175.155";
//		final String database = "ui-2016-tim202.1";
//		final String username = "ui-2016-tim202.1";
//		final String password = "ui-2016-tim202.1.IoP994";
		
		databaseConnection = new DatabaseConnection();
	}
	
	public ModelGenerator getModelGenerator()
	{
		return modelGenerator;
	}

	public LogInModel getUserModel()
	{
		return userModel;
	}

	public TreeModel getTreeModel()
	{
		return treeModel;
	}

	public void loadFromJSONToRoot()
	{
		treeModel.setRoot(interpreter.ucitaj());
		interpreter.fillRelations();
	}
	
	public void loadFromJSONToRoot(String path)
	{
		treeModel.setRoot(interpreter.loadToTree(path));
		interpreter.fillRelations();
	}

	public MetaSchemeInterpreter getInterpreter()
	{
		return interpreter;
	}

	synchronized public void loadFromJSONEditorToTree()
	{
		treeModel.setRoot(interpreter.getStorage());
	}

	public void loadFirstTimeMetaEditJSON()
	{
		treeModel.setRoot(interpreter.ucitaj());
		interpreter.fillRelations();
	}

	public void initilazeModelGenerator()
	{
		modelGenerator = new ModelGenerator();
	}
	
	public DatabaseConnection getDatabaseConnection()
	{
		return databaseConnection;
	}
}
