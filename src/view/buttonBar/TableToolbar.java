package view.buttonBar;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.SortMdiTab;
import javafx.scene.control.Button;
import net.miginfocom.swing.MigLayout;

public class TableToolbar extends JPanel {
	
	private MigLayout migLayout = null;
	
	private JLabel lbFBlockFactor;
	private JLabel lbFileSize;
	private JLabel lbRecordSize;
	private JLabel lbRecordNum;
	private JLabel lbBlockNum;
	
	private JTextField tfFBlockFactor;
	private JTextField tfFileSize;
	private JTextField tfRecordSize;
	private JTextField tfRecordNum;
	private JTextField tfBlockNum;
	
	private JButton btFetchNextBlock;
	private JButton btAddRecord;
	private JButton btDeleteRecord;
	private JButton btFindRecord;
	private JButton btSortMdi;
	private JButton btSortMm;
	private JButton btMakeSek;
	
	private JButton btChangeF;
	
	
	public TableToolbar() {
		initialize();
	}
	
	private void initialize(){
		lbFBlockFactor = new JLabel("f (block factor)");
		lbFileSize = new JLabel("File size:");
		lbRecordSize = new JLabel("Record size(B):");
		lbRecordNum = new JLabel("Record Num:");
		lbBlockNum = new JLabel("Block num:");
		
		tfFBlockFactor = new JTextField();
		tfFileSize = new JTextField();
		tfRecordSize = new JTextField();
		tfRecordNum = new JTextField();
		tfBlockNum = new JTextField();
		
		btFetchNextBlock = new JButton("Fetch Next Block");
		btAddRecord = new JButton("Add Record");
		btDeleteRecord = new JButton("Delete Record");
		btFindRecord = new JButton("Find Record");
		btSortMdi = new JButton("Sort MDI");
		btSortMm = new JButton("Sort MM");
		btMakeSek = new JButton("Make .sek");
		
		btChangeF = new JButton("Change f");
		
		migLayout = new MigLayout();
		
		setBackground(new Color(153, 204, 255));
		
		setLayout(migLayout);
		add(lbFBlockFactor, "split11");
		add(tfFBlockFactor, "sg 1, pushX, growX");
		
		add(btChangeF);
		
		add(lbFileSize);
		add(tfFileSize, "sg 1, pushX, growX");
		
		add(lbRecordSize);
		add(tfRecordSize, "sg 1, pushX, growX");
		
		add(lbRecordNum);
		add(tfRecordNum, "sg 1, pushX, growX");
		
		add(lbBlockNum);
		add(tfBlockNum, "sg 1, pushX, growX, wrap");
		
		add(btFetchNextBlock, "split7");
		add(btAddRecord, "sg 2");
		add(btDeleteRecord, "sg 2");
		add(btFindRecord, "sg 2");
		add(btSortMdi, "sg 2");
		add(btSortMm, "sg 2");
		add(btMakeSek, "sg 2");
		
		
	}
}
