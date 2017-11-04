package Structure;

public class Attribute extends InformationResource {
	private String type;
	private String value;
	private long length;
	private boolean mandatory, primary;
	
	
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public Attribute(String type, String value) {
		super();
		this.type = type;
		this.value = value;
		setInformationType("Attribute");
	}
	public Attribute() {
		super();
		type = "";
		value = "";
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return getName();
	}
	
	public long getLength()
	{
		return length;
	}
	public void setLength(long length)
	{
		this.length = length;
	}
	public boolean isMandatory()
	{
		return mandatory;
	}
	public void setMandatory(boolean mandatory)
	{
		this.mandatory = mandatory;
	}
}
