package Structure;

import java.net.URL;
import java.util.ArrayList;

public class Entity extends InformationResource implements AbstractEntity {
	private ArrayList<Attribute> childrenAttributes;
	private ArrayList<Relation> childrenRelations;
	private String dataBaseType;
	private String url, urlTree, urlOver;

	public Entity() {
		super();
		childrenAttributes = new ArrayList<>();
		childrenRelations = new ArrayList<>();
		setInformationType("Entity");
	}

	public ArrayList<Attribute> getChildrenAttributes() {
		return childrenAttributes;
	}

	public void setChildrenAttributes(ArrayList<Attribute> childrenAttributes) {
		this.childrenAttributes = childrenAttributes;
	}

	public ArrayList<Relation> getChildrenRelations() {
		return childrenRelations;
	}

	public void setChildrenRelations(ArrayList<Relation> childrenRelations) {
		this.childrenRelations = childrenRelations;
	}
	
	public void addChildAttribute(Attribute child){
		childrenAttributes.add(child);
	}
	
	public void removeChildAttribute(Attribute child){
		childrenAttributes.remove(child);
	}
	
	public void addChildRelation(Relation child){
		childrenRelations.add(child);
	}
	
	public void removeChildRelation(Relation child){
		childrenRelations.remove(child);
		//FOR LATER DEVELOPMENT. REVISE THIS METHOD! USE INDEX
	}
	
	@Override
	public String toString(){
		return getName();
	}
	
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDataBaseType()
	{
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType)
	{
		this.dataBaseType = dataBaseType;
	}

	public String getUrlTree() {
		return urlTree;
	}

	public void setUrlTree(String urlTree) {
		this.urlTree = urlTree;
	}

	public String getUrlOver() {
		return urlOver;
	}

	public void setUrlOver(String urlOver) {
		this.urlOver = urlOver;
	}
	
	
}
