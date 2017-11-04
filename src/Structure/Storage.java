package Structure;

import java.util.ArrayList;

public class Storage extends InformationResource {
	private ArrayList<AbstractEntity> children;
	private String url, username, password;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private boolean SQL;
	
	public boolean isSQL() {
		return SQL;
	}

	public void setSQL(boolean sQL) {
		SQL = sQL;
	}

	public Storage() {
		super();
		children = new ArrayList<>();
		setInformationType("Storage");
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public ArrayList<AbstractEntity> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<AbstractEntity> children) {
		this.children = children;
	}
	public void addChild(AbstractEntity child){
		children.add(child);
	}
	public void removeChild(AbstractEntity child){
		children.remove(child);

		//FOR LATER DEVELOPMENT. REVISE THIS METHOD! USE INDEX
	}

	@Override
	public String toString(){
		return getName();
	}
}
