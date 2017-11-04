package model.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Vector;

import Structure.Attribute;
import Structure.Entity;

public class SekDatoteka extends IVAbstractFile
{
	public SekDatoteka(Entity entity)
	{
		super(entity);
	}

	@Override
	public ArrayList<Vector<String>> fetchNextBlock(long size, String absolutePath)
	{
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRecord(ArrayList<String> record)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findRecord(ArrayList<String> searchRec, int[] position)
	{
		boolean result = false;
//		
//		while (FILE_POINTER < FILE_SIZE && position[0]==-1)
//		{
//			
//			if(!fetchNextBlock(BLOCK_FACTOR, url))
//			{
//				position[0]=-1;
//				return false;
//			}
//			
//			for (int row=0;row<data.length;row++)
//			{
//		           
//			    if (isRowEqual(data[row], searchRec))
//			    {
//	             	 position[0]=row;
//			    	 return true;
//			    }
//			    else if (isRowGreater(data[row], searchRec))
//			    {
//			    	 position[0]=row;
//			    	 
//			    	 return false;
//			    }
//		   }
//			
//		}
		
		return result;
	}

	public boolean findRecordFromBegginig(ArrayList<String> searchRec, int [] position)
	{
		long file_p = 0;
		
		boolean result = false;
		
//		while (file_p < FILE_SIZE && position[0]==-1)
//		{
//			
//			if(!fetchNextBlock(BLOCK_FACTOR))
//			{
//				position[0]=-1;
//				return false;
//			}
//			
//			for (int row=0;row<data.length;row++)
//			{
//		           
//			    if (isRowEqual(data[row], searchRec))
//			    {
//	             	 position[0]=row;
//			    	 return true;
//			    }
//			    else if (isRowGreater(data[row], searchRec))
//			    {
//			    	 position[0]=row;
//			    	 
//			    	 return false;
//			    }
//		   }
//			
//		}
		
		return result;
	}

	@Override
	public boolean deleteRecord(ArrayList<String> searchRec)
	{
		// TODO Auto-generated method stub
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
	
	private boolean isRowEqual(String [] aData, ArrayList<String> searchRec)
	{
		boolean result=true;
		   
		
	    for (int col=0; col < entity.getChildrenAttributes().size(); col++)
	    {
	    	if (!searchRec.get(col).trim().equals(""))
	    	{
	    		if (!aData[col].trim().equals(searchRec.get(col).trim()))
	    		{
	    			result=false;
	    			return result;
	    		}
	    	}
	    }	   
		   
	    return result;	
	}
	
	private boolean isRowGreater(String [] aData, ArrayList<String> searchRec)
	{
		boolean result=true;
		int noPK=0;
		boolean prev=true;
		   
		for (int i=0; i < entity.getChildrenAttributes().size(); i++)
		{
			if (!searchRec.get(i).trim().equals("") && !entity.getChildrenAttributes().get(i).isMandatory())
			{
				return false;	       
			}
			if (entity.getChildrenAttributes().get(i).isMandatory())
				noPK++;
			   
		}
		   
		for (int col=0; col < entity.getChildrenAttributes().size(); col++)
		{
			if (!searchRec.get(col).trim().equals(""))
			{
				if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) > 0 && col < noPK-1)
					return true;
				else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) !=0 && col < noPK-1)
				{
					result=false;
					prev=false;
				}
				else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim())<=0)
					result=false;
				else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim())>0 && prev && col==(noPK-1))
				  result=true;
			}
		}	   
		
		return result;
	}  
}
