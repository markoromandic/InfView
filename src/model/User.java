package model;

public class User
{
	private long id = -1;
	private String type = null;
	private String username = null;
	private String password = null;
	private String name = null;
	private String surname= null;
	private String email = null;
	
	public User(long id, String type, String username, String password, String name, String surname, String email)
	{
		super();
		this.id = id;
		this.type = type;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
	@Override
	public String toString()
	{
		return id + ";" + type + ";" + username + ";" + password + ";" + name + ";" + surname + ";" + email;
	}
}
