package actions;

import actions.generate.GenerateAttribute;
import actions.generate.GenerateEntity;
import actions.generate.GeneratePackage;
import actions.generate.GenerateRepository;
import actions.test.OpenMetaSchemeAdmin;
import actions.test.OpenOperativeUser;
import actions.test.OpenStorageAdmin;
import actions.test.OpenSystemAdmin;

public class ActionManager
{
	private Exit exit = null;
	private OpenMetaSchemeEditor openMetaSchemeEditor = null;
	private OpenAbout openAbout = null;
	private SetSystemLookAndFeel systemLookAndFeel = null;
	private SetSwingLookAndFeel swingLookAndFeel = null;
	private SetNimbusLookAndFeel nimbusLookAndFeel = null;
	private LogIn logIn = null;
	private SignOut signOut = null;
	private SaveMetaScheme saveMetaScheme = null;
	private ValidateMetaScheme validateMetaScheme = null;
	private CloseTab closeTab = null;
	private EditorTextChangeListener editorTextChangeListener = null;
	private OpenUsers openUsers = null;
	private OpenMetaSchemesView openMetaSchemesView = null;
	private NewMeta newMeta = null;
	private RemoveMeta removeMeta = null;
	private SelectPath selectPath = null;
	private ApplyMetaScheme applyMetaScheme = null;
	private DocChangeListener docChangeListener = null;
	private DialogMetaSchemeListener dialogMetaSchemeListener = null;
	private FinishMetaSchemeST finishMetaSchemeST = null;
	private DocChangeListenerNewMeta docChangeListenerNewMeta = null;
	private AddNewMetaScheme addNewMetaScheme = null;
	private NewMetaSelectPath newMetaSelectPath = null;
	private NewMetaSelectMetaScheme newMetaSelectMetaScheme = null;
	private SelectNewMetaScheme selectNewMetaScheme = null;
	private DialogNewMetaSchemeListener dialogNewMetaSchemeListener = null;
	private SelectMetaToInterpret selectMetaToInterpret = null;
	private ChangeMetaScheme changeMetaScheme = null;
	private OpenChooseMetaDialog openChooseMetaDialog = null;
	private GetLastPositionEditorMouse getLastPositionEditorMouse = null;
	private EditorTimer editorTimer = null;
	private CheckerAutoValidation checkerAutoValidation = null;
	private InfoUser infoUser = null;
	private RemoveUser removeUser = null;
	private AddUser addUser = null;
	private ApplyUser applyUser = null;
	private FinishUser finishUser = null;
	private DialogUserListner dialogUserListner = null;
	private DialogNewUserListener dialogNewUserListener = null;
	private DocNewUserListener docNewUserListener = null;
	private NewUser newUser = null;
	private Forgot forgot = null;
	private FetchTab fetchTab = null;
	private AddRecordTab addRecordTab = null;
	private DeleteRecordTab deleteRecordTab = null;
	private FindRecordTab findRecordTab = null;
	private SortMdiTab sortMdiTab = null;
	private SortMmTab sortMmTab = null;
	private MakeSekTab makeSekTab = null;
	
//	SQL
	private AddSqlRecord addSqlRecord;
	private DeleteSqlRecord deleteSqlRecord;
	private UpdateSqlRecord updateSqlRecord;
	private FindSqlRecord findSqlRecord;
	private SortSql sortSql;
	
	//	GENERATORS
	private GenerateRepository generateRepository = null;
	private GeneratePackage generatePackage = null;
	private GenerateEntity generateEntity = null;
	private GenerateAttribute generateAttribute = null;
	private ShowInfoOfMetaScheme showInfoOfMetaScheme = null;
	
	//	TEST
	private OpenSystemAdmin openSystemAdmin = null;
	private OpenOperativeUser openOperativeUser = null;
	private OpenMetaSchemeAdmin openMetaSchemeAdmin = null;
	private OpenStorageAdmin openStorageAdmin = null;
	private OpenUserAdmin openUserAdmin = null;
	
	public ActionManager()
	{
		exit = new Exit();
		openMetaSchemeEditor = new OpenMetaSchemeEditor();
		openAbout = new OpenAbout();
		systemLookAndFeel = new SetSystemLookAndFeel();
		swingLookAndFeel = new SetSwingLookAndFeel();
		nimbusLookAndFeel = new SetNimbusLookAndFeel();
		logIn = new LogIn();
		signOut = new SignOut();
		saveMetaScheme = new SaveMetaScheme();
		validateMetaScheme =  new ValidateMetaScheme();
		closeTab = new CloseTab();
		editorTextChangeListener = new EditorTextChangeListener();
		generateRepository = new GenerateRepository();
		generateEntity = new GenerateEntity();
		generatePackage = new GeneratePackage();
		generateAttribute = new GenerateAttribute();
		openUsers = new OpenUsers();
		openMetaSchemesView = new OpenMetaSchemesView();
		newMeta = new NewMeta();
		removeMeta = new RemoveMeta();
		showInfoOfMetaScheme = new ShowInfoOfMetaScheme();
		selectPath = new SelectPath();
		applyMetaScheme = new ApplyMetaScheme();
		docChangeListener = new DocChangeListener();
		dialogMetaSchemeListener = new DialogMetaSchemeListener();
		finishMetaSchemeST = new FinishMetaSchemeST();
		docChangeListenerNewMeta = new DocChangeListenerNewMeta();
		addNewMetaScheme = new AddNewMetaScheme();
		newMetaSelectMetaScheme = new NewMetaSelectMetaScheme();
		newMetaSelectPath = new NewMetaSelectPath();
		selectNewMetaScheme = new SelectNewMetaScheme();
		dialogNewMetaSchemeListener = new DialogNewMetaSchemeListener();
		selectMetaToInterpret = new SelectMetaToInterpret();
		changeMetaScheme = new ChangeMetaScheme();
		openChooseMetaDialog = new OpenChooseMetaDialog();
		getLastPositionEditorMouse = new GetLastPositionEditorMouse();
		editorTimer = new EditorTimer();
		checkerAutoValidation = new CheckerAutoValidation();
		infoUser = new InfoUser();
		addUser = new AddUser();
		removeUser = new RemoveUser();
		applyUser = new ApplyUser();
		finishUser = new FinishUser();
		dialogUserListner = new DialogUserListner();
		dialogNewUserListener = new DialogNewUserListener();
		docNewUserListener = new DocNewUserListener();
		newUser = new NewUser();
		forgot = new Forgot();
		fetchTab = new FetchTab();
		addRecordTab = new AddRecordTab();
		deleteRecordTab = new DeleteRecordTab();
		findRecordTab = new FindRecordTab();
		sortMdiTab = new SortMdiTab();
		sortMmTab = new SortMmTab();
		makeSekTab = new MakeSekTab();
		
//		TEST
		openSystemAdmin = new OpenSystemAdmin();
		openOperativeUser = new OpenOperativeUser();
		openMetaSchemeAdmin = new OpenMetaSchemeAdmin();
		openStorageAdmin = new OpenStorageAdmin();
		openUserAdmin = new OpenUserAdmin();
		
//		SQL
		updateSqlRecord = new UpdateSqlRecord();
		findSqlRecord = new FindSqlRecord();
		addSqlRecord = new AddSqlRecord();
		deleteSqlRecord = new DeleteSqlRecord();
		sortSql = new SortSql();
	}
	
	public SortSql getSortSql()
	{
		return sortSql;
	}

	public AddSqlRecord getAddSqlRecord()
	{
		return addSqlRecord;
	}

	public DeleteSqlRecord getDeleteSqlRecord()
	{
		return deleteSqlRecord;
	}

	public UpdateSqlRecord getUpdateSqlRecord()
	{
		return updateSqlRecord;
	}

	public FindSqlRecord getFindSqlRecord()
	{
		return findSqlRecord;
	}

	public MakeSekTab getMakeSekTab() {
		return makeSekTab;
	}

	public SortMmTab getSortMmTab() {
		return sortMmTab;
	}

	public SortMdiTab getSortMdiTab() {
		return sortMdiTab;
	}

	public FindRecordTab getFindRecordTab() {
		return findRecordTab;
	}

	public DeleteRecordTab getDeleteRecordTab() {
		return deleteRecordTab;
	}

	public AddRecordTab getAddRecordTab() {
		return addRecordTab;
	}

	public Forgot getForgot()
	{
		return forgot;
	}

	public FetchTab getFetchTab() {
		return fetchTab;
	}

	public NewUser getNewUser()
	{
		return newUser;
	}

	public DocNewUserListener getDocNewUserListener()
	{
		return docNewUserListener;
	}

	public DialogNewUserListener getDialogNewUserListener()
	{
		return dialogNewUserListener;
	}

	public DialogUserListner getDialogUserListner()
	{
		return dialogUserListner;
	}

	public OpenUserAdmin getOpenUserAdmin()
	{
		return openUserAdmin;
	}

	public ApplyUser getApplyUser()
	{
		return applyUser;
	}

	public FinishUser getFinishUser()
	{
		return finishUser;
	}

	public InfoUser getInfoUser()
	{
		return infoUser;
	}

	public RemoveUser getRemoveUser()
	{
		return removeUser;
	}

	public AddUser getAddUser()
	{
		return addUser;
	}

	public Exit getExit()
	{
		return exit;
	}
	
	public OpenMetaSchemeEditor getOpenMetaSchemeEditor()
	{
		return openMetaSchemeEditor;
	}
	
	public OpenAbout getOpenAbout()
	{
		return openAbout;
	}
	
	public SetSystemLookAndFeel getSystemLookAndFeel()
	{
		return systemLookAndFeel;
	}
	
	public SetSwingLookAndFeel getSwingLookAndFeel()
	{
		return swingLookAndFeel;
	}
	
	public SetNimbusLookAndFeel getNimbusLookAndFeel()
	{
		return nimbusLookAndFeel;
	}
	
	public LogIn getLogIn()
	{
		return logIn;
	}
	
	public SignOut getSignOut()
	{
		return signOut;
	}
	
	public SaveMetaScheme getSaveMetaScheme()
	{
		return saveMetaScheme;
	}
	
	public ValidateMetaScheme getValidateMetaScheme()
	{
		return validateMetaScheme;
	}
	
	public CloseTab getCloseTab()
	{
		return closeTab;
	}
	
	public EditorTextChangeListener getEditorTextChangeListener()
	{
		return editorTextChangeListener;
	}
	
	public OpenUsers getOpenUsers()
	{
		return openUsers;
	}
	
	public OpenMetaSchemesView getOpenMetaSchemesView()
	{
		return openMetaSchemesView;
	}
	
	public NewMeta getNewMeta()
	{
		return newMeta;
	}
	
	public RemoveMeta getRemoveMeta()
	{
		return removeMeta;
	}
	
	public SelectPath getSelectPath()
	{
		return selectPath;
	}
	
	public ApplyMetaScheme getApplyMetaScheme()
	{
		return applyMetaScheme;
	}
	
	public DocChangeListener getDocChangeListener()
	{
		return docChangeListener;
	}
	
	public DialogMetaSchemeListener getDialogMetaSchemeListener()
	{
		return dialogMetaSchemeListener;
	}
	
	public FinishMetaSchemeST getFinishMetaSchemeST()
	{
		return finishMetaSchemeST;
	}
	
	public DocChangeListenerNewMeta getDocChangeListenerNewMeta()
	{
		return docChangeListenerNewMeta;
	}
	
	public AddNewMetaScheme getAddNewMetaScheme()
	{
		return addNewMetaScheme;
	}
	
	public NewMetaSelectPath getNewMetaSelectPath()
	{
		return newMetaSelectPath;
	}
	
	public NewMetaSelectMetaScheme getNewMetaSelectMetaScheme()
	{
		return newMetaSelectMetaScheme;
	}
	
	public SelectNewMetaScheme getSelectNewMetaScheme()
	{
		return selectNewMetaScheme;
	}
	
	public DialogNewMetaSchemeListener getDialogNewMetaSchemeListener()
	{
		return dialogNewMetaSchemeListener;
	}
	
	public SelectMetaToInterpret getSelectMetaToInterpret()
	{
		return selectMetaToInterpret;
	}
	
	public ChangeMetaScheme getChangeMetaScheme()
	{
		return changeMetaScheme;
	}
	
	public OpenChooseMetaDialog getOpenChooseMetaDialog()
	{
		return openChooseMetaDialog;
	}
	
	public GetLastPositionEditorMouse getGetLastPositionEditorMouse()
	{
		return getLastPositionEditorMouse;
	}
	
	public EditorTimer getEditorTimer()
	{
		return editorTimer;
	}
	
	public CheckerAutoValidation getCheckerAutoValidation()
	{
		return checkerAutoValidation;
	}
	
	public GenerateRepository getGenerateRepository()
	{
		return generateRepository;
	}
	
	public GeneratePackage getGeneratePackage()
	{
		return generatePackage;
	}
	
	public GenerateEntity getGenerateEntity()
	{
		return generateEntity;
	}
	
	public GenerateAttribute getGenerateAttribute()
	{
		return generateAttribute;
	}
	
	public ShowInfoOfMetaScheme getShowInfoOfMetaScheme()
	{
		return showInfoOfMetaScheme;
	}
	
	public OpenSystemAdmin getOpenSystemAdmin()
	{
		return openSystemAdmin;
	}
	
	public OpenOperativeUser getOpenOperativeUser()
	{
		return openOperativeUser;
	}
	
	public OpenMetaSchemeAdmin getOpenMetaSchemeAdmin()
	{
		return openMetaSchemeAdmin;
	}
	
	public OpenStorageAdmin getOpenStorageAdmin()
	{
		return openStorageAdmin;
	}
}
