package model;

import java.io.File;
import java.util.Scanner;

import constants.EditorMetaSchemeConstants;

public class ModelGenerator
{
	private Scanner sc = null;
	private File f = null;
	
	private String repositoryGenerator = null;
	private String packageGenerator = null;
	private String entityGenerator = null;
	private String attributeGenerator = null;
	
	public ModelGenerator()
	{
		loadRepositoryGenerator();
		loadPackageGenerator();
		loadEntityGenerator();
		loadAttributeGenerator();
	}
	
	private void loadRepositoryGenerator()
	{
		f = new File(EditorMetaSchemeConstants.REPOSITORY_GENERATOR_PATH);
		
		if(!f.exists())
		{
			System.err.println("FILE " + f.toString() + " DOES NOT EXIST");
			return;
		}
		
		try
		{
			sc = new Scanner(f);
			repositoryGenerator = "";
			
			while(sc.hasNext())
			{
				repositoryGenerator = repositoryGenerator + sc.nextLine() + "\n";
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			sc.close();
		}
	}
	
	private void loadPackageGenerator()
	{
		f = new File(EditorMetaSchemeConstants.PACKAGE_GENERATOR_PATH);
		
		if(!f.exists())
		{
			System.err.println("FILE " + f.toString() + " DOES NOT EXIST");
			return;
		}
		
		try
		{
			sc = new Scanner(f);
			packageGenerator = "";
			
			while(sc.hasNext())
			{
				packageGenerator = packageGenerator + sc.nextLine() + "\n";
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			sc.close();
		}
	}
	
	private void loadEntityGenerator()
	{
		f = new File(EditorMetaSchemeConstants.ENTITY_GENERATOR_PATH);
		
		if(!f.exists())
		{
			System.err.println("FILE " + f.toString() + " DOES NOT EXIST");
			return;
		}
		
		try
		{
			sc = new Scanner(f);
			entityGenerator = "";
			
			while(sc.hasNext())
			{
				entityGenerator = entityGenerator + sc.nextLine() + "\n";
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			sc.close();
		}
	}
	
	private void loadAttributeGenerator()
	{
		f = new File(EditorMetaSchemeConstants.ATTRIBUTE_GENERATOR_PATH);
		
		if(!f.exists())
		{
			System.err.println("FILE " + f.toString() + " DOES NOT EXIST");
			return;
		}
		
		try
		{
			sc = new Scanner(f);
			attributeGenerator = "";
			
			while(sc.hasNext())
			{
				attributeGenerator = attributeGenerator + sc.nextLine() + "\n";
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			sc.close();
		}
	}
	
	public String getRepositoryGenerator()
	{
		return repositoryGenerator;
	}

	public String getPackageGenerator()
	{
		return packageGenerator;
	}

	public String getEntityGenerator()
	{
		return entityGenerator;
	}

	public String getAttributeGenerator()
	{
		return attributeGenerator;
	}
}
