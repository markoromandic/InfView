package model.file;

import java.util.ArrayList;
import java.util.Vector;

import Structure.Entity;

public abstract class IVAbstractFile
{
	protected Entity entity;
	
	protected byte[] buffer;
	protected long FILE_SIZE = 0;
	protected long BLOCK_FACTOR=20;
	protected int BUFFER_SIZE=0;
	protected int RECORD_SIZE=0;
	protected int BLOCK_NUM=0;
	protected long RECORD_NUM=0;
	protected long FILE_POINTER=0;	
	protected String[][] data = null;
	protected String url="";
	
	public IVAbstractFile(Entity entity)
	{
		this.entity = entity;
	}

	public abstract int bufferSizeCount();
	
	public abstract ArrayList<Vector<String>> fetchNextBlock(long size, String url);

	public abstract boolean addRecord(ArrayList<String> record);

	public abstract boolean updateRecord(ArrayList<String> record);

	public abstract boolean findRecord(ArrayList<String> searchRec, int[] position);

	public abstract boolean deleteRecord(ArrayList<String> searchRec);

}
