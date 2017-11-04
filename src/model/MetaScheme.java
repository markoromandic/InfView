package model;

public class MetaScheme
{
	private String name = null;
	private String location = null;
	private long id = -1;
	private String description = null;
	
	public MetaScheme(Long id, String name, String location, String description)
	{
		this.id = id;
		this.name = name;
		this.location = location;
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Long getId()
	{
		return id;
	}

	public String getLocation()
	{
		return location;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return id + ";" + name + ";" + location + ";" + description;
	}
}
