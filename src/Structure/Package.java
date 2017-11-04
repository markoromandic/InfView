package Structure;

import java.util.ArrayList;

public class Package extends InformationResource implements AbstractEntity {
	private ArrayList<AbstractEntity> children;
	private String url;
	
	
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
//		System.out.println("URL OD PACKAGE-a: " + url);
		this.url = url;
	}

	public Package() {
		super();
		children = new ArrayList<>();
		setInformationType("Package");
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
