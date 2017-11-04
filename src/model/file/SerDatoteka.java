package model.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Vector;

import Structure.Attribute;
import Structure.Entity;

public class SerDatoteka extends IVAbstractFile
{
	public SerDatoteka(Entity entity)
	{
		super(entity);
	}
	
	@Override
	public ArrayList<Vector<String>> fetchNextBlock(long size, String absolutePath)
	{
//		size = 100;
		ArrayList<Vector<String>> v = new ArrayList<>();
		RandomAccessFile file;
		try
		{
			String entityPath = entity.getUrl();
			String entityName = entity.getName();
			
			
			RECORD_SIZE = bufferSizeCount();
			
			System.out.println("RECORD SIZE:"+RECORD_SIZE);
			
			BLOCK_FACTOR = size;
			
			file = new RandomAccessFile(absolutePath, "r");
		
			FILE_SIZE = file.length();
			RECORD_NUM = (long) Math.ceil((FILE_SIZE*1.0000) / (RECORD_SIZE*1.0000));
			
			BLOCK_NUM = (int)(RECORD_NUM /BLOCK_FACTOR) + 1;
			
			BUFFER_SIZE = 0;
			
			if(FILE_POINTER / RECORD_SIZE + BLOCK_FACTOR > RECORD_NUM)
				BUFFER_SIZE = (int)(RECORD_NUM - FILE_POINTER / RECORD_SIZE) * RECORD_SIZE;
			else
				BUFFER_SIZE = (int)(RECORD_SIZE * BLOCK_FACTOR);
		
			buffer = new byte[BUFFER_SIZE];
			
			data = new String[(int) BUFFER_SIZE / RECORD_SIZE][];
			
			file.seek(FILE_POINTER);
			file.read(buffer);
			
			String contentS = new String(buffer);
			if (contentS.length() < buffer.length)
			{
		    	  for (int x = contentS.length(); x < buffer.length; x++){
		    		  contentS = contentS + " ";
		    		  System.out.println(contentS);
		    	  }
		    }
			int pomocni = 0;
			contentS = contentS.replace("\n", " ").replace("\r",  " ");
			int fromStarting =0;
//			try{
			for (int i=0;i<BUFFER_SIZE/(RECORD_SIZE+2);i++)
			{
		    	 String line=contentS.substring(fromStarting+i*RECORD_SIZE,fromStarting+i*RECORD_SIZE+RECORD_SIZE);
		    	 fromStarting+=2;
		    	 data[i] = new String[entity.getChildrenAttributes().size()]; 
		    	 Vector<String> vec = new Vector<>();
		    	 int k=0;
				 for (int j=0;j<entity.getChildrenAttributes().size();j++){
					String field=null;
				   	field = line.substring(k, k + (int)entity.getChildrenAttributes().get(j).getLength());
					data[i][j]=field;
					vec.add(field);
					k= k + (int)entity.getChildrenAttributes().get(j).getLength();
				 }	
				 v.add(vec);
				 
		    }
			
			
			FILE_POINTER = file.getFilePointer();
			file.close();
		}
		catch (FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return v;
	}

	@Override
	public boolean addRecord(ArrayList<String> record)
	{
		String newRecord="\r\n";
		
		for (int i = 0; i < record.size(); i++)
		{
			newRecord = newRecord + record.get(i); 
		}
		
		String entityPath = entity.getUrl();
		String entityName = entity.getName();
		
		String absolutePath = entityPath + File.pathSeparator + entityName + ".txt";
		
		try
		{
			RandomAccessFile file;
			file = new RandomAccessFile(absolutePath,"rw");
			file.seek(file.length());
		    file.writeBytes(newRecord);
		    file.setLength(file.length());
		    file.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	    
	    return true;
	}

	@Override
	public boolean updateRecord(ArrayList<String> record)
	{
		return false;
	}

	@Override
	public boolean findRecord(ArrayList<String> searchRec, int[] position)
	{
		return false;
	}

	@Override
	public boolean deleteRecord(ArrayList<String> searchRec)
	{
		return false;
	}



	@Override
	public int bufferSizeCount()
	{
		int size = 0;
		for (Attribute a : entity.getChildrenAttributes())
		{
//			if(a.getValue().equals("CHAR"))
//				size += a.getLength();
//			else if(a.getValue().equals("VARCHAR"))
//				size += a.getLength();
////				size += 4 * a.getLength();
//			else if(a.getValue().equals("NUMERIC"))
//				size += a.getLength();
////				size += 4 * a.getLength();
			size += a.getLength();
			System.out.println("IN FOR SIZE:"+size);
		}
		System.out.println(entity.getName());
		System.out.println("THIS IS SIZE:"+size);
		return size;
	}
}
