package Structure;

import java.util.ArrayList;

public class Relation {
	private Entity childEntity;
//	private Attribute refered, reference;
	private ArrayList<Attribute> refered, reference;
	

	public Relation(Entity childEntity, ArrayList<Attribute> refered, ArrayList<Attribute> reference) {
		super();
		this.childEntity = childEntity;
		this.reference = reference;
		this.refered = refered;
	}

	public Relation() {
		super();
	}

	public Entity getChildEntity() {
		return childEntity;
	}

	public void setChildEntity(Entity childEntity) {
		this.childEntity = childEntity;
	}

	public ArrayList<Attribute> getRefered() {
		return refered;
	}

	public void setRefered(ArrayList<Attribute> refered) {
		this.refered = refered;
	}

	public ArrayList<Attribute> getReference() {
		return reference;
	}

	public void setReference(ArrayList<Attribute> reference) {
		this.reference = reference;
	}
	
}
