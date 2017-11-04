package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import constants.Constants;
import constants.DialogConstants;
import constants.StorageAdminConstants;
import constants.UserType;

public class LogInModel
{
	private ArrayList<User> users = null;
	private ArrayList<MetaScheme> metaSchemes = null;
	private String logInUserType = null;
	private String username = null;
	private PrintWriter printWriter = null;
	private File locator = null;
	
	public LogInModel()
	{
		loadUsers();
	}
	
	private void loadUsers()
	{
		File f = new File(Constants.USERS);
		Scanner sc = null;
		
		if(!f.exists())
		{
			System.err.println(Constants.NOT_EXIST);
			return;
		}
		
		try
		{
			users = new ArrayList<>();
			sc = new Scanner(f);
			
			while(sc.hasNextLine())
			{
				String line = sc.nextLine();
				String[] a = line.split(";");
				
				try
				{
					long id = Long.parseLong(a[0]);
					String type = a[1];
					String username = a[2];
					String password = a[3];
					String name = a[4];
					String surname= a[5];
					String email = a[6];
					
					User u = new User(id, type, username, password, name, surname, email);
					
					users.add(u);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
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
	
	public void loadMetaSchemes()
	{
		locator = new File(StorageAdminConstants.URL);
		Scanner sc = null;
		
		if(!locator.exists())
		{
			System.err.println(Constants.NOT_EXIST + locator.getName() + "\"");
			return;
		}
		
		try
		{
			metaSchemes = new ArrayList<>();
			sc = new Scanner(locator);
			
			while(sc.hasNextLine())
			{
				String line = sc.nextLine();
				String split[] = line.split(";");
				
				long id = Long.parseLong(split[0]);
				
				String name = split[1];
				String url = split[2];
				String description = "";
				
				try
				{
					description = split[3];
				}
				catch (Exception e) 
				{
					description = "";
				}
				
				File file_check = new File(url + File.separator + name + DialogConstants.JSON_EXTENSION);
				
				if(!file_check.exists())
				{
					System.err.println(Constants.NOT_EXIST + file_check.getName() + "\"");
				}
				else
				{
					MetaScheme m = new MetaScheme(id, name, url, description);
					
					metaSchemes.add(m);
				}
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
	
	public String CheckUser(String username, String password)
	{
		String none = UserType.NONE;
		
		for (User u : users)
		{
			if(u.getUsername().equals(username) && u.getPassword().equals(password))
			{
				logInUserType = u.getType();
				this.username = u.getUsername();
				return u.getType() + ";" + u.getUsername();
			}
		}
		
		return none;
	}

	public String getUsername()
	{
		return username;
	}

	public String getLogInUserType()
	{
		return logInUserType;
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public ArrayList<MetaScheme> getMetaSchemes()
	{
		return metaSchemes;
	}
	
	public void saveMetaSchemesInfo()
	{
		File file = new File(StorageAdminConstants.URL);
		
		if (file.exists())
			file.delete();
		
		try
		{
			printWriter = new PrintWriter(file);

			for (MetaScheme m : metaSchemes)
			{
				printWriter.println(m.toString());
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			printWriter.close();
		}
	}
	
	public void removeMetaScheme(MetaScheme rm)
	{
		metaSchemes.remove(rm);
		saveMetaSchemesInfo();
	}
	
	public boolean addNewMeta(MetaScheme nm)
	{
		for (MetaScheme metaScheme : metaSchemes)
		{
			if (nm.getName().equals(metaScheme.getName()) && nm.getLocation().equals(metaScheme.getLocation()))
				return false;
		}
		
		metaSchemes.add(nm);
		saveMetaSchemesInfo();
		
		return true;
	}

	public File getLocator()
	{
		return locator;
	}
	
	public void saveUserInfo()
	{
		File file = new File(Constants.USERS);
		
		if (file.exists())
			file.delete();
		
		try
		{
			printWriter = new PrintWriter(file);

			for (User u : users)
			{
				printWriter.println(u.toString());
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			printWriter.close();
		}
	}
	
	private boolean checkUsername(String username)
	{
		for (User u : users)
		{
			if(u.getUsername().equals(username))
				return false;
		}
		return true;
	}
	
	private boolean checkEmail(String email, String usernameEx)
	{
		for (User u : users)
		{
			if(u.getEmail().equals(email) && !u.getUsername().equals(usernameEx))
				return false;
		}
		return true;
	}
	
	public boolean checkUser(String username, String email, String usernameEx)
	{
		if (checkUsername(username) && checkEmail(email, usernameEx))
			return true;
		return false;
	}
	
	public boolean checkNewUser(String username, String email)
	{
		if(checkUsername(username) && checkNewUserEmail(email))
			return true;
		return false;
	}
	
	private boolean checkNewUserEmail(String email)
	{
		for (User u : users)
		{
			if(u.getEmail().equals(email))
				return false;
		}
		return true;
	}
	
	public void removeUser(User ur)
	{
		users.remove(ur);
		saveUserInfo();
	}
	
	public void addNewUser(User nu)
	{
		users.add(nu);
	}
}