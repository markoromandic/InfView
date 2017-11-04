package view.metaSchemeEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import appCore.Core;
import constants.AdminViewConstants;
import constants.Constants;
import constants.EditorMetaSchemeConstants;
import net.miginfocom.swing.MigLayout;
import view.buttonBar.IVButtonBar;
import view.dialogs.ChangeMeta;
import view.menuBar.IVMenuBar;
import view.tree.IVTree;

public class MainViewMA extends JFrame
{
	private MigLayout migLayout = null;
	
	private JTextPane textAreaEditor = null;
	private String username = null;
	private JButton buttonSave = null;
	private JButton buttonValidate = null;
	private JScrollPane scrollPaneEditor = null;
	private JSplitPane splitPaneTreeTextEditor = null;
	private IVTree treeMetaScheme = null;
	private IVMenuBar menuBar = null;
	private JPanel editorPanel = null;
	private JScrollPane treeScroll = null;
	private boolean isChanged = false;
	private int pos = 0;
	private TextLineNumber textLineNumber = null;
	private IVButtonBar generatorBar = null;
	private int lastPosition = 0;
	private ChangeMeta changeMeta = null;
	private boolean active = false;
	private JCheckBox cbAutoParser = null;
	private boolean timerStatus = false;

	public MainViewMA(String username)
	{
		this.username = username;
		initialize();
	}
	
	@Override
	public boolean isActive()
	{
		return active;
	}

	private void initialize()
	{
		initializeGUI();
		initializeMenu();
		initializeTree();
		initializeEditor();
		initializeButtonPanel();
		
		splitPaneTreeTextEditor = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, editorPanel);
		splitPaneTreeTextEditor.setDividerLocation(150);
		splitPaneTreeTextEditor.setOneTouchExpandable(true);
		splitPaneTreeTextEditor.setBorder(BorderFactory.createEtchedBorder());
		
		add(splitPaneTreeTextEditor, "push, grow");
	}
	
	private void initializeMenu()
	{
		menuBar = new IVMenuBar();
		setJMenuBar(menuBar);
	}
	
	private void initializeGUI()
	{
		migLayout = new MigLayout();
		
		setTitle(AdminViewConstants.META_SCHEME_EDITOR_TITLE + " - " + username);
		setVisible(true);
		setLayout(migLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon iconNotResized = new ImageIcon(AdminViewConstants.META_SCHEME_EDITOR_ICON);

		Image iconMetaSchemeEditor = iconNotResized.getImage();
		iconMetaSchemeEditor = iconMetaSchemeEditor.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		setIconImage(iconMetaSchemeEditor);
		
		setMinimumSize(new Dimension(800, 500));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		pack();
	}
	
	private void initializeTree()
	{
		treeMetaScheme = new IVTree();
		treeScroll = new JScrollPane(treeMetaScheme);
		treeScroll.setBorder(BorderFactory.createEmptyBorder());
		startTimer();
	}
	
	private void initializeButtonPanel()
	{
		buttonSave = new JButton(EditorMetaSchemeConstants.SAVE);
		buttonSave.addActionListener(Core.getInstance().getActionManager().getSaveMetaScheme());
		
		cbAutoParser = new JCheckBox(EditorMetaSchemeConstants.AUTO_VALIDATION);
		cbAutoParser.addItemListener(Core.getInstance().getActionManager().getCheckerAutoValidation());
		
		buttonValidate = new JButton(EditorMetaSchemeConstants.VALIDATE);
		buttonValidate.addActionListener(Core.getInstance().getActionManager().getValidateMetaScheme());
		
		editorPanel.add(buttonValidate, "tag apply, split3, sg 1");
		editorPanel.add(buttonSave, "tag finish, sg 1");
		editorPanel.add(cbAutoParser, "sg 1");
	}
	
	private void initializeEditor()
	{
		MigLayout editor = new MigLayout();
		
		editorPanel = new JPanel();
		editorPanel.setLayout(editor);
		
		textAreaEditor = new JTextPane();
		
		textAreaEditor.addCaretListener(Core.getInstance().getActionManager().getGetLastPositionEditorMouse());
		
		scrollPaneEditor = new JScrollPane(textAreaEditor);
		
		textLineNumber = new TextLineNumber(textAreaEditor);
		scrollPaneEditor.setRowHeaderView(textLineNumber);
		
		generatorBar = new IVButtonBar();
		
		editorPanel.add(generatorBar, "pushx, growx, wrap");
		
		editorPanel.add(scrollPaneEditor, "push, grow, wrap");
	}
	
	public void systemLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void crossPlatformLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void nimbusPlatformLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(Constants.NIMBUS);
			SwingUtilities.updateComponentTreeUI(this);
			pack();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadJSONToTextPane(Object metashema, ObjectMapper mapper)
	{
		try
		{
			textAreaEditor.setText(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(metashema));
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,Color.BLACK);
		
		textAreaEditor.selectAll();
		textAreaEditor.setCharacterAttributes(aSet, false);
		textAreaEditor.setCaretPosition(0);
		
		textAreaEditor.getDocument().addDocumentListener(Core.getInstance().getActionManager().getEditorTextChangeListener());
	}
	
	private void startTimer()
	{
		  Timer t = new Timer();
		  t.schedule(new TimerTask() 
		  {	
		      @Override public void run() 
		      {
		        	if(isChanged)
		        	{
		        		Core.getInstance().getActionManager().getEditorTimer().check();;
		        	}
		      }
		  }, 0L, 2000L);
	}

	public IVTree getTreeMetaScheme()
	{
		return treeMetaScheme;
	}

	synchronized public JTextPane getTextAreaEditor() {
		return textAreaEditor;
	}
	
	public String getJSON()
	{
		return textAreaEditor.getText();
	}
	
	synchronized public void edit()
	{
		treeMetaScheme.loadNewTreeModel();
		setButtonSaveStatus(true);
		isChanged = false;
	}
	
	public void findName(String find)
	{
		find = find.toLowerCase();
		
		textAreaEditor.requestFocusInWindow();
		
		if (find != null && find.length() > 0) 
		{
			Document document = textAreaEditor.getDocument();
			
			int findLength = find.length();
			
			try
			{
				boolean found = false;
				
				if(pos + findLength > document.getLength())
					pos = 0;
				
				while (pos + findLength <= document.getLength())
				{
					String match = document.getText(pos, findLength).toLowerCase();
					
					if (match.equals(find) && document.getText(pos + findLength, 1).equals("\"")) 
					{
                        found = true;
                        break;
                    }
                    pos++;
				}
				
				if(found)
				{
					Rectangle viewRect = textAreaEditor.modelToView(pos);
					
					textAreaEditor.scrollRectToVisible(viewRect);
					
					textAreaEditor.setCaretPosition(pos + findLength);
					
					textAreaEditor.moveCaretPosition(pos);
					
					pos += findLength;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void setButtonSaveStatus(boolean status)
	{
		buttonSave.setEnabled(status);
	}
	
	public void setLastPosition(int position)
	{
		lastPosition = position;
	}

	public ChangeMeta getChangeMeta()
	{
		return changeMeta;
	}
	
	public void openDialogToChangeMetaScheme()
	{
		active = true;
		changeMeta = new ChangeMeta(this);
	}
	
	public boolean checkAutoValidationStatus()
	{
		return cbAutoParser.isSelected();
	}
	
	public boolean isTimerStatus()
	{
		return timerStatus;
	}
	
	public void setTimerStatus(boolean status)
	{
		this.timerStatus = status;
	}

	public void setChanged(boolean isChanged)
	{
		this.isChanged = isChanged;
	}
}
